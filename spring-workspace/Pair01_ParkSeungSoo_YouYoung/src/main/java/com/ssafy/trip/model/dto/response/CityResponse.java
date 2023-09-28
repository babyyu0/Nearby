package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.common.CityInvalidException;

import java.util.List;

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
        if(gugunList == null || gugunList.size() == 0) {
            throw new CityInvalidException();
        }
        this.sidoList = sidoList;
    }

    public List<GugunResponse> getGugunList() {
        return gugunList;
    }
    public void setGugunList(List<GugunResponse> gugunList) throws CityInvalidException {
        if(gugunList == null || gugunList.size() == 0) {
            throw new CityInvalidException();
        }
        this.gugunList = gugunList;
    }
}
