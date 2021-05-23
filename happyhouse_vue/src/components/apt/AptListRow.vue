<template>
  <li>
    <article class="box post-summary">
      <h3>
        <a class="nav-link" @click="detail">{{ apt.aptName }}</a>
      </h3>
      <ul class="meta">
        <li><h4>법정동 :</h4></li>
        <li>
          <h5>{{ apt.dong }}</h5>
        </li>
        <br />
        <li><h4>건축연도 :</h4></li>
        <li>
          <h5>{{ apt.buildYear }}</h5>
        </li>
      </ul>
    </article>
  </li>
</template>

<script>
import { mapMutations, mapActions } from "vuex";
export default {
  name: "aptrow",

  props: {
    apt: {
      type: Object,
      default: function () {
        return {
          aptName: "",
          buildYear: "",
          dong: "",
          code: "",
          lat: "",
          lng: "",
        };
      },
    },
  },
  methods: {
    ...mapMutations(["SET_APT"]),
    ...mapActions(["setApt"]),
    detail() {
      console.log(this.apt);
      this.setApt(this.apt);
      this.$router.push("/housedetail");
    },
  },
};
</script>
