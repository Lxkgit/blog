import {requestProcess} from '../../plugins/request-process'
import {selectDocList, selectDocContentById, insertDoc, deleteDoc, updateDocCatalog, updateDocContent} from '../../api/home/doc'

const none = () => {
}
const doc = {
  namespaced: true,
  state: {
    docCatalogList: [],
    docContent: {},
    docMsg: {}
  },
  getters: {
    docCatalogList(state) {return state.docCatalogList},
    docContent(state) {return state.docContent},
    docMsg(state) {return state.docMsg}
  },
  mutations: {
    setDocCatalogList(state, data) {state.docCatalogList = data.result},
    setDocContent(state, data) {state.docContent = data.result},
    setDocMsg(state, data) {state.docMsg = data}
  },
  actions: {
    selectDocList({commit}, data) {return requestProcess(selectDocList, data => commit('setDocCatalogList', data), none, data)},
    selectDocContentById({commit}, data) {return requestProcess(selectDocContentById, data => commit('setDocContent', data), none, data)},
    insertDoc({commit}, data) {return requestProcess(insertDoc, data => commit('setDocMsg', data), none, data)},
    deleteDoc({commit}, data) {return requestProcess(deleteDoc, data => commit('setDocMsg', data), none, data)},
    updateDocCatalog({commit}, data) {return requestProcess(updateDocCatalog, data => commit('setDocMsg', data), none, data)},
    updateDocContent({commit}, data) {return requestProcess(updateDocContent, data => commit('setDocMsg', data), none, data)}
  }
}

//导出
export default doc;
