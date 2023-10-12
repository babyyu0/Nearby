package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.exception.trip.CityInvalidException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class Gugun {

	@Id
	@Column(name = "gugun_code")
	@Comment("코드")
	private long gugunCode;

	@Column(name = "gugun_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("이름")
	private String gugunName;

	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "sido_code")
	private Sido sido;

	public static GugunBuilder builder() {
		return Gugun.innerBuilder();
	}

	public GugunBuilder gugunCode(long gugunCode) throws CityInvalidException {
		if(gugunCode <= 0) {
			log.error("Gugun: 구군 코드 입력 실패");
			throw new CityInvalidException();
		}
		return innerBuilder().gugunCode(gugunCode);
	}

	public GugunBuilder gugunName(String gugunName) throws CityInvalidException {
		if(gugunName == null || gugunName.trim().equals("")) {
			log.error("Gugun: 지역 (구, 군) 이름 입력 실패");
			throw new CityInvalidException();
		}
		return innerBuilder().gugunName(gugunName);
	}

	public GugunBuilder sido(Sido sido) {
		this.sido = sido;
		return innerBuilder().sido(sido);
	}

}
