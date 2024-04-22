package com.my.nearby.attraction.model.dto;

import com.my.nearby.attraction.model.vo.CategoryVo;

public record CategoryResponseDto(
        String code,
        String name
) {
    public static CategoryResponseDto from (CategoryVo category) {
        return new CategoryResponseDto(category.getCode(), category.getName());
    }
    public CategoryResponseDto(String code, String name) {
        if (code == null || code.isBlank()) {
            throw new RuntimeException("분류 코드가 유효하지 않습니다.");
        }
        if (name == null || name.isBlank()) {
            throw new RuntimeException("분류명이 유효하지 않습니다.");
        }
        this.code = code;
        this.name = name;
    }
}
