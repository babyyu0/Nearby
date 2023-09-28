// Modules
import { NavLink } from "react-router-dom";
import { existId } from "../../services/member/MemberService";

// Components
import RegisterComponent from "../../components/member/RegisterComponent";

// Style
import registerStyle from "../../resources/css/member/Register.module.css";
import { useState } from "react";

function RegisterContainer() {
  const [id, setId] = useState("");

  const isExistId = () => {
    const request = { id };
    const data = existId(request);
    console.log(data);
  };

  return (
    <>
      <div className={registerStyle.container}>
        <NavLink to="/" className={registerStyle.logoBox}>
          <img className={registerStyle.logo} src="/image/logo.png" alt="logo" />
        </NavLink>
        <span className={registerStyle.title}>회원가입</span>
        <RegisterComponent registerStyle={registerStyle} id={id} setId={setId} isExistId={isExistId} />
      </div>
    </>
  );
}

export default RegisterContainer;
