<template>
  <div class="blog-page">
    <div id="main-part" class="blog-type">
      <div class="b-wrap">
        <div class="primary-menu-itnl">
          <div id="primaryPageTab" class="page-tab report-wrap-module">
            <ul class="con">
              <li>
                <router-link :to="{path:'/blog'}">
                  <div class="round home">
                    <font-awesome-icon size="lg" icon="home"/>
                  </div>
                  <span style="color: #FFFFFF" @click="setArticleTypeP({value: 0})">全部</span>
                </router-link>
              </li>
            </ul>
          </div>
          <span class="tab-line-itnl"/>
          <div id="primaryChannelMenu" class="channel-menu-itnl report-wrap-module">
            <div v-for="(item,index2) in category" :key="index2" class="item">
              <el-popover
                popper-class="popover-item"
                placement="bottom"
                width="100"
                trigger="hover">
                <template v-for="(item2,index3) in item.children">
                  <a :href="'#/blog'"
                     v-if="item.value === item2.parent"
                     :key="index3"
                     style="color: #FFFFFF; text-decoration:none;"
                     @click="setArticleTypeC(item2)"> {{ item2.label }}</a>
                </template>
                <a slot="reference" style="color: #FFFFFF" class="name" href="javascript:void(0);" @click="setArticleTypeP(item)"><span>{{ item.label }}<em></em></span></a>
              </el-popover>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="blog-list" class="blog-list">
      <div :style="{height:tableHeight}" id="blog-post" class="blog-post">
        <div v-for="(item,index) in blogList.list"
             v-if="typeP === 0 ? true : item.type_value === typeP && (typeC === 0 ? true : item.type_value_add === typeC)"
             :key="index" class="panel">
          <div class="index-post-img">
            <router-link :to="{path:'/BlogDetail',query:{id: item.id}}" class="router-link">
              <div :background-image="posts.image" class="item-thumb"/>
            </router-link>
          </div>
          <div class="post-meta wrapper-lg">
            <h2 class="m-t-none index-post-title">
              <router-link style="text-decoration: none;" :to="{path:'/BlogDetail',query:{id: item.id}}">
                {{ item.title}}
              </router-link>
            </h2>
            <p class="summary text-muted">
              {{ item.content_html | ellipsis}}
            </p>
            <div class="line b-b b-light"/>
            <div class="text-muted">
              <span><font-awesome-icon icon="clock"/>{{ item.create_time }}</span>
              <span><font-awesome-icon icon="eye"/>{{ item.browse_count }}次阅读</span>
              <span class="good"><font-awesome-icon icon="thumbs-up"/>{{ item.like_count }}人点赞</span>
            </div>
          </div>
        </div>
      </div>
      <el-pagination
        @size-change="getBlogList"
        @current-change="getBlogList"
        :page-size="this.pageSize"
        layout="prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>

  import {mapGetters} from "vuex";

  export default {
    name: "BlogList",
    data() {
      return {
        // 表格高度
        blogHeight:"",
        // 数据总数
        blogCount:0,
        // 是否加载中
        blogLoading:false,
        // 表格搜索条件
        blogSearch:{
          page:1
        },
        category: [],
        typeP: 0,
        typeC: 0,
        pageSize: 4,
        total: 0,
        // total: 0,
        posts: {
          image: 'https://xiaoyou66.com/assets/images/background/img111.jpg', // 代表图片
          comment: 0, // 评论数
          is_top: false, // 是否置顶
          encryption: false // 是否加密
        },
        // blogList: []
      }
    },
    created() {
      let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
      // 计算文章列表高度
      // this.tableHeight = windowHeight - 100 +'px';
      this.tableHeight = 100 + '%';

    },
    computed: {
      ...mapGetters('article',['blogList'])
    },
    mounted() {
      this.getBlogList(1)
      this.selectArticleTypeTreeHome()
      document.getElementById("blog-post").addEventListener('scroll',this.onTableScroll);
    },
    beforeDestroy() {
      // 移除监听事件
      window.removeEventListener('scroll', this.onTableScroll)
    },
    methods: {
      onTableScroll(){
        var obj = document.getElementById("blog-post");
        var scrollHeight = obj.scrollHeight;
        var scrollTop = obj.scrollTop;
        var objHeight = obj.offsetHeight;
        // 100为阈值，可根据实际情况调整
        if(scrollHeight <= (scrollTop + objHeight + 100) && !this.blogLoading && this.blogList.length<this.blogCount){
          this.blogLoading = true;
          this.blogSearch.page++;
          setTimeout(()=>{
            this.getBlogList(this.blogSearch.page);
          },300)
        }
      },
      getBlogList(page) {
        this.$store.dispatch('article/getBlogList', {page: page, size: this.pageSize}).then((resp) => {
          // this.blogList = resp.result
          this.blogLoading = false;
          this.total = resp.result.total
        })
      },
      selectArticleTypeTreeHome() {
        this.$store.dispatch('articleType/selectArticleTypeTreeHome').then(resp => {
          if (resp && resp.code === 200) {
            this.category = resp.result
          }
        })
      },
      setArticleTypeP(item) {
        this.typeP = item.value
        this.typeC = 0
      },
      setArticleTypeC(item) {
        this.typeP = item.parent
        this.typeC = item.value
      }
    },
    filters: {
      ellipsis (value) {
        if (!value) return ''
        if (value.length > 100) {
          return value.slice(0, 100) + '...'
        }
        return value
      }
    }
  }
