<template>
  <div>
    <!-- Main -->
    <section id="main">
      <div class="container">
        <div class="row">
          <!-- filters start -->
          <div class="col-3 col-12-medium">
            <h3>
              <a href="#">{{ getApt.aptName }}</a>
            </h3>
            <section>
              <a href="#" class="image featured"
                ><img src="@/assets/css/images/house1.jpg" alt=""
              /></a>
            </section>
          </div>
          <div class="col-9 col-12-medium">
            <div class="card" style="border: 0px; height: 230px; overflow: auto">
              <section>
                <ul class="divided">
                  <apt-detail-list-row
                    v-for="(ad, index) in aptdetail"
                    :key="`${index}_ad`"
                    :dong="ad.dong"
                    :aptName="ad.aptName"
                    :dealAmount="ad.dealAmount"
                    :dealYear="ad.dealYear"
                    :dealMonth="ad.dealMonth"
                    :dealDay="ad.dealDay"
                    :area="ad.area"
                  />
                </ul>
              </section>
            </div>
          </div>
          <!-- filters end -->
        </div>
        <h2 class="major"></h2>
        <div class="row">
          <div>
            <ul class="contact" style="display: inline-block">
              <h3 style="display: inline-block; margin: 0 1em">이곳의 땡세권</h3>
              <li>
                <a
                  :class="{ current: isCoffeeCurrent }"
                  class="icon solid fa-coffee green"
                  @mousedown="[getCafeData(m)]"
                  @mouseup="[chartOpen(0)]"
                  ><span class="label"></span
                ></a>
              </li>
              <li>
                <a
                  class="icon solid fa-shopping-cart green"
                  :class="{ current: isMarketCurrent }"
                  @mousedown="[getMarketData(m)]"
                  @mouseup="[chartOpen(1)]"
                  ><span class="label"></span
                ></a>
              </li>
              <li>
                <a class="icon solid fa-subway green" href="#"><span class="label"></span></a>
              </li>
              <li>
                <a class="icon solid fa-hospital-alt green" href="#"><span class="label"></span></a>
              </li>
              <input
                type="text"
                style="
                  display: inline-block;
                  margin: 0 1em;
                  width: 300px;
                  border: solid 2px #e7eae8;
                  border-radius: 8px;
                "
                id="apttext"
              /><button type="button" id="aptsearch" class="button" value="검색">검색</button>
            </ul>
            <!-- <div class="col-4 col-12-medium"> -->
            <h3 style="display: inline-block; margin: 0 1em">반경 설정</h3>
            <button
              class="button"
              :class="{ selected: dist100 }"
              @mouseover="changeDist(100)"
              @mouseup="[selectDist100(), chartOpen(), getCoffeeShopRadius(100, true)]"
              @mousedown="chartClose()"
            >
              100m
            </button>
            <button
              class="button"
              :class="{ selected: dist300 }"
              @mouseover="changeDist(300)"
              @mouseup="[selectDist300(), chartOpen(), getCoffeeShopRadius(300, true)]"
              @mousedown="chartClose()"
            >
              300m
            </button>
            <button
              class="button"
              :class="{ selected: dist500 }"
              @mouseover="changeDist(500)"
              @mouseup="[selectDist500(), chartOpen(), getCoffeeShopRadius(500, true)]"
              @mousedown="chartClose()"
            >
              500m
            </button>
            <!-- </div> -->
          </div>
        </div>
        <div class="row">
          <div class="col-8 col-12-medium">
            <div class="content">
              <!-- Content -->
              <article class="box page-content">
                <section>
                  <!-- Map Start -->
                  <GmapMap
                    ref="mapRef"
                    :center="{ lat: getApt.lat * 1, lng: getApt.lng * 1 }"
                    :zoom="zoom"
                    style="width: 100%; height: 500px"
                  >
                    <GmapMarker
                      :position="{ lat: getApt.lat * 1, lng: getApt.lng * 1 }"
                      :icon="markerOptions"
                    />
                    <GmapMarker
                      v-for="(c, index) in coffeeshop"
                      :key="`${index}_coffeeshop`"
                      :position="{ lat: c.lat * 1, lng: c.lng * 1 }"
                      :icon="cafemarkerOptions"
                      @click="markerClick(c.lat, c.lng, 0)"
                    />
                    <GmapMarker
                      v-for="(c, index) in store"
                      :key="`${index}_store`"
                      :position="{ lat: c.lat * 1, lng: c.lng * 1 }"
                      @click="markerClick(c.lat, c.lng, 1)"
                    />
                  </GmapMap>
                  <!-- Map End -->
                </section>
              </article>
            </div>
          </div>
          <div class="col-4 col-12-medium">
            <div class="card" style="border: 0px; height: 600px; overflow: auto">
              <div class="sidebar">
                <section>
                  <h2 class="major"><span>카페 정보</span></h2>
                  <div v-for="(cafe, index) in markerinfo" :key="`${index}_markerinfo`">
                    <h4>{{ cafe.cname }}</h4>
                    <h5>{{ cafe.branchname }}</h5>
                    <h5>{{ cafe.address1 }}</h5>
                    <h5>{{ cafe.address2 }}</h5>
                  </div>
                  <h2 class="major" style="margin: 1.5em 0 1.5em 0"><span>편의점 정보</span></h2>
                  <div v-for="(store, index) in markerinfo2" :key="`${index}_markerinfo2`">
                    <h4>{{ store.sname }}</h4>
                    <h5>{{ store.branchname }}</h5>
                    <h5>{{ store.address1 }}</h5>
                    <h5>{{ store.address2 }}</h5>
                  </div>
                </section>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-4 col-12-medium">
              <section>
                <h2 class="major">
                  <span>카세권 그래프</span>
                  <doughnut-chart v-if="chartbar" :chartdata="chartdata" :options="options">
                  </doughnut-chart>
                </h2>
              </section>
            </div>
            <div class="col-4 col-12-medium">
              <section>
                <h2 class="major">
                  <span>편세권 그래프</span>
                  <doughnut-chart2
                    v-if="chartbar2"
                    :chartdata="chartdata2"
                    :options="options2"
                  ></doughnut-chart2>
                </h2>
              </section>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 컨테이너 끝 -->
  </div>
