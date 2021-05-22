<template>
  <li>
    <article class="box post-summary">
      <h3>
        <a href="#">{{ aptName }}</a>
      </h3>
      <ul class="meta">
        <li><h4>법정동 :</h4></li>
        <li>
          <h5>{{ dong }}</h5>
        </li>
        <br />
        <li><h4>건축연도 :</h4></li>
        <li>
          <h5>{{ buildYear }}</h5>
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
    buildYear: { type: Number },
  },
};
</script>
