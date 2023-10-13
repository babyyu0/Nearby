package com.ssafy.trip.util.exception.member;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MyException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    public MemberNotFoundException() {
        super("회원을 찾을 수 없습니다.");
    }
}
