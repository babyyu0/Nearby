package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.model.entity.Sido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;


public record SidoGetResponse(
        @Positive(message = "지역 (시, 도) 코드는 자연수여야 합니다.")
        int code,
        @NotBlank(message = "지역 (시, 도) 이름이 존재하지 않습니다.")
        String name
) {
    @Builder(access = AccessLevel.PRIVATE)
    public SidoGetResponse(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SidoGetResponse from(Sido sido) {
        return SidoGetResponse.builder()
                .code(sido.getSidoCode())
                .name(sido.getSidoName())
                .build();
    }
}
