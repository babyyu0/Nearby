package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.command.LoginCommandDto;
import com.ssafy.trip.model.dto.command.RegisterCommandDto;
import com.ssafy.trip.model.dto.command.ValidIdCommandDto;
import com.ssafy.trip.model.dto.response.LoginResponseDto;
import com.ssafy.trip.model.dto.response.ValidIdResponseDto;
import com.ssafy.trip.util.exception.MyException;

public interface MemberService {

    ValidIdResponseDto isValidId(ValidIdCommandDto validIdCommandDto) throws MyException;

    boolean register(RegisterCommandDto registerCommandDto) throws MyException;

    LoginResponseDto login(LoginCommandDto loginCommandDto) throws MyException;
}
