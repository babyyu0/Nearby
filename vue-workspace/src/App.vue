<template>
  <div id="app">
    <trip-header></trip-header>
    <router-view />
    <KakaoMap />
  </div>
</template>

<script>
import TripHeader from "@/components/common/TripHeader.vue";

export default {
  name: 'App',
  components: {
    TripHeader,
  }

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif, 'NanumSquareNeo-Variable';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
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
