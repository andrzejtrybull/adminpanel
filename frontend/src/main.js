import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'

Vue.config.productionTip = false;
Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: () => import('@/components/HelloWorld')
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
