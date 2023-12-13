package com.ssafy.trip.attraction.model.entity;

import com.ssafy.trip.global.model.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Cat extends Base {

    @NotBlank(message = "분류 고유 번호가 존재하지 않습니다.")
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(10) CHARACTER SET UTF8")
    @Comment("고유 번호")
    @Id
    private String code;

    @NotBlank(message = "분류명이 존재하지 않습니다.")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("분류명")
    private String name;

    @PositiveOrZero(message = "분류 깊이는 양수여야 합니다.")
    @Column(name = "depth", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    @Comment("분류명")
    private int depth;

    @Builder
    public Cat(String code, String name, int depth) {
        this.code = code;
        this.name = name;
        this.depth = depth;
    }
}
