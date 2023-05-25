package com.ssafy.trip.model.vo;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String email;
	private int sidoCode;
	private int gugunCode;
	
	private SidoVO sidoVO;
	private GugunVO gugunVO;

	public MemberVO(String id, String password, String name, String email, int sidoCode, int gugunCode) {
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
		if(id != null || !id.trim().equals("")) this.id = id;
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
		if(name != null || !name.trim().equals("")) this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email != null || !email.trim().equals("")) this.email = email;
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

	public SidoVO getSidoVO() {
		return sidoVO;
	}

	public void setSidoVO(SidoVO sidoVO) {
		this.sidoVO = sidoVO;
	}

	public GugunVO getGugunVO() {
		return gugunVO;
	}

	public void setGugunVO(GugunVO gugunVO) {
		this.gugunVO = gugunVO;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", sidoCode="
				+ sidoCode + ", gugunCode=" + gugunCode + "]";
	}
}
