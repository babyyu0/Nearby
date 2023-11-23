package com.ssafy.trip.member.model.dto.request;

import com.ssafy.trip.global.util.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id,
        @NotBlank(message = "비밀번호가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.PASSWORD, message = "비밀번호가 올바르지 않습니다.")
        String password
) {
}
