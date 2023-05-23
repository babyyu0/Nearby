import Vue from "vue";
import App from "./App.vue";
import router from "./router";
// import store from "./store";
import Axios from 'axios';
import Vuex from "vuex";
import VueSession from 'vue-session'

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "@/assets/fonts.css";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
Vue.use(Vuex);

var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions);


const store = new Vuex.Store({
  state: {
    id: "",
    name: "",
    isLogin: false
  },
  mutations: {
    login(state, member) {
      state.isLogin = true;
      state.id = member.id;
      state.name = member.name;
    },
    logout() {
      this.isLogin = false;
      this.id = "";
      this.name = "";
    }
  }
});

Vue.config.productionTip = false;
Vue.prototype.$axios = Axios;

Axios.defaults.withCredentials = true;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
