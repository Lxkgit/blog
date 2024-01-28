<template>
  <div>
    <div>后台管理主页 - 2</div>
    <div style="width:1000px;height:800px" ref="chart"></div>
  </div>
</template>

<script>
  export default {
    name: 'index',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    mounted () {
      this.initCharts();
    },
    methods: {
      initCharts () {
        let myChart = this.$echarts.init(this.$refs.chart);
        let base = +new Date(2000, 0, 1);
        let oneDay = 24 * 60 * 60 *1000;
        let data = [[base, 20]];
        for (let i = 1; i < 365; i++) {
          let now = new Date((base += oneDay));
          data.push([+now, Math.round((Math.random()) * 20)]);
        }
        // 绘制图表
        myChart.setOption({
          tooltip: {
            trigger: 'axis',
            position: function (pt) {
              return [pt[0], '10%'];
            }
          },
          title: {
            left: 'center',
            text: 'Large Ara Chart'
          },
          toolbox: {
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              restore: {},
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'time',
            boundaryGap: false
          },
          yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
          },
          dataZoom: [
            {
              type: 'inside',
              start: 0,
              end: 20
            },
            {
              start: 0,
              end: 20
            }
          ],
          series: [
            {
              name: 'Fake Data',
              type: 'line',
              smooth: true,
              symbol: 'none',
              areaStyle: {},
              data: data
            }
          ]
        });
      }
    }
  }
</script>

<style scoped>

</style>
