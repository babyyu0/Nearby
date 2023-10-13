package com.ssafy.trip.model.repository;

import com.ssafy.trip.model.entity.MemberSec;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberSecRepository extends JpaRepository<MemberSec, Long> {
    Optional<MemberSec> findByMemberId(String memberId) throws DataAccessException;
}
