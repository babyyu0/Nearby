package com.ssafy.trip.area.model.service;

import com.ssafy.trip.area.model.dto.response.GugunGetResponseDto;
import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.area.model.repository.GugunRepository;
import com.ssafy.trip.global.util.TripUtil;
import com.ssafy.trip.global.util.ValidateUtil;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GugunServiceImpl implements GugunService {
    private final TripUtil tripUtil;
    private final GugunRepository gugunRepository;

    @Override
    @Transactional
    public boolean refreshGugun() {
        tripUtil.setGugun();

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GugunGetResponseDto> getGugun() {
        List<Gugun> gugunList = gugunRepository.findAll();

        if (gugunList.isEmpty()) {
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
