<template>
  <div>
    <el-card class="box-card">
      <vxe-grid
        ref="grid"
        border
        resizable
        height="780"
        row-id="id"
        :data="userList"
        :pager-config="{pageSize: this.pageSize}"
        :toolbar-config="tableToolbar"
        :edit-config="editConfig"
        :columns="tableColumn"
        @toolbar-button-click="toolbarClick">
        <!-- 操作模板 -->
        <template v-slot:option="{ row }">
          <!-- 编辑保存按钮 -->
          <vxe-button
            v-if="$refs.grid.isActiveByRow(row)"
            icon="fa fa-save"
            status="primary"
            title="保存"
            circle
            @click="saveRowEvent(row)"
          />
          <vxe-button v-else icon="fa fa-edit" title="编辑" circle @click="editRowEvent(row)" />
          <!--删除按钮 onConfirm 点击确认按钮激发-->
          <el-popconfirm
            confirm-button-text='确认'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除此用户？"
            @confirm="deleteBlog(row.id)">
            <vxe-button slot="reference" icon="fa fa-trash" title="删除" circle/>
          </el-popconfirm>
        </template>
      </vxe-grid>
    </el-card>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

  const userStatus = [{ label: '已激活', value: 1 }, { label: '未激活', value: 0 }]
  export default {
    name: 'UserProfile',
    data() {
      return {
        dialogFormVisible: false,
        selectedUser: [],
        pageSize: 10,
        // 搜索表单设置
        tableForm: {
          items: [
            {
              field: 'search_type',
              title: '搜索类型',
              itemRender: {
                name: '$select',
                options: [
                  {label: '文章id', value: 'post_id'},
                  {label: '文章标题', value: 'title'}
                ]
              }
            },
            {itemRender: {name: '$button', props: {content: '查询', type: 'submit', status: 'primary'}}},
            {itemRender: {name: '$button', props: {content: '重置', type: 'reset'}}}
          ]
        },
        // 工具栏设置
        tableToolbar: {
          buttons: [
            // {code: 'write', name: '创建用户', icon: 'fa fa-plus', status: 'primary'},
            {code: 'delete', name: '删除选中', icon: 'fa fa-trash', status: 'danger'}
          ],
          zoom: true, // 缩放
          custom: true // 自定义显示内容
        },
        // 开启行编辑功能
        editConfig: {
          // 手动触发
          trigger: 'manual',
          // 单元格编辑模式
          mode: 'row',
          // 点击其他地方不清除激活状态
          autoClear: false,
          // 不显示按钮
          icon: 'none'
        },
        // 表格结构
        tableColumn: [
          {type: 'checkbox', width: 50},
          {field: 'id', title: '角色id', width: 90},
          {field: 'username', title: '昵称', editRender: { name: 'input'}},
          {field: 'email', title: '邮箱'},
          {field: 'status', title: '状态', editRender: { name: '$select', options: userStatus }},
          {title: '操作', slots: {default: 'option'}}
        ]
      }
    },
    mounted() {
      this.listUsers()
    },
    computed: {
      ...mapState('user',['userList']),
    },
    methods: {
      listUsers() {
        this.$store.dispatch('user/selectUserList')
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
      // 点击编辑事件
      editRowEvent (row) {
        // 我们激活编辑框
        this.$refs.grid.setActiveRow(row)
      },
      // 点击保存事件
      saveRowEvent (row) {
        // 点击保存的时候自动触发代理的保存事件
        this.$refs.grid.clearActived().then(() => {
          // 设置更新的值
          const data = {
            id: row.id,
            username: row.username,
            status: row.status.toString()
          }
          this.$store.dispatch('user/updateUserMessage', data).then(resp => {
            console.log(resp)
            if (resp && resp.code === 200) {
              this.$message.success(resp.result)
            }
          })
        })
      }
    }
  }
</script>

<style scoped>

</style>
