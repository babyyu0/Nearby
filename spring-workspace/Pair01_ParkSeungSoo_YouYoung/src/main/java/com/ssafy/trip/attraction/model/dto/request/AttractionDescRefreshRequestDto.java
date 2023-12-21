package com.ssafy.trip.attraction.model.dto.request;

import jakarta.validation.constraints.Positive;

public record AttractionDescRefreshRequestDto(
        @Positive(message = "관광지 코드는 양수여야 합니다.")
        int attractionCode
) {
}
