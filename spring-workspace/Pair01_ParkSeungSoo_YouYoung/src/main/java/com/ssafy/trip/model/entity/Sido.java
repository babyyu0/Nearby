package com.ssafy.trip.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor
public class Sido {
	@Builder
	public Sido(int sidoCode, String sidoName) {
		setSidoCode(sidoCode);
		setSidoName(sidoName);
	}

	@Id
	@Column(name="sido_code")
	@Comment("코드")
	private int sidoCode;

	@Column(name = "sido_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("이름")
	private String sidoName;

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	@Override
	public String toString() {
		return "Sido [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
}
