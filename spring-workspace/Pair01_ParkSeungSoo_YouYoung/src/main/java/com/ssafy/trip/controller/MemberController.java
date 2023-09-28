package com.ssafy.trip.controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.ssafy.trip.dto.command.ValidIdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.model.service.MemberService;
import com.ssafy.trip.model.service.TripService;
import com.ssafy.trip.model.vo.MemberVO;
import com.ssafy.trip.util.exception.MyException;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

	private final MemberService memberService;
	private final TripService tripService;

	@Autowired
	public MemberController(MemberService memberService, TripService tripService) {
		this.memberService = memberService;
		this.tripService = tripService;
	}

	@GetMapping("exist/{memberId}")
	public ResponseEntity<?> isValidId(@PathVariable("memberId") String memberId) {
		try {
			return ResponseEntity.ok(memberService.isValidId(new ValidIdCommand().toValidCommand(memberId)));
		} catch(MyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody MemberVO member) {
		try {
			return ResponseEntity.ok(memberService.register(member));
		} catch(MyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("login")
	public synchronized ResponseEntity<?> login(@RequestBody MemberVO member) throws MyException {
		return ResponseEntity.ok(memberService.login(member));
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
					throw new RuntimeException("파일 업로드 실패");
		        }
		        else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
		            if(contentType.contains("image/jpeg"))
		                originalFileExtension = ".jpg";
		            else if(contentType.contains("image/png"))
		                originalFileExtension = ".png";
		            else  // 다른 확장자일 경우 처리 x
		            	throw new RuntimeException("파일 업로드 실패");
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
			} catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		
	}

}
