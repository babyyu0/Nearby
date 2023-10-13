package com.ssafy.trip.model.dto.command;


import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class MemberLoginCommand implements RegexData {

    private String memberId;
    private String password;

    public static MemberLoginCommand.MemberLoginCommandBuilder builder() {
        return MemberLoginCommand.innerBuilder();
    }

    public MemberLoginCommand.MemberLoginCommandBuilder memberId(String memberId) throws MemberInvalidException {
        if(memberId == null || memberId.trim().equals("") || !memberId.trim().matches(RegexData.regex.get("email"))) {
            log.error("MemberLoginCommand: 아이디 입력 실패 " + memberId);
            throw new MemberInvalidException();
        }

        return innerBuilder().memberId(memberId);
    }

    public MemberLoginCommand.MemberLoginCommandBuilder password(String password) throws MemberInvalidException {
        if(password == null || password.trim().equals("") || !password.trim().matches(RegexData.regex.get("password"))) {
            log.error("MemberLoginCommand: 비밀번호 입력 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().password(password);
    }
}
