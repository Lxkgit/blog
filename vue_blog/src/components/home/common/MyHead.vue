<template>
  <div class="headBack">
    <div class="headBox">
      <div class="nav_menu_all">
        <div class="nav_menu">
          <a href="#/index"><i class="fa fa-wa fa-home"></i> 首页 </a>
        </div>
        <!--https://fontawesome.dashgame.com/-->
        <div class="nav_menu type_box">
          <i class="fa fa-wa fa-archive"></i> 分类 <i class="fa fa-wa fa-angle-down"></i>
          <div class="type_list">
            <div><a href="#/blog"><i class=""></i> 博客 </a></div>
            <div><a href="#/game"><i class=""></i> 游戏 </a></div>
          </div>
        </div>
        <div class="nav_menu">
          <a href="#/doc"><i class="fa fa-wa fa-book"></i> 文档 </a>
        </div>
        <div class="nav_menu">
          <a href="#/Friendslink"><i class="fa fa-wa fa-users"></i> 伙伴 </a>
        </div>
        <div class="nav_menu">
          <a href="#/About"><i class="fa fa-wa fa-vcard"></i> 关于 </a>
        </div>
        <div class="nav_menu">
          <a href="#/nav"><i class="fa fa-wa fa-chrome"></i> 导航 </a>
        </div>
      </div>
    </div>
    <div class="login_menu_list">
      <div class="login_menu" v-show="!loginFlag">
        <div class="login"><a href="#/register"><i class="fa fa-wa fa-registered"></i> 注册 </a></div>
        <div class="login"><a href="#/login"><i class="fa fa-wa fa-location-arrow"></i> 登录 </a></div>
      </div>
      <div class="login_menu" v-show="loginFlag">
        <div class="login self_space">
          <i class="fa fa-wa fa-cog"></i> 个人中心 <i class="fa fa-wa fa-angle-down"></i>
          <div class="self_list">
            <div><a href="#/"><i class=""></i> 后台管理 </a></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyHead',
  data () {
    return {
      keywords: '',
      loginFlag: false
    }
  },
  computed: {
    currentPath () {
      var x = this.$route.path.indexOf('/', 1)
      if (x !== -1) {
        return this.$route.path.substring(0, x)
      } else {
        return this.$route.path
      }
    }
  }
}
</script>

<style scoped>
  /*头部导航栏盒子*/

  .headBack {
    height: 35px;
    background: rgba(40, 42, 44, 0.6);
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .12), 0 0 6px 0 rgba(0, 0, 0, .04);
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    z-index: 100;
  }

  .headBox {
    padding-right: 10%;
    padding-left: 10%;
    background: transparent;
    border-bottom: none;
  }

  .nav_menu_all {
    width: 50%;
    line-height: 35px;
    top: 0;
    color: #fff;
    display: inline-block;
    float: left;
  }

  .nav_menu_all a {
    text-decoration: none;
    color: #fff;
    font-size: 13px;
    transition: all 0.2s ease-out;
  }

  .nav_menu_all a:hover {
    color: #00a7e0;
  }

  .nav_menu {
    padding-left: 1%;
    padding-right: 1%;
    height: 35px;
    display: inline-block;
  }

  .nav_menu:hover {
    background-color: #47456d;
  }

  .type_box {
    font-size: 13px;
    position: relative;
    padding-left: 1%;
    padding-right: 1%;
    height: 35px;
    top: 0;
    right: 0;
    cursor: pointer;
  }

  .type_box:hover .type_list {
    opacity: 1;
    visibility: visible;
  }

  .type_list {
    width: auto;
    padding: 10px 20px 10px 20px;
    background: rgba(40, 42, 44, 0.6);
    border-radius: 0 0 2px 2px;
    position: absolute;
    min-width: 26px;
    left: 0;
    top: 38px;
    opacity: 0;
    visibility: hidden;
    transform-origin: right;
    transition: all 0.3s ease-out;
  }

  .login_menu_list {
    width: 50%;
    line-height: 35px;
    top: 0;
    color: #fff;
    display: inline-block;
    float: right;

  }

  .login_menu_list a {
    text-decoration: none;
    color: #fff;
    font-size: 13px;
    transition: all 0.2s ease-out;
  }

  .login_menu_list a:hover {
    color: #00a7e0;
  }

  .login_menu {
    margin-right: 9%;
    height: 100%;
    color: #fff;

  }

  .login {
    float: right;
    padding-left: 1%;
    padding-right: 1%;
    height: 35px;
    display: inline-block;
  }

  .login:hover {
    background-color: #47456d;
  }

  .self_space {
    position: relative;
    padding-left: 1%;
    padding-right: 1%;
    height: 35px;
    top: 0;
    right: 0;
    cursor: pointer;
  }

  .self_space:hover .self_list {
    opacity: 1;
    visibility: visible;
  }

  .self_list {
    width: auto;
    padding: 10px 20px 10px 20px;
    background: rgba(40, 42, 44, 0.6);
    border-radius: 0 0 2px 2px;
    position: absolute;
    min-width: 52px;
    left: 0;
    top: 38px;
    opacity: 0;
    visibility: hidden;
    transform-origin: right;
    transition: all 0.3s ease-out;
  }
</style>
