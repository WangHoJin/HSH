<template>
  <div>
    <login-form type="login" />
  </div>
</template>

<script>
import LoginForm from "@/components/LoginForm.vue";
export default {
  name: "login",
  components: {
    LoginForm,
  },
};
</script>
