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
                  @click="[getCoffeeShopRadius(100, false), chartOpenAndClose()]"
                  ><span class="label"></span
                ></a>
              </li>
              <li>
                <a
                  class="icon solid fa-shopping-cart green"
                  :class="{ current: isMarketCurrent }"
                  @click="getMarketRadius(100), chartOpenAndClose()"
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
                      @click="markerClick(c.lat, c.lng)"
                    />
                    <GmapMarker
                      v-for="(c, index) in store"
                      :key="`${index}_store`"
                      :position="{ lat: c.lat * 1, lng: c.lng * 1 }"
                      @click="markerClick(c.lat, c.lng)"
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
                  <h2 class="major">
                    <span>땡세권 그래프</span>
                    <pie-chart
                      v-if="chartbar"
                      :chartdata="chartdata"
                      :options="options"
                    ></pie-chart>
                  </h2>
                </section>
                <section>
                  <h2 class="major"><span>마커 정보</span></h2>
                  <div v-for="(cafe, index) in markerinfo" :key="`${index}_markerinfo`">
                    <h4>{{ cafe.cname }}</h4>
                    <h5>{{ cafe.branchname }}</h5>
                    <h5>{{ cafe.address1 }}</h5>
                    <h5>{{ cafe.address2 }}</h5>
                  </div>
                </section>
              </div>
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
import PieChart from "@/components/Charts/PieChart.vue";
import { mapGetters } from "vuex";
const mapMarker = require("@/assets/css/images/apartment.png");
const cafeMarker = require("@/assets/css/images/coffee-cup.png");

export default {
  name: "search",
  components: {
    AptDetailListRow,
    // M100,
    PieChart,
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
      isCoffeeCurrent: false,
      isMarketCurrent: false,
      dist100: true,
      dist300: false,
      dist500: false,
      chartdata: null,
      options: null,
      chartbar: false,
      loaded: false,
      start: true,
      labels: [],
      datas: [],
      meter: 0,
      zoom: 10,
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
  watch: {
    meter: function (newVal) {
      //console.log("와치");
      //console.log( "/coffeeshop/coffeeradiusrank/" + this.getApt.lat + "/" + this.getApt.lng + "/" + newVal);
      http
        .get(
          "/coffeeshop/coffeeradiusrank/" + this.getApt.no + "/" + this.getApt.lng + "/" + newVal
        )
        .then(({ data }) => {
          this.labels = [];
          this.datas = [];
          //console.log(data);
          for (let index = 0; index < data.length; index++) {
            if (index == 5) break;
            this.labels.push(data[index].cname);
            this.datas.push(data[index].coffeeshopcnt);
          }
          //console.log(this.labels);
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
  },
  async mounted() {
    http
      .get("/coffeeshop/coffeeradiusrank/" + this.getApt.lat + "/" + this.getApt.lng + "/100")
      .then(({ data }) => {
        for (let index = 0; index < data.length; index++) {
          if (index == 5) break;
          this.labels.push(data[index].cname);
          this.datas.push(data[index].coffeeshopcnt);
        }
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
    this.fillData(this.labels, this.datas);
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
    markerClick(lat, lng) {
      http
        .get("/coffeeshop/coffeemarker/" + lat + "/" + lng)
        .then(({ data }) => {
          //console.log(data);
          this.markerinfo = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });

      this.zoom = 18;
    },
    selectDist100() {
      this.dist100 = true;
      this.dist300 = false;
      this.dist500 = false;
      //console.log("렌더링100");
      this.fillData(this.labels, this.datas);
    },
    selectDist300() {
      this.dist100 = false;
      this.dist300 = true;
      this.dist500 = false;
      //console.log("렌더링300");
      this.fillData(this.labels, this.datas);
    },
    selectDist500() {
      this.dist100 = false;
      this.dist300 = false;
      this.dist500 = true;
      //console.log("렌더링500");
      this.fillData(this.labels, this.datas);
    },

    changeDist(d) {
      this.meter = d;
    },
    chartOpen() {
      this.chartbar = true;
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
