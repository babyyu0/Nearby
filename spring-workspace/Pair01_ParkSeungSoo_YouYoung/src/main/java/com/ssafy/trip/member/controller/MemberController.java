package com.ssafy.trip.member.controller;

import com.ssafy.trip.global.util.exception.MyException;
import com.ssafy.trip.member.model.dto.command.LoginCommandDto;
import com.ssafy.trip.member.model.dto.command.LogoutCommandDto;
import com.ssafy.trip.member.model.dto.command.MemberGetCommandDto;
import com.ssafy.trip.member.model.dto.command.RegisterCommandDto;
import com.ssafy.trip.member.model.dto.command.ValidIdCommandDto;
import com.ssafy.trip.member.model.dto.request.LoginRequestDto;
import com.ssafy.trip.member.model.dto.request.LogoutRequestDto;
import com.ssafy.trip.member.model.dto.request.MemberGetRequestDto;
import com.ssafy.trip.member.model.dto.request.RegisterRequestDto;
import com.ssafy.trip.member.model.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
        return ResponseEntity.ok(
                memberService.isValidId(ValidIdCommandDto.builder().memberId(memberId).build())
        );
    }

    @PostMapping(value = "register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> register(@RequestPart(value = "member") @Valid RegisterRequestDto registerRequestDto,
            @RequestPart(value = "profile", required = false) MultipartFile profile) throws MyException {
        return ResponseEntity.ok(
                memberService.register(RegisterCommandDto.from(registerRequestDto, profile))
        );
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws MyException {
        return ResponseEntity.ok(memberService.login(LoginCommandDto.from(loginRequestDto)));
    }

    @PostMapping
    public ResponseEntity<?> getMember(@RequestHeader("Authorization") String accessToken,
            @RequestBody @Valid MemberGetRequestDto memberGetRequestDto) throws MyException {
        return ResponseEntity.ok(memberService.getMember(MemberGetCommandDto.from(memberGetRequestDto, accessToken)));
    }

    @PostMapping(value = "logout")
    public ResponseEntity<?> logout(@RequestBody @Valid LogoutRequestDto loginRequestDto) throws MyException {
        return ResponseEntity.ok(memberService.logout(LogoutCommandDto.from(loginRequestDto)));
    }
}
