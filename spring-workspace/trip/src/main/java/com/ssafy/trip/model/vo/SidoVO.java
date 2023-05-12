package com.ssafy.trip.model.vo;

public class SidoVO {
	private int sidoCode;
	private String sidoName;
	
	public SidoVO() {
		super();
	}

	public SidoVO(int sidoCode, String sidoName) {
		setSidoCode(sidoCode);
		setSidoName(sidoName);
	}

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
		return "SidoVO [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
}
