package com.ssafy.trip.attraction.model.service;

import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;

import java.util.List;

public interface AttractionService {
    boolean refreshAttraction();
    boolean refreshContentType();
    List<AttractionGetResponseDto> getPopularAttraction();
}
