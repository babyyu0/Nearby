package com.ssafy.trip.member.model.dto.command;

import com.ssafy.trip.member.model.dto.request.RegisterRequestDto;
import com.ssafy.trip.global.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record RegisterCommandDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String memberId,
        @NotBlank(message = "비밀번호가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.PASSWORD, message = "비밀번호가 올바르지 않습니다.")
        String password,
        @NotBlank(message = "회원명이 존재하지 않습니다.")
        @Size(min = 2, max = 20, message = "회원명은 최소 2자, 최대 20자여야 합니다.")
        String name,

        @Positive(message = "행정구역 코드는 자연수여야 합니다.")
        int sidoCode,
        @Positive(message = "하위 행정구역 코드는 자연수여야 합니다.")
        int gugunCode,
        MultipartFile profile
) {
        @Builder
        public RegisterCommandDto(String memberId, String password, String name, int sidoCode, int gugunCode, MultipartFile profile) {
                this.memberId = memberId;
                this.password = password;
                this.name = name;
                this.sidoCode = sidoCode;
                this.gugunCode = gugunCode;
                this.profile = profile;
        }

        public static RegisterCommandDto from (RegisterRequestDto registerRequestDto, MultipartFile profile) {
                return RegisterCommandDto.builder()
                        .memberId(registerRequestDto.memberId())
                        .password(registerRequestDto.password())
                        .name(registerRequestDto.name())
                        .sidoCode(registerRequestDto.sidoCode())
                        .gugunCode(registerRequestDto.gugunCode())
                        .profile(profile)
                        .build();
        }
}
