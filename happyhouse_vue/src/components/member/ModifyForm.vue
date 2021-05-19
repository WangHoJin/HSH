<template>
  <div style="height: 500px; width: 500px; margin: 0 auto">
    <div class="form-group">
      <label for="usr">아이디* (아이디는 변경할 수 없습니다)</label>
      <input
        type="text"
        class="form-control"
        id="id"
        name="modify_userid"
        v-model="userid"
        value="userid"
        readonly
      />
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호* (영문 숫자 포함 6자리 이상)</label>
      <input
        type="password"
        class="form-control"
        id="pwd"
        v-model="userpwd"
        value="userpwd"
        name="modify_userpwd"
      />
    </div>
    <div class="form-group">
      <label for="usr">이름*</label>
      <input
        type="text"
        class="form-control"
        id="name"
        v-model="username"
        value="username"
        name="modify_username"
      />
    </div>
    <div class="form-group">
      <label for="usr">주소*</label>
      <input
        type="text"
        class="form-control"
        id="addr"
        v-model="address"
        value="address"
        name="modify_zipcode"
      />
    </div>
    <div class="form-group">
      <label for="usr">전화번호*</label>
      <input
        type="tel"
        class="form-control"
        id="tel"
        v-model="phone"
        value="phone"
        name="modify_usertel"
      />
    </div>

    <div class="text-right">
      <input type="submit" class="btn btn-primary" @click="modifyHandler" value="수정" />
      <input type="submit" class="btn btn-danger" @click="deleteHandler" value="탈퇴" />
    </div>
  </div>
</template>

<script>
import { mapMutations, mapActions } from "vuex";
import http from "@/util/http-common";
export default {
  name: "modify",
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
  created() {
    http.get("/member/select/" + this.$store.state.userid).then(({ data }) => {
      this.userid = data.userid;
      this.userpwd = data.userpwd;
      this.username = data.username;
      this.address = data.address;
      this.phone = data.phone;
    });
  },
  methods: {
    ...mapMutations(["mLogout"]),
    ...mapActions(["aLogout"]),
    modifyHandler() {
      http
        .put("/member/modify", {
          userid: this.userid,
          userpwd: this.userpwd,
          username: this.username,
          address: this.address,
          phone: this.phone,
        })
        .then(({ data }) => {
          let msg = "회원정보수정에 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/");
        });
    },
    deleteHandler() {
      http.delete("/member/delete/" + this.$store.state.userid).then(({ data }) => {
        let msg = "탈퇴 처리 시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "탈퇴가 완료되었습니다.";
        }
        alert(msg);
        this.aLogout();
        this.$router.push("/");
      });
    },
  },
};
</script>
