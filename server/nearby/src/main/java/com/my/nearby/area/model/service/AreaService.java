package com.my.nearby.area.model.service;

import com.my.nearby.area.model.dto.AreaResponseDto;

import java.util.List;

public interface AreaService {
    AreaResponseDto findArea(int areaCode, int sigunguCode);

    List<AreaResponseDto> findAllArea();

    void refreshArea();
    void refreshSigungu(int areaCode);
}
