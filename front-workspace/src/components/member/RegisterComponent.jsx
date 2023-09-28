// Style
import { NavLink } from "react-router-dom";

function LoginComponent(props) {
  const registerStyle = props.registerStyle;
  const id = props.id;
  const setId = props.setId;
  const isExistId = props.isExistId;

  const doLogin = props.doLogin;

  return (
    <>
      <div className={registerStyle.contents}>
        <div className={registerStyle.box}>
          <label className={registerStyle.inputLabel}>아이디 (이메일)</label>
          <div>
            <input type="text" className={`${registerStyle.inputText} ${registerStyle.halfInputText}`} value={id} onChange={(e) => { setId(e.target.value)}} />
            <button className={registerStyle.confirm} onClick={isExistId}> 중복확인 </button>
          </div>
        </div>
        <div className={`${registerStyle.box} ${registerStyle.confirmBox}`}>
          <NavLink className={registerStyle.find} to="/find">
            아이디 혹은 비밀번호를 잊으셨나요?
          </NavLink>
          <button className={registerStyle.confirm} onClick={doLogin}> 로그인 </button>
        </div>
      </div>
    </>
  );
}

export default LoginComponent;
