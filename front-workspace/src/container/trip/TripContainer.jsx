// Modules
import { NavLink } from "react-router-dom";

// Services
import { login } from "../../services/member/MemberService";

// Atoms
import { memberAtom } from "../../jotai/member";

// Components
import LoginComponent from "../../components/member/LoginComponent";

// Style
import loginStyle from "../../resources/css/member/Login.module.css";

function TripContainer() {

  return (
    <>
      <HeaderComponent />
      <div className={loginStyle.container}>
      </div>
    </>
  );
}

export default TripContainer;
