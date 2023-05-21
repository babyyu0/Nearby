<template>
    <div>
      <b-container>
        <board-header :type="type"></board-header>
        <h1 class="mt-5">{{ title }} 게시판</h1>
        <div class="mt-5" align="center">
            <b-table :fields="fields" :items="boards" v-if="boards.length > 0">
                <template #cell(title)="row">
                    <div @click="moveInfo(row.item.code)" v-text="row.item.title">
                    </div>
                </template>
            </b-table>
            <div v-else>게시물이 존재하지 않습니다.</div>
        </div>
      </b-container>
    </div>
</template>

<script>
import axios from "axios";
import BoardHeader from "@/components/common/BoardHeader.vue";

export default {
    components: {
        BoardHeader
    },
    data() {
        return {
            type: "",
            title: "",
            page: {
                pageNum: 1,
                pageSize: 20
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
            boards: [],
        };
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
            axios({
                url: "http://localhost:9999/board/list",
                method: "post",
                params: { type: this.type, pageNum: this.page.pageNum, pageSize: this.page.pageSize },
            }).then((response) => {
                this.boards = response.data.list;
            }).catch(() => {
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
        $route: function() {
            this.changeType();
        }
    }
}
</script>

<style scoped>

</style>