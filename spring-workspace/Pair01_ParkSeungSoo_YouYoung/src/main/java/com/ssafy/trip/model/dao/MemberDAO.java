package com.ssafy.trip.model.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.vo.Member;

@Mapper
@Repository
public interface MemberDao {
	Member findByMemberId(String memberId) throws DataAccessException;
	boolean save(Member member)throws DataAccessException;
	public Member findByIdAndPassword(Member member) throws DataAccessException;
	
	public String login(Member member) throws DataAccessException;
	
	public Member selectOne(Member member) throws DataAccessException;

	public void regist(Member member) throws DataAccessException;

	public void insertProfileImg(String id, String profileImgPath) throws DataAccessException;
}
