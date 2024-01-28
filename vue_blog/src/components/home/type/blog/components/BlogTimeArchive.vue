<template>
  <div class="time-nav">
    <h4 class="time-title">
      时间轴
    </h4>
    <div class="time-body">

      <el-timeline :reverse="reverse">
        <el-timeline-item
          v-for="(activity, index) in activities"
          :key="index"
          :timestamp="activity.timestamp">
          {{activity.content}}
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>

</template>

<script>
  export default {
    name: "BlogTimeArchive",
    data() {
      return {
        reverse: true,
        activities: [{
          content: '活动按期开始',
          timestamp: '2018-04-15'
        }, {
          content: '通过审核',
          timestamp: '2018-04-13'
        }, {
          content: '创建成功',
          timestamp: '2018-04-11'
        }]
      };
    }
  }
</script>

<style lang="scss" scoped>

  .time-nav{
    min-width: 240px;
    max-width: 240px;
    background-color: rgba(26,26,26,.1);
    .time-title{
      padding-left: 12px;
      padding-top: 12px;
      padding-bottom: 8px;
      border-bottom: 1px solid #e7e7e7;
    }
    .time-body{
      margin: 12px;
    }
  }

</style>
