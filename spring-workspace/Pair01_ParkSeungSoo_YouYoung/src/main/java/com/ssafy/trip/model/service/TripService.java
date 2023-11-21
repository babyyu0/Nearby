package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.response.GugunGetResponse;
import com.ssafy.trip.model.dto.response.SidoGetResponse;
import com.ssafy.trip.util.exception.MyException;

import java.util.List;

public interface TripService {
    List<SidoGetResponse> getSido() throws MyException;
    List<GugunGetResponse> getGugun() throws MyException;
}
