package com.ssafy.trip.util;

import com.ssafy.trip.util.exception.ErrorMessage;
import com.ssafy.trip.util.exception.MyException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider implements InitializingBean {
    @Value("${token.validity.access}")
    private long accessValidity;
    @Value("${token.validity.refresh}")
    private long refreshValidity;
    @Value("${token.secret}")
    private String secret;
    private SecretKey key;

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 토큰 생성
    public String generateToken(String memberId, String role, long validity) {
        Claims claims = Jwts.claims().setSubject(memberId);  // 사용하는 클레임 세트
        claims.put("role", role);  // 임의 역할 부여

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + validity))
                .signWith(key, SignatureAlgorithm.HS512)  // MemberSec에 저장된 Key 값
                .compact();
    }

    // 토큰 생성
    public String generateAccessToken(String memberId, String role) {
        return generateToken(memberId, role, accessValidity);
    }

    // 토큰 생성
    public String generateRefreshToken(String memberId, String role) {
        return generateToken(memberId, role, refreshValidity);
    }

    // Authorization Header를 통해 인증
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token) throws MyException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.debug("validateToken : {}", "잘못된 JWT 서명");
            throw new MyException(ErrorMessage.TOKEN_NOT_FOUND);
        } catch (ExpiredJwtException e) {  // Access 토큰이 만료 되었을 때
            log.debug("validateToken : {}", "JWT 토큰 만료");
            throw new MyException(ErrorMessage.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.debug("validateToken : {}", "지원되지 않는 JWT 토큰");
            throw new MyException(ErrorMessage.TOKEN_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            log.debug("validateToken : {}", "JWT 토큰 잘못됨");
            throw new MyException(ErrorMessage.TOKEN_NOT_FOUND);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new MyException(ErrorMessage.TOKEN_NOT_FOUND);
        }
    }

    // 토큰으로부터 받은 정보를 기반으로 Authentication 객체 반환
    public Authentication getAuthentication(String token) {
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
