<template>
<!--  <div class="sidebar-logo-container" :class="{'collapse':collapse}">-->
  <div class="sidebar-logo-container collapse">
    <transition name="sidebarLogoFade">
      <router-link v-if="collapse" key="collapse" class="sidebar-logo-link" to="/">
        111
<!--        <img v-if="logo" alt :src="logo" class="sidebar-logo">-->
<!--        <h1 v-else class="sidebar-title">-->
<!--          {{ title }}-->
<!--        </h1>-->
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        222
<!--        <img v-if="logo" alt :src="logo" class="sidebar-logo">-->
<!--        <h1 class="sidebar-title">-->
<!--          {{ title }}-->
<!--        </h1>-->
      </router-link>
    </transition>
  </div>
</template>

<script>
// import logo from '@/src/assets/logo.png'
export default {
  name: 'MenuBarLogo',
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data () {
    return {
      title: 'XBlog博客系统'
      // logo
    }
  }
}
</script>

<!--<style lang="scss" scoped>-->
<style scoped>
  .sidebarLogoFade-enter-active {
    transition: opacity 1.5s;
  }

  .sidebarLogoFade-enter,
  .sidebarLogoFade-leave-to {
    opacity: 0;
  }

  .sidebar-logo-container {
    position: relative;
    border-right: solid 1px #e6e6e6;
    border-bottom: solid 1px #e6e6e6;
    width: 100%;
    height: 50px;
    line-height: 50px;
    background: #ffffff;
    text-align: center;
    overflow: hidden;

    & .sidebar-logo-link {
      height: 100%;
      width: 100%;

      & .sidebar-logo {
        width: 32px;
        height: 32px;
        vertical-align: middle;
        margin-right: 12px;
        border-radius: 50%;
      }

      & .sidebar-title {
        display: inline-block;
        margin: 0;
        color: #2b2f3a;
        font-weight: 600;
        line-height: 50px;
        font-size: 14px;
        font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
        vertical-align: middle;
      }
    }

    &.collapse {
      .sidebar-logo {
        margin-right: 0;
        border-radius: 50%;
      }
    }
  }
</style>
