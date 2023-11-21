package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.model.entity.Gugun;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;

public record GugunGetResponseDto(
        @Positive(message = "지역(구, 군) 코드는 자연수여야 합니다.")
        int code,
        @NotBlank(message = "지역(구, 군) 이름이 존재하지 않습니다.")
        String name,
        @Positive(message = "지역(시, 도) 코드는 자연수여야 합니다.")
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
