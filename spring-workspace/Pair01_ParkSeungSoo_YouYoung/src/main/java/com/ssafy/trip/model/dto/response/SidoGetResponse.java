package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.model.entity.Sido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SidoGetResponse(
        @Positive(message = "시도 코드는 자연수여야 합니다.")
        int code,
        @NotBlank(message = "시도명이 존재하지 않습니다.")
        String name
) {
    public static SidoGetResponse from(Sido sido) {
        return SidoGetResponse.builder()
                .code(sido.getSidoCode())
                .name(sido.getSidoName())
                .build();
    }
}
