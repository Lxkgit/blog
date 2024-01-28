<template>
  <div class="main" :style="`background-image: url(${blogListPageWallpaper.value});`">
    <div>
      <MyHead></MyHead>
    </div>
    <div class="show">
      <!--    <div id="bg" v-lazy:background-image="settingPost.background" />-->
      <el-row style="display: flex;justify-content: center; padding-top: 80px">
        <el-col :xs="24" :sm="18" :md="12">
<!--          <BlogType/>-->
          <BlogList/>
        </el-col>
        <el-col style="right: 200px;position: fixed" :xs="0" :sm="18" :md="3">
          <BlogLabel></BlogLabel>
          <BlogTimeArchive></BlogTimeArchive>
        </el-col>
      </el-row>
    </div>
    <div>
      <MyFoot></MyFoot>
    </div>
  </div>
</template>

<script>

  import MyHead from '../../common/MyHead'
  import MyFoot from '../../common/MyFoot'
  import BlogType from "./components/BlogType";
  import BlogList from "./components/BlogList";
  import {mapGetters} from "vuex";
  import BlogLabel from "./components/BlogLabel";
  import BlogTimeArchive from "./components/BlogTimeArchive";
  export default {
    name: 'HomeIndex',
    data () {
      return {

      }
    },
    components: {BlogTimeArchive, BlogLabel, BlogList, BlogType, MyFoot, MyHead},
    computed: {
      ...mapGetters('setting', ['blogListPageWallpaper']),
    },
    mounted() {
      this.blogListWallpaper()
    },
    methods: {
      blogListWallpaper() {
        this.$store.dispatch('setting/selectBlogListPageWallpaper', 'blog_list_page')
      }
    }
  }
</script>

<style scoped>
  .main {
    /*background: url("../../../../assets/loginbackground.jpg") no-repeat fixed;*/
    /*背景图片位置固定 不重复*/
    background: fixed  no-repeat;
    /*背景图片大小自适应屏幕*/
    background-size: cover;

  }

  #bg {
    background-size: cover;
    background-position: right bottom;
    background-repeat: no-repeat;
    position: fixed;
    z-index: -1;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }
  .show{
    min-height: 807px;
  }
</style>
