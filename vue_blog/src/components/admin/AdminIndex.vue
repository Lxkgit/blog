<!--管理员界面的布局文件-->
<template>
    <div>
      <MenuBar />
      <div class="main-container" :class="{'container-open':sideStatus}">
        <div class="main-head">
          <HeadBar :style="{left: headBarLeft}" style="position: fixed; top: 0; right: 0; z-index: 20"/>
          <PageTab style="position: fixed; top: 50px; z-index: 10"/>
        </div>
        <div class="main-bg" >
          <router-view class="layout-main" />
        </div>
      </div>
    </div>
</template>

<script>
import MenuBar from './components/MenuBar'
import HeadBar from './components/HeadBar'
import PageTab from './components/PageTab'
import {mapGetters} from "vuex";
export default {
  name: 'AdminPage',
  data () {
    return {
      // headBarLeft: '210px'
    }
  },
  components: {PageTab, HeadBar, MenuBar},
  computed: {
    ...mapGetters('tab',['sideStatus']),
    ...mapGetters('vuexLogin',['adminMenus']),
    headBarLeft(){
      if (!this.sideStatus)
        return '210px';
      else
        return '64px';
    }
  },
  mounted () {
    // 页面刷新时及时保存tag信息
    window.addEventListener('beforeunload', () => this.$store.dispatch('tab/saveStatus'))
    // 恢复状态
    this.$store.dispatch('tab/restoreStatus')
  }
}
</script>

<style>
  /*图片地址为空时自动隐藏img*/
  img[src=""],img:not([src]){
    opacity:0;
  }
  /*清除输入框选中黑色*/
  input{outline:none}
  /*输入框界面*/
  .el-input.el-input--prefix {
    margin-top: 10px;
  }
</style>

<style scoped>
  .container-open{
    margin-left: 64px!important;
  }
  .main-container{
    margin-left: 210px;
    height: 100%;
  }
  .main-bg{
    bottom: 0;
    top: 200px;
    left: 210px;
    right: 0;
    z-index: 0;
  }
  .layout-main{
    margin-top: 80px;
    padding: 15px;
    z-index: -1;
  }
</style>

<!--<template>-->
<!--  <el-container id="admin-body">-->
<!--    <el-header style="z-index: 1;height: 80px;margin-left: -20px">-->
<!--      <Header style="position: absolute;width: 98%;"></Header>-->
<!--    </el-header>-->
<!--    &lt;!&ndash;<el-row style="height: 100%;">&ndash;&gt;-->
<!--    <el-container>-->
<!--      <el-aside style="height: 112%;margin-top: -80px;width: 250px">-->
<!--        <admin-menu></admin-menu>-->
<!--      </el-aside>-->
<!--      <el-main>-->
<!--        <router-view/>-->
<!--      </el-main>-->
<!--    </el-container>-->
<!--  </el-container>-->
<!--</template>-->

<!--<script>-->
<!--import AdminMenu from './AdminMenu'-->
<!--import Header from './Header'-->

<!--export default {-->
<!--  name: 'AdminIndex',-->
<!--  components: {AdminMenu, Header},-->
<!--  data () {-->
<!--    return {-->
<!--      dialogVisible: false-->
<!--    }-->
<!--  },-->
<!--  mounted () {-->
<!--    // this.$router.replace('/admin/dashboard')-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--  #admin-body {-->
<!--  height: 95vh;-->
<!--}-->
<!--</style>-->
