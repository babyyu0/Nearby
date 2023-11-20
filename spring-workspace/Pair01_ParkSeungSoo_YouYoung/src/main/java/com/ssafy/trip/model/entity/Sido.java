package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.exception.trip.CityInvalidException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Slf4j
public class Sido {
    @Id
    @Column(name = "sido_code")
    @Comment("코드")
    private int sidoCode;

    @Column(name = "sido_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("이름")
    private String sidoName;
}
