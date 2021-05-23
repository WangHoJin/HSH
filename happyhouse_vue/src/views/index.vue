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
                  <a href="#icondetail" @click="showCoffeeShop" class="icon solid fa-coffee"
                    ><router-link to="/chartjs"></router-link>
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
              <div>
                <bar-chart style="height: 400px"></bar-chart>

                <!-- <chartjs-bar :labels="labels" :datasets="datasets"></chartjs-bar> -->
              </div>
            </section>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { Bar } from "vue-chartjs";
import BarChart from "../components/Charts/BarChart.vue";
export default {
  components: { BarChart },
  name: "index",
  extends: Bar,
  component: {
    BarChart,
  },
  data: function () {
    return {
      showcoffee: false,

      // labels: ["January", "February", "March", "April", "May", "June", "July"],
      // datasets: [
      //   {
      //     data: [65, 59, 80, 81, 56, 55, 40],
      //     backgroundColor: [
      //       "rgba(255, 99, 132, 0.2)",
      //       "rgba(255, 159, 64, 0.2)",
      //       "rgba(255, 205, 86, 0.2)",
      //       "rgba(75, 192, 192, 0.2)",
      //       "rgba(54, 162, 235, 0.2)",
      //       "rgba(153, 102, 255, 0.2)",
      //       "rgba(201, 203, 207, 0.2)",
      //     ],
      //     borderColor: [
      //       "rgb(255, 99, 132)",
      //       "rgb(255, 159, 64)",
      //       "rgb(255, 205, 86)",
      //       "rgb(75, 192, 192)",
      //       "rgb(54, 162, 235)",
      //       "rgb(153, 102, 255)",
      //       "rgb(201, 203, 207)",
      //     ],
      //   },
      // ],
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
