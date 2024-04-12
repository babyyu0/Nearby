package com.my.nearby.area.model.vo;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AreaVo {
    private final int areaCode;
    private final int sigunguCode;
    private final String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AreaVo(int areaCode, int sigunguCode, String name) {
        if (areaCode < 0 || sigunguCode < 0) {
            throw new RuntimeException("지역 코드가 유효하지 않습니다.");
        }
        if (name == null || name.isBlank()) {
            throw new RuntimeException("지역명이 유효하지 않습니다.");
        }
        this.areaCode = areaCode;
        this.sigunguCode = sigunguCode;
        this.name = name;
    }

    public AreaVo(int areaCode, int sigunguCode, String name, Timestamp createdAt, Timestamp updatedAt) {
        this(areaCode, sigunguCode, name);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
