import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import {library} from "@fortawesome/fontawesome-svg-core";
import {fas} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

library.add(fas);
Vue.component('vue-fontawesome', FontAwesomeIcon);

Vue.use(Buefy, {
  defaultIconComponent: 'vue-fontawesome',
  defaultIconPack: 'fa'
});

Vue.config.productionTip = false;
Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: () => import('@/components/HelloWorld')
  },
  {
    path: '/users',
    component: () => import('@/components/Users')
  },
  {
    path: '/newuser/:id',
    props: {
      id: id
    },
    component: () => import('@/components/UserForm')
  },
  {
    path: '/about',
    component: () => import('@/components/About')
  },
  {
    path: '/loginform',
    component: () => import('@/components/LoginForm')
  }
];

const router = new VueRouter({
  routes
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
