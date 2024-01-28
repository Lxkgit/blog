<!--<template>-->
<!--  <div id="main-part" class="giligili-left">-->
<!--    <div class="b-wrap">-->
<!--      <div class="primary-menu-itnl">-->
<!--        <div id="primaryPageTab" class="page-tab report-wrap-module">-->
<!--          <ul class="con">-->
<!--            <li>-->
<!--              <router-link :to="{path:'/'}">-->
<!--                <div class="round home">-->
<!--                  <font-awesome-icon size="lg" icon="home"/>-->
<!--                </div>-->
<!--                <span>首页</span>-->
<!--              </router-link>-->
<!--            </li>-->
<!--          </ul>-->
<!--        </div>-->
<!--        <span class="tab-line-itnl"/>-->
<!--        <div id="primaryChannelMenu" class="channel-menu-itnl report-wrap-module">-->
<!--          <div v-for="(item,index2) in category" :key="index2" class="item">-->
<!--            <el-popover-->
<!--              placement="bottom"-->
<!--              width="100"-->
<!--              trigger="hover"-->
<!--            >-->
<!--              <template v-for="(item2,index3) in item.children">-->
<!--                <a :href="'#/blog'"-->
<!--                   v-if="item.value === item2.parent"-->
<!--                   :key="index3"-->
<!--                   @click="setArticleTypeC(item2)"> {{ item2.label }}</a>-->
<!--              </template>-->
<!--              <a slot="reference" class="name" href="javascript:void(0);" @click="setArticleTypeP(item)"><span>{{ item.label }}<em></em></span></a>-->
<!--            </el-popover>-->
<!--            &lt;!&ndash;            <el-popover&ndash;&gt;-->
<!--            &lt;!&ndash;              placement="bottom"&ndash;&gt;-->
<!--            &lt;!&ndash;              width="100"&ndash;&gt;-->
<!--            &lt;!&ndash;              trigger="hover"&ndash;&gt;-->
<!--            &lt;!&ndash;            >&ndash;&gt;-->
<!--            &lt;!&ndash;              <template v-for="(item2,index3) in item.children">&ndash;&gt;-->
<!--            &lt;!&ndash;                <a :href="'#/blog'"&ndash;&gt;-->
<!--            &lt;!&ndash;                   v-if="item.value === item2.parent"&ndash;&gt;-->
<!--            &lt;!&ndash;                   :key="index3"&ndash;&gt;-->
<!--            &lt;!&ndash;                   @click="setArticleTypeC(item2)"> {{ item2.label }}</a>&ndash;&gt;-->
<!--            &lt;!&ndash;              </template>&ndash;&gt;-->
<!--            &lt;!&ndash;              <a slot="reference" class="name" href="javascript:void(0);"><span>{{ item.label }}<em></em></span></a>&ndash;&gt;-->
<!--            &lt;!&ndash;            </el-popover>&ndash;&gt;-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->
<!--<script>-->

<!--  import {mapGetters} from "vuex";-->

