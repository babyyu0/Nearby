package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@Builder
@Slf4j
@ToString
public class Member {
	public Member(long id, String memberId, String password, String name, Sido sido, Gugun gugun, String profileImg) throws MemberInvalidException {
		setId(id);
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setSido(sido);
		setGugun(gugun);
		setProfileImg(profileImg);
	}
	public Member(String memberId, String password, String name, Sido sido, Gugun gugun, String profileImg) throws MemberInvalidException {
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setSido(sido);
		setGugun(gugun);
		setProfileImg(profileImg);
	}

	public Member(String memberId, String password, String name, Sido sido, Gugun gugun) throws MemberInvalidException {
		super();
		setMemberId(memberId);
		setPassword(password);
		setName(name);
		setSido(sido);
		setGugun(gugun);
		setProfileImg("tmp.jpg");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("고유번호")
	private long id;

	@Column(name = "member_id", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("회원 아이디")
	private String memberId;

	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
	@Comment("비밀번호")
	private String password;

	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("이름")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sido_code", nullable = false)
	@Comment("시도 코드")
	private Sido sido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gugun_code", referencedColumnName = "gugun_code", nullable = false)
	@Comment("구군 코드")
	private Gugun gugun;

	@Column(name = "profile_img", columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
	@Comment("프로필 이미지")
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
			throw new MemberInvalidException();
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
