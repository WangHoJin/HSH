<template>
  <tr>
    <td>
      <h6>{{ no }}</h6>
    </td>
    <td>
      <h6>
        <router-link :to="`/read?no=${no}`">{{ title }}</router-link>
      </h6>
    </td>
    <td>
      <h6>{{ writer }}</h6>
    </td>
    <td>
      <h6>{{ getFormatDate(regtime) }}</h6>
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
  font-size: 1.5em;
  border-top: solid 0px #e7eae8;
  color: #6d7776;
}

h6 {
  margin-bottom: 0;
}
</style>
