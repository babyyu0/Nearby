package com.ssafy.trip.model.service;

import com.ssafy.trip.model.data.GugunPk;
import com.ssafy.trip.model.dto.command.LoginCommandDto;
import com.ssafy.trip.model.dto.command.RegisterCommandDto;
import com.ssafy.trip.model.dto.command.ValidIdCommandDto;
import com.ssafy.trip.model.dto.response.LoginResponseDto;
import com.ssafy.trip.model.dto.response.ValidIdResponseDto;
import com.ssafy.trip.model.entity.Gugun;
import com.ssafy.trip.model.entity.Member;
import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.model.repository.GugunRepository;
import com.ssafy.trip.model.repository.MemberRepository;
import com.ssafy.trip.model.repository.SidoRepository;
import com.ssafy.trip.util.ImageUtil;
import com.ssafy.trip.util.ValidateUtil;
import com.ssafy.trip.util.data.RegexPattern;
import com.ssafy.trip.util.exception.ErrorMessage;
import com.ssafy.trip.util.exception.MyException;
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

    @Value("${member.profile.img.url}")
    private static String MEMBER_PROFILE_IMG_URI;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
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
        } else if (memberRepository.findByMemberId(validIdCommandDto.memberId()).isPresent()) {
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
        if (memberRepository.findByMemberId(registerCommandDto.memberId()).isPresent()) {
            throw new MyException(ErrorMessage.ID_DUPLICATED);
        }

        String profileName = null;
        if (!registerCommandDto.profile().isEmpty()) {  // 프로필 사진 존재할 경우
            profileName = ImageUtil.saveIamge(MEMBER_PROFILE_IMG_URI, registerCommandDto.profile());
        }

        Sido sido = sidoRepository.findById(registerCommandDto.sidoCode())
                .orElseThrow(() -> new MyException(ErrorMessage.SIDO_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(GugunPk.builder()
                        .gugunCode(registerCommandDto.gugunCode()).sido(sido).build())
                .orElseThrow(() -> new MyException(ErrorMessage.GUGUN_NOT_FOUND));

        Member member = Member.builder()
                .memberId(registerCommandDto.memberId())
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

    public LoginResponseDto login(LoginCommandDto loginCommandDto)
            throws MyException {
        ValidateUtil.clientValidate(loginCommandDto);  // 유효성 검사
        Member member = memberRepository.findByMemberId(loginCommandDto.id())
                .orElseThrow(() -> new MyException(ErrorMessage.MEMBER_NOT_FOUND));

        // 비밀번호 틀렸을 시
        if (!passwordEncoder.matches(loginCommandDto.password(), member.getPassword())) {
            throw new MyException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        LoginResponseDto loginResponseDto = LoginResponseDto.from(member,
                ImageUtil.toByteArray(MEMBER_PROFILE_IMG_URI, member.getProfileImg()));
        ValidateUtil.serverValidate(loginResponseDto);  // 유효성 검사

        return loginResponseDto;
    }
}
