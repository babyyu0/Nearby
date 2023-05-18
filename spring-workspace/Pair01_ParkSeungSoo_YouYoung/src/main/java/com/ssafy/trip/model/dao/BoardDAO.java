package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.vo.BoardVO;

@Mapper
@Repository
public interface BoardDAO {

	public List<BoardVO> selectAll(String type) throws DataAccessException;

	public BoardVO selectOne(BoardVO boardVO) throws DataAccessException;

	public int update(BoardVO boardVO) throws DataAccessException;

}
