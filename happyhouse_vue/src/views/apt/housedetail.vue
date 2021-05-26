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
            <div class="card scroll" style="border: 0px; height: 230px; overflow: auto">
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
          <div class="col-6 col-12-medium">
            <ul class="contact" style="display: inline-block">
              <h3 style="display: inline-block; margin: 0 1em">원하는 정보를 클릭!</h3>
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
            </ul>
          </div>
          <div class="col-5 col-12-medium">
            <ul v-if="showRadius()" class="contact" style="display: inline-block">
              <h3 style="display: inline-block; margin: 0 0.5em">반경 설정</h3>
              <li>
                <button
                  class="button"
                  style="height: 48px"
                  :class="{ selected: dist100 }"
                  @mousedown="[selectDist100()]"
                  @mouseup="[chartRadiusOpen()]"
                >
                  100m
                </button>
              </li>
              <li>
                <button
                  class="button"
                  style="height: 48px"
                  :class="{ selected: dist300 }"
                  @mousedown="[selectDist300()]"
                  @mouseup="[chartRadiusOpen()]"
                >
                  300m
                </button>
              </li>
              <li>
                <button
                  class="button"
                  style="height: 48px"
                  :class="{ selected: dist500 }"
                  @mousedown="[selectDist500()]"
                  @mouseup="[chartRadiusOpen()]"
                >
                  500m
                </button>
              </li>
            </ul>
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
                      :icon="storemarkerOptions"
                      @click="markerClick(c.lat, c.lng, 1)"
                    />
                  </GmapMap>
                  <!-- Map End -->
                </section>
              </article>
            </div>
          </div>
          <div class="col-4 col-12-medium">
            <div class="card scroll" style="border: 0px; height: 600px; overflow: auto">
              <div class="sidebar">
                <section>
                  <h2 class="major"><span>카페 정보</span></h2>

                  <ul class="divided">
                    <li v-for="(cafe, index) in markerinfo" :key="`${index}_markerinfo`">
                      <article class="box-post-summary">
                        <h4>{{ cafe.cname }}</h4>
                        <h5>{{ cafe.branchname }}</h5>
                        <h5>{{ cafe.address1 }}</h5>
                      </article>
                    </li>
                  </ul>

                  <h2 class="major" style="margin: 1.5em 0 1.5em 0"><span>편의점 정보</span></h2>
                  <ul class="divided">
                    <li v-for="(store, index) in markerinfo2" :key="`${index}_markerinfo2`">
                      <article class="box-post-summary">
                        <h4>{{ store.sname }}</h4>
                        <h5>{{ store.branchname }}</h5>
                        <h5>{{ store.address1 }}</h5>
                      </article>
                    </li>
                  </ul>
                </section>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-4 col-12-medium">
              <section>
                <h2 class="major">
                  <span>카세권 그래프</span>
                  <line-chart v-if="chartbar" :chartdata="chartdata" :options="options">
                  </line-chart>
                </h2>
              </section>
            </div>
            <div class="col-4 col-12-medium">
              <section>
                <h2 class="major">
                  <span>편세권 그래프</span>
                  <line-chart2
                    v-if="chartbar2"
                    :chartdata="chartdata2"
                    :options="options2"
                  ></line-chart2>
                </h2>
              </section>
            </div>
            <div class="col-4 col-12-medium">
              <section>
                <h2 class="major">
                  <span>가장 가까운 곳</span>
                  <ul class="divided">
                    <li v-if="isCoffeeCurrent">
                      <article class="box-post-summary">
                        <h4>이름 : {{ coffeeshop[0].cname }}</h4>
                        <h5>주소 : {{ coffeeshop[0].address1 }}</h5>
                        <h5>거리 : {{ Math.ceil(coffeeshop[0].distance * 1000) }}m</h5>
                      </article>
                    </li>
                    <li v-if="isMarketCurrent">
                      <article class="box-post-summary">
                        <h4>이름 : {{ store[0].sname }}</h4>
                        <h5>주소 : {{ store[0].address1 }}</h5>
                        <h5>거리 : {{ Math.ceil(store[0].distance * 1000) }}m</h5>
                      </article>
                    </li>
                  </ul>
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
// import DoughnutChart from "@/components/Charts/DoughnutChart.vue";
// import DoughnutChart2 from "@/components/Charts/DoughnutChart2.vue";
import LineChart from "@/components/Charts/LineChart.vue";
import LineChart2 from "@/components/Charts/LineChart2.vue";
import { mapGetters } from "vuex";
const mapMarker = require("@/assets/css/images/apartment.png");
const cafeMarker = require("@/assets/css/images/coffee-cup.png");
const storeMarker = require("@/assets/css/images/24-hours.png");

