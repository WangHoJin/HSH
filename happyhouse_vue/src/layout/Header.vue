<template>
  <header>
    <nav id="nav" class="navbar navbar-expand-sm bg-light fixed-top shadow">
      <div class="container">
        <!-- <button
          class="navbar-toggler navbar-toggler-right bg-secondary text-white"
          type="button"
          data-toggle="collapse"
          data-target="#navb"
        >
          <span class="navbar-toggler-icon">-</span>
        </button> -->
        <div id="navb" class="collapse navbar-collapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item menu_confirm_on">
              <router-link class="nav-link" to="/"> HOME </router-link>
            </li>
            <li v-if="getUserId != null" class="nav-item menu_confirm_on">
              <router-link class="nav-link" to="/addinterest">관심 지역 추가</router-link>
            </li>
            <!-- 로그인 후 -->
            <li class="nav-item menu_confirm_on">
              <router-link class="nav-link" to="/list">Q&A 게시판</router-link>
            </li>
            <li class="nav-item menu_confirm_on">
              <router-link class="nav-link" to="/housesearch">검색</router-link>
            </li>
          </ul>

          <!-- 로그인 전 -->
          <div v-if="getUserId == null">
            <ul id="header_nav_confirm_off" class="navbar-nav justify-content-end">
              <li class="nav-item">
                <router-link class="nav-link" to="/login">로그인</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/regist">회원가입</router-link>
              </li>
            </ul>
          </div>
          <!-- 로그인 후 -->
          <div v-else>
            <ul id="header_nav_confirm_on" class="navbar-nav justify-content-end">
              <li class="nav-item">
                <a class="nav-link">{{ getUserId }}님 환영합니다!</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="logout" @click="logout">로그아웃</a>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/modify"> 회원정보</router-link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
    <!-- <div style="height: 100px"></div> -->
  </header>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "AppHeader",
  data() {
    return {
      d: null,
    };
  },
  computed: {
    ...mapGetters(["getUserId", "getUserName"]),
  },
  methods: {
    ...mapMutations(["mLogout"]),
    ...mapActions(["aLogout"]),
    logout() {
      if (this.userid != "") {
        let msg = "로그아웃";
        alert(msg);
        this.aLogout();
      }
    },
  },
};
</script>

<style>
@import "../assets/css/main.css";
</style>
