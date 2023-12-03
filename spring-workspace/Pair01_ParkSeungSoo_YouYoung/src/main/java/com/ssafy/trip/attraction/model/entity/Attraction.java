package com.ssafy.trip.attraction.model.entity;

import com.ssafy.trip.area.model.entity.Gugun;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne(mappedBy = "attraction")
    AttractionInfo attractionInfo;

    @OneToOne(mappedBy = "attraction")
    AttractionDesc attractionDesc;

    @OneToMany(mappedBy = "attraction")
    List<AttractionHeart> attractionHeartList;

    @Column(name = "img", columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("관광지 이미지")
    private String img;

    @Column(name = "img_sub", columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("관광지 이미지 2")
    private String imgSub;

    @Column(name = "crated_at", nullable = false, columnDefinition = "VARCHAR(14) CHARACTER SET UTF8")
    @Comment("생성 시간")
    private String createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "VARCHAR(14) CHARACTER SET UTF8")
    @Comment("수정 시간")
    private String updatedAt;

    @Builder
    public Attraction(int code, ContentType contentType, String title, Gugun gugun, String img, String imgSub, String createdAt, String updatedAt) {
        this.code = code;
        this.contentType = contentType;
        this.title = title;
        this.gugun = gugun;
        this.img = img;
        this.imgSub = imgSub;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
