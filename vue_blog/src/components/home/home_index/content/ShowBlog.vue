<template>
  <div id="main-part" class="giligili-left">
<!--    <catalog ref="catalog" />-->
    <div id="main-content">
      <div id="postcontent">
        <div class="giligili-item breadcrumbs">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{name:'index'}">
              首页
            </el-breadcrumb-item>
            <el-breadcrumb-item>
<!--              <nuxt-link :to="`/?category=${postContent.categoryLink}`">-->
<!--                {{ postContent.categoryName }}-->
<!--              </nuxt-link>-->
            </el-breadcrumb-item>
<!--            <el-breadcrumb-item>{{ postContent.title }}</el-breadcrumb-item>-->
          </el-breadcrumb>
        </div>
        <article id="post" class="giligili-item post">
          <div class="post-info">
<!--            <image-bubble :img="postContent.image" />-->
            <div class="post-content">
              <div class="text-info">
                <header class="entry-header">
                  <h1 class="entry-title text-center">
<!--                    {{ postContent.title }}-->
                  </h1>
                  <div class="post-meta text-center">
                    <span>
<!--                      <font-awesome-icon icon="calendar-alt" /> {{ postContent.date }}-->
<!--                      <font-awesome-icon icon="book-reader" /> {{ postContent.view }}次阅读-->
                      <!-- <font-awesome-icon icon="calculator" /> 共个字-->
                      <span class="hd">
<!--                        <font-awesome-icon icon="comments" /> {{ postContent.comment }}条评论-->
<!--                        <font-awesome-icon icon="thumbs-up" /> {{ postContent.good }}人点赞-->
                      </span>
                    </span>
                  </div>
                </header>
<!--                <div v-if="!postContent.encrypt" id="post-content" class="content" v-html="postContent.content" />-->
<!--                <div v-else class="lock-face">-->
<!--&lt;!&ndash;                  <img class="face-img" alt src="~/assets/image/post/face.jpg">&ndash;&gt;-->
<!--                  <div class="lock-text">-->
<!--                    这是一篇受保护的文章，请输入访问密码!-->
<!--                  </div>-->
<!--                  <div class="lock-input">-->
<!--                    <el-input v-model="password" type="password" placeholder="请输入访问密码">-->
<!--                      <el-button slot="prepend" icon="el-icon-key" />-->
<!--                    </el-input>-->
<!--                  </div>-->
<!--                  <el-button class="lock-btn" type="primary" @click="getEncryptContent($route.params.post,password)">-->
<!--                    点击访问-->
<!--                  </el-button>-->
<!--                </div>-->
              </div>
            </div>
            <span class="category">
<!--              <nuxt-link :to="`/?category=${postContent.categoryLink}`">{{ postContent.categoryName }}</nuxt-link>-->
            </span>
          </div>
          <footer class="entry-footer clearfix">
<!--            <post-function-->
<!--              :id="postContent.id"-->
<!--              :alipay="postContent.alipay"-->
<!--              :wechat="postContent.wechat"-->
<!--            />-->
            <div class="footer-tag clearfix">
              <div class="pull-left">
                <font-awesome-icon icon="tags" />
<!--                <router-link v-for="(item,index) in postContent.tag" :key="index" :to="`/?tag=${item.link}`" rel="tag">-->
<!--                  {{ item.name }}-->
<!--                </router-link>-->
              </div>
              <div class="pull-date">
<!--                <span>最后编辑：{{ postContent.modify }}</span>-->
              </div>
            </div>
          </footer>
        </article>
      </div>
    </div>
    <!--评论部分-->
<!--    <comment :post-id="postContent.id" />-->
  </div>
</template>

<script>
export default {
  name: 'ShowBlog',
  data () {
    return {
    }
  }
}
</script>

<style>
  /*数学公式不换行*/
  .vditor-math p {
    display: inline;
  }
