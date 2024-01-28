import {requestProcess} from '../../plugins/request-process'
import {selectUserList, updateUserMessage, updateUserPassword, updateUserStatus} from '../../api/admin/adminUser'


const none = () => {}
const user = {
  namespaced: true,
  state: {
    userList: [],
    userMsg: {},
  },
  getters: {
    userList(state) {return state.userList},
    userMsg(state) {return state.userMsg},
  },
  mutations: {
    setUserList(state, data) {state.userList = data.result},
    setUserMsg(state, data) {state.userMsg = data},
  },
  actions: {
    selectUserList({commit}, data) {return requestProcess(selectUserList, data => commit('setUserList', data), none, data)},
    updateUserStatus({commit}, data) {return requestProcess(updateUserStatus, data => commit('setUserMsg', data), none, data)},
    updateUserMessage({commit}, data) {return requestProcess(updateUserMessage, data => commit('setUserMsg', data), none, data)},
    updateUserPassword({commit}, data) {return requestProcess(updateUserPassword, data => commit('setUserMsg', data), none, data)},
  }
}

//导出
export default user;
