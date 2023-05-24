let pingList = [];
let infoList = [];

// 각 관광 등의 시설물 위치의 핑을 찍는 함수
function setCenter(map, latitude, longitude) {
	map.panTo(new window.kakao.maps.LatLng(latitude, longitude));
}
function setMapLocation(map, location) {
  for (var i = 0; i < pingList.length; i++) {
    pingList[i].setMap(null);
  }
  closeOverlay();

  pingList = [];
  infoList = [];

  if (location == null) return;
  for (let i = 0; i < location.length; i++) {
    pingList.push(
      new window.kakao.maps.Marker({
        position: new window.kakao.maps.LatLng(location[i].latitude, location[i].longitude),
      })
    );
    pingList[i].setMap(map);

    /** Custom Overlay **/
    var content =
      "<div class='wrap'>" +
      "<div class='info'>" +
      "<div class='title'>" +
      location[i].title +
      "<div class='close' onclick='closeOverlay(" + i +
      ")' title='닫기'></div>" +
      "</div>" + // title close
      "<div class='body'>" +
      "<div class='img'><img src='" +
      location[i].firstImage +
      "' width=73 height=70></div>" +
      "<div class='desc'>" +
      "<div class='ellipsis'>" +
      location[i].addr +
      "</div>" +
      "<div class='ellipsis'>" +
      location[i].addr2 +
      "</div>" +
      "</div>" + // addr close
      "</div>" + // desc close
      "</div>" + // body close
      "</div>" + // info close
      "</div>"; // wrap close
    infoList.push(
      new window.kakao.maps.CustomOverlay({
        content: content,
        position: pingList[i].getPosition(),
      })
    );

    //let tmp = i;
    window.kakao.maps.event.addListener(pingList[i], "click", function () {
      this.closeOverlay();
      infoList[i].setMap(map);
    });
  }
  map.setCenter(new window.kakao.maps.LatLng(location[0].latitude, location[0].longitude));
}

function closeOverlay(idx) {
  if (idx == null) {
    infoList.forEach((info) => {
      info.setMap(null);
    });
  } else {
    infoList[idx].setMap(null);
  }
}

console.log("kakao-map.js");

export { setMapLocation, closeOverlay, setCenter };