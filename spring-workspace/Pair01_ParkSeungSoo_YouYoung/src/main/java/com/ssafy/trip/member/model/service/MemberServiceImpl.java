package com.ssafy.trip.member.model.service;

import com.ssafy.trip.area.model.dto.response.GugunGetResponseDto;
import com.ssafy.trip.area.model.dto.response.SidoGetResponseDto;
import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.area.model.entity.Sido;
import com.ssafy.trip.area.model.entity.primary.GugunPk;
import com.ssafy.trip.area.model.repository.GugunRepository;
import com.ssafy.trip.area.model.repository.SidoRepository;
import com.ssafy.trip.global.util.ImageUtil;
import com.ssafy.trip.global.util.TokenProvider;
import com.ssafy.trip.global.util.ValidateUtil;
import com.ssafy.trip.global.util.data.RegexPattern;
import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;
import com.ssafy.trip.member.model.dto.command.*;
import com.ssafy.trip.member.model.dto.response.LoginResponseDto;
import com.ssafy.trip.member.model.dto.response.MemberGetResponseDto;
import com.ssafy.trip.member.model.dto.response.ValidIdResponseDto;
import com.ssafy.trip.member.model.entity.Member;
import com.ssafy.trip.member.model.entity.MemberRole;
import com.ssafy.trip.member.model.entity.MemberToken;
import com.ssafy.trip.member.model.repository.MemberRepository;
import com.ssafy.trip.member.model.repository.MemberTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Value("${url.member.profile-img}")
    private static String MEMBER_PROFILE_IMG_URI;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final MemberTokenRepository memberTokenRepository;
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    @Override
    @Transactional(readOnly = true)
    public ValidIdResponseDto isValidId(ValidIdCommandDto validIdCommandDto) throws MyException {
        ValidIdResponseDto validIdResponseDto = ValidIdResponseDto.builder()
                .valid(true)
                .message("사용 가능한 아이디 입니다.")
                .build();

        if (validIdCommandDto.memberId() == null
                || validIdCommandDto.memberId().trim().equals("")
                || !validIdCommandDto.memberId().trim().matches(RegexPattern.EMAIL)) {
            validIdResponseDto = ValidIdResponseDto.builder()
                    .valid(false)
                    .message("아이디 형식이 올바르지 않습니다.").build();
        } else if (memberRepository.findById(validIdCommandDto.memberId()).isPresent()) {
            validIdResponseDto = ValidIdResponseDto.builder()
                    .valid(false)
                    .message("중복된 아이디입니다.")
                    .build();
        }

        return validIdResponseDto;
    }

    @Override
    @Transactional
    public boolean register(RegisterCommandDto registerCommandDto) throws MyException {
        ValidateUtil.clientValidate(registerCommandDto);
        if (memberRepository.findById(registerCommandDto.memberId()).isPresent()) {
            throw new MyException(ErrorMessage.ID_DUPLICATED);
        }

        String profileName = null;
        if (registerCommandDto.profile() != null) {  // 프로필 사진 존재할 경우
            profileName = ImageUtil.saveIamge(MEMBER_PROFILE_IMG_URI, registerCommandDto.profile());
        }

        Sido sido = sidoRepository.findById(registerCommandDto.sidoCode())
                .orElseThrow(() -> new MyException(ErrorMessage.SIDO_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(GugunPk.builder()
                        .gugunCode(registerCommandDto.gugunCode()).sido(sido).build())
                .orElseThrow(() -> new MyException(ErrorMessage.GUGUN_NOT_FOUND));

        Member member = Member.builder()
                .id(registerCommandDto.memberId())
                .password(passwordEncoder.encode(registerCommandDto.password()))
                .name(registerCommandDto.name())
                // .sido(sido)
                .gugun(gugun)
                .profileImg(profileName)
                .build();

        ValidateUtil.serverValidate(member);  // 유효성 검사
        memberRepository.save(member);

        return true;
    }

    @Override
    @Transactional
    public LoginResponseDto login(LoginCommandDto loginCommandDto) throws MyException {
        ValidateUtil.clientValidate(loginCommandDto);  // 유효성 검사
        Member member = memberRepository.findById(loginCommandDto.id()).orElseThrow(() -> {
            log.debug("login : 회원 아이디 존재 X");
            return new MyException(ErrorMessage.MEMBER_NOT_FOUND);
        });

        // 비밀번호 틀렸을 시
        if (!passwordEncoder.matches(loginCommandDto.password(), member.getPassword())) {
            log.debug("login : 비밀번호 틀림");
            throw new MyException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        // Access Token, Refresh Token 생성
        String accessToken = tokenProvider.generateAccessToken(member.getId(), MemberRole.USER.getRole());
        String refreshToken = tokenProvider.generateRefreshToken(member.getId(), MemberRole.USER.getRole());

        MemberToken memberToken = MemberToken.builder()
                .id(member.getId())
                .refreshToken(refreshToken)
                .build();

        ValidateUtil.serverValidate(memberToken);
        memberTokenRepository.save(memberToken);  // 액세스 토큰 저장

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .profile(ImageUtil.toByteArray(MEMBER_PROFILE_IMG_URI, member.getProfileImg()))
                .build();
        ValidateUtil.serverValidate(loginResponseDto);  // 유효성 검사

        return loginResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberGetResponseDto getMember(MemberGetCommandDto memberGetCommandDto) {
        ValidateUtil.clientValidate(memberGetCommandDto);

        if(!tokenProvider.getMemberId(memberGetCommandDto.accessToken())
                .equals(memberGetCommandDto.id())) {  // 토큰 아이디와 요청 아이디 비교
            log.debug("getMember : 잘못된 토큰");
            throw new MyException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        Member member = memberRepository.findById(memberGetCommandDto.id()).orElseThrow(() -> {
            log.debug("getMember : 회원 아이디 존재 X");
            return new MyException(ErrorMessage.MEMBER_NOT_FOUND);
        });

        // 비밀번호 틀렸을 시
        if (!passwordEncoder.matches(memberGetCommandDto.password(), member.getPassword())) {
            log.debug("getMember : 비밀번호 틀림");
            throw new MyException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        SidoGetResponseDto sidoGetResponseDto = SidoGetResponseDto.from(member.getGugun().getSido());
        ValidateUtil.serverValidate(sidoGetResponseDto);

        GugunGetResponseDto gugunGetResponseDto = GugunGetResponseDto.from(member.getGugun());
        ValidateUtil.serverValidate(gugunGetResponseDto);

        MemberGetResponseDto memberGetResponseDto = MemberGetResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .sidoGetResponseDto(sidoGetResponseDto)
                .gugunGetResponseDto(gugunGetResponseDto)
                .profile(ImageUtil.toByteArray(MEMBER_PROFILE_IMG_URI, member.getProfileImg()))
                .build();

        ValidateUtil.serverValidate(memberGetResponseDto);
        return memberGetResponseDto;
    }

    @Override
    @Transactional
    public boolean logout(LogoutCommandDto logoutCommandDto) throws MyException {
        return true;
    }
}
