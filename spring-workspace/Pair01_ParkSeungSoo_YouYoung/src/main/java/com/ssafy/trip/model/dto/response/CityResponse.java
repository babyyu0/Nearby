package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.trip.CityInvalidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class CityResponse {

    private List<SidoResponse> sidoList;
    private List<GugunResponse> gugunList;

    public static CityResponseBuilder builder() {
        return CityResponse.innerBuilder();
    }
    public CityResponseBuilder sidoList(List<SidoResponse> sidoList) throws CityInvalidException {
        if(sidoList == null || sidoList.size() == 0) {
            log.error("CityResponse: 시도 리스트 받기 실패");
            throw new CityInvalidException();
        }
        return innerBuilder().sidoList(sidoList);
    }

    public CityResponseBuilder gugunList(List<GugunResponse> gugunList) throws CityInvalidException {
        if(gugunList == null || gugunList.size() == 0) {
            log.error("CityResponse: 구군 리스트 받기 실패");
            throw new CityInvalidException();
        }
        return innerBuilder().gugunList(gugunList);
    }
}
