package com.my.nearby.attraction.model.vo;

import com.my.nearby.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
public class CategoryVo {
    private final String code;
    private final String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CategoryVo(String code, String name) {
        if (code == null || code.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "분류 코드가 유효하지 않습니다.");
        }
        if (name == null || name.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "분류명이 유효하지 않습니다.");
        }
        this.code = code;
        this.name = name;
    }

    public CategoryVo(String code, String name, Timestamp createdAt, Timestamp updatedAt) {
        this(code, name);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
