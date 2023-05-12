package com.ssafy.trip.model.vo;

public class TripTypeVO {
	private int contentCode;
	private String contentName;
	
	public TripTypeVO(int contentCode, String contentName) {
		setContentCode(contentCode);
		setContentName(contentName);
	}

	public int getContentCode() {
		return contentCode;
	}

	public void setContentCode(int contentCode) {
		this.contentCode = contentCode;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
}
