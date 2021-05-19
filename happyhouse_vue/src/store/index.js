import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: { userid: null, username: "" },
  getters: {
    getUserId(state) {
      return state.userid;
    },
    getUserName(state) {
      return state.username;
    },
  },
  actions: {
    asyncLogin(context, id) {
      context.commit("M_LOGIN", id);
    },
    aLogout(context) {
      context.commit("mLogout");
    },
  },
  mutations: {
    M_LOGIN(state, id) {
      state.userid = id;
    },
    mLogout(state) {
      state.userid = null;
    },
  },
});
