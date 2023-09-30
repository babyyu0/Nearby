package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.command.ValidIdCommand;
import com.ssafy.trip.model.dto.command.MemberCreateCommand;
import com.ssafy.trip.model.dto.response.ValidIdResponse;
import com.ssafy.trip.model.vo.Member;
import com.ssafy.trip.util.exception.MyException;

import java.io.File;

public interface MemberService {
    ValidIdResponse isValidId(ValidIdCommand validIdCommand) throws MyException;
    boolean register(MemberCreateCommand memberCreateCommand) throws MyException;
    // String login(Member member) throws MyException;
    // Member selectOne(Member member);
    // void setProfileImg(String id, File profileFile);
}
