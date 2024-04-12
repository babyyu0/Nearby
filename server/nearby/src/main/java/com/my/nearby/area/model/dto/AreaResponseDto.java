package com.my.nearby.area.model.dto;

import com.my.nearby.BusinessException;
import com.my.nearby.area.model.vo.AreaVo;
import org.springframework.http.HttpStatus;

public record AreaResponseDto(
        int areaCode,
        int sigunguCode,
        String name
) {
    public static AreaResponseDto from(AreaVo area) {
        return new AreaResponseDto(area.getAreaCode(), area.getSigunguCode(), area.getName());
    }

    public AreaResponseDto(int areaCode, int sigunguCode, String name) {
        if (areaCode < 0 || sigunguCode < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "지역 정보가 유효하지 않습니다.");
        }
        if (name == null || name.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "지역 정보가 유효하지 않습니다.");
        }

        this.areaCode = areaCode;
        this.sigunguCode = sigunguCode;
        this.name = name;
    }
}
