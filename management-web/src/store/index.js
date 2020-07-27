import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({

  state: {
    // 存储token
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    avatar: localStorage.getItem('avatar') ? localStorage.getItem('avatar') : ''
  },
  mutations: {
    // 修改token，并将token存入localStorage
    changeLogin (state, userInfo) {
      state.Authorization = userInfo.Authorization
      localStorage.setItem('Authorization', userInfo.Authorization)

      state.avatar = userInfo.avatar
      localStorage.setItem('avatar', userInfo.avatar)
    },
    removeToken () {
      localStorage.removeItem('Authorization')
      localStorage.removeItem('router')
      localStorage.removeItem('avatar')
    }
  }
})

export default store
