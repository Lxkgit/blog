<template>
    <div class="error-page" >
<!--    <div class="error-page" :style="`background-image: url(${homePageWallpaper.value});`">-->
      <div style="padding:100px 500px 0px 0px">
<!--        <img src="../../assets/logo.png" class="error-tag">-->
      </div>
    </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
  name: 'Error404',
  data () {
    return {}
  },
  computed: {
    ...mapGetters('setting', ['errorPageWallpaper'])
  },
  mounted() {
    this.backgroundImg()
  },
  methods: {
    backgroundImg() {
      this.$store.dispatch('setting/selectErrorPageWallpaper', 'error_page')
    },
  }
}
</script>

<style scoped>
  .error-page {
    background: url("../../assets/preview.jpg") no-repeat center;
    margin: 0;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
</style>
