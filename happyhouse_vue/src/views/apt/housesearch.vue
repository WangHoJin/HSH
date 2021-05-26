<template>
  <div>
    <!-- Main -->
    <section id="main">
      <div class="container">
        <div class="row">
          <!-- filters start -->
          <div class="col-8 col-12-medium">
            <div class="sorting-filters text-center d-flex justify-content-center">
              <button type="button" class="button mr-3" id="searchall" @click="searchAll">
                전체 조회
              </button>

              <form class="form-inline ml-3 mr-3" id="frm" method="post" action="housesearch">
                <input type="hidden" name="act" id="act" value="search" />

                <div class="form-group md">
                  <select class="form-control mr-2" id="sido" @change="getGugun($event)">
                    <option value="0">시/도</option>
                    <option
                      v-for="(sido, index) in sidos"
                      :key="`${index}_sidos`"
                      :value="sido.sido_code"
                    >
                      {{ sido.sido_name }}
                    </option>
                  </select>
                </div>
                <div class="form-group mr-2 md-1">
                  <select class="form-control" id="gugun" @change="getDong($event)">
                    <option value="0">시/군/구</option>
                    <option
                      v-for="(gugun, index) in guguns"
                      :key="`${index}_guguns`"
                      :value="gugun.gugun_code"
                    >
                      {{ gugun.gugun_name }}
                    </option>
                  </select>
                </div>
                <div class="form-group md-1">
                  <select class="form-control" id="dong" @change="getInfo($event)">
                    <option value="0">동</option>
                    <option
                      v-for="(dong, index) in dongs"
                      :key="`${index}_dongs`"
                      :value="dong.dong"
                    >
                      {{ dong.dong }}
                    </option>
                  </select>
                </div>
              </form>
              <button
                type="button"
                class="form-inline ml-3 button"
                id="searchinterest"
                @click="searchInterest"
              >
                관심지역
              </button>
              <button
                type="button"
                class="form-inline ml-3 button"
                id="addinterest"
                @click="addInterest"
              >
                관심지역추가
              </button>
            </div>
          </div>
          <div class="col-4 col-12-medium">
            <div class="form-group sorting-filters text-center d-flex justify-content-center">
              <input
                type="text"
                style="
                  display: inline-block;
                  width: 80%;
                  border: solid 2px #e7eae8;
                  border-radius: 8px;
                "
                id="apttext"
                placeholder="아파트 이름으로 검색하세요."
                v-model="aptname"
              />
              <button
                type="button"
                id="aptsearch"
                class="button"
                value="검색"
                @click="searchName"
                style="margin: 0 0.5em"
              >
                검색
              </button>
            </div>
          </div>
          <!-- filters end -->
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
                    :zoom="16"
                    style="width: 100%; height: 500px"
                  >
                    <GmapMarker
                      v-for="(m, index) in apts"
                      :key="`${index}_apts`"
                      :position="{ lat: m.lat * 1, lng: m.lng * 1 }"
                      :icon="markerOptions"
                    />
                  </GmapMap>
                  <GmapMap
                    ref="mapRef"
                    v-else
                    :center="center"
                    :zoom="16"
                    style="width: 100%; height: 500px"
                  >
                  </GmapMap>
                  <!-- Map End -->
                </section>
              </article>
            </div>
          </div>
          <div class="col-4 col-12-medium">
            <div class="card scroll" style="border: 0px; height: 600px; overflow: auto">
              <div class="sidebar">
                <!-- Sidebar -->

                <!-- Recent Posts -->
                <section>
                  <h2 class="major"><span>아파트 거래 정보</span></h2>
                  <ul v-if="apts[0] != null" class="divided">
                    <apt-list-row v-for="(apt, index) in apts" :key="`${index}_apts`" :apt="apt" />
                  </ul>
                  <ul v-else class="divided">
                    <h5>매물이 없습니다.</h5>
                  </ul>
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
import AptListRow from "@/components/apt/AptListRow.vue";
import { mapGetters } from "vuex";
const mapMarker = require("@/assets/css/images/apartment.png");
export default {
  name: "search",
  components: {
    AptListRow,
  },
  data() {
    return {
      center: {
        lat: 37.5724163,
        lng: 126.9824791,
      },
      apts: [],
      aptdetail: [],
      areainterest: [],
      sidos: {},
      guguns: {},
      dongs: {},
      sidocode: 0,
      guguncode: 0,
      dong: "",
      aptname: "",
      markerOptions: {
        url: mapMarker,
        size: { width: 60, height: 90, f: "px", b: "px" },
        scaledSize: { width: 30, height: 45, f: "px", b: "px" },
      },
    };
  },
  computed: {
    ...mapGetters(["getUserId"]),
  },
  created() {
    http
      .get("/housesearch2/house")
      .then(({ data }) => {
        this.sidos = data;
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
    http.get("/member/getareainterest/" + this.getUserId).then(({ data }) => {
      this.areainterest = data;
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
    getGugun(event) {
      http
        .get("/housesearch2/house/" + event.target.value)
        .then(({ data }) => {
          this.sidocode = event.target.value;
          this.guguns = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    getDong(event) {
      http
        .get("/housesearch2/house/" + this.sidocode + "/" + event.target.value)
        .then(({ data }) => {
          this.guguncode = event.target.value;
          this.dongs = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    getInfo(event) {
      http
        .get(
          "/housesearch2/house/" + this.sidocode + "/" + this.guguncode + "/" + event.target.value
        )
        .then(({ data }) => {
          this.apts = data;
          this.dong = event.target.value;
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
    },
    searchInterest() {
      if (this.$store.state.userid == null) {
        alert("회원가입 후 이용가능합니다.");
        return;
      }
      http
        .get("/housesearch2/interest/" + this.$store.state.userid)
        .then(({ data }) => {
          this.apts = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
    addInterest() {
      if (this.$store.state.userid == null) {
        alert("회원가입 후 이용가능합니다.");
        return;
      }
      if (this.areainterest.length >= 5) {
        alert("등록할 수 있는 관심지역 수를 초과하였습니다.");
      } else {
        http
          .post("/member/addinterest", {
            userid: this.$store.state.userid,
            dong: this.dong,
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "관심 지역 추가가 완료되었습니다.";
            }
            alert(msg);
          })
          .catch(() => {
            alert("관심지역추가시 문제가 발생했습니다.");
          });
      }
    },
  },
};
</script>

<style></style>
