package com.ssafy.trip.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@CrossOrigin
public class BoardProcessController {
/*
	@Autowired
	BoardService boardService;

	@PostMapping("list")
	public List<BoardVO> getList(@RequestBody HashMap<String, Object> map) {
		String type = (String) map.get("type");
		
		double latitude = (double) map.getOrDefault("latitude", 0.0);
		double longitude = (double) map.getOrDefault("longitude", 0.0);
		
		List<BoardVO> boards = boardService.selectAll(type, latitude, longitude);
		if(map.containsKey("limit") && (int) map.get("limit") < boards.size()) return boards.subList(0, (int) map.get("limit")); 
			
		return boards;
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
	public ResponseEntity<String> updateBoard(HttpServletRequest request, @RequestBody BoardVO boardVO) {
		HttpSession session = request.getSession(false);
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
			
			BoardVO board = boardService.selectOne(boardVO);
			System.out.println(board.getWriterId() + " : " + session.getAttribute("id"));
			if(!board.getWriterId().equals(session.getAttribute("id"))) isAccept = false;

			if (isAccept) {
				boardService.update(boardVO);
				return new ResponseEntity<>("ok", HttpStatus.OK);
			} else {
				System.out.println("비정상적인 요청");
			}
		}
		return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
	}

	@PostMapping("write")
	public ResponseEntity<String> writeBoard(HttpServletRequest request, @RequestBody BoardVO boardVO) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			if(session.getAttribute("userCheck").equals(request.getHeader("user-agent"))) {
				boardService.write(boardVO);
				return new ResponseEntity<>("ok", HttpStatus.OK);
			} else {
				System.out.println("비정상적인 요청");
			}
			
		}
		return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
	}

	@PostMapping("delete")
	public ResponseEntity<String> deleteBoard(HttpServletRequest request, @RequestBody BoardVO boardVO) {
		HttpSession session = request.getSession(false);
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
			
			BoardVO board = boardService.selectOne(boardVO);
			if(!board.getWriterId().equals(session.getAttribute("id"))) isAccept = false;

			if (isAccept) {
				boardService.delete(boardVO);
				return new ResponseEntity<>("ok", HttpStatus.OK);
			} else {
				System.out.println("비정상적인 요청");
			}
		}
		return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
	}
	
	public void fileUpload(String filename) {
	     File file = new File(filename);
	     file.setExecutable(false);
	}


 */
}
