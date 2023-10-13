package com.ssafy.trip.model.dto.command;


import com.ssafy.trip.model.dto.request.MemberCreateRequest;
import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class MemberCreateCommand implements RegexData {

    private String memberId;
    private String password;
    private String name;

    private int sidoCode;
    private int gugunCode;
    private MultipartFile profile;

    public static MemberCreateCommandBuilder builder() {
        return MemberCreateCommand.innerBuilder();
    }

    public MemberCreateCommandBuilder memberId(String memberId) throws MemberInvalidException {
        if(memberId == null || memberId.trim().equals("") || !memberId.trim().matches(RegexData.regex.get("email"))) {
            log.error("MemberCreateCommand: 아이디 입력 실패 " + memberId);
            throw new MemberInvalidException();
        }

        return innerBuilder().memberId(memberId);
    }

    public MemberCreateCommandBuilder password(String password) throws MemberInvalidException {
        if(password == null || password.trim().equals("") || !password.trim().matches(RegexData.regex.get("password"))) {
            log.error("MemberCreateCommand: 비밀번호 입력 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().password(password);
    }

    public MemberCreateCommandBuilder name(String name) throws MemberInvalidException {
        if(name == null || name.trim().equals("") || 20 < name.length() ) {
            log.error("MemberCreateCommand: 이름 입력 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().name(name);
    }

    public MemberCreateCommandBuilder sidoCode(int sidoCode) throws MemberInvalidException {
        if(sidoCode <= 0) {
            log.error("MemberCreateCommand: 지역 (시, 도) 입력 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().sidoCode(sidoCode);
    }

    public MemberCreateCommandBuilder gugunCode(int gugunCode) throws MemberInvalidException {
        if(gugunCode <= 0) {
            log.error("MemberCreateCommand: 지역 (구, 군) 입력 실패");
            throw new MemberInvalidException();
        }
        return innerBuilder().gugunCode(gugunCode);
    }

    public MemberCreateCommandBuilder profile(MultipartFile profile) throws MemberInvalidException {
        if(profile.isEmpty()) {
            log.error("MemberCreateCommand: 프로필 입력 실패");
            throw new MemberInvalidException();
        }

        String contentType = profile.getContentType();
        if(!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            log.error("MemberCreateCommand: 프로필 확장명 변환 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().profile(profile);
    }
}
