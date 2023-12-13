package com.ssafy.trip.area.util;

import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.entity.primary.GugunPk;
import com.ssafy.trip.area.model.repository.GugunRepository;
import com.ssafy.trip.area.model.repository.SidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaUtil {
    private static SidoRepository sidoRepository;
    private static GugunRepository gugunRepository;

    @Autowired
    public AreaUtil(SidoRepository sidoRepository, GugunRepository gugunRepository) {
        AreaUtil.sidoRepository = sidoRepository;
        AreaUtil.gugunRepository = gugunRepository;
    }

    public static Sido getSido(int sidoCode) {
        return sidoRepository.findById(sidoCode).orElse(null);
    }

    public static Gugun getGugun(int sidoCode, int gugunCode) {
        GugunPk gugunPk = GugunPk.builder()
                .sido(getSido(sidoCode))
                .gugunCode(gugunCode)
                .build();

        return gugunRepository.findById(gugunPk).orElse(null);
    }
}
