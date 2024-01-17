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
          <BannerComponent registerStyle={registerStyle} text={`text A`} />
          <BannerComponent registerStyle={registerStyle} text={`text B`} />
          <BannerComponent registerStyle={registerStyle} text={`text C`} />
        </Slider>
      </div>
    </>
  );
}

export default RegisterDescComponent;
