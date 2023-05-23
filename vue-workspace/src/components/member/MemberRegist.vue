<template>
  <b-container>
    <h1 class="mt-5">회원가입</h1>
    <div class="mt-4" role="group">
      <b-card class="m-auto" style="max-width: 40rem">

        <!-- 아이디 -->
        <div class="mt-2 ml-2 text-left">아이디</div>
        <b-form-group :state="validCheck.id" :invalid-feedback="validCheck.idFeedback">
          <b-input-group class="mt-2">
            <b-form-input v-model="member.id" placeholder="아이디를 입력해 주세요." :state="validCheck.id" trim></b-form-input>
            <b-input-group-append>
              <b-button variant="secondary" @click="idValid">중복 확인</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>

        <!-- 비밀번호 -->
        <div class="mt-4 ml-2 text-left">비밀번호</div>
        <b-form-group :state="validCheck.password" invalid-feedback="8-16자의 영문 대문자, 소문자, 숫자와 특수문자(~, !, @, #, $, %, ^, &, *, _, -)를 사용하여 입력해 주세요.">
          <b-form-input type="password" v-model="member.password" class="mt-2" placeholder="비밀번호를 입력해 주세요." :state="validCheck.password" trim></b-form-input>
        </b-form-group>
        <b-form-group :state="validCheck.passwordCheck" invalid-feedback="비밀번호와 일치하지 않습니다.">
          <b-form-input type="password" v-model="member.passwordCheck" class="mt-2" placeholder="비밀번호 확인" :state="validCheck.passwordCheck" trim></b-form-input>
        </b-form-group>

        <!-- 이름 -->
        <div class="mt-4 ml-2 text-left">이름</div>
        <b-form-input v-model="member.name" class="mt-2" placeholder="이름을 입력해 주세요." trim></b-form-input>

        <!-- 이메일 -->
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
    <b-modal id="bv-modal-regist" title="알림" ok-only centered>
      <p class="my-4">{{ modalMsg }}</p>
    </b-modal>
  </b-container>
</template>

<script>


export default {
  data() {
    return {
      modalMsg: "",
      sidos: {},
      guguns: {},
      validCheck: {
        id: null,
        idFeedback: "",
        password: null,
        passwordCheck: null,

        email: null,
        emailFeedback: "이메일 형식을 확인해 주세요."
      },
      member: {
        id: "",
        password: "",
        passwordCheck: "",
        name: "",
        email: "",
        sidoCode: 1,
        gugunCode: 1
      }
    };
  },
  methods: {
    changeGugun() {
      this.$axios({
        url: "http://localhost:9999/trip/get-gugun",
        method: "post",
        params: { sidoCode: this.member.sidoCode },
      }).then((response) => {
        this.guguns = response.data;
      });
    },
    idValid() {
      if (this.member.id.length < 4) {
        this.validCheck.id = false;
        this.validCheck.idFeedback = "아이디를 4자 이상 입력해 주세요.";
        return;
      }
      this.$axios({
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
      this.$axios({
        url: "http://localhost:9999/member/regist",
        method: "post",
        params: { id: this.member.id, password: this.member.password, name: this.member.name, email: this.member.email, sidoCode: this.member.sidoCode, gugunCode: this.member.gugunCode },
      }).then((response) => {
        if (response.data == "ok") {
          this.modalMsg = "회원가입이 완료 되었습니다.";
          this.$bvModal.show('bv-modal-regist');
        } else {
          this.modalMsg = "회원가입을  실패하였습니다.";
          this.$bvModal.show('bv-modal-regist');
        }
      });
    }
  },
  created() {
    this.$axios({
      url: "http://localhost:9999/trip/get-region",
      method: "post",
    }).then((response) => {
      this.sidos = response.data.sido;
      this.guguns = response.data.gugun;
    });
  },
  watch: {
    'member.id': function (value) {
      if (value.length < 4) {
        this.validCheck.id = false;
        this.validCheck.idFeedback = "아이디를 4자 이상 입력해 주세요.";
      } else {
        this.validCheck.id = null;
      }
    },
    'member.password': function (value) {
      this.member.password = value.replace(/[^0-9a-zA-Z~!@#$%^&*_-]/g, "");
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
    },
    'member.email': function (value) {
      let emailReg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*_-])[A-Za-z0-9~!@#$%^&*_-]{8,16}$/;

      if (!emailReg.test(value)) {
        this.validCheck.email = false;
      } else {
        this.validCheck.email = true;
      }
    }
  }
};
</script>
