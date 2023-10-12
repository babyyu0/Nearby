package com.ssafy.trip.util.exception.common;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class MessageInvalidException extends MyException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public MessageInvalidException() {
        super("메시지가 유효하지 않습니다.");
    }
}
