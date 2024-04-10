package com.my.nearby.area;

import com.my.nearby.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SidoServiceImpl implements SidoService {
    private final SidoDao sidoDao;
    @Override
    @Transactional(readOnly = true)
    public SidoResponseDto findSido(int sidoCode) {
        SidoVo sido = sidoDao.select(sidoCode);
        if(sido == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 행정구역이 존재하지 않습니다.");
        }

        return SidoResponseDto.from(sido);
    }

    @Override
    public List<SidoResponseDto> findAllSido() {
        List<SidoVo> sidoList = sidoDao.selectAll();
        List<SidoResponseDto> sidoResponseDtoList = new ArrayList<>();
        if(sidoList == null || sidoList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 행정구역이 존재하지 않습니다.");
        }

        for(SidoVo sido : sidoList) {
            sidoResponseDtoList.add(SidoResponseDto.from(sido));
        }
        return sidoResponseDtoList;
    }
}
