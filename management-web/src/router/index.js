import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/views/layout/index'

import Axios from 'axios'

const _import = require('./_import_' + process.env.NODE_ENV)
let getRouter

Vue.use(Router)
const constantRouterMap = [
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'home',
    children: [{
      path: 'home',
      name: 'Home',
      component: () => import('@/views/home'),
      meta: { title: 'Home', icon: 'el-icon-s-home' }
    }]
  },
  {
    path: '/home',
    component: Layout,
    redirect: '/home',
    meta: {title: '首页', icon: ''},
    children: [{
      path: 'home',
      component: () => import('@/views/home'),
      meta: {title: '首页', icon: 'el-icon-s-home'}
    }]
  }
]

const router = new Router({
  routes: constantRouterMap
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    window.SITE_CONFIG['dynamicMenuRoutesHasAdded'] = false
    return next()
  }

  let token = localStorage.getItem('Authorization')
  if (token === null || token === '') {
    return next('/login')
  }

  // 不加这个判断，路由会陷入死循环
  if (!window.SITE_CONFIG['dynamicMenuRoutesHasAdded']) {
    // 请求后台的菜单列表，解析生成菜单栏
    Axios.get('/permission/list').then(res => {
      getRouter = res.data.data
      window.SITE_CONFIG['dynamicMenuRoutesHasAdded'] = true
      // 执行路由跳转方法
      routerGo(to, next)
    }).catch(() => {
      next('/login')
    })
  } else {
    next()
  }
})

/**
 * 路由跳转
 * @param to
 * @param next
 */
function routerGo (to, next) {
  // 过滤路由
  getRouter = filterAsyncRouter(getRouter)
  // 动态添加路由
  router.addRoutes(getRouter)
  saveObjArr('router', getRouter)
  next({ ...to, replace: true })
}

/**
 * localStorage 存储数组对象的方法
 * @param name
 * @param data
 */
function saveObjArr (name, data) {
  localStorage.setItem(name, JSON.stringify(data))
}

/**
 * localStorage 获取数组对象的方法
 * @param name
 * @returns {any}
 */
function getObjArr (name) {
  return JSON.parse(window.localStorage.getItem(name))
}

/**
 * 遍历后台传来的路由字符串，转换为组件对象
 * @param asyncRouterMap
 * @returns {*}
 */
function filterAsyncRouter (asyncRouterMap) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (route.component) {
      // Layout组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = _import(route.component)
      }
    }
    if (route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })
  return accessedRouters
}

export default router
