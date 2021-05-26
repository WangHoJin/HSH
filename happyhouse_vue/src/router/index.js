import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/index.vue";

import List from "../views/board/list.vue";
import Create from "../views/board/create.vue";
import Read from "../views/board/read.vue";
import Update from "../views/board/update.vue";
import Delete from "../views/board/delete.vue";

import Login from "../views/member/Login.vue";
import Regist from "../views/member/Regist.vue";
import Modify from "../views/member/Modify.vue";
import Addinterest from "../views/member/Addinterest.vue";

import Housesearch from "../views/apt/housesearch.vue";
import HouseDetail from "../views/apt/housedetail.vue";
import Ranking from "../views/index.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Index",
    component: Index,
  },
  {
    path: "/list",
    name: "list",
    component: List,
  },
  {
    path: "/create",
    name: "create",
    component: Create,
  },
  {
    path: "/read",
    name: "read",
    component: Read,
  },
  {
    path: "/update",
    name: "update",
    component: Update,
  },
  {
    path: "/delete",
    name: "delete",
    component: Delete,
  },
  {
    path: "/housesearch",
    name: "housesearch",
    component: Housesearch,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/regist",
    name: "regist",
    component: Regist,
  },
  {
    path: "/addinterest",
    name: "addinterest",
    component: Addinterest,
  },
  {
    path: "/modify",
    name: "modify",
    component: Modify,
  },
  {
    path: "/housedetail",
    name: "housedetail",
    component: HouseDetail,
  },
  {
    path: "/",
    name: "ranking",
    component: Ranking,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else if (to.hash) {
      return {
        selector: to.hash,
      }
    }
    else {
      return { x: 0, y: 0 }
    }
    }
  
});

export default router;
