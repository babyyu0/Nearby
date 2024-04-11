package com.my.nearby.area.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.nearby.BusinessException;
import com.my.nearby.area.model.dao.GugunDao;
import com.my.nearby.area.model.dto.AreaCodeDto;
import com.my.nearby.area.model.dto.GugunResponseDto;
import com.my.nearby.area.model.vo.GugunVo;
import com.my.nearby.area.util.KorServiceApiUtil;
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
        if (gugun == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 하위 행정구역이 존재하지 않습니다.");
        }

        return GugunResponseDto.from(gugun);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GugunResponseDto> findAllGugun() {
        List<GugunVo> gugunList = gugunDao.selectAll();
        List<GugunResponseDto> gugunResponseDtoList = new ArrayList<>();
        if (gugunList == null || gugunList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 하위 행정구역이 존재하지 않습니다.");
        }

        for (GugunVo gugun : gugunList) {
            gugunResponseDtoList.add(GugunResponseDto.from(gugun));
        }
        return gugunResponseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GugunResponseDto> findAllGugunBySidoCode(int sidoCode) {
        List<GugunVo> gugunList = gugunDao.selectAllBySidoCode(sidoCode);
        List<GugunResponseDto> gugunResponseDtoList = new ArrayList<>();
        if (gugunList == null || gugunList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 하위 행정구역이 존재하지 않습니다.");
        }

        for (GugunVo gugun : gugunList) {
            gugunResponseDtoList.add(GugunResponseDto.from(gugun));
        }
        return gugunResponseDtoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void refreshGugun(int sidoCode) {
        JsonNode jsonNode = KorServiceApiUtil.getAreaCode(Integer.toString(sidoCode));
        ObjectMapper objectMapper = new ObjectMapper();
        List<AreaCodeDto> gugunDtoList = new ArrayList<>();
        gugunDtoList.add(new AreaCodeDto(0, 0, "분류되지 않음"));

        int i = 0;
        try {
            while (i < jsonNode.size()) {
                gugunDtoList.add(objectMapper.treeToValue(jsonNode.get(i++), AreaCodeDto.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        for (AreaCodeDto gugunDto : gugunDtoList) {
            GugunVo gugun = new GugunVo(gugunDto.code(), sidoCode, gugunDto.name());
            gugunDao.insert(gugun);
        }
    }
}
