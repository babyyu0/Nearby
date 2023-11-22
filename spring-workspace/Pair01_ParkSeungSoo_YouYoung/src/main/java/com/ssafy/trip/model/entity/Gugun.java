package com.ssafy.trip.model.entity;

import com.ssafy.trip.model.data.GugunPk;
import jakarta.persistence.*;
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
	@Column(name = "code", columnDefinition = "TINYINT UNSIGNED")
	@Comment("고유 번호")
	private int gugunCode;

	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("하위 행정구역명")
	private String gugunName;

	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "sido_code")
	@Comment("행정구역 고유 번호")
	private Sido sido;

	@Builder
	public Gugun(int gugunCode, String gugunName, Sido sido) {
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sido = sido;
	}
}
