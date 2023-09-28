package com.ssafy.trip.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trip.util.exception.common.CityInvalidException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CityResponse {
    public CityResponse (List<SidoResponse> sidoList, List<GugunResponse> gugunList) throws CityInvalidException {
        setSidoList(sidoList);
        setGugunList(gugunList);
    }

    private List<SidoResponse> sidoList;
    private List<GugunResponse> gugunList;

    public List<SidoResponse> getSidoList() {
        return sidoList;
    }
    public void setSidoList(List<SidoResponse> sidoList) throws CityInvalidException {
        if(sidoList == null || sidoList.size() == 0) {
            log.error("CityResponse: 시도 리스트 받기 실패");
            throw new CityInvalidException();
        }
        this.sidoList = sidoList;
    }

    public List<GugunResponse> getGugunList() {
        return gugunList;
    }
    public void setGugunList(List<GugunResponse> gugunList) throws CityInvalidException {
        if(gugunList == null || gugunList.size() == 0) {
            log.error("CityResponse: 구군 리스트 받기 실패");
            throw new CityInvalidException();
        }
        this.gugunList = gugunList;
    }
}
