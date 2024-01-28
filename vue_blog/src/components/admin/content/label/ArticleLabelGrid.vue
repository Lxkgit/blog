<template>
  <div>
    <el-card class="box-card">
      <vxe-grid
        resizable
        border
        ref="xTree"
        height="780"
        :tree-config="{transform: true, rowKey: 'id', parentKey: 'parent'}"
        :data="blogArticleTypeTree"
        :pager-config="{pageSize: this.pageSize}"
        :form-config="tableForm"
        :columns="tableColumn"
        :highlight-hover-row="true"
        :toolbar-config="tableToolbar"
        @toolbar-button-click="toolbarClick">
        <!-- 操作模板 -->
        <template v-slot:option="{ row }">
          <vxe-button icon="fa fa-edit" title="编辑" circle @click="updateArticleType(row)"/>
          <!--删除标签按钮 confirm 点击确认按钮激发-->
          <el-popconfirm
            confirm-button-text='确认'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除此标签？"
            @confirm="deleteArticleType(row)">
            <vxe-button slot="reference" icon="fa fa-trash" title="删除" circle/>
          </el-popconfirm>
        </template>
      </vxe-grid>
    </el-card>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    name: 'ArticleLabelGrid',
    data() {
      return {
        pageSize: 10,
        // 搜索表单设置
        tableForm: {
          items: [
            {
              field: 'search_type',
              title: '搜索类型',
              itemRender: {
                name: '$select',
                options: [{label: '标签id', value: 'post_id'}, {label: '标签名字', value: 'title'}]
              }
            },
            {
              field: 'search_key',
              title: '关键词',
              itemRender: {name: '$input', attrs: {placeholder: '请输入标签名字', name: 'sex'}}
            },
            {itemRender: {name: '$button', props: {content: '查询', type: 'submit', status: 'primary'}}},
            {itemRender: {name: '$button', props: {content: '重置', type: 'reset'}}}
          ]
        },
        // 工具栏设置
        tableToolbar: {
          buttons: [
            {code: 'write', name: '新增标签', icon: 'fa fa-plus', status: 'primary'},
            {code: 'delete', name: '删除选中', icon: 'fa fa-trash', status: 'danger'}
          ],
          zoom: true, // 缩放
          custom: true // 自定义显示内容
        },
        // 表格结构
        tableColumn: [
          {type: 'checkbox', width: 50},
          {field: 'value', title: 'id', width: 90, treeNode: true},
          {field: 'label', title: '标签名字'},
          {title: '操作', slots: {default: 'option'}}
        ]
      }
    },
    mounted() {
      this.selectLabelTree()
    },
    computed: {
      ...mapState('articleType',['blogArticleTypeTree'])
    },
    methods: {
      selectLabelTree() {
        this.$store.dispatch('articleType/selectArticleTypeTree')
        console.log(this.blogArticleTypeTree)
      },
      insertArticleType(parent) {
        this.$prompt('请输入分类名', '提示', {
          closeOnClickModal: false, // 点击其他地方不关闭
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({value}) => {
          if (parent === undefined)
            parent = 0
          this.$store.dispatch('articleType/insertArticleType', {parent: parent, label: value}).then(resp => {
            if (resp && resp.code === 200) {
              this.selectLabelTree()
            }
          })
        })
      },
      deleteArticleType(data) {
        this.$store.dispatch('articleType/deleteArticleType', data.value).then(resp => {
          if (resp && resp.code === 200) {
            this.selectLabelTree()
          }
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
      },
      // 工具栏按钮点击事件
      toolbarClick({code, button}) {
        // 根据我们的code来判断不同操作
        switch (code) {
          case 'write':
            this.insertArticleType()
            break
        }
      },
    }
  }
</script>

<style scoped>
  .table-tag {
    margin: 2px 5px;
  }

  .vxe-button + .vxe-button {
    margin-left: 0 !important;
  }
</style>
