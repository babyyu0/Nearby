package com.ssafy.trip.util.exception.member;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class MemberInvalidException extends MyException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    public MemberInvalidException() {
        super("잘못된 회원정보가 입력되었습니다.");
    }
}
