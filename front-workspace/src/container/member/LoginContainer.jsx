// Modules
import { NavLink } from "react-router-dom";
import { login } from "../../services/member/MemberService";

// Components
import LoginComponent from "../../components/member/LoginComponent";

// Style
import loginStyle from "../../resources/css/member/Login.module.css";
import { useState } from "react";

function LoginContainer() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const doLogin = async () => {
    const request = {id, password};
    const data = await login(request);

    console.log(data);
  }

  return (
    <>
      <div className={loginStyle.style.container}>
        <NavLink to="/" className={loginStyle.logoBox}>
          <img className={loginStyle.logo} src="/image/logo.png" alt="logo" />
        </NavLink>
        <span className={loginStyle.title}>로그인</span>
        <LoginComponent loginStyle={loginStyle} id={id} setId={setId} password={password} setPassword={setPassword} doLogin={doLogin} />
      </div>
    </>
  );
}

export default LoginContainer;
