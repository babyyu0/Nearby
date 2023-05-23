<template>
  <div>
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
            <b-button variant="primary" @click="$router.push(`/board/${type}/write`)">글쓰기</b-button>
          </b-col>
        </b-row>
        <b-table class="mt-3" :fields="fields" :items="boards" hover head-variant="light">
          <template #cell(title)="row">
            <span @click="$router.push(`/board/${type}/${row.item.code}`);" style="cursor:pointer"> {{ row.item.title }} </span>
          </template>
          <template #cell(createdAt)="row">
            <div> {{ row.item.createdAt | dateFilter }} </div>
          </template>
        </b-table>
          
        <b-pagination
        align="center"
          v-model="page.pageNum"
          :per-page="page.pages"
        ></b-pagination>
      </div>
        <div class="mt-5" v-else>게시물이 존재하지 않습니다.</div>
    </b-container>
  </div>
</template>

<script>
import BoardHeader from "@/components/common/BoardHeader.vue";

export default {
  components: {
    BoardHeader,
  },
  data() {
    return {
      type: "",
      title: "",
      searchCategory: "title",
      searchWord: "",
      page: {
        pages: null,
        pageNum: 1,
        pageSize: 20,
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
      this.$axios({
        url: "http://localhost:9999/board/list",
        method: "post",
        params: {
          type: this.type,
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize, 
        },
      })
        .then((response) => {
          console.log(response.data);
          this.page.pages = response.data.pages;
          this.boards = response.data.list;
        })
        .catch(() => {
          this.boards = [];
        });
    },
    moveInfo(code) {
      this.$router.push(`/board/${this.type}/${code}`);
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
