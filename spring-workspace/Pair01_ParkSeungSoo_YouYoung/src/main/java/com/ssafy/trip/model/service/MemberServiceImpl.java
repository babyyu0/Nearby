package com.ssafy.trip.model.service;

import com.ssafy.trip.model.data.GugunPk;
import com.ssafy.trip.model.dto.command.RegisterCommandDto;
import com.ssafy.trip.model.dto.command.ValidIdCommandDto;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    @Override
    @Transactional(readOnly = true)
    public ValidIdResponseDto isValidId(ValidIdCommandDto validIdCommandDto) throws MyException {
        ValidIdResponseDto validIdResponseDto = ValidIdResponseDto.builder().valid(true).message("사용 가능한 아이디 입니다.").build();
        if (validIdCommandDto.memberId() == null
                || validIdCommandDto.memberId().trim().equals("")
                || !validIdCommandDto.memberId().trim().matches(RegexPattern.EMAIL)) {
            validIdResponseDto = ValidIdResponseDto.builder().valid(false).message("아이디 형식이 올바르지 않습니다.").build();
        } else if (memberRepository.findByMemberId(validIdCommandDto.memberId()).isPresent()) {
            validIdResponseDto = ValidIdResponseDto.builder().valid(false).message("중복된 아이디입니다.").build();
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
            ImageUtil.saveMemberProfile(registerCommandDto.profile());
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

        ValidateUtil.serverValidate(member);
        memberRepository.save(member);

        return true;
    }

/*
    public MemberLoginResponse login(MemberLoginCommand memberLoginCommand) throws MyException {
        Member member = memberRepository.findByMemberId(memberLoginCommand.memberId()).orElseThrow(() -> {
            log.error("MemberService: 회원 아이디 찾기 실패 (Member) " + memberLoginCommand.memberId());
            return new MemberNotFoundException();
        });

        if (!myPasswordEncoder.matches(memberLoginCommand.getPassword(), member.getPassword())) {
            log.error("MemberService: 비밀번호 비교 실패 " + memberLoginCommand.getPassword());
            throw new MemberNotFoundException();
        }

        return MemberLoginResponse.builder()
                .name(member.getName())
                .profile(imageService.imgToByteArray(MEMBER_PROFILE_IMG_URI, member.getProfileImg()))
                .build();
    }

}
	public String login(MemberVO member) throws MyException {
		try {
			MemberSecVO loggedMemberSec = memberSecDAO.login(member);
			if(loggedMemberSec == null) {
				return null;
			}
			
			String salt = loggedMemberSec.getSalt();
			// 해쉬값 비교
			String hashPassword = OpenCrypt.getSHA256(member.getPassword(), salt);
			member.setPassword(hashPassword);
			
			String loggedMemberName = memberRepository.login(member);
			// 아이디 혹은 비밀번호가 일치하지 않을 경우
			if(loggedMemberName == null) {
				return null;
			}
			
			if(member.getPassword().equals(hashPassword)) {
				return loggedMemberName;
			}
		} catch (Exception e) {
			throw new RuntimeException("해쉬값 변환 실패.");
		}

		return null;
	}

	public MemberVO selectOne(MemberVO member) {
		return memberRepository.selectOne(member);
	}

	public void regist(MemberVO member) throws MyException {

		// 아이디 중복 검사
		MemberVO selMemberVO = selectOne(member);
		if(selMemberVO != null) throw new RuntimeException("회원 등록 실패.");
		
		// 비밀번호 유효성 검사
		String pwReg = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*_-])[A-Za-z0-9~!@#$%^&*_-]{8,16}$";
		if(!Pattern.matches(pwReg, member.getPassword())) {
			throw new RuntimeException("회원 등록 실패.");
		}
		
		if(member.getName().equals("")) {
			throw new RuntimeException("회원 등록 실패.");
		}
		
		String emailReg = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
		if(!Pattern.matches(emailReg, member.getEmail())) {
			throw new RuntimeException("회원 등록 실패.");
		}
		
		try {
			//회원가입 시 입력한 비밀번호를 암호화 하기 위해 렌덤해시값인 salt를 UUID.randomUUID()을 통해서 생성합니다.
			String salt = UUID.randomUUID().toString();
			System.out.println("salt : " + salt);
			// 사용자가 회원 가입 시 입력한 password와 salt값을 바탕으로 랜덤 해쉬값을 생성합니다.
			String hashPassword = OpenCrypt.getSHA256(member.getPassword(), salt);
			member.setPassword(hashPassword);
			byte[]  secKey = OpenCrypt.generateKey("AES", 128);
			
			memberRepository.regist(member);
			
			// 암호화한 아이디를 저장합니다.
			memberSecDAO.registSec(new MemberSecVO(member.getId(), salt, new String(secKey)));
		} catch (Exception e) {
			throw new RuntimeException("회원 등록 실패.");
		}
		
	}
	
	public void setProfileImg(String id, File profileFile) {
		memberRepository.insertProfileImg(id, "http://localhost:9999/static/img/userProfile/" + profileFile.getName());
	}

 */

}
