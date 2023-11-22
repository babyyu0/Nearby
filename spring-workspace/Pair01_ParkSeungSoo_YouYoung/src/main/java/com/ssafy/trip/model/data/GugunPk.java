package com.ssafy.trip.model.data;

import com.ssafy.trip.model.entity.Sido;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

public class GugunPk implements Serializable {
    @Column(name = "code", columnDefinition = "TINYINT UNSIGNED")
    @Comment("고유 번호")
    private int gugunCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "sido_code")
    @Comment("행정구역 고유 번호")
    private Sido sido;

    @Builder
    public GugunPk(int gugunCode, Sido sido) {
        this.gugunCode = gugunCode;
        this.sido = sido;
    }
}
