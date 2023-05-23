<template>
    <b-container>
        <h1 class="mt-5">회원 정보</h1>
        <b-card class="mt-5" align="left" header="기본 정보">
            <b-list-group flush>
                <b-list-group-item><b-row>
                    <b-col cols="2">아이디</b-col>
                    <b-col>1234</b-col>
                </b-row></b-list-group-item>
                <b-list-group-item><b-row>
                    <b-col cols="2">비밀번호</b-col>
                    <b-col>1234</b-col>
                </b-row></b-list-group-item>
            </b-list-group>
        </b-card>
        <b-card class="mt-3" align="left" header="개인 정보">
            <b-list-group flush>
                <b-list-group-item><b-row>
                    <b-col cols="2">이름</b-col>
                    <b-col>1234</b-col>
                </b-row></b-list-group-item>
                <b-list-group-item><b-row>
                    <b-col cols="2">이메일</b-col>
                    <b-col>1234</b-col>
                </b-row></b-list-group-item>
            </b-list-group>
        </b-card>
        <b-card class="mt-3" align="left" header="지역 정보">
            <b-list-group flush>
                <b-list-group-item><b-row>
                    <b-col cols="2">지역</b-col>
                    <b-col>1234</b-col>
                </b-row></b-list-group-item>
                <b-list-group-item><b-row>
                    <b-col cols="2">시 · 군 · 구</b-col>
                    <b-col>1234</b-col>
                </b-row></b-list-group-item>
            </b-list-group>
        </b-card>
    </b-container>
</template>

<script>
export default {
    data() {
        return {
            member: "",
        };
    },
    created() {
      this.$axios({
        url: "http://localhost:9999/member/get-logged-member",
        method: "post",
      }).then((response) => {
          console.log(response.data);
      });
    }
}
</script>