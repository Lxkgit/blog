


const tab = {
  namespaced: true,
  state: {
    // 侧边栏状态(false 为开启 true为关闭)
    sideStatus: false,
    // tab 标签
    tags: [{ // 当前任务栏所有的任务
      active: true, // 是否激活
      title: '主页', // 任务栏标题
      close: false, // 是否可以关闭
      path: '/admin/index' // 当前标签的路径
    }],
  },
  getters: {
    sideStatus (state, data) { return state.sideStatus },
    tags (state, data) { return state.tags }
  },
  mutations: {
    changeSideStatue (state) { state.sideStatus = !state.sideStatus },
    // 添加新的标签
    addNewTag (state, data) {
      // 查找标签是否存在
      if (state.tags.find(item => item.path === data.path) === undefined) {
        // 所有的标签全部为未激活状态
        state.tags.map(item => (item.active = false))
        // 数组添加新的标签
        state.tags.push(data)
      }
    },
    // 更据path来激活对应的标签
    changeTag (state, path) {
      // map遍历数组，更据路径来切换激活状态
      // 这里加了一个=>(),()表示函数体返回对象字面变量
      state.tags.map(item => (item.active = path === item.path))
    },
    // 删除某一个标签
    delTag (state, index) { state.tags.splice(index, 1) },
    // 这里我们利用数组的过滤来删除我们不需要的
    closeOther (state, index) {
      state.tags = state.tags.filter((value, index1, array) => (index1 === index || !value.close))
      // 设置最后一个标签状态为激活
      state.tags[state.tags.length - 1].active = true
    },
    closeAll (state) {
      state.tags = state.tags.filter((value, index1, array) => (!value.close))
      state.tags[0].active = true
    },
    // 保存当前的tag
    saveStatus (state) {
      sessionStorage.setItem('tags', JSON.stringify(state.tags))
    },
    // 恢复当前的tag
    restore (state) {
      if (JSON.parse(sessionStorage.getItem('tags')) != null) {
        state.tags = JSON.parse(sessionStorage.getItem('tags'))
      }
      // state.tags = sessionStorage.getItem('tags') == null ? '' : JSON.parse(sessionStorage.getItem('tags'))
    }
  },
  actions: {
    changeSideBar ({ commit }) { commit('changeSideStatue') },
    addTag ({ commit }, data) {commit('addNewTag', { active: true, title: data.name, close: true, path: data.path }) },
    changeTag ({ commit }, data) { commit('changeTag', data) },
    // 关闭标签
    closeTag ({ commit, state }, data) {
      return new Promise((resolve, reject) => {
        // 为了避免commit里面修改数据的种种问题，所以直接在action中操作
        // 这里是因为不能在action中操作数据，所以我们使用副本
        const tags = state.tags.slice()
        if (tags[data].active) {
          // 判断当前下标是否为最后一个
          let index = tags.length - 1
          if (data === tags.length - 1) {
            index = tags.length - 2
          }
          resolve(tags[index].path)
        } else {
          reject(data)
        }
        commit('delTag', data)
      })
    },
    // 关闭其他标签
    closeOther ({ commit }, data) { commit('closeOther', data) },
    // // 关闭所有标签
    closeAll ({ commit }) { commit('closeAll') },
    // 保存当前tag
    saveStatus ({ commit }) { commit('saveStatus') },
    // 保存所有tag
    restoreStatus ({ commit }) { commit('restore') }
  }
}

//导出
export default tab;
