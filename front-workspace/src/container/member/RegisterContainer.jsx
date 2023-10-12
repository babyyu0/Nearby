// Modules
import { NavLink } from "react-router-dom";
import Swal from "sweetalert2";
import { useAtom } from "jotai";

// Services
import { existId, getCity, register } from "../../services/member/MemberService";

//jotai
import { sidoAtom, gugunAtom } from "../../jotai/city";

// Components
import RegisterComponent from "../../components/member/RegisterComponent";

// Styles
import registerStyle from "../../resources/css/member/Register.module.css";
import { useEffect, useState } from "react";

function RegisterContainer() {
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
    if (!id) {
      Swal.fire({
        icon: "info",
        title: "아이디가 입력되지 않았습니다!",
      });
      return;
    } else if (!new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,20}$').test(id)) {
      Swal.fire({
        icon: "info",
        title: "이메일 형식으로 아이디를 입력해 주세요.",
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

  const getCities = async () => {
    const data = await getCity();

    // 시도 리스트 세팅
    if (!sidoList) {
      setSidoList(data.sidoList);
    }

    tmpGugunList = [];
      data.gugunList.forEach((e) => {
        if (!tmpGugunList[e.sidoCode]) {
          tmpGugunList[e.sidoCode] = [];
        }
        tmpGugunList[e.sidoCode].push({ 'gugunCode': e.gugunCode, 'gugunName': e.gugunName });
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
      const data = await register(new Blob([JSON.stringify({ "memberId": id, password, name, "sidoCode": sido, "gugunCode": gugun })], { type: "application/json" }), document.getElementById('profile').files[0]);
      console.log(data);
    }
  };

  useEffect(() => {
    if (!gugunList || gugunList.length == 0) {
      getCities();
    }
  }, [gugunList]);

  return (
    <>
      <div className={registerStyle.container}>
        <NavLink to="/" className={registerStyle.logoBox}>
          <img className={registerStyle.logo} src="/image/logo.png" alt="logo" />
        </NavLink>
        <span className={registerStyle.title}>회원가입</span>
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
      </div>
    </>
  );
}

export default RegisterContainer;
