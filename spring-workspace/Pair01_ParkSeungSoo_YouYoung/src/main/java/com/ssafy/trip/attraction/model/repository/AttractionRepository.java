package com.ssafy.trip.attraction.model.repository;

import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.repository.custom.AttractionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Integer>, AttractionCustomRepository {
}
