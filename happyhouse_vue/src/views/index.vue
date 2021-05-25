<template>
  <div>
    <!-- Banner -->
    <section id="banner">
      <div class="content">
        <h2 style="color: #6b7770">당신이 원하는 집이 여기있습니다!</h2>
        <p>A free responsive site template built on HTML5, CSS3, and some other stuff</p>
        <a href="#main" class="button scrolly">Let's go</a>
      </div>
    </section>

    <!-- Main -->
    <section id="main">
      <div class="container">
        <div class="row gtr-200">
          <div class="col-12">
            <!-- Highlight -->
            <section class="box highlight">
              <h2 style="margin-bottom: 2em">우리집은 카세권? 편세권?</h2>
              <ul class="special">
                <li>
                  <a
                    href="#icondetail"
                    @mouseover="[changeCategory(0)]"
                    @mouseup="[showCafeRank()]"
                    class="icon solid fa-coffee"
                  >
                    <span class="label">Magnifier</span></a
                  >
                </li>
                <li>
                  <a
                    href="#icondetail"
                    class="icon solid fa-shopping-cart"
                    @mouseover="[changeCategory(1)]"
                    @mouseup="[showStoreRank()]"
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
          <div id="icondetail" class="col-12" v-show="showrank">
            <!-- Features -->
            <section class="box features">
              <h2 class="major"><span>명예의 전당</span></h2>
              <div class="row">
                <div class="col-6">
                  <bar-chart v-if="chartbar" :chartdata="chartdata" :options="options"></bar-chart>
                </div>
                <div class="col-6 text-center">
                  <div
                    v-for="(cafe, index) in cafes"
                    :key="`${index}_cafes`"
                    style="padding: 0.5em 1em"
                    v-show="cafeRank"
                  >
                    <!-- <h2 style="margin-bottom: 2em">{{ topdong }} 카페 순위</h2> -->
                    <h2 style="display: inline-block">{{ index + 1 }}. {{ cafe.cname }}</h2>

                    <img
                      class="cafelogo"
                      :src="require(`@/assets/css/images/${cafeEngName[index]}.png`)"
                    />
                  </div>
                  <div
                    v-for="(store, index) in stores"
                    :key="`${index}_stores`"
                    style="padding: 0.5em 1em"
                    v-show="storeRank"
                  >
                    <!-- <h2 style="margin-bottom: 2em">{{ topdong }} 편의점 순위</h2> -->
                    <h2 style="display: inline-block">{{ index + 1 }}. {{ store.sname }}</h2>

                    <img
                      class="cafelogo"
                      :src="require(`@/assets/css/images/${storeEngName[index]}.png`)"
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
  name: "index",
  components: { BarChart },
  data() {
    return {
      loaded: false,
      topdong: "",
      chartdata: null,
      options: null,
      labels: [],
      datas: [],
      showrank: false,
      chartbar: false,
      cafes: [],
      cafeEngName: [],
      category: 5,
      stores: [],
      storeEngName: [],
      cafeRank: false,
      storeRank: false,
      rank: "",
    };
  },
  async mounted() {
    // const labels = [];
    // const datas = [];
    // console.log("마운트");
    // http
    //   .get("/coffeeshop/dongrank")
    //   .then(({ data }) => {
    //     for (let index = 0; index < 3; index++) {
    //       labels.push(data[index].dong);
    //       datas.push(data[index].cnt);
    //       console.log("데이터");
    //     }
    //   })
    //   .catch(() => {
    //     alert("에러가 발생했습니다.");
    //   });
    // console.log("no!!");
    // this.fillData(labels, datas);
  },
  watch: {
    category: function (newVal) {
      this.labels = [];
      this.datas = [];
      switch (newVal) {
        case 0:
          console.log("와치 진입");
          this.getCafeData();
          break;
        case 1:
          this.getMarketData();
          break;
        case 2:
          break;
        default:
          break;
      }
    },
  },
  methods: {
    getCafeData() {
      http
        .get("/coffeeshop/dongrank")
        .then(({ data }) => {
          for (let index = 0; index < 3; index++) {
            console.log("데이타 잘 받아오고");
            console.log(data);
            this.labels.push(data[index].dong);
            this.datas.push(data[index].cnt);
          }
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    getMarketData() {
      http
        .get("/store/dongrank")
        .then(({ data }) => {
          for (let index = 0; index < 3; index++) {
            this.labels.push(data[index].dong);
            this.datas.push(data[index].cnt);
          }
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
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
      if (this.rank === "카페") {
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
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      } else if (this.rank === "편의점") {
        http.get("/store/storerank/" + this.topdong).then(({ data }) => {
          this.stores = data;
          console.log(data);
          for (let i = 0; i < 5; i++) {
            if (this.stores[i].sname === "CU") {
              this.storeEngName[i] = "CU";
            } else if (this.stores[i].sname === "GS25") {
              this.storeEngName[i] = "gs25";
            } else if (this.stores[i].sname === "세븐일레븐") {
              this.storeEngName[i] = "seveneleven";
            } else if (this.stores[i].sname === "미니스톱") {
              this.storeEngName[i] = "ministop";
            } else if (this.stores[i].sname === "이마트24") {
              this.storeEngName[i] = "emart24";
            } else {
              this.storeEngName[i] = "profile";
            }
          }
        });
      }
    },

    showCoffeeShop() {
      console.log("차트도 열고");
      this.showrank = !this.showrank;
      this.chartbar = !this.chartbar;
    },
    changeCategory(c) {
      this.category = c;
      if (c == 0) {
        this.rank = "카페";
      } else if (c == 1) {
        this.rank = "편의점";
      }
      console.log(c);
    },
    chartOpen() {
      console.log("렌더링해야지");
      this.fillData(this.labels, this.datas);
      this.showrank = !this.showrank;
      this.chartbar = !this.chartbar;
      console.log(this.chartbar);
    },
    showCafeRank() {
      console.log("렌더링해야지");
      this.fillData(this.labels, this.datas);
      this.cafeRank = true;
      this.storeRank = false;
      this.showrank = !this.showrank;
      this.chartbar = !this.chartbar;
      console.log(this.chartbar);
    },
    showStoreRank() {
      console.log("렌더링해야지");
      this.fillData(this.labels, this.datas);
      this.storeRank = true;
      this.cafeRank = false;
      this.showrank = !this.showrank;
      this.chartbar = !this.chartbar;
      console.log(this.chartbar);
    },
    chartClose() {
      // this.chartbar = false;
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
