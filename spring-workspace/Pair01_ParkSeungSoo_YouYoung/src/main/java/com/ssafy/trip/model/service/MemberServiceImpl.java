package com.ssafy.trip.model.service;

import com.ssafy.trip.util.MyPasswordEncoder;
import com.ssafy.trip.model.dao.MemberSecDao;
import com.ssafy.trip.model.dto.command.ValidIdCommand;
import com.ssafy.trip.model.dto.command.MemberCreateCommand;
import com.ssafy.trip.model.dto.response.ValidIdResponse;
import com.ssafy.trip.model.vo.Member;
import com.ssafy.trip.model.vo.MemberSec;
import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.MemberDao;
import com.ssafy.trip.util.exception.MyException;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final MemberSecDao memberSecDao;

    private final MyPasswordEncoder myPasswordEncoder;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao, MemberSecDao memberSecDao, MyPasswordEncoder myPasswordEncoder) {
        this.memberDao = memberDao;
        this.memberSecDao = memberSecDao;
        this.myPasswordEncoder = myPasswordEncoder;
    }

    @Override
    public ValidIdResponse isValidId(ValidIdCommand validIdCommand) throws MyException {
        try {
            if (validIdCommand.getMemberId() == null
                    || validIdCommand.getMemberId().trim().equals("")
                    || !validIdCommand.getMemberId().trim().matches(RegexData.regex.get("email"))) {
                return new ValidIdResponse(false, "사용할 수 없는 아이디입니다.");
            } else if (memberDao.findByMemberId(validIdCommand.getMemberId()) == null) {
                return new ValidIdResponse(false, "중복된 아이디입니다.");
            } else {
                return new ValidIdResponse(true, "사용 가능한 아이디입니다.");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new MemberInvalidException();
        }
    }

    @Override
    public boolean register(MemberCreateCommand memberCreateCommand) throws MyException {
        if (memberCreateCommand.getMemberId() == null
                || memberCreateCommand.getMemberId().trim().equals("")
                || !memberCreateCommand.getMemberId().trim().matches(RegexData.regex.get("email"))) {
            log.error("MemberService: 아이디 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }
        if (!isValidId(new ValidIdCommand(memberCreateCommand.getMemberId())).isValid()) {  // 아이디 중복 검사
            log.error("MemberService: 아이디 중복");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }
        if (memberCreateCommand.getPassword() == null
                || memberCreateCommand.getPassword().trim().equals("")
                || !memberCreateCommand.getPassword().trim().matches(RegexData.regex.get("password"))) {
            log.error("MemberService: 비밀번호 입력 실패");
            throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
        }
		if(memberCreateCommand.getName() == null
				|| memberCreateCommand.getName().trim().equals("")
				|| 20 < memberCreateCommand.getName().length() ) {
			log.error("MemberService: 이름 입력 실패");
			throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
		}
		if(memberCreateCommand.getSidoCode() <= 0 || memberCreateCommand.getGugunCode() <= 0) {
			log.error("MemberService: 지역 입력 실패");
			throw new MemberInvalidException(HttpStatus.BAD_REQUEST);
		}

		String salt = myPasswordEncoder.getRandomSalt();  // 비밀번호 및 토큰 암호화 로직
		log.info(memberCreateCommand.getMemberId() + " 회원 salt 생성 : " + salt);
        memberCreateCommand.setPassword(myPasswordEncoder.encode(memberCreateCommand.getPassword(), salt));
        byte[] key = myPasswordEncoder.generateKey();

        Member member = new Member(memberCreateCommand.getMemberId(), memberCreateCommand.getPassword(), memberCreateCommand.getName(), memberCreateCommand.getSidoCode(), memberCreateCommand.getGugunCode(), "tmp.jpg");
        MemberSec memberSec = new MemberSec(member.getMemberId(), salt, new String(key));

        memberDao.save(member);
        memberSecDao.save(memberSec);

        return true;
    }

}
/*
	public String login(Member member) throws MyException {
		try {
			MemberSecVO loggedMemberSec = memberSecDAO.login(member);
			if(loggedMemberSec == null) {
				return null;
			}
			
			String salt = loggedMemberSec.getSalt();
			// 해쉬값 비교
			String hashPassword = OpenCrypt.getSHA256(member.getPassword(), salt);
			member.setPassword(hashPassword);
			
			String loggedMemberName = memberDao.login(member);
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

	public Member selectOne(Member member) {
		return memberDao.selectOne(member);
	}

	public void regist(Member member) throws MyException {

		// 아이디 중복 검사
		Member selMember = selectOne(member);
		if(selMember != null) throw new RuntimeException("회원 등록 실패.");
		
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
			
			memberDao.regist(member);
			
			// 암호화한 아이디를 저장합니다.
			memberSecDAO.registSec(new MemberSecVO(member.getId(), salt, new String(secKey)));
		} catch (Exception e) {
			throw new RuntimeException("회원 등록 실패.");
		}
		
	}
	
	public void setProfileImg(String id, File profileFile) {
		memberDao.insertProfileImg(id, "http://localhost:9999/static/img/userProfile/" + profileFile.getName());
	}

}

 */
