<template>
  <div>
    <section id="main">
      <h2 class="text-center">Q&A</h2>
      <div class="container" style="width: 70%">
        <div v-if="items.length">
          <table class="table table-bordered table-condensed">
            <colgroup>
              <col :style="{ width: '5%' }" />
              <col :style="{ width: '50%' }" />
              <col :style="{ width: '10%' }" />
              <col :style="{ width: '25%' }" />
            </colgroup>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
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
        <div v-else>글이 없습니다.</div>
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

<style></style>
