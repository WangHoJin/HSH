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
            </section>
          </div>
          <div id="icondetail" class="col-12" v-show="showcoffee">
            <!-- Features -->
            <section class="box features">
              <h2 class="major"><span>A Major Heading</span></h2>
              <div class="row">
                <div class="col-6">
                  <bar-chart v-if="loaded" :chartdata="chartdata" :options="options"></bar-chart>
                </div>
                <div class="col-6 text-center">
                  <h2 style="margin-bottom: 2em">{{ topdong }} 카페 순위</h2>
                  <div
                    v-for="(cafe, index) in cafes"
                    :key="`${index}_cafes`"
                    style="display: inline-block; padding: 2em 1em"
                  >
                    <h2>{{ index + 1 }}. {{ cafe.cname }}</h2>

                    <img
                      class="cafelogo"
                      :src="require(`@/assets/css/images/${cafeEngName[index]}.png`)"
                    />
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/util/http-common";
import BarChart from "@/components/Charts/BarChart.vue";
export default {
  components: { BarChart },
  name: "index",
  component: {
    BarChart,
  },
  data() {
    return {
      loaded: false,
      showcoffee: false,
      topdong: "",
      chartdata: null,
      options: null,
      cafes: [],
      cafeEngName: [],
    };
  },
  async mounted() {
    const labels = [];
    const datas = [];
    http
      .get("/coffeeshop/dongrank")
      .then(({ data }) => {
        for (let index = 0; index < 3; index++) {
          labels.push(data[index].dong);
          datas.push(data[index].cnt);
        }
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
    this.fillData(labels, datas);
  },
  methods: {
    fillData(l, d) {
      this.chartdata = {
        labels: l,
        datasets: [
          {
            label: "카페 많은 동네 TOP 3",
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 205, 86, 0.2)",
            ],
            borderColor: ["rgb(255, 99, 132)", "rgb(255, 159, 64)", "rgb(255, 205, 86)"],
            data: d,
          },
        ],
      };

      this.options = {
        onClick: this.handle,
      };
    },
    handle(point, event) {
      console.log(point);
      const item = event[0];
      this.topdong = item._model.label;

      console.log(item._model.label);
      http
        .get("/coffeeshop/coffeeshoprank/" + this.topdong)
        .then(({ data }) => {
          console.log(data);
          console.log(data[0].cname);
          this.cafes = data;
          for (let i = 0; i < 5; i++) {
            if (this.cafes[i].cname === "스타벅스") {
              this.cafeEngName[i] = "starbucks";
            } else if (this.cafes[i].cname === "이디야커피") {
              this.cafeEngName[i] = "ediya";
            } else if (this.cafes[i].cname === "투썸플레이스") {
              this.cafeEngName[i] = "twosome";
            } else if (this.cafes[i].cname === "커피빈") {
              this.cafeEngName[i] = "coffeebean";
            } else if (this.cafes[i].cname === "빽다방") {
              this.cafeEngName[i] = "paikscoffee";
            } else if (this.cafes[i].cname === "파스쿠찌") {
              this.cafeEngName[i] = "pascucci";
            } else if (this.cafes[i].cname === "할리스커피") {
              this.cafeEngName[i] = "hollys";
            } else if (this.cafes[i].cname === "커피에반하다") {
              this.cafeEngName[i] = "cuban";
            } else if (this.cafes[i].cname === "탐앤탐스") {
              this.cafeEngName[i] = "tomtom";
            } else if (this.cafes[i].cname === "메가엠지씨커피") {
              this.cafeEngName[i] = "mega";
            } else if (this.cafes[i].cname === "달콤") {
              this.cafeEngfName[i] = "dalkomm";
            } else {
              this.cafeEngName[i] = "profile";
            }
          }

          if (this.cafes.cname === "스타벅스") {
            console.log(this.cafes.cname);
            this.cafes.cname = "starbucks";
          }
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    showCoffeeShop() {
      this.showcoffee = !this.showcoffee;
      this.loaded = true;
    },
  },
};
</script>
<style>
.small {
  width: 50%;
  max-width: 600px;
  display: inline-block;
  text-align: center;
}
.cafelogo {
  width: 100px;
  height: 100px;
}
</style>
