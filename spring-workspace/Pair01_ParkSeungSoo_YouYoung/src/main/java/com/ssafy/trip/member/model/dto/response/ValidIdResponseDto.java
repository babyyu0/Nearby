package com.ssafy.trip.member.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record ValidIdResponseDto(
        boolean valid,
        @NotBlank(message = "메시지가 존재하지 않습니다.")
        String message
) {
    @Builder
    public ValidIdResponseDto(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
}