export default {
  name: "search",
  components: {
    AptDetailListRow,
    // DoughnutChart,
    // DoughnutChart2,
    LineChart,
    LineChart2,
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
      storemarkerOptions: {
        url: storeMarker,
        size: { width: 60, height: 70, f: "px", b: "px" },
        scaledSize: { width: 30, height: 30, f: "px", b: "px" },
      },
      category: 5,
      markerinfo: {},
      markerinfo2: {},
      isCoffeeCurrent: false,
      isMarketCurrent: false,
      chartdata: null,
      options: null,
      chartbar: false,
      labels: [],
      datas: [],
      datas1: [],
      chartdata2: null,
      options2: null,
      chartbar2: false,
      labels2: [],
      datas2: [],
      datas3: [],
      dist100: false,
      dist300: false,
      dist500: false,
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
  methods: {
    getCafeData(radius) {
      console.log("반경 정보:" + radius);
      this.labels = [];
      this.datas = [];
      http
        .get("/coffeeshop/coffeeradius/" + this.getApt.lat + "/" + this.getApt.lng + "/" + radius)
        .then(({ data }) => {
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
          for (let index = 0; index < data.length; index++) {
            if (index == 5) break;
            this.labels.push(data[index].cname);
            this.datas.push(data[index].coffeeshopcnt);
            this.datas1.push(data[index].distance * 100);
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
            if (index == 5) break;
            this.labels2.push(data[index].sname);
            this.datas2.push(data[index].storecnt);
            this.datas3.push(data[index].distance * 100);
          }
          console.log("편의점 데이터 삽입");
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    changeCategory(c) {
      this.category = c;
    },
    chartOpen(c) {
      switch (c) {
        case 0:
          this.isCoffeeCurrent = !this.isCoffeeCurrent;
          this.chartbar = !this.chartbar;
          if (this.chartbar) console.log("카페 차트 열림");
          else console.log("카페 차트 닫힘");
          if (!this.isCoffeeCurrent) this.coffeeshop = null;
          this.a();
          break;
        case 1:
          this.isMarketCurrent = !this.isMarketCurrent;
          this.chartbar2 = !this.chartbar2;
          if (this.chartbar2) console.log("편의점 차트 열림");
          else console.log("편의점 차트 닫힘");
          if (!this.isMarketCurrent) this.store = null;
          this.b();
          break;
        case 2:
          break;
        default:
          break;
      }
    },
    a() {
      console.log("카페 렌더링");
      this.fillData(this.labels, this.datas, this.datas1);
    },
    b() {
      console.log("편의점 렌더링");
      this.fillData2(this.labels2, this.datas2, this.datas3);
    },
    fillData(l, d, d1) {
      this.chartdata = {
        labels: l,
        datasets: [
          {
            label: "카페 갯수",
            borderColor: "rgb(255, 99, 132)",
            backgroundColor: "rgba(255, 99, 132,0.2)",
            stack: "combined",
            type: "bar",
            data: d,
          },
          {
            label: "거리",
            borderColor: "rgb(54, 162, 235)",
            fill: false,
            lineTension: 0,
            stack: "combined",
            type: "line",
            data: d1,
          },
        ],
      };
      this.options = {
        scales: {
          y: {
            stacked: true,
          },
        },

        onClick: this.aa,
      };
    },
    fillData2(l, d, d3) {
      this.chartdata2 = {
        labels: l,
        datasets: [
          {
            label: "주변 편의점 TOP 3",
            borderColor: "rgb(255, 99, 132)",
            backgroundColor: "rgba(255, 99, 132,0.2)",
            type: "bar",
            stack: "combined",
            data: d,
          },
          {
            label: "주변 편의점 TOP 3",
            borderColor: "rgb(54, 162, 235)",
            fill: false,
            lineTension: 0,
            stack: "combined",
            type: "line",
            data: d3,
          },
        ],
      };

      this.options = {
        onClick: this.aa,
        scales: {
          y: {
            stacked: true,
          },
        },
      };
    },

    selectDist100() {
      this.dist100 = true;
      this.dist300 = false;
      this.dist500 = false;
      this.m = 100;
      if (this.isCoffeeCurrent) {
        this.getCafeData(100);
        this.chartbar = !this.chartbar;
        if (!this.chartbar) console.log("카페 차트 닫힘 :" + this.chartbar);
      }
      if (this.isMarketCurrent) {
        this.getMarketData(100);
        this.chartbar2 = !this.chartbar2;
        if (!this.chartbar2) console.log("편의점 차트 닫힘 :" + this.chartbar2);
      }
      if (this.zoom != 16.2) {
        this.zoom = 16.2;
      }
    },
    selectDist300() {
      this.dist100 = false;
      this.dist300 = true;
      this.dist500 = false;
      this.m = 300;
      if (this.isCoffeeCurrent) {
        this.getCafeData(300);
        this.chartbar = !this.chartbar;
        if (!this.chartbar) console.log("카페 차트 닫힘 :" + this.chartbar);
      }
      if (this.isMarketCurrent) {
        this.getMarketData(300);
        this.chartbar2 = !this.chartbar2;
        if (!this.chartbar2) console.log("편의점 차트 닫힘 :" + this.chartbar2);
      }
      if (this.zoom != 15.7) {
        this.zoom = 15.7;
      }
    },
    selectDist500() {
      this.dist100 = false;
      this.dist300 = false;
      this.dist500 = true;
      this.m = 500;
      if (this.isCoffeeCurrent) {
        this.getCafeData(500);
        this.chartbar = !this.chartbar;
        if (!this.chartbar) console.log("카페 차트 닫힘 :" + this.chartbar);
      }
      if (this.isMarketCurrent) {
        this.getMarketData(500);
        this.chartbar2 = !this.chartbar2;
        if (!this.chartbar2) console.log("편의점 차트 닫힘 :" + this.chartbar2);
      }
      if (this.zoom != 15.5) {
        this.zoom = 15.5;
      }
    },
    showRadius() {
      if (!(this.isCoffeeCurrent || this.isMarketCurrent)) return false;
      else return true;
    },
    chartRadiusOpen() {
      if (this.isCoffeeCurrent) {
        this.chartbar = !this.chartbar;
        if (this.chartbar) console.log("카페 차트 열림:" + this.chartbar);
        this.a();
      }
      if (this.isMarketCurrent) {
        this.chartbar2 = !this.chartbar2;
        if (this.chartbar2) console.log("편의점 차트 열림");
        this.b();
      }
    },

    markerClick(lat, lng, c) {
      if (c == 0) {
        http
          .get("/coffeeshop/coffeemarker/" + lat + "/" + lng)
          .then(({ data }) => {
            this.markerinfo = data;
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      } else {
        http
          .get("/store/storemarker/" + lat + "/" + lng)
          .then(({ data }) => {
            this.markerinfo2 = data;
          })
          .catch(() => {
            alert("에러가 발생했습니다.");
          });
      }

      this.center.lat = lat * 1;
      this.center.lng = lng * 1;

      if (this.zoom == 17.5) {
        if (this.m == 100) {
          this.zoom = 16.2;
        } else if (this.m == 300) {
          this.zoom = 15.7;
        } else if (this.m == 500) {
          this.zoom = 15.5;
        }
      } else if (this.zoom <= 16.2) {
        this.zoom = 17.5;
      }

      this.$refs.mapRef.panTo(this.center);
    },
  },
};
</script>

<style></style>
