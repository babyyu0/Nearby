package com.ssafy.trip.model.vo;

public class MemberSecVO {
	private String id;
	private String salt;
	private String secKey;
	public MemberSecVO() {
		super();
	}
	public MemberSecVO(String id, String salt, String secKey) {
		setId(id);
		setSalt(salt);
		setSecKey(secKey);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getSecKey() {
		return secKey;
	}
	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}
	@Override
	public String toString() {
		return "MemberSecVO [id=" + id + ", salt=" + salt + ", secKey=" + secKey + "]";
	}
}
