package com.ssafy.trip.area.model.dto.response;

import com.ssafy.trip.area.model.entity.Sido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;


public record SidoGetResponseDto(
        @PositiveOrZero(message = "행정구역 코드는 0 이상이어야 합니다.")
        int code,
        @NotBlank(message = "행정구역 이름이 존재하지 않습니다.")
        String name
) {
    @Builder(access = AccessLevel.PRIVATE)
    public SidoGetResponseDto(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SidoGetResponseDto from(Sido sido) {
        return SidoGetResponseDto.builder()
                .code(sido.getSidoCode())
                .name(sido.getSidoName())
                .build();
    }
}
