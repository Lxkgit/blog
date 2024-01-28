<template>
  <div class="game-box">
    <div class="head">
      <div class="head-box">
        <div class="head-img">
          <img src="../../../../../assets/logo.png" alt="head">
        </div>
        <div class="self-msg">
          <div class="game-name"><span>steam用户名：&nbsp</span><span>asd</span></div>
          <div class="game-id"><span>steamId：&nbsp</span><span>asd</span></div>
        </div>
      </div>
    </div>
    <div class="all-game-date">
      <div>
        <p class="num">123</p>
        <p class="text">数量</p>
      </div>
      <div >
        <p class="num">321</p>
        <p class="text">总时长</p>
      </div>
      <div>
        <p class="num">222</p>
        <p class="text">价值</p>
      </div>
    </div>
    <div class="game-list">
      <template v-for="item in gameList">
        <div class="game-body">
          <div class="game-img">
            <img src="" alt="">
          </div>
          <div class="game-date">
            {{item.name}}
            <el-progress :percentage="item.lastHour/item.allHour*100"></el-progress>
          </div>
        </div>
      </template>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .game-box {
    height: 500px;
    background-color: #f0c78a;
    border-radius: 10px;
    padding: 20px;
    .head {
      .head-box {
        padding-top: 10px;
        padding-left: 10px;
        display: flex;
        .head-img {
          display: inline-block;
          img {
            height: 60px;
            background-color: #f7f7f7;
          }
        }
        .self-msg {
          text-align:left;
          display: inline-block;
          font-size: 15px;
          margin-left: 10px;
          margin-top: 8px;
          .game-name {
          }
          .game-id {
          }
        }
      }
    }
    .all-game-date {
      text-align: center;
      div {
        display: inline-block;
        margin-top: 40px;
        margin-left: 20px;
        margin-right: 20px;
      }
      p {
        font-size: 15px;
      }
    }
    .game-list {
      margin-top: 40px;
        .game-body {
          margin-top: 20px;
          display: flex;
          .game-img {
            width: 38%;
            display: inline-block;
            img {
              width: 100%;
              height: 70px;
              background-color: #47456d;
            }
          }
          .game-date {
            margin-left: 3%;
            width: 59%;
            display: inline-block;
            margin-top: 20px;
          }
        }

    }
  }
</style>

<script>
  export default {
    name: 'GameBox',
    data () {
      return {
        gameList: [
          {"img":"img", "allHour": 20, "lastHour": 1, "name":"缺氧"},
          {"img":"img", "allHour": 20, "lastHour": 1, "name":"饥荒"}
        ]
      }
    }
  }
</script>
