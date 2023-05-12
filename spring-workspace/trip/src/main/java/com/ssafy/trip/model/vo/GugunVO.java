package com.ssafy.trip.model.vo;

public class GugunVO {
	private int gugunCode;
	private String gugunName;
	private int sidoCode;
	private SidoVO sidoVO;
	
	public GugunVO() {
		super();
	}

	public GugunVO(int gugunCode, String gugunName, int sidoCode, SidoVO sidoVO) {
		super();
		setGugunCode(gugunCode);
		setGugunName(gugunName);
		setSidoCode(sidoCode);
		setSidoVO(sidoVO);
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public SidoVO getSidoVO() {
		return sidoVO;
	}

	public void setSidoVO(SidoVO sidoVO) {
		this.sidoVO = sidoVO;
	}

	@Override
	public String toString() {
		return "GugunVO [gugunCode=" + gugunCode + ", gugunName=" + gugunName + ", sidoCode=" + sidoCode + ", sidoVO="
				+ sidoVO + "]";
	}
}
