package com.ssafy.trip.attraction.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.Tuple;
import com.ssafy.trip.area.util.AreaUtil;
import com.ssafy.trip.attraction.model.dto.command.AttractionDescRefreshCommandDto;
import com.ssafy.trip.attraction.model.dto.command.NearestAttractionCommandDto;
import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;
import com.ssafy.trip.attraction.model.entity.*;
import com.ssafy.trip.attraction.model.repository.*;
import com.ssafy.trip.global.data.RegexPattern;
import com.ssafy.trip.global.util.ImageUtil;
import com.ssafy.trip.global.util.TripApiUtil;
import com.ssafy.trip.global.util.ValidateUtil;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttractionServiceImpl implements AttractionService {

    private final ContentTypeRepository contentTypeRepository;
    private final CatRepository catRepository;
    private final AttractionRepository attractionRepository;
    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionDescRepository attractionDescRepository;
    private final AttractionHeartRepository attractionHeartRepository;

    @Value("${url.attraction.img}")
    private static String ATTRACTION_IMG_URI;
    @Value("${url.attraction.api}")
    private String API_URL;
    @Value("${parameter.attraction.api.key}")
    private String API_KEY;

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean refreshContentType() {
        for (ContentTypeEnum contentTypeEnum : ContentTypeEnum.values()) {  // Enum에 저장된 ContentType
            ContentType contentType = ContentType.builder()
                    .code(contentTypeEnum.getCode())
                    .name(contentTypeEnum.getName())
                    .build();
            ValidateUtil.serverValidate(contentType);

            contentTypeRepository.save(contentType);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean refreshCat(int depth, String cat1, String cat2) {
        String attractionUrl = API_URL + "/categoryCode1?serviceKey=" + API_KEY
                + TripApiUtil.DEFAULT_PARAM  // 기본 URL
                + "&numOfRows=1000"
                + "&cat1=" + cat1
                + "&cat2=" + cat2;

        HttpResponse<String> response;
        if ((response = TripApiUtil.getResponse(attractionUrl)) == null) {
            throw new MyException(ErrorMessage.CAT_INVALID);
        }
        ObjectMapper objectmapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectmapper.readTree(response.body())
                    .get("response").get("body")
                    .get("items").get("item");

            if (jsonNode.isArray()) {
                for (JsonNode catNode : jsonNode) {
                    String code = catNode.get("code").asText();

                    Cat cat = Cat.builder()
                            .code(code)
                            .name(catNode.get("name").asText())
                            .depth(depth)
                            .build();
                    ValidateUtil.serverValidate(cat);
                    catRepository.save(cat);

                    if (code.length() == 3) {
                        refreshCat(2, code, "");
                    } else if (code.length() == 5) {
                        refreshCat(3, code.substring(0, 3), code);
                    }
                }
            }
        } catch (JsonProcessingException e) {
            log.debug("refreshCat : 데이터 뽑기 실패 - {}", e.getMessage());
            throw new MyException(ErrorMessage.CAT_INVALID);
        }

        return true;
    }

    private String findTel(String rawTel) {
        String tel = rawTel
                .replace(" ", "")
                .replace("<br/>", ",")
                .replace("<br>", ",")
                .replace("\n", ",")
                .replace("\t", ",")
                .split(",")[0];
        return (tel.matches(RegexPattern.TEL) || tel.matches(RegexPattern.TEL2)) ? tel : "-";
    }

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean refreshAttraction() {
        int pageNo = 1;
        int numOfRows = 50000;  // 조회 데이터 수

        while (true) {
            String attractionUrl = API_URL + "/areaBasedSyncList1?serviceKey=" + API_KEY
                    + TripApiUtil.DEFAULT_PARAM  // 기본 URL
                    + "&numOfRows=" + numOfRows
                    + "&pageNo=" + (pageNo++);

            HttpResponse<String> response;
            if ((response = TripApiUtil.getResponse(attractionUrl)) == null) {
                throw new MyException(ErrorMessage.ATTRACTION_INVALID);
            }
            ObjectMapper objectmapper = new ObjectMapper();

            try {
                JsonNode jsonNode = objectmapper.readTree(response.body())
                        .get("response").get("body");

                JsonNode itemNodes = jsonNode.get("items").get("item");

                if (itemNodes.isArray()) {
                    for (JsonNode item : itemNodes) {
                        log.info("{}번 {} 저장 시작", item.get("contentid").asText(), item.get("title").asText());

                        ContentType contentType = contentTypeRepository.findById(item.get("contenttypeid").asInt())
                                .orElseThrow(() -> {
                                    log.debug("refreshAttraction : 콘텐츠 타입 받기 실패");
                                    return new MyException(ErrorMessage.ATTRACTION_INVALID);
                                });
                        int sidoCode = (item.get("areacode").asInt() <= 50) ? item.get("areacode").asInt() : 0;  // sido code 값
                        int gugunCode = (item.get("sigungucode").asInt() <= 50) ? item.get("sigungucode").asInt() : 0;  // gugun 코드 값

                        Attraction attraction = Attraction.builder()
                                .code(item.get("contentid").asInt())
                                .contentType(contentType)
                                .title(item.get("title").asText())
                                .gugun(AreaUtil.getGugun(sidoCode, gugunCode))
                                .img(item.get("firstimage").asText())
                                .imgSub(item.get("firstimage2").asText())
                                .createdTime(item.get("createdtime").asText())
                                .updatedTime(item.get("modifiedtime").asText())
                                .build();
                        ValidateUtil.serverValidate(attraction);
                        attraction = attractionRepository.save(attraction);

                        AttractionInfo attractionInfo = AttractionInfo.builder()
                                .code(attraction.getCode())
                                .addr1(item.get("addr1").asText())
                                .addr2(item.get("addr2").asText())
                                .tel(findTel(item.get("tel").asText()))
                                .longitude(item.get("mapx") != null ? item.get("mapx").asDouble() : 0)
                                .latitude(item.get("mapy") != null ? item.get("mapy").asDouble() : 0)
                                .cat1(catRepository.findById(item.get("cat1").asText()).orElse(null))
                                .cat2(catRepository.findById(item.get("cat2").asText()).orElse(null))
                                .cat3(catRepository.findById(item.get("cat3").asText()).orElse(null))
                                .build();

                        ValidateUtil.serverValidate(attractionInfo);
                        attractionInfoRepository.save(attractionInfo);

                    }
                }

                if (jsonNode.get("numOfRows").asInt() < numOfRows) break;  // 반복문 종료 지점

            } catch (JsonProcessingException e) {
                log.error("refreshAttraction : 데이터 뽑기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.ATTRACTION_INVALID);
            }
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean refreshAttractionDesc(AttractionDescRefreshCommandDto attractionDescRefreshCommandDto) {
        Attraction attraction = attractionRepository.findById(attractionDescRefreshCommandDto.attractionCode())
                .orElseThrow(() -> {
                    log.debug("refreshAttractionDesc : 관광지 조회 실패");
                    return new MyException(ErrorMessage.ATTRACTION_NOT_FOUND);
                });
        String attractionDescUrl = API_URL + "/detailCommon1?serviceKey=" + API_KEY
                + TripApiUtil.DEFAULT_PARAM  // 기본 URL
                + "&contentId=" + attractionDescRefreshCommandDto.attractionCode()
                + "&overviewYN=Y";

        HttpResponse<String> descResponse;
        if ((descResponse = TripApiUtil.getResponse(attractionDescUrl)) == null) {
            throw new MyException(ErrorMessage.ATTRACTION_INVALID);
        }
        ObjectMapper objectmapper = new ObjectMapper();

        try {
            String desc = (objectmapper.readTree(descResponse.body())
                    .get("response").get("body").get("totalCount").asInt() <= 0) ? "-"  // 설명 없을 경우 빈 칸
                    : objectmapper.readTree(descResponse.body())
                    .get("response").get("body").get("items").get("item")
                    .get(0).get("overview").asText();  // 설명 삽입*/

            AttractionDesc attractionDesc = AttractionDesc.builder()
                    .code(attraction.getCode())
                    .desc(desc)
                    .build();
            ValidateUtil.serverValidate(attractionDesc);
            attractionDescRepository.save(attractionDesc);
        } catch (JsonProcessingException e) {
            log.error("refreshAttractionDesc : 데이터 뽑기 실패 - {}", e.getMessage());
            throw new MyException(ErrorMessage.ATTRACTION_INVALID);
        }

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttractionGetResponseDto> getNearestAttraction(NearestAttractionCommandDto nearestAttractionCommandDto) {
        List<Tuple> attractionNearestList = attractionRepository.findAllByOrderByMeterAsc(nearestAttractionCommandDto.latitude(), nearestAttractionCommandDto.longitude());

        List<AttractionGetResponseDto> attractionGetResponseDtoList = new ArrayList<>();

        AttractionGetResponseDto attractionGetResponseDto;
        Attraction attraction;
        String dist;
        int heart;
        for (Tuple attractionTuple : attractionNearestList) {
            attraction = attractionTuple.get(0, Attraction.class);
            heart = attractionHeartRepository.countAllByAttraction(attraction);
            dist = attractionTuple.get(1, String.class);  // TO DO :: KM 계산하기

            attractionGetResponseDto = AttractionGetResponseDto.builder()
                    .code(attraction.getCode())
                    .title(attraction.getTitle())
                    .km(dist == null ? -1.0 : Double.parseDouble(dist))
                    .heart(heart)
                    .img(ImageUtil.toByteArray(ATTRACTION_IMG_URI, attraction.getImg()))
                    .build();
            ValidateUtil.serverValidate(attractionGetResponseDto);

            attractionGetResponseDtoList.add(attractionGetResponseDto);
        }

        return attractionGetResponseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttractionGetResponseDto> getPopularAttraction() {
        List<Tuple> attractionHeartList = attractionHeartRepository.findAllByOrderByCountDesc();

        List<AttractionGetResponseDto> attractionGetResponseDtoList = new ArrayList<>();

        AttractionGetResponseDto attractionGetResponseDto;
        Attraction attraction;
        double km;
        String heart;
        for (Tuple attractionTuple : attractionHeartList) {
            attraction = attractionTuple.get(0, Attraction.class);
            heart = attractionTuple.get(1, String.class);
            km = 1;  // TO DO :: KM 계산하기

            attractionGetResponseDto = AttractionGetResponseDto.builder()
                    .code(attraction.getCode())
                    .title(attraction.getTitle())
                    .km(km)
                    .heart(heart == null ? 0 : Integer.parseInt(heart))
                    .img(ImageUtil.toByteArray(ATTRACTION_IMG_URI, attraction.getImg()))
                    .build();
            ValidateUtil.serverValidate(attractionGetResponseDto);

            attractionGetResponseDtoList.add(attractionGetResponseDto);
        }

        return attractionGetResponseDtoList;
    }
}
