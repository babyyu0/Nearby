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
        <alert-modal :modal-msg="modalMsg"></alert-modal>
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
            modalMsg: "",
            type: this.$route.params.type,
            board: {
                code: this.$route.params.code,
                createdAt: "-",
                writerId: ""
            },
        };
    },
    methods: {
        getArticle() {
            this.$axios({
                url: "board/view",
                data: { code: this.board.code, type: this.type },
            }).then((response) => {
                this.board = response.data;
                if (this.$store.state.member.id != this.board.writerId) {
                    this.modalMsg = "수정할 수 있는 권한이 없습니다.";
                    this.$bvModal.show('bv-modal');

                    this.$router.push(`/board/${this.type}`);
                    return;
                }
            });
        },
        updateBoard() {
            this.$axios({
                url: "board/update",
                data: {
                    type: this.type,
                    code: this.board.code,
                    title: this.board.title,
                    contents: this.board.contents,
                },
            }).then((response) => {
                if (response.data == "ok") {
                    this.modalMsg = "수정 완료.";
                    this.$bvModal.show('bv-modal');

                    this.$router.push(`/board/${this.type}/${this.board.code}`);
                } else {
                    this.modalMsg = "수정 오류!";
                    this.$bvModal.show('bv-modal');
                }
            });
        },
        writeBoard() {
            if (!this.$store.state.member.logged) {
                this.modalMsg = "로그인된 사용자만 글을 작성할 수 있습니다.";
                this.$bvModal.show('bv-modal');
                return;
            }

            if (this.type == "location") {
                navigator.geolocation.getCurrentPosition(pos => {

                    this.$axios({
                        url: "board/write",
                        method: "post",
                        data: {
                            code: 0,
                            type: this.type,
                            title: this.board.title,
                            writerId: this.$store.state.member.id,
                            contents: this.board.contents,
                            latitude: pos.coords.latitude,
                            longitude: pos.coords.longitude
                        },
                    }).then((response) => {
                        if (response.data == "ok") {
                            this.modalMsg = "작성 완료.";
                            this.$bvModal.show('bv-modal-update');
                            
                            this.$router.push(`/board/${this.type}`);
                        } else {
                            this.modalMsg = "작성 오류!";
                            this.$bvModal.show('bv-modal-update');
                        }
                    });
                }, err => {
                    console.log(err.message);
                });
            } else {
                this.$axios({
                    url: "board/write",
                    method: "post",
                    data: {
                        code: 0,
                        type: this.type,
                        title: this.board.title,
                        writerId: this.$store.state.member.id,
                        contents: this.board.contents,
                    },
                }).then((response) => {
                    if (response.data == "ok") {
                        this.modalMsg = "작성 완료.";
                        this.$bvModal.show('bv-modal-update');
                        this.$router.push(`/board/${this.type}`);
                        
                    } else {
                        this.modalMsg = "작성 오류!";
                        this.$bvModal.show('bv-modal-update');
                    }
                });
            }

        }
    },
    created() {
        if (!this.$store.state.member.logged) {
            this.$router.push(`/board/${this.type}`);
        }

        if (!this.board.code) {  // 글 작성
            this.board.writerId = this.$store.state.member.name;
        } else {
            this.getArticle(); 
        }
    },
    watch: {
        "$store.state.member.logged": function () {
            if (!this.$store.state.member.logged) {
                this.$router.push(`/board/${this.type}`);
            }
        }
    }
}
</script>

<style scoped>

</style>