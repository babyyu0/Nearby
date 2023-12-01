package com.ssafy.trip.member.model.entity;

import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.attraction.model.entity.AttractionHeart;
import com.ssafy.trip.global.util.data.RegexPattern;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유 번호")
    private int code;

    @NotBlank(message = "아이디가 존재하지 않습니다.")
    @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
    @Column(name = "member_id", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("아이디")
    private String id;

    @NotBlank(message = "비밀번호가 존재하지 않습니다.")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "비밀번호가 올바르지 않습니다.")
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("비밀번호")
    private String password;

    @NotBlank(message = "회원명이 존재하지 않습니다.")
    @Size(min = 2, max = 20, message = "회원명은 최소 2자, 최대 20자여야 합니다.")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("회원명")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "gugun_code", referencedColumnName = "code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '행정구역 고유 번호'"),
            @JoinColumn(name = "sido_code", referencedColumnName = "sido_code", nullable = false, columnDefinition = "TINYINT UNSIGNED COMMENT '하위 행정구역 고유 번호'"),
    })
    @Valid
    private Gugun gugun;

    @Column(name = "profile_img", columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("프로필 이미지")
    private String profileImg;

    @OneToMany(mappedBy = "member")
    List<AttractionHeart> attractionHeartList;

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
