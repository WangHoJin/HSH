import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: { userid: null, username: "", apt: {}, aptdetail: [] },
  getters: {
    getUserId(state) {
      return state.userid;
    },
    getUserName(state) {
      return state.username;
    },
    getApt(state) {
      return state.apt;
    },
    getAptList(state) {
      return state.aptdetail;
    },
  },
  actions: {
    asyncLogin(context, id) {
      context.commit("M_LOGIN", id);
    },
    aLogout(context) {
      context.commit("mLogout");
    },
    setApt({ commit }, apt) {
      commit("SET_APT", apt);
    },
    setAptList({ commit }, aptdetail) {
      commit("SET_APT_LIST", aptdetail);
    },
  },
  mutations: {
    M_LOGIN(state, id) {
      state.userid = id;
    },
    mLogout(state) {
      state.userid = null;
    },
    SET_APT(state, apt) {
      state.apt = apt;
    },
    SET_APT_LIST(state, aptdetail) {
      state.aptdetail = aptdetail;
    },
  },
});
