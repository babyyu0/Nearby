package com.ssafy.trip.attraction.model.entity;

import com.ssafy.trip.global.model.entity.Base;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity(name = "attraction_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttractionInfo extends Base {
    @Column(columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    @Id
    private int code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", nullable = false, columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    private Attraction attraction;

    @Column(name = "addr1", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("주소")
    private String addr1;

    @Column(name = "addr2", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("상세 주소")
    private String addr2;

    @Column(name = "tel", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("전화 번호")
    private String tel;

    @Column(name = "longitude", nullable = false)
    @Comment("x 좌표")
    private double longitude;

    @Column(name = "latitude", nullable = false)
    @Comment("y 좌표")
    private double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat1_code")
    @Comment("분류 1")
    private Cat cat1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat2_code")
    @Comment("분류 2")
    private Cat cat2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat3_code")
    @Comment("분류 3")
    private Cat cat3;

    @Builder
    public AttractionInfo(int code, String addr1, String addr2, String tel, double longitude, double latitude, Cat cat1, Cat cat2, Cat cat3) {
        this.code = code;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.tel = tel;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
    }
}
