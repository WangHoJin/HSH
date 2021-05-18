<template>
  <header>
    <nav class="navbar navbar-expand-sm bg-light fixed-top shadow">
      <div id="nav">
        <router-link to="/">Home</router-link> |
        <router-link to="/list">게시판</router-link>
      </div>
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
            <li class="nav-item">
              <a class="nav-link text-secondary" href="#">공지사항</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="#">오늘의 뉴스</a>
            </li>

            <!-- 로그인 후 -->
            <li class="nav-item menu_confirm_on">
              <a
                class="nav-link text-secondary"
                href="#InterestArea"
                data-toggle="modal"
                id="interest"
                >관심 지역 추가</a
              >
            </li>
          </ul>

          <!-- 로그인 전 -->
          <ul id="header_nav_confirm_off" class="navbar-nav justify-content-end">
            <li class="nav-item">
              <a
                class="nav-link text-secondary"
                href="#"
                id="login"
                data-toggle="modal"
                data-target="#loginModal"
                >로그인</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="#voteModal" data-toggle="modal">회원가입</a>
            </li>
          </ul>

          <!-- 로그인 후 -->
          <ul id="header_nav_confirm_on" class="navbar-nav justify-content-end">
            <li class="nav-item">
              <a class="nav-link text-secondary">${userinfo.username}님 환영합니다!</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="${root}/member/logout" id="logout"
                >로그아웃</a
              >
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
  </header>
</template>

<script>
export default {
  name: "AppHeader",
};
</script>
