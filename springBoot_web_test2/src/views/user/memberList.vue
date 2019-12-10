<template>
  <div>
    <el-table
      :data="tableList"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
/* eslint-disable */
import member from '@/api/member'
  export default {
    data() {
      return {
        tableList: []
      }
    },
    //在这里调用ajax请求方法
    created () {
      this.convert();
    },
    methods: {
      convert: function () {
        member.getUserList().then(res => {
          this.tableList = res.data.data.list;
        })
      }
    }
  }
</script>

<style scoped>

</style>