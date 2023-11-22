package com.ssafy.trip.model.service;

import com.ssafy.trip.aop.TimeTrace;
import com.ssafy.trip.model.dto.response.GugunGetResponseDto;
import com.ssafy.trip.model.dto.response.SidoGetResponseDto;
import com.ssafy.trip.model.entity.Gugun;
import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.model.repository.GugunRepository;
import com.ssafy.trip.model.repository.SidoRepository;
import com.ssafy.trip.util.ValidateUtil;
import com.ssafy.trip.util.exception.ErrorMessage;
import com.ssafy.trip.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService {

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    @Autowired
    public TripServiceImpl(SidoRepository sidoRepository, GugunRepository gugunRepository) {
        this.sidoRepository = sidoRepository;
        this.gugunRepository = gugunRepository;
    }

    @Override
    @TimeTrace
    public List<SidoGetResponseDto> getSido() throws MyException {
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

    @Override
    @TimeTrace
    public List<GugunGetResponseDto> getGugun() throws MyException {
        List<Gugun> gugunList = gugunRepository.findAll();

        if(gugunList.isEmpty()) {
            throw new MyException(ErrorMessage.GUGUN_INVALID);
        }

        List<GugunGetResponseDto> gugunResponseDtoList = new ArrayList<>();

        GugunGetResponseDto gugunGetResponseDto;
        for (Gugun gugun : gugunList) {
            gugunGetResponseDto = GugunGetResponseDto.from(gugun);
            ValidateUtil.serverValidate(gugunGetResponseDto);  // 유효성 검사
            gugunResponseDtoList.add(gugunGetResponseDto);
        }

        return gugunResponseDtoList;
    }
}
