import {requestProcess} from '../../plugins/request-process'
import {
  selectArticleTypeByIdHome,
  selectArticleTypeListHome,
  selectArticleTypeTreeHome
} from '../../api/home/articleType'
import {
  deleteArticleType, insertArticleType, selectArticleTypeList, selectArticleTypeTree, updateArticleType
} from '../../api/admin/adminArticleType'

const none = () => {}
const articleType = {
  namespaced: true,
  state: {
    blogArticleTypeList: [],
    blogArticleTypeTree: [],
    blogArticleType: [],
    blogArticleMsg: [],
  },
  getters: {
    blogArticleTypeList(state) {return state.blogArticleTypeList},
    blogArticleTypeTree(state) {return state.blogArticleTypeTree},
    blogArticleType(state) {return state.blogArticleType},
    blogArticleMsg(state) {return state.blogArticleMsg},
  },
  mutations: {
    setBlogArticleTypeList(state, data) {state.blogArticleTypeList = data.result},
    setBlogArticleTypeTree(state, data) {state.blogArticleTypeTree = data.result},
    setBlogArticleType(state, data) {state.blogArticleType = data.result},
    setBlogArticleMsg(state, data) {state.blogArticleMsg = data},
  },
  actions: {
    selectArticleTypeByIdHome({commit}, data) {return requestProcess(selectArticleTypeByIdHome, data => commit('setBlogArticleType', data), none, data)},
    selectArticleTypeListHome({commit}, data) {return requestProcess(selectArticleTypeListHome, data => commit('setBlogArticleTypeList', data), none, data)},
    selectArticleTypeTreeHome({commit}, data) {return requestProcess(selectArticleTypeTreeHome, data => commit('setBlogArticleTypeTree', data), none, data)},
    selectArticleTypeList({commit}, data) {return requestProcess(selectArticleTypeList, data => commit('setBlogArticleTypeList', data), none, data)},
    selectArticleTypeTree({commit}, data) {return requestProcess(selectArticleTypeTree, data => commit('setBlogArticleTypeTree', data), none, data)},
    insertArticleType({commit}, data) {return requestProcess(insertArticleType, data => commit('setBlogArticleMsg', data), none, data)},
    deleteArticleType({commit}, data) {return requestProcess(deleteArticleType, data => commit('setBlogArticleMsg', data), none, data)},
    updateArticleType({commit}, data) {return requestProcess(updateArticleType, data => commit('setBlogArticleMsg', data), none, data)},

  }
}

//导出
export default articleType;


// const none = () => {}
// const articleType = {
//   namespaced: true,
//   state: {
//
//   },
//   getters: {
//
//   },
//   mutations: {
//
//   },
//   actions: {
//
//   }
// }
//
// //导出
// export default articleType;
