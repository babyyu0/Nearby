<template>
  <div>
    <div id="map"></div>
    <b-jumbotron style="background-color: white;">
      <template #header><div class="font-hyun mb-5">Enjoy Your Trip ✈️</div></template>
      <template #lead>PyP Trip과 함께 여행지를 찾아보세요!</template>
    </b-jumbotron>
    <b-container>
      <b-carousel id="carousel-fade" style="text-shadow: 0px 0px 2px #000" fade controls indicators img-width="1024" img-height="480">
        <b-carousel-slide v-for="(thumbnail, index) in thumbnails" :key="index">
          <template #img>
            <img
              class="d-block img-fluid w-100"
              style="width:1024px;height:480px;"
              :src="thumbnail.thumbImg"
              :alt="thumbnail.sidoName"
            >
          </template>
          <h4>{{ thumbnail.sidoName }}에 갈까요?</h4>
          <b-button variant="success" class="m-2" to="/trip/list">여행지 보기</b-button>
        </b-carousel-slide>
      </b-carousel>
      <div class="mt-5">
        <h4 align="left">🗺️ 현재 나와 가장 가까운 관광지는?</h4>
        <b-card-group deck class="mt-3">
          <b-card class="overflow-hidden" v-for="(close, index) in closestTrips" :key="index" align="left" :title="close.title" no-body img-top>
            <b-img :src="close.firstImage" height="160px" v-if="close.firstImage"></b-img>
            <b-skeleton-img card-img="top" height="160px" v-else></b-skeleton-img>
            <b-card-body>
              <b-card-title class="text-truncate">{{ close.title }}</b-card-title>
              <b-card-text>{{ close.addr }} {{ close.addr2 }}</b-card-text>
            </b-card-body>
            <b-card-footer footer-bg-variant="transparent" footer-border-variant="light">
              <b-button size="sm" variant="outline-info" v-model="close.title" @click="$router.push(`/trip/info/${close.contentId}`)">관광지 보기</b-button>
            </b-card-footer>
          </b-card>
        </b-card-group>
      </div>
      <div class="mt-5">
        <b-row>
          <b-col cols="6">
            <b-row align-v="center">
              <b-col><h4 align="left">📋 자유 게시판</h4></b-col>
              <b-col align="right"><b-link to="/board/free">더보기 ></b-link></b-col>
            </b-row>
            <b-list-group v-if="boards.freeBoards.length > 0" flush align="left">
              <b-list-group-item v-for="(freeboard, index) in boards.freeBoards" :key="index">
                <div @click="$router.push(`/board/free/${freeboard.code}`);" style="cursor: pointer;">{{ freeboard.title }}</div>
              </b-list-group-item>
            </b-list-group>
            <div class="mt-5" v-else> 아직 게시글이 없습니다.</div>
          </b-col>
          <b-col cols="6">
            <b-row align-v="center">
              <b-col><h4 align="left">📋 거리별 게시판</h4></b-col>
              <b-col align="right"><b-link to="/board/location">더보기 ></b-link></b-col>
            </b-row>
            <b-list-group v-if="boards.locationBoards.length > 0" flush align="left">
              <b-list-group-item v-for="(locationboard, index) in boards.locationBoards" :key="index">
                <div @click="$router.push(`/board/location/${locationboard.code}`);" style="cursor: pointer;">{{ locationboard.title }}</div>
              </b-list-group-item>
            </b-list-group>
            <div class="mt-5" v-else> 아직 게시글이 없습니다.</div>
          </b-col>
        </b-row>
      </div>
      <div class="holder">
        <svg xmlns="http://www.w3.org/2000/svg">
            <filter id="stripes">
              <feTurbulence baseFrequency=".1 0.5"/>
              <feColorMatrix values=".05 .1 0 0 0 .65 .4 0 0 0 .07 .1 .1 0 0 0 0 0 0 1"/>
            </filter>
            <pattern id="pattern" width="1" height="1">
              <rect width="200%" height="200%" filter="url(#stripes)"/>
            </pattern>
            <filter id="filter">
                <feTurbulence type="fractalNoise" baseFrequency=".025" numOctaves="11"/>
                <feDisplacementMap in="SourceGraphic" scale="250"/>
              <feComposite operator="in" in2="SourceGraphic" />
            </filter>
          <circle cx="50%" cy="50%" r="25%" fill="darkblue"/>
          <circle cx="50%" cy="50%" r="25%" fill="url(#pattern)" filter="url(#filter)" stroke="tan" stroke-width="2"/>
        </svg>
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
    name: "HomeView",
    data() {
        return {
            thumbnails: [],
            latitude: null,
            longitude: null,
          closestTrips: [],
            boards: {
              fields: [
                { key: "code",      label: "번호" },
                { key: "writerId",  label: "작성자" },
                { key: "title",     label: "게시글" },
                { key: "createdAt", label: "날짜", sortable: true },
              ],
              freeBoards: [],
              locationBoards: []
            }
        };
    },
    methods: {
      getBoards() {
        // Free Board
        this.$axios({
          url: "board/list",
          data: { type: "free", pageNum: 1, pageSize: 4 }
        }).then((response) => {
          this.boards.freeBoards = response.data.list;
        });

        // Location Board
        this.$axios({
          url: "board/list",
          data: { type: "location", pageNum: 1, pageSize: 4}
        }).then((response) => {
          this.boards.locationBoards = response.data.list;
        });
      }
    },
    created() {
      this.getBoards();
        // 랜덤 썸네일 가져오기
      this.$axios.post("trip/get-thumbnail").then((response) => {
            this.thumbnails = response.data;
        });

        // 위치에 따른 거리별 관광지 가져오기
        navigator.geolocation.getCurrentPosition(pos => {
            this.latitude = pos.coords.latitude;
            this.longitude = pos.coords.longitude;
            // console.log("Your location data is " + this.latitude + ", " + this.longitude);
            this.$axios({
                url: "trip/get-closest-trip",
                data: { latitude: this.latitude, longitude: this.longitude }
            }).then((response) => {
              this.closestTrips = response.data;
            });
        }, err => {
            console.log(err.message);
        });
    },
}
</script>

<style scoped>

svg {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	animation: go 5s ease-in-out infinite;
	height: auto;
	width: 100%;
}
.holder {
  position: relative;
  margin: 0 auto;
	height: 250px;
	width: 400px;
}
@keyframes go {
	0% {
		width: 100%;
	}
	50% {
		width: 50%;
	}
	100% {
		width: 100%
	}
}
</style>