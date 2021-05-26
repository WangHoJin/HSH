<template>
  <div>
    <section id="main">
      <h2 class="major"><span>게시판</span></h2>
      <div class="container" style="width: 70%">
        <div v-if="items.length">
          <table class="table table-bordered table-condensed">
            <colgroup>
              <col :style="{ width: '10%' }" />
              <col :style="{ width: '50%' }" />
              <col :style="{ width: '10%' }" />
              <col :style="{ width: '25%' }" />
            </colgroup>
            <tr id="htr">
              <th><h2>번호</h2></th>
              <th><h2>제목</h2></th>
              <th><h2>작성자</h2></th>
              <th><h2>날짜</h2></th>
            </tr>
            <list-row
              v-for="(item, index) in items"
              :key="`${index}_items`"
              :no="item.no"
              :title="item.title"
              :writer="item.writer"
              :regtime="item.regtime"
            />
          </table>
        </div>
        <div v-else><h2>글이 없습니다.</h2></div>
        <div class="text-right">
          <button class="button" @click="movePage">등록</button>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/util/http-common";
import ListRow from "@/components/board/Row.vue";
export default {
  name: "list",
  components: {
    ListRow,
  },
  data: function () {
    return {
      items: [],
    };
  },
  created() {
    http
      .get("/board")
      .then(({ data }) => {
        this.items = data;
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  },
  methods: {
    movePage() {
      if (this.$store.state.userid == null) {
        alert("회원가입 후 이용가능합니다.");
      } else {
        this.$router.push("/create");
      }
    },
  },
};
</script>

<style>
h2 {
  font-size: 1.5em;
  border-top: solid 0px #e7eae8;
}
#htr {
  background-color: #f2f5f3;
}
#htr h2 {
  font-size: 1.5em;
  margin-bottom: 0;
}
</style>
