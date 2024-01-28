import {selectWebSetting, updateValueByKey, selectSettingByKey} from '../../api/admin/adminSetting'
import {requestProcess} from '../../plugins/request-process'

const setting = {
  namespaced: true,
  state: {
    // 登陆页面壁纸
    loginPageWallpaper: {},
    // 注册页面壁纸
    registerPageWallpaper: {},
    // 博客主页壁纸
    homePageWallpaper: {},
    // 博客列表页面壁纸
    blogListPageWallpaper: {},
    // 博客页面壁纸
    blogPageWallpaper: {},
    // 游戏页面壁纸
    gamePageWallpaper: {},
    // 导航页面壁纸
    navPageWallpaper: {},
    // 404页面壁纸
    errorPageWallpaper: {},
    webSetting: [],
    settingMsg: {}
  },
  getters: {
    registerPageWallpaper(state) {return state.registerPageWallpaper},
    loginPageWallpaper(state) {return state.loginPageWallpaper},
    homePageWallpaper(state) {return state.homePageWallpaper},
    blogListPageWallpaper(state) {return state.blogListPageWallpaper},
    blogPageWallpaper(state) {return state.blogPageWallpaper},
    gamePageWallpaper(state) {return state.gamePageWallpaper},
    navPageWallpaper(state) {return state.navPageWallpaper},
    errorPageWallpaper(state) {return state.errorPageWallpaper},

    webSetting(state) {return state.webSetting},
    settingMsg(state) {return state.settingMsg}
  },
  mutations: {
    setLoginPageWallpaper(state, data) {state.loginPageWallpaper = data.result},
    setRegisterPageWallpaper(state, data) {state.registerPageWallpaper = data.result},
    setHomePageWallpaper(state, data) {state.homePageWallpaper = data.result},
    setBlogListPageWallpaper(state, data) {state.blogListPageWallpaper = data.result},
    setBlogPageWallpaper(state, data) {state.blogPageWallpaper = data.result},
    setGamePageWallpaper(state, data) {state.gamePageWallpaper = data.result},
    setNavPageWallpaper(state, data) {state.navPageWallpaper = data.result},
    setErrorPageWallpaper(state, data) {state.errorPageWallpaper = data.result},

    setWebSetting(state, data) {state.webSetting = data.result},
    setSettingMsg(state, data) {state.settingMsg = data},
  },
  actions: {
    selectWebSetting({commit}, data) {return requestProcess(selectWebSetting, data => commit('setWebSetting', data), null, data)},
    updateSetting({commit}, data) {return requestProcess(updateValueByKey, data => commit('setSettingMsg', data), null, data)},

    // 查询壁纸接口
    selectLoginPageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setLoginPageWallpaper', data), null, data)},
    selectRegisterPageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setRegisterPageWallpaper', data), null, data)},
    selectHomePageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setHomePageWallpaper', data), null, data)},
    selectBlogListPageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setBlogListPageWallpaper', data), null, data)},
    selectBlogPageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setBlogPageWallpaper', data), null, data)},
    selectGamePageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setGamePageWallpaper', data), null, data)},
    selectNavPageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setNavPageWallpaper', data), null, data)},
    selectErrorPageWallpaper({commit}, data) {return requestProcess(selectSettingByKey, data => commit('setErrorPageWallpaper', data), null, data)},

  }
}

//导出
export default setting;
