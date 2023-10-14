package com.ssafy.trip.model.service.common;

import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.util.exception.MyException;

import java.util.List;

public interface TripApiService {
    void getSidoCode() throws MyException;
    void getGugunCode() throws MyException;
}
