package com.ssafy.trip.model.entity;

import com.ssafy.trip.model.entity.Gugun;
import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.http.HttpStatus;

@Entity
@NoArgsConstructor
@Slf4j
public class Member {
	@Builder
	public Member(String memberId, String password, String name, Sido sido, Gugun gugun, String profileImg) throws MemberInvalidException {
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setSido(sido);
		setGugun(gugun);
		setProfileImg(profileImg);
	}
	@Builder
	public Member(String memberId, String password, String name, String email, Sido sido, Gugun gugun) throws MemberInvalidException {
		super();
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setEmail(email);
		setSido(sido);
		setGugun(gugun);
		setProfileImg("");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("고유번호")
	private long id;
	private String memberId;
	private String password;
	private String name;
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sido_id", nullable = false)
	private Sido sido;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gugun_id", nullable = false)
	private Gugun gugun;
	private String profileImg;

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
}
