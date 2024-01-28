<template>
  <div>
    <el-card class="option-card" shadow="hover">
      <div slot="header" style="background: #996600;" class="card-head">
        <font-awesome-icon icon="list"/>
        <span>文章分类</span>
      </div>
      <el-tree
        ref="tree"
        :data="blogArticleTypeTree"
        draggable
        :highlight-current="true"
        :expand-on-click-node="false"
        @node-contextmenu="nodeRightClick"
        @node-drop="nodeDrop"
      />
      <div class="add_chapter">
        <el-button plain @click="insertArticleType(0)">
          新增分类
        </el-button>
      </div>
    </el-card>
    <div v-show="visible" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
      <div @click="insertArticleType(clickRightItem.value, clickRightItem)">
        新建子分类
      </div>
      <div @click="updateArticleType(clickRightItem)">
        编辑
      </div>
      <div @click="deleteArticleType(clickRightItem, clickRightNode)">
        删除
      </div>
    </div>
  </div>
</template>

<script>

  import {mapState} from 'vuex'

  export default {
    name: 'ArticleLabelTree',
    data() {
      return {
        left: 0, // 右键菜单的左边距
        top: 0, // 右键菜单的上边距
        visible: false, // 右键菜单是否可见
        clickRightItem: {}, // 右键选中的对象
        clickRightNode: {} // 右键选中对象的node信息
      }
    },
    computed: {
      ...mapState('articleType',['blogArticleTypeTree'])
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
      this.selectLabelTree()
    },
    methods: {
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
      selectLabelList() {
        this.$store.dispatch('articleType/selectArticleTypeList')
      },
      selectLabelTree() {
        this.$store.dispatch('articleType/selectArticleTypeTree').then(resp => {
          if (resp && resp.code === 200) {
            this.selectLabelList()
          }
        })
      },
      nodeDrop(node, node2, position) { // 节点拖拽事件
        const id = node.data.value // 获取当前节点id
        let parent = node2.data.value // 获取拖动到的父节点的id
        // 发送请求更改父节点
        if (node2.data.parent !== 0) {
          this.$message({
            type: 'error',
            message: '最多支持二级目录'
          })
          this.selectLabelTree()
        } else {
          this.$store.dispatch('articleType/updateArticleType', {value: id, parent: parent}).then(resp => {
              if (resp && resp.code === 200) {
                this.selectLabelTree()
              }
            }
          )
        }
      },
      insertArticleType(parent) {
        this.$prompt('请输入分类名', '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({value}) => {
          if (parent === undefined)
            parent = 0
          console.log(value)
          this.$store.dispatch('articleType/insertArticleType', {parent: parent, label: value}).then(resp => {
            if (resp && resp.code === 200) {
              this.selectLabelTree()
            }
          })
        })
      },
      deleteArticleType(data, node) {
        this.$confirm('删除该分类(该分类下所有子分类都会消失)?', '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('articleType/deleteArticleType', data.value).then(resp => {
            if (resp && resp.code === 200) {
              this.selectLabelTree()
            }
          })
        }).catch(() => {
        })
      },
      updateArticleType(data) {
        this.$prompt('新标题', '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValue: data.label
        }).then(({value}) => {
          this.$store.dispatch('articleType/updateArticleType', {
            value: data.value,
            label: value,
            parent: data.parent
          }).then(resp => {
            if (resp && resp.code === 200) {
              this.selectLabelTree()
            }
          })
        })
      }
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
