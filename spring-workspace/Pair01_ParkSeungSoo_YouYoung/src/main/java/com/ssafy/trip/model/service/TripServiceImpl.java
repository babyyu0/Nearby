package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dao.GugunDao;
import com.ssafy.trip.model.dao.SidoDao;
import com.ssafy.trip.model.dto.response.CityResponse;
import com.ssafy.trip.model.dto.response.GugunResponse;
import com.ssafy.trip.model.dto.response.SidoResponse;
import com.ssafy.trip.model.vo.GugunVO;
import com.ssafy.trip.model.vo.SidoVO;
import com.ssafy.trip.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService {

    private final SidoDao sidoDao;
    private final GugunDao gugunDao;

    @Autowired
    public TripServiceImpl(SidoDao sidoDao, GugunDao gugunDao) {
        this.sidoDao = sidoDao;
        this.gugunDao = gugunDao;
    }

    public CityResponse getCity() throws MyException {
        List<SidoVO> sidoList = sidoDao.selectAll();
        List<GugunVO> gugunList = gugunDao.selectAll();

        List<SidoResponse> sidoResponseList = new ArrayList<>();
        List<GugunResponse> gugunResponseList = new ArrayList<>();

        for (SidoVO sido : sidoList) {
            sidoResponseList.add(new SidoResponse(sido.getSidoCode(), sido.getSidoName()));
        }

        for (GugunVO gugun : gugunList) {
            gugunResponseList.add(new GugunResponse(gugun.getGugunCode(), gugun.getGugunName(), gugun.getSidoCode()));
        }

        return new CityResponse(sidoResponseList, gugunResponseList);
    }
}
