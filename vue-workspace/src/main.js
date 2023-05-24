import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueSession from 'vue-session'
import { ServerApi } from "@/api/axios";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "@/assets/fonts.css";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions);

Vue.config.productionTip = false;
const http = ServerApi();
Vue.prototype.$axios = http;

// Axios.defaults.withCredentials = true;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
