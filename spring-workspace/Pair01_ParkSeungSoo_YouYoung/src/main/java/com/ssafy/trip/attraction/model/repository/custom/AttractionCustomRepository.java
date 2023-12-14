package com.ssafy.trip.attraction.model.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface AttractionCustomRepository {
    List<Tuple> findAllByOrderByMeterAsc(double latitude, double longitude);
}
