package com.ssafy.trip.model.data;

import com.ssafy.trip.model.entity.Sido;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "InnerBuilder")
@Slf4j
@ToString
public class GugunPk implements Serializable {
    private long gugunCode;
    private Sido sido;

    public static GugunPkBuilder builder() {
        return GugunPk.InnerBuilder();
    }

    public GugunPkBuilder gugunCode(long gugunCode) {
        return InnerBuilder().gugunCode(gugunCode);
    }

    public GugunPkBuilder sido(Sido sido) {
        return InnerBuilder().sido(sido);
    }
}
