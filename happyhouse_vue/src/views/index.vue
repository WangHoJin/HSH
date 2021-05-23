<template>
  <div>
    <!-- Banner -->
    <section id="banner">
      <div class="content">
        <h2>Welcome to TXT by HTML5 UP</h2>
        <p>A free responsive site template built on HTML5, CSS3, and some other stuff</p>
        <a href="#main" class="button scrolly">Alright let's go</a>
      </div>
    </section>

    <!-- Main -->
    <section id="main">
      <div class="container">
        <div class="row gtr-200">
          <div class="col-12">
            <!-- Highlight -->
            <section class="box highlight">
              <ul class="special">
                <li>
                  <a href="#icondetail" @click="showCoffeeShop" class="icon solid fa-coffee">
                    <span class="label">Magnifier</span></a
                  >
                </li>
                <li>
                  <a href="#icondetail" class="icon solid fa-shopping-cart"
                    ><span class="label">Tablet</span></a
                  >
                </li>
                <li>
                  <a href="#icondetail" class="icon solid fa-subway"
                    ><span class="label">Flask</span></a
                  >
                </li>
                <li>
                  <a href="#icondetail" class="icon solid fa-hospital"
                    ><span class="label">Cog?</span></a
                  >
                </li>
              </ul>
              <header>
                <h2>A random assortment of icons in circles</h2>
                <p>And some text that attempts to explain their significance</p>
              </header>
              <p>
                Phasellus quam turpis, feugiat sit amet ornare in, hendrerit in lectus. Praesent
                semper mod quis eget mi. Etiam eu<br />
                ante risus. Aliquam erat volutpat. Aliquam luctus et mattis lectus amet pulvinar.
                Nam nec turpis consequat.
              </p>
            </section>
          </div>
          <div id="icondetail" class="col-12" v-show="showcoffee">
            <!-- Features -->
            <section class="box features">
              <h2 class="major"><span>A Major Heading</span></h2>
              <bar-chart></bar-chart>
              <ul>
                <li><router-link to="/chartjs">vue-chartjs</router-link></li>
              </ul>
            </section>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import BarChart from "../components/Charts/BarChart.vue";
export default {
  name: "index",
  component: {
    BarChart,
  },
  data: function () {
    return {
      showcoffee: false,
    };
  },
  methods: {
    showCoffeeShop() {
      console.log("뭐야");
      this.showcoffee = true;
      console.log(this.showcoffee);
    },
  },
};
</script>
