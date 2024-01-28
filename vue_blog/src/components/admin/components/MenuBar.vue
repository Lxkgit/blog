<template>
  <div class="side-contain" :class="{'sideBarOpen':sideStatus}">
    <MenuBarLogo :collapse="sideStatus" />
    <el-menu class="side-menu el-menu-vertical-demo" :collapse="sideStatus">
      <MyMenu v-for="(item,index) in adminMenus" :key="index" :index-key="index" :item="item" />
    </el-menu>
  </div>
</template>

<script>
import MenuBarLogo from './MenuBarLogo'
import MyMenu from './MyMenu'
import {mapGetters} from 'vuex'
export default {
  name: 'MenuBar',
  data () {
    return {
      isCollapse: false
    }
  },
  components: {MyMenu, MenuBarLogo},
  computed: {
    ...mapGetters('tab',['sideStatus']),
    ...mapGetters('vuexLogin',['adminMenus']),
  }
}
</script>

<style>
  /**修复侧边栏收缩还是显示文字的问题 参考：https://github.com/ElemeFE/element/issues/17391**/
  .el-menu--collapse > div > .el-submenu > .el-submenu__title span,
  .el-menu--collapse > div > .el-submenu > .el-submenu__title .el-submenu__icon-arrow {
    display: none;
  }
</style>

<style scoped>
  .sideBarOpen{
    width: 64px !important;
  }
  .side-contain{
    width: 210px;
    position: fixed;
    bottom: 0;
    top: 0;
    left: 0;
  }
  .side-menu{
    height: 100%;
  }
</style>
