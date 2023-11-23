package com.ssafy.trip.member.model.repository;

import com.ssafy.trip.member.model.entity.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findById(String id) throws DataAccessException;
}
