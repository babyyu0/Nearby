package com.ssafy.trip.area.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.area.model.dto.response.SidoGetResponseDto;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.repository.SidoRepository;
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
public class SidoServiceImpl implements SidoService {
    private final SidoRepository sidoRepository;
    @Value("${url.attraction.api}")
    private String API_URL;
    @Value("${parameter.attraction.api.key}")
    private String API_KEY;

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean refreshSido() {
        String sidoUrl = API_URL + "/areaCode1?serviceKey=" + API_KEY
                + TripApiUtil.DEFAULT_PARAM  // 기본 URL
                + "&numOfRows=100";

        HttpResponse<String> response;
        if ((response = TripApiUtil.getResponse(sidoUrl)) == null) {
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }
        ObjectMapper objectmapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectmapper.readTree(response.body())
                    .get("response").get("body")
                    .get("items").get("item");

            Sido sido;
            if (jsonNode.isArray()) {
                for (JsonNode sidoNode : jsonNode) {
                    sido = Sido.builder()
                            .sidoCode(sidoNode.get("code").asInt())
                            .sidoName(sidoNode.get("name").asText())
                            .build();
                    ValidateUtil.serverValidate(sido);
                    sidoRepository.save(sido);
                }
            }
        } catch (JsonProcessingException e) {
            log.debug("refreshSido : 데이터 뽑기 실패 - {}", e.getMessage());
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SidoGetResponseDto> getSidoList() {
        List<Sido> sidoList = sidoRepository.findAll();

        if (sidoList.isEmpty()) {
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        List<SidoGetResponseDto> sidoResponseDtoList = new ArrayList<>();

        SidoGetResponseDto sidoGetResponseDto;
        for (Sido sido : sidoList) {
            sidoGetResponseDto = SidoGetResponseDto.from(sido);
            ValidateUtil.serverValidate(sidoGetResponseDto);  // 유효성 검사
            sidoResponseDtoList.add(sidoGetResponseDto);
        }

        return sidoResponseDtoList;
    }

}
