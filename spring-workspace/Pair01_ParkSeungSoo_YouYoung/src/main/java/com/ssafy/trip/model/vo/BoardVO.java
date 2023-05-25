package com.ssafy.trip.model.vo;

public class BoardVO {
	private int code;
	private String type;
	private String title;
	private String contents;
	
	private double latitude;
	private double longitude;
	
	private String writerId;
	private String createdAt;
	
	public BoardVO(int code, String title, String contents, String writerId) {
		super();
		setCode(code);
		setTitle(title);
		setContents(contents);
		setWriterId(writerId);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title != null || !title.trim().equals("")) this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		if(contents != null || !contents.trim().equals("")) this.contents = contents;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		if(writerId != null || !writerId.trim().equals("")) this.writerId = writerId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		if(createdAt != null || !createdAt.trim().equals("")) this.createdAt = createdAt;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "BoardVO [code=" + code + ", type=" + type + ", title=" + title + ", contents=" + contents
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", writerId=" + writerId + ", createdAt="
				+ createdAt + "]";
	}
	
	
	
}
