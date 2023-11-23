package com.ssafy.trip.area.model.service;

import com.ssafy.trip.area.model.dto.response.SidoGetResponseDto;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.repository.SidoRepository;
import com.ssafy.trip.global.util.TripUtil;
import com.ssafy.trip.global.util.ValidateUtil;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SidoServiceImpl implements SidoService {
    private final TripUtil tripUtil;
    private final SidoRepository sidoRepository;

    @Override
    @Transactional
    public boolean refreshSido() {
        tripUtil.setSido();

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SidoGetResponseDto> getSido() {
        List<Sido> sidoList = sidoRepository.findAll();

        if(sidoList.isEmpty()) {
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        List<SidoGetResponseDto> sidoResponseDtoList = new ArrayList<>();

        SidoGetResponseDto sidoGetResponseDto;
        for (Sido sido : sidoList) {
            sidoGetResponseDto = SidoGetResponseDto.from(sido);
            ValidateUtil.serverValidate(sidoGetResponseDto);  // 유효성 검사
            sidoResponseDtoList.add(sidoGetResponseDto);
        }

        return sidoResponseDtoList;
    }

}
