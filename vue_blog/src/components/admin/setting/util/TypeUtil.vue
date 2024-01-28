<template>
  <div>
    <div v-for="(item,index) in options" :key="index">
      <el-divider v-if="item.type==='divider'" content-position="left">
        {{ item.title }}
      </el-divider>
      <TypeInput v-else-if="item.type==='input'" :title="item.title" :field="item.key" :model.sync="item.value" :type="item.type" />
      <TypeNumber v-else-if="item.type==='number'" :title="item.title" :field="item.key" :model.sync="item.num" :type="item.type" />
      <TypeUpload v-else-if="item.type==='upload'" :title="item.title" :field="item.key" :model.sync="item.value" :type="item.type" />
      <TypeText v-else-if="item.type==='text'" :title="item.title" :field="item.key" :model.sync="item.value" :type="item.type" />
      <TypeSwitch v-else-if="item.type==='switch'" :title="item.title" :field="item.key" :model.sync="item.bool" :type="item.type"/>
    </div>
  </div>
</template>

<script>
  import TypeText from './TypeText'
  import TypeInput from './TypeInput'
  import TypeNumber from './TypeNumber'
  import TypeUpload from './TypeUpload'
  import TypeSwitch from './TypeSwitch'
  export default {
    name: 'TypeUtil',
    components: {TypeSwitch, TypeText, TypeUpload, TypeNumber, TypeInput},
    props: { options: { type: Array, default: () => ([]) } },
    mounted() {

    }

  }
</script>

<style scoped>

</style>
