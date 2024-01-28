<template>
<!--  <div class="main">-->
  <div class="main" :style="`background-image: url(${homePageWallpaper.value});`">
    <div>
      <MyHead></MyHead>
    </div>
    <div class="show">
      <!--    <div id="bg" v-lazy:background-image="settingPost.background" />-->
          <el-row style="display: flex;justify-content: center; padding-top: 80px">
            <el-col :xs="0" :sm="6" :md="4">
              <LeftSide></LeftSide>
            </el-col>
            <el-col :xs="24" :sm="12" :md="10">
              <ShowBlog></ShowBlog>
            </el-col>
            <el-col :xs="0" :sm="6" :md="4">
              <RightSide></RightSide>
            </el-col>
          </el-row>
    </div>
    <div>
      <MyFoot></MyFoot>
    </div>
  </div>
</template>

<script>

import LeftSide from './left_side/LeftSide'
import RightSide from './right_side/RightSide'
import ShowBlog from './content/ShowBlog'
import MyHead from '../common/MyHead'
import MyFoot from '../common/MyFoot'
import {mapGetters} from 'vuex'
export default {
  name: 'HomeIndex',
  data () {
    return {

    }
  },
  components: {MyFoot, MyHead, ShowBlog, RightSide, LeftSide},
  computed: {
    ...mapGetters('setting', ['homePageWallpaper'])
  },
  mounted() {
    this.homeWallpaper()
  },
  methods: {
    homeWallpaper () {
      this.$store.dispatch('setting/selectHomePageWallpaper', 'home_page')
    }
  }
}
</script>

<style scoped>
  .main {
    /*background:url("../../../assets/preview.jpg") no-repeat fixed;*/
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
