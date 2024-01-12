package com.ssafy.trip.area.model.entity.primary;

import com.ssafy.trip.area.model.entity.Sido;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GugunPk implements Serializable {
    @Column(name = "code", columnDefinition = "TINYINT UNSIGNED")
    @Comment("고유 번호")
    @PositiveOrZero(message = "하위 행정구역 코드는 0 이상이어야 합니다.")
    private int gugunCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "sido_code")
    @Comment("행정구역 고유 번호")
    @Valid
    private Sido sido;

    @Builder
    public GugunPk(int gugunCode, Sido sido) {
        this.gugunCode = gugunCode;
        this.sido = sido;
    }
}
