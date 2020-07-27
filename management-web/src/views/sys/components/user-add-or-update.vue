<template>
  <el-dialog :title="title" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="formData" :rules="rules" ref="formData" style="width: 80%;align-items: center">
      <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
        <el-input v-model="formData.username" size="mini" placeholder="用户名" clearable ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
        <el-input v-model="formData.password" size="mini" placeholder="密码" clearable ></el-input>
      </el-form-item>
      <el-form-item label="角色" :label-width="formLabelWidth">
        <el-select v-model="formData.roleId" placeholder="请选择活动区域" style="width: 100%" size="mini">
          <el-option v-for="role in roleList" :key="role.id" :label="role.roleName" :value="role.id" ></el-option>
        </el-select>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="头像" :label-width="formLabelWidth">
            <el-upload class="avatar-uploader" :action="uploadUrl" :show-file-list="false"
                       :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item style="text-align: center">
        <el-button size="mini" @click="resetForm('formData')">重置</el-button>
        <el-button type="primary" size="mini" @click="submitFormData('formData')">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
import { getUser, addOrUpdateUser } from '@/api/sys/user'
import { getRoleList } from '@/api/sys/role'
import { uploadFile, downloadImg } from '@/api/file'

export default {
  data () {
    return {
      visible: false,
      title: '用户添加',
      formData: {},
      formLabelWidth: '120px',
      roleList: [],
      imageUrl: '',
      uploadUrl: uploadFile(),
      rules: {
        username: [
          { required: true, message: '不可为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '不可为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (userId) {
      this.visible = true

      if (userId) {
        this.title = '用户编辑'
        getUser(userId).then(res => {
          this.formData = res.data.data

          // 设置该用户的头像地址
          if (this.formData.avatar) {
            this.imageUrl = downloadImg() + this.formData.avatar
          }
        })
      }

      this.getRole()
    },
    handleClose (done) {
      this.formData = {}
      this.imageUrl = ''
      done()
    },
    getRole () {
      getRoleList().then(res => {
        this.roleList = res.data.data
      })
    },
    submitFormData (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addOrUpdateUser(this.formData).then(res => {
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
              this.$message.error(res.data.message)
            }
          })
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },

    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      this.formData.avatar = file.name
    },
    beforeAvatarUpload (file) {
      let isJPG = file.type === 'image/jpeg'
      let isPNG = file.type === 'image/png'
      let isLt1M = file.size / 1024 / 1024 < 1

      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG/PNG 格式!')
      }
      if (!isLt1M) {
        this.$message.error('上传头像图片大小不能超过 1MB!')
      }
      return true
    }
  }
}
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
