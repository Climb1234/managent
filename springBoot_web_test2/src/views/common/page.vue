<template>
  <el-container style="height: 100%;">
    <el-aside style="width:200px;background-color: #283443;">
      <el-menu
        default-active="defaultAsideIndex"
        @select="handleAsideSelect"
        background-color="#283443"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-menu-item index="0">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>test</span>
          </template>
          <el-menu-item index="1-1" path="/test/test1">test1</el-menu-item>
          <el-menu-item index="1-2" path="/test/test1">test2</el-menu-item>
        </el-submenu>
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span slot="title">导航二</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-setting"></i>
          <span slot="title">导航四</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <el-menu
          :default-active="defaultHeadIndex"
          mode="horizontal"
          @select="handleSelect">
          <!--<el-menu-item index="1"></el-menu-item>-->
          <el-submenu index="1" style="float: right">
            <template slot="title">我的工作台</template>
            <el-menu-item index="1-1">退出登录</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-header>
      <el-main><router-view/></el-main>
    </el-container>
  </el-container>
</template>

<script>
  import { mapMutations } from 'vuex'
  export default {
    name: 'App',
    data() {
      return {
        defaultHeadIndex: '',
        defaultAsideIndex: ''
      };
    },
    methods: {
      ...mapMutations(['removeToken']),
      handleSelect(key, keyPath) {
        if (key === '1-1'){
          this.logout()
        }
      },
      handleAsideSelect(index,indexPath){
        if (index === '0'){
          this.$router.push('/home');
        }else if (indexPath[0] === '1'){
          if (indexPath[1] === '1-1'){
            this.$router.push('/test/test1');
          }else if(indexPath[1] === '1-2'){
            this.$router.push('/test/test2');
          }
        }
      },
      logout(){
        this.removeToken()
        this.$router.push('/login')
      }
    }
  }
</script>

<style scoped>
 .el-header {
    text-align: center;
    line-height: 60px;
  }

  .el-header .fr{

  }

  .el-aside {
    text-align: center;
    line-height: 200px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>
