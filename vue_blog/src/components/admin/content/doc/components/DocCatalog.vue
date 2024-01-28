<template>
  <div>
    <el-card class="option-card" shadow="hover">
      <div slot="header" style="background: #996600;" class="card-head">
        <font-awesome-icon icon="list"/>
        <span>文档列表</span>
      </div>
      <el-tree
        ref="tree"
        :data="docCatalogList"
        :props="defaultProps"
        draggable
        :highlight-current="true"
        :expand-on-click-node="false"
        @node-contextmenu="nodeRightClick"
        @node-click="click"
        @node-drop="nodeDrop"
      />
      <div class="add_chapter">
        <el-button plain @click="insertDoc(0)">
          新增文档
        </el-button>
      </div>
    </el-card>
    <div v-show="visible" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
      <div @click="insertDoc(clickRightItem.value, clickRightItem)">
        新建子文档
      </div>
      <div @click="updateDocCatalog(clickRightItem)">
        编辑
      </div>
      <div @click="deleteDoc(clickRightItem, clickRightNode)">
        删除
      </div>
    </div>
  </div>
</template>

<script>

  import {mapGetters, mapState} from 'vuex'

  export default {
    name: "DocCatalog",
    data() {
      return {
        left: 0, // 右键菜单的左边距
        top: 0, // 右键菜单的上边距
        visible: false, // 右键菜单是否可见
        clickRightItem: {}, // 右键选中的对象
        clickRightNode: {}, // 右键选中对象的node信息
        defaultProps: {
          children: 'children',
          label: 'title'
        }
      }
    },
    computed: {
      ...mapGetters('doc',['docCatalogList']),
    },
    watch: {
      visible(value) { // 监听右键菜单显示变化
        if (value) {
          document.body.addEventListener('click', this.closeMenu)
        } else {
          document.body.removeEventListener('click', this.closeMenu)
        }
      }
    },
    mounted() {
      this.selectDocCatalog()
    },
    methods: {
      selectDocCatalog() {
        this.$store.dispatch('doc/selectDocList')
      },
      click(data, node, self) {
        this.$store.dispatch('doc/selectDocContentById', data.value)
      },
      nodeRightClick(event, data, node, self) { // 节点点击事件
        this.clickRightItem = data
        this.clickRightNode = node
        // 设置右键菜单位置
        this.left = event.clientX
        this.top = event.clientY
        this.visible = true
      },
      closeMenu() { // 关闭右键菜单
        this.visible = false
      },
      insertDoc(parent) {
        this.$prompt('请输入文档名', '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({value}) => {
          if (parent === undefined)
            parent = 0
          this.$store.dispatch('doc/insertDoc', {parent: parent, title: value}).then(resp => {
            if (resp && resp.code === 200) {
              this.selectDocCatalog()
            }
          })
        })
      },
      updateDocCatalog(data) {
        this.$prompt('请输入新的文档名', '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValue: data.label
        }).then(({value}) => {
          this.$store.dispatch('doc/updateDocCatalog', {
            value: data.value,
            title: value,
            parent: data.parent
          }).then(resp => {
            if (resp && resp.code === 200) {
              this.selectDocCatalog()
            }
          })
        })
      },
      deleteDoc(data, node) {
        this.$confirm('确认删除该文档?' + data.value, '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('doc/deleteDoc', data.value).then(resp => {
            if (resp && resp.code === 200) {
              this.selectDocCatalog()
            }
          })
        }).catch(() => {
        })
      },
      nodeDrop(node, node2, position) { // 节点拖拽事件
        const id = node.data.value // 获取当前节点id
        let parent = node2.data.value // 获取拖动到的父节点的id
        // 发送请求更改父节点
        this.$store.dispatch('doc/updateDocCatalog', {value: id, parent: parent}).then(resp => {
            if (resp && resp.code === 200) {
              this.selectDocCatalog()
            }
          }
        )
      },
    }
  }
</script>

<style>
  /*设置card的边距为0，同时设置分割线的间距*/
  .option-card .el-card__header {
    padding: 0 !important;
  }

  .option-card .el-divider--horizontal {
    margin: 10px 0 !important;
  }
</style>

<style scoped>
  .card-head {
    padding: 18px 20px;
    color: #ffffff;
    font-size: 16px;
  }

  .option-card {
    margin-bottom: 10px;
  }

  /*添加章节按钮*/
  .add_chapter {
    margin-top: 10px;
    width: 100%;
    display: flex;
    justify-content: center;
  }

  /*多选框样式*/
  .contextmenu {
    position: fixed;
    min-width: min-content;
    z-index: 1900;
    border: 1px solid #d4d4d5;
    line-height: 1.4285em;
    max-width: 150px;
    background: #fff;
    font-weight: 400;
    font-style: normal;
    color: rgba(0, 0, 0, .87);
    border-radius: .28571429rem;
    box-shadow: 0 2px 4px 0 rgba(34, 36, 38, .12), 0 2px 10px 0 rgba(34, 36, 38, .15);
  }

  .contextmenu div {
    position: relative;
    vertical-align: middle;
    line-height: 1;
    -webkit-tap-highlight-color: transparent;
    padding: 10px 15px;
    color: rgba(0, 0, 0, .87);
    font-size: 14px;
    cursor: pointer;
  }

  .contextmenu div:hover {
    background: #eee;
  }
</style>
