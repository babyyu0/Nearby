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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("고유번호")
	private long id;
	
	@Column(name = "member_id", columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
	@Comment("회원 아이디")
	private String memberId;

	@Column(name = "sec_key", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
	@Comment("key")
	private String secKey;

	public static MemberSecBuilder builder() {
		return MemberSec.innerBuilder();
	}

	public MemberSecBuilder id(long id) {
		return innerBuilder().id(id);
	}
	public MemberSecBuilder memberId(String memberId) {
		return innerBuilder().memberId(memberId);
	}
	public MemberSecBuilder secKey(String secKey) {
		return innerBuilder().secKey(secKey);
	}
}
