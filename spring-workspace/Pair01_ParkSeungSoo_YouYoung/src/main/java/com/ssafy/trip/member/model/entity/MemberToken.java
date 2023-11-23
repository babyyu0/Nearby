package com.ssafy.trip.member.model.entity;

import com.ssafy.trip.global.util.data.RegexPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberToken {

    @NotBlank(message = "리프레시 토큰이 존재하지 않습니다.")
    @Indexed
    String refreshToken;
    @NotBlank(message = "아이디가 존재하지 않습니다.")
    @Pattern(regexp = RegexPattern.EMAIL, message = "아이디가 올바르지 않습니다.")
    @Comment("회원 아이디")
    @Id
    private Member member;

    @Builder
    public MemberToken(Member member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }
}
