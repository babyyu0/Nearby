<template>
  <div>
    <b-container>
      <b-nav tabs fill class="mt-5">
        <b-nav-item to="/board/free" v-bind="{ active: isActive.free }"
          >자유 게시판</b-nav-item
        >
        <b-nav-item to="/board/qna" v-bind="{ active: isActive.qna }"
          >QnA 게시판</b-nav-item
        >
      </b-nav>
      <router-view
        :articles="articles"
        @changeGroup="changeGroup"
      ></router-view>
      <h1 class="mt-5">{{ title }} 게시판</h1>
      <div class="mt-5" align="center">
        <div v-if="selectArticle">
          <div>제목 :</div>
        </div>
        <b-table
          hover
          fixed
          :items="articles"
          :fields="fields"
          v-else-if="articles.length != 0"
        >
          <template #cell(title)="row">
            <div @click="moveInfo(row.item.code)">
              {{ row.item.title }}
            </div>
          </template>
          <b-button>dd</b-button>
        </b-table>
        <div v-else>게시글이 없습니다.</div>
      </div>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardView",
  data() {
    return {
      group: "free",
      title: "자유",
      isActive: {
        free: false,
        qna: true,
      },
      fields: [
        {
          key: "code",
          label: "게시글번호",
        },
        {
          key: "writerId",
          label: "작성자",
        },
        {
          key: "title",
          label: "게시글",
        },
        {
          key: "createdAt",
          label: "게시 날짜",
          sortable: true,
        },
      ],
      articles: [],
      selectArticle: null,
    };
  },
  methods: {
    changeGroup(group) {
      this.group = group;
      axios({
        url: "http://localhost:9999/board/list",
        method: "post",
        params: { type: group },
      }).then((response) => {
        this.articles = response.data;
      
        if (this.$route.params.code) {
          this.selectArticle = this.$route.params.code;
        } else {
          this.selectArticle = null;
        }
      });

      for (const active in this.isActive) {
        this.isActive[active] = false;
      }
      if (group == "free") {
        this.title = "자유";
        this.isActive.free = true;
      } else if (group == "qna") {
        this.title = "QnA";
        this.isActive.qna = true;
      }

    },
    moveInfo(code) {
      this.$router.push(`/board/${this.group}/${code}`);
    },
  },
  watch: {
    '$route' () {
      location.reload();
    }
  }
};
</script>
