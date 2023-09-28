package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.response.CityResponse;
import com.ssafy.trip.util.exception.MyException;

public interface TripService {
    CityResponse getCity() throws MyException;
}
