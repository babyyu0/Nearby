package com.ssafy.trip.attraction.model.service;

import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService  {
    @Override
    public List<AttractionGetResponseDto> getPopularAttraction() {

        return null;
    }
}
