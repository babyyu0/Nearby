package com.ssafy.trip.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class Sido {
    @Id
    @Column(name = "code", columnDefinition = "TINYINT UNSIGNED")
    @Comment("고유 번호")
    private int sidoCode;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("행정구역명")
    private String sidoName;

    @Builder
    public Sido(int sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}
