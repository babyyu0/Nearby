package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class MemberLoginResponse {
    private String name;
    private byte[] profile;

    public static MemberLoginResponseBuilder builder() {
        return MemberLoginResponse.innerBuilder();
    }

    public MemberLoginResponseBuilder name(String name) throws MemberInvalidException {
        if(name == null || name.trim().equals("") || 20 < name.length() ) {
            log.error("MemberLoginResponse: 이름 입력 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().name(name);
    }

    public MemberLoginResponseBuilder profile(byte[] profile) throws MemberInvalidException {
        if(profile == null || profile.length == 0) {
            log.error("MemberLoginResponse: 프로필 입력 실패");
            throw new MemberInvalidException();
        }

        return innerBuilder().profile(profile);
    }
}
