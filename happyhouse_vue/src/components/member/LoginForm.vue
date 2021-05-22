<template>
  <!-- <div style="height: 400px; width: 400px; margin: 0 auto">
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
    </div> -->
  <!-- Modal footer -->
  <!-- <div class="text-right">
      <input type="submit" class="btn btn-primary" value="로그인" @click="login" />
    </div>
  </div> -->

  <div style="margin-top: 100px" class="container" ref="container">
    <div class="form-container sign-up-container">
      <form action="#">
        <h1>Create Account</h1>
        <div class="social-container">
          <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
          <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
          <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
        </div>
        <span>or use your email for registration</span>
        <input type="text" placeholder="Id" v-model="userid" />
        <input type="password" placeholder="Password" v-model="userpwd" />
        <input type="text" placeholder="Name" v-model="username" />
        <input type="text" placeholder="Address" v-model="address" />
        <input type="tel" placeholder="Tel" v-model="phone" />
        <button @click="registHandler">Sign Up</button>
      </form>
    </div>
    <div class="form-container sign-in-container">
      <form action="#">
        <h1>Sign in</h1>
        <div class="social-container">
          <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
          <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
          <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
        </div>
        <span>or use your account</span>
        <input type="text" placeholder="Id" v-model="userid" />
        <input type="password" placeholder="Password" v-m odel="userpwd" />
        <a href="#">Forgot your password?</a>
        <button @click="login">Sign In</button>
      </form>
    </div>
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-left">
          <h1>Welcome Back!</h1>
          <p>To keep connected with us please login with your personal info</p>
          <button class="ghost" @click="signInButton">Sign In</button>
        </div>
        <div class="overlay-panel overlay-right">
          <h1>Hello, Friend!</h1>
          <p>Enter your personal details and start journey with us</p>
          <button class="ghost" @click="signUpButton">Sign Up</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapMutations, mapActions } from "vuex";
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
    ...mapMutations({
      Mlogin: "M_LOGIN",
    }),
    ...mapActions(["asyncLogin"]),
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
            alert(msg);
            // this.$store.dispatch();
            // this.Mlogin(this.userid);
            // this.$store.commit("LOGIN", this.userid);
            this.asyncLogin(this.userid);
          } else {
            alert(msg);
          }

          this.$router.push("/");
        });
    },
    signUpButton() {
      const container = this.$refs.container;
      container.classList.add("right-panel-active");
    },

    signInButton() {
      const container = this.$refs.container;
      container.classList.remove("right-panel-active");
    },

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
          this.$router.push("/");
        });
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

form {
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
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
