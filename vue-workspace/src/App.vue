<template>
  <div id="app">
    <trip-header :member="member"></trip-header>
    <router-view  @setMember="setMember"/>
    <b-modal id="bv-modal" title="알림" ok-only centered>
      <p class="my-4">{{ modalMsg }}</p>
  </b-modal>
  </div>
</template>

<script>
import TripHeader from "@/components/common/TripHeader.vue";

export default {
  name: "App",
  data() {
    return {
      modalMsg: "",
      member: {
        id: "",
        name: "",
      }
    };
  },
  components: {
    TripHeader,
  },
  methods: {
    setMember(member) {
      if (member.name != "") {
        this.member = member;
        this.$router.push("/");
      } else {
        this.modalMsg = "아이디 혹은 비밀번호가 일치하지 않습니다!";
        this.$bvModal.show('bv-modal');
      }
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif, "NanumSquareNeo-Variable";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  -ms-user-select: none;
  -moz-user-select: -moz-none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  user-select: none;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
