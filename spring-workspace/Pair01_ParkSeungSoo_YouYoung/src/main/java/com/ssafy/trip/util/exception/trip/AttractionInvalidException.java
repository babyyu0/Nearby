package com.ssafy.trip.util.exception.trip;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class AttractionInvalidException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public AttractionInvalidException() {
        super("관광지가 유효하지 않습니다.");
    }
}
