package com.my.nearby;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    public HttpStatus status;
    public String message;

    @Builder
    public BusinessException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
