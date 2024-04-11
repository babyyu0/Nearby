package com.ssafy.trip.attraction.model.repository;

import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.attraction.model.entity.Attraction;
import com.ssafy.trip.attraction.model.entity.ContentType;
import com.ssafy.trip.attraction.model.repository.custom.AttractionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Integer>, AttractionCustomRepository {
    List<Attraction> findByGugunAndContentType(Gugun gugun, ContentType contentType);
}
