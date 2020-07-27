<template>
  <el-card>
    <question-table v-model="formData"></question-table>
    <item-form v-model="formData"></item-form>
  </el-card>
</template>

<script>
import ItemForm from './component/survey-form-define-table'
import QuestionTable from './component/survey-form-body'
export default {
  components: {
    ItemForm,
    QuestionTable
  },
  data () {
    return {
      formData: {
        dataList: []
      }
    }
  }
}
</script>
