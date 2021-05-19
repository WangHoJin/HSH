<template>
  <div style="height: 400px; width: 400px; margin: 0 auto">
    <div class="form-group">
      <select class="form-control mr-2" id="sido" @change="getGugun($event)">
        <option value="0">선택</option>
        <option v-for="(sido, index) in sidos" :key="`${index}_sidos`" :value="sido.sido_code">
          {{ sido.sido_name }}
        </option>
      </select>
    </div>
    <div class="form-group">
      <select class="form-control" id="gugun" @change="getDong($event)">
        <option value="0">선택</option>
        <option v-for="(gugun, index) in guguns" :key="`${index}_guguns`" :value="gugun.gugun_code">
          {{ gugun.gugun_name }}
        </option>
      </select>
    </div>
    <div class="form-group">
      <select class="form-control" id="dong" @change="getInfo($event)">
        <option value="0">선택</option>
        <option v-for="(dong, index) in dongs" :key="`${index}_dongs`" :value="dong.dong">
          {{ dong.dong }}
        </option>
      </select>
    </div>

    <div class="text-right">
      <input type="submit" class="btn btn-primary" @click="addInterest" value="추가" />
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "addinterest",
  props: {
    type: { type: String },
  },
  data: function () {
    return {
      userid: "",
      sidos: "",
      guguns: "",
      dongs: "",
      dong: "",
      sidocode: 0,
      guguncode: 0,
      dongcode: 0,
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
    addInterest() {
      http
        .post("/member/addinterest", {
          userid: sessionStorage.getItem("ID"),
          dong: this.dong,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "관심 지역 추가가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/");
        });
    },
    getInfo(event) {
      this.dong = event.target.value;
    },
  },
};
</script>
