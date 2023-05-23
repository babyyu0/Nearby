<template>
  <div>
    <div id="map"></div>
    <b-jumbotron header="Enjoy Your Trip ✈️" lead="PyP Trip과 함께 여행지를 찾아보세요!" style="background-color: white;">
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
          <b-button variant="success" class="m-2">여행지 보기</b-button>
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
              <b-button size="sm" variant="outline-info" v-model="close.title" @click="$router.push(`/trip/info/${close.contentId}`)">관광지 보기</b-button>
            </b-card-body>
          </b-card>
        </b-card-group>
      </div>
      <div class="mt-5">
        <b-row>
          <b-col cols="6">
            <h4 align="left">📋 자유게시판</h4>
            <b-list-group v-if="boards.freeBoards.length > 0" flush align="left">
              <b-list-group-item v-for="(freeboard, index) in boards.freeBoards" :key="index">{{ freeboard.title }}</b-list-group-item>
            </b-list-group>
            <div class="mt-5" v-else> 아직 게시글이 없습니다.</div>
          </b-col>
          <b-col cols="6">
            <h4 align="left">📋 거리별 게시판</h4>
            <b-table :fields="boards.fields" :items="boards.locationBoards" v-if="boards.locationBoards.length > 0">
              <template #cell(title)="row">
                <div class="text-truncate" @click="moveInfo(row.item.code)" v-text="row.item.title" align="left"></div>
              </template>
            </b-table>
            <div class="mt-5" v-else> 아직 게시글이 없습니다.</div>
          </b-col>
        </b-row>
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
      moveTripInfo(contentId) {
        this.$emit("moveTripInfo", contentId);
        this.$router.push("/trip/info");
      },
      getBoards() {
        // Free Board
        this.$axios({
          url: "http://localhost:9999/board/list",
          method: "POST",
          params: { type: "free", pageNum: 1, pageSize: 5}
        }).then((response) => {
          this.boards.freeBoards = response.data.list;
          console.log(this.boards.freeBoards.list);
        });

        // Location Board
        this.$axios({
          url: "http://localhost:9999/board/list",
          method: "POST",
          params: { type: "location", pageNum: 1, pageSize: 5}
        }).then((response) => {
          this.boards.locationBoards = response.data.list;
          console.log(this.boards.locationBoards.list);
        });
      }
    },
    created() {
      this.getBoards();
        // 랜덤 썸네일 가져오기
        this.$axios({
            url: "http://localhost:9999/trip/get-thumbnail",
            method: "POST",
        }).then((response) => {
            this.thumbnails = response.data;
        });

        // 위치에 따른 거리별 관광지 가져오기
        navigator.geolocation.getCurrentPosition(pos => {
            this.latitude = pos.coords.latitude;
            this.longitude = pos.coords.longitude;
            // console.log("Your location data is " + this.latitude + ", " + this.longitude);
            this.$axios({
                url: "http://localhost:9999/trip/get-closest-trip",
                method: "POST",
                params: { latitude: this.latitude, longitude: this.longitude }
            }).then((response) => {
              this.closestTrips = response.data;
            });
        }, err => {
            console.log(err.message);
        });
    },
}
</script>
