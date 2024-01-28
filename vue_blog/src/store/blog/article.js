import {requestProcess} from '../../plugins/request-process'
import {selectBlogList, selectBlogDetail} from '../../api/home/blog'
import {selectBlogListAdmin, selectBlogDetailAdmin, deleteBlog, insertBlog, updateBlog} from '../../api/admin/adminBlog'

const none = () => {}
const article = {
  namespaced: true,
  state: {
    blogList: [],
    blog: {},
    blogMsg: {},
  },
  getters: {
    blogList(state) {return state.blogList},
    blogDetail(state) {return state.blog},
    blogMsg(state) {return state.blogMsg},
  },
  mutations: {
    setBlogList (state,data) {state.blogList = data.result},
    setBlogDetail (state, data) {state.blog = data.result},
    setBlogMsg(state, data) {state.blogMsg = data},
  },
  actions: {
    getBlogList({commit}, data) {return requestProcess(selectBlogList, data => commit('setBlogList', data), none, data)},
    getBlogDetail({commit}, data) {return requestProcess(selectBlogDetail, data => commit('setBlogDetail', data), none, data)},
    getBlogListAdmin({commit}, data) {return requestProcess(selectBlogListAdmin, data => commit('setBlogList', data), none, data)},
    getBlogDetailAdmin({commit}, data) {return requestProcess(selectBlogDetailAdmin, data => commit('setBlogDetail', data), none, data)},
    insertBlog({commit}, data) {return requestProcess(insertBlog, data => commit('setBlogDetail', data), none, data)},
    updateBlog({commit}, data) {return requestProcess(updateBlog, data => commit('setBlogMsg', data), none, data)},
    deleteBlog({commit}, data) {return requestProcess(deleteBlog, data => commit('setBlogMsg', data), none, data)},

  }
}

//导出
export default article;
