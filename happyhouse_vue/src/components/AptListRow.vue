<template>
  <tr>
    <td>{{ dong }}</td>
    <td>
      {{ aptName }}
    </td>
    <td>{{ buildYear }}</td>
  </tr>
</template>

<script>
export default {
  name: "aptrow",
  props: {
    dong: { type: String },
    aptName: { type: String },
    buildYear: { type: String },
  },
};
</script>
