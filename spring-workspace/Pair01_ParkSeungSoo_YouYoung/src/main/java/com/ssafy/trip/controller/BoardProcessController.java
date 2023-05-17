package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.service.BoardService;
import com.ssafy.trip.model.vo.BoardVO;

@RestController
@RequestMapping("/board")
@CrossOrigin
public class BoardProcessController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("list")
	public List<BoardVO> getList(String type) {
		System.out.println(type);
		List<BoardVO> trips = boardService.selectAll(type);
		
		return trips;
	}
	
}
