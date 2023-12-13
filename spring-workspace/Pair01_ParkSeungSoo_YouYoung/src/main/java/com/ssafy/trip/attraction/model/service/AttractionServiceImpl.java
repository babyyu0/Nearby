package com.ssafy.trip.attraction.model.service;

import com.querydsl.core.Tuple;
import com.ssafy.trip.attraction.model.dto.response.AttractionGetResponseDto;
import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.entity.ContentType;
import com.ssafy.trip.attraction.model.entity.ContentTypeEnum;
import com.ssafy.trip.attraction.model.repository.AttractionHeartRepository;
import com.ssafy.trip.attraction.model.repository.ContentTypeRepository;
import com.ssafy.trip.global.util.ImageUtil;
import com.ssafy.trip.global.util.TripUtil;
import com.ssafy.trip.global.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService  {

    private final TripUtil tripUtil;
    @Value("${url.attraction.img}")
    private static String ATTRACTION_IMG_URI;
    private final ContentTypeRepository contentTypeRepository;
    private final AttractionHeartRepository attractionHeartRepository;

    @Override
    public boolean refreshAttraction() {
        tripUtil.setAttraction();
        return true;
    }

    @Override
    public boolean refreshContentType() {
        for (ContentTypeEnum contentTypeEnum : ContentTypeEnum.values()) {  // Enum에 저장된 ContentType
            ContentType contentType = ContentType.builder()
                    .code(contentTypeEnum.getCode())
                    .name(contentTypeEnum.getName())
                    .build();
            ValidateUtil.serverValidate(contentType);

            contentTypeRepository.save(contentType);
        }
        return true;
    }

    @Override
    public List<AttractionGetResponseDto> getPopularAttraction() {
        List<Tuple> attractionHeartList = attractionHeartRepository.findAllByOrderByCountDesc();

        List<AttractionGetResponseDto> attractionGetResponseDtoList = new ArrayList<>();

        AttractionGetResponseDto attractionGetResponseDto;
        Attraction attraction;
        double km; String heart;
        for(Tuple attractionTuple : attractionHeartList) {
            attraction = attractionTuple.get(0, Attraction.class);
            heart = attractionTuple.get(1, String.class);
            km = 1;  // TO DO :: KM 계산하기

            attractionGetResponseDto = AttractionGetResponseDto.builder()
                    .code(attraction.getCode())
                    .title(attraction.getTitle())
                    .km(km)
                    .heart(heart == null ? 0 : Integer.parseInt(heart))
                    .img(ImageUtil.toByteArray(ATTRACTION_IMG_URI, attraction.getImg()))
                    .build();
            ValidateUtil.serverValidate(attractionGetResponseDto);

            attractionGetResponseDtoList.add(attractionGetResponseDto);
        }

        return attractionGetResponseDtoList;
    }
}
