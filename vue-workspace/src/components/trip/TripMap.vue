<template>
  <div id="map" style="width: 100%; height: 460px"></div>
</template>

<script>
//import { setMapLocation, setCenter } from "@/api/kakao-map";

export default {
  name: "MapView",
  data() {
    return {
      map: {
        map: "",
        pingList: [],
        infoList: [],
      },
    };
  },
  props: {
    tripList: Array,
    latitude: Number,
    longitude: Number,
  },
  methods: {
    setPing() {
      if (this.map.pingList.length > 0) {
        for (var ping in this.map.pingList) {
          this.map.pingList[ping].setMap(null);
        }
      }
      if (this.map.infoList.length > 0) {
        for (var info in this.map.infoList) {
          this.map.infoList[info].setMap(null);
        }
      }

      this.map.pingList = [];
      this.map.infoList = [];
      if (this.tripList == null || this.tripList.length <= 0) return;
      
      for (var trip in this.tripList) {
        // 핑 찍기
        this.map.pingList.push(new window.kakao.maps.Marker({
          position: new window.kakao.maps.LatLng(this.tripList[trip].latitude, this.tripList[trip].longitude),
        }));
        this.map.pingList[trip].setMap(this.map.map);

        // 오버레이 만들기
        let wrapDiv = document.createElement("div");
        wrapDiv.className = "wrap";
        let infoDiv = document.createElement("div");
        infoDiv.className = "info";
        
        let titleDiv = document.createElement("div");
        titleDiv.className = "title";
        titleDiv.textContent = this.tripList[trip].title;
        let closeDiv = document.createElement("div");
        closeDiv.className = "close";
        let index = trip;
        closeDiv.onclick = () => {
          this.closeOverlay(index);
        };
        
        let bodyDiv = document.createElement("div");
        bodyDiv.className = "body";
        let imgDiv = document.createElement("div");
        imgDiv.className = "img";
        if (this.tripList[trip].firstImage != "") {
          let img = document.createElement("img");
          img.src = this.tripList[trip].firstImage;
          img.style.width = "73px";
          img.style.height = "70px";

          imgDiv.appendChild(img);
        } else {
          imgDiv.className = "img b-skeleton b-skeleton-img b-skeleton-animate-wave";
        }

        let descDiv = document.createElement("div");
        descDiv.className = "desc";
        let addrDiv = document.createElement("div");
        addrDiv.className = "ellipsis";
        addrDiv.textContent = this.tripList[trip].addr + " " + this.tripList[trip].addr2;
        
        descDiv.appendChild(addrDiv);

        titleDiv.appendChild(closeDiv);

        bodyDiv.appendChild(imgDiv);
        bodyDiv.appendChild(descDiv);

        infoDiv.appendChild(titleDiv);
        infoDiv.appendChild(bodyDiv);
        wrapDiv.appendChild(infoDiv);
        
        this.map.infoList.push(new window.kakao.maps.CustomOverlay({
          content: wrapDiv,
          position: this.map.pingList[trip].getPosition(),
        }));

        window.kakao.maps.event.addListener(this.map.pingList[trip], "click", () => {
          this.closeOverlay();
          this.map.infoList[index].setMap(this.map.map);
        });
      }
      this.map.map.setCenter(new window.kakao.maps.LatLng(this.tripList[0].latitude, this.tripList[0].longitude));
    },
    closeOverlay(index) {
      if (index) this.map.infoList[index].setMap(null);
      else {
        if (this.map.infoList.length > 0) {
          for (var info in this.map.infoList) {
            this.map.infoList[info].setMap(null);
          }
        }
      }
    },
    setCenter() {
      this.map.map.setCenter(new window.kakao.maps.LatLng(this.latitude, this.longitude));
    }
  },
  mounted() {
    var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    var options = {
      //지도를 생성할 때 필요한 기본 옵션
      center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
      level: 8, //지도의 레벨(확대, 축소 정도)
    };

    this.map.map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    //setMapLocation(this.map, this.tripList);
    this.setPing();
  },
  watch: {
    tripList: function () {
      this.setPing();
    },
    latitude: function () {
      this.setCenter();
    },
  },
};
</script>

<style>
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
