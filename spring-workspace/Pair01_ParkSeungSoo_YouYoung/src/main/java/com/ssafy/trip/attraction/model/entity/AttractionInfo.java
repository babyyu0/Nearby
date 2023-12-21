package com.ssafy.trip.attraction.model.entity;

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

@Entity(name = "attraction_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttractionInfo extends Base {
    @Positive(message = "고유 번호는 양수여야 합니다.")
    @Column(columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    @Id
    private int code;

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", nullable = false, columnDefinition = "INT UNSIGNED")
    @Comment("고유 번호")
    private Attraction attraction;

    @Size(max = 100, message = "주소는 100자 이하여야 합니다.")
    @Column(name = "addr1", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("주소")
    private String addr1;

    @Size(max = 255, message = "상세 주소는 255자 이하여야 합니다.")
    @Column(name = "addr2", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("상세 주소")
    private String addr2;

    @Size(max = 50, message = "전화 번호는 50자 이하여야 합니다.")
    @Column(name = "tel", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("전화 번호")
    private String tel;

    @Column(name = "longitude", nullable = false)
    @Comment("x 좌표")
    private double longitude;

    @Column(name = "latitude", nullable = false)
    @Comment("y 좌표")
    private double latitude;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat1_code")
    @Comment("분류 1")
    private Cat cat1;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat2_code")
    @Comment("분류 2")
    private Cat cat2;

    @Valid
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
