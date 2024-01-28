import {requestProcess} from '../../plugins/request-process'
import {selectAllMenu, selectCurrentMenu} from '../../api/admin/adminMenu'


const none = () => {}
const menu = {
  namespaced: true,
  state: {
    menu: {},
    menuList: []
  },
  getters: {
    menu(state) {return state.menu},
  },
  mutations: {
    setMenuList(state, data) {state.menuList = data.result},
    setMenu(state, data) {state.menu = data.result},
  },
  actions: {
    selectCurrentMenu({commit}, data) {return requestProcess(selectCurrentMenu, data => commit('setMenu', data), none, data)},
    selectAllMenu({commit}, data) {return requestProcess(selectAllMenu, data => commit('setMenuList', data), none, data)},
  }
}

//导出
export default menu;
