<template>
    <div>
      <b-container>
        <board-header :type="type"></board-header>
        <b-card border-variant="light">
            <b-card-header>
                <!-- 제목 -->
                <b-row align-h="between">
                    <b-col cols="8" align="left"><b>제목 : {{ article.title }}</b></b-col>
                    <b-col cols="4">{{ article.createdAt }}</b-col>
                </b-row>
            </b-card-header>

            <b-card-body>
                <!-- 작성자 -->
                <b-row>
                    <b-col cols="8" align="left">
                        <b-avatar size="sm" src="https://placekitten.com/300/300" class="mr-1"></b-avatar>
                        {{ article.writerId }}
                    </b-col>
                </b-row>

                <div class="mt-4" align="left" style="min-height: 400px">
                    {{ article.contents }}
                </div>
            </b-card-body>
        </b-card>
        <div class="m-5">
            <b-row class="mt-4" align-h="between">
                <b-col cols="4" align="left"><!--공감 : 추후 구현--></b-col>
                <b-col cols="4"><b-button @click="$router.go(-1)" variant="outline-secondary">목록으로</b-button></b-col>
                <b-col cols="4" align="right">
                    <b-button class="mr-1" @click="$router.push($route.path + '/modify')" variant="outline-primary">수정</b-button>
                    <b-button variant="secondary" @click="deleteBoard">삭제</b-button>
                </b-col>
            </b-row>
        </div>
      </b-container>
      <alert-modal :modalMsg="modalMsg"></alert-modal>
    </div>
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
            code: this.$route.params.code,
            modalMsg: "",
            type: "",
            article: {},
        };
    },
    methods: {
        getBoard() {
            this.type = this.$route.params.type;
            this.$axios({
                url: "http://localhost:9999/board/view",
                method: "post",
                params: { code: this.code, type: this.type },
            }).then((response) => {
                this.article = response.data;
            });
        },
        deleteBoard() {
            if (this.$store.state.id != this.article.writerId) {
                this.modalMsg = "삭제할 수 있는 권한이 없습니다.";
                this.$bvModal.show('bv-modal');
                return;
            }
            this.$axios({
                url: "http://localhost:9999/board/delete",
                method: "post",
                params: { code: this.code, type: this.type },
            }).then((response) => {
                console.log(response.data);
            });
        }
    },
    created() {
        this.getBoard();
    },
    code: {
        $route: function() {
            this.getBoard();
        }
    }
}
</script>