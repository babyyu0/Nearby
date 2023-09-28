package com.ssafy.trip.model.vo;

public class Member {

	public Member(String id, String password, String name, String email, int sidoCode, int gugunCode) {
		super();
		setId(id);
		setPassword(password);
		setName(name);
		setEmail(email);
		setSidoCode(sidoCode);
		setGugunCode(gugunCode);
		setProfileImg("");
	}
	private String id;
	private String password;
	private String name;
	private String email;
	private int sidoCode;
	private int gugunCode;
	private String profileImg;
	
	private Sido sido;
	private Gugun gugun;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id != null && !id.trim().equals("")) this.id = id;
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
		if(name != null && !name.trim().equals("")) this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email != null && !email.trim().equals("")) this.email = email;
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

	public Sido getSido() {
		return sido;
	}

	public void setSido(Sido sido) {
		this.sido = sido;
	}

	public Gugun getGugun() {
		return gugun;
	}

	public void setGugun(Gugun gugun) {
		this.gugun = gugun;
	}
	

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", sidoCode="
				+ sidoCode + ", gugunCode=" + gugunCode + "]";
	}
}
