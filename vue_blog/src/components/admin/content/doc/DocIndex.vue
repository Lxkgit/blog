<template>
  <div style="z-index: -1">
    <div class="doc_content">
      <DocEditor/>
    </div>
    <div class="doc_catalog">
      <DocCatalog/>
    </div>
  </div>
</template>

<script>
  import DocCatalog from "./components/DocCatalog";
  import DocEditor from "./components/DocEditor";
  export default {
    name: 'DocIndex',
    components: {DocEditor, DocCatalog},
  }
</script>

<style scoped>
  .doc_catalog{
    width: 15%;
    display: inline-block;
    vertical-align: top;
  }
  .doc_content{
    width: 84%;
    display: inline-block;
    vertical-align: top;
  }
</style>
