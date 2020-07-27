<template>
  <el-dialog title="上传" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <el-upload
      class="upload-demo"
      ref="upload"
      drag
      :action="actionURL"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      multiple
      :limit="3"
      :on-exceed="handleExceed"
      :file-list="fileList"
      :auto-upload="false">
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div slot="tip" class="el-upload__tip">只能上传xlsx文件，且不超过10mb</div>
    </el-upload>
    <div style="text-align: right; padding-top: 10px" >
      <el-button size="small" type="primary" @click="submitUpload">上传到服务器</el-button>
      <el-button size="small" @click="dialogVisible = false">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  data () {
    return {
      dialogVisible: false,
      fileList: [],
      actionURL: process.env.BASE_API + '/excel/upload'
    }
  },
  methods: {
    handleClose () {
      this.dialogVisible = false
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview (file) {
      console.log(file)
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    submitUpload () {
      this.$refs.upload.submit()
      this.dialogVisible = false
      this.$message.success('上传成功')
      this.$emit('callback')
    }
  }
}
</script>
