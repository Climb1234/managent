<template>
  <basic-container>
    <avue-crud
      :data="data"
      ref="crud"
      :option="option"
      :page="page"
      v-model="form"
      :search.sync="search"
      :table-loading="loading"
      @on-load="onLoad"
      @row-del="rowDel"
      @refresh-change="refreshChange"
      @search-change="searchChange"
      @search-reset="searchReset">
      <template slot-scope="scope" slot="menuLeft">
        <el-button type="primary"
                   icon="el-icon-download"
                   size="small"
                   @click.stop="handleDownload()">模板下载</el-button>
        <el-button type="primary"
                   icon="el-icon-upload2"
                   size="small"
                   @click.stop="handleUpload()">上传</el-button>
      </template>
    </avue-crud>
    <excel-upload-dialog ref="excelUploadDialog" @callback="onLoad" ></excel-upload-dialog>
  </basic-container>
</template>
<script>
import {getPageList, remove, downloadModel} from '@/api/test/excel-import'
import ExcelUploadDialog from './component/excel-upload-dialog'
export default {
  components: {
    ExcelUploadDialog
  },
  data () {
    return {
      page: {
        pageSize: 10,
        currentPage: 1,
        total: 0
      },
      loading: true,
      data: [],
      form: {},
      search: {},
      option: {
        size: 'mini',
        align: 'center',
        viewBtn: false,
        editBtn: false,
        addBtn: false,
        menuAlign: 'center',
        border: true,
        labelWidth: 100,
        index: true,
        tip: false,
        searchShow: true,
        searchMenuSpan: 6,
        column: [
          {
            label: 'username',
            prop: 'username'
          },
          {
            label: 'birthday',
            prop: 'birthday'
          },
          {
            label: 'doubleData',
            prop: 'doubleData'
          }
        ]
      }
    }
  },
  methods: {
    onLoad (page = this.page, params = {}) {
      this.loading = true
      getPageList(
        page.currentPage,
        page.pageSize,
        Object.assign(params, this.search)
      ).then(res => {
        this.data = res.data.data.list
        this.page.total = res.data.data.total
        this.loading = false
      })
    },
    refreshChange () {
      this.onLoad(this.page, this.search)
    },
    rowDel (row) {
      this.$confirm('确定将选择数据删除?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return remove(row.id)
        })
        .then(() => {
          this.onLoad(this.page)
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
        })
    },
    searchChange (params, done) {
      this.page.currentPage = 1
      this.onLoad(this.page, params)
      done()
    },
    searchReset () {
      this.search = {}
      this.onLoad(this.page)
    },
    handleDownload () {
      window.location.href = process.env.BASE_API + '/excel/model'
    },
    handleUpload () {
      this.$refs['excelUploadDialog'].dialogVisible = true
    }
  }
}
</script>
