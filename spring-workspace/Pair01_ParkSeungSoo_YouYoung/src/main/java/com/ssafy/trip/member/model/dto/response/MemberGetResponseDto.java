package com.ssafy.trip.member.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trip.area.model.dto.response.GugunGetResponseDto;
import com.ssafy.trip.area.model.dto.response.SidoGetResponseDto;
import com.ssafy.trip.global.util.data.RegexPattern;
import com.ssafy.trip.member.model.entity.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record MemberGetResponseDto(
        @NotBlank(message = "아이디가 존재하지 않습니다.")
        @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
        String id,
        @NotBlank(message = "회원명이 존재하지 않습니다.")
        @Size(min = 2, max = 20, message = "회원명은 최소 2자, 최대 20자여야 합니다.")
        String name,
        @JsonProperty("sido")
        @Valid
        SidoGetResponseDto sidoGetResponseDto,
        @JsonProperty("gugun")
        @Valid
        GugunGetResponseDto gugunGetResponseDto,
        byte[] profile
) {

    @Builder
    public MemberGetResponseDto(String id, String name, SidoGetResponseDto sidoGetResponseDto,
            GugunGetResponseDto gugunGetResponseDto, byte[] profile) {
        this.id = id;
        this.name = name;
        this.sidoGetResponseDto = sidoGetResponseDto;
        this.gugunGetResponseDto = gugunGetResponseDto;
        this.profile = profile;
    }
}
