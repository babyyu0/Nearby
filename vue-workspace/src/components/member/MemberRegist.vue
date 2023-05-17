<template>
  <b-container>
    <h1 class="mt-5">회원가입</h1>
    <div class="mt-4">
      <b-card class="m-auto" style="max-width: 40rem">
        <div class="mt-2 ml-2 text-left">아이디</div>
        <b-input-group class="mt-2">
          <b-form-input placeholder="아이디를 입력해 주세요."></b-form-input>
          <b-input-group-append>
            <b-button variant="secondary">중복 확인</b-button>
          </b-input-group-append>
        </b-input-group>

        <div class="mt-4 ml-2 text-left">비밀번호</div>
        <b-form-input
          class="mt-2"
          placeholder="비밀번호를 입력해 주세요."
        ></b-form-input>
        <b-form-input class="mt-2" placeholder="비밀번호 확인"></b-form-input>

        <div class="mt-4 ml-2 text-left">이름</div>
        <b-form-input
          class="mt-2"
          placeholder="이름을 입력해 주세요."
        ></b-form-input>

        <div class="mt-4 ml-2 text-left">이메일</div>
        <b-form-input
          class="mt-2"
          placeholder="이메일을 입력해 주세요."
        ></b-form-input>

        <b-row>
          <b-col>
            <div class="mt-4 ml-2 text-left">지역</div>
            <b-form-select v-model="selectSido" @change="changeGugun">
              <b-form-select-option
                v-for="(sido, index) in sidos"
                :key="index"
                :value="sido.sidoCode"
                >{{ sido.sidoName }}</b-form-select-option
              >
            </b-form-select>
          </b-col>
          <b-col>
            <div class="mt-4 ml-2 text-left">시 · 군 · 구</div>
            <b-form-select v-model="selectGugun">
              <b-form-select-option
                v-for="(gugun, index) in guguns"
                :key="index"
                :value="gugun.gugunCode"
                >{{ gugun.gugunName }}</b-form-select-option
              >
            </b-form-select>
          </b-col>
        </b-row>

        <div class="mt-5">
          <b-button button-right variant="primary">로그인</b-button>
        </div>
      </b-card>
    </div>
    <div class="mt-2">
      <b-nav align="center">
        <b-nav-item to="/member/login">이미 아이디가 있으신가요?</b-nav-item>
      </b-nav>
    </div>
  </b-container>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      sidos: {},
      guguns: {},
      selectSido: 1,
      selectGugun: 1,
    };
  },
  methods: {
    changeGugun() {
      axios({
        url: "http://localhost:9999/trip/get-gugun",
        method: "post",
		params: { sidoCode: this.selectSido },
      }).then((response) => {
        console.log(response);
        this.guguns = response.data;
      });
    },
  },
  created() {
    axios({
      url: "http://localhost:9999/trip/get-region",
		method: "post",
    }).then((response) => {
      this.sidos = response.data.sido;
      this.guguns = response.data.gugun;
    });
  },
  mounted() {},
};
</script>
