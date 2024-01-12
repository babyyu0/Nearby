package com.ssafy.trip.area.model.entity;

import com.ssafy.trip.area.model.entity.primary.GugunPk;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@IdClass(GugunPk.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Gugun {
	@Id
	private int gugunCode;

	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("하위 행정구역명")
	@NotBlank(message = "하위 행정구역 이름이 존재하지 않습니다.")
	private String gugunName;

	@ManyToOne(fetch = FetchType.LAZY)
	private Sido sido;

	@Builder
	public Gugun(int gugunCode, String gugunName, Sido sido) {
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sido = sido;
	}
}
