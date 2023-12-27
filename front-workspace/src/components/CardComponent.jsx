import { useEffect } from "react";


function CardComponent({ mainStyle, item }) {
  useEffect(() => {
    if (item && item.img === "") {
      item.img = "/image/none_attraction.jpg";
    }
  }, [item]);
  return (
    <>
      <div className={mainStyle.cardContents}>
        <img className={mainStyle.cardImg} src={item.img} alt={`${item.title} 이미지`} />
        <h3 className={mainStyle.cardTitle}>{item.title}</h3>
        <p className={mainStyle.cardContent}>당신과의 거리가 {item.dist < 1000 ? `${item.dist} m` : `${(item.dist / 10000).toFixed(2)} km`} </p>
        <p className={mainStyle.cardContent}><img className={mainStyle.cardHeart} src={ `/image/heart_blank.png` } alt="좋아요 이미지" /> {item.heart}</p>
      </div>
    </>
  );
}

export default CardComponent;
