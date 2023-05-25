package com.ssafy.trip.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public synchronized ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody MemberVO member) throws MyException {
       String name = memberService.login(member);
       HttpSession session = request.getSession();
       session.setAttribute("id", member.getId());
       //세션에 유저정보를 넣어놓음
       //TODO 이후에 세션 탙취당했을경우 or 환경이 바뀔경우 재 로그인 받아야함.
       session.setAttribute("userCheck",(request.getHeader("user-agent")));
       
       
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
	public ResponseEntity<?> regist(@RequestBody MemberVO member) {
		try {
			memberService.regist(member);
			return new ResponseEntity<>("ok", HttpStatus.OK);
		} catch(MyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("get-logged-member")
	public ResponseEntity<?> getLoggedMember(HttpServletRequest request) throws MyException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			MemberVO member = memberService.selectOne(new MemberVO((String) session.getAttribute("id"), null, null, null, 0, 0));
			member.setSidoVO(tripService.getOneSido(member.getSidoCode()));
			member.setGugunVO(tripService.getOneGugunBySidoCode(member.getGugunCode(), member.getSidoCode()));
			
			return new ResponseEntity<>(member, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping("upload-profile")
	public ResponseEntity<?> uploadProfile(HttpServletRequest request, @RequestBody MultipartFile profileImg) {

		HttpSession session = request.getSession(false);
		
		if(session != null) {
			try {
				String contentType = profileImg.getContentType();
				String originalFileExtension;
				if(contentType == null || contentType.trim().equals("")) {
					throw new MyException("파일 업로드 실패");
		        }
		        else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
		            if(contentType.contains("image/jpeg"))
		                originalFileExtension = ".jpg";
		            else if(contentType.contains("image/png"))
		                originalFileExtension = ".png";
		            else  // 다른 확장자일 경우 처리 x
		            	throw new MyException("파일 업로드 실패");
		        }
				// 파일 이름
				String new_file_name = System.nanoTime() + originalFileExtension;
				File profileFile = new File("D:/SSAFY/01_workspace/04_PJT/spring-workspace/Pair01_ParkSeungSoo_YouYoung/src/main/resources/static/img/userProfile" + File.separator + new_file_name);
				profileImg.transferTo(profileFile);
	
				profileFile.setExecutable(false);
				
				memberService.setProfileImg((String) session.getAttribute("id"), profileFile);
				
				return new ResponseEntity<>("http://localhost:9999/static/img/userProfile/" + profileFile.getName()	, HttpStatus.OK);
			} catch (IllegalStateException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (IOException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch(MyException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		
	}

}
