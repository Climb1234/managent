<template>
  <el-dialog :title="title" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" style="width: 80%;align-items: center">
      <el-form-item label="角色名" prop="roleName" :label-width="formLabelWidth">
        <el-input v-model="formData.roleName" size="mini" placeholder="角色名称" clearable ></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="description" :label-width="formLabelWidth">
        <el-input v-model="formData.description" size="mini" placeholder="描述" clearable ></el-input>
      </el-form-item>
      <el-form-item label="菜单授权" prop="menuList" :label-width="formLabelWidth">
        <el-tree
          :data="menuList"
          show-checkbox
          ref="tree"
          node-key="id"
          :props="defaultProps">
        </el-tree>
      </el-form-item>
      <el-form-item class="form-submit-button">
        <el-button size="mini" @click="resetForm('formData')">重置</el-button>
        <el-button type="primary" size="mini" @click="submitFormData('formData')">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
import { detail, addOrUpdate } from '@/api/sys/role'
import { queryTree } from '@/api/sys/permission'

export default {
  data () {
    return {
      visible: false,
      title: '角色添加',
      formData: {},
      formLabelWidth: '120px',
      rules: {
        roleName: [
          { required: true, message: '不可为空', trigger: 'blur' }
        ]
      },
      menuList: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods: {
    init (id) {
      this.visible = true
      this.queryMenuList()

      if (id) {
        this.title = '角色编辑'
        detail(id).then(res => {
          if (res.data.success) {
            this.formData = res.data.data
            this.$refs.tree.setCheckedKeys(res.data.data.permissionIdList)
          }
        })
      }
    },
    handleClose (done) {
      this.formData = {}
      this.$refs.tree.setCheckedKeys([])
      done()
    },
    submitFormData (formName) {
      this.formData.permissionIdList = this.$refs.tree.getCheckedKeys()
      if (this.formData.permissionIdList.length < 1) {
        this.$message.error('请选择授权的菜单')
        return
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addOrUpdate(this.formData).then(res => {
            if (res.data.success) {
              this.$message({
                message: '修改成功',
                type: 'success',
                duration: 500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error('修改失败')
            }
          })
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.$refs.tree.setCheckedKeys([])
    },
    queryMenuList () {
      queryTree().then(res => {
        if (res.data.success) {
          this.menuList = res.data.data
        }
      })
    }
  }
}
</script>

<style>
</style>
