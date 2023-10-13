package com.ssafy.trip.model.dto.request;


import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class MemberCreateRequest implements RegexData {
    private String memberId;
    private String password;
    private String name;

    private int sidoCode;
    private int gugunCode;
}
