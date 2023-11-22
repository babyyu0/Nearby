package com.ssafy.trip.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Attraction {

    @Column(columnDefinition = "TINYINT UNSIGNED")
    @Comment("고유 번호")
    @Id private int code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_type_code", nullable = false)
    @Comment("관광 타입")
        private ContentType contentType;

    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("관광지명")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "gugun_code", referencedColumnName = "code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '행정구역 고유 번호'"),
            @JoinColumn(name = "sido_code", referencedColumnName = "sido_code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '하위 행정구역 고유 번호'"),
    })
    private Gugun gugun;

    @Builder
    public Attraction(int code, ContentType contentType, String title, Gugun gugun) {
        this.code = code;
        this.contentType = contentType;
        this.title = title;
        this.gugun = gugun;
    }
}
