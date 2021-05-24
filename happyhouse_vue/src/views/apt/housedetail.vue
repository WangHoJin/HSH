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
                  @click="getCoffeeShopRadius"
                  ><span class="label"></span
                ></a>
              </li>
              <li>
                <a
                  class="icon solid fa-shopping-cart green"
                  :class="{ current: isMarketCurrent }"
                  @click="getMarketRadius"
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
            <button class="button" :class="{ selected: dist100 }" @click="getDist100">100m</button>
            <button class="button" :class="{ selected: dist300 }" @click="getDist300">300m</button>
            <button class="button" :class="{ selected: dist500 }" @click="getDist500">500m</button>
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
                    :zoom="16"
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
                      @click="aa"
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
                  <h2 class="major"><span>땡세권 그래프</span></h2>
                </section>
                <section>
                  <h2 class="major"><span>마커 정보</span></h2>
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
import { mapGetters } from "vuex";
const mapMarker = require("@/assets/logo.png");
export default {
  name: "search",
  components: {
    AptDetailListRow,
  },
  data() {
    return {
      center: {
        lat: 37.5724163,
        lng: 126.9824791,
      },
      aptdetail: [],
      coffeeshop: [],
      markerOptions: {
        url: mapMarker,
        size: { width: 60, height: 90, f: "px", b: "px" },
        scaledSize: { width: 30, height: 45, f: "px", b: "px" },
      },
      isCoffeeCurrent: false,
      isMarketCurrent: false,
      dist100: true,
      dist300: false,
      dist500: false,
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
    getCoffeeShopRadius() {
      http
        .get("/coffeeshop/coffeeradius/" + this.getApt.aptName)
        .then(({ data }) => {
          this.coffeeshop = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      this.isCoffeeCurrent = !this.isCoffeeCurrent;
    },
    getMarketRadius() {
      this.isMarketCurrent = !this.isMarketCurrent;
    },
    aa() {
      alert("야야");
    },
    getDist100() {
      this.dist100 = true;
      this.dist300 = false;
      this.dist500 = false;
    },
    getDist300() {
      this.dist100 = false;
      this.dist300 = true;
      this.dist500 = false;
    },
    getDist500() {
      this.dist100 = false;
      this.dist300 = false;
      this.dist500 = true;
    },
  },
};
</script>

<style></style>
