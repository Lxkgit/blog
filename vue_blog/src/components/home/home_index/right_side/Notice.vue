<template>
  <div class="notice-panel tools-item">
    <h4 class="widget-title">
      公告栏
    </h4>
    <div>
      这个公告栏
    </div>
<!--    <div class="textwidget" v-html="notice.notice" />-->
  </div>
</template>

<script>
export default {
  name: 'Notice'
}
</script>

<style scoped>
  .widget-title {
    position: relative;
    margin: 0 0 10px;
    border-bottom: 1px solid #e7e7e7;
    font-size: 16px;
    color: #000;
    font-weight: bold;
    border-bottom: 1px solid #E1E8ED;
    padding: 8px 10px;
  }
</style>
