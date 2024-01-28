<template>
  <div id="main-part" class="giligili-left">
<!--    <catalog ref="catalog" />-->
    <div id="main-content">
      <div id="postcontent">
        <div class="giligili-item breadcrumbs">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{path:'/index'}">
              首页
            </el-breadcrumb-item>
            <el-breadcrumb-item :to="{path:'/blog'}">
              文章列表
            </el-breadcrumb-item>
            <el-breadcrumb-item>{{ blog.title }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <article id="post" class="giligili-item post">
          <div class="post-info">
            <div class="post-content">
              <div class="text-info">
                <header class="entry-header">
                  <h1 class="entry-title text-center">
                    {{ blog.title }}
                  </h1>
                  <div class="post-meta text-center">
                    <span>
                      <font-awesome-icon icon="calendar-alt" /> {{ blog.create_time }}
                      <font-awesome-icon icon="book-reader" /> {{ blog.browse_count }}次阅读
                      <span class="hd">
                        <font-awesome-icon icon="thumbs-up" /> {{ blog.like_count }}人点赞
                      </span>
                    </span>
                  </div>
                </header>
                <div id="post-content" class="content markdown-body" v-html="blog.content_html" />
              </div>
            </div>
          </div>
        </article>
      </div>
    </div>
  </div>
</template>

<script>
  import marked from 'marked'
  export default {
    name: 'BlogDetail',
    data () {
      return {
        blog: []
      }
    },
    computed: {

    },
    mounted () {
      this.getBlogDetail()
      const link = document.createElement('link')
      link.type = 'text/css'
      link.rel = 'stylesheet'
      link.href = 'https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css'
      document.head.appendChild(link)
    },
    methods: {
      getBlogDetail(){
        this.$store.dispatch('article/getBlogDetail', this.$route.query.id).then( () => {
          // this.blog = this.$store.blog.getters.blogDetail
          this.blog = this.$store.getters["article/blogDetail"]
          // this.blog = this.$store.getters.blogDetail
        })
      }
    }
  }
</script>

<style scoped>
  /*数学公式不换行*/
  .vditor-math p {
    display: inline;
  }
</style>
<style scoped>
  /*密码加密*/
  /*.lock-face{*/
  /*  display: flex;*/
  /*  flex-wrap: wrap;*/
  /*  justify-content: center;*/
  /*  .face-img{*/
  /*    height: 400px;*/
  /*  }*/
  /*  .lock-text{*/
  /*    width: 100%;*/
  /*    text-align: center;*/
  /*    font-size: 25px;*/
  /*    font-weight: 700;*/
  /*    color: #F56C6C;*/
  /*  }*/
  /*  .lock-input{*/
  /*    width: 400px;*/
  /*    margin: 10px;*/
  /*  }*/
  /*  .lock-btn{*/
  /*    width: 400px;*/
  /*    margin-bottom: 20px;*/
  /*  }*/
  /*}*/
  /*灰色图标*/
  .post-meta{
    color: #777;
  }
  #main-part {
    margin: 0 8px;
  }
  #main-part .giligili-item.breadcrumbs {
    height: 48px;
    line-height: 48px;
    font-size: 14px;
    letter-spacing: 0;
    color: #99a2aa;
    padding: 0;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-all;
    white-space: nowrap;
  }
  .text-info{
    padding: 20px;
  }
  .post-info .post-preface {
    display: block;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: justify;
    font-size: 14px;
    line-height: 1.6;
    cursor: pointer;
    margin-bottom: 10px;
  }
  .post-info .post-content .text-info .post-title {
    font-size: 20px;
    line-height: 1.2;
    margin: 10px 0;
  }
  .post-info .post-content .text-info .content {
    font-size: 14px;
  }
  #post{
    min-height: 200px;
    overflow: visible;
    padding: 0;
    margin-top: 19px;
    padding-bottom: 33px;
  }
  .post-info {
    padding: 0;
    margin: 0;
  }
  .text-center{
    text-align: center;
  }
  #post .post-info .category {
    position: absolute;
    left: -5px;
    top: 40px;
    display: block;
    background-color: #eb7350;
    color: #fff;
    padding: 5px 10px;
    border-radius: 0 5px 5px 0;
    height: auto;
    line-height: 26px;
    text-align: center;
    font-size: 16px;
    -webkit-box-shadow: 0 1px 3px rgba(26,26,26,.1);
    box-shadow: 0 1px 3px rgba(26,26,26,.1);
    z-index: 1;
  }
  #post .post-info .category a{
    color: white;
  }
  .entry-header {
    margin-bottom: 15px;
  }
  .entry-title {
    margin: 0 0 15px;
    font-size: 30px;
  }
  #main-content {
    min-height: 50px;
    position: relative;
  }
  /*文章内容*/
  .pull-left {
    float: left;
  }
  /*头部分类*/
  .giligili-item {
    position: relative;
    width: 100%;
    background: #fff;
    border-radius: 4px;
    margin-bottom: 8px;
    -webkit-box-shadow: 0 1px 3px rgba(26,26,26,.1);
    box-shadow: 0 1px 3px rgba(26,26,26,.1);
  }
  .giligili-item.breadcrumbs {
    height: 48px;
    line-height: 48px;
    font-size: 14px;
    letter-spacing: 0;
    color: #99a2aa;
    padding: 0;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-all;
    white-space: nowrap;
  }
  .giligili-item.breadcrumbs #crumbs {
    display: inline;
  }
  .giligili-item.breadcrumbs #crumbs a {
    color: #99a2aa;
  }
  /*顶部分类*/
  .el-breadcrumb {
    margin-top: 17px;
    margin-left: 15px;
  }
  /*底部打赏部分*/
  .entry-footer {
    color: #9d9d9d;
    margin: 10px;
  }
  .entry-footer .post-like-donate {
    margin-top: 15px;
    margin-bottom: 20px;
  }
  .text-center {
    text-align: center;
  }
  .entry-footer .footer-tag a {
    background-color: #aaa;
    border-radius: 0;
    color: #fff;
    display: inline-block;
    font-size: 12px;
    line-height: 13px;
    margin: 0 0 2px 5px;
    padding: 3px 7px;
    position: relative;
  }
  .entry-footer .footer-tag .pull-date {
    float: right;
    font-size: 14px;
  }
</style>
