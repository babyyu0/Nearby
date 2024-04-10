package com.my.nearby.area;

import java.util.List;

public interface GugunService {
    GugunResponseDto findGugun(int gugunCode);
    List<GugunResponseDto> findAllGugun();
    List<GugunResponseDto> findAllGugunBySidoCode(int sidoCode);
}
