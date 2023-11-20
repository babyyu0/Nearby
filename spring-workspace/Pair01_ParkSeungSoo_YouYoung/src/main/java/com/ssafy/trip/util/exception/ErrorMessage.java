package com.ssafy.trip.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    SIDO_INVALID(INTERNAL_SERVER_ERROR, "시도가 유효하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
