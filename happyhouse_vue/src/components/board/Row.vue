<template>
  <tr>
    <td>
      <h2>{{ no }}</h2>
    </td>
    <td>
      <h2>
        <router-link :to="`/read?no=${no}`">{{ title }}</router-link>
      </h2>
    </td>
    <td>
      <h2>{{ writer }}</h2>
    </td>
    <td>
      <h2>{{ getFormatDate(regtime) }}</h2>
    </td>
  </tr>
</template>

<script>
import moment from "moment";
export default {
  name: "row",
  props: {
    no: { type: Number },
    writer: { type: String },
    title: { type: String },
    regtime: { type: String },
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
  },
};
</script>
<style scoped>
h2 {
  font-size: 100%;
  border-top: solid 0px #e7eae8;
  color: #c1cac5;
}
</style>
