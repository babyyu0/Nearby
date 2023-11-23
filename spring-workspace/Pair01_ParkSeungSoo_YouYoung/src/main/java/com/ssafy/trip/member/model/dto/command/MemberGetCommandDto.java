package com.ssafy.trip.member.model.dto.command;

import com.ssafy.trip.global.util.data.RegexPattern;
import com.ssafy.trip.member.model.dto.request.MemberGetRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;

public record MemberGetCommandDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id,
        @NotBlank(message = "비밀번호가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.PASSWORD, message = "비밀번호가 올바르지 않습니다.")
        String password,
        @NotBlank(message = "액세스 토큰이 존재하지 않습니다.")
        String accessToken
) {
        @Builder(access = AccessLevel.PRIVATE)
        public MemberGetCommandDto(String id, String password, String accessToken) {
                this.id = id;
                this.password = password;
                this.accessToken = accessToken;
        }

        public static MemberGetCommandDto from (MemberGetRequestDto memberGetCommandDto, String accessToken) {
                return MemberGetCommandDto.builder()
                        .id(memberGetCommandDto.id())
                        .password(memberGetCommandDto.password())
                        .accessToken(accessToken)
                        .build();
        }
}
