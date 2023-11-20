package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.response.*;
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
    public List<SidoGetResponse> getSido() throws MyException {
        List<Sido> sidoList = sidoRepository.findAll();

        if(sidoList.isEmpty()) {
            throw new MyException(ErrorMessage.SIDO_INVALID);
        }

        List<SidoGetResponse> sidoResponseList = new ArrayList<>();

        SidoGetResponse sidoGetResponse;
        for (Sido sido : sidoList) {
            sidoGetResponse = (SidoGetResponse) ValidateUtil.validate(SidoGetResponse.from(sido));
            sidoResponseList.add(sidoGetResponse);
        }

        return sidoResponseList;
    }

    @Override
    public List<GugunGetResponse> getGugun() throws MyException {
        List<Gugun> gugunList = gugunRepository.findAll();

        if(gugunList.isEmpty()) {
            throw new MyException(ErrorMessage.GUGUN_INVALID);
        }

        List<GugunGetResponse> gugunResponseList = new ArrayList<>();

        GugunGetResponse gugunGetResponse;
        for (Gugun gugun : gugunList) {
            gugunGetResponse = (GugunGetResponse) ValidateUtil.validate(GugunGetResponse.from(gugun));
            gugunResponseList.add(gugunGetResponse);
        }

        return gugunResponseList;
    }
}
