import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    member: {
      logged: false,
      id: "",
      name: "",
    }
  },
  getters: {
  },
  mutations: {
    login(state, member) {
      state.member.logged = true;
      state.member.id = member.id;
      state.member.name = member.name;
    },
    logout(state) {
      state.member.logged = false;
      state.member.id = "";
      state.member.name = "";
    }
  },
  actions: {
  },
  modules: {
  }
})
