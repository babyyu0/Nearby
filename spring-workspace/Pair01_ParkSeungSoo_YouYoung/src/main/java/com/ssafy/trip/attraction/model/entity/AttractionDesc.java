package com.ssafy.trip.attraction.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity(name = "attraction_desc")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttractionDesc {

    @OneToOne
    @JoinColumn(name = "code")
    @Comment("고유 번호")
    @Id private Attraction attraction;

    @Column(name = "desc", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("설명")
    private String desc;

    @Builder
    public AttractionDesc(Attraction attraction, String desc) {
        this.attraction = attraction;
        this.desc = desc;
    }
}
