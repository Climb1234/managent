<template>
  <basic-container>
    <el-button @click="handleAddOrEdit" type="primary" size="mini" >新增</el-button>
    <el-table :data="dataList" header-align="center" size="mini" lazy :load="treeLoad"
              border row-key="id" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column type="index" width="50" align="center"></el-table-column>
      <el-table-column prop="title" label="名称" align="center"></el-table-column>
      <el-table-column prop="icon" label="图标" align="center"></el-table-column>
      <el-table-column prop="path" label="请求路径" align="center" ></el-table-column>
      <el-table-column prop="component" label="资源路径" align="center" ></el-table-column>
      <el-table-column prop="sort" label="排序" align="center" ></el-table-column>
      <el-table-column label="操作" align="center" >
        <template slot-scope="scope" >
          <el-link type="primary"  @click="handleAddOrEdit(scope.row)" >编辑</el-link>
          <el-link type="primary"  @click="handleDelete(scope.row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <menu-add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @callback="handleAddOrEditCallback"></menu-add-or-update>
  </basic-container>
</template>

<script>
import { listLazy, remove } from '@/api/sys/permission'
import MenuAddOrUpdate from './components/menu-add-or-update'

export default {
  components: { MenuAddOrUpdate },
  data () {
    return {
      addOrUpdateVisible: false,
      dataList: [],
      node: Object(),
      resolve: Object(),
      tree: Object()
    }
  },
  created () {
    this.getList()
  },
  methods: {
    getList () {
      listLazy().then(res => {
        if (res.data.success) {
          this.dataList = res.data.data
        }
      })
    },
    handleAddOrEdit (row) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row.id)
      })
    },
    handleAddOrEditCallback () {
      this.treeLoad(this.tree, this.treeNode, this.resolve)
    },
    handleDelete (row) {
      this.$confirm('是否删除当前用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        remove(row.id).then(res => {
          if (res.data.success) {
            this.$message.success('删除成功')
            this.treeLoad(this.tree, this.treeNode, this.resolve)
            this.getList()
          } else {
            this.$message.error(res.data.message)
          }
        })
      })
    },
    treeLoad (tree, treeNode, resolve) {
      this.tree = tree
      this.treeNode = treeNode
      this.resolve = resolve
      listLazy(tree.id).then(res => {
        if (res.data.success) {
          resolve(res.data.data)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
