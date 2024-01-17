function BannerComponent({ registerStyle, imgUrl, text }) {
  return (
    <div className={registerStyle.bannerBox}>
      <div className={registerStyle.bannerImg}>
        <img src={imgUrl} alt="carousal 이미지" />
      </div>
      <div className={registerStyle.bannerDesc}>{text}</div>
    </div>
  );
}

export default BannerComponent;
