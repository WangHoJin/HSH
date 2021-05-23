<template>
  <!-- <div style="height: 500px; width: 500px; margin: 0 auto">
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
  </div> -->

  <div class="container" ref="container">
    <div class="form-container sign-in-container">
      <div class="form-class">
        <h1>My page</h1>
        <div class="social-container">
          <a class="social"><i class="fab fa-facebook-f"></i></a>
          <a class="social"><i class="fab fa-google-plus-g"></i></a>
          <a class="social"><i class="fab fa-linkedin-in"></i></a>
        </div>
        <span>아이디는 변경이 불가능합니다.</span>
        <input type="text" v-model="userid" value="userid" readonly />
        <input type="password" v-model="userpwd" value="userpwd" />
        <input type="text" v-model="username" value="username" />
        <input type="text" v-model="address" value="address" />
        <input type="tel" v-model="phone" value="phone" />
        <div>
          <button style="margin-right: 15px; display: inline-block" @click="modifyHandler">
            수정
          </button>
          <button @click="deleteHandler" style="display: inline-block">탈퇴</button>
        </div>
      </div>
    </div>
    <div class="form-container sign-up-container">
      <div class="form-class-area">
        <h1>Areas of Interest</h1>
        <div class="social-container">
          <a class="social"><i class="fab fa-facebook-f"></i></a>
          <a class="social"><i class="fab fa-google-plus-g"></i></a>
          <a class="social"><i class="fab fa-linkedin-in"></i></a>
        </div>
        <span>관심지역은 최대 5개까지 설정가능합니다.</span>
        <ul class="list-group list-group-flush">
          <li
            style="text-align: left"
            class="list-group-item"
            v-for="(area, index) in areainterest"
            :key="index"
          >
            {{ area.city }} {{ area.gugun }} {{ area.dong }}
            <div style="float: right; margin-left: 10px">
              <button class="delete" @click="deleteConfirm(index)">삭제</button>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-left">
          <h1>My Page</h1>
          <p>회원정보 수정 및 탈퇴</p>
          <button class="ghost" @click="AreaInterestButtons">My Page</button>
        </div>
        <div class="overlay-panel overlay-right">
          <h1>Areas of Interest</h1>
          <p>관심 지역 관리</p>
          <button class="ghost" @click="UpdateButtons">Areas Of Interest</button>
        </div>
      </div>
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
      userid: this.userid,
      userpwd: this.userpwd,
      username: this.username,
      address: this.address,
      phone: this.phone,
      areainterest: [],
      is_show: false,
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
    http.get("/member/getareainterest/" + this.$store.state.userid).then(({ data }) => {
      this.areainterest = data;
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
          this.$router.push("/modify");
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
    deleteConfirm(index) {
      let result = confirm("삭제하시겠습니까?");
      if (result) {
        this.deleteArea(index);
      }
    },
    deleteArea(index) {
      http
        .delete(
          "member/deleteinterest/" +
            this.areainterest[index].userid +
            "/" +
            this.areainterest[index].dongcode
        )
        .then(({ data }) => {
          if (data !== "success") {
            alert("삭제 시 문제가 발생했습니다.");
          } else {
            this.areainterest.splice(index, 1);
          }
        });
    },
    UpdateButtons() {
      const container = this.$refs.container;
      container.classList.add("right-panel-active");
    },

    AreaInterestButtons() {
      const container = this.$refs.container;
      container.classList.remove("right-panel-active");
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Montserrat:400,800");

* {
  box-sizing: border-box;
}

body {
  background: #f6f5f7;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: "Montserrat", sans-serif;
  height: 100vh;
  margin: -20px 0 50px;
}

h1 {
  font-weight: bold;
  margin: 0;
}

h2 {
  text-align: center;
}

p {
  font-size: 14px;
  font-weight: 100;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

button {
  border-radius: 20px;
  border: 1px solid #b1ddab;
  background-color: #b1ddab;
  color: #ffffff;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #ffffff;
}

button.delete {
  border-radius: 10px;
  border: 1px solid #b1ddab;
  background-color: #b1ddab;
  color: #ffffff;
  font-size: 12px;
  font-weight: bold;
  padding: 10px 10px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
  display: inline-block;
}

.form-class {
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  text-align: center;
}
.form-class-area {
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 30px;
  height: 100%;
  text-align: center;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  width: 800px;
  max-width: 100%;
  min-height: 600px;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}

.sign-up-container {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
}

.container.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: show 0.6s;
}

@keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.container.right-panel-active .overlay-container {
  transform: translateX(-100%);
}

.overlay {
  background: #b1ddab;
  background: -webkit-linear-gradient(to right, #a8ece1, #b1ddab);
  background: linear-gradient(to right, #a8ece1, #b1ddab);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 0 0;
  color: #ffffff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  text-align: center;
  top: 0;
  height: 100%;
  width: 50%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
  transform: translateX(0);
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay-right {
  transform: translateX(20%);
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #dddddd;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}
</style>
