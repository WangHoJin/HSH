<template>
  <div>
    <section id="main">
      <div class="container w-50">
        <h2 style="text-align: center">Q&A</h2>
        <table class="table table-bordered">
          <tr>
            <th>번호</th>
            <td>{{ no }}</td>
          </tr>
          <tr>
            <th>글쓴이</th>
            <td>{{ writer }}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>{{ title }}</td>
          </tr>
          <tr>
            <th>날짜</th>
            <td>{{ getFormatDate(regtime) }}</td>
          </tr>
          <tr>
            <td colspan="2">
              {{ content }}
            </td>
          </tr>
        </table>

        <br />
        <div class="text-center">
          <router-link to="/list"><button class="button">목록</button></router-link>
          <router-link :to="'/update?no=' + no"
            ><button class="button" v-if="checkWriter">수정</button></router-link
          >
          <router-link :to="'/delete?no=' + no"
            ><button class="button" v-if="checkWriter">삭제</button></router-link
          >
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: "detail",
  data() {
    return {
      checkWriter: false,
      //userid: this.$store.state.userid,
    };
  },
  props: {
    no: { type: Number },
    writer: { type: String },
    title: { type: String },
    content: { type: String },
    regtime: { type: String },
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
    },
  },
  mounted() {
    //console.log($writer);
    console.log(this.$props.writer);
    if (this.$props.writer === this.$store.state.userid) {
      this.checkWriter = true;
    }
  },
};
</script>
