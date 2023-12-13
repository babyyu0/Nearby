package com.ssafy.trip.area.model.service;

import com.ssafy.trip.area.model.dto.response.SidoGetResponseDto;
import java.util.List;

public interface SidoService {
    boolean refreshSido();
    List<SidoGetResponseDto> getSidoList();

}
