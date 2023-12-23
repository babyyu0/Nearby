package com.ssafy.trip.attraction.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PopularAttractionRequestDto(
        @NotNull(message = "위도가 존재하지 않습니다.")
        @Positive(message = "위도는 양수여야 합니다.")
        double latitude,
        @NotNull(message = "경도가 존재하지 않습니다.")
        @Positive(message = "경도는 양수여야 합니다.")
        double longitude
) {
}
