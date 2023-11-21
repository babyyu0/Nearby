package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.model.entity.Member;
import com.ssafy.trip.util.data.RegexPattern;
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
        byte[] profile
){
    @Builder(access = AccessLevel.PRIVATE)
    public LoginResponseDto(String id, String name, byte[] profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
    }

    public static LoginResponseDto from(Member member, byte[] profile) {
        return LoginResponseDto.builder()
                .id(member.getMemberId())
                .name(member.getName())
                .profile(profile)
                .build();
    }
}
