<template>
  <div class="main" :style="`background-image: url(${blogPageWallpaper.value});`">
    <div>
      <MyHead></MyHead>
    </div>
    <div class="show">
      <!--    <div id="bg" v-lazy:background-image="settingPost.background" />-->
      <el-row style="display: flex;justify-content: center; padding-top: 80px">
        <el-col :xs="24" :sm="22" :md="13">
          <BlogDetail/>
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
  import BlogDetail from "./components/BlogDetails";
  import {mapGetters} from "vuex";
  export default {
    name: "BlogDetailsIndex",
    data () {
      return {

      }
    },
    components: {BlogDetail, BlogList, BlogType, MyFoot, MyHead},
    computed: {
      ...mapGetters('setting', ['blogPageWallpaper'])
    },
    mounted() {
      this.backgroundImg()
    },
    methods: {
      backgroundImg(){
        this.$store.dispatch('setting/selectBlogPageWallpaper', 'blog_page')
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
