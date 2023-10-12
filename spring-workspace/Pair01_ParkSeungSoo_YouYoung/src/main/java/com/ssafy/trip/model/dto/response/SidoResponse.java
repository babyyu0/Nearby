package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.util.exception.trip.CityInvalidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class SidoResponse {
    private long sidoCode;
    private String sidoName;

    public static SidoResponseBuilder builder() {
        return SidoResponse.innerBuilder();
    }

    public SidoResponseBuilder sidoCode(long sidoCode) throws CityInvalidException {
        if(sidoCode <= 0) {
            log.error("SidoResponse: 지역 (시, 도) 번호 입력 실패 " + sidoCode);
            throw new CityInvalidException();
        }
        return innerBuilder().sidoCode(sidoCode);
    }
    public SidoResponseBuilder sidoName(String sidoName) throws CityInvalidException {
        if(sidoName == null || sidoName.trim().equals("")) {
            log.error("SidoResponse: 지역 (시, 도) 이름 입력 실패 " + sidoName);
            throw new CityInvalidException();
        }
        return innerBuilder().sidoName(sidoName);
    }
}
