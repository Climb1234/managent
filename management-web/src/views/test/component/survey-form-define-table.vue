<template>
  <el-table style="width: 100%; margin-bottom: 20px" :data="value.dataList" border size="mini" >
    <el-table-column width="67" align="center">
      <template slot="header">
        <el-button icon="el-icon-plus" circle size="mini" @click="handleAddItem" style="padding: 3px; font-size: 5px"></el-button>
      </template>
      <template slot-scope="scope">
        <el-button type="danger" circle icon="el-icon-delete" size="mini" @click="handleDelItem(scope.$index)" style="padding: 3px; font-size: 5px"></el-button>
      </template>
    </el-table-column>
    <el-table-column label="名称">
      <template slot-scope="scope">
        <el-input v-model="scope.row.label" size="mini"></el-input>
      </template>
    </el-table-column>
    <el-table-column label="英文名">
      <template slot-scope="scope">
        <el-input v-model="scope.row.prop" size="mini"></el-input>
      </template>
    </el-table-column>
    <el-table-column label="类型">
      <template slot-scope="scope">
        <el-select v-model="scope.row.type" placeholder="请选择" size="mini" @change="handleTypeChange(value)">
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column label="字数限制/关联数据">
      <template slot-scope="scope">
        <el-input-number v-if="scope.row.type === 'input'" v-model="scope.row.maxlength" size="mini" :min="1" :max="100"></el-input-number>
<!--        <el-input v-else v-model="scope.row.dicData" size="mini"></el-input>-->
        <el-select v-else v-model="scope.row.dicDataType" placeholder="请选择" size="mini" @change="handleDictDataChange(scope.$index, scope.row.dicDataType)">
          <el-option
            v-for="item in dictDataOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  components: {
  },
  props: {
    value: Object.assign({})
  },
  data () {
    return {
      typeOptions: [{
        label: '输入框',
        value: 'input'
      }, {
        label: '选择框',
        value: 'select'
      }, {
        label: '单选按钮',
        value: 'radio'
      }, {
        label: '多选按钮',
        value: 'checkbox'
      }],
      dictDataOptions: [{
        label: '性别',
        value: 'sex'
      }, {
        label: '城市',
        value: 'city'
      }],
      city: [{
        label: '苏州',
        value: 'su'
      }, {
        label: '南京',
        value: 'nj'
      }],
      sex: [{
        label: '男',
        value: 'nan'
      }, {
        label: '女',
        value: 'nv'
      }]
    }
  },
  created () {
    this.handleAddItem()
  },
  methods: {
    handleAddItem () {
      let item = {
        label: '',
        prop: '',
        type: 'input',
        dicData: '',
        maxlength: 1
      }
      this.value.dataList.push(item)
      console.log(this.value)
    },
    handleDelItem (index) {
      this.value.dataList.splice(index, 1)
    },
    handleTypeChange (value) {
      console.log(value)
    },
    handleDictDataChange (index, value) {
      console.log(value)
      if (value === 'city') {
        this.value.dataList[index].dicData = this.city
      } else {
        this.value.dataList[index].dicData = this.sex
      }
      console.log(this.value.dataList)
    }
  }
}
</script>
