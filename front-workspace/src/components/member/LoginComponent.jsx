// Modules
import { NavLink } from "react-router-dom";

function LoginComponent(props) {
  const loginStyle = props.loginStyle;
  const id = props.id;
  const setId = props.setId;
  const password = props.password;
  const setPassword = props.setPassword;

  const doLogin = props.doLogin;

  return (
    <>
      <div className={loginStyle.contents}>
        <div className={loginStyle.idBox}>
          <label className={loginStyle.inputLabel}>아이디 (이메일)</label>
          <input type="text" className={loginStyle.inputText} value={id} onChange={(e) => { setId(e.target.value)}} placeholder="아이디를 입력해 주세요." />
        </div>
        <div className={loginStyle.passwordBox}>
          <label className={loginStyle.inputLabel}>비밀번호</label>
          <input type='password' className={loginStyle.inputText} value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호를 입력해 주세요." />
        </div>
        <div className={loginStyle.confirmBox}>
          <NavLink className={loginStyle.find} to="/find">
            아이디 혹은 비밀번호를 잊으셨나요?
          </NavLink>
          <button className={loginStyle.confirm} onClick={doLogin}> 로그인 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
