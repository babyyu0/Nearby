package com.ssafy.trip.member.model.dto.command;

import com.ssafy.trip.member.model.dto.request.LogoutRequestDto;
import com.ssafy.trip.global.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;

public record LogoutCommandDto(
        @NotBlank(message = "클라이언트 정보가 존재하지 않습니다.")
        String accessToken,
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id
) {
        @Builder(access = AccessLevel.PRIVATE)
        public LogoutCommandDto(String accessToken, String id) {
                this.accessToken = accessToken;
                this.id = id;
        }

        public static LogoutCommandDto from (LogoutRequestDto logoutRequestDto) {
                return LogoutCommandDto.builder()
                        .accessToken(logoutRequestDto.accessToken())
                        .id(logoutRequestDto.id())
                        .build();
        }
}
