<template>
  <li>
    <article class="box post-summary">
      <h3>
        <router-link class="nav-link" to="/housedetail">{{ aptName }}</router-link>
      </h3>
      <ul class="meta">
        <li><h5>법정동 :</h5></li>
        <li>
          <h6>{{ dong }}</h6>
        </li>
        <br />
        <li><h5>거래가격 :</h5></li>
        <li>
          <h6>{{ dealAmount }}</h6>
        </li>
        <li><h5>거래날짜 :</h5></li>
        <li>
          <h6>{{ dealYear }}.{{ dealMonth }}.{{ dealDay }}</h6>
        </li>
        <li><h5>전용면적 :</h5></li>
        <li>
          <h6>{{ area }}</h6>
        </li>
      </ul>
    </article>
  </li>
</template>

<script>
export default {
  name: "aptrow",
  props: {
    dong: { type: String },
    aptName: { type: String },
    dealAmount: { type: String },
    dealYear: { type: String },
    dealMonth: { type: String },
    dealDay: { type: String },
    area: { type: String },
  },
};
</script>
