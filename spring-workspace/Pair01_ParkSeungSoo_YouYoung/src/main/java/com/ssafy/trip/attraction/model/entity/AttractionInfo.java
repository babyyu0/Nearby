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

    @OneToOne
    @JoinColumn(name = "code")
    @Comment("고유 번호")
    @Id private Attraction attraction;

    @Column(name = "addr1", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("주소")
    private String addr1;

    @Column(name = "addr2", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("상세 주소")
    private String addr2;

    @Column(name = "tel", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("전화 번호")
    private String tel;

    @Column(name = "map_x", nullable = false)
    @Comment("x 좌표")
    private double mapX;

    @Column(name = "map_y", nullable = false)
    @Comment("y 좌표")
    private double mapY;

    @Column(name = "cat1", nullable = false, columnDefinition = "VARCHAR(3) CHARACTER SET UTF8")
    @Comment("분류1")
    private String cat1;

    @Column(name = "cat1", nullable = false, columnDefinition = "VARCHAR(5) CHARACTER SET UTF8")
    @Comment("분류2")
    private String cat2;

    @Column(name = "cat1", nullable = false, columnDefinition = "VARCHAR(9) CHARACTER SET UTF8")
    @Comment("분류3")
    private String cat3;

    @Builder
    public AttractionInfo(Attraction attraction, String addr1, String addr2, String tel, double mapX, double mapY, String cat1, String cat2, String cat3) {
        this.attraction = attraction;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.tel = tel;
        this.mapX = mapX;
        this.mapY = mapY;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
    }
}
