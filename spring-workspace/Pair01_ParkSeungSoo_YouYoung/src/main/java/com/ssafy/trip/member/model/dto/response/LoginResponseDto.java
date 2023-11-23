package com.ssafy.trip.member.model.dto.response;

import com.ssafy.trip.member.model.entity.Member;
import com.ssafy.trip.global.util.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;

public record LoginResponseDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id,
        @NotBlank(message = "회원명이 존재하지 않습니다.")
        @Size(min = 2, max = 20, message = "회원명은 최소 2자, 최대 20자여야 합니다.")
        String name,
        @NotBlank(message = "액세스 토큰이 존재하지 않습니다.")
        String accessToken,
        @NotBlank(message = "리프레시 토큰이 존재하지 않습니다.")
        String refreshToken,
        byte[] profile
){
    @Builder
    public LoginResponseDto(String id, String name, String accessToken, String refreshToken, byte[] profile) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.profile = profile;
    }
}
