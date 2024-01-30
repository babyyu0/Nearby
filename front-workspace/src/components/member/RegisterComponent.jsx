// Modules
import { useEffect, useState, useRef } from "react";
import { Tooltip } from 'react-tooltip';
import { GoQuestion } from "react-icons/go";

// Styles
import 'react-tooltip/dist/react-tooltip.css';

function LoginComponent({ registerStyle, profile, setProfile, id, setId, isExistId, setIdConfirm, password, setPassword, passwordConfirm, setPasswordConfirm, name, setName,
  sido, setSido, gugun, setGugun, sidoList, gugunList, doRegister }) {
  const fileDOM = useRef();
  const [idAvailable, isIdAvailable] = useState(true);
  const [passwordAvailable, isPasswordAvailable] = useState(true);
  const [passwordConfirmAvailable, isPasswordConfirmAvailable] = useState(true);

  // useEffect(() => {
  //   document.addEventListener('change', (e) => {
  //     if (e.target !== fileDOM.current) return;
  //     const imageSrc = URL.createObjectURL(fileDOM.current.files[0]);
  //     setProfile(imageSrc);
  //   });

  //   return URL.revokeObjectURL(fileDOM.current.files[0]);
  // }, []);

  const refreshProfile = (e) => {
    const {files} = e.target;
    const uploadFile = files[0];
    const reader = new FileReader();
    reader.readAsDataURL(uploadFile);
    reader.onloadend = () => {
      setProfile(reader.result);
    }
  }

  useEffect(() => {
    if (id.trim() && !new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,20}$').test(id)) {
      isIdAvailable(false);
    } else {
      isIdAvailable(true);
    }
  }, [id]);

  useEffect(() => {
    if (password.trim() && !new RegExp('^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,20}$').test(password)) {
      isPasswordAvailable(false);
    } else {
      isPasswordAvailable(true);
    }
  }, [password]);

  useEffect(() => {
    if (passwordConfirm && password !== passwordConfirm) {
      isPasswordConfirmAvailable(false);
    } else {
      isPasswordConfirmAvailable(true);
    }
  }, [passwordConfirm]);

  useEffect(() => {
    if (name.includes(" ")) {
      setName(name.replaceAll(" ", ""));
    }
  }, [name]);

  return (
    <>
      <div className={registerStyle.registerBox}>
        <div className={registerStyle.infoBox}>
          <label htmlFor="profile">{ /* className={`${registerStyle.infoLabel} ${registerStyle.profileLabel}`} */}
            <span className={`${registerStyle.infoLabel} ${registerStyle.profileLabel}`}>프로필</span>
            <img src={profile} alt="프로필"  className={`${registerStyle.infoInput} ${registerStyle.profileInput}`} />
          </label>
          <input type="file" id="profile" accept="image/*" style={{ display: 'none' }} ref={fileDOM} onChange={(e) => { refreshProfile(e); }} />
        </div>
        <div className={registerStyle.infoBox}>
          <label htmlFor="id" className={`${registerStyle.infoLabel} ${registerStyle.idLabel}`}>아이디 (이메일)</label>
          <div className={registerStyle.idBox}>
            <input type="text" id="id" className={`${registerStyle.infoInput} ${registerStyle.idInput}`} value={id} onChange={(e) => { setId(e.target.value) }} available={`${idAvailable}`} /> 
            <button className={registerStyle.idExist} onClick={isExistId}>중복 확인</button>
          </div>
          <span className={registerStyle.infoWarning} available={`${idAvailable}`}>올바른 아이디 형식이 아닙니다.</span>
        </div>
        <div className={registerStyle.infoBox}>
          <label htmlFor="password" className={`${registerStyle.infoLabel} ${registerStyle.passwordLabel}`}>비밀번호</label>
          <GoQuestion className={registerStyle.infoHelp} data-tooltip-content='비밀번호는 하나 이상의 대문자, 소문자, 특수문자를 포함한 6~20자리의 문자입니다.' data-tooltip-id='passwordTooltip' />
          <Tooltip
            id='passwordTooltip'
            place='top-start'
            opacity='0.7'
          />
          <input type="password" id="password" className={`${registerStyle.infoInput} ${registerStyle.passwordInput}`} value={password} onChange={(e) => { setPassword(e.target.value) }} available={`${passwordAvailable}`} />
          <span className={registerStyle.infoWarning} available={`${passwordAvailable}`}>올바른 비밀번호 형식이 아닙니다.</span>
        </div>
        <div className={registerStyle.infoBox}>
          <label htmlFor="passwordConfirm" className={`${registerStyle.infoLabel} ${registerStyle.passwordConfirmLabel}`}>비밀번호 확인</label>
          <input type="password" id="passwordConfirm" className={`${registerStyle.infoInput} ${registerStyle.passwordConfirmInput}`} value={passwordConfirm} onChange={(e) => { setPasswordConfirm(e.target.value) }} available={`${passwordConfirmAvailable}`} />
          <span className={registerStyle.infoWarning} available={`${passwordConfirmAvailable}`}>비밀번호가 일치하지 않습니다.</span>
        </div>
        <div className={registerStyle.infoBox}>
          <label htmlFor="name" className={`${registerStyle.infoLabel} ${registerStyle.nameLabel}`}>이름</label>
          <input type="text" id="name" className={`${registerStyle.infoInput} ${registerStyle.nameInput}`} value={name} onChange={(e) => { setName(e.target.value) }} /> 
          </div>
        <div className={`${registerStyle.infoBox} ${registerStyle.areaBox}`}>
          <div className={registerStyle.sidoBox}>
            <label htmlFor="sido" className={`${registerStyle.infoLabel} ${registerStyle.sidoLabel}`}>행정구역 (시 · 도)</label>
            <select className={`${registerStyle.infoInput} ${registerStyle.sidoInput}`} value={sido} onChange={(e) => { setGugun(0); setSido(e.target.value); }}>
              {
                sidoList?
                  sidoList.map((sido, index) => (
                    <option value={sido.code} key={index}>{sido.name}</option>
                    )) : <option value="0" disabled>분류되지 않음</option>
              }
            </select>
          </div>
          <div className={registerStyle.gugunBox}>
            <label htmlFor="gugun" className={`${registerStyle.infoLabel} ${registerStyle.gugunLabel}`}>하위 행정구역 (시 · 군 · 구)</label>
            <select className={`${registerStyle.infoInput} ${registerStyle.gugunInput}`} value={gugun} onChange={(e) => { setGugun(e.target.value); }}>
            {
                gugunList && gugunList[sido] && sido?
                  gugunList[sido].map((gugun, index) => (
                    <option value={gugun.code} key={index}>{gugun.name}</option>
                    )) : <option value="0" disabled>분류되지 않음</option>
              }
            </select>
          </div>
        </div>
        <div className={registerStyle.confirmBox}>
          <button className={registerStyle.confirmBtn} onClick={doRegister}> 회원가입 완료 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
