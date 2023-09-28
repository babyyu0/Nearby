import { NavLink } from "react-router-dom";
import header from "../resources/css/Header.module.css";

function HeaderComponent() {
  return (
    <div className={header.container}>
      <div className={header.contents}>
        <div className={header.leftContents}>
          <NavLink to="/" className={header.logoBox}>
            <img className={header.logo} src="/image/logo.png" alt="logo" />
          </NavLink>
          <NavLink to="/trip" className={header.leftMenu}>
            관광지
          </NavLink>
          <NavLink to="/board" className={header.leftMenu}>
            여행게시판
          </NavLink>
        </div>
        <div className={header.rightContents}>
					<NavLink to="/my" className={header.rightMenu}>마이페이지</NavLink>
					<NavLink to="/login" className={header.rightMenu}>로그인</NavLink>
					<NavLink to="/register" className={header.rightMenu}>회원가입</NavLink>
        </div>
      </div>
    </div>
  );
}

export default HeaderComponent;