<!--  export default {-->
<!--    name: 'BlogType',-->
<!--    data() {-->
<!--      return {-->
<!--        category: [],-->
<!--      }-->
<!--    },-->
<!--    mounted() {-->
<!--      this.selectArticleTypeTreeHome()-->
<!--    },-->
<!--    methods: {-->
<!--      selectArticleTypeTreeHome() {-->
<!--        this.$store.dispatch('selectArticleTypeTreeHome').then(resp => {-->
<!--          if (resp && resp.code === 200) {-->
<!--            this.category = resp.result-->
<!--          }-->
<!--        })-->
<!--      },-->
<!--      setArticleTypeP(item) {-->
<!--        this.$store.commit('setArticleTypeP', item)-->
<!--        console.log(this.$store.getters.articleTypeP)-->
<!--      },-->
<!--      setArticleTypeC(item) {-->
<!--        this.$store.commit('setArticleTypeC', item)-->
<!--        console.log(this.$store.getters.articleTypeC)-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style scoped>-->
<!--  /*置顶*/-->
<!--  .stick {-->
<!--    position: absolute;-->
<!--    z-index: 9;-->
<!--    right: 0;-->
<!--  }-->

<!--  .con .home {-->
<!--    color: #ffffff;-->
<!--  }-->

<!--  .el-popover a {-->
<!--    display: block;-->
<!--    font-size: 12px;-->
<!--    padding: 6px 7px;-->
<!--    height: 20px;-->
<!--    float: left;-->
<!--  }-->

<!--  /*新的导航栏*/-->
<!--  .b-wrap {-->
<!--    background: #ffffff;-->
<!--    border-radius: 10px;-->
<!--    margin-bottom: 10px;-->
<!--  }-->

<!--  .primary-menu-itnl {-->
<!--    display: -ms-flexbox;-->
<!--    display: flex;-->
<!--    -ms-flex-pack: justify;-->
<!--    justify-content: space-between;-->
<!--    -ms-flex-align: center;-->
<!--    align-items: center;-->
<!--    padding: 7px 0;-->
<!--    width: 100%;-->
<!--  }-->

<!--  .page-tab, .page-tab .con {-->
<!--    display: -ms-flexbox;-->
<!--    display: flex;-->
<!--    padding: 0;-->
<!--    list-style: none;-->
<!--    margin: 3px 0 0 0;-->
<!--  }-->

<!--  .page-tab .con li {-->
<!--    cursor: pointer;-->
<!--    width: 58px;-->
<!--    height: 68px;-->
<!--    text-align: center;-->
<!--    font-size: 14px;-->
<!--    white-space: nowrap;-->
<!--    border-radius: 16px;-->
<!--    transition: all .3s;-->
<!--  }-->

<!--  .con .el-timeline li {-->
<!--    width: auto;-->
<!--    height: auto;-->
<!--  }-->

<!--  .page-tab .con li .round {-->
<!--    position: relative;-->
<!--    width: 36px;-->
<!--    height: 36px;-->
<!--    background: #ff5c7c;-->
<!--    border-radius: 36px;-->
<!--    line-height: 36px;-->
<!--    text-align: center;-->
<!--    margin: 0 auto 4px;-->
<!--  }-->

<!--  .tab-line-itnl {-->
<!--    display: inline-block;-->
<!--    height: 67px;-->
<!--    border-left: 1px solid #e7e7e7;-->
<!--    margin: 0 10px;-->
<!--  }-->

<!--  .channel-menu-itnl {-->
<!--    display: -ms-flexbox;-->
<!--    display: flex;-->
<!--    -ms-flex-wrap: wrap;-->
<!--    flex-wrap: wrap;-->
<!--    height: 68px;-->
<!--    -ms-flex: 1;-->
<!--    flex: 1;-->
<!--  }-->

<!--  .channel-menu-itnl .item {-->
<!--    cursor: pointer;-->
<!--    display: -ms-flexbox;-->
<!--    display: -webkit-box;-->
<!--    display: flex;-->
<!--    height: 34px;-->
<!--    margin: 6px;-->
<!--    float: left;-->
<!--  }-->

<!--  .channel-menu-itnl .item .name {-->
<!--    font-size: 14px;-->
<!--    display: -ms-flexbox;-->
<!--    display: flex;-->
<!--    -ms-flex-align: center;-->
<!--    align-items: center;-->
<!--    white-space: nowrap;-->
<!--  }-->

<!--  .channel-menu-itnl .item .name em {-->
<!--    font-style: normal;-->
<!--    font-size: 12px;-->
<!--    display: inline-block;-->
<!--    background: #73c9e5;-->
<!--    border-radius: 2px;-->
<!--    color: #fff;-->
<!--    margin-left: 1px;-->
<!--    transform: scale(.85);-->
<!--    width: 32px;-->
<!--    text-align: center;-->
<!--  }-->

<!--  .friendship-link .item {-->
<!--    height: 34px;-->
<!--    display: -ms-flexbox;-->
<!--    display: flex;-->
<!--  }-->

<!--  .friendship-link .item .name span {-->
<!--    margin-left: 4px;-->
<!--  }-->

<!--  .page-tab {-->
<!--    -ms-flex-align: center;-->
<!--    align-items: center;-->
<!--  }-->

<!--  /*图标相关*/-->

<!--  .text-muted.post-item-foot-icon span {-->
<!--    margin-right: 15px;-->
<!--    padding-right: 4px;-->
<!--  }-->

<!--  #main-part {-->
<!--    padding: 0 8px;-->
<!--  }-->

<!--  .panel:not(.b-a) {-->
<!--    transition: all .2s;-->
<!--    box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.2) !important;-->
<!--  }-->

<!--  .index-post-img {-->
<!--    border-top-left-radius: 5px;-->
<!--    border-top-right-radius: 5px;-->
<!--    overflow: hidden;-->
<!--  }-->

<!--  .item-thumb {-->
<!--    min-height: 250px;-->
<!--    position: relative;-->
<!--    display: block;-->
<!--    background-position: 50% 50%;-->
<!--    background-size: cover;-->
<!--    border-top-left-radius: 4px;-->
<!--    border-top-right-radius: 4px;-->
<!--    transition: all .3s;-->
<!--  }-->

<!--  .index-post-title {-->
<!--    overflow: hidden;-->
<!--    text-overflow: ellipsis;-->
<!--    white-space: nowrap;-->
<!--    font-size: 22px;-->
<!--    transition: all .3s;-->
<!--  }-->

<!--  .panel-small .post-meta > div {-->
<!--    transition: all .3s;-->
<!--    transform: translateY(-10px);-->
<!--    opacity: 0;-->
<!--  }-->

<!--  .panel .post-meta.wrapper-lg {-->
<!--    padding: 20px;-->
<!--    background-color: white;-->
<!--  }-->

<!--  #posts a {-->
<!--    color: inherit;-->
<!--    text-decoration: none;-->
<!--    cursor: pointer;-->
<!--  }-->

