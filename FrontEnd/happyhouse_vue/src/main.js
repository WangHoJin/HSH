import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import * as VueGoogleMaps from "vue2-google-maps";

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

// Google Maps
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyCGl1i-Ux9jScPt5LxRSkDtkEN0legZ67E",
    libraries: "places",
  },
});

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "chart.js";
import "hchs-vue-charts";
Vue.use(window.VueCharts);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
