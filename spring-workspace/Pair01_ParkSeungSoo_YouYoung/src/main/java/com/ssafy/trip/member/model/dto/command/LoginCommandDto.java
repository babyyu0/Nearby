package com.ssafy.trip.member.model.dto.command;

import com.ssafy.trip.member.model.dto.request.LoginRequestDto;
import com.ssafy.trip.global.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;

public record LoginCommandDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id,
        @NotBlank(message = "비밀번호가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.PASSWORD, message = "비밀번호가 올바르지 않습니다.")
        String password
) {
    @Builder(access = AccessLevel.PRIVATE)
    public LoginCommandDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static LoginCommandDto from(LoginRequestDto loginRequestDto) {
        return LoginCommandDto.builder()
                .id(loginRequestDto.id())
                .password(loginRequestDto.password())
                .build();
    }
}
