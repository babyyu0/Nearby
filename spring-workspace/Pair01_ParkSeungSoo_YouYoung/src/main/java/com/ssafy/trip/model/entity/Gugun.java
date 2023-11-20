package com.ssafy.trip.model.entity;

import com.ssafy.trip.model.data.GugunPk;
import com.ssafy.trip.util.exception.trip.CityInvalidException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@IdClass(GugunPk.class)
@Getter
@Slf4j
public class Gugun {

	@Id
	@Column(name = "gugun_code")
	@Comment("코드")
	private int gugunCode;

	@Column(name = "gugun_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("구군명")
	private String gugunName;

	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "sido_code")
	@Comment("시도 코드")
	private Sido sido;
}
