import { NavLink } from "react-router-dom";
import header from "../../resources/css/common/Header.module.css";
import { useAtom } from "jotai";
import { memberAtom } from "../../jotai/member";

function HeaderComponent() {
  const [member, setMember] = useAtom(memberAtom);
  console.log(member);
  return (
    <div className={header.container}>
      <div className={header.contents}>
        <div className={header.leftContents}>
          <NavLink to="/" className={header.logoBox}>
            <img className={header.logo} src="/image/logo.png" alt="logo" />
          </NavLink>
          <div className={header.searchBox}>
            <input type="text" className={header.search} placeholder="검색어를 입력해 주세요." />
            <img className={header.searchBtn} src="/image/search.png" alt="search" />
          </div>
          <NavLink to="/trip" className={header.leftMenu}>
            관광지
          </NavLink>
          <NavLink to="/board" className={header.leftMenu}>
            여행게시판
          </NavLink>
        </div>
        {(member.name) ?
          (
            <div className={header.rightContents}>
              <NavLink to="/my" className={header.rightMenu}>마이페이지</NavLink>
              <img src=""/>
              <div>{member.name}</div>
            </div>
          ) :
          (
            <div className={header.rightContents}>
              <NavLink to="/login" className={header.login}>로그인</NavLink>
            </div>
          )
        }
      </div>
    </div>
  );
}

export default HeaderComponent;
