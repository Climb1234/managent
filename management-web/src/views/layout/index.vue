<template>
  <el-container style="height: 100%;">
    <el-menu class="el-menu-vertical-demo" mode="vertical" unique-opened :default-active="defaultAsideIndex"
      background-color="#283443" text-color="#fff" active-text-color="#ffd04b" style="height: 100%;">
      <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" ></sidebar-item>
    </el-menu>

    <el-container>
      <el-header>
        <el-row :gutter="20" style="height: 100%">
          <el-col :span="20" class="header-col-20">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">
                <span v-if="item.redirect==='noRedirect'||index==levelList.length-1" class="no-redirect">{{ item.meta.title }}</span>
                <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
              </el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
          <el-col :span="4" >
          <el-dropdown @command="handleCommand">
            <span>
              <img :src="avatarURL" style="width: 50px; height: 50px; margin-top: 5px">
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :command="composeValue('gitee')" >Gitee地址</el-dropdown-item>
              <el-tooltip class="item" effect="dark" content="推荐使用 Gitee 地址" placement="left">
                <el-dropdown-item :command="composeValue('github')" >Github地址</el-dropdown-item>
              </el-tooltip>
              <el-dropdown-item :command="composeValue('form-design')" >表单设计(Github)</el-dropdown-item>
              <el-dropdown-item :command="composeValue('logout')" >退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          </el-col>
        </el-row>
      </el-header>
      <el-main class="layout-main"><router-view/></el-main>
    </el-container>
  </el-container>
</template>

<script>
import { mapMutations } from 'vuex'
import SidebarItem from './SidebarItem'
import { logout } from '@/api/sys/user'
import { downloadImg } from '../../api/file'
import pathToRegexp from 'path-to-regexp'

export default {
  name: 'layout',
  components: { SidebarItem },
  computed: {
    routes () {
      return JSON.parse(window.localStorage.getItem('router'))
    }
  },
  data () {
    return {
      defaultHeadIndex: '',
      defaultAsideIndex: '',
      avatarURL: downloadImg() + this.$store.state.avatar,
      levelList: null
    }
  },
  watch: {
    $route (route) {
      if (route.path.startsWith('/redirect/')) {
        return
      }
      this.getBreadcrumb()
    }
  },
  methods: {
    ...mapMutations(['removeToken']),
    logoutSystem () {
      logout().then(res => {
        this.removeToken()
        this.$router.push('/login')
      })
    },
    getBreadcrumb () {
      // only show routes with meta.title
      let matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      this.levelList = matched.filter(item => item.meta && item.meta.title !== false)
    },
    pathCompile (path) {
      const { params } = this.$route
      let toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink (item) {
      const { redirect, path } = item
      if (redirect) {
        // this.$router.push(redirect)
        // return
      }
      // this.$router.push(this.pathCompile(path))
    },
    handleCommand (command) {
      switch (command.item) {
        case 'logout':
          this.logoutSystem()
          break
        case 'gitee':
          window.open('https://gitee.com/Climbin/management.git')
          break
        case 'github':
          window.open('https://github.com/Climb1234/managent.git')
          break
        case 'form-design':
          window.open('https://github.com/sscfaith/avue-form-design')
          break
        default:
          break
      }
    },
    composeValue (item) {
      return {
        'item': item
      }
    }
  }
}
</script>

<style scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
  }

  .layout-main {
    background: #f0f2f5;
    padding: 7px 5px;
  }

  .header-col-20 {
    height: 100%;
    display: flex;
    align-items: center;
  }
</style>
