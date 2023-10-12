package com.ssafy.trip.provider;

import com.ssafy.trip.util.exception.MyException;
import com.ssafy.trip.util.exception.common.TokenInvalidException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
@Slf4j
public class TokenProvider {

    private final long accessValidity;  // token 유효 기간
    private final long refreshValidity;  // token 유효 기간

    public TokenProvider() {
        this.accessValidity = 2 * 60 * 60;  // 2시간
        this.refreshValidity = 14 * 24 * 60 * 60;  // 2주일
    }

    // 토큰 생성
    public String generateToken(String memberId, String role, SecretKey key, long validity) throws NoSuchAlgorithmException {
        Claims claims = Jwts.claims().setSubject(memberId);  // 사용하는 클레임 세트
        claims.put("role", role);  // 임의 역할 부여

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + validity))
                .signWith(key, SignatureAlgorithm.HS512)  // MemberSec에 저장된 Key 값
                .compact();
    }

    // 토큰 생성
    public String generateAccessToken(String memberId, String role, SecretKey key) throws NoSuchAlgorithmException {
        return generateToken(memberId, role, key, accessValidity);
    }
    // 토큰 생성
    public String generateRefreshToken(String memberId, String role, SecretKey key) throws NoSuchAlgorithmException {
        return generateToken(memberId, role, key, refreshValidity);
    }

    // Authorization Header를 통해 인증
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token, SecretKey key) throws MyException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("TokenProvider: 잘못된 JWT 서명");
            throw new TokenInvalidException();
        } catch (ExpiredJwtException e) {  // Access 토큰이 만료 되었을 때
            log.error("TokenProvider: JWT 토큰 만료");
            throw new TokenInvalidException();
        } catch (UnsupportedJwtException e) {
            log.error("TokenProvider: 지원되지 않는 JWT 토큰");
            throw new TokenInvalidException();
        } catch (IllegalArgumentException e) {
            log.error("TokenProvider: JWT 토큰 잘못됨");
            throw new TokenInvalidException();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TokenInvalidException();
        }
    }

    // 토큰으로부터 받은 정보를 기반으로 Authentication 객체 반환
    public Authentication getAuthentication(String token, SecretKey key) {
        return new UsernamePasswordAuthenticationToken(getMemberId(token, key), "", createAuthorityList(getRole(token, key)));
    }

    // 토큰에 담겨있는 회원 ID 획득
    public String getMemberId(String token, SecretKey key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private String getRole(String token, SecretKey key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);

    }
}
