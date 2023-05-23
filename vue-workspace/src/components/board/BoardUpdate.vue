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
                <b-col cols="4">
                    <b-button class="mr-1" variant="primary" @click="updateBoard" v-if="this.board.code">수정 완료</b-button>
                    <b-button class="mr-1" variant="primary" @click="writeBoard" v-else>글쓰기</b-button>
                </b-col>
                <b-col cols="4"></b-col>
            </b-row>
        </div>
      </b-container>
        <b-modal id="bv-modal-update" title="알림" ok-only centered>
        <p class="my-4">{{ modalMsg }}</p>
        </b-modal>
    </div>
</template>

<script>

import BoardHeader from "@/components/common/BoardHeader.vue";

export default {
    components: {
        BoardHeader
    },
    data() {
        return {
            modalMsg: "",
            type: this.$route.params.type,
            board: {
                code: this.$route.params.code,
                createdAt: "-",
                writerId: "추후 추가 요망"
            },
        };
    },
    methods: {
        getArticle() {
            this.$axios({
                url: "http://localhost:9999/board/view",
                method: "post",
                params: { code: this.board.code, type: this.type },
            }).then((response) => {
                this.board = response.data;
            });
        },
        updateBoard() {
            this.$axios({
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
        },
        writeBoard() {
            this.$axios({
                url: "http://localhost:9999/board/write",
                method: "post",
                params: {
                    code: 0,
                    type: this.type,
                    title: this.board.title,
                    writerId: this.$store.state.id,
                    contents: this.board.contents,
                },
            }).then((response) => {
                if (response.data == 1) {
                    this.modalMsg = "작성 완료.";
                    this.$bvModal.show('bv-modal-update');
                    this.$router.go(-1);
                } else {
                    this.modalMsg = "작성 오류!";
                    this.$bvModal.show('bv-modal-update');
                }
            });
        }
    },
    created() {
        console.log(this.board.code);
        if (!this.board.code) {  // 글 작성
            this.board.writerId = this.$store.state.name;
        } else {
            this.getArticle();
        }
    },
    // watch: {
    //     $route: function() {
    //         this.getArticle();
    //     },

    // }
}
</script>

<style scoped>

</style>