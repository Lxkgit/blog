import Vue from 'vue'
import Vuex from 'vuex'
import setting from './admin/setting'
import article from './blog/article'
import articleType from './blog/articleType'
import diary from './diary/diary'
import menu from './admin/menu'
import user from './admin/user'
import role from './admin/role'
import vuexLogin from './admin/login'
import upload from './admin/upload'
import tab from './admin/tab'
import doc from './doc/doc'
import articleLabel from "./blog/articleLabel";

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    setting,
    article,
    articleType,
    diary,
    menu,
    user,
    role,
    vuexLogin,
    upload,
    tab,
    doc,
    articleLabel
  }
})
