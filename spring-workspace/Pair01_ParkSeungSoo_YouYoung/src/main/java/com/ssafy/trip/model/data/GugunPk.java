package com.ssafy.trip.model.data;

import com.ssafy.trip.model.entity.Sido;
import lombok.Data;

import java.io.Serializable;

@Data
public class GugunPk implements Serializable {
    private long gugunCode;
    private Sido sido;
}
