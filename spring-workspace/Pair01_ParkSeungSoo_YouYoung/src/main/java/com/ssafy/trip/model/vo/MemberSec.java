package com.ssafy.trip.model.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

public class MemberSec {
	private long id;
	private String memberId;
	private String salt;
	private String secKey;
	public MemberSec() {
		super();
	}
	public MemberSec(String memberId, String salt, String secKey) {
		setMemberId(memberId);
		setSalt(salt);
		setSecKey(secKey);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
}
