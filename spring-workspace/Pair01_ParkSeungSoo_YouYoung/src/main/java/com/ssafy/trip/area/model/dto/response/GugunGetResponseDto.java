package com.ssafy.trip.area.model.dto.response;

import com.ssafy.trip.area.model.entity.Gugun;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;

public record GugunGetResponseDto(
        @PositiveOrZero(message = "하위 행정구역 코드는 0 이상이어야 합니다.")
        int code,
        @NotBlank(message = "하위 행정구역 이름이 존재하지 않습니다.")
        String name,
        @PositiveOrZero(message = "행정구역 코드는 0 이상이어야 합니다.")
        int sidoCode
) {
    @Builder(access = AccessLevel.PRIVATE)
    public GugunGetResponseDto(int code, String name, int sidoCode) {
        this.code = code;
        this.name = name;
        this.sidoCode = sidoCode;
    }

    public static GugunGetResponseDto from(Gugun gugun) {
        return GugunGetResponseDto.builder()
                .code(gugun.getGugunCode())
                .name(gugun.getGugunName())
                .sidoCode(gugun.getSido().getSidoCode())
                .build();
    }
}
