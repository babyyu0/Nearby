package com.ssafy.trip.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity(name = "attraction_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttractionDesc {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @Comment("고유 번호")
    @Id private Attraction code;

    @Column(name = "desc", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("설명")
    private String desc;

    @Builder
    public AttractionDesc(Attraction code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
