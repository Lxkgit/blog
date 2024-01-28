import {requestProcess} from '../../plugins/request-process'
import {uploadImg} from '../../api/admin/adminUpload'

const none = () => {}
const upload = {
  namespaced: true,
  state: {
    uploadMsg: {},
  },
  getters: {
    uploadImg(state) {return state.uploadMsg},
  },
  mutations: {
    uploadImg(state, data) {state.uploadMsg = data},
  },
  actions: {
    uploadImg({commit}, data) {return requestProcess(uploadImg, data => commit('uploadImg', data), null, data)},
  }
}

//导出
export default upload;
