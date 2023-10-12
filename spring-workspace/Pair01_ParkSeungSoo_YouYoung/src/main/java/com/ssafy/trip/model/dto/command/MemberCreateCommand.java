package com.ssafy.trip.model.dto.command;


import com.ssafy.trip.model.dto.request.MemberCreateRequest;
import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Slf4j
@ToString
public class MemberCreateCommand implements RegexData {
    public MemberCreateCommand(MemberCreateRequest memberCreateRequest, MultipartFile profile) throws MemberInvalidException {
        setMemberId(memberCreateRequest.getMemberId());
        setPassword(memberCreateRequest.getPassword());
        setName(memberCreateRequest.getName());
        setSidoCode(memberCreateRequest.getSidoCode());
        setGugunCode(memberCreateRequest.getGugunCode());
        setProfile(profile);
    }

    private String memberId;
    private String password;
    private String name;

    private int sidoCode;
    private int gugunCode;
    private MultipartFile profile;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) throws MemberInvalidException {
        if(memberId == null || memberId.trim().equals("") || !memberId.trim().matches(RegexData.regex.get("email"))) {
            log.error("MemberCreateCommand: 아이디 입력 실패 " + memberId);
            throw new MemberInvalidException();
        }

        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws MemberInvalidException {
        if(password == null || password.trim().equals("") || !password.trim().matches(RegexData.regex.get("password"))) {
            log.error("MemberCreateCommand: 비밀번호 입력 실패");
            throw new MemberInvalidException();
        }

        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MemberInvalidException {
        if(name == null || name.trim().equals("") || 20 < name.length() ) {
            log.error("MemberCreateCommand: 이름 입력 실패");
            throw new MemberInvalidException();
        }

        this.name = name;
    }

    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) throws MemberInvalidException {
        if(sidoCode <= 0) {
            log.error("MemberCreateCommand: 지역 (시, 도) 입력 실패");
            throw new MemberInvalidException();
        }
        this.sidoCode = sidoCode;
    }

    public int getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(int gugunCode) throws MemberInvalidException {
        if(gugunCode <= 0) {
            log.error("MemberCreateCommand: 지역 (구, 군) 입력 실패");
            throw new MemberInvalidException();
        }
        this.gugunCode = gugunCode;
    }

    public MultipartFile getProfile() {
        return profile;
    }

    public void setProfile(MultipartFile profile) throws MemberInvalidException {
        if(profile.isEmpty()) {
            log.error("MemberCreateCommand: 프로필 입력 실패");
            throw new MemberInvalidException();
        }

        String contentType = profile.getContentType();
        if(!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            log.error("MemberCreateCommand: 프로필 확장명 변환 실패");
            throw new MemberInvalidException();
        }

        this.profile = profile;
    }
}
