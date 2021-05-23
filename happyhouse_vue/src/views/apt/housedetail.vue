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
              <a href="#" class="image featured"><img src="@/img/house1.jpg" alt="" /></a>
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
          <div class="col-9 col-12-medium">
            <ul class="contact" style="display: inline-block">
              <h3 style="display: inline-block">이곳은 땡세권</h3>
              <li>
                <a class="fas fa-coffee" @click="getCoffeeShopRadius"
                  ><span class="label"></span
                ></a>
              </li>
              <li>
                <a class="fas fa-shopping-cart" href="#"><span class="label"></span></a>
              </li>
              <li>
                <a class="fas fa-subway" href="#"><span class="label"></span></a>
              </li>
              <li>
                <a class="fas fa-hospital-alt" href="#"><span class="label"></span></a>
              </li>
              <input
                type="text"
                style="display: inline-block; width: 150px"
                id="apttext"
                v-model="aptname"
              /><button
                type="button"
                id="aptsearch"
                class="btn btn-dark"
                value="검색"
                @click="searchName"
              >
                검색
              </button>
            </ul>
          </div>
          <div class="col-3 col-12-medium">
            <button
              type="button"
              id="aptsearch"
              class="btn btn-dark"
              value="검색"
              @click="searchName"
            >
              가장 가까운 곳 보기
            </button>
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
                    <GmapMarker :position="{ lat: getApt.lat * 1, lng: getApt.lng * 1 }" />
                    <GmapMarker
                      v-for="(c, index) in coffeeshop"
                      :key="`${index}_coffeeshop`"
                      :position="{ lat: c.lat * 1, lng: c.lng * 1 }"
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
                  <h2 class="major"><span>가장 가까운 곳</span></h2>
                </section>
                <section>
                  <h2 class="major"><span>땡세권 그래프</span></h2>
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
      apts: [],
      aptdetail: [],
      coffeeshop: [],
      sidos: {},
      guguns: {},
      dongs: {},
      sidocode: 0,
      guguncode: 0,
      dong: "",
      aptname: "",
      cflag: false,
      mflag: false,
    };
  },
  computed: {
    ...mapGetters(["getApt"]),
  },
  created() {
    http
      .get("/housesearch2/housedeal/" + this.getApt.dong + "/" + this.getApt.aptName)
      .then(({ data }) => {
        console.log(data);
        this.aptdetail = data;
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  },
  methods: {
    searchName() {
      http
        .get("/housesearch/aptofname/" + this.aptname)
        .then(({ data }) => {
          this.apts = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      http
        .get("/housesearch/dealofaptname/" + this.aptname)
        .then(({ data }) => {
          this.aptdetail = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    getCoffeeShopRadius() {
      http
        .get("/coffeeshop/coffeeradius/" + this.getApt.aptName)
        .then(({ data }) => {
          this.coffeeshop = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
  },
};
</script>

<style></style>
