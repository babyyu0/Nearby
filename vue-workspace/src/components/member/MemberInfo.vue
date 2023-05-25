<template>
    <b-container>
        <h1 class="mt-5">회원 정보</h1>
        <b-avatar class="mt-5" size="10rem" v-if="member.profileImg" :src="member.profileImg" onclick="document.getElementById('input-file').click()"></b-avatar>
        <b-avatar class="mt-5" size="10rem" v-else onclick="document.getElementById('input-file').click()"></b-avatar>
        <b-form-file id="input-file" type="file" v-model="uploadImg" v-show="false" />
        <h3 class="mt-3">{{ member.name }}</h3>
        <b-card class="mt-5" align="left" header="기본 정보">
            <b-list-group flush>
                <b-list-group-item><b-row>
                    <b-col cols="2">아이디</b-col>
                    <b-col>{{ member.id }}</b-col>
                </b-row></b-list-group-item>
                <b-list-group-item><b-row>
                    <b-col cols="2">비밀번호</b-col>
                    <b-col><b-button>비밀번호 변경</b-button></b-col>
                </b-row></b-list-group-item>
            </b-list-group>
        </b-card>
        <b-card class="mt-3" align="left" header="개인 정보">
            <b-list-group flush>
                <b-list-group-item><b-row>
                    <b-col cols="2">이름</b-col>
                    <b-col>{{ member.name }}</b-col>
                </b-row></b-list-group-item>
                <b-list-group-item><b-row>
                    <b-col cols="2">이메일</b-col>
                    <b-col>{{ member.email }}</b-col>
                </b-row></b-list-group-item>
            </b-list-group>
        </b-card>
        <b-card class="mt-3 mb-5" align="left" header="지역 정보">
            <b-list-group flush>
                <b-list-group-item><b-row>
                    <b-col cols="2">지역</b-col>
                    <b-col>{{ member.sidoVO.sidoName }}</b-col>
                </b-row></b-list-group-item>
                <b-list-group-item><b-row>
                    <b-col cols="2">시 · 군 · 구</b-col>
                    <b-col>{{ member.gugunVO.gugunName }}</b-col>
                </b-row></b-list-group-item>
            </b-list-group>
        </b-card>
    </b-container>
</template>

<script>
export default {
    data() {
        return {
            uploadImg: [],
            member: {
                sidoVO: {
                    sidoName: "",
                },
                gugunVO: {
                    gugunName: ""
                },
                profileImg: "",
            },
        };
    },
    created() {
        if (!this.$store.state.member.logged) {
            this.$router.push("/");
        }

      this.$axios({
        url: "member/get-logged-member",
      }).then((response) => {
          this.member = response.data;
      });
    },
    watch: {
        "$store.state.member.logged": function () {
            if (!this.$store.state.member.logged) {
                this.$router.push("/");
            }
        },
        "uploadImg": function () {
            console.log(this.uploadImg);
            this.$axios({
                url: "member/upload-profile",
                headers: {
                    "Content-Type": "multipart/form-data"
                },
                data: { profileImg: this.uploadImg }
            }).then((response) => {
                this.member.profileImg = response.data;
                this.$store.commit("setProfile", this.member.profileImg);
            });
        }
    }
}
</script>