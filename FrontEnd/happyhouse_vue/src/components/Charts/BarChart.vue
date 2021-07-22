<script>
//Importing Bar class from the vue-chartjs wrapper
import { Bar } from "vue-chartjs";
// const { reactiveProp } = mixins;
//Exporting this so it can be used in other components
export default {
  extends: Bar,
  // mixins: [reactiveProp],
  props: {
    chartdata: {
      type: Object,
      default: null,
    },
    options: {
      type: Object,
      default: null,
    },
  },
  mounted() {
    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.chartdata, this.options);
  },
};
</script>
