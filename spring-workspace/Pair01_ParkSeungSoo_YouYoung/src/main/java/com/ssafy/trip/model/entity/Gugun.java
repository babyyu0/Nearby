package com.ssafy.trip.model.entity;

import com.ssafy.trip.model.data.GugunPk;
import com.ssafy.trip.util.exception.common.CityInvalidException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor
@IdClass(GugunPk.class)
@Slf4j
public class Gugun {
	@Builder
	public Gugun(long gugunCode, String gugunName, Sido sido) throws CityInvalidException {
		setGugunCode(gugunCode);
		setGugunName(gugunName);
		setSido(sido);
	}

	@Id
	@Comment("코드")
	private long gugunCode;

	@Column(name = "gugun_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("이름")
	private String gugunName;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sido_id", nullable = false)
	private Sido sido;

	public long getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(long gugunCode) throws CityInvalidException {
		if(gugunCode <= 0) {
			log.error("Gugun: 구군 코드 받기 실패");
			throw new CityInvalidException();
		}
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) throws CityInvalidException {
		if(gugunName == null || gugunName.trim().equals("")) {
			log.error("Gugun: 구군 이름 받기 실패");
			throw new CityInvalidException();
		}
		this.gugunName = gugunName;
	}

	public Sido getSido() {
		return sido;
	}

	public void setSido(Sido sido) {
		this.sido = sido;
	}
}
