<script>
import http from "@/util/http-common";
//Importing Bar class from the vue-chartjs wrapper
import { Bar } from "vue-chartjs";
//Exporting this so it can be used in other components
export default {
  extends: Bar,
  data() {
    return {
      datacollection: {
        //Data to be represented on x-axis
        labels: [
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
          "October",
          "November",
          "December",
        ],
        datasets: [
          {
            label: "Data One",
            backgroundColor: "#f87979",
            pointBackgroundColor: "white",
            borderWidth: 1,
            pointBorderColor: "#249EBF",
            //Data to be represented on y-axis
            data: [40, 20, 30, 50, 90, 10, 20, 40, 50, 70, 90, 100],
          },
        ],
      },
      //Chart.js options that controls the appearance of the chart
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
        legend: {
          display: true,
        },
        responsive: true,
        maintainAspectRatio: false,
        onClick: this.handleClick,
      },
    };
  },
  created() {
    console.log("생성");
  },
  mounted() {
    //renderChart function renders the chart with the datacollection and options object.
    console.log("렌더링");
    this.renderChart(this.datacollection, this.options);
    http
      .get("/coffeeshop/dongrank")
      .then(({ coffee }) => {
        for (let index = 0; index < 3; index++) {
          console.log(coffee);
          console.log(coffee[index].dong);
          // this.labels.push(data[index].dong);
          // this.data.push(data[index].cnt);
        }
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
    this.renderChart(this.datacollection, this.options);
  },
  methods: {
    handleClick() {
      console.log("클릭");
    },
  },
};
</script>
