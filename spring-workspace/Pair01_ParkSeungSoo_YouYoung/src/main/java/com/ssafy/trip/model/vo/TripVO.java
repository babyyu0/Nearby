package com.ssafy.trip.model.vo;

public class TripVO {
	private int contentId;
	private int sidoCode;
	private int gugunCode;
	private int contentTypeId;
	
	private String title;
	private String addr;
	private String addr2;
	private double latitude;
	private double longitude;
	
	private String firstImage;

	public TripVO() {
		super();
	}

	public TripVO(int contentId, int sidoCode, int gugunCode, int contentTypeId, String title, String addr,
			String addr2, double latitude, double longitude, String firstImage) {
		super();
		this.contentId = contentId;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr = addr;
		this.addr2 = addr2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.firstImage = firstImage;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
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

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	@Override
	public String toString() {
		return "TripVO [contentId=" + contentId + ", sidoCode=" + sidoCode + ", gugunCode=" + gugunCode
				+ ", contentTypeId=" + contentTypeId + ", title=" + title + ", addr=" + addr + ", addr2=" + addr2
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", firstImage=" + firstImage + "]";
	}
	
}
