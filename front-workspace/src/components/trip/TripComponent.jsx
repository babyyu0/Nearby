import { useEffect, useRef, useState } from "react";

function TripComponent({tripStyle}) {
  console.log("Kakao : ", window.kakao);
  const mapContainer = useRef();
  const [map, setMap] = useState(null);
  useEffect(() => {
    if (window.kakao) {
      setMap(
        new window.kakao.maps.Map(mapContainer.current, {
          center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
          level: 3, //지도의 레벨(확대, 축소 정도)
        })
      );
    }
  }, [window.kakao]);

  return (
    <>
      <div className={tripStyle.tripContents}>
        <div className={tripStyle.mapBox} ref={mapContainer}></div>
        <div className={ tripStyle.tripBox }></div>
      </div>
    </>
  );
}

export default TripComponent;
