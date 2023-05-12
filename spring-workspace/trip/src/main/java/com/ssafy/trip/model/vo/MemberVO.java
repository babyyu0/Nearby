package com.ssafy.trip.model.vo;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String email;
	private String sidoCode;
	private String gugunCode;
	
	private SidoVO sidoVO;
	private SidoVO gugunVO;

	public MemberVO(String id, String password, String name, String email, String sidoCode, String gugunCode) {
		super();
		setId(id);
		setPassword(password);
		setName(name);
		setEmail(email);
		setSidoCode(sidoCode);
		setGugunCode(gugunCode);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", sidoCode="
				+ sidoCode + ", gugunCode=" + gugunCode + "]";
	}
}
