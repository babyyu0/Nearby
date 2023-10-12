package com.ssafy.trip.util.exception.member;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class MemberCreateException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public MemberCreateException() {
        super("회원 생성에 실패 했습니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
