package com.ssafy.trip.util.exception.common;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class TokenInvalidException extends MyException {

    private HttpStatus status = HttpStatus.FORBIDDEN;
    public TokenInvalidException() {
        super("사용자 확인을 실패 했습니다.");
    }
}
