package com.ssafy.trip.member.model.service;

import com.ssafy.trip.member.model.dto.command.LoginCommandDto;
import com.ssafy.trip.member.model.dto.command.LogoutCommandDto;
import com.ssafy.trip.member.model.dto.command.RegisterCommandDto;
import com.ssafy.trip.member.model.dto.command.ValidIdCommandDto;
import com.ssafy.trip.member.model.dto.response.LoginResponseDto;
import com.ssafy.trip.member.model.dto.response.ValidIdResponseDto;
import com.ssafy.trip.global.util.exception.MyException;

public interface MemberService {

    ValidIdResponseDto isValidId(ValidIdCommandDto validIdCommandDto) throws MyException;

    boolean register(RegisterCommandDto registerCommandDto) throws MyException;

    LoginResponseDto login(LoginCommandDto loginCommandDto) throws MyException;

    boolean logout(LogoutCommandDto logoutCommandDto) throws MyException;
}