<!--  .m-t-none {-->
<!--    margin-top: 0 !important;-->
<!--  }-->

<!--  h1, h2, h3 {-->
<!--    margin-top: 20px;-->
<!--    margin-bottom: 10px;-->
<!--    font-family: inherit;-->
<!--    font-weight: 500;-->
<!--    line-height: 1.1;-->
<!--  }-->

<!--  #posts > div.blog-post > .panel {-->
<!--    background-color: rgba(255, 255, 255, .9);-->
<!--  }-->

<!--  .blog-post > .panel {-->
<!--    border: 0;-->
<!--    border-radius: 5px;-->
<!--    margin-bottom: 15px;-->
<!--    position: relative;-->
<!--  }-->

<!--  .b-b {-->
<!--    border-bottom: 1px solid #dee5e7;-->
<!--  }-->

<!--  @media screen and (min-width: 1200px) {-->
<!--    .panel .item-thumb {-->
<!--      height: 300px;-->
<!--    }-->

<!--    .panel .post-meta {-->
<!--      position: relative;-->
<!--      margin-top: -300px;-->
<!--      height: 168px;-->
<!--      padding-top: 133px !important;-->
<!--      padding-bottom: 0 !important;-->
<!--      background-color: rgba(0, 0, 0, .3) !important;-->
<!--      color: #fff !important;-->
<!--      transition: all .3s;-->
<!--      border-radius: 5px;-->
<!--    }-->

<!--    .panel .post-meta > h2, #posts > div.blog-post > .panel-small .post-meta > h2 {-->
<!--      text-align: center;-->
<!--      text-shadow: 0 0 3px #fff;-->
<!--    }-->

<!--    .blog-post > .panel:hover .summary {-->
<!--      transform: translateY(10px);-->
<!--      opacity: 1;-->
<!--    }-->

<!--    .blog-post > .panel:hover .index-post-title {-->
<!--      transform: translateY(-30px) scale(1.2, 1.2);-->
<!--      opacity: 1;-->
<!--      padding: 0 60px;-->
<!--    }-->

<!--    .blog-post > .panel:hover .item-thumb {-->
<!--      transform: scale(1.2, 1.2);-->
<!--    }-->

<!--    .blog-post > .panel:hover .text-muted {-->
<!--      transform: translateY(10px);-->
<!--      opacity: 1;-->
<!--    }-->

<!--    .blog-post > .panel:hover .line {-->
<!--      transform: translateY(10px);-->
<!--      opacity: 1;-->
<!--    }-->

<!--    .panel .post-meta > .text-muted {-->
<!--      position: absolute;-->
<!--      bottom: 16px;-->
<!--      opacity: 0;-->
<!--      transition: all .3s;-->
<!--    }-->

<!--    .panel .post-meta > .summary {-->
<!--      /*position: absolute;*/-->
<!--      bottom: 60px;-->
<!--      /*width: 710px;*/-->
<!--      padding: 3px;-->
<!--      opacity: 0;-->
<!--      transition: all .3s;-->
<!--    }-->

<!--    .panel .post-meta > .line {-->
<!--      position: absolute;-->
<!--      bottom: 40px;-->
<!--      width: 708px;-->
<!--      opacity: 0;-->
<!--      transition: all .3s;-->
<!--    }-->

<!--    #posts > div.blog-post > .panel-small .post-meta > p, #posts > div.blog-post > .panel .post-meta > div {-->
<!--      transition: all .3s;-->
<!--      transform: translate(-10px);-->
<!--      opacity: 0;-->
<!--    }-->

<!--    .blog-post > .panel:hover .index-post-img {-->
<!--      filter: blur(3px);-->
<!--    }-->

<!--    .panel .post-meta * {-->
<!--      color: #fff !important;-->
<!--    }-->

<!--    .blog-post {-->
<!--      position: relative;-->
<!--    }-->

<!--    .b-light {-->
<!--      border-color: #bbb4;-->
<!--    }-->
<!--  }-->

<!--  /*.blog-post, .wrapper-md > .breadcrumb, .m-t-lg.m-b-lg, .wrapper-md > .no_search_result {*/-->
<!--  /*  max-width: 800px;*/-->
<!--  /*  margin-left: auto;*/-->
<!--  /*  margin-right: auto;*/-->
<!--  /*}*/-->

<!--  /*分页按钮*/-->
<!--  .el-pagination {-->
<!--    text-align: center;-->
<!--    margin-bottom: 12px;-->
<!--  }-->

<!--  .el-pager li {-->
<!--    min-width: 37px;-->
<!--    min-height: 33px;-->
<!--    padding-top: 4PX;-->
<!--  }-->
<!--</style>-->
