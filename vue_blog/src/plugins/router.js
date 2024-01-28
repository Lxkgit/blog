// import Vue from "vue";
// import router from "../router";
// import store from "../store";
//
//
// var axios = require('axios')
// axios.defaults.baseURL = 'http://localhost:8095/'
// // axios.defaults.baseURL = 'http://1.116.112.250:8095/blog-0.0.1-SNAPSHOT/'
// // 使请求带上凭证信息
// axios.defaults.withCredentials = true
//
// Vue.prototype.$axios = axios
// Vue.config.productionTip = false
//
// router.beforeEach(
//   (to, from, next) => {
//     if (store.state.username && to.path.startsWith('/admin')) {
//       initAdminMenu(router, store)
//     }
//     if (store.state.username && to.path.startsWith('/login')) {
//       next({
//         name: 'Index'
//       })
//     }
//     // 如果前端没有登录信息则直接拦截，如果有则判断后端是否正常登录（防止构造参数绕过）
//     if (to.meta.requireAuth) {
//       if (store.state.username) {
//         axios.get('/user/authentication').then(resp => {
//           if (resp) {
//             next()
//           }
//         })
//       } else {
//         next({
//           path: 'login',
//           query: {redirect: to.fullPath}
//         })
//       }
//     } else {
//       next()
//     }
//   }
// )
//
// // http response 拦截器
// axios.interceptors.response.use(
//   response => {
//     return response
//   },
//   error => {
//     if (error) {
//       store.commit('logout')
//       router.replace('/login')
//     }
//     // 返回接口返回的错误信息
//     return Promise.reject(error)
//   })
//
// const initAdminMenu = (router, store) => {
//   // 防止重复触发加载菜单操作
//   if (store.state.adminMenus.length > 0) {
//     return
//   }
//   axios.get('/admin/menu').then(resp => {
//     if (resp && resp.status === 200) {
//       var fmtRoutes = formatRoutes(resp.data.result)
//       router.addRoutes(fmtRoutes)
//       store.commit('initAdminMenu', fmtRoutes)
//     }
//   })
// }
//
// const formatRoutes = (routes) => {
//   let fmtRoutes = []
//   routes.forEach(route => {
//     if (route.children) {
//       route.children = formatRoutes(route.children)
//     }
//
//     let fmtRoute = {
//       path: route.path,
//       component: resolve => {
//         require(['./components/admin/' + route.component + '.vue'], resolve)
//       },
//       name: route.name,
//       nameZh: route.nameZh,
//       iconCls: route.iconCls,
//       meta: {
//         requireAuth: true
//       },
//       children: route.children
//     }
//     fmtRoutes.push(fmtRoute)
//   })
//   return fmtRoutes
// }