</template>

<script>
import http from "@/util/http-common";
import AptDetailListRow from "@/components/apt/AptDetailListRow.vue";
// import M100 from "@/components/Charts/100M.vue";
import DoughnutChart from "@/components/Charts/DoughnutChart.vue";
import DoughnutChart2 from "@/components/Charts/DoughnutChart2.vue";
import { mapGetters } from "vuex";
const mapMarker = require("@/assets/css/images/apartment.png");
const cafeMarker = require("@/assets/css/images/coffee-cup.png");

export default {
  name: "search",
  components: {
    AptDetailListRow,
    // M100,
    DoughnutChart,
    DoughnutChart2,
  },
  data() {
    return {
      center: {
        lat: 37.5724163,
        lng: 126.9824791,
      },
      aptdetail: [],
      coffeeshop: [],
      store: [],
      markerOptions: {
        url: mapMarker,
        size: { width: 60, height: 90, f: "px", b: "px" },
        scaledSize: { width: 30, height: 45, f: "px", b: "px" },
      },
      cafemarkerOptions: {
        url: cafeMarker,
        size: { width: 60, height: 70, f: "px", b: "px" },
        scaledSize: { width: 30, height: 30, f: "px", b: "px" },
      },
      markerinfo: {},
      markerinfo2: {},
      isCoffeeCurrent: false,
      isMarketCurrent: false,
      dist100: true,
      dist300: false,
      dist500: false,
      chartdata: null,
      options: null,
      chartbar: false,
      labels: [],
      datas: [],
      chartdata2: null,
      options2: null,
      chartbar2: false,
      labels2: [],
      datas2: [],
      category: 5,
      loaded: false,
      start: true,
      m: 100,
      zoom: 16,
    };
  },
  computed: {
    ...mapGetters(["getApt"]),
  },
  created() {
    http
      .get("/housesearch2/housedeal/" + this.getApt.dong + "/" + this.getApt.aptName)
      .then(({ data }) => {
        this.aptdetail = data;
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  },
  async mounted() {},
  watch: {
    category: function (newVal) {
      this.labels = [];
      this.datas = [];
      this.labels2 = [];
      this.datas2 = [];
      switch (newVal) {
        case 0:
          console.log("와치1 진입");
          this.getCafeData(this.meter);
          break;
        case 1:
          console.log("와치2 진입");
          this.getMarketData(this.meter);
          break;
        case 2:
          break;
        default:
          break;
      }
    },
    isCoffeeCurrent: function (newVal) {
      console.log("바낐다");
      console.log(newVal);
      if (newVal == false) this.coffeeshop = null;
    },
    isMarketCurrent: function (newVal) {
      if (newVal == false) this.store = null;
    },
    // meter: function (newVal) {
    //   //console.log("와치");
    //   //console.log( "/coffeeshop/coffeeradiusrank/" + this.getApt.lat + "/" + this.getApt.lng + "/" + newVal);
    //   http
    //     .get(
    //       "/coffeeshop/coffeeradiusrank/" + this.getApt.no + "/" + this.getApt.lng + "/" + newVal
    //     )
    //     .then(({ data }) => {
    //       this.labels = [];
    //       this.datas = [];
    //       //console.log(data);
    //       for (let index = 0; index < data.length; index++) {
    //         if (index == 5) break;
    //         this.labels.push(data[index].cname);
    //         this.datas.push(data[index].coffeeshopcnt);
    //       }
    //       //console.log(this.labels);
    //     })
    //     .catch(() => {
    //       alert("에러가 발생했습니다.");
    //     });
    // },
  },
  methods: {
    getCafeData(radius) {
      console.log(radius);
      this.labels = [];
      this.datas = [];
      http
        .get("/coffeeshop/coffeeradius/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius)
        .then(({ data }) => {
          console.log("카페 데이터 가져와");
          this.coffeeshop = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });

      http
        .get(
          "/coffeeshop/coffeeradiusrank/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius
        )
        .then(({ data }) => {
          for (let index = 0; index < 3; index++) {
            this.labels.push(data[index].cname);
            this.datas.push(data[index].coffeeshopcnt);
          }
          console.log("카페 데이터 삽입");
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    getMarketData(radius) {
      this.labels2 = [];
      this.datas2 = [];
      http
        .get("/store/storeradius/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius)
        .then(({ data }) => {
          this.store = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      http
        .get("/store/storeradiusrank/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius)
        .then(({ data }) => {
          for (let index = 0; index < data.length; index++) {
            if (index == 3) break;
            this.labels2.push(data[index].sname);
            this.datas2.push(data[index].storecnt);
          }
          console.log("편의점 데이터 삽입");
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    changeCategory(c) {
      this.category = c;
      console.log(c);
    },
    chartOpen(c) {
      switch (c) {
        case 0:
          console.log("커피 버튼 바뀌기 전");
          console.log(this.isCoffeeCurrent);
          this.isCoffeeCurrent = !this.isCoffeeCurrent;
          console.log("커피 버튼 바뀐 후");
          console.log(this.isCoffeeCurrent);
          this.chartbar = !this.chartbar;
          this.a();
          break;
        case 1:
          this.isMarketCurrent = !this.isMarketCurrent;
          this.chartbar2 = !this.chartbar2;
          this.b();
          break;
        case 2:
          break;
        default:
          break;
      }
      console.log(this.chartbar);
    },
    a() {
      console.log("1렌더링해야지");
      this.fillData(this.labels, this.datas);
    },
    b() {
      console.log("2렌더링해야지");
      this.fillData2(this.labels2, this.datas2);
    },
    fillData(l, d) {
      this.chartdata = {
        labels: l,
        datasets: [
          {
            label: "주변 카페 동네 TOP 3",
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 205, 86, 0.2)",
            ],
            borderColor: ["rgb(255, 99, 132)", "rgb(255, 159, 64)", "rgb(255, 205, 86)"],
            data: d,
          },
        ],
        // datasets: [
        //   {
        //     label: "카페 많은 동네 TOP 3",
        //     backgroundColor: [
        //       "rgba(255, 99, 132, 0.2)",
        //       "rgba(255, 159, 64, 0.2)",
        //       "rgba(255, 205, 86, 0.2)",
        //     ],
        //     borderColor: ["rgb(255, 99, 132)", "rgb(255, 159, 64)", "rgb(255, 205, 86)"],
        //     data: d,
        //   },
        // ],
      };
      this.options = {
        onClick: this.aa,
      };
    },
    fillData2(l, d) {
      this.chartdata2 = {
        labels: l,
        datasets: [
          {
            label: "주변 편의점 TOP 3",
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
        onClick: this.aa,
      };
    },
    getCoffeeShopRadius(radius, meter) {
      // 통신을 켜야되는 상황
      // 시작 , 미터 선택
      // f->t f and t->f t
      // f->t t
      // 끄는 상황
      // t->f f
      //console.log("로그인상태" + !this.isCoffeeCurrent);
      //console.log("클릭상태" + meter);
      if ((!this.isCoffeeCurrent && meter) == true) {
        alert("세권입력");
        this.chartbar = false;
      } else if (!this.isCoffeeCurrent || meter) {
        http
          .get("/coffeeshop/coffeeradius/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius)
          .then(({ data }) => {
            //console.log(data);
            this.coffeeshop = data;
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      } else {
        this.coffeeshop = null;
      }
      if (!meter) this.isCoffeeCurrent = !this.isCoffeeCurrent;
      console.log("카페 클릭");
      console.log(this.getApt.lat + "/" + this.getApt.lng);
    },
    getMarketRadius(radius) {
      console.log("편의점 클릭");
      http
        .get("/store/storeradius/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius)
        .then(({ data }) => {
          //console.log(data);
          this.store = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      this.isMarketCurrent = !this.isMarketCurrent;
    },
    markerClick(lat, lng, c) {
      if (c == 0) {
        console.log("카페 클릭");
        http
          .get("/coffeeshop/coffeemarker/" + lat + "/" + lng)
          .then(({ data }) => {
            console.log(data);
            this.markerinfo = data;
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      } else {
        http
          .get("/store/storemarker/" + lat + "/" + lng)
          .then(({ data }) => {
            console.log(data);
            this.markerinfo2 = data;
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      }

      this.center.lat = lat * 1;
      this.center.lng = lng * 1;

      if (this.zoom == 17) {
        if (this.meter == 100) {
          this.zoom = 16.2;
        } else if (this.meter == 300) {
          this.zoom = 15.7;
        } else if (this.meter == 500) {
          this.zoom = 15.5;
        }
      } else if (this.zoom <= 16.2) {
        this.zoom = 17;
      }

      this.$refs.mapRef.panTo(this.center);
    },
    selectDist100() {
      this.dist100 = true;
      this.dist300 = false;
      this.dist500 = false;
      //console.log("렌더링100");
      this.fillData(this.labels, this.datas);
      if (this.zoom != 16.2) {
        this.zoom = 16.2;
      }
    },
    selectDist300() {
      this.dist100 = false;
      this.dist300 = true;
      this.dist500 = false;
      //console.log("렌더링300");
      this.fillData(this.labels, this.datas);
      if (this.zoom != 15.7) {
        this.zoom = 15.7;
      }
    },
    selectDist500() {
      this.dist100 = false;
      this.dist300 = false;
      this.dist500 = true;
      //console.log("렌더링500");
      this.fillData(this.labels, this.datas);
      if (this.zoom != 15.5) {
        this.zoom = 15.5;
      }
    },

    changeDist(d) {
      this.meter = d;
    },

    chartClose() {
      this.chartbar = false;
    },
    chartOpenAndClose() {
      this.chartbar = !this.chartbar;
    },
  },
};
</script>

<style></style>
