package com.ssafy.trip.attraction.model.repository;

import com.ssafy.trip.attraction.model.entity.AttractionHeart;
import com.ssafy.trip.attraction.model.repository.custom.AttractionHeartCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionHeartRepository extends JpaRepository<AttractionHeart, Integer>, AttractionHeartCustomRepository {
}
