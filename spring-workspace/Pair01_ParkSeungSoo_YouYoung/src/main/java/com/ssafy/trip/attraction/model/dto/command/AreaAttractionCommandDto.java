package com.ssafy.trip.attraction.model.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record AreaAttractionCommandDto(
        @PositiveOrZero(message = "행정구역 코드는 0 이상이어야 합니다.")
        int sidoCode,
        @PositiveOrZero(message = "하위 행정구역 코드는 0 이상이어야 합니다.")
        int gugunCode,
        @Positive(message = "콘텐츠 분류 코드는 자연수여야 합니다.")
        int contentType,
        @NotNull(message = "위도가 존재하지 않습니다.")
        @Positive(message = "위도는 양수여야 합니다.")
        double latitude,
        @NotNull(message = "경도가 존재하지 않습니다.")
        @Positive(message = "경도는 양수여야 합니다.")
        double longitude
) {
}
