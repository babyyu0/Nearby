// Style
import { NavLink } from "react-router-dom";

function LoginComponent(props) {
  const registerStyle = props.registerStyle;

  const profile = props.profile;
  const setProfile = props.setProfile;

  const id = props.id;
  const setId = props.setId;
  const isExistId = props.isExistId;

  const password = props.password;
  const setPassword = props.setPassword;
  const passwordConfirm = props.passwordConfirm;
  const setPasswordConfirm = props.setPasswordConfirm;

  const name = props.name;
  const setName = props.setName;
  const sido = props.sido;
  const setSido = props.setSido;

  const doLogin = props.doLogin;

  return (
    <>
      <div className={registerStyle.contents}>
        <div className={registerStyle.box}>
          <label className={`${registerStyle.inputLabel} ${registerStyle.inputImgLabel}`} htmlFor={profile}>
            <img src="/image/none_profile.png" alt="profile"/>
          </label>
          <div>
            <input type="file" style={{ display: 'none' }} value={profile} onChange={(e) => { setProfile(e.target.value); }} />
          </div>
        </div>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel} htmlFor={id}>
            아이디 (이메일)
          </label>
          <div>
            <input type="text" className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={id} onChange={(e) => { setId(e.target.value); }} />
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
            <select className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={sido} onChange={(e) => { setSido(e.target.value); }}>
            </select>
            <select className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={sido} onChange={(e) => { setSido(e.target.value); }}>
            </select>
          </div>
        </div>
        <div className={`${registerStyle.box} ${registerStyle.confirmBox}`}>
          <button className={registerStyle.confirm} onClick={doLogin}> 로그인 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
