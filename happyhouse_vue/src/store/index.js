import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: { userid: null, username: "", aptname: "", apts: [] },
  getters: {
    getUserId(state) {
      return state.userid;
    },
    getUserName(state) {
      return state.username;
    },
    getAptName(state) {
      return state.aptname;
    },
    getAptList(state) {
      return state.apts;
    },
  },
  actions: {
    asyncLogin(context, id) {
      context.commit("M_LOGIN", id);
    },
    aLogout(context) {
      context.commit("mLogout");
    },
    getAptName({ commit }, aptname) {
      commit("GET_APT_NAME", aptname);
    },
    getAptList({ commit }, aptlist) {
      commit("GET_APT_LIST", aptlist);
    },
  },
  mutations: {
    M_LOGIN(state, id) {
      state.userid = id;
    },
    mLogout(state) {
      state.userid = null;
    },
    GET_APT_NAME(state, aptname) {
      state.aptname = aptname;
    },
    GET_APT_LIST(state, aptlist) {
      state.apts = aptlist;
    },
  },
});
