package com.my.nearby.area;

import java.util.List;

public interface SidoService {
    SidoResponseDto findSido(int sidoCode);
    List<SidoResponseDto> findAllSido();
}
