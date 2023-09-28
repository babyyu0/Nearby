// Modules
import { NavLink } from "react-router-dom";

// Components
import LoginComponent from "../../components/member/LoginComponent";

// Style
import login from "../../resources/css/Login.module.css";
import { useState } from "react";

function LoginContainer() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  return (
    <>
      <div className={login.container}>
        <NavLink to="/" className={login.logoBox}>
          <img className={login.logo} src="/image/logo.png" alt="logo" />
        </NavLink>
        <span className={login.title}>로그인</span>
        <LoginComponent id={id} setId={setId} password={password} setPassword={setPassword} />
      </div>
    </>
  );
}

export default LoginContainer;
