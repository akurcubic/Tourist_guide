import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/HomePage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/destinations',
    name: 'AllDestinations',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AllDestinations.vue')
  },
  {
    path: '/most_popular',
    name: 'MostPopular',
    meta: {
      authRequired: false,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/MostPopular.vue')
  },
  {
    path: '/articles/destination/:id',
    name: 'articles-for-destination',
    meta: {
      authRequired: true,
    },
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/ArticlesForDestination.vue')
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginPage.vue')
  },
  {
    path: '/destinations_for_reading',
    name: 'Destinations',
    meta: {
      authRequired: false,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Destinations.vue')
  },
  {
    path: '/articles_for_reading',
    name: 'ArticlesForReading',
    meta: {
      authRequired: false,
    },
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/ArticleForReading.vue')
  },
  {
    path: '/single-article/:id',
    name: 'SingleArticle',
    meta: {
      authRequired: false,
    },
    props: route => ({ articleId: parseInt(route.params.id) }),
    component: () => import(/* webpackChunkName: "single-article" */ '../components/SingleArticle.vue')
  },
  {
    path: '/articles-for-activity/:activityId', // Definišite rutu sa parametrom activityId
    name: 'ArticlesForActivity',
    meta: {
      authRequired: false,
    },
    component: () => import(/* webpackChunkName: "single-article" */ '../views/ArticlesForActivity.vue')
  },
  {
    path: '/all-articles', // Definišite rutu sa parametrom activityId
    name: 'AllArticles',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "single-article" */ '../views/AllArticles.vue')
  },
  {
    path: '/all-users', // Definišite rutu sa parametrom activityId
    name: 'AllUsers',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "single-article" */ '../views/AllUsers.vue')
  }

]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'LoginPage'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'LoginPage'});
      return;
    }
  }

  next();
});



export default router


