<template>
    <b-container>
      <board-header :type="type"></board-header>
      <h1 class="mt-5">{{ title }} 게시판</h1>
      <div align="center" v-if="boards.length > 0">
        <b-row class="mt-5">
          <b-col cols="2"></b-col>
          <b-col cols="8">
            <b-input-group>
              <b-input-group-prepend>
                <b-form-select v-model="searchCategory">
                  <b-form-select-option value="title">제목만</b-form-select-option>
                  <b-form-select-option value="titleContents">제목 + 내용</b-form-select-option>
                  <b-form-select-option value="writerName">작성자 이름</b-form-select-option>
                </b-form-select>
              </b-input-group-prepend>
              <b-form-input :v-model="searchWord"></b-form-input>
              <b-input-group-append>
                <b-button>검색</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-col>
          <b-col cols="2" align="right">
            <b-button variant="primary" @click="moveWriteBoard" v-if="this.$store.state.member.logged">글쓰기</b-button>
          </b-col>
        </b-row>
        <b-table id="board-table" class="mt-3" :fields="fields" :items="boards" hover head-variant="light" :per-page="page.perPage" :current-page="page.currentPage">
          <template #cell(title)="row">
            <span @click="$router.push(`/board/${type}/${row.item.code}`);" style="cursor:pointer"> {{ row.item.title }} </span>
          </template>
          <template #cell(createdAt)="row">
            <div> {{ row.item.createdAt | dateFilter }} </div>
          </template>
						<template #cell(distance)="row">
							{{ row.item.distance.toFixed(2) }} km
						</template>
        </b-table>
          
				<b-pagination
					v-model="page.currentPage"
					:total-rows="boards.length"
					:per-page="page.perPage"
					aria-controls="board-table"
					align="center"
				></b-pagination>
      </div>
      <div class="mt-5" v-else>
        게시물이 존재하지 않습니다.
        <b-link @click="moveWriteBoard" class="d-block mt-5">글쓰러 가기</b-link>
      </div>
      <alert-modal :modalMsg="modalMsg"></alert-modal>
  </b-container>
</template>

<script>
import BoardHeader from "@/components/common/BoardHeader.vue";
import AlertModal from "../common/AlertModal.vue";

export default {
  components: {
    BoardHeader,
    AlertModal
  },
  data() {
    return {
      modalMsg: "",
      type: "",
      title: "",
      searchCategory: "title",
      searchWord: "",
			latitude: 0,
			longitude: 0,
      page: {
        perPage: 10,
        currentPage: 1,
      },
      fields: [
        { key: "code", label: "번호", sortable: true },
        { key: "title", label: "게시글" },
        { key: "writerId", label: "작성자" },
        { key: "createdAt", label: "날짜", sortable: true },
      ],
      boards: [],
    };
  },
  filters: {
    dateFilter(selDate) {
      const curDate = new Date();
      selDate = new Date(selDate);
      if (curDate.getFullYear() == selDate.getFullYear() && curDate.getMonth() == selDate.getMonth() && curDate.getDate() == selDate.getDate()) {  // 날짜 전체가 같을 경우
        return ('0' + selDate.getHours()).slice(-2) + ":" + ('0' + selDate.getMinutes()).slice(-2);
      }
      return selDate.getFullYear() + "." + ('0' + (selDate.getMonth() + 1)).slice(-2) + "." + ('0' + selDate.getDate()).slice(-2);
    }
  },
  methods: {
    changeType() {
      this.type = this.$route.params.type;

      // 타이틀 변경
      if (this.type == "free") {
        this.title = "자유";
      } else if (this.type == "location") {
        this.title = "거리별";
      }

      // 페이지 가져오기
      this.getPage();
    },
    getPage() {
      
      navigator.geolocation.getCurrentPosition(pos => {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;
      
        this.$axios({
          url: "board/list",
          data: {
            type: this.type,
            latitude: pos.coords.latitude,
            longitude: pos.coords.longitude,
          },
        })
          .then((response) => {
            this.boards = response.data;
            if (this.type == "location") {
              this.getDistanceFromLatLonInKm();
              this.fields.push({ key: "distance", label: "거리", sortable: true });
            } else {
              this.fields.length = 4;
          }
        })
        .catch(() => {
          this.boards = [];
        });
      }, err => {
          console.log(err.message);
      });
    },
    moveWriteBoard() {
      if (!this.$store.state.member.logged) {
        this.modalMsg = "로그인된 사용자만 글을 작성할 수 있습니다.";
        this.$bvModal.show('bv-modal');
        return;
      } else {
        this.$router.push(`/board/${this.type}/write`);
      }
    },
		getDistanceFromLatLonInKm() {
			function deg2rad(deg) {
				return deg * (Math.PI/180)
			}

			var R = 6371; // Radius of the earth in km
			for (var index in this.boards) {
				//console.log(this.tripList[index]);

				var dLat = deg2rad(this.latitude - this.boards[index].latitude);  // deg2rad below
				var dLon = deg2rad(this.longitude - this.boards[index].longitude);
				var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(this.boards[index].latitude)) * Math.cos(deg2rad(this.latitude)) * Math.sin(dLon/2) * Math.sin(dLon/2);
				var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				var d = R * c; // Distance in km
        this.boards[index].distance = d;
			}
		},
  },
  created() {
    this.changeType();
  },
  watch: {
    $route: function () {
      this.changeType();
    },
  },
};
</script>

<style scoped></style>
