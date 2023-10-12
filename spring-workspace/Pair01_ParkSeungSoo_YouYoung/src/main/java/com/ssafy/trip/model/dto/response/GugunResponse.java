package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.trip.CityInvalidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class GugunResponse {
    private long gugunCode;
    private String gugunName;
    private long sidoCode;

    public static GugunResponseBuilder builder() {
        return GugunResponse.innerBuilder();
    }

    public GugunResponseBuilder gugunCode(long gugunCode) throws CityInvalidException {
        if(gugunCode <= 0) {
            log.error("GugunResponse: 구군 코드 입력 실패 " + gugunCode);
            throw new CityInvalidException();
        }
        return innerBuilder().gugunCode(gugunCode);
    }

    public GugunResponseBuilder gugunName(String gugunName) throws CityInvalidException {
        if(gugunName == null || gugunName.trim().equals("")) {
            log.error("GugunResponse: 지역 (구, 군) 이름 입력 실패 " + gugunName);
            throw new CityInvalidException();
        }
        return innerBuilder().gugunName(gugunName);
    }
}
