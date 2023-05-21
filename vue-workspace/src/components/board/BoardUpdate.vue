<template>
    <div>
      <b-container>
        <board-header :type="type"></board-header>
        <div class="m-5">
            <!-- 제목 -->
            <b-row class="align-items-center" align-h="between">
                <b-col cols="8" align="left"><b-form-input placeholder="제목을 입력하세요..." v-model="board.title"/></b-col>
                <b-col cols="4">{{ board.createdAt }}</b-col>
            </b-row>

            <!-- 작성자 -->
            <b-row class="mt-3 align-items-center" align-h="between">
                <b-col cols="8" align="left">
                    <b-avatar size="sm" src="https://placekitten.com/300/300" class="mr-1"></b-avatar>
                    {{ board.writerId }}
                </b-col>
            </b-row>

            <div class="mt-4" align="left" style="min-height: 400px">
                <b-form-textarea placeholder="내용을 입력하세요..." v-model="board.contents"  style="min-height: 400px"/>
            </div>
            <b-row class="mt-4" align-h="between">
                <b-col cols="4"></b-col>
                <b-col cols="4"><b-button class="mr-1" variant="primary" @click="updateBoard">수정 완료</b-button></b-col>
                <b-col cols="4"></b-col>
            </b-row>
        </div>
      </b-container>
        <b-modal id="bv-modal-modify" title="알림" ok-only centered>
        <p class="my-4">{{ modalMsg }}</p>
        </b-modal>
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
            modalMsg: "",
            type: "",
            board: {},
        };
    },
    methods: {
        getArticle() {
            this.type = this.$route.params.type;
            axios({
                url: "http://localhost:9999/board/view",
                method: "post",
                params: { code: this.$route.params.code, type: this.type },
            }).then((response) => {
                this.board = response.data;
            });
        },
        updateBoard() {
            this.board.type = this.type;
            axios({
                url: "http://localhost:9999/board/update",
                method: "post",
                params: {
                    type: this.type,
                    code: this.board.code,
                    title: this.board.title,
                    contents: this.board.contents,
                },
            }).then((response) => {
                if (response.data == 1) {
                    this.modalMsg = "수정 완료.";
                    this.$bvModal.show('bv-modal-modify');
                    this.$router.go(-1);
                } else {
                    this.modalMsg = "수정 오류!";
                    this.$bvModal.show('bv-modal-modify');
                }
            });
        }
    },
    created() {
        this.getArticle();
    },
    watch: {
        $route: function() {
            this.getArticle();
        },

    }
}
</script>

<style scoped>

</style>