</style>
<!--<style scoped lang="scss">-->
<!--  /*密码加密*/-->
<!--  .lock-face{-->
<!--    display: flex;-->
<!--    flex-wrap: wrap;-->
<!--    justify-content: center;-->
<!--    .face-img{-->
<!--      height: 400px;-->
<!--    }-->
<!--    .lock-text{-->
<!--      width: 100%;-->
<!--      text-align: center;-->
<!--      font-size: 25px;-->
<!--      font-weight: 700;-->
<!--      color: #F56C6C;-->
<!--    }-->
<!--    .lock-input{-->
<!--      width: 400px;-->
<!--      margin: 10px;-->
<!--    }-->
<!--    .lock-btn{-->
<!--      width: 400px;-->
<!--      margin-bottom: 20px;-->
<!--    }-->
<!--  }-->
<!--  /*灰色图标*/-->
<!--  .post-meta{-->
<!--    color: #777;-->
<!--  }-->
<!--  #main-part {-->
<!--    margin: 0 8px;-->
<!--  }-->
<!--  #main-part .giligili-item.breadcrumbs {-->
<!--    height: 48px;-->
<!--    line-height: 48px;-->
<!--    font-size: 14px;-->
<!--    letter-spacing: 0;-->
<!--    color: #99a2aa;-->
<!--    padding: 0;-->
<!--    overflow: hidden;-->
<!--    text-overflow: ellipsis;-->
<!--    word-break: break-all;-->
<!--    white-space: nowrap;-->
<!--  }-->
<!--  .post-info .post-preface {-->
<!--    display: block;-->
<!--    display: -webkit-box;-->
<!--    overflow: hidden;-->
<!--    text-overflow: ellipsis;-->
<!--    text-align: justify;-->
<!--    font-size: 14px;-->
<!--    line-height: 1.6;-->
<!--    cursor: pointer;-->
<!--    margin-bottom: 10px;-->
<!--  }-->
<!--  .post-info .post-content .text-info .post-title {-->
<!--    font-size: 20px;-->
<!--    line-height: 1.2;-->
<!--    margin: 10px 0;-->
<!--  }-->
<!--  .post-info .post-content .text-info .content {-->
<!--    font-size: 14px;-->
<!--  }-->
<!--  #post{-->
<!--    min-height: 200px;-->
<!--    overflow: visible;-->
<!--    padding: 0;-->
<!--    margin-top: 19px;-->
<!--    padding-bottom: 33px;-->
<!--  }-->
<!--  .post-info {-->
<!--    padding: 0;-->
<!--    margin: 0;-->
<!--  }-->
<!--  .text-center{-->
<!--    text-align: center;-->
<!--  }-->
<!--  #post .post-info .category {-->
<!--    position: absolute;-->
<!--    left: -5px;-->
<!--    top: 40px;-->
<!--    display: block;-->
<!--    background-color: #eb7350;-->
<!--    color: #fff;-->
<!--    padding: 5px 10px;-->
<!--    border-radius: 0 5px 5px 0;-->
<!--    height: auto;-->
<!--    line-height: 26px;-->
<!--    text-align: center;-->
<!--    font-size: 16px;-->
<!--    -webkit-box-shadow: 0 1px 3px rgba(26,26,26,.1);-->
<!--    box-shadow: 0 1px 3px rgba(26,26,26,.1);-->
<!--    z-index: 1;-->
<!--  }-->
<!--  #post .post-info .category a{-->
<!--    color: white;-->
<!--  }-->
<!--  .entry-header {-->
<!--    margin-bottom: 15px;-->
<!--  }-->
<!--  .entry-title {-->
<!--    margin: 0 0 15px;-->
<!--    font-size: 30px;-->
<!--  }-->
<!--  #main-content {-->
<!--    min-height: 50px;-->
<!--    position: relative;-->
<!--  }-->
<!--  /*文章内容*/-->
<!--  .pull-left {-->
<!--    float: left;-->
<!--  }-->
<!--  /*头部分类*/-->
<!--  .giligili-item {-->
<!--    position: relative;-->
<!--    width: 100%;-->
<!--    background: #fff;-->
<!--    border-radius: 4px;-->
<!--    margin-bottom: 8px;-->
<!--    -webkit-box-shadow: 0 1px 3px rgba(26,26,26,.1);-->
<!--    box-shadow: 0 1px 3px rgba(26,26,26,.1);-->
<!--  }-->
<!--  .giligili-item.breadcrumbs {-->
<!--    height: 48px;-->
<!--    line-height: 48px;-->
<!--    font-size: 14px;-->
<!--    letter-spacing: 0;-->
<!--    color: #99a2aa;-->
<!--    padding: 0;-->
<!--    overflow: hidden;-->
<!--    text-overflow: ellipsis;-->
<!--    word-break: break-all;-->
<!--    white-space: nowrap;-->
<!--  }-->
<!--  .giligili-item.breadcrumbs #crumbs {-->
<!--    display: inline;-->
<!--  }-->
<!--  .giligili-item.breadcrumbs #crumbs a {-->
<!--    color: #99a2aa;-->
<!--  }-->
<!--  /*顶部分类*/-->
<!--  .el-breadcrumb {-->
<!--    margin-top: 17px;-->
<!--    margin-left: 15px;-->
<!--  }-->
<!--  /*底部打赏部分*/-->
<!--  .entry-footer {-->
<!--    color: #9d9d9d;-->
<!--    margin: 10px;-->
<!--  }-->
<!--  .entry-footer .post-like-donate {-->
<!--    margin-top: 15px;-->
<!--    margin-bottom: 20px;-->
<!--  }-->
<!--  .text-center {-->
<!--    text-align: center;-->
<!--  }-->
<!--  .entry-footer .footer-tag a {-->
<!--    background-color: #aaa;-->
<!--    border-radius: 0;-->
<!--    color: #fff;-->
<!--    display: inline-block;-->
<!--    font-size: 12px;-->
<!--    line-height: 13px;-->
<!--    margin: 0 0 2px 5px;-->
<!--    padding: 3px 7px;-->
<!--    position: relative;-->
<!--  }-->
<!--  .entry-footer .footer-tag .pull-date {-->
<!--    float: right;-->
<!--    font-size: 14px;-->
<!--  }-->
<!--</style>-->
