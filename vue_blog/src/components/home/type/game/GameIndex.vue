<template>
  <div class="game-page" :style="`background-image: url(${gamePageWallpaper.value});`">
    <div>
      <MyHead></MyHead>
    </div>
    <div class="game-box">
      <el-row style="display: flex;justify-content: center; padding-top: 80px;">
        <el-col style="margin-right: 50px;" :xs="24" :sm="6" :md="5">
          <GameBox></GameBox>
        </el-col>
        <el-col :xs="24" :sm="6" :md="10">
          <GameList></GameList>
        </el-col>
      </el-row>
    </div>
    <div>
      <MyFoot></MyFoot>
    </div>
  </div>
</template>

<script>

  import MyHead from "../../common/MyHead";
  import MyFoot from "../../common/MyFoot";
  import GameBox from "./components/GameBox";
  import GameList from "./components/GameList";
  import {mapGetters} from "vuex";
  export default {
    name: 'GameIndex',
    data () {
      return {

      }
    },
    components: {GameList, GameBox, MyFoot, MyHead},
    computed: {
      ...mapGetters('setting', ['gamePageWallpaper'])
    },
    mounted() {
      this.backgroundImg()
    },
    methods: {
      backgroundImg() {
        this.$store.dispatch('setting/selectGamePageWallpaper', 'game_page')
      }
    }
  }
</script>

<style scoped lang="scss">
  .game-page{
    /*背景图片位置固定 不重复*/
    background: fixed  no-repeat;
    /*背景图片大小自适应屏幕*/
    background-size: cover;
  }
</style>
