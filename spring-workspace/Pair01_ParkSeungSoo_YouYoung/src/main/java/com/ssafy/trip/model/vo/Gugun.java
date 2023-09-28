package com.ssafy.trip.model.vo;

import com.ssafy.trip.util.exception.common.CityInvalidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Gugun {
	public Gugun(int gugunCode, String gugunName, int sidoCode) throws CityInvalidException {
		setGugunCode(gugunCode);
		setGugunName(gugunName);
		setSidoCode(sidoCode);
	}
	private int gugunCode;
	private String gugunName;
	private int sidoCode;

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) throws CityInvalidException {
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
	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) throws CityInvalidException {
		if(sidoCode <= 0) {
			log.error("Gugun: 시도 코드 받기 실패");
			throw new CityInvalidException();
		}
		this.sidoCode = sidoCode;
	}
}
