<template>
  <div>
    <b-container>
      <b-nav tabs fill class="mt-5">
        <b-nav-item to="/board/free" v-bind="{ active: isActive.free }">자유 게시판</b-nav-item>
        <b-nav-item to="/board/qna" v-bind="{ active: isActive.qna }">QnA 게시판</b-nav-item>
      </b-nav>
      <router-view
        :articles="articles"
        @changeGroup="changeGroup"
      ></router-view>
      <h1 class="mt-5">{{ title }} 게시판</h1>
      <div class="mt-5" align="center">
        <b-table
          hover
          fixed
          :items="articles"
          :fields="fields"
        >
          <template #cell(title)="row">
            <div @click="moveInfo(row.item.code)">
              {{ row.item.title }}
            </div>
          </template>
          <b-button>dd</b-button>
        </b-table>
      </div>
    </b-container>
  </div>
</template>

<script>
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
          key: "writer",
          label: "작성자",
        },
        {
          key: "title",
          label: "게시글",
        },
        {
          key: "created",
          label: "게시 날짜",
          sortable: true,
        },
      ],
      articles: [
        { code: 1, title: "게시글 1", created: "9999-09-01", writer: "유영" },
        { code: 2, title: "게시글 2", created: "9999-09-02", writer: "유영" },
        { code: 3, title: "게시글 3", created: "9999-09-03", writer: "박승수" },
        { code: 4, title: "게시글 4", created: "9999-09-04", writer: "박승수" },
      ],
    };
  },
  methods: {
    changeGroup(group) {
      this.group = group;
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
};
</script>
