package com.ssafy.trip.model.repository;

import com.ssafy.trip.model.entity.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, Integer> {
}
