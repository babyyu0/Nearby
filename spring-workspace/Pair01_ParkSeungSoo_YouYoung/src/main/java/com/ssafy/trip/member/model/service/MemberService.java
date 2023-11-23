package com.ssafy.trip.member.model.service;

import com.ssafy.trip.member.model.dto.command.LoginCommandDto;
import com.ssafy.trip.member.model.dto.command.LogoutCommandDto;
import com.ssafy.trip.member.model.dto.command.MemberGetCommandDto;
import com.ssafy.trip.member.model.dto.command.RegisterCommandDto;
import com.ssafy.trip.member.model.dto.command.ValidIdCommandDto;
import com.ssafy.trip.member.model.dto.response.LoginResponseDto;
import com.ssafy.trip.member.model.dto.response.MemberGetResponseDto;
import com.ssafy.trip.member.model.dto.response.ValidIdResponseDto;

public interface MemberService {

    ValidIdResponseDto isValidId(ValidIdCommandDto validIdCommandDto);

    boolean register(RegisterCommandDto registerCommandDto);

    LoginResponseDto login(LoginCommandDto loginCommandDto);
    
    MemberGetResponseDto getMember(MemberGetCommandDto memberGetCommandDto);

    boolean logout(LogoutCommandDto logoutCommandDto);
}
