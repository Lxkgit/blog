<template>
  <div>
    <el-row style="z-index: 1">
      <div class="mavonEditor">
        <mavon-editor style="height: calc(100vh - 110px);" :codeStyle="codeStyle" :ishljs="true" :boxShadow="true" v-model="docContent.content_md" @save="saveDoc"/>
      </div>
    </el-row>
  </div>
</template>

<script>

  import {mapGetters} from 'vuex'

  export default {
    name: 'DocEditor',
    data () {
      return {
        markdownOption:{
          // toolbars: {
          bold: false, // 粗体
          italic: false, // 斜体
          header: false, // 标题
          underline: false, // 下划线
          strikethrough: false, // 中划线
          mark: false, // 标记
          superscript: false, // 上角标
          subscript: false, // 下角标
          quote: false, // 引用
          ol: false, // 有序列表
          ul: false, // 无序列表
          link: false, // 链接
          imagelink: false, // 图片链接
          code: false, // code
          table: false, // 表格
          fullscreen: false, // 全屏编辑
          readmodel: false, // 沉浸式阅读
          htmlcode: false, // 展示html源码
          help: false, // 帮助
          /* 1.3.5 */
          undo: false, // 上一步
          redo: false, // 下一步
          trash: false, // 清空
          save: false, // 保存（触发events中的save事件）
          /* 1.4.2 */
          navigation: false, // 导航目录
          /* 2.1.8 */
          alignleft: false, // 左对齐
          aligncenter: false, // 居中
          alignright: false, // 右对齐
          /* 2.2.1 */
          subfield: false, // 单双栏模式
          preview: false, // 预览
        },
        codeStyle:'monokai-sublime',//主题
      }
    },
    computed: {
      ...mapGetters('doc',['docContent']),
    },
    mounted() {
      console.log(this.$store.getters["doc/docContent"])
      // const link = document.createElement('link')
      // link.type = 'text/css'
      // link.rel = 'stylesheet'
      // link.href = 'https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css'
      // document.head.appendChild(link)
    },
    methods: {
      saveDoc(md, html) {
        this.$store.dispatch('doc/updateDocContent',
          {
            id: this.docContent.id,
            title: this.docContent.title,
            content_md: md,
            content_html: html
          }).then(resp => {
            if (resp || resp.code === 200) {
              this.$message({
                type: 'success',
                message: '已保存成功'
              })
            }
        })
      }
    }

  }
</script>

<style scoped lang="scss">

</style>


