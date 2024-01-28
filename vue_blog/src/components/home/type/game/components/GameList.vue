<template>
  <div class="game-list-box">
    <div class="game-list-nav">

    </div>
    <div class="game-list-body">

    </div>
  </div>
</template>

<script>
    export default {
        name: 'GameList'
    }
</script>

<style scoped lang="scss">
  .game-list-box {
    height: 900px;
    border-radius: 10px;
    .game-list-nav {
      border-radius:10px 10px 0 0;
      height: 50px;
      background-color: #f0c78a;
    }
    .game-list-body {
      border-radius:0 0 10px 10px;
      background-color: #47456d;
      height: 800px;
    }
  }

</style>
