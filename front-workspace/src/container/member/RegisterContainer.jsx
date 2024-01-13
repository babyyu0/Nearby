// Modules
import { NavLink, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useAtom } from "jotai";

// Services
import { existId, register } from "../../services/member/MemberService";
import { getArea } from "../../services/attraction/AreaService";

// Atoms
import { sidoAtom, gugunAtom } from "../../jotai/city";

// Components
import RegisterComponent from "../../components/member/RegisterComponent";
import RegisterDescComponent from "../../components/member/RegisterDescComponent";

// Styles
import registerStyle from "../../resources/css/member/Register.module.css";
import { useEffect, useState } from "react";

function RegisterContainer() {
  const navigate = useNavigate();
  const [profile, setProfile] = useState("image/none_profile.png");
  const [id, setId] = useState("");
  const [idConfirm, setIdConfirm] = useState(false);
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [name, setName] = useState("");
  const [sido, setSido] = useState(0);
  const [gugun, setGugun] = useState(0);

  const [sidoList, setSidoList] = useAtom(sidoAtom);
  const [gugunList, setGugunList] = useAtom(gugunAtom);
  let tmpGugunList = [];

  const isExistId = async () => {
    if (!new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,20}$').test(id)) {
      Swal.fire({
        icon: "error",
        title: "올바른 아이디 형식이 아닙니다.",
      });
      return;
    }

    const data = await existId({ id });
    if (data.valid === false) {
      setIdConfirm(false);
      Swal.fire({
        icon: "error",
        title: "중복된 아이디입니다.",
      });
      return;
    } else if (data.valid === true) {
      setIdConfirm(true);
      Swal.fire({
        icon: "success",
        title: "사용 가능한 아이디입니다.",
      });
    }
  };

  const refreshArea = async () => {
    const data = await getArea();
    console.log("data: ", data.sido);

    // 시도 리스트 세팅
    if (!sidoList) {
      setSidoList(data.sido);
    }

    tmpGugunList = [];
    data.gugun.forEach((e) => {
      if (!tmpGugunList[e.sidoCode]) {
        tmpGugunList[e.sidoCode] = [];
      }
      tmpGugunList[e.sidoCode].push({ 'code': e.code, 'name': e.name });
    });

    setGugunList([...tmpGugunList]);
  };

  const doRegister = async () => {
    if (!idConfirm) {
      Swal.fire({
        icon: "error",
        title: "아이디 중복확인을 해 주세요.",
      });
      return;
    } else if (!id || !new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,20}$').test(id)) {
      Swal.fire({
        icon: "error",
        title: "아이디를 확인해 주세요.",
      });
      return;
    } else if (!password || !new RegExp('^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,20}$').test(password)) {
      Swal.fire({
        icon: "error",
        title: "비밀번호를 확인해 주세요.",
      });
    } else if (password !== passwordConfirm) {
      Swal.fire({
        icon: "error",
        title: "비밀번호가 일치하지 않습니다.",
      });
    } else if (!name) {
      Swal.fire({
        icon: "error",
        title: "이름을 확인해 주세요.",
      });
    } else if (!sido || !gugun) {
      Swal.fire({
        icon: "error",
        title: "지역을 확인해 주세요.",
      });
    } else {
      const response = await register(new Blob([JSON.stringify({ "memberId": id, password, name, "sidoCode": sido, "gugunCode": gugun })], { type: "application/json" }), document.getElementById('profile').files[0]);
      if (response) {
        Swal.fire({
          icon: "success",
          title: "회원가입을 성공 했습니다.",
        }).then((result) => {
          if (result.isConfirmed) {
            navigate("/")
          }
        });
      }
    }
  };

  useEffect(() => {
    if (!gugunList || gugunList.length === 0) {
      refreshArea();
    }
  }, [gugunList]);

  return (
    <>
      <div className={registerStyle.headerContainer}>
        <div className={registerStyle.headerContents}>
          <NavLink to="/" className={registerStyle.logoBox}>
            <img className={registerStyle.logo} src="/image/logo.png" alt="logo" />
          </NavLink>
          <span className={registerStyle.headerTitle}>회원가입</span>
        </div>
      </div>
      <div className={registerStyle.registerContainer}>
        <RegisterComponent
          registerStyle={registerStyle}
          profile={profile} setProfile={setProfile}
          id={id} setId={setId} isExistId={isExistId} setIdConfirm={setIdConfirm}
          password={password} setPassword={setPassword} passwordConfirm={passwordConfirm} setPasswordConfirm={setPasswordConfirm}
          name={name} setName={setName}
          sido={sido} setSido={setSido}
          gugun={gugun} setGugun={setGugun}
          sidoList={sidoList} gugunList={gugunList}
          doRegister={doRegister}
        />
        <RegisterDescComponent registerStyle={registerStyle} />
      </div>
    </>
  );
}

export default RegisterContainer;
