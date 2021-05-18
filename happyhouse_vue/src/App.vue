<template>
  <div id="app" class="container">
    <nav-header></nav-header>
    <h2 class="text-center">Vue를 이용한 게시판</h2>
    <router-view />
  </div>
</template>
<script>
import NavHeader from './layout/Header.vue';

export default {
  name: 'App',
  components: {
    NavHeader,
  },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
