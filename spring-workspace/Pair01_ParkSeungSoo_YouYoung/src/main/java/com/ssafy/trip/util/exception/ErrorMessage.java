package com.ssafy.trip.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    TOKEN_NOT_FOUND(BAD_REQUEST, "회원을 찾을 수 없습니다."),
    TOKEN_EXPIRED(BAD_REQUEST, "로그인이 만료 되었습니다."),
    IMAGE_NOT_FOUND(BAD_REQUEST, "이미지를 찾을 수 없습니다"),
    IMAGE_INVALID(BAD_REQUEST, "이미지가 유효하지 않습니다"),
    ID_DUPLICATED(BAD_REQUEST, "중복된 아이디 입니다."),
    MEMBER_NOT_FOUND(BAD_REQUEST, "회원을 찾을 수 없습니다."),
    SIDO_NOT_FOUND(BAD_REQUEST, "지역(시, 도)을 찾을 수 없습니다"),
    GUGUN_NOT_FOUND(BAD_REQUEST, "지역(구, 군)을 찾을 수 없습니다"),
    SIDO_INVALID(INTERNAL_SERVER_ERROR, "지역(시, 도)이 유효하지 않습니다."),
    GUGUN_INVALID(INTERNAL_SERVER_ERROR, "지역(구, 군)이 유효하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
