<template>
  <div class="tool-number-item">
    <h4>{{ title }}</h4>
    <el-switch
      v-model="data"
      active-color="#13ce66"
      inactive-color="#ff4949"
      @change="change"
    />
  </div>
</template>

<script>
  export default {
    name: 'TypeSwitch',
    props: {
      title: {type: String, default: ''},
      field: {type: String, default: ''},
      model: {type: Boolean, default: false},
      type: {type: String, default: ''}
    },
    data() {
      return {
        // 因为vue默认不能直接修改设置，所以我们需要自己创建一个副本
        data: this.model
      }
    },
    methods: {
      change (value) {
        // 自动保存改设置
        this.$store.dispatch('setting/updateSetting', {key: this.field, value:value, type: this.type}).then(resp => {
          if (resp && resp.code === 200) {
            this.$message.success('保存成功');
            this.$store.dispatch('setting/selectWebSetting')
          }
        }).catch( () => {
          this.$message.error('设置保存失败')
          this.data = this.model
        })
      }
    }
  }
</script>

<style scoped lang="scss">
  .tool-number-item {
    display: flex;
    flex-direction: column;

    h4 {
      font-size: 14px;
      font-weight: bold;
      margin: 6px 0;
    }
  }
</style>
