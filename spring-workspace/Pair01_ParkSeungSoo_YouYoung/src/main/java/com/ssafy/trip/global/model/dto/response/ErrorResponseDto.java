package com.ssafy.trip.global.model.dto.response;

public record ErrorResponseDto(
        String code,
        String message
) { }
