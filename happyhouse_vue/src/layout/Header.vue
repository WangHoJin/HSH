<template>
  <header>
    <nav>
      <div id="nav">
        <router-link to="/">Home</router-link> |
        <router-link to="/list">게시판</router-link>
      </div>
      <router-view />
    </nav>
  </header>
</template>

<script>
export default {
  name: 'AppHeader',
};
</script>
