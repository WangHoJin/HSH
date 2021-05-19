<template>
  <header>
    <nav class="navbar navbar-expand-sm bg-light fixed-top shadow">
      <div class="container">
        <a class="navbar-brand" href="/">
          <img src="../img/logo.jpg" alt="Logo" style="width: 100px" />
        </a>
        <button
          class="navbar-toggler navbar-toggler-right bg-secondary text-white"
          type="button"
          data-toggle="collapse"
          data-target="#navb"
        >
          <span class="navbar-toggler-icon">-</span>
        </button>
        <div id="navb" class="collapse navbar-collapse">
          <ul class="navbar-nav mr-auto">
            <!-- 로그인 후 -->
            <li class="nav-item menu_confirm_on">
              <router-link class="nav-link text-secondary" to="/addinterest"
                >관심 지역 추가</router-link
              >
              <!-- <a
                class="nav-link text-secondary"
                href="#InterestArea"
                data-toggle="modal"
                id="interest"
                >관심 지역 추가</
              > -->
            </li>
            <li class="nav-item menu_confirm_on">
              <router-link class="nav-link text-secondary" to="/list">Q&A 게시판</router-link>
            </li>
            <li class="nav-item menu_confirm_on">
              <router-link class="nav-link text-secondary" to="/housesearch">검색</router-link>
            </li>
          </ul>

          <!-- 로그인 전 -->
          <ul id="header_nav_confirm_off" class="navbar-nav justify-content-end">
            <li class="nav-item">
              <router-link class="nav-link text-secondary" to="/login">로그인</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link text-secondary" to="/regist">회원가입</router-link>
            </li>
          </ul>

          <!-- 로그인 후 -->
          <ul id="header_nav_confirm_on" class="navbar-nav justify-content-end">
            <li class="nav-item">
              <a class="nav-link text-secondary">{{ username }}님 환영합니다!</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="" @click="logout">로그아웃</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="#searchModal" data-toggle="modal" id="view"
                >회원정보</a
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div style="height: 100px"></div>
  </header>
</template>

<script>
export default {
  name: "AppHeader",
  data() {
    return {
      username: sessionStorage.getItem("ID"),
    };
  },
  methods: {
    logout() {
      sessionStorage.clear();
      this.$router.push("/");
    },
    // sessionCheck() {
    //   if (sessionStorage.length == 0) {
    //     this.isSession = false;
    //     return this.isSession;
    //   }
    // },
  },
};
</script>
