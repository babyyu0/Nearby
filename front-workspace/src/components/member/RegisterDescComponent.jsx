// Modules
import Slider from "react-slick";

// Components
import BannerComponent from "./BannerComponent";

// Styles
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

function RegisterDescComponent({ registerStyle }) {
  const sliderSetting = {
    dots: true,
    arrows: false,
    autoplay: true
  };

  return (
    <>
      <div className={registerStyle.registerDescBox}>
        <Slider {...sliderSetting}>
          <BannerComponent registerStyle={registerStyle} imgUrl={`${process.env.PUBLIC_URL}/image/banner/desc01.png`} text={` 오늘의 인기 관광지를 확인해 보세요 🚀`} />
          <BannerComponent registerStyle={registerStyle} imgUrl={`${process.env.PUBLIC_URL}/image/banner/desc02.png`} text={`거리를 비교하며 가장 가까운 관광지를 찾아갈 수 있어요 🗺️`} />
          <BannerComponent registerStyle={registerStyle} imgUrl={`${process.env.PUBLIC_URL}/image/banner/desc03.png`} text={`함께 여행할 친구들도 모을 수 있답니다 😎`} />
        </Slider>
      </div>
    </>
  );
}

export default RegisterDescComponent;
