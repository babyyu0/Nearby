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

  // useEffect(() => {
  //   document.addEventListener('change', (e) => {
  //     if (e.target !== fileDOM.current) return;
  //     const imageSrc = URL.createObjectURL(fileDOM.current.files[0]);
  //     setProfile(imageSrc);
  //   });

  //   return URL.revokeObjectURL(fileDOM.current.files[0]);
  // }, []);

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

  return (
    <>
      <div className={registerStyle.registerBox}>
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
          />
          <input type="password" id="password" className={`${registerStyle.infoInput} ${registerStyle.passwordInput}`} value={password} onChange={(e) => { setPassword(e.target.value) }} available={`${passwordAvailable}`} />
          <span className={registerStyle.infoWarning} available={`${passwordAvailable}`}>올바른 비밀번호 형식이 아닙니다.</span>
        </div>
      {/* 
      <div className={registerStyle.registerContainer}>
        <div className={registerStyle.box}>
          <label className={`${registerStyle.inputLabel} ${registerStyle.inputImgLabel}`} htmlFor="profile">
            <img src={profile} alt="profile"/>
          </label>
          <div>
            <input type="file" id="profile" accept="image/*" style={{ display: 'none' }} ref={fileDOM} onChange={(e) => { setProfile(e.target.value); }} />
          </div>
        </div>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel} htmlFor={id}>
            아이디 (이메일)
          </label>
          <div>
            <input type="text" className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={id} onChange={(e) => { setIdConfirm(false); setId(e.target.value); }} />
            <button className={registerStyle.confirm} onClick={isExistId}>중복확인</button>
          </div>
        </div>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel} htmlFor={password}>비밀번호</label>
          <div>
            <input type="password" className={registerStyle.inputText} value={password} onChange={(e) => { setPassword(e.target.value); }} />
          </div>
        </div>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel} htmlFor={passwordConfirm}>비밀번호 확인</label>
          <div>
            <input type="password" className={registerStyle.inputText} value={passwordConfirm} onChange={(e) => { setPasswordConfirm(e.target.value); }} />
          </div>
        </div>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel} htmlFor={name}>이름</label>
          <div>
            <input type="text" className={registerStyle.inputText} value={name} onChange={(e) => { setName(e.target.value); }} />
          </div>
        </div>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel} htmlFor={sido}>지역</label>
          <div>
            <select className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={sido} onChange={(e) => { setGugun(0);  setSido(e.target.value); }}>
              <option value="0" disabled>시 · 도</option>
              {
                sidoList?
                  sidoList.map((sido, index) => (
                    <option value={sido.code} key={index}>{sido.name}</option>
                    )) : null
              }
            </select>
            <select className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={gugun} onChange={(e) => { setGugun(e.target.value); }}>
              <option value="0" disabled>구 · 군</option>
              {
                gugunList[sido] && sido?
                  gugunList[sido].map((gugun, index) => (
                    <option value={gugun.code} key={index}>{gugun.name}</option>
                    )) : null
              }
            </select>
          </div>
        </div>
         */}
        <div className={`${registerStyle.box} ${registerStyle.confirmBox}`}>
          <button className={registerStyle.confirm} onClick={doRegister}> 회원가입 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
