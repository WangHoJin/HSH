import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import List from '../views/list.vue';
import Create from '../views/create.vue';
import Read from '../views/read.vue';
import Update from '../views/update.vue';
import Delete from '../views/delete.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/list',
    name: 'list',
    component: List,
  },
  {
    path: '/create',
    name: 'create',
    component: Create,
  },
  {
    path: '/read',
    name: 'read',
    component: Read,
  },
  {
    path: '/update',
    name: 'update',
    component: Update,
  },
  {
    path: '/delete',
    name: 'delete',
    component: Delete,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
