package com.ssafy.trip.attraction.model.dto.command;

import com.ssafy.trip.attraction.model.dto.request.AttractionDescRefreshRequestDto;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

public record AttractionDescRefreshCommandDto(
        @Positive(message = "관광지 코드는 양수여야 합니다.")
        int attractionCode
) {
    public static AttractionDescRefreshCommandDto of(int attractionCode) {
        return AttractionDescRefreshCommandDto.builder()
                .attractionCode(attractionCode)
                .build();
    }
    @Builder
    public AttractionDescRefreshCommandDto(int attractionCode) {
        this.attractionCode = attractionCode;
    }
}
