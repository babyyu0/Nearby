package com.ssafy.trip.model.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	MemberVO findById(String memberId) throws DataAccessException;
	public MemberVO findByIdAndPassword(MemberVO member) throws DataAccessException;
	
	public String login(MemberVO member) throws DataAccessException;
	
	public MemberVO selectOne(MemberVO member) throws DataAccessException;

	public void regist(MemberVO member) throws DataAccessException;

	public void insertProfileImg(String id, String profileImgPath) throws DataAccessException;
}
