<template>
  <div>
    <div class="tool-input-item">
      <h4>{{ title }}</h4><el-input v-model="data" autosize type="textarea" @change="change" />
    </div>
  </div>
</template>

<script>
  export default {
    name: 'TypeText',
    props: {
      title: { type: String, default: '' },
      field: { type: String, default: '' },
      model: { type: String, default: '' },
      type: {type: String, default: ''}
    },
    data () {
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

<style lang="scss" scoped>
  /*侧边栏设置的相关设置*/
  .tool-input-item{
    display: flex;
    flex-direction: column;
    h4{
      font-size: 14px;
      font-weight: bold;
      margin: 6px 0;
    }
    .item-input{
      display: flex;
      flex-direction: row;
      margin: 5px 0 0 0;
    }
    .item-input .el-input{
      margin-right: 5px;
    }
  }
</style>
