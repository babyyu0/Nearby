package com.ssafy.trip.area.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero(message = "행정구역 코드는 0 이상이어야 합니다.")
    private int sidoCode;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("행정구역명")
    @NotBlank(message = "행정구역 이름이 존재하지 않습니다.")
    private String sidoName;

    @Builder
    public Sido(int sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}
