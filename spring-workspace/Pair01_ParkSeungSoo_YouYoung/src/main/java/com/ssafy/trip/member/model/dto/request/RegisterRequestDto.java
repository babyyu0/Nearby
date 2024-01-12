package com.ssafy.trip.member.model.dto.request;


import com.ssafy.trip.global.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String memberId,
        @NotBlank(message = "비밀번호가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.PASSWORD, message = "비밀번호가 올바르지 않습니다.")
        String password,
        @NotBlank(message = "회원명이 존재하지 않습니다.")
        @Size(min = 2, max = 20, message = "회원명은 최소 2자, 최대 20자여야 합니다.")
        String name,

        @PositiveOrZero(message = "행정구역 코드는 0 이상이어야 합니다.")
        int sidoCode,
        @PositiveOrZero(message = "하위 행정구역 코드는 0 이상이어야 합니다.")
        int gugunCode
) {
}
