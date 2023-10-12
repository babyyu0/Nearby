package com.ssafy.trip.util.exception.common;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class CookieInvalidException extends MyException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    public CookieInvalidException() {
        super("쿠키가 존재하지 않습니다.");
    }
}
