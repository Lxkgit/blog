<template>
  <div>
    <TinyMCE class="tinymce" v-model="diary_html" :disabled="disabled" ref="editor"/>
    <el-button plain @click="saveDiary">保存日记</el-button>
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
      diary: {},
      postDiary: {},
      diary_html: '',
      create_time: '',
      disabled: false
    }
  },
  mounted () {
    if (this.$route.params.diary) {
      this.diary = this.$route.params.diary
      this.diary_html = this.diary.diary_html
      this.create_time = this.diary.create_time
    }
  },
  methods: {
    // 清空内容
    clear () {
      this.$refs.editor.clear()
    },
    saveDiary () {
      if (this.create_time === '') {
        this.getDate()
        this.postDiary = {
          diary_html: this.diary_html,
          create_time: this.create_time
        }
        this.$store.dispatch('diary/insertDiary', this.postDiary).then(resp => {
          if (resp && resp.code === 200) {
            this.$message({
              type: 'success',
              message: '日记保存成功'
            })
            this.$router.replace('/admin/content/diary')
          } else {
            this.$message({
              message: '日记保存失败',
              type: 'error'
            })
          }
        })
      } else {
        this.postDiary = {
          id: this.diary.id,
          diary_html: this.diary_html,
          create_time: this.create_time
        }
        this.$store.dispatch('diary/updateDiary', this.postDiary).then(resp => {
          if (resp && resp.code === 200) {
            this.$message({
              message: '日记修改成功',
              type: 'success'
            })
            this.$router.replace('/admin/content/diary')
          } else {
            this.$message({
              message: '日记保存失败',
              type: 'error'
            })
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
      this.create_time = this.data
    }
  }
}
</script>

<style scoped>
  .tinymce {
    margin-top: 30px;
  }
</style>
