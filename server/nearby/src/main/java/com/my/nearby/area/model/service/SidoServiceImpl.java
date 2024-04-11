package com.my.nearby.area.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.nearby.BusinessException;
import com.my.nearby.area.model.dao.SidoDao;
import com.my.nearby.area.model.dto.AreaCodeDto;
import com.my.nearby.area.model.dto.SidoResponseDto;
import com.my.nearby.area.model.vo.SidoVo;
import com.my.nearby.area.util.KorServiceApiUtil;
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
        if (sido == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 행정구역이 존재하지 않습니다.");
        }

        return SidoResponseDto.from(sido);
    }

    @Override
    public List<SidoResponseDto> findAllSido() {
        List<SidoVo> sidoList = sidoDao.selectAll();
        List<SidoResponseDto> sidoResponseDtoList = new ArrayList<>();
        if (sidoList == null || sidoList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 행정구역이 존재하지 않습니다.");
        }

        for (SidoVo sido : sidoList) {
            sidoResponseDtoList.add(SidoResponseDto.from(sido));
        }
        return sidoResponseDtoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void refreshSido() {
        JsonNode jsonNode = KorServiceApiUtil.getAreaCode("");
        ObjectMapper objectMapper = new ObjectMapper();
        List<AreaCodeDto> sidoDtoList = new ArrayList<>();
        sidoDtoList.add(new AreaCodeDto(0, 0, "분류되지 않음"));

        int i = 0;
        try {
            while (i < jsonNode.size()) {
                sidoDtoList.add(objectMapper.treeToValue(jsonNode.get(i++), AreaCodeDto.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        for (AreaCodeDto sidoDto : sidoDtoList) {
            SidoVo sido = new SidoVo(sidoDto.code(), sidoDto.name());
            sidoDao.insert(sido);
        }
    }
}
