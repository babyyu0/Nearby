package com.ssafy.trip.attraction.model.service;

import com.ssafy.trip.attraction.model.dto.command.AreaAttractionCommandDto;
import com.ssafy.trip.attraction.model.dto.command.AttractionDescRefreshCommandDto;
import com.ssafy.trip.attraction.model.dto.command.MemberDistCommandDto;
import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;

import java.util.List;

public interface AttractionService {
    boolean refreshContentType();
    boolean refreshCat(int depth, String cat1, String cat2);
    boolean refreshAttraction();
    boolean refreshAttractionDesc(AttractionDescRefreshCommandDto attractionDescRefreshCommandDto);
    List<AttractionGetResponseDto> getNearestAttraction(MemberDistCommandDto memberDistCommandDto);
    List<AttractionGetResponseDto> getPopularAttraction(MemberDistCommandDto memberDistCommandDto);
    List<AttractionGetResponseDto> getAttractionByArea(AreaAttractionCommandDto areaAttractionCommandDto);
}
