package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.response.CityResponse;
import com.ssafy.trip.model.dto.response.GugunResponse;
import com.ssafy.trip.model.dto.response.SidoResponse;
import com.ssafy.trip.model.entity.Gugun;
import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.model.repository.GugunRepository;
import com.ssafy.trip.model.repository.SidoRepository;
import com.ssafy.trip.model.service.common.TripApiService;
import com.ssafy.trip.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService {

    private final TripApiService tripApiService;
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    @Autowired
    public TripServiceImpl(TripApiService tripApiService, SidoRepository sidoRepository, GugunRepository gugunRepository) {
        this.tripApiService = tripApiService;
        this.sidoRepository = sidoRepository;
        this.gugunRepository = gugunRepository;
    }

    public CityResponse getCity() throws MyException {
        List<Sido> sidoList = sidoRepository.findAll();
        List<Gugun> gugunList = gugunRepository.findAll();

        List<SidoResponse> sidoResponseList = new ArrayList<>();
        List<GugunResponse> gugunResponseList = new ArrayList<>();

        if(sidoList.isEmpty()) {
            tripApiService.getSidoCode();
            sidoList = sidoRepository.findAll();
        }
        if(gugunList.isEmpty()) {
            tripApiService.getGugunCode(sidoList);
            gugunList = gugunRepository.findAll();
        }
        for (Sido sido : sidoList) {
            sidoResponseList.add(SidoResponse.builder().sidoCode(sido.getSidoCode()).sidoName(sido.getSidoName()).build());
        }

        for (Gugun gugun : gugunList) {
            gugunResponseList.add(GugunResponse.builder().gugunCode(gugun.getGugunCode()).gugunName(gugun.getGugunName()).sidoCode(gugun.getSido().getSidoCode()).build());
        }

        return CityResponse.builder().sidoList(sidoResponseList).gugunList(gugunResponseList).build();
    }
}
