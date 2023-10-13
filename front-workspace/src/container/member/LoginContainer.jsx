// Modules
import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useAtom } from "jotai";

// Services
import { login } from "../../services/member/MemberService";

// Atoms
import { memberAtom } from "../../jotai/member";

// Components
import LoginComponent from "../../components/member/LoginComponent";

// Style
import loginStyle from "../../resources/css/member/Login.module.css";

function LoginContainer() {
  const navigate = useNavigate();
  
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [member, setMember] = useAtom(memberAtom);

  const doLogin = async () => {
    const request = {"memberId": id, password};
    const response = await login(request);

    if(response.data) {
      setMember(response.data);
      Swal.fire({
        icon: "success",
        title: "로그인을 성공 했습니다.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/");
        }
      });
    }
  }

  return (
    <>
      <div className={loginStyle.container}>
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
