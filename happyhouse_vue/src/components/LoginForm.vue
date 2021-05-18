<template>
  <div style="height: 400px; width: 400px; margin: 0 auto">
    <div class="form-group">
      <label for="uname">아이디:</label>
      <input
        type="text"
        class="form-control"
        id="mID"
        placeholder="Enter ID"
        name="userid"
        required
        v-model="userid"
      />
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호:</label>
      <input
        type="password"
        class="form-control"
        id="mpwd"
        placeholder="Enter Password"
        name="userpwd"
        v-model="userpwd"
        required
      />
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <!-- Modal footer -->
    <div class="text-right">
      <input type="submit" class="btn btn-primary" value="로그인" @click="login" />
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "login",
  props: {
    type: { type: String },
  },
  data: function () {
    return {
      userid: "",
      userpwd: "",
    };
  },
  methods: {
    login() {
      if (this.userid == "") {
        alert("아이디를 입력해주세요");
        return;
      }
      if (this.userpwd == "") {
        alert("비밀번호를 입력해주세요.");
        return;
      }
      http
        .post("/member/login", {
          userid: this.userid,
          userpwd: this.userpwd,
        })
        .then(({ data }) => {
          let msg = "로그인 실패";
          if (data === "success") {
            msg = "로그인 성공";
          } else {
            alert(msg);
          }
          this.$router.push("/");
        });
    },
  },
};
</script>
