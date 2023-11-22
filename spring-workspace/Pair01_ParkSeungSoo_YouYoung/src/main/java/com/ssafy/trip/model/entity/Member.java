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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유 번호")
    private int code;

    @Column(name = "member_id", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("아이디")
    private String id;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("비밀번호")
    private String password;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("회원명")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "gugun_code", referencedColumnName = "code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '행정구역 고유 번호'"),
            @JoinColumn(name = "sido_code", referencedColumnName = "sido_code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '하위 행정구역 고유 번호'"),
    })
    private Gugun gugun;

    @Column(name = "profile_img", columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("프로필 이미지")
    private String profileImg;

    @Builder
    public Member(int code, String id, String password, String name, Gugun gugun, String profileImg) {
        this.code = code;
        this.id = id;
        this.password = password;
        this.name = name;
        this.gugun = gugun;
        this.profileImg = profileImg;
    }
}
