package com.ssafy.trip.model.repository;

import com.ssafy.trip.model.data.GugunPk;
import com.ssafy.trip.model.entity.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, GugunPk> {
}
