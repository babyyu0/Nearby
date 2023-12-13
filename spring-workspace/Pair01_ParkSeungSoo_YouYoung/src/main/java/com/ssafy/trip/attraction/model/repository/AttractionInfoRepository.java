package com.ssafy.trip.attraction.model.repository;

import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.entity.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {
}
