package com.ssafy.trip.model.dao;

import com.ssafy.trip.model.vo.MemberSec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.vo.MemberSecVO;
import com.ssafy.trip.model.vo.Member;

@Mapper
@Repository
public interface MemberSecDao {
	MemberSec findByMemberId(String memberId) throws DataAccessException;
	boolean save(MemberSec memberSec) throws DataAccessException;

	// MemberSecVO login(Member member) throws DataAccessException;

	// void registSec(MemberSecVO memberSec) throws DataAccessException;
	
}
