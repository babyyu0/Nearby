package com.ssafy.trip.member.model.dto.request;

import com.ssafy.trip.global.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LogoutRequestDto(
        @NotBlank(message = "클라이언트 정보가 존재하지 않습니다.")
        String accessToken,
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id
) { }
