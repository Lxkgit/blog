<template>
  <div class="nav" :style="`background-image: url(${registerPageWallpaper.value});`">
    <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <img src="https://img-blog.csdnimg.cn/20191224222225841.png" alt="logo" class="logo"/>
      <div style="text-align: center;margin-bottom: 5px;">我的个人小站</div>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="name">
        <el-input type="text" v-model="loginForm.name"
                  auto-complete="off" placeholder="昵称"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item prop="password2">
        <el-input type="password" v-model="loginForm.password2"
                  auto-complete="off" placeholder="重复密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="text" v-model="loginForm.email"
                  auto-complete="off" placeholder="E-Mail"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'Register',
  data () {
    return {
      rules: {
        username: [{required: true, message: '账号不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        name: '',
        password: '',
        password2: '',
        email: ''
      },
      loading: false
    }
  },
  computed: {
    ...mapGetters('setting', ['registerPageWallpaper']),
  },
  mounted() {
    this.registerWallpaper()
  },
  methods: {
    registerWallpaper () {
      this.$store.dispatch('setting/selectRegisterPageWallpaper', 'register_page')
    },
    register () {

    }
  }
}
</script>

<style scoped>
  .nav {
    /*background: url("../../assets/register.jpg") no-repeat right bottom;*/
    background-size: cover;
    position: fixed;
    z-index: -1;
    width: 100%;
    height: 100%;
    background-position-x: 50%;
    background-position-y: 0;
    opacity: 1;
  }
  body{
    margin: -5px 0;
  }
  .logo {
    /*background: url("../../assets/logo.png");*/
    width: 80px;
    height: 80px;
    background-size:cover;
    text-align:center;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
</style>
