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
public class MemberSec {
	@Id
	@Column(name = "member_id", columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("회원 아이디")
	private String memberId;

	@Column(name = "salt", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
	@Comment("랜덤 salt")
	private String salt;

	@Column(name = "sec_key", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
	@Comment("key")
	private String secKey;

	public static MemberSecBuilder builder() {
		return MemberSec.innerBuilder();
	}

	public MemberSecBuilder memberId(String memberId) {
		return innerBuilder().memberId(memberId);
	}
	public MemberSecBuilder salt(String salt) {
		return innerBuilder().salt(salt);
	}
	public MemberSecBuilder secKey(String secKey) {
		return innerBuilder().secKey(secKey);
	}
}