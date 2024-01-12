// Modules
import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useAtom } from "jotai";
import toast from "react-hot-toast";

// Jotai
import { memberAtom } from "../../jotai/member";

// Styles
import header from "../../resources/css/common/Header.module.css";

function HeaderComponent() {
  const navigate = useNavigate();
  const [member, setMember] = useAtom(memberAtom);
  const [word, setWord] = useState("");
  const search = () => {
    if (word === "") {
      toast("검색어를 입력해 주세요.", { duration: 800, icon: '⌨️'});
    } else {
      navigate(`/search?word=${word}`);
    }
  };

  return (
    <div className={header.container}>
      <div className={header.contents}>
        <div className={header.leftContents}>
          <NavLink to="/" className={header.logoBox}>
            <img className={header.logo} src="/image/logo.png" alt="logo" />
          </NavLink>
          <div className={header.searchBox}>
            <input type="text" className={header.search} value={word} onChange={(e) => setWord(e.target.value)} placeholder="검색어를 입력해 주세요." />
            <img className={header.searchBtn} src="/image/search.png" alt="search" onClick={search} />
          </div>
          <NavLink to="/attraction" className={header.leftMenu}>관광지</NavLink>
          <NavLink to="/map" className={header.leftMenu}>지도로보기</NavLink>
          <NavLink to="/board" className={header.leftMenu}>여행게시판</NavLink>
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
