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
public class AttractionInfo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @Comment("고유 번호")
    @Id private Attraction code;

    @Column(name = "addr1", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("주소")
    private String addr1;

    @Column(name = "addr2", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("상세 주소")
    private String addr2;

    @Column(name = "tel", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("전화번호")
    private String tel;

    @Column(name = "map_x", nullable = false)
    @Comment("x 좌표")
    private double mapX;

    @Column(name = "map_y", nullable = false)
    @Comment("y 좌표")
    private double mapY;

    @Builder
    public AttractionInfo(Attraction code, String addr1, String addr2, String tel, double mapX, double mapY) {
        this.code = code;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.tel = tel;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
