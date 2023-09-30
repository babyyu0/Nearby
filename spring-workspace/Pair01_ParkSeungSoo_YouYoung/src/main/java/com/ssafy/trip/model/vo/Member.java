package com.ssafy.trip.model.vo;

import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class Member {

	public Member(String memberId, String password, String name, int sidoCode, int gugunCode, String profileImg) throws MemberInvalidException {
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setSidoCode(sidoCode);
		setGugunCode(gugunCode);
		setProfileImg(profileImg);
	}
	public Member(String memberId, String password, String name, String email, int sidoCode, int gugunCode) throws MemberInvalidException {
		super();
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setEmail(email);
		setSidoCode(sidoCode);
		setGugunCode(gugunCode);
		setProfileImg("");
	}
	private long id;
	private String memberId;
	private String password;
	private String name;
	private String email;
	private int sidoCode;
	private int gugunCode;
	private String profileImg;
	
	private Sido sido;
	private Gugun gugun;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) throws MemberInvalidException {
		if(memberId == null || memberId.trim().equals("") || !memberId.trim().matches(RegexData.regex.get("email"))) {
			log.error("MemberCreateCommand: 아이디 입력 실패 " + memberId);
			throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
		}
		this.memberId = memberId;
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
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", sidoCode="
				+ sidoCode + ", gugunCode=" + gugunCode + "]";
	}
}
