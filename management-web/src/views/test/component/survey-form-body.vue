<template>
  <avue-form ref="form" v-model="formData" :option="option"></avue-form>
</template>

<script>
export default {
  props: {
    value: Object.assign({})
  },
  data () {
    return {
      formData: {}
    }
  },
  computed: {
    option () {
      return {
        size: 'mini',
        column: this.value.dataList
      }
    }
  },
  methods: {
  }
}
</script>
