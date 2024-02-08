// Modules
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useAtom } from "jotai";

// Services
import { login } from "../../services/member/MemberService";

// Atoms
import { memberAtom } from "../../jotai/member";

// Components
import HeaderComponent from "../../components/common/HeaderComponent";
import LoginComponent from "../../components/member/LoginComponent";
import LoginDescComponent from "../../components/member/LoginDescComponent";

// Style
import loginStyle from "../../resources/css/member/Login.module.css";

function LoginContainer() {
  const navigate = useNavigate();
  
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [member, setMember] = useAtom(memberAtom);

  const doLogin = async () => {
    const response = await login({id, password});

    if(response) {
      setMember(response);
      Swal.fire({
        icon: "success",
        title: "로그인에 성공 했습니다.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/");
        }
      });
    }
  }

  return (
    <>
      <HeaderComponent />
      <div className={loginStyle.loginContainer}>
        <LoginComponent loginStyle={loginStyle} id={id} setId={setId} password={password} setPassword={setPassword} doLogin={doLogin} />
        <LoginDescComponent loginStyle={loginStyle} navigate={navigate} />
      </div>
    </>
  );
}

export default LoginContainer;
