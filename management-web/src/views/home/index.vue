<template>
  <el-container class="height-100 home-container">
    <el-main class="height-100 home-main">
      <el-row :gutter="10" class="height-100">
        <el-col :span="8" class="height-100">
          <el-card class="el-main-row-col-card">
            <h1 class="el-main-row-col-card-h1">前端</h1>
            <p>nodeJs + Vue</p>
            <p>Element-ui</p>
            <p>Axios</p>
            <p>Router</p>
            <p>Avue</p>
          </el-card>
        </el-col>
        <el-col :span="8" class="height-100">
          <el-card class="el-main-row-col-card">
            <h1 class="el-main-row-col-card-h1">后端</h1>
            <p>SpringBoot + Mybatis</p>
            <p>Swagger：Api文档</p>
            <p>PageHelper: 实现列表分页查询 </p>
            <p>Cors: 跨域</p>
            <p>jjwt: 生成Token</p>
            <p>Flyway: 自动更新Sql文件</p>
            <p>Shiro: 安全框架</p>
            <p>EasyExcel: 阿里开源Excel导入/出</p>
            <p>Orika：类型转换</p>
            <p>数据、异常返回统一格式</p>
          </el-card>
        </el-col>
        <el-col :span="8" class="height-100">
          <el-card class="el-main-row-col-card">
            <h1 class="el-main-row-col-card-h1">改进</h1>
            <p>头像重名</p>
            <p>Token验证（现只是 Shiro 单独验证）</p>
            <p>加入 Redis </p>
            <p>Mybatis-plus</p>
            <p>代码自动生成</p>
            <p>SpringCloud Alibaba </p>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
    <el-aside width="255px">
      <el-card class="home-pay-card">
        <el-row style="margin-bottom: 10px">
          <h1>感谢支持</h1>
        </el-row>
        <el-row>
          <img class="image-pay" src="../../assets/alipay.jpg">
        </el-row>
        <el-row>
          <img class="image-pay" src="../../assets/wechat.jpg">
        </el-row>
      </el-card>
    </el-aside>
  </el-container>
</template>

<script>
export default{}
</script>

<style scoped lang="scss">
  .home-container {
    min-height: 500px;
  }
  .home-main {
    padding: 0px 5px;
  }
  .el-main-row-col-card {
    height: 99%;
    text-align: left;
  }
  .el-main-row-col-card-h1 {
    text-align: center;
    margin-bottom: 20px;
  }

  .image-pay {
    width: 200px;
    height: 200px;
  }
  .home-pay-card {
    width: 250px;
    height: 99%;
    margin-right: 5px;
  }
</style>
