<template>
  <div>
    <el-row :gutter="20">
      <!--功能选择区-->
      <el-col :xs="24" :sm="15" :md="7">
        <TypeUtil :options="webSetting" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import TypeUtil from '../util/TypeUtil'

  export default {
    name: 'SettingSite',
    components: { TypeUtil },
    computed: {
      // 把getter混入到computed对象中,我们可以直接调用vuex里面的内容即可
      ...mapGetters('setting',['webSetting'])
    }
  }
</script>

<style scoped>

</style>
