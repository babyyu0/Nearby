package com.ssafy.trip.attraction.model.entity;

import com.ssafy.trip.global.model.entity.Base;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity(name = "attraction_desc")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttractionDesc extends Base {
    @Column(columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    @Id
    private int code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", nullable = false, columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    private Attraction attraction;

    @Column(name = "desc", nullable = false, columnDefinition = "TEXT CHARACTER SET UTF8")
    @Comment("설명")
    private String desc;

    @Builder
    public AttractionDesc(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
