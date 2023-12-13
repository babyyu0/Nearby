package com.ssafy.trip.attraction.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cat extends BootstrapMethodError {

    @NotBlank(message = "분류 고유 번호가 존재하지 않습니다.")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(10) CHARACTER SET UTF8")
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
}
