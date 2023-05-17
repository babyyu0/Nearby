<template>
    <b-container>
        <h1 class="mt-5">로그인</h1>
        <div class="mt-4">
            <b-card class="m-auto" style="max-width: 40rem;">
                <b-form-input class="mt-4" placeholder="아이디" v-model="id"></b-form-input>
                <b-form-input class="mt-2" placeholder="비밀번호" v-model="password" type="password"></b-form-input>
                <div class="mt-4">
                    <b-button variant="primary" @click="login">로그인</b-button>
                </div>
            </b-card>
            </div>
            <div class="mt-2">
            <b-nav align="center">
                <b-nav-item to="/member/find-pw">비밀번호 찾기</b-nav-item>
                <b-nav-item to="/member/regist">회원가입</b-nav-item>
            </b-nav>
        </div>
    </b-container>
</template>
<script>

export default {
    data() {
        return {
            id: "",
            password: ""
        };
    },
    methods: {
        login() {
            this.$emit("login", { id: this.id, password: this.password });
        }
    }
}
</script>