package com.ssafy.trip.attraction.model.repository.custom;

import com.querydsl.core.Tuple;
import com.ssafy.trip.attraction.model.entity.AttractionHeart;

import java.util.List;

public interface AttractionHeartCustomRepository {
    List<Tuple> findAllByOrderByCountDesc();
}
