<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/index' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: '/admin/content/update'}">系统更新</el-breadcrumb-item>
        <el-breadcrumb-item>编辑更新</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <TinyMCE class="tinymce" v-model="update_html" :disabled="disabled" ref="editor"/>
    <el-button plain @click="saveUpdate">保存更新</el-button>
    <el-button plain @click="disabled = true">禁用</el-button>
  </div>
</template>

<script>
import TinyMCE from '../util/TinyMCE'
export default {
  components: {
    TinyMCE
  },
  data () {
    return {
      update: {},
      update_html: '',
      update_time: '',
      disabled: false
    }
  },
  mounted () {
    if (this.$route.params.update) {
      this.update = this.$route.params.update
      this.update_html = this.update.updateHtml
      this.update_time = this.update.updateTime
    }
  },
  methods: {
    // 清空内容
    clear () {
      this.$refs.editor.clear()
    },
    saveUpdate () {
      if (this.update_time === '') {
        this.getDate()
        this.$axios.post('/admin/update/insert', {
          updateHtml: this.update_html,
          updateTime: this.update_time
        }).then(resp => {
          if (resp && resp.data.code === 200) {
            this.$message({
              message: '更新日志保存成功',
              type: 'success'
            })
            this.$router.replace('/admin/content/update')
          } else {
            this.$message({
              message: '更新日志保存失败',
              type: 'error'
            })
          }
        })
      } else {
        this.$axios.post('/admin/update/update', {
          id: this.update.id,
          updateHtml: this.update_html,
          updateTime: this.update_time
        }).then(resp => {
          if (resp && resp.data.code === 200) {
            this.$message({
              message: '更新日志修改成功',
              type: 'success'
            })
            this.$router.replace('/admin/content/update')
          }
        })
      }
    },
    getDate () {
      var time = new Date()
      var year = time.getFullYear()
      var month = time.getMonth() + 1
      if (month < 10) {
        month = '0' + month
      }
      var day = time.getDate()
      if (day < 10) {
        day = '0' + day
      }
      this.data = year + '-' + month + '-' + day
      this.update_time = this.data
    }
  }
}
</script>

<style scoped>
  .tinymce {
    margin-top: 30px;
  }
</style>
