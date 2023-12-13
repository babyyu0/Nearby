package com.ssafy.trip.attraction.model.repository;

import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.entity.AttractionDesc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionDescRepository extends JpaRepository<AttractionDesc, Integer> {
}
