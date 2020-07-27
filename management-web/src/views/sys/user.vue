<template>
  <basic-container>
    <el-form :inline="true" :model="params" ref="searchForm" size="mini" label-width="100px">
      <el-form-item label="用户名:">
        <el-input v-model="params.username" placeholder="用户名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <el-button @click="handleClearSearch">清空</el-button>
      </el-form-item>
    </el-form>
    <el-button @click="handleAddOrEdit" type="primary" size="mini" >新增</el-button>
    <el-table :data="dataList" header-align="center" size="mini" border>
      <el-table-column type="selection" width="55" align="center"> </el-table-column>
      <el-table-column prop="id" label="ID" align="center"></el-table-column>
      <el-table-column prop="username" label="用户名" align="center"></el-table-column>
      <el-table-column prop="password" label="密码" align="center"></el-table-column>
      <el-table-column prop="roleName" label="角色" align="center"></el-table-column>
      <el-table-column prop="roleDescription" label="角色描述" align="center"></el-table-column>
      <el-table-column label="头像" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.avatar" :src="imgUrl+scope.row.avatar " width="50px" height="50px" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" >
        <template slot-scope="scope" >
          <el-link type="primary" v-if="scope.row.username!='admin'" @click="handleAddOrEdit(scope.row)" >编辑</el-link>
          <el-link type="primary" v-if="scope.row.username!='admin'" @click="handleDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :page-sizes="[10, 20, 50]" :page-size="page.pageSize" layout="total, sizes, prev, pager, next"
                   :total="page.total" :current-page.sync="page.pageNum">
    </el-pagination>
    <user-add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getList"></user-add-or-update>
  </basic-container>
</template>

<script>
import { getUserList, deleteUser } from '@/api/sys/user'
import UserAddOrUpdate from './components/user-add-or-update'
import { downloadImg } from '@/api/file'

export default {
  components: { UserAddOrUpdate },
  data () {
    return {
      addOrUpdateVisible: false,
      page: {
        pageNum: 1,
        pageSize: 10,
        total: 0
      },
      dataList: [],
      imgUrl: downloadImg(),
      params: {}
    }
  },
  created () {
    this.getList()
  },
  methods: {
    getList (page = this.page, params = {}) {
      getUserList(page.pageNum, page.pageSize, params).then(res => {
        if (res.data.success) {
          this.dataList = res.data.data.list
          this.page.total = res.data.data.total
        }
      })
    },
    handleSizeChange (val) {
      this.page.pageNum = 1
      this.page.pageSize = val
      this.getList(this.page)
    },
    handleCurrentChange (val) {
      this.page.pageNum = val
      this.getList(this.page)
    },
    handleAddOrEdit (row) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row.id)
      })
    },
    handleDelete (row) {
      this.$confirm('是否删除当前用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser(row.id).then(() => {
          this.$message.success('删除成功')
          this.getList()
        })
      }).catch(() => {
        this.$message.error('删除失败')
      })
    },
    handleSearch () {
      this.page.pageNum = 1
      this.getList(this.page, this.params)
    },
    handleClearSearch () {
      this.page.pageNum = 1
      this.params = {}
      this.getList(this.page, this.params)
    }
  }
}
</script>

<style scoped>

</style>
