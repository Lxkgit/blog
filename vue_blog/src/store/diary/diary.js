import {requestProcess} from '../../plugins/request-process'
import {deleteDiary, insertDiary, selectDiaryDetail, selectDiaryList, updateDiary} from "../../api/admin/adminDiary";

const none = () => {}
const diary = {
  namespaced: true,
  state: {
    diaryList: {},
    diary: {},
    diaryMsg: {},
  },
  getters: {
    diaryList(state) {return state.diaryList},
    diary(state) {return state.diary},
    diaryMsg(state) {return state.diaryMsg},
  },
  mutations: {
    setDiaryList(state, data) {state.diaryList = data.result},
    setDiaryDetail(state, data) {state.diary = data.result},
    setDiaryMsg(state, data) {state.diaryMsg = data},
  },
  actions: {
    selectDiaryList({commit}, data) {return requestProcess(selectDiaryList, data => commit('setDiaryList', data), none, data)},
    selectDiaryDetail({commit}, data) {return requestProcess(selectDiaryDetail, data => commit('setDiaryDetail', data), none, data)},
    insertDiary({commit}, data) {return requestProcess(insertDiary, data => commit('setDiaryMsg', data), none, data)},
    updateDiary({commit}, data) {return requestProcess(updateDiary, data => commit('setDiaryMsg', data), none, data)},
    deleteDiary({commit}, data) {return requestProcess(deleteDiary, data => commit('setDiaryMsg', data), none, data)},

  }
}

//导出
export default diary;
