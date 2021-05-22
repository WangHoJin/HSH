<template>
  <tr>
    <td>{{ cname }}</td>
    <td>
      {{ dong }}
    </td>
    <td>{{ address1 }}</td>
  </tr>
</template>

<script>
export default {
  name: "coffeeshoprow",
  props: {
    cname: { type: String },
    dong: { type: String },
    address1: { type: String },
  },
};
</script>
