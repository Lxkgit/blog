import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index'

import './plugins/fontAwesome'
import './plugins/elementUI'
import './plugins/editor'
import './plugins/vxeTable'
import './plugins/echarts'

var axios = require('axios')

// 使请求带上凭证信息
axios.defaults.withCredentials = true

router.beforeEach(
  (to, from, next) => {
    if (store.state.vuexLogin.user && to.path.startsWith('/admin')) {
      initAdminMenu(router, store)
    }
    if (store.state.vuexLogin.user && to.path.startsWith('/login')) {
      next({
        name: 'Index'
      })
    }
    // 如果前端没有登录信息则直接拦截，如果有则判断后端是否正常登录（防止构造参数绕过）
    if (to.meta.requireAuth) {
      if (store.state.vuexLogin.user) {
        store.dispatch('vuexLogin/auth').then(resp => {
            if (resp) {
              next()
            }
          }
        )
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)

// http response 拦截器
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error) {
      store.commit('vuexLogin/logout')
      router.replace('/login')
    }
    // 返回接口返回的错误信息
    return Promise.reject(error)
  })

const initAdminMenu = (router, store) => {
  // 防止重复触发加载菜单操作
  if (store.state.vuexLogin.adminMenus.length > 0) {
    return
  }
  store.dispatch('menu/selectCurrentMenu').then(resp => {
    if (resp && resp.code === 200) {
      var fmtRoutes = formatRoutes(resp.result)
      router.addRoutes(fmtRoutes)
      store.commit('vuexLogin/initAdminMenu', fmtRoutes)
    }
  })
}

const formatRoutes = (routes) => {
  let fmtRoutes = []
  routes.forEach(route => {
    if (route.children) {
      route.children = formatRoutes(route.children)
    }
    let fmtRoute = {
      path: route.path,
      component: resolve => {
        require(['./components/admin/' + route.component + '.vue'], resolve)
      },
      name: route.name,
      nameZh: route.nameZh,
      iconCls: route.iconCls,
      meta: {
        requireAuth: true
      },
      children: route.children
    }
    fmtRoutes.push(fmtRoute)
  })
  return fmtRoutes
}

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
