<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>网站设置</span>
      </div>
      <el-tabs v-model="active" type="card" @tab-click="change">
        <el-tab-pane label="站点设置" name="1">
          <WebSetting/>
        </el-tab-pane>
        <el-tab-pane label="壁纸设置" name="2">
          <WallpaperSetting/>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
  import WebSetting from './components/WebSetting'
  import WallpaperSetting from './components/WallpaperSetting'

  export default {
    name: 'SettingIndex',
    components: {WallpaperSetting, WebSetting},
    data() {
      return {
        active: '1'
      }
    },
    mounted() {
      this.change({ name: '1' })
    },
    methods: {
      change(item) {
        switch (item.name) {
          case '1':
            this.$store.dispatch('setting/selectWebSetting')
            break
        }
      }
    }
  }
</script>

<style scoped>

</style>
