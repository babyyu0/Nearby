package com.ssafy.trip.attraction.model.dto.command;

import com.ssafy.trip.attraction.model.dto.request.NearestAttractionRequestDto;
import com.ssafy.trip.attraction.model.dto.request.PopularAttractionRequestDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;

public record MemberDistCommandDto(
        @NotNull(message = "위도가 존재하지 않습니다.")
        @Positive(message = "위도는 양수여야 합니다.")
        double latitude,
        @NotNull(message = "경도가 존재하지 않습니다.")
        @Positive(message = "경도는 양수여야 합니다.")
        double longitude
) {
    public static MemberDistCommandDto from(NearestAttractionRequestDto nearestAttractionRequestDto) {
        return MemberDistCommandDto.builder()
                .latitude(nearestAttractionRequestDto.latitude())
                .longitude(nearestAttractionRequestDto.longitude())
                .build();
    }
    public static MemberDistCommandDto from(PopularAttractionRequestDto popularAttractionRequestDto) {
        return MemberDistCommandDto.builder()
                .latitude(popularAttractionRequestDto.latitude())
                .longitude(popularAttractionRequestDto.longitude())
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public MemberDistCommandDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
