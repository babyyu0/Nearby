<template>
  <b-container>
    <h1 class="mt-5">회원가입</h1>
    <div class="mt-4" role="group">
      <b-card class="m-auto" style="max-width: 40rem">
        <div class="mt-2 ml-2 text-left">아이디</div>
        <b-form-group :state="validCheck.id" :invalid-feedback="validCheck.idFeedback">
          <b-input-group class="mt-2">
            <b-form-input v-model="member.id" placeholder="아이디를 입력해 주세요." :state="validCheck.id" trim></b-form-input>
            <b-input-group-append>
              <b-button variant="secondary" @click="duplicateCheck">중복 확인</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>

        <div class="mt-4 ml-2 text-left">비밀번호</div>
        <b-form-group :state="validCheck.password" :invalid-feedback="validCheck.passwordFeedback">
          <b-form-input type="password" v-model="member.password" class="mt-2" placeholder="비밀번호를 입력해 주세요." :state="validCheck.password" trim></b-form-input>
        </b-form-group>
        <b-form-group :state="validCheck.passwordCheck" :invalid-feedback="validCheck.passwordCheckFeedback">
          <b-form-input type="password" v-model="member.passwordCheck" class="mt-2" placeholder="비밀번호 확인" :state="validCheck.passwordCheck" trim></b-form-input>
        </b-form-group>

        <div class="mt-4 ml-2 text-left">이름</div>
        <b-form-input v-model="member.name" class="mt-2" placeholder="이름을 입력해 주세요." trim></b-form-input>

        <div class="mt-4 ml-2 text-left">이메일</div>
        <b-form-input v-model="member.email" class="mt-2" placeholder="이메일을 입력해 주세요." trim></b-form-input>

        <b-row>
          <b-col>
            <div class="mt-4 ml-2 text-left">지역</div>
            <b-form-select v-model="member.sidoCode" @change="changeGugun" trim>
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
            <b-form-select v-model="member.gugunCode" trim>
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
          <b-button button-right variant="primary" @click="regist">회원가입</b-button>
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
      validCheck: {
        id: null,
        idFeedback: "",

        password: null,
        passwordFeedback: "8-16자의 영문 대문자, 소문자, 숫자와 특수문자(~, !, @, #, $, %, ^, &, *, _, -)를 사용하여 입력해 주세요.",

        passwordCheck: null,
        passwordCheckFeedback: "비밀번호와 일치하지 않습니다.",

        email: null,
        emailFeedback: ""
      },
      member: {
        id: "",
        password: "",
        passwordCheck: "",
        email: "",
        sidoCode: 1,
        gugunCode: 1
      }
    };
  },
  methods: {
    changeGugun() {
      axios({
        url: "http://localhost:9999/trip/get-gugun",
        method: "post",
        params: { sidoCode: this.member.sidoCode },
      }).then((response) => {
        this.guguns = response.data;
      });
    },
    duplicateCheck() {
      if (this.member.id.length <= 4) {
        this.validCheck.id = false;
        this.validCheck.idFeedback = "아이디를 5자 이상 입력해 주세요.";
        return;
      }
      axios({
        url: "http://localhost:9999/member/duplicate-check",
        method: "post",
        params: { id: this.member.id },
      }).then((response) => {
        if (response.data == "") {
          this.validCheck.id = false;
          this.validCheck.idFeedback = "중복된 아이디입니다.";
        } else {
          this.validCheck.id = true;
        }
      });
    },
    regist() {

    }
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
  watch: {
    'member.password': function (value) {
      this.member.password = this.member.password.replace(/[^0-9a-zA-Z~!@#$%^&*_-]/g, "");
      console.log(this.member.password); 
      let pwReg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*_-])[A-Za-z0-9~!@#$%^&*_-]{8,16}$/;
      if (!pwReg.test(value)) {
        this.validCheck.password = false;
      } else {
        this.validCheck.password = true;
      }
    },
    'member.passwordCheck': function () {
      if (this.member.password != this.member.passwordCheck) {
        this.validCheck.passwordCheck = false;
      } else {
        this.validCheck.passwordCheck = true;
      }
    }
  }
};
</script>
