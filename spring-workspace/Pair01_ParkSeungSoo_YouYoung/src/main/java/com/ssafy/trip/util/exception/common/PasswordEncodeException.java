package com.ssafy.trip.util.exception.common;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class PasswordEncodeException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public PasswordEncodeException() {
        super("비밀번호 암호화에 실패 했습니다.");
    }

    public HttpStatus getStatus() {
        return status;
    }
}
