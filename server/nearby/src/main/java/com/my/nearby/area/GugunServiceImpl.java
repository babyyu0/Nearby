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
public class GugunServiceImpl implements GugunService {
    private final GugunDao gugunDao;
    @Override
    @Transactional(readOnly = true)
    public GugunResponseDto findGugun(int gugunCode) {
        GugunVo gugun = gugunDao.select(gugunCode);
        if(gugun == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 하위 행정구역이 존재하지 않습니다.");
        }

        return GugunResponseDto.from(gugun);
    }

    @Override
    public List<GugunResponseDto> findAllGugun() {
        List<GugunVo> gugunList = gugunDao.selectAll();
        List<GugunResponseDto> gugunResponseDtoList = new ArrayList<>();
        if(gugunList == null || gugunList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 하위 행정구역이 존재하지 않습니다.");
        }

        for(GugunVo gugun : gugunList) {
            gugunResponseDtoList.add(GugunResponseDto.from(gugun));
        }
        return gugunResponseDtoList;
    }

    @Override
    public List<GugunResponseDto> findAllGugunBySidoCode(int sidoCode) {
        List<GugunVo> gugunList = gugunDao.selectAllBySidoCode(sidoCode);
        List<GugunResponseDto> gugunResponseDtoList = new ArrayList<>();
        if(gugunList == null || gugunList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 하위 행정구역이 존재하지 않습니다.");
        }
        
        for(GugunVo gugun : gugunList) {
            gugunResponseDtoList.add(GugunResponseDto.from(gugun));
        }
        return gugunResponseDtoList;
    }
}
