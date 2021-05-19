<template>
  <tr>
    <td>{{ dong }}</td>
    <td>
      {{ aptName }}
    </td>
    <td>{{ dealAmount }}</td>
    <td>{{ dealYear }}</td>
    <td>{{ area }}</td>
  </tr>
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
