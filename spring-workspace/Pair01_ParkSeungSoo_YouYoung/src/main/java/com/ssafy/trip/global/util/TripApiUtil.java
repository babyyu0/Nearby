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
import com.ssafy.trip.attraction.model.entity.AttractionInfo;
import com.ssafy.trip.attraction.model.entity.ContentType;
import com.ssafy.trip.attraction.model.entity.ContentTypeEnum;
import com.ssafy.trip.attraction.model.repository.AttractionInfoRepository;
import com.ssafy.trip.attraction.model.repository.AttractionRepository;
import com.ssafy.trip.attraction.model.repository.CatRepository;
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
public class TripApiUtil {
    public static final String DEFAULT_PARAM = "&MobileOS=ETC&MobileApp=nearby&_type=json";

    public static HttpResponse<String> getResponse(String ApiUrl) {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(ApiUrl))
                    .GET().build();
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != HttpStatus.OK.value()) {
                log.debug("getResponse : API 호출 200 ok 아님");
                return null;
            }

            return response;
        } catch (IOException | InterruptedException e) {
            log.debug("getResponse : Response 받기 실패 - {}", e.getMessage());
            return null;
        }
    }
}
