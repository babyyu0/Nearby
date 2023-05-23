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
  created() {
    this.$axios({
      url: "http://localhost:9999/member/get-logged-member",
      method: "POST"
    }).then((response) => {
      console.log(response.data);
    });
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
