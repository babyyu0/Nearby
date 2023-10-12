package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.common.CityInvalidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SidoResponse {

    public SidoResponse(long sidoCode, String sidoName) throws CityInvalidException {
        setSidoCode(sidoCode);
        setSidoName(sidoName);
    }

    private long sidoCode;
    private String sidoName;

    public long getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(long sidoCode) throws CityInvalidException {
        if(sidoCode <= 0) {
            log.error("시도 번호 오류");
            throw new CityInvalidException();
        }
        this.sidoCode = sidoCode;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) throws CityInvalidException {
        if(sidoName == null || sidoName.trim().equals("")) {
            log.error("시도 이름 오류");
            throw new CityInvalidException();
        }
        this.sidoName = sidoName;
    }
}
