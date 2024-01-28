<template>
  <div id="user-card" class="tools-item">
    <div class="photo-background">
      <div
        class="photo-background"
      />
<!--      :style="`background:url(${myInfo.background}) no-repeat center center;-webkit-background-size:cover;background-size:cover`"-->
    </div>
    <div class="photo-wrapper clearfix">
      <div class="photo-wrapper-tip text-center">
        <img class="about-photo" src="../../../../assets/logo.png" alt>
<!--        <img class="about-photo" :src="myInfo.avatar" alt>-->
      </div>
    </div>
    <p class="desc">
      个人描述
<!--      {{ myInfo.introduce }}-->
    </p>
    <div id="user-card-info">
      <a id="user-card-article" class="giligili-left">
<!--        <p class="num">{{ myInfo.post }}</p>-->
        <p class="num">123</p>
        <p class="text">文章</p>
      </a>
      <a id="user-card-shuoshuo" class="giligili-left">
<!--        <p class="num">{{ myInfo.diary }}</p>-->
        <p class="num">321</p>
        <p class="text">日记</p>
      </a>
      <a id="user-card-comment" class="giligili-left">
<!--        <p class="num">{{ myInfo.comment }}</p>-->
        <p class="num">222</p>
        <p class="text">评论</p>
      </a>
    </div>
<!--    <div class="textwidget">-->
<!--      <div class="links-of-author motion-element">-->
<!--        <span v-for="(item,index) in myInfo.social" :key="index" class="links-of-author-item">-->
<!--          <el-tooltip class="item" effect="dark" :content="item.name" placement="top-start">-->
<!--            <a :href="item.link" target="_blank" :title="item.name">-->
<!--              <font-awesome-icon v-if="item.icon!=null" :icon="item.icon" size="lg"/>-->
<!--              <img-->
<!--                v-else-->
<!--                alt-->
<!--                style="height: 24px;"-->
<!--                :title="item.name"-->
<!--                :src="item.image"-->
<!--              >-->
<!--            </a>-->
<!--          </el-tooltip>-->
<!--        </span>-->
<!--      </div>-->
<!--    </div>-->
  </div>
</template>

<script>
export default {
  name: 'Myself'
}
</script>

<style scoped>
  #user-card-info {
    background-color: #ffffff;
    height: 120px;
    margin: -34px 0 19px 0;
  }

  #user-card-info a {
    width: 33.3%;
    height: 92px;
    text-align: center;
    float: left;
    cursor: pointer;
  }

  #user-card-info a:hover {
    color: #4c4c4c;
  }

  #user-card-info .num {
    letter-spacing: 0;
    padding-top: 35px;
    font-weight: bold;
    font-size: 14px;
    color: #00a1d6;
  }

  /*联系方式*/
  .links-of-author {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    margin: 0 0 20px;
  }

  .links-of-author-item {
    margin: 5px 4px 0;
    border-radius: 50%;
  }

  .links-of-author span :hover {
    background: #97dffd;
  }

  .links-of-author-item a {
    display: block;
    text-decoration: none;
    overflow: visible;
  }

  .links-of-author-item a {
    max-width: 216px;
    box-sizing: border-box;
    display: inline-block;
    margin-right: 0;
    margin-bottom: 0;
    padding: 0 5px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    border-bottom: none;
    vertical-align: middle;
  }

  .links-of-author-item a {
    font-size: 16px;
    width: 42px;
    height: 42px;
    line-height: 42px;
    border-radius: 42px;
    color: rgba(0, 0, 0, .5);
    background-color: rgba(0, 0, 0, .1);
    font-style: normal;
    display: flex;
    vertical-align: middle;
    align-items: center;
    justify-content: center;
  }

  #user-card {
    text-align: center;

  }

  .photo-background {
    border-top-right-radius: 9px;
    border-top-left-radius: 9px;
    height: 120px;

    background:url("../../../../assets/myself.png")no-repeat center center;-webkit-background-size:cover;background-size:cover;
  }

  .widget_kratos_about .photo-background {
    border-top-right-radius: 9px;
    border-top-left-radius: 9px;
    height: 120px;
    background: no-repeat center center;
    -webkit-background-size: cover;
    background-size: cover;
  }

  .photo-wrapper {
    background-color: #fff;
    width: 100%;
  }

  .photo-wrapper img {
    left: 133px;
    margin: -50px 0 0;
    width: 90px;
    height: 90px;
    border: rgba(255, 255, 255, .4) 4px solid;
    border-radius: 20%;
    transition: all .3s;
    cursor: pointer;
  }

  /*图片悬停旋转放大效果*/
  .photo-wrapper img:hover {
    transform: scale(1.3) rotate(360deg);
  }

  .desc {
    margin: 0;
    background-color: #ffffff;
  }
</style>
