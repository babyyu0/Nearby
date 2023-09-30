package com.ssafy.trip.model.dto.request;


import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@ToString
public class MemberCreateRequest implements RegexData {
    public MemberCreateRequest(String memberId, String password, String name, int sidoCode, int gugunCode) throws MemberInvalidException {
        setMemberId(memberId);
        setPassword(password);
        setName(name);
        setSidoCode(sidoCode);
        setGugunCode(gugunCode);
    }
    private String memberId;
    private String password;
    private String name;

    private int sidoCode;
    private int gugunCode;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) throws MemberInvalidException {
        if(memberId == null || memberId.trim().equals("") || !memberId.trim().matches(RegexData.regex.get("email"))) {
            log.error("MemberCreateRequest: 아이디 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws MemberInvalidException {
        if(password == null || password.trim().equals("") || !password.trim().matches(RegexData.regex.get("password"))) {
            log.error("MemberCreateRequest: 비밀번호 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }

        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MemberInvalidException {
        if(name == null || name.trim().equals("") || 20 < name.length() ) {
            log.error("MemberCreateRequest: 닉네임 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }

        this.name = name;
    }

    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) throws MemberInvalidException {
        if(sidoCode <= 0) {
            System.out.println(sidoCode);
            log.error("MemberCreateRequest: 지역 (시, 도) 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }
        this.sidoCode = sidoCode;
    }

    public int getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(int gugunCode) throws MemberInvalidException {
        if(gugunCode <= 0) {
            log.error("MemberCreateRequest: 지역 (구, 군) 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }
        this.gugunCode = gugunCode;
    }
}
