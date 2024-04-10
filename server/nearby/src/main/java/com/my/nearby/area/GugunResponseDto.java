package com.my.nearby.area;

import com.my.nearby.BusinessException;
import org.springframework.http.HttpStatus;

public record GugunResponseDto(
        int code,
        int sidoCode,
        String name
) {
    public static GugunResponseDto from(GugunVo gugun) {
        return new GugunResponseDto(gugun.getCode(), gugun.getSidoCode(), gugun.getName());
    }

    public GugunResponseDto(int code, int sidoCode, String name) {
        if(code < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "하위 행정구역이 유효하지 않습니다.");
        }
        if(sidoCode < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "하위 행정구역이 유효하지 않습니다.");
        }
        if(name == null || name.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "하위 행정구역이 유효하지 않습니다.");
        }

        this.code = code;
        this.sidoCode = sidoCode;
        this.name = name;
    }
}
