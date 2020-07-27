<template>
  <basic-container>
    <el-form :inline="true" :model="params" ref="searchForm" size="mini" label-width="100px">
      <el-form-item label="角色名:">
        <el-input v-model="params.roleName" placeholder="角色名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <el-button @click="handleClearSearch">清空</el-button>
      </el-form-item>
    </el-form>
    <el-button @click="handleAddOrEdit" type="primary" size="mini" >新增</el-button>
    <el-table :data="dataList" header-align="center" size="mini" border>
      <el-table-column type="selection" width="50" align="center"> </el-table-column>
      <el-table-column type="index" width="50" align="center"></el-table-column>
      <el-table-column prop="roleName" label="角色名" align="center"></el-table-column>
      <el-table-column prop="description" label="描述" align="center"></el-table-column>
      <el-table-column prop="gmtCreate" label="创建时间" align="center" ></el-table-column>
      <el-table-column label="操作" align="center" >
        <template slot-scope="scope" >
          <el-link type="primary" v-if="scope.row.roleName!='超级管理员'" @click="handleAddOrEdit(scope.row)" >编辑</el-link>
          <el-link type="primary" v-if="scope.row.roleName!='超级管理员'" @click="handleDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :page-sizes="[10, 20, 50]" :page-size="page.pageSize" layout="total, sizes, prev, pager, next"
                   :total="page.total" :current-page.sync="page.pageNum">
    </el-pagination>
    <role-add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getList"></role-add-or-update>
  </basic-container>
</template>

<script>
import { getPageList, remove } from '@/api/sys/role'
import RoleAddOrUpdate from './components/role-add-or-update'

export default {
  components: { RoleAddOrUpdate },
  data () {
    return {
      addOrUpdateVisible: false,
      page: {
        pageNum: 1,
        pageSize: 10,
        total: 0
      },
      dataList: [],
      params: {}
    }
  },
  created () {
    this.getList()
  },
  methods: {
    getList (page = this.page, params = {}) {
      getPageList(page.pageNum, page.pageSize, params).then(res => {
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
        remove(row.id).then(() => {
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
