package com.ssafy.trip.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.repository.GugunRepository;
import com.ssafy.trip.area.model.repository.SidoRepository;
import com.ssafy.trip.attraction.model.entity.ContentType;
import com.ssafy.trip.attraction.model.entity.ContentTypeEnum;
import com.ssafy.trip.attraction.model.repository.ContentTypeRepository;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
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
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;
    @Value("${url.attraction.api.sido}")
    private String SIDO_URL;
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
            System.out.println("contentTypeEnum: " + contentTypeEnum);
            System.out.println("contentTypeEnum.getCode(): " + contentTypeEnum.getCode());
            ContentType contentType = ContentType.builder()
                    .code(contentTypeEnum.getCode())
                    .name(contentTypeEnum.getName())
                    .build();
            ValidateUtil.serverValidate(contentType);

            contentTypeRepository.save(contentType);
        }
    }

    public void setSido() {
        HttpClient client = HttpClient.newBuilder().build();
        String sidoUrl = SIDO_URL + "?" + "serviceKey=" + API_KEY
                + "&MobileOS=" + OS
                + "&MobileApp=" + MOBILE_APP
                + "&_type=" + TYPE
                + "&numOfRows=100";

        log.debug("== setSido : SIDO URL : {}", sidoUrl);

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
                Sido sido = Sido.builder()
                        .sidoCode(curJsonNode.get("code").asInt())
                        .sidoName(curJsonNode.get("name").asText())
                        .build();

                ValidateUtil.serverValidate(sido);
                sidoRepository.save(sido);
            }
        } catch (JsonProcessingException e) {
            log.debug("setSido : 데이터 뽑기 실패 - {}", e.getMessage());
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }
    }

    public void setGugun() {
        List<Sido> sidoList = sidoRepository.findAll();
        if (sidoList == null) {
            log.debug("setGugun : SIDO List 없음");
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        HttpClient client = HttpClient.newBuilder().build();
        String gugunUrl;
        for (Sido sido : sidoList) {
            gugunUrl = SIDO_URL + "?" + "serviceKey=" + API_KEY
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

                JsonNode curJsonNode;
                Gugun gugun;
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
            } catch (JsonProcessingException e) {
                log.debug("setGugun : 데이터 뽑기 실패 - {}", e.getMessage());
                throw new MyException(ErrorMessage.GUGUN_INVALID);
            }
        }
    }
}
