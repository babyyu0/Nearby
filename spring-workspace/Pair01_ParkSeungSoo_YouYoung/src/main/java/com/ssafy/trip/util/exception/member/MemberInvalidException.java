package com.ssafy.trip.util.exception.member;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class MemberInvalidException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public MemberInvalidException() {
        super("유효하지 않은 회원입니다.");
    }

    public HttpStatus getStatus() {
        return status;
    }
}
