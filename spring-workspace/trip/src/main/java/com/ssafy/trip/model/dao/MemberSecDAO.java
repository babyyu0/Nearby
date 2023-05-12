package com.ssafy.trip.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.vo.MemberSecVO;
import com.ssafy.trip.model.vo.MemberVO;

@Mapper
@Repository
public interface MemberSecDAO {

	MemberSecVO login(MemberVO member) throws DataAccessException;

	void registSec(MemberSecVO memberSec) throws DataAccessException;
	
}
