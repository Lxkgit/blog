<template>
  <div id="sidebar-left" class="giligili-left">
    <Myself></Myself>
  </div>
  <!--  <div id="sidebar-left" class="giligili-left">-->
  <!--    <MyInfo v-if="settingHead.left_side.includes(1)" />-->
  <!--    <music-box v-if="settingHead.left_side.includes(2)" />-->
  <!--    <BiliCard v-if="settingHead.left_side.includes(3)" />-->
  <!--  </div>-->
</template>

<script>
import Myself from './Myself'
export default {
  name: 'LeftSide',
  components: {Myself}
}
</script>

<style scoped>
  #sidebar-left #user-card {
    padding: 0;
  }
</style>
