import { useEffect, useRef } from "react";

// Style
function LoginComponent(props) {
  const registerStyle = props.registerStyle;

  const profile = props.profile;
  const setProfile = props.setProfile;
  const fileDOM = useRef();

  const id = props.id;
  const setId = props.setId;
  const isExistId = props.isExistId;
  const setIdConfirm = props.setIdConfirm;

  const password = props.password;
  const setPassword = props.setPassword;
  const passwordConfirm = props.passwordConfirm;
  const setPasswordConfirm = props.setPasswordConfirm;

  const name = props.name;
  const setName = props.setName;
  const sido = props.sido;
  const setSido = props.setSido;
  const gugun = props.gugun;
  const setGugun = props.setGugun;

  const sidoList = props.sidoList;
  const gugunList = props.gugunList;

  const doRegister = props.doRegister;

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
                    <option value={sido.sidoCode} key={index}>{sido.sidoName}</option>
                    )) : null
              }
            </select>
            <select className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={gugun} onChange={(e) => { setGugun(e.target.value); }}>
              <option value="0" disabled>구 · 군</option>
              {
                gugunList[sido] && sido?
                  gugunList[sido].map((gugun, index) => (
                    <option value={gugun.gugunCode} key={index}>{gugun.gugunName}</option>
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
