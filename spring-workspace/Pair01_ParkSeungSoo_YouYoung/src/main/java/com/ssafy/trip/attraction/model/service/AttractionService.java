package com.ssafy.trip.attraction.model.service;

import com.ssafy.trip.attraction.model.dto.command.AttractionDescRefreshCommandDto;
import com.ssafy.trip.attraction.model.dto.command.NearestAttractionCommandDto;
import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;

import java.util.List;

public interface AttractionService {
    boolean refreshContentType();
    boolean refreshCat(int depth, String cat1, String cat2);
    boolean refreshAttraction();
    boolean refreshAttractionDesc(AttractionDescRefreshCommandDto attractionDescRefreshCommandDto);
    List<AttractionGetResponseDto> getPopularAttraction();
    List<AttractionGetResponseDto> getNearestAttraction(NearestAttractionCommandDto nearestAttractionCommandDto);
}
