package com.ssafy.trip.controller;

import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.model.service.common.TripApiService;
import com.ssafy.trip.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {
    private final TripApiService tripApiService;

    @Autowired
    public ApiController(TripApiService tripApiService) {
        this.tripApiService = tripApiService;
    }

    @GetMapping("city")
    public void getCity() throws MyException {  // 모든 지역 반환
        tripApiService.getSidoCode();
        tripApiService.getGugunCode();
    }

    @GetMapping("sido")
    public void getSido() throws MyException {  // 지역(시, 도) 반환
        tripApiService.getSidoCode();
    }

    @GetMapping("gugun")
    public void getGugun() throws MyException {  // 지역(구, 군) 반환
        tripApiService.getGugunCode();
    }

    @GetMapping("trip-type")
    public void getTripType() throws MyException {  // 관광지 타입 반환
        // tripApiService.getTripType();
    }

    @GetMapping("attraction")
    public void getAttraction() throws MyException {  // 관광지 반환
        // tripApiService.getAttraction();
    }
}
