package com.ssafy.trip.util.exception.trip;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class CityInvalidException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public CityInvalidException() {
        super("지역이 유효하지 않습니다.");
    }
}
