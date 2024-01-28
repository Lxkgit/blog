import {requestProcess} from '../../plugins/request-process'
import {
  insertArticleLabel, deleteArticleLabel, updateArticleLabel, selectArticleLabelList, selectArticleLabelById,
  insertArticleLabelType, deleteArticleLabelType, updateArticleLabelType,
  selectArticleLabelTypeList, selectArticleLabelTypeAndLabelList, selectArticleLabelTypeAndLabelById, selectArticleLabelTypeById,
  selectArticleLabelByLabelTypeId
} from '../../api/admin/adminArticleLabel'

const none = () => {}
const articleLabel = {
  namespaced: true,
  state: {
    // 标签
    blogArticleLabel: {},
    blogArticleLabelList: [],
    blogArticleLabelMsg: {},
    // 标签和标签分组
    blogArticleLabelType: {},
    blogArticleLabelTypeList: [],
    blogArticleLabelTypeMsg: {},
  },
  getters: {
    // 标签
    blogArticleLabel(state) {return state.blogArticleLabel},
    blogArticleLabelList(state) {return state.blogArticleLabelList},
    blogArticleLabelMsg(state) {return state.blogArticleLabelMsg},
    // 标签和标签分组
    blogArticleLabelType(state) {return state.blogArticleLabelType},
    blogArticleLabelTypeList(state) {return state.blogArticleLabelTypeList},
    blogArticleLabelTypeMsg(state) {return state.blogArticleLabelTypeMsg},
  },
  mutations: {
    // 标签
    setBlogArticleLabel(state, data) {state.blogArticleLabel = data.result},
    setBlogArticleLabelList(state, data) {state.blogArticleLabelList = data.result},
    setBlogArticleLabelMsg(state, data) {state.blogArticleLabelMsg = data},
    // 标签和标签分组
    setBlogArticleLabelType(state, data) {state.blogArticleLabelType = data.result},
    setBlogArticleLabelTypeList(state, data) {state.blogArticleLabelTypeList = data.result},
    setBlogArticleLabelTypeMsg(state, data) {state.blogArticleLabelTypeMsg = data}
  },
  actions: {
    // 标签
    selectArticleLabelById({commit}, data) {return requestProcess(selectArticleLabelById, data => commit('setBlogArticleLabel', data), none, data)},
    selectArticleLabelList({commit}, data) {return requestProcess(selectArticleLabelList, data => commit('setBlogArticleLabelList', data), none, data)},
    selectArticleLabelByLabelTypeId({commit}, data) {return requestProcess(selectArticleLabelByLabelTypeId, data => commit('setBlogArticleLabelList', data), none, data)},
    updateArticleLabel({commit}, data) {return requestProcess(updateArticleLabel, data => commit('setBlogArticleLabelMsg', data), none, data)},
    insertArticleLabel({commit}, data) {return requestProcess(insertArticleLabel, data => commit('setBlogArticleLabelMsg', data), none, data)},
    deleteArticleLabel({commit}, data) {return requestProcess(deleteArticleLabel, data => commit('setBlogArticleLabelMsg', data), none, data)},
    // 标签和标签分组
    selectArticleLabelTypeById({commit}, data){return requestProcess(selectArticleLabelTypeById, data => commit('setBlogArticleLabelType', data), none, data)},
    selectArticleLabelTypeAndLabelById({commit}, data){return requestProcess(selectArticleLabelTypeAndLabelById, data => commit('setBlogArticleLabelType', data), none, data)},
    selectArticleLabelTypeList({commit}, data){return requestProcess(selectArticleLabelTypeList, data => commit('setBlogArticleLabelList', data), none, data)},
    selectArticleLabelTypeAndLabelList({commit}, data){return requestProcess(selectArticleLabelTypeAndLabelList, data => commit('setBlogArticleLabelTypeList', data), none, data)},
    insertArticleLabelType({commit}, data){return requestProcess(insertArticleLabelType, data => commit('setBlogArticleLabelTypeMsg', data), none, data)},
    deleteArticleLabelType({commit}, data){return requestProcess(deleteArticleLabelType, data => commit('setBlogArticleLabelTypeMsg', data), none, data)},
    updateArticleLabelType({commit}, data){return requestProcess(updateArticleLabelType, data => commit('setBlogArticleLabelTypeMsg', data), none, data)}
  }
}

//导出
export default articleLabel;
