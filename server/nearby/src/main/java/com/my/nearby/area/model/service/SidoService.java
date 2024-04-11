package com.my.nearby.area.model.service;

import com.my.nearby.area.model.dto.SidoResponseDto;

import java.util.List;

public interface SidoService {
    SidoResponseDto findSido(int sidoCode);

    List<SidoResponseDto> findAllSido();

    void refreshSido();
}
