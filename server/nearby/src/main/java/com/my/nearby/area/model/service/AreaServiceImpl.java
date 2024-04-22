package com.my.nearby.area.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.nearby.BusinessException;
import com.my.nearby.area.model.dao.AreaDao;
import com.my.nearby.area.model.dto.AreaCodeDto;
import com.my.nearby.area.model.dto.AreaResponseDto;
import com.my.nearby.area.model.vo.AreaVo;
import com.my.nearby.util.KorServiceApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaDao areaDao;

    @Override
    @Transactional(readOnly = true)
    public AreaResponseDto findArea(int areaCode, int sigunguCode) {
        AreaVo area = areaDao.select(areaCode, sigunguCode);
        if (area == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 지역이 존재하지 않습니다.");
        }

        return AreaResponseDto.from(area);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaResponseDto> findAllArea() {
        List<AreaVo> areaList = areaDao.selectAll();
        List<AreaResponseDto> areaResponseDtoList = new ArrayList<>();
        if (areaList == null || areaList.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "일치하는 지역이 존재하지 않습니다.");
        }

        for (AreaVo area : areaList) {
            areaResponseDtoList.add(AreaResponseDto.from(area));
        }
        return areaResponseDtoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void refreshArea() {
        JsonNode jsonNode = KorServiceApiUtil.getAreaCode("");
        ObjectMapper objectMapper = new ObjectMapper();
        List<AreaCodeDto> areaDtoList = new ArrayList<>();

        int i = 0;
        try {
            while (i < jsonNode.size()) {
                areaDtoList.add(objectMapper.treeToValue(jsonNode.get(i++), AreaCodeDto.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        for (AreaCodeDto areaDto : areaDtoList) {
            AreaVo area = new AreaVo(areaDto.code(), 0, areaDto.name());
            areaDao.insert(area);
        }
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void refreshSigungu(int areaCode) {
        JsonNode jsonNode = KorServiceApiUtil.getAreaCode(Integer.toString(areaCode));
        ObjectMapper objectMapper = new ObjectMapper();
        List<AreaCodeDto> areaDtoList = new ArrayList<>();

        int i = 0;
        try {
            while (i < jsonNode.size()) {
                areaDtoList.add(objectMapper.treeToValue(jsonNode.get(i++), AreaCodeDto.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        for (AreaCodeDto areaDto : areaDtoList) {
            AreaVo area = new AreaVo(areaCode, areaDto.code(), areaDto.name());
            areaDao.insert(area);
        }
    }
}
