package com.ssafy.trip.attraction.model.entity;

import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.global.model.entity.Base;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Attraction extends Base {
    @Positive(message = "고유 번호는 양수여야 합니다.")
    @Column(columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    @Id
    private int code;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_type_code", nullable = false)
    @Comment("관광 타입")
    private ContentType contentType;

    @Size(max = 100, message = "관광지명은 100자 이하여야 합니다.")
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("관광지명")
    private String title;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "gugun_code", referencedColumnName = "code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '행정구역 고유 번호'"),
            @JoinColumn(name = "sido_code", referencedColumnName = "sido_code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '하위 행정구역 고유 번호'"),
    })
    private Gugun gugun;

    @OneToOne(mappedBy = "attraction")
    private AttractionInfo attractionInfo;

    @OneToOne(mappedBy = "attraction")
    private AttractionDesc attractionDesc;

    @OneToMany(mappedBy = "attraction")
    private List<AttractionHeart> attractionHeartList;

    @Size(max = 255, message = "관광지 이미지는 255자 이하여야 합니다.")
    @Column(name = "img", columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("관광지 이미지")
    private String img;

    @Size(max = 255, message = "관광지 이미지 2는 255자 이하여야 합니다.")
    @Column(name = "img_sub", columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("관광지 이미지 2")
    private String imgSub;

    @Column(name = "created_time", nullable = false, columnDefinition = "VARCHAR(14) CHARACTER SET UTF8")
    @Comment("생성 시간 (외부 데이터)")
    private String createdTime;

    @Column(name = "updated_time", nullable = false, columnDefinition = "VARCHAR(14) CHARACTER SET UTF8")
    @Comment("수정 시간 (외부 데이터)")
    private String updatedTime;

    @Builder
    public Attraction(int code, ContentType contentType, String title, Gugun gugun, String img, String imgSub, String createdTime, String updatedTime) {
        this.code = code;
        this.contentType = contentType;
        this.title = title;
        this.gugun = gugun;
        this.img = img;
        this.imgSub = imgSub;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
