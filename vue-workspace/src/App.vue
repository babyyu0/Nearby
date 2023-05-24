<template>
  <div id="app">
    <trip-header></trip-header>
    <router-view />
    </div>
</template>

<script>
import TripHeader from "@/components/common/TripHeader.vue";


export default {
  name: "App",
  components: {
    TripHeader,
  },
  methods: {
    isLogged() {
      this.$axios({
        url: "member/get-logged-member",
      }).then((response) => {
        if (response.data == "") {
          this.$store.commit('logout');
        } else {
          this.$store.commit('login', { id: response.data.id, name: response.data.name });
        }
      });
    },
  },
  created() {
    this.isLogged();
  },
  watch: {
    "$store.state.id": function () {
      this.isLogged();
    }
  }
};
</script>

<style>
#app {
  font-family: "NanumSquareNeo-Variable";
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

.font-hyun {
  font-family: "UhBeeSe_hyun";
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
