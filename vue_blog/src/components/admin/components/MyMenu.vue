<template>
  <div>
    <el-menu-item v-if="item.children === undefined" :index="indexKey.toString()" @click="gotoSite(item.path, item.nameZh)">
      <font-awesome-icon :icon="item.iconCls" />
      <span slot="title">{{ item.nameZh }}</span>
    </el-menu-item>
    <el-submenu v-else :index="indexKey.toString()">
      <template slot="title">
        <font-awesome-icon :icon="item.iconCls" />
        <span slot="title">{{ item.nameZh }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="(item2,index2) in item.children" :key="index2" :index="`${indexKey.toString()}-${index2.toString()}`" @click="gotoSite(`${item2.path}`, item2.nameZh)">
          <span slot="title">{{ item2.nameZh }}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </div>
</template>

<script>
export default {
  name: 'MyMenu',
  props: { indexKey: { type: Number, default: 0 }, item: { type: Object, default: () => { return {} } } },
  methods: {
    gotoSite (path, name) {
      // 跳转路由
      this.$router.push(path)
      // 添加标签
      this.$store.dispatch('tab/addTag', { name, path })
    }
  }
}
</script>

<style scoped>

</style>
