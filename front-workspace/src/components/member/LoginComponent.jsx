// Style
import { NavLink } from "react-router-dom";
import login from "../../resources/css/Login.module.css";

function LoginComponent(props) {
  const id = props.id;
  const setId = props.setId;
  const password = props.password;
  const setPassword = props.setPassword;

  const doLogin = props.doLogin;

  return (
    <>
      <div className={login.contents}>
        <div className={login.idBox}>
          <label className={login.inputLabel}>아이디 (이메일)</label>
          <input type="text" className={login.inputText} value={id} onChange={(e) => { setId(e.target.value)}} />
        </div>
        <div className={login.passwordBox}>
          <label className={login.inputLabel}>비밀번호</label>
          <input className={login.inputText} value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div className={login.confirmBox}>
          <NavLink className={login.find} to="/find">
            아이디 혹은 비밀번호를 잊으셨나요?
          </NavLink>
          <button className={login.confirm} onClick={doLogin}> 로그인 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
