<template>
  <div class="label-nav">
    <h4 class="label-title">
      标签栏
    </h4>
    <div class="label-body">
      <el-tag size="small" class="label-tag" v-for="tag in blogArticleLabelList" :key="tag.labelId">{{tag.labelName}}</el-tag>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from "vuex";

  export default {
    name: 'BlogLabel',
    mounted(){
      this.selectLabelList()
    },
    computed: {
      ...mapGetters('articleLabel',['blogArticleLabelList'])
    },
    methods: {
      selectLabelList(){
        this.$store.dispatch('articleLabel/selectArticleLabelList').then(resp => {

        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .label-nav{
    /*position: absolute;*/
    min-width: 240px;
    max-width: 240px;
    background-color: rgba(26,26,26,.1);

    .label-title{
      padding-left: 12px;
      padding-top: 12px;
      padding-bottom: 8px;
      border-bottom: 1px solid #e7e7e7;
    }
    .label-body{
      margin: 12px;
      .label-tag{
        margin: 5px;
      }
    }
  }

</style>
