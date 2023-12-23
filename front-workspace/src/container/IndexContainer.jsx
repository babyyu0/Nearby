// Modules
import { useEffect, useState } from "react";

// Services
import { getNearestList, getPopularList } from "../services/MainService";
import { getLocation } from "../services/common/GeoLocationService";

// Components
import HeaderComponent from "../components/common/HeaderComponent";
import CardComponent from "../components/CardComponent";

// Styles
import mainStyle from "../resources/css/Main.module.css";

function IndexContainer() {
  let [myLocation, setMyLocation] = useState(null);
  const [nearestList, setNearestList] = useState(null);
  const [popularList, setPopularList] = useState(null);

  const refreshMyLocation = async () => {
    setMyLocation(await getLocation());
  };

  const refreshNearestList = async () => {
    console.log(myLocation);
    const response = await getNearestList(myLocation);
    setNearestList(response);
  };

  const refreshPopularList = async () => {
    console.log(myLocation);
    const response = await getPopularList(myLocation);
    setPopularList(response);
  };

  useEffect(() => {
    if (!nearestList && myLocation) {
      refreshNearestList();
    }
  }, [nearestList, myLocation]); // 가까운 관광지 호출

  useEffect(() => {
    if (!popularList && myLocation) {
      refreshPopularList();
    }
  }, [popularList, myLocation]); // 유명한 관광지 호출

  useEffect(() => {
    if (!myLocation) {
      refreshMyLocation();
    }
  }, [myLocation]);

  return (
    <>
      <HeaderComponent />
      <div className={mainStyle.container}>
        <div className={mainStyle.mainBanner}></div>
        <div className={mainStyle.popularList}>
          <h3 className={mainStyle.menuTitle}>🏠 가까운 관광지를 확인하세요</h3>
          {nearestList && nearestList.length !== 0 ? (
            <div className={mainStyle.cardContainer}>
              {nearestList.map((nearest, index) => (
                <CardComponent mainStyle={mainStyle} item={nearest} />
              ))}
            </div>
          ) : (
            <div className={mainStyle.noContent}>
              🤔 근처 관광지가 없습니다! 관광지 검색을 통해 관광지를 찾아보세요.
            </div>
          )}
        </div>
        <div className={mainStyle.popularList}>
          <h3 className={mainStyle.menuTitle}>🚀 인기 관광지를 확인하세요</h3>
          {popularList && popularList.length !== 0 ? (
            <div className={mainStyle.cardContainer}>
              {popularList.map((popular, index) => (
                <CardComponent mainStyle={mainStyle} item={popular} />
              ))}
            </div>
          ) : (
            <div className={mainStyle.noContent}>
              🤔 인기 관광지가 없습니다! 하트를 눌러 좋아하는 관광지를 표시하세요.
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default IndexContainer;
