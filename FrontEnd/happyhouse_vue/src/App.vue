<template>
  <div id="app">
    <nav-header></nav-header>
    <!-- <div style="height: 80px"></div> -->
    <router-view />
    <my-footer></my-footer>
  </div>
</template>
<script>
import NavHeader from "./layout/Header.vue";
import MyFooter from "./layout/Footer.vue";

export default {
  name: "App",
  components: {
    NavHeader,
    MyFooter,
  },
};
</script>
<style>
@import "/assets/css/main.css";
@import "https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css";
@import "https://fonts.googleapis.com/css?family=Montserrat:400,700";
@import "https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic";
/* @import "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"; */
/* @import "https://use.fontawesome.com/releases/v4.7.0/js/all.js"; */
/* @import "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"; */
@import "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css";
/* @import "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"; */
</style>
