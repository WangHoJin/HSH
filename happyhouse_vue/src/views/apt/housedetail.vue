<template>
  <div>
    <!-- Main -->
    <section id="main">
      <div class="container">
        <div class="row">
          <!-- filters start -->
          <div class="col-3 col-12-medium">
            <h3>
              <a href="#">{{ getAptName }}</a>
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
                    v-for="(ad, index) in getAptList"
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
                <a class="icon food fa-coffee" href="#"><span class="label">Facebook</span></a>
              </li>
              <li>
                <a class="icon brands fa-twitter" href="#"><span class="label">Twitter</span></a>
              </li>
              <li>
                <a class="icon brands fa-instagram" href="#"
                  ><span class="label">Instagram</span></a
                >
              </li>
              <li>
                <a class="icon brands fa-dribbble" href="#"><span class="label">Dribbble</span></a>
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
                    v-if="apts[0] != null"
                    :center="{ lat: apts[0].lat * 1, lng: apts[0].lng * 1 }"
                    :zoom="14"
                    style="width: 100%; height: 500px"
                  >
                    <GmapMarker
                      v-for="(m, index) in apts"
                      :key="`${index}_apts`"
                      :position="{ lat: m.lat * 1, lng: m.lng * 1 }"
                    />
                    <GmapMarker
                      v-for="(c, index) in coffeeshop"
                      :key="`${index}_coffeeshop`"
                      :position="{ lat: c.lat * 1, lng: c.lng * 1 }"
                    />
                  </GmapMap>
                  <GmapMap
                    ref="mapRef"
                    v-else
                    :center="center"
                    :zoom="14"
                    style="width: 100%; height: 500px"
                  >
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
    };
  },
  computed: {
    ...mapGetters(["getAptName", "getAptList"]),
  },
  created() {
    console.log(this.$store.state.apts);
    http
      .get("/housesearch2/house")
      .then(({ data }) => {
        this.sidos = data;
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  },
  methods: {
    searchAll() {
      http
        .get("/housesearch2/houseall")
        .then(({ data }) => {
          this.apts = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
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
    searchInterest() {
      http
        .get("/housesearch2/interest/" + this.$store.state.userid)
        .then(({ data }) => {
          this.apts = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      http
        .get("/housesearch2/dealinterest/" + this.$store.state.userid)
        .then(({ data }) => {
          this.aptdetail = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    searchCoffeeShop() {
      http
        .get("/coffeeshop/coffee/" + this.dong)
        .then(({ data }) => {
          this.coffeeshop = data;
          this.cflag = !this.cflag;
          console.log(data);
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
  },
};
</script>

<style></style>
