<template>
  <pie-chart v-if="loaded" :chartdata="chartdata" :options="options"></pie-chart>
</template>
<script>
import http from "@/util/http-common";
import PieChart from "@/components/Charts/PieChart.vue";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      chartdata: null,
      options: null,
      labels: [],
      datas: [],
    };
  },
  props: {
    loaded: { type: Boolean },
  },
  components: {
    PieChart,
  },
  computed: {
    ...mapGetters(["getApt"]),
  },
  watch: {
    loaded() {
      console.log("와치");
      http
        .get("/coffeeshop/coffeeradiusrank/" + this.getApt.aptName + "/100")
        .then(({ data }) => {
          this.labels = [];
          this.datas = [];
          for (let index = 0; index < 5; index++) {
            this.labels.push(data[index].cname);
            this.datas.push(data[index].coffeeshopcnt);
          }
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
      this.fillData(this.labels, this.datas);
    },
  },
  async mounted() {
    console.log("create");
    http
      .get("/coffeeshop/coffeeradiusrank/" + this.getApt.aptName + "/100")
      .then(({ data }) => {
        this.labels = [];
        this.datas = [];
        for (let index = 0; index < 5; index++) {
          this.labels.push(data[index].cname);
          this.datas.push(data[index].coffeeshopcnt);
        }
        console.log(this.labels);
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
    console.log("no !!");
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
  },
};
</script>
<style></style>
