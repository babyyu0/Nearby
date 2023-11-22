package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.model.entity.Sido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;


public record SidoGetResponseDto(
        @Positive(message = "행정구역 코드는 자연수여야 합니다.")
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
