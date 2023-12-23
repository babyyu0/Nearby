package com.ssafy.trip.attraction.model.dto.command;

import com.ssafy.trip.attraction.model.dto.request.NearestAttractionRequestDto;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;

public record MemberDistCommandDto(
        @Positive(message = "위도는 양수여야 합니다.")
        double latitude,
        @Positive(message = "경도는 양수여야 합니다.")
        double longitude
) {
    public static MemberDistCommandDto from(NearestAttractionRequestDto nearestAttractionRequestDto) {
        return MemberDistCommandDto.builder()
                .latitude(nearestAttractionRequestDto.latitude())
                .longitude(nearestAttractionRequestDto.longitude())
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public MemberDistCommandDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
