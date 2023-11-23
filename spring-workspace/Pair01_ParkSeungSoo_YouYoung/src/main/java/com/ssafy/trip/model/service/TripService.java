package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.response.GugunGetResponseDto;
import com.ssafy.trip.model.dto.response.SidoGetResponseDto;
import com.ssafy.trip.util.exception.MyException;

import java.util.List;

public interface TripService {
    boolean refreshSido();
    boolean refreshGugun();
    List<SidoGetResponseDto> getSido();
    List<GugunGetResponseDto> getGugun();
}
