package com.ssafy.trip.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.entity.primary.GugunPk;
import com.ssafy.trip.area.model.repository.GugunRepository;
import com.ssafy.trip.area.model.repository.SidoRepository;
import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.entity.ContentType;
import com.ssafy.trip.attraction.model.entity.ContentTypeEnum;
import com.ssafy.trip.attraction.model.repository.AttractionRepository;
import com.ssafy.trip.attraction.model.repository.ContentTypeRepository;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TripUtil {

    private final ContentTypeRepository contentTypeRepository;
    private final AttractionRepository attractionRepository;
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;
    @Value("${url.attraction.api}")
    private String API_URL;
    @Value("${parameter.attraction.api.key}")
    private String API_KEY;
    @Value("${parameter.attraction.os}")
    private String OS;
    @Value("${parameter.attraction.mobile-app}")
    private String MOBILE_APP;
    @Value("${parameter.attraction.type}")
    private String TYPE;

    public void setContentType() {
        for (ContentTypeEnum contentTypeEnum : ContentTypeEnum.values()) {
            ContentType contentType = ContentType.builder()
                    .code(contentTypeEnum.getCode())
                    .name(contentTypeEnum.getName())
                    .build();
            ValidateUtil.serverValidate(contentType);

            contentTypeRepository.save(contentType);
        }
    }

    public void setSido() {
        // null값 삽입
        Sido sido = Sido.builder()
                .sidoCode(0)
                .sidoName("분류되지 않음")
                .build();

        ValidateUtil.serverValidate(sido);
        sidoRepository.save(sido);

        HttpClient client = HttpClient.newBuilder().build();
        String sidoUrl = API_URL + "/areaCode1" + "?" + "serviceKey=" + API_KEY
                + "&MobileOS=" + OS
                + "&MobileApp=" + MOBILE_APP
                + "&_type=" + TYPE
                + "&numOfRows=100";

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(sidoUrl))
                .GET().build();

        HttpResponse<String> response;  // Response 받을 변수
        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.debug("setSido : Response 받기 실패 - {}", e.getMessage());
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        // 응답 코드 확인
        int statusCode = response.statusCode();
        if (statusCode != HttpStatus.OK.value()) {
            log.debug("setSido : API 호출 200 ok 아님");
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        ObjectMapper objectmapper = new ObjectMapper();
        try {
            //  불러오기
            JsonNode jsonNode = objectmapper.readTree(response.body()).get("response").get("body").get("items")
                    .get("item");
            JsonNode curJsonNode;
            for (int i = 0; i < jsonNode.size(); i++) {
                curJsonNode = jsonNode.get(i);
                    sido = Sido.builder()
                        .sidoCode(curJsonNode.get("code").asInt())
                        .sidoName(curJsonNode.get("name").asText())
                        .build();

                ValidateUtil.serverValidate(sido);
                sidoRepository.save(sido);
            }
        } catch (JsonProcessingException | RuntimeException e) {
            log.debug("setSido : 데이터 뽑기 실패 - {}", e.getMessage());
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }
    }

    public void setGugun() {
        List<Sido> sidoList = sidoRepository.findAll();
        if (sidoList.isEmpty()) {
            log.debug("setGugun : SIDO List 없음");
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        HttpClient client = HttpClient.newBuilder().build();
        String gugunUrl;
        for (Sido sido : sidoList) {
            // null 값 삽입
            Gugun gugun = Gugun.builder()
                    .gugunCode(0)
                    .gugunName("분류되지 않음")
                    .sido(sido)
                    .build();

            ValidateUtil.serverValidate(gugun);
            gugunRepository.save(gugun);

            gugunUrl = API_URL + "/areaCode1" + "?" + "serviceKey=" + API_KEY
                    + "&MobileOS=" + OS
                    + "&MobileApp=" + MOBILE_APP
                    + "&_type=" + TYPE
                    + "&areaCode=" + sido.getSidoCode()
                    + "&numOfRows=100";

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(gugunUrl))
                    .GET().build();

            HttpResponse<String> response;  // Response 받을 변수
            try {
                response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                log.debug("setGugun : Response 받기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.GUGUN_INVALID);
            }

            // 응답 코드 확인
            int statusCode = response.statusCode();
            if (statusCode != HttpStatus.OK.value()) {
                log.debug("setGugun : API 호출 200 ok 아님");
                throw new MyException(ErrorMessage.GUGUN_INVALID);
            }

            ObjectMapper objectmapper = new ObjectMapper();
            try {
                //  불러오기
                JsonNode jsonNode = objectmapper.readTree(response.body())
                        .get("response").get("body")
                        .get("items").get("item");
                if(jsonNode == null) continue;

                JsonNode curJsonNode;
                for (int i = 0; i < jsonNode.size(); i++) {
                    curJsonNode = jsonNode.get(i);
                    gugun = Gugun.builder()
                            .gugunCode(curJsonNode.get("code").asInt())
                            .gugunName(curJsonNode.get("name").asText())
                            .sido(sido)
                            .build();

                    ValidateUtil.serverValidate(gugun);
                    gugunRepository.save(gugun);
                }
            } catch (JsonProcessingException | RuntimeException e) {
                log.debug("setGugun : 데이터 뽑기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.GUGUN_INVALID);
            }
        }
    }

    public void setAttraction() {
        HttpClient client = HttpClient.newBuilder().build();
        String attractionUrl;
        long pageNo = 1;

        while(true) {
            attractionUrl = API_URL + "/areaBasedSyncList1" + "?" + "serviceKey=" + API_KEY
                    + "&MobileOS=" + OS
                    + "&MobileApp=" + MOBILE_APP
                    + "&_type=" + TYPE
                    + "&numOfRows=1000"
                    + "&pageNo=" + (pageNo++);

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(attractionUrl))
                    .GET().build();

            HttpResponse<String> response;  // Response 받을 변수
            try {
                response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                log.debug("setAttraction : Response 받기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.ATTRACTION_INVALID);
            }

            // 응답 코드 확인
            int statusCode = response.statusCode();
            if (statusCode != HttpStatus.OK.value()) {
                log.debug("setAttraction : API 호출 200 ok 아님");
                throw new MyException(ErrorMessage.ATTRACTION_INVALID);
            }

            ObjectMapper objectmapper = new ObjectMapper();

            try {
                if(objectmapper.readTree(response.body())
                        .get("response").get("body")
                        .get("numOfRows").asInt() <= 0) {  // 더이상 불러올 관광지가 없을 경우
                    break;
                }

                //  불러오기
                JsonNode jsonNode = objectmapper.readTree(response.body())
                        .get("response").get("body")
                        .get("items").get("item");

                JsonNode curJsonNode;
                Attraction attraction;
                Optional<Sido> sido;
                Optional<Gugun> gugun;
                for (int i = 0; i < jsonNode.size(); i++) {
                    curJsonNode = jsonNode.get(i);
                    gugun = gugunRepository.findById(GugunPk.builder()
                            .sido(sidoRepository.findById(curJsonNode.get("areacode").asInt())
                                    .orElseThrow(() -> {
                                        log.debug("setAttraction : 행정구역 코드 받기 실패");
                                        return new MyException(ErrorMessage.SIDO_INVALID);
                                    }))
                            .gugunCode(curJsonNode.get("sigungucode").asInt())
                            .build());
                    if (gugun.isEmpty()) {
                        gugun = gugunRepository.findById(GugunPk.builder()
                                .sido(sidoRepository.findById(curJsonNode.get("areacode").asInt())
                                        .orElseThrow(() -> {
                                            log.debug("setAttraction : 행정구역 코드 받기 실패");
                                            return new MyException(ErrorMessage.SIDO_INVALID);
                                        }))
                                .gugunCode(0)
                                .build());
                    }

                    attraction = Attraction.builder()
                            .code(curJsonNode.get("contentid").asInt())
                            .contentType(contentTypeRepository.findById(curJsonNode.get("contenttypeid").asInt())
                                    .orElseThrow(() -> {
                                        log.debug("setAttraction : 콘텐츠 타입 받기 실패");
                                        return new MyException(ErrorMessage.ATTRACTION_INVALID);
                                    }))
                            .title(curJsonNode.get("title").asText())
                            .gugun(gugun.orElseThrow(() -> {
                                log.debug("setAttraction : 하위 행정구역 코드 받기 실패");
                                return new MyException(ErrorMessage.GUGUN_INVALID);
                            }))
                            // .img(curJsonNode.get("title").asText())
                            .createdTime(curJsonNode.get("createdtime").asText())
                            .updatedTime(curJsonNode.get("modifiedtime").asText())
                            .build();

                    log.debug("attraction :: " + attraction);
                    ValidateUtil.serverValidate(attraction);
                    attractionRepository.save(attraction);
                }
            } catch (JsonProcessingException | RuntimeException e) {
                log.debug("setAttraction : 데이터 뽑기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.ATTRACTION_INVALID);
            }
        }
    }
}
