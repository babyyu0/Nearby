package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.MyException;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class Member {

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
	@JoinColumns({
			@JoinColumn(name = "gugun_code", nullable = false, columnDefinition = "BIGINT COMMENT '구군 코드'"),
			@JoinColumn(name = "sido_code", nullable = false, columnDefinition = "BIGINT COMMENT '시도 코드'"),
	})
	private Gugun gugun;

	@Column(name = "profile_img", columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
	@Comment("프로필 이미지")
	private String profileImg;

	public static MemberBuilder builder() {
		return Member.innerBuilder();
	}
	public MemberBuilder id(long id) throws MemberInvalidException {
		if(id < 0) {
			log.error("Member: 고유번호 입력 실패 " + id);
			throw new MemberInvalidException();
		}
		return innerBuilder().id(id);
	}
	public MemberBuilder memberId(String memberId) throws MemberInvalidException {
		if(memberId == null || memberId.trim().equals("") || !memberId.trim().matches(RegexData.regex.get("email"))) {
			log.error("Member: 아이디 입력 실패 " + memberId);
			throw new MemberInvalidException();
		}
		return innerBuilder().memberId(memberId);
	}
	public MemberBuilder password(String password) throws MemberInvalidException {
		return innerBuilder().password(password);
	}

	public MemberBuilder name(String name) throws MemberInvalidException {
		if(name == null || name.trim().equals("") || 20 < name.length()) {
			log.error("Member: 이름 입력 실패 " + name);
			throw new MemberInvalidException();
		}
		return innerBuilder().name(name);
	}

	public MemberBuilder gugun(Gugun gugun) {
		return innerBuilder().gugun(gugun);
	}

	public MemberBuilder profileImg(String profileImg) {
		return innerBuilder().profileImg(profileImg);
	}
}
