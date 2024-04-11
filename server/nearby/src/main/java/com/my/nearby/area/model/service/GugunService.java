package com.my.nearby.area.model.service;

import com.my.nearby.area.model.dto.GugunResponseDto;

import java.util.List;

public interface GugunService {
    GugunResponseDto findGugun(int gugunCode);

    List<GugunResponseDto> findAllGugun();

    List<GugunResponseDto> findAllGugunBySidoCode(int sidoCode);

    void refreshGugun(int sidoCode);
}
