<template>
    <div id="sidebar-right" class="giligili-left sidebar">
      <Notice></Notice>
    </div>
<!--  <div id="sidebar-right" class="giligili-left sidebar">-->
<!--    <notice v-if="settingHead.right_side.includes(4)" />-->
<!--    <MoreFunction v-if="settingHead.right_side.includes(5)" />-->
<!--    <Download v-if="settingHead.right_side.includes(6)" />-->
<!--    <RecentComment v-if="settingHead.right_side.includes(7)" />-->
<!--  </div>-->
</template>

<script>
import Notice from './Notice'
export default {
  name: 'RightSide',
  components: {Notice}
}
</script>

<style scoped>
  /*#sidebar-right {*/
  /*  float: left;*/
  /*  width: 14%;*/
  /*  padding-right: 5px;*/
  /*}*/

  .tools-item {
    position: relative;
    width: 100%;
    background: #fff;
    border-radius: 4px;
    overflow: hidden;
    margin-bottom: 8px;
    padding: 12px 16px;
    -webkit-box-shadow: 0 1px 3px rgba(26, 26, 26, .1);
    box-shadow: 0 1px 3px rgba(26, 26, 26, .1);
  }
</style>
