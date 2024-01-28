import {requestProcess} from '../../plugins/request-process'
import {
  insertRole,
  selectPermissionList,
  selectPermsAndMenus, updateRoleMenu,
  updateRolePermission,
  updateRoleStatus
} from '../../api/admin/adminRole'


const none = () => {}
const role = {
  namespaced: true,
  state: {
    roleList: [],
    permList: [],
    menuList: [],
    roleMsg: {},
    permMsg: {},
    menuMsg: {},
  },
  getters: {
    roleList(state) {return state.roleList},
    roleMsg(state) {return state.roleMsg},
    permList(state) {return state.permList},
    permMsg(state) {return state.permMsg},
    menuList(state) {return state.menuList},
    menuMsg(state) {return state.menuMsg},
  },
  mutations: {
    setRoleList(state, data) {state.roleList = data.result},
    setRoleMsg(state, data) {state.roleMsg = data},
    setPermList(state, data) {state.permList = data.result},
    setPermMsg(state, data) {state.permMsg = data},
    setMenuList(state, data) {state.menuList = data.result},
    setMenuMsg(state, data) {state.menuMsg = data},
  },
  actions: {
    selectRoleList({commit}, data) {return requestProcess(selectPermsAndMenus, data => commit('setRoleList', data), none, data)},
    selectPermissionList({commit}, data) {return requestProcess(selectPermissionList, data => commit('setPermList', data), none, data)},
    insertRole({commit}, data) {return requestProcess(insertRole, data => commit('setRoleMsg', data), none, data)},
    updateRoleStatus({commit}, data) {return requestProcess(updateRoleStatus, data => commit('setRoleMsg', data), none, data)},
    updateRolePermission({commit}, data) {return requestProcess(updateRolePermission, data => commit('setRoleMsg', data),none, data)},
    updateRoleMenu({commit}, data) {return requestProcess(updateRoleMenu, data => commit('setMenuMsg', data), none, data)},

  }
}

//导出
export default role;
