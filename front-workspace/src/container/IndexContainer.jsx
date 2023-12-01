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
  const [nearestList, setNearestList] = useState(null);
  const [popularList, setPopularList] = useState(null);

  const refreshNearestList = async () => {
    const response = await getNearestList(await getLocation());
    setNearestList(response);
  };

  const refreshPopularList = async () => {
    const response = await getPopularList();
    setPopularList(response);
  };

  useEffect(() => {
    if (!nearestList) {
      refreshNearestList();
    }
  }, [nearestList]); // 가까운 관광지 호출

  useEffect(() => {
    if (!popularList) {
      refreshPopularList();
    }
  }, [popularList]); // 유명한 관광지 호출

  return (
    <>
      <HeaderComponent />
      <div className={mainStyle.container}>
        <div className={mainStyle.mainBanner}></div>
        <div className={mainStyle.popularList}>
          <h3 className={mainStyle.menuTitle}>🏠 가까운 관광지를 확인하세요</h3>
          {nearestList ? (
            nearestList.map((popular, index) => <CardComponent />)
          ) : (
            <div className={mainStyle.noContent}>
              🤔 근처 관광지가 없습니다! 관광지 검색을 통해 관광지를 찾아보세요.
            </div>
          )}
        </div>
        <div className={mainStyle.popularList}>
          <h3 className={mainStyle.menuTitle}>🚀 인기 관광지를 확인하세요</h3>
          {popularList ? (
            popularList.map((popular, index) => <CardComponent />)
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
