import {requestProcess} from '../../plugins/request-process'
import {login, logout, auth} from '../../api/admin/login'

const none = () => {}
const vuexLogin = {
  namespaced: true,
  state: {
    loginMsg: {},
    user: {},
    // username: window.localStorage.getItem('username') == null ? '' : JSON.parse(window.localStorage.getItem('username' || '[]')),
    adminMenus: [],
    auth: {}
  },
  getters: {
    loginMsg(state) {return state.loginMsg},
    user(state) {
      state.user = JSON.parse(sessionStorage.getItem('user'))
      return sessionStorage.getItem('user') == null ? '' : JSON.parse(sessionStorage.getItem('user'))
    },
    adminMenus(state) {
      state.adminMenus = localStorage.getItem('adminMenus') == null ? '' : JSON.parse(localStorage.getItem('adminMenus'))
      return state.adminMenus
    },
    auth(state) {
      return state.auth
    }
  },
  mutations: {
    initAdminMenu (state, menus) {
      state.adminMenus = menus
      localStorage.setItem('adminMenus', JSON.stringify(menus))
    },
    // 登陆
    login (state, data) {
      state.user = data.result
      sessionStorage.setItem('user', JSON.stringify(data.result))
      // window.localStorage.setItem('username', JSON.stringify(data))
    },
    loginMsg(state, data) {
      state.loginMsg = data
    },
    // 登出
    logout (state) {
      // 注意不能用 null 清除，否则将无法判断 user 里具体的内容
      state.user = ''
      sessionStorage.removeItem('user')
      localStorage.removeItem('adminMenus')
      // window.localStorage.removeItem('username')
      state.adminMenus = []
    },
    auth(state, data) {
      state.auth = data
    }
  },
  actions: {
    login({commit}, data) {return requestProcess(login, data => commit('login', data), none, data)},
    logout({commit}, data) {return requestProcess(logout, data => commit('loginMsg', data), none, data)},
    auth({commit}, data) {return requestProcess(auth, data => commit('auth', data), none, data)}
  }
}

//导出
export default vuexLogin;
