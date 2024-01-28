<template>
  <div>
    <div class="label_grid">
      <ArticleLabelGrid/>
    </div>
    <div class="label_tree">
      <ArticleLabelTree/>
    </div>
  </div>
</template>

<script>
  import ArticleLabelTree from "./ArticleLabelTree";
  import ArticleLabelGrid from "./ArticleLabelGrid";
  export default {
    components: {ArticleLabelGrid, ArticleLabelTree}
  }
</script>

<style scoped>
  .label_tree{
    width: 15%;
    display: inline-block;
    vertical-align: top;
  }
  .label_grid{
    width: 84%;
    display: inline-block;
    vertical-align: top;
  }
</style>

