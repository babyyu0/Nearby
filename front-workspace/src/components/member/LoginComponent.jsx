// Modules
import { NavLink } from "react-router-dom";

function LoginComponent({ loginStyle, id, setId, password, setPassword, doLogin }) {

  return (
    <>
      <div className={loginStyle.loginBox}>
        <div className={`${loginStyle.infoDesc} ${loginStyle.loginDesc}`}>Always Nearby You!<br />다시 만나게 되어<br />반가워요 👋</div>
        <div className={loginStyle.infoBox}>
          <label htmlFor="id" className={`${loginStyle.infoLabel} ${loginStyle.idLabel}`}>
            아이디 (이메일)
          </label>
          <input
            type="text"
            id="id"
            className={`${loginStyle.infoInput} ${loginStyle.idInput}`}
            value={id}
            onChange={(e) => {
              setId(e.target.value);
            }}
          />
        </div>
        <div className={loginStyle.infoBox}>
          <label htmlFor="id" className={`${loginStyle.infoLabel} ${loginStyle.passwordLabel}`}>
            비밀번호
          </label>
          <input
            type="password"
            id="password"
            className={`${loginStyle.infoInput} ${loginStyle.passwordInput}`}
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
        </div>
        <div className={loginStyle.confirmBox}>
          <button className={loginStyle.confirmBtn} onClick={doLogin}> 로그인 </button>
          <NavLink className={loginStyle.findBtn} to="/find">
            아이디 혹은 비밀번호를 잊으셨나요?
          </NavLink>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
