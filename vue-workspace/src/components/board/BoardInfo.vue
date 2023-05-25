<template>
      <b-container>
        <board-header :type="type"></board-header>
        <b-card border-variant="light">
            <b-card-header>
                <!-- 제목 -->
                <b-row align-h="between">
                    <b-col cols="8" align="left"><b>제목 : {{ board.title }}</b></b-col>
                    <b-col cols="4">{{ board.createdAt }}</b-col>
                </b-row>
            </b-card-header>

            <b-card-body>
                <!-- 작성자 -->
                <b-row>
                    <b-col cols="8" align="left">
                        <b-avatar size="sm" src="https://placekitten.com/300/300" class="mr-1"></b-avatar>
                        {{ board.writerId }}
                    </b-col>
                </b-row>

                <div class="mt-4" align="left" style="min-height: 400px">
                    {{ board.contents }}
                </div>
            </b-card-body>
        </b-card>
        <div class="m-5">
            <b-row class="mt-4" align-h="between">
                <b-col cols="4" align="left"><!--공감 : 추후 구현--></b-col>
                <b-col cols="4"><b-button @click="$router.push(`/board/${type}`)" variant="outline-secondary">목록으로</b-button></b-col>
                <b-col cols="4" align="right" v-if="board.writerId == $store.state.member.id">
                    <b-button class="mr-1" @click="moveModifyBoard" variant="outline-primary">수정</b-button>
                    <b-button variant="secondary" @click="deleteBoard">삭제</b-button>
                </b-col>
                <b-col cols="4" v-else></b-col>
            </b-row>
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
            code: this.$route.params.code,
            modalMsg: "",
            type: this.$route.params.type,
            board: {},
        };
    },
    methods: {
        getBoard() {
            this.$axios({
                url: "board/view",
                method: "post",
                data: { code: this.code, type: this.type },
            }).then((response) => {
                this.board = response.data;
            });
        },
        moveModifyBoard() {
            if (this.$store.state.member.id != this.board.writerId) {
                this.modalMsg = "수정할 수 있는 권한이 없습니다.";
                this.$bvModal.show('bv-modal');
                //return;
            } else {
                this.$router.push(`/board/${this.type}/${this.code}/modify`);
            }
        },
        deleteBoard() {
            if (this.$store.state.member.id != this.board.writerId) {
                this.modalMsg = "삭제할 수 있는 권한이 없습니다.";
                this.$bvModal.show('bv-modal');
                return;
            }
            this.$axios({
                url: "board/delete",
                method: "post",
                data: { code: this.code, type: this.type },
            }).then((response) => {
                if(response.data == "ok") {
                    this.modalMsg = "게시글이 삭제되었습니다.";
                    this.$bvModal.show('bv-modal');

                    this.$router.push(`/board/${this.type}`);
                } else {
                    this.modalMsg = "게시글이 삭제 오류!";
                    this.$bvModal.show('bv-modal');
                }
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