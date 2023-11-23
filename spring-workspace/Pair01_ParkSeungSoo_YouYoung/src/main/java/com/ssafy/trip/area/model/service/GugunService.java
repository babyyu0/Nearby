package com.ssafy.trip.area.model.service;

import com.ssafy.trip.area.model.dto.response.GugunGetResponseDto;
import java.util.List;

public interface GugunService {
    boolean refreshGugun();
    List<GugunGetResponseDto> getGugun();
}
