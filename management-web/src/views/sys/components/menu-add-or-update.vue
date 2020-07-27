<template>
  <el-dialog :title="title" :visible.sync="visible" :before-close="handleClose">
    <div style="padding-bottom: 20px">
      <el-alert
        title="注意：1、一级菜单请求路径前需加‘/’；2、菜单添加成功后需要配置相应角色的菜单权限"
        type="warning">
      </el-alert>
    </div>
    <el-form :model="formData" :rules="rules" ref="formData" style="width: 80%;" size="mini">
      <el-form-item label="上级菜单" prop="pid" :label-width="formLabelWidth">
        <el-popover v-model="menuListVisible" ref="menuListPopover" placement="bottom-start" trigger="click" >
          <el-tree
            :data="menuList"
            :props="defaultProps"
            node-key="id"
            ref="menuListTree"
            :highlight-current="true"
            :expand-on-click-node="false"
            accordion
            @current-change="handleMenuListTreeCurrentChange">
          </el-tree>
        </el-popover>
        <el-input v-model="formData.parentName" v-popover:menuListPopover :readonly="true" placeholder="上级菜单">
          <i v-if="formData.pid !== 0" slot="suffix" @click="menuListTreeSetDefaultHandle()" class="el-icon-circle-close"></i>
        </el-input>
      </el-form-item>
      <el-form-item label="名称" prop="title" :label-width="formLabelWidth">
        <el-input v-model="formData.title" placeholder="名称" clearable ></el-input>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="图标" prop="icon" :label-width="formLabelWidth">
            <el-popover v-model="iconListVisible" ref="iconListPopover" placement="bottom-start" trigger="click" class="menu-icon-popover">
              <div class="menu-icon-inner">
                <div class="menu-icon-list">
                  <el-button
                    v-for="(item, index) in iconList"
                    :key="index"
                    @click="iconListCurrentChangeHandle(item)"
                    :class="{ 'is-active': formData.icon === item }">
                    <i :class="item"></i>
                  </el-button>
                </div>
              </div>
            </el-popover>
            <el-input v-model="formData.icon" v-popover:iconListPopover :readonly="true" placeholder="图标"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序" prop="component" :label-width="formLabelWidth">
            <el-input-number v-model="formData.sort" :min="1" :max="100" label="排序"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="请求路径" prop="path" :label-width="formLabelWidth">
        <el-input v-model="formData.path" placeholder="请求路径" clearable ></el-input>
      </el-form-item>
      <!--<el-form-item label="资源路径" prop="component" :label-width="formLabelWidth">
        <el-input v-model="formData.component" placeholder="资源路径" clearable ></el-input>
      </el-form-item>-->
      <el-form-item class="form-submit-button">
        <el-button size="mini" @click="resetForm('formData')">重置</el-button>
        <el-button type="primary" size="mini" @click="submitFormData('formData')">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
import { detail, addOrUpdate, queryTree } from '@/api/sys/permission'
import { getElementIconList } from '@/utils'

export default {
  data () {
    let validatePath = (rule, value, callback) => {
      let valResult = /^\//.test(value)
      if (this.formData.pid === 0 && !valResult) {
        callback(new Error('一级菜单请求路径前需加‘/’'))
      } else if (this.formData.pid !== 0 && valResult) {
        callback(new Error('非一级菜单请求路径前不加‘/’'))
      }
      callback()
    }
    return {
      visible: false,
      title: '添加',
      formData: {
        sort: 1,
        pid: 0
      },
      formLabelWidth: '120px',
      menuList: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      menuListVisible: false,
      iconListVisible: false,
      iconList: [],
      rules: {
        title: { required: true, message: '请输入菜单名称', trigger: 'blur' },
        path: [{ required: true, message: '请输入请求路径', trigger: 'blur' },
          {validator: validatePath, trigger: 'blur'}]
      }
    }
  },
  methods: {
    init (id) {
      this.visible = true
      this.queryMenuList()
      this.iconList = getElementIconList()
      this.menuListTreeSetDefaultHandle()

      if (id) {
        this.title = '编辑'
        detail(id).then(res => {
          if (res.data.success) {
            this.formData = res.data.data
            if (this.formData.pid === 0) {
              this.formData.parentName = '一级菜单'
            }
          }
        })
      }
    },
    handleClose (done) {
      this.formData = {}
      done()
    },
    submitFormData (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addOrUpdate(this.formData).then(res => {
            if (res.data.success) {
              this.$message({
                message: '保存成功',
                type: 'success',
                duration: 500,
                onClose: () => {
                  this.formData = {}
                  this.visible = false
                  this.$emit('callback')
                }
              })
            } else {
              this.$message.error(res.data.message)
            }
          })
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    queryMenuList () {
      queryTree().then(res => {
        if (res.data.success) {
          this.menuList = res.data.data
        }
      })
    },
    // 上级菜单树, 设置默认值
    menuListTreeSetDefaultHandle () {
      this.formData.pid = 0
      this.formData.parentName = '一级菜单'
    },
    // 上级菜单树, 选中
    handleMenuListTreeCurrentChange (data) {
      this.formData.pid = data.id
      this.formData.parentName = data.label
      this.menuListVisible = false
    },
    // 图标, 选中
    iconListCurrentChangeHandle (icon) {
      this.formData.icon = icon
      this.iconListVisible = false
    }
  }
}
</script>

<style lang="scss">
  .menu-icon-popover {
    width: 458px;
    overflow: hidden;
  }
  .menu-icon-inner {
     width: 478px;
     max-height: 258px;
     overflow-x: hidden;
     overflow-y: auto;
   }
  .menu-icon-list {
     width: 458px;
     padding: 0;
     margin: -8px 0 0 -8px;
    > .el-button {
        padding: 8px;
        margin: 8px 0 0 8px;
      > span {
        display: inline-block;
        vertical-align: middle;
        width: 18px;
        height: 18px;
        font-size: 18px;
      }
    }
  }
</style>
