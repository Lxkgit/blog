<template>
  <div>
    <el-card class="box-card">
      <vxe-grid
        ref="grid"
        border
        resizable
        height="806"
        row-id="id"
        :data="blogList"
        :pager-config="tablePage"
        :form-config="tableForm"
        :toolbar-config="tableToolbar"
        :columns="tableColumn"
        @page-change="getBlogList"
        @toolbar-button-click="toolbarClick">
        <!-- 分类模板 -->
        <template v-slot:types="{ row }">
          <el-tag v-for="(item,index) in row.types" :key="index" size="small" class="table-tag">
            {{ item.label }}
          </el-tag>
        </template>
        <!-- 标签模板 -->
        <template v-slot:labels="{ row }">
          <el-tag v-for="(item,index) in row.labels" :key="index" type="success" size="mini" class="table-tag">
            {{ item.labelName }}
          </el-tag>
        </template>
        <!-- 操作模板 -->
        <template v-slot:option="{ row }">
          <vxe-button icon="fa fa-edit" title="编辑" circle @click="updateBlog(row)"/>
<!--          <vxe-button v-if="row.is_draft" icon="fa fa-send" title="发布" circle @click="updatePost(row.id,'false')"/>-->
<!--          <vxe-button v-else icon="fa fa-file-o" title="草稿" circle @click="updatePost(row.id,'true')"/>-->
          <!--删除文章按钮 onConfirm 点击确认按钮激发-->
<!--          <vxe-button icon="fa fa-trash" title="删除" circle @click="deleteBlog(row.id)"/>-->
          <el-popconfirm
            confirm-button-text='确认'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除这篇文章？"
            @confirm="deleteBlog(row.id)">
            <vxe-button slot="reference" icon="fa fa-trash" title="删除" circle/>
          </el-popconfirm>
        </template>
      </vxe-grid>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: 'blogList',
    data() {
      return {
        blogList: [],
        // 多选框选中的值
        selects: '',
        tablePage: {
          currentPage: 1,
          pageSize: 10,
          total: 0
        },
        // 搜索表单设置
        tableForm: {
          items: [
            {
              field: 'search_type',
              title: '搜索类型',
              itemRender: {
                name: '$select',
                options: [{label: '文章id', value: 'post_id'}, {label: '文章标题', value: 'title'}]
              }
            },
            {
              field: 'search_key',
              title: '关键词',
              itemRender: {name: '$input', attrs: {placeholder: '请输入文章标题', name: 'sex'}}
            },
            {itemRender: {name: '$button', props: {content: '查询', type: 'submit', status: 'primary'}}},
            {itemRender: {name: '$button', props: {content: '重置', type: 'reset'}}}
          ]
        },
        // 工具栏设置
        tableToolbar: {
          buttons: [
            {code: 'write', name: '写文章', icon: 'fa fa-plus', status: 'primary'},
            {code: 'publish', name: '发布', icon: 'fa fa-send', status: 'success'},
            {code: 'cancel', name: '取消发布', status: 'warning'},
            {code: 'delete', name: '删除选中', icon: 'fa fa-trash', status: 'danger'}
          ],
          zoom: true, // 缩放
          custom: true // 自定义显示内容
        },
        // 表格结构
        tableColumn: [
          {type: 'checkbox', width: 50},
          {field: 'id', title: '文章id', width: 90},
          {field: 'title', title: '文章标题'},
          {title: '分类', slots: {default: 'types'}},
          {title: '标签', slots: {default: 'labels'}},
          {field: 'browse_count', title: '阅读数', width: 80},
          {field: 'like_count', title: '点赞数', width: 80},
          {field: 'comment', title: '评论数', width: 80},
          {field: 'create_time', title: '发布时间', width: 120},
          {title: '操作', slots: {default: 'option'}, width: 160}
        ]
      }
    },
    mounted() {
      this.getBlogList({ currentPage:1, pageSize:10})
    },
    methods: {
      getBlogList({ currentPage, pageSize }) {
        this.tablePage.currentPage = currentPage
        this.tablePage.pageSize = pageSize
        this.$store.dispatch('article/getBlogListAdmin', {page: this.tablePage.currentPage, size: this.tablePage.pageSize}).then((resp) => {
          this.blogList = resp.result.list
          this.tablePage.total = resp.result.total
        })
      },
      // 工具栏按钮点击事件
      toolbarClick({code, button}) {
        // 根据我们的code来判断不同操作
        switch (code) {
          case 'write':
            this.editBlog()
            break
        }
      },
      editBlog() {
        this.$router.push(
          {
            name: 'Editor'
          }
        )
      },
      updateBlog(article) {
        this.$router.push(
          {
            name: 'Editor',
            params: {
              article: article
            }
          }
        )
      },
      deleteBlog(id) {
        this.$store.dispatch('article/deleteBlog', id).then(resp => {
          if (resp && resp.code === 200){
            this.getBlogList({ currentPage: 1, pageSize: 10 })
          }
        })
      }
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
