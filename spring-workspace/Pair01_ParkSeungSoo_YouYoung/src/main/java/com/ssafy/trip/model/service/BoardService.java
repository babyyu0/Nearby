package com.ssafy.trip.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.BoardDAO;
import com.ssafy.trip.model.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	BoardDAO boardDAO;
	
	public List<BoardVO> selectAll(String type) {
		return boardDAO.selectAll(type);
	}

	public BoardVO selectOne(BoardVO boardVO) {
		return boardDAO.selectOne(boardVO);
	}

	public int update(BoardVO boardVO) {
		return boardDAO.update(boardVO);
	}

	public int write(BoardVO boardVO) {
		return boardDAO.insert(boardVO);
	}

	public int delete(BoardVO boardVO) {
		return boardDAO.delete(boardVO);
	}
	
}
