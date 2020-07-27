// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import './utils/axios'
import store from './store'

import './styles/common.scss'
import basicContainer from './components/basicContainer'

import Avue from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'
import AvueFormDesign from '@sscfaith/avue-form-design'
Vue.use(Avue)
Vue.use(AvueFormDesign)

Vue.config.productionTip = false
Vue.use(ElementUI)
// 全局容器
Vue.component('basicContainer', basicContainer)

/* eslint-disable no-new */
new Vue({
  store,
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
