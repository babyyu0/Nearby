package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.command.ValidIdCommand;
import com.ssafy.trip.model.vo.MemberVO;
import com.ssafy.trip.util.exception.MyException;

import java.io.File;

public interface MemberService {
    boolean register(MemberVO memberVO) throws MyException;
    String login(MemberVO member) throws MyException;
    public boolean isExistId(ValidIdCommand validIdCommand) throws MyException;
    MemberVO selectOne(MemberVO member);
    void setProfileImg(String id, File profileFile);
}
