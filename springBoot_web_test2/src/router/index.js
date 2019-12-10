import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
const router = new Router({
  routes: [{
    path: '/',
    redirect: '/home'
  },{
    path: '/login',
    name: 'login',
    component: () => import('@/views/login')
  },{
    path: '/home',
    name: 'page',
    component: () => import('@/views/common/page'),
    children: [{
      path: '/home',
      name: 'home',
      component: () => import('@/views/home/index')
    },{
      path: '/test/test1',
      name: 'test1',
      component: () => import('@/views/test/test1')
    },{
      path: '/test/test2',
      name: 'test2',
      component: () => import('@/views/test/test2')
    }]
  }]
})

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    let token = localStorage.getItem('Authorization')
    if (token === null || token === '') {
      next('/login')
    } else {
      next()
    }
  }
})

export default router
