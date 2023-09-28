// Modules
import { NavLink } from "react-router-dom";
import Swal from "sweetalert2";

// Services
import { existId, getSido } from "../../services/member/MemberService";

// Components
import RegisterComponent from "../../components/member/RegisterComponent";

// Styles
import registerStyle from "../../resources/css/member/Register.module.css";
import { useEffect, useState } from "react";

function RegisterContainer() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [name, setName] = useState("");
  const [sido, setSido] = useState("");

  const [sidoList, setSidoList] = useState({});
  const [gugunList, SetGugunList] = useState({});

  const isExistId = async () => {
    if (!id) {
      Swal.fire({
        icon: "info",
        title: "아이디가 입력되지 않았습니다!",
      });
      return;
    }

    const request = { id };
    const data = await existId(request);
    if (data === true) {
      Swal.fire({
        icon: "error",
        title: "중복된 아이디입니다.",
      });
      return;
    } else if (data === false) {
      Swal.fire({
        icon: "success",
        title: "사용 가능한 아이디입니다.",
      });
    }
  };

  const getSido = async () => {
    const data = await getSido();
    console.log(data);
  };

  useEffect(() => {
    if (!sidoList) {
      const data = getCity();
      console.log(data);
    }
  }, []);

  return (
    <>
      <div className={registerStyle.container}>
        <NavLink to="/" className={registerStyle.logoBox}>
          <img className={registerStyle.logo} src="/image/logo.png" alt="logo" />
        </NavLink>
        <span className={registerStyle.title}>회원가입</span>
        <RegisterComponent
          registerStyle={registerStyle}
          id={id}
          setId={setId}
          isExistId={isExistId}
          password={password}
          setPassword={setPassword}
          passwordConfirm={passwordConfirm}
          setPasswordConfirm={setPasswordConfirm}
          name={name}
          setName={setName}
          sido={sido}
          setSido={setSido}
        />
      </div>
    </>
  );
}

export default RegisterContainer;
