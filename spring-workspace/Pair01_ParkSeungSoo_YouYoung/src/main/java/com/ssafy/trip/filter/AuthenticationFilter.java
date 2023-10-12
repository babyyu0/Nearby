package com.ssafy.trip.filter;

import com.ssafy.trip.model.repository.MemberSecRepository;
import com.ssafy.trip.provider.MyPasswordEncoder;
import com.ssafy.trip.provider.TokenProvider;
import com.ssafy.trip.util.exception.MyException;
import com.ssafy.trip.util.exception.common.CookieInvalidException;
import com.ssafy.trip.util.exception.common.PasswordEncodeException;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final MyPasswordEncoder myPasswordEncoder;
    private final TokenProvider tokenProvider;

    public AuthenticationFilter(MyPasswordEncoder myPasswordEncoder, TokenProvider tokenProvider) {
        this.myPasswordEncoder = myPasswordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenProvider.resolveToken(request);

            // permitAll일 경우 거치지 X
            if (request.getHeader("Authorization") != null){
                // 아이디 가져오기
                Cookie[] cookies = request.getCookies();
                if (cookies == null) {
                    log.error("doFilterInternal: 쿠키 미존재");
                    errorResponse(response, new CookieInvalidException());  // 쿠키 미존재 exception 던지기
                }

                String memberId = null;
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("memberId")) {
                        memberId = cookie.getValue();
                        break;
                    }
                }
                if (memberId == null) {
                    log.error("doFilterInternal: 쿠키 내 member id 미존재");
                    errorResponse(response, new CookieInvalidException());
                }

                SecretKey key = null;
                try {
                    key = myPasswordEncoder.getKey(memberId);
                } catch (PasswordEncodeException e) {
                    log.error("doFilterInternal: key 미존재");
                    errorResponse(response, new MemberInvalidException());
                }

                try {
                if(tokenProvider.validateToken(token, key)) {
                    Authentication authentication = tokenProvider.getAuthentication(token, key);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                } catch (MyException e) {
                    log.error(e.getMessage());
                    errorResponse(response, e);
                }
            }
        filterChain.doFilter(request, response);

    }

    private void errorResponse(HttpServletResponse response, MyException e) throws IOException {
        response.setStatus(e.getStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        String errorMessage = e.getMessage();
        byte[] errorMessageBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(errorMessageBytes);
    }
}
