package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.command.ExistIdCommand;
import com.ssafy.trip.model.vo.MemberVO;
import com.ssafy.trip.util.exception.MyException;

import java.io.File;

public interface MemberService {
    public boolean isExistId(ExistIdCommand existIdCommand) throws MyException;
    boolean register(MemberVO memberVO) throws MyException;
    String login(MemberVO member) throws MyException;
    MemberVO selectOne(MemberVO member);
    void setProfileImg(String id, File profileFile);
}
