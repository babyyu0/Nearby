package com.ssafy.trip.attraction.model.dto.response;

public record AttractionGetResponseDto(
        String code,
        String name,
        double km,
        byte[] img
) {
}
