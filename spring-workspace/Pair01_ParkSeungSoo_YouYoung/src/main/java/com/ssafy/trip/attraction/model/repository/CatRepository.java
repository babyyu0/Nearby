package com.ssafy.trip.attraction.model.repository;

import com.ssafy.trip.attraction.model.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, String> {
}
