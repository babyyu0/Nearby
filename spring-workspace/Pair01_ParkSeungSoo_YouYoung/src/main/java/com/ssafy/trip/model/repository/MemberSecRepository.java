package com.ssafy.trip.model.repository;

import com.ssafy.trip.model.entity.MemberSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSecRepository extends JpaRepository<MemberSec, String> {
}