</script>

<style>
  .popover-item{
    background-color: rgba(26,26,26,.2);
    border-style: none;
    border-bottom-color: #03204e !important;
  }
  .popover-item[x-placement^=bottom] .popper__arrow{
    border-bottom-color: rgba(26,26,26,.2);
  }
  .popover-item[x-placement^=bottom] .popper__arrow::after{
    border-bottom-color: rgba(26,26,26,.2);
  }
</style>

<style scoped lang="scss">
  .blog-page {
    .blog-type {
      padding: 0 10px;
      /*新的导航栏*/
      .b-wrap {
        background-color: rgba(26,26,26,.1);
        border-radius: 20px;
        margin-bottom: 20px;
        .primary-menu-itnl {
          display: -ms-flexbox;
          display: flex;
          -ms-flex-pack: justify;
          justify-content: space-between;
          -ms-flex-align: center;
          align-items: center;
          padding: 7px 0;
          width: 100%;
          .page-tab {
            -ms-flex-align: center;
            align-items: center;
            display: -ms-flexbox;
            display: flex;
            padding: 0;
            list-style: none;
            margin: 3px 0 0 0;
            .con {
              display: -ms-flexbox;
              display: flex;
              padding: 0;
              list-style: none;
              margin: 3px 0 0 0;
              li {
                cursor: pointer;
                width: 58px;
                height: 68px;
                text-align: center;
                font-size: 14px;
                white-space: nowrap;
                border-radius: 16px;
                transition: all .3s;
                .round {
                  position: relative;
                  width: 36px;
                  height: 36px;
                  border-radius: 36px;
                  line-height: 36px;
                  text-align: center;
                  margin: 0 auto 4px;
                }
                .home {
                  color: #ffffff;
                }
              }
            }
          }
          .tab-line-itnl {
            display: inline-block;
            height: 67px;
            border-left: 1px solid #e7e7e7;
            margin: 0 10px;
          }
          .channel-menu-itnl {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            height: 68px;
            -ms-flex: 1;
            flex: 1;
            .item {
              cursor: pointer;
              display: -ms-flexbox;
              display: -webkit-box;
              display: flex;
              height: 34px;
              margin: 6px;
              float: left;
              a {
                text-decoration:none;
                display: block;
                font-size: 12px;
                padding: 6px 7px;
                height: 20px;
                float: left;
              }
              .name {
                font-size: 14px;
                display: -ms-flexbox;
                display: flex;
                -ms-flex-align: center;
                align-items: center;
                white-space: nowrap;
                em {
                  font-style: normal;
                  font-size: 12px;
                  display: inline-block;
                  background: #73c9e5;
                  border-radius: 2px;
                  color: #fff;
                  margin-left: 1px;
                  transform: scale(.85);
                  width: 32px;
                  text-align: center;
                }
              }
            }
          }
        }
      }
    }
    .blog-list{
      .blog-post {
        .panel {
          border: 0;
          border-radius: 5px;
          margin-bottom: 15px;
          position: relative;
          transition: all .2s;
          box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.2) !important;
          .index-post-img {
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
            overflow: hidden;
            .router-link {
              .item-thumb {
                min-height: 250px;
                position: relative;
                display: block;
                background-position: 50% 50%;
                background-size: cover;
                border-top-left-radius: 4px;
                border-top-right-radius: 4px;
                transition: all .3s;
              }
            }
          }
          .wrapper-lg {
            padding: 20px;
            background-color: white;
            .m-t-none {
              margin-top: 0 !important;
            }
            .index-post-title {
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
              font-size: 22px;
              transition: all .3s;
            }
            .b-b {
              border-bottom: 1px solid #dee5e7;
            }
            /*图标相关*/
            .text-muted {
              span {
                margin-right: 15px;
                padding-right: 4px;
              }
            }
          }
        }
      }
    }
  }

  /*置顶*/
  .stick {
    position: absolute;
    z-index: 9;
    right: 0;
  }

  h1, h2, h3 {
    margin-top: 20px;
    margin-bottom: 10px;
    font-family: inherit;
    font-weight: 500;
    line-height: 1.1;
  }

  /*分页按钮*/
  .el-pagination {
    text-align: center;
    margin-bottom: 12px;
  }

  .el-pager li {
    min-width: 37px;
    min-height: 33px;
    padding-top: 4PX;
  }

  @media screen and (min-width: 1200px) {
    .panel .item-thumb {
      height: 300px;
    }

    .panel .post-meta {
      position: relative;
      margin-top: -300px;
      height: 168px;
      padding-top: 133px !important;
      padding-bottom: 0 !important;
      background-color: rgba(0, 0, 0, .3) !important;
      color: #fff !important;
      transition: all .3s;
      border-radius: 5px;
    }

    .panel .post-meta > h2, #posts > div.blog-post > .panel-small .post-meta > h2 {
      text-align: center;
      text-shadow: 0 0 3px #fff;
    }

    .blog-post > .panel:hover .summary {
      transform: translateY(10px);
      opacity: 1;
    }

    .blog-post > .panel:hover .index-post-title {
      transform: translateY(-30px) scale(1.2, 1.2);
      opacity: 1;
      padding: 0 60px;
    }

    .blog-post > .panel:hover .item-thumb {
      transform: scale(1.2, 1.2);
    }

    .blog-post > .panel:hover .text-muted {
      transform: translateY(10px);
      opacity: 1;
    }

    .blog-post > .panel:hover .line {
      transform: translateY(10px);
      opacity: 1;
    }

    .panel .post-meta > .text-muted {
      position: absolute;
      bottom: 16px;
      opacity: 0;
      transition: all .3s;
    }

    .panel .post-meta > .summary {
      /*position: absolute;*/
      bottom: 60px;
      /*width: 710px;*/
      padding: 3px;
      opacity: 0;
      transition: all .3s;
    }

    .panel .post-meta > .line {
      position: absolute;
      bottom: 40px;
      width: 300px;
      opacity: 0;
      transition: all .3s;
    }

    #posts > div.blog-post > .panel-small .post-meta > p, #posts > div.blog-post > .panel .post-meta > div {
      transition: all .3s;
      transform: translate(-10px);
      opacity: 0;
    }

    .blog-post > .panel:hover .index-post-img {
      filter: blur(3px);
    }

    .panel .post-meta * {
      color: #fff !important;
    }

    .blog-post {
      position: relative;
    }

    .b-light {
      border-color: #bbb4;
    }
  }
</style>
