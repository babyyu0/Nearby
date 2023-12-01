package com.ssafy.trip.attraction.model.service;

import com.querydsl.core.Tuple;
import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;
import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.repository.AttractionHeartRepository;
import com.ssafy.trip.global.util.ImageUtil;
import com.ssafy.trip.global.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService  {

    @Value("${url.attraction.img}")
    private static String ATTRACTION_IMG_URI;
    private final AttractionHeartRepository attractionHeartRepository;
    @Override
    public List<AttractionGetResponseDto> getPopularAttraction() {
        List<Tuple> attractionHeartList = attractionHeartRepository.findAllByOrderByCountDesc();

        List<AttractionGetResponseDto> attractionGetResponseDtoList = new ArrayList<>();

        AttractionGetResponseDto attractionGetResponseDto;
        Attraction attraction;
        double km = 1;
        for(Tuple attractionHeart : attractionHeartList) {
            attraction = attractionHeart.get(0, Attraction.class);
            km = 1;  // TO DO :: KM 계산하기

            attractionGetResponseDto = AttractionGetResponseDto.builder()
                    .code(attraction.getCode())
                    .title(attraction.getTitle())
                    .km(km)
                    .img(ImageUtil.toByteArray(ATTRACTION_IMG_URI, attraction.getImg()))
                    .build();
            ValidateUtil.serverValidate(attractionGetResponseDto);

            attractionGetResponseDtoList.add(attractionGetResponseDto);
        }

        return attractionGetResponseDtoList;
    }
}
