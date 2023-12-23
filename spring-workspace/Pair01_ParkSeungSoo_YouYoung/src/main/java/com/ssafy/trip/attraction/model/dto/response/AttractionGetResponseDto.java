package com.ssafy.trip.attraction.model.dto.response;

import com.ssafy.trip.attraction.model.entity.Attraction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;

public record AttractionGetResponseDto(
        @Positive(message = "관광지 고유 번호는 자연수여야 합니다.")
        int code,
        @NotBlank(message = "관광지명이 존재하지 않습니다.")
        String title,
        @PositiveOrZero(message = "거리는 양수여야 합니다.")
        double dist,
        @PositiveOrZero(message = "하트 수는 양수여야 합니다.")
        int heart,
        String img
) {
        @Builder
        public AttractionGetResponseDto(int code, String title, double dist, int heart, String img) {
                this.code = code;
                this.title = title;
                this.dist = dist;
                this.heart = heart;
                this.img = img;
        }
}
