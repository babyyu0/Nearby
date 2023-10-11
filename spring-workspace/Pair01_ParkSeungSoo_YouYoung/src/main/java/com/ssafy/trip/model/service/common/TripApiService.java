package com.ssafy.trip.model.service.common;

import com.ssafy.trip.model.dto.command.MemberCreateCommand;
import com.ssafy.trip.model.dto.command.ValidIdCommand;
import com.ssafy.trip.model.dto.response.ValidIdResponse;
import com.ssafy.trip.util.exception.MyException;

public interface TripApiService {
    void getCityCode() throws MyException;
}
