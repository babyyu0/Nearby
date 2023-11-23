package com.ssafy.trip.area.model.repository;

import com.ssafy.trip.area.model.entity.primary.GugunPk;
import com.ssafy.trip.area.model.entity.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, GugunPk> {
}
