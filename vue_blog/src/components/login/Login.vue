<template>
  <div class="nav" :style="`background-image: url(${loginPageWallpaper.value});`">
    <div class="login-block">
      <div class="login">
        <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
                 label-width="0px" v-loading="loading">
          <img src="https://img-blog.csdnimg.cn/20191224222225841.png?x-oss-process=image/resize,m_fixed,h_224,w_224" alt="logo" class="logo"/>
          <div style="text-align: center;margin-bottom: 5px;">我的个人小站</div>
          <el-form-item prop="email">
            <el-input type="text" v-model="loginForm.email"
                      auto-complete="off" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password"
                      auto-complete="off" placeholder="密码"></el-input>
          </el-form-item>
          <el-checkbox class="login_remember" v-model="checked"
                       label-position="left"><span style="color: #505458;">记住密码</span></el-checkbox>
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="login">登录</el-button>
            <router-link to="/register">
              <el-button type="primary" style="width: 40%;background: #505458;border: none">注册</el-button>
            </router-link>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
  name: 'Login',
  data () {
    return {
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        email: '470687917@qq.com',
        password: '123456'
      },
      loading: false
    }
  },
  computed: {
    ...mapGetters('setting', ['loginPageWallpaper']),
  },
  mounted() {
    this.loginWallpaper()
  },
  methods: {
    loginWallpaper () {
      this.$store.dispatch('setting/selectLoginPageWallpaper', 'login_page')
    },
    login () {
      this.$store.dispatch('vuexLogin/login', this.loginForm).then( (resp) => {
        if (resp.code === 200) {
          var path = this.$route.query.redirect
          this.$router.replace({path: path === '/' || path === undefined ? '/admin/index' : path})
        } else {
          this.$alert(resp.data.message, '提示', {
            confirmButtonText: '确定'
          })
        }
      })
    }
  }
}
</script>

<style scoped>
  .nav {
    background: url("../../assets/loginbackground.jpg") no-repeat right bottom;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
    z-index: -1;
  }
  .logo {
    background: url("../../assets/logo.png");
    width: 80px;
    height: 80px;
    background-size:cover;
    text-align:center;
    /*justify-content: space-between;*/
    /*position: relative;*/
    /*top: 95px;*/
    /*right: -118px;*/
    /*border: rgba(255,255,255,.4) 4px solid;*/
  }
  .login-container {
    border-radius: 15px;
    margin-top: 200px;
    margin-left: auto;
    margin-right: auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0 auto 10px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin-top: 20px;
    margin-bottom: 25px;
    margin-left: 30px;
    display: flex;
    justify-content: left;
  }
</style>
