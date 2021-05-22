<template>
  <div style="height: 500px; width: 500px; margin: 0 auto">
    <div class="form-group">
      <label for="usr">아이디*</label>
      <input type="text" class="form-control" id="id" name="userid" v-model="userid" />
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호*</label>
      <input
        type="password"
        class="form-control"
        id="pwd"
        placeholder="영문 숫자 포함 6자리 이상"
        name="userpwd"
        v-model="userpwd"
      />
    </div>
    <div class="form-group">
      <label for="usr">이름*</label>
      <input
        type="text"
        class="form-control"
        id="name"
        placeholder="User Name"
        name="username"
        v-model="username"
      />
    </div>
    <div class="form-group">
      <label for="usr">주소*</label>
      <input
        type="text"
        class="form-control"
        id="addr"
        placeholder="address"
        name="zipcode"
        v-model="address"
      />
    </div>
    <div class="form-group">
      <label for="usr">전화번호*</label>
      <input
        type="tel"
        class="form-control"
        id="tel"
        placeholder="010-xxxx-xxxx"
        name="usertel"
        v-model="phone"
      />
    </div>
    <div class="text-right">
      <input type="submit" class="btn btn-primary" @click="registHandler" value="가입" />
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "regist",
  props: {
    type: { type: String },
  },
  data: function () {
    return {
      userid: "",
      userpwd: "",
      username: "",
      address: "",
      phone: "",
    };
  },
  methods: {
    registHandler() {
      http
        .post("/member/regist", {
          userid: this.userid,
          userpwd: this.userpwd,
          username: this.username,
          address: this.address,
          phone: this.phone,
        })
        .then(({ data }) => {
          let msg = "가입 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "가입이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/login");
        });
    },
  },
};
</script>
