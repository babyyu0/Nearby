package com.ssafy.trip.model.dto.request;


import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class MemberLoginRequest {
    private String memberId;
    private String password;
}
