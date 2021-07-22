<template>
  <div>
    <login-form type="login" />
  </div>
</template>

<script>
import LoginForm from "@/components/member/LoginForm.vue";
export default {
  name: "login",
  components: {
    LoginForm,
  },
};
</script>
