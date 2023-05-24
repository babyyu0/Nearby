package com.ssafy.trip.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.service.MemberService;
import com.ssafy.trip.model.service.TripService;
import com.ssafy.trip.model.vo.MemberVO;
import com.ssafy.trip.util.MyException;
import com.ssafy.trip.util.SessionListener;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberProcessController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private TripService tripService;

	@PostMapping("login")
	public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody MemberVO member) throws MyException {
		String name = memberService.login(member);
		HttpSession session = request.getSession();

		// 세션에 유저정보를 넣어놓음
		// TODO 이후에 세션 탙취당했을경우 or 환경이 바뀔경우 재 로그인 받아야함.
		session.setAttribute("userCheck", (request.getHeader("user-agent")));

		// 세션 중복 처리
		if (name != null) {
			SessionListener.getSessionidCheck("id", member.getId());
			session.setAttribute("id", member.getId());
		}

		return new ResponseEntity<>(name, HttpStatus.OK);
	}

	@PostMapping("logout")
	public String logout(HttpServletRequest request) throws MyException {
		HttpSession session = request.getSession(false);
		session.invalidate();

		return null;
	}

	@PostMapping("duplicate-check")
	public String duplicateCheck(@RequestBody MemberVO member) throws MyException {
		MemberVO selMember = memberService.selectOne(member);

		if (selMember == null)
			return "ok";
		return null;
	}

	@PostMapping("regist")
	public String regist(@RequestBody MemberVO member) throws MyException {
		MemberVO selMember = memberService.selectOne(member);
		if (selMember != null)
			return null;

		memberService.regist(member);
		return "ok";
	}

	@PostMapping("get-logged-member")
	public ResponseEntity<?> getLoggedMember(HttpServletRequest request) throws MyException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			MemberVO member = memberService.selectOne(new MemberVO((String) session.getAttribute("id"), null, null, null, 0, 0));
			member.setSidoVO(tripService.getOneSido(member.getSidoCode()));
			member.setGugunVO(tripService.getOneGugunBySidoCode(member.getGugunCode(), member.getSidoCode()));
			
			System.out.println(member);
			return new ResponseEntity<>(member, HttpStatus.OK);
		}

		return null;
	}

	public void csrfIn(HttpServletResponse response, HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		response.addCookie(new Cookie("CSRF", uuid));
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.setAttribute("CSRF", uuid);
		}

	}

	public void csrfOut(HttpServletRequest request) {
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

			if (isAccept) {
				System.out.println("작업 고고");
			} else {
				System.out.println("비정상적인 요청");
			}
		}
	}
}
