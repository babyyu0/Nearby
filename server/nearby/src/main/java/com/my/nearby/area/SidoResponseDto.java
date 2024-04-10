package com.my.nearby.area;

import com.my.nearby.BusinessException;
import org.springframework.http.HttpStatus;

public record SidoResponseDto(
        int code,
        String name
) {
    public static SidoResponseDto from(SidoVo sido) {
        return new SidoResponseDto(sido.getCode(), sido.getName());
    }

    public SidoResponseDto(int code, String name) {
        if (code < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "행정구역이 유효하지 않습니다.");
        }
        if (name == null || name.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "행정구역이 유효하지 않습니다.");
        }

        this.code = code;
        this.name = name;
    }
}
