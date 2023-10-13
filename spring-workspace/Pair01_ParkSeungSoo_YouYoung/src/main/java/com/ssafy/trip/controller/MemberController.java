package com.ssafy.trip.controller;

import com.ssafy.trip.model.dto.command.MemberCreateCommand;
import com.ssafy.trip.model.dto.command.MemberLoginCommand;
import com.ssafy.trip.model.dto.command.ValidIdCommand;
import com.ssafy.trip.model.dto.request.MemberCreateRequest;
import com.ssafy.trip.model.dto.request.MemberLoginRequest;
import com.ssafy.trip.model.service.MemberService;
import com.ssafy.trip.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("exist/{memberId}")
    public ResponseEntity<?> isValidId(@PathVariable("memberId") String memberId) throws MyException {
        return ResponseEntity.ok(memberService.isValidId(new ValidIdCommand(memberId)));
    }

    @PostMapping(value = "register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> register(@RequestPart(value = "member") MemberCreateRequest memberCreateRequest, @RequestPart(value = "profile") MultipartFile profile) throws MyException {
        return ResponseEntity.ok(memberService.register(MemberCreateCommand.builder()
                .memberId(memberCreateRequest.getMemberId())
                .password(memberCreateRequest.getPassword())
                .name(memberCreateRequest.getName())
                .sidoCode(memberCreateRequest.getSidoCode())
                .gugunCode(memberCreateRequest.getGugunCode())
                .profile(profile)
                .build()));
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequest memberLoginRequest) throws MyException {
        return ResponseEntity.ok(memberService.login(MemberLoginCommand.builder().memberId(memberLoginRequest.getMemberId()).password(memberLoginRequest.getPassword()).build()));
    }

}
/*
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

	@PostMapping("get-logged-member")
	public ResponseEntity<?> getLoggedMemberVO(HttpServletRequest request) throws MyException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			MemberVO member = memberService.selectOne(new MemberVO((String) session.getAttribute("id"), null, null, null, 0, 0));
			member.setSido(tripService.getOneSido(member.getSidoCode()));
			member.setGugun(tripService.getOneGugunBySidoCode(member.getGugunCode(), member.getSidoCode()));
			
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
*/
