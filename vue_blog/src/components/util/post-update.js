// 文章更新后的准备工作
import $ from 'jquery'
import Prism from 'prismjs'

export default {
  updated () {
    this.$nextTick(() => {
      // 自动给a标签添加target属性
      $('.content a').attr({ rel: 'noopener', target: '_blank' })
      // 代码高亮
      Prism.highlightAll()
      // 显示数学公式
      const editor = require('vditor/dist/method.min')
      $('.vditor-math').each(function (i) {
        editor.preview(document.getElementsByClassName('vditor-math')[i], '$' + $(this).text() + '$')
      })
      // 点击自动开合的控件
      $('.xHeading').click(function () {
        if ($(this).next().css('display') === 'block') {
          $(this).children('.xIcon').css('transform', 'rotate(0deg)')
          $(this).next().css('display', 'none')
        } else {
          $(this).children('.xIcon').css('transform', 'rotate(45deg)')
          $(this).next().css('display', 'block')
        }
      })
      // 显示文章目录
      if (this.$refs.catalog !== undefined) {
        this.$refs.catalog.getCatalog()
      }
      // 下面这种预览的好像没啥用，后续需要改进一下
      // editor.mathRender(document.getElementsByClassName('vditor-math')[i])
    })
  }
}
