package com.ssafy.trip.member.model.repository;

import com.ssafy.trip.member.model.entity.MemberToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTokenRepository extends CrudRepository<MemberToken, String> {

}
