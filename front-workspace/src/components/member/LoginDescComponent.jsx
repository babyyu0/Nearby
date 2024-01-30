function LoginDescComponent({ loginStyle, navigate }) {

  return (
    <>
      <div className={loginStyle.loginDescBox}>
        <div className={loginStyle.infoDesc}>잠깐만요!<br/>😙 Nearby가 처음이시라면?</div>
        <div className={loginStyle.confirmBox}>
          <button className={loginStyle.confirmBtn} onClick={() => navigate(`/register`)}> 회원가입 하러 가기 </button>
        </div>
      </div>
    </>
  );
}

export default LoginDescComponent;
