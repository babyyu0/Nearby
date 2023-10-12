package com.ssafy.trip.util.exception.common;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends MyException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    public FileNotFoundException() {
        super("파일이 존재하지 않습니다.");
    }
}
