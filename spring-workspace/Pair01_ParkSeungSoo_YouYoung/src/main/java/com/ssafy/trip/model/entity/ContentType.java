package com.ssafy.trip.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class ContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT UNSIGNED")
    @Comment("고유 번호")
    private int code;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("콘텐츠명")
    private String name;

    @Builder
    public ContentType(int id, String name) {
        this.code = code;
        this.name = name;
    }
}
