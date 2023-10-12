package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.common.CityInvalidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GugunResponse {
    public GugunResponse(long gugunCode, String gugunName, long sidoCode) throws CityInvalidException {
        setGugunCode(gugunCode);
        setGugunName(gugunName);
        setSidoCode(sidoCode);
    }
    private long gugunCode;
    private String gugunName;
    private long sidoCode;

    public long getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(long gugunCode) throws CityInvalidException {
        if(gugunCode <= 0) {
            log.error("GugunResponse: 구군 코드 받기 실패");
            throw new CityInvalidException();
        }
        this.gugunCode = gugunCode;
    }

    public String getGugunName() {
        return gugunName;
    }

    public void setGugunName(String gugunName) throws CityInvalidException {
        if(gugunName == null || gugunName.trim().equals("")) {
            log.error("GugunResponse: 구군 이름 받기 실패");
            throw new CityInvalidException();
        }
        this.gugunName = gugunName;
    }
    public long getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(long sidoCode) throws CityInvalidException {
        if(sidoCode <= 0) {
            log.error("GugunResponse: 시도 이름 받기 실패");
            throw new CityInvalidException();
        }
        this.sidoCode = sidoCode;
    }
}
