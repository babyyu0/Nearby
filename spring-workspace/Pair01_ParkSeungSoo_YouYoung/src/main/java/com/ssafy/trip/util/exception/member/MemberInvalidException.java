package com.ssafy.trip.util.exception.member;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class MemberInvalidException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public MemberInvalidException() {
        super("유효하지 않은 회원입니다.");
    }
    public MemberInvalidException(HttpStatus status) {
        super("잘못된 데이터가 입력 되었습니다.");
        this.status = status;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
