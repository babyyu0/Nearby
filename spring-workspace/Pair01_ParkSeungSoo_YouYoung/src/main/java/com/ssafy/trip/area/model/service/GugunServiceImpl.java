package com.ssafy.trip.area.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.area.model.dto.response.GugunGetResponseDto;
import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.repository.GugunRepository;
import com.ssafy.trip.area.model.repository.SidoRepository;
import com.ssafy.trip.global.util.TripApiUtil;
import com.ssafy.trip.global.util.ValidateUtil;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GugunServiceImpl implements GugunService {
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;
    @Value("${url.attraction.api}")
    private String API_URL;
    @Value("${parameter.attraction.api.key}")
    private String API_KEY;

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean refreshGugun() {
        List<Sido> sidoList = sidoRepository.findAll();
        for (Sido sido : sidoList) {
            Gugun gugun = Gugun.builder()
                    .gugunCode(0)
                    .gugunName("분류되지 않음")
                    .sido(sido)
                    .build();  // 기본값

            ValidateUtil.serverValidate(gugun);
            gugunRepository.save(gugun);

            String gugunUrl = API_URL + "/areaCode1?serviceKey=" + API_KEY
                    + TripApiUtil.DEFAULT_PARAM  // 기본 URL
                    + "&areaCode=" + sido.getSidoCode()
                    + "&numOfRows=100";

            HttpResponse<String> response;
            if ((response = TripApiUtil.getResponse(gugunUrl)) == null) {
                throw new MyException(ErrorMessage.GUGUN_INVALID);
            }
            ObjectMapper objectmapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectmapper.readTree(response.body())
                        .get("response").get("body")
                        .get("items").get("item");

                if (jsonNode.isArray()) {
                    for (JsonNode gugunNode : jsonNode) {
                        gugun = Gugun.builder()
                                .gugunCode(gugunNode.get("code").asInt())
                                .gugunName(gugunNode.get("name").asText())
                                .sido(sido)
                                .build();

                        ValidateUtil.serverValidate(sido);
                        gugunRepository.save(gugun);
                    }
                }
            } catch (JsonProcessingException e) {
                log.debug("refreshGugun : 데이터 뽑기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.GUGUN_INVALID);
            }
        }

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GugunGetResponseDto> getGugunList() {
        List<Gugun> gugunList = gugunRepository.findAll();

        if (gugunList.isEmpty()) {
            throw new MyException(ErrorMessage.GUGUN_INVALID);
        }

        List<GugunGetResponseDto> gugunResponseDtoList = new ArrayList<>();

        GugunGetResponseDto gugunGetResponseDto;
        for (Gugun gugun : gugunList) {
            gugunGetResponseDto = GugunGetResponseDto.from(gugun);
            ValidateUtil.serverValidate(gugunGetResponseDto);  // 유효성 검사
            gugunResponseDtoList.add(gugunGetResponseDto);
        }

        return gugunResponseDtoList;
    }

}
