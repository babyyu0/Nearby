package com.ssafy.trip.model.repository;

import com.ssafy.trip.model.entity.Sido;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Integer> {
}
