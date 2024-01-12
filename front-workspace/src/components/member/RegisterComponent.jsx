// Modules
import { useEffect, useRef } from "react";

function LoginComponent({ registerStyle, profile, setProfile, id, setId, isExistId, setIdConfirm, password, setPassword, passwordConfirm, setPasswordConfirm, name, setName,
  sido, setSido, gugun, setGugun, sidoList, gugunList, doRegister }) {
  const fileDOM = useRef();

  useEffect(() => {
    document.addEventListener('change', (e) => {
      if (e.target !== fileDOM.current) return;
      const imageSrc = URL.createObjectURL(fileDOM.current.files[0]);
      setProfile(imageSrc);
    });

    return URL.revokeObjectURL(fileDOM.current.files[0]);
  }, []);

  return (
    <>
      <div className={registerStyle.contents}>
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
        <div className={`${registerStyle.box} ${registerStyle.confirmBox}`}>
          <button className={registerStyle.confirm} onClick={doRegister}> 회원가입 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
