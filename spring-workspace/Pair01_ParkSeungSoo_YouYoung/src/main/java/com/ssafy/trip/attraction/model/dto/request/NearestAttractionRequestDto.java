package com.ssafy.trip.attraction.model.dto.request;

import jakarta.validation.constraints.Positive;

public record NearestAttractionRequestDto(
        @Positive(message = "위도는 양수여야 합니다.")
        double latitude,
        @Positive(message = "경도는 양수여야 합니다.")
        double longitude
) {
}
