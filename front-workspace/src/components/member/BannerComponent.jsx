function BannerComponent({ registerStyle, text }) {
  return (
    <>
      <div className={registerStyle.bannerBox}>{text}</div>
    </>
  );
}

export default BannerComponent;
