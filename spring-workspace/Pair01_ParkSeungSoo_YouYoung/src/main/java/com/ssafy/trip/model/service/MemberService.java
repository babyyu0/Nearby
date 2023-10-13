package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.command.MemberLoginCommand;
import com.ssafy.trip.model.dto.command.ValidIdCommand;
import com.ssafy.trip.model.dto.command.MemberCreateCommand;
import com.ssafy.trip.model.dto.response.MemberGetResponse;
import com.ssafy.trip.model.dto.response.ValidIdResponse;
import com.ssafy.trip.util.exception.MyException;

public interface MemberService {
    ValidIdResponse isValidId(ValidIdCommand validIdCommand) throws MyException;
    boolean register(MemberCreateCommand memberCreateCommand) throws MyException;
    MemberGetResponse login(MemberLoginCommand memberLoginCommand) throws MyException;
    // MemberVO selectOne(MemberVO member);
    // void setProfileImg(String id, File profileFile);
}
