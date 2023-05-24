package com.ssafy.trip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.trip.model.service.BoardService;
import com.ssafy.trip.model.vo.BoardVO;

@RestController
@RequestMapping("/board")
@CrossOrigin
public class BoardProcessController {

	@Autowired
	BoardService boardService;

	@PostMapping("list")
	public ResponseEntity<PageInfo<BoardVO>> getList(@RequestBody HashMap<String, ?> map) {
		int pageNum = (int) map.get("pageNum");
		int pageSize = (int) map.get("pageSize");
		String type = (String) map.get("type");
		
		PageHelper.startPage(pageNum, pageSize);
		List<BoardVO> boards = boardService.selectAll(type);

		return new ResponseEntity<>(PageInfo.of(boards), HttpStatus.OK);
	}

	@PostMapping("view")
	public BoardVO getBoard(HttpServletRequest request, HttpServletResponse response, @RequestBody BoardVO boardVO) {
		String uuid = UUID.randomUUID().toString();
		response.addCookie(new Cookie("CSRF", uuid));
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.setAttribute("CSRF", uuid);
		}
		return boardService.selectOne(boardVO);
	}

	@PostMapping("update")
	public int updateBoard(@RequestBody BoardVO boardVO) {
		return boardService.update(boardVO);
	}

	@PostMapping("write")
	public int writeBoard(@RequestBody BoardVO boardVO) {
		return boardService.write(boardVO);
	}

	@PostMapping("delete")
	public ResponseEntity<String> deleteBoard(HttpServletRequest request, @RequestBody BoardVO boardVO) {
		// return boardService.delete(boardVO);
		HttpSession session = request.getSession(false);
		System.out.println();
		if (session != null) {
			session.getAttribute("CSRF").equals("");
			Cookie[] cookie = request.getCookies();

			boolean isAccept = false;

			for (int idx = 0; idx < cookie.length; idx++) {
				if (cookie[idx].getName().equals("CSRF")) {
					isAccept = true;
					break;
				}
			}

			if (isAccept) {
				//System.out.println("작업 고고");
				//boardService.delete(boardVO);
				return new ResponseEntity<>("", HttpStatus.OK);
			} else {
				System.out.println("비정상적인 요청");
			}
		}
		return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
	}

}
