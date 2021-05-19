<template>
  <div id="search" class="container">
    <GmapMap ref="mapRef" :center="center" :zoom="16" style="width: 100%; height: 300px"></GmapMap>
    <!-- 아파트 거래정보 검색 시작 -->
    <div class="d-flex justify-content-center mb-3 mt-3">
      <div class="dark-bg section">
        <div class="container-fluid" style="margin-bottom: 10px">
          <!-- filters start -->
          <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
            <button type="button" class="btn btn-dark mr-3" id="searchall" @click="searchAll">
              전체 조회
            </button>

            <form class="form-inline ml-3 mr-3" id="frm" method="post" action="housesearch">
              <input type="hidden" name="act" id="act" value="search" />
              <label for="dongsearch" style="display: inline-block; margin-right: 10px"
                >동별 검색</label
              >

              <div class="form-group md">
                <select class="form-control mr-2" id="sido" @change="getGugun($event)">
                  <option value="0">선택</option>
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
                  <option value="0">선택</option>
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
                  <option value="0">선택</option>
                  <option v-for="(dong, index) in dongs" :key="`${index}_dongs`" :value="dong.dong">
                    {{ dong.dong }}
                  </option>
                </select>
              </div>
            </form>

            <form class="form-inline ml-3" id="frm" method="post" action="housesearch">
              <div>
                <input type="hidden" name="act" value="aptsearch" />
                <label for="aptname" style="display: inline-block; margin-right: 10px"
                  >아파트별 검색</label
                >
                <input type="text" id="apttext" v-model="aptname" />
                <input
                  type="button"
                  id="aptsearch"
                  class="btn btn-dark"
                  value="검색"
                  @click="searchName"
                />
              </div>
            </form>

            <button
              type="button"
              class="form-inline ml-3 btn btn-warning"
              id="searchinterest"
              @click="searchInterest"
            >
              관심지역
            </button>
          </div>
          <!-- filters end -->
        </div>
      </div>
    </div>
    <!-- 아파트 거래정보 끝 -->

    <!-- 중앙 콘텐츠 시작 -->
    <div data-spy="scroll" data-offset="50">
      <!-- 모든집의정보 받아오기 시작 -->
      <div class="row">
        <div class="col-lg-6">
          <div class="card" style="height: 400px; overflow: auto">
            <div class="card-header bg-dark text-white">
              <h4>아파트 거래 정보</h4>
            </div>
            <div class="card-body" style="padding:0;'">
              <table id="houseinfo-list" class="table table-striped">
                <thead>
                  <tr>
                    <th>법정동</th>
                    <th>아파트명</th>
                    <th>건축년도</th>
                  </tr>
                </thead>
                <tbody id="searchResult">
                  <apt-list-row
                    v-for="(apt, index) in apts"
                    :key="`${index}_apts`"
                    :dong="apt.dong"
                    :aptName="apt.aptName"
                    :buildYear="apt.buildYear"
                  />
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <div class="col-lg-6">
          <div class="card" style="height: 400px; overflow: auto">
            <div class="card-header bg-dark text-white">
              <h4>아파트 거래 상세 정보</h4>
            </div>
            <div class="card-body" style="padding:0;'">
              <table id="houseinfo-list" class="table table-striped">
                <thead>
                  <tr>
                    <th>법정동</th>
                    <th>아파트명</th>
                    <th>거래가격</th>
                    <th>거래날짜</th>
                    <th>전용면적</th>
                  </tr>
                </thead>
                <tbody id="detailResult">
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
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <!-- 모든집의정보 받아오기 끝 -->
    </div>
    <!-- 컨테이너 끝 -->
  </div>
</template>

<script>
import http from "@/util/http-common";
import AptListRow from "@/components/apt/AptListRow.vue";
import AptDetailListRow from "@/components/apt/AptDetailListRow.vue";
export default {
  name: "search",
  components: {
    AptListRow,
    AptDetailListRow,
  },
  data() {
    return {
      center: {
        lat: 37.5,
        lng: 127.0324,
      },
      apts: [],
      aptdetail: [],
      sidos: {},
      guguns: {},
      dongs: {},
      sidocode: 0,
      guguncode: 0,
      aptname: "",
    };
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
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      http
        .get(
          "/housesearch2/housedeal/" +
            this.sidocode +
            "/" +
            this.guguncode +
            "/" +
            event.target.value
        )
        .then(({ data }) => {
          console.log(data);
          this.aptdetail = data;
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
  },
};
</script>

<style></style>
