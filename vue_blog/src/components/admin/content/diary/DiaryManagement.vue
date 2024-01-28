<template>
  <div>
    <el-card class="box-card">
      <vxe-grid
        ref="grid"
        border
        resizable
        height="780"
        row-id="id"
        :data="diaryList"
        :pager-config="{pageSize: this.pageSize}"
        :form-config="tableForm"
        :toolbar-config="tableToolbar"
        :columns="tableColumn"
        @toolbar-button-click="toolbarClick"
      >
        <!-- 操作模板 -->
        <template v-slot:option="{ row }">
          <vxe-button icon="fa fa-edit" title="编辑" circle @click="updateDiary(row)"/>
<!--          <vxe-button v-if="row.is_draft" icon="fa fa-send" title="发布" circle-->
<!--                      @click="updatePost(row.id,'false')"/>-->
          <!--删除文章按钮 onConfirm 点击确认按钮激发-->
          <el-popconfirm
            confirm-button-text='确认'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除这篇日记？"
            @confirm="deleteDiary(row.id)">
            <vxe-button slot="reference" icon="fa fa-trash" title="删除" circle/>
          </el-popconfirm>
        </template>
      </vxe-grid>
    </el-card>
  </div>
</template>

<script>
  export default {
    layout: 'admin',
    data() {
      return {
        diaryList: [],
        pageSize: 10,
        // 多选框选中的值
        selects: '',
        // 搜索表单设置
        tableForm: {
          items: [
            {
              field: 'search_type',
              title: '搜索类型',
              itemRender: {
                name: '$select',
                options: [{label: '日记id', value: 'post_id'}, {label: '日记内容', value: 'content'}]
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
            {code: 'write', name: '写日记', icon: 'fa fa-plus', status: 'primary'},
            // { code: 'publish', name: '发布', icon: 'fa fa-send', status: 'success' },
            // { code: 'cancel', name: '取消发布', status: 'warning' },
            {code: 'delete', name: '删除选中', icon: 'fa fa-trash', status: 'danger'}
          ],
          zoom: true, // 缩放
          custom: true // 自定义显示内容
        },
        // 表格结构
        tableColumn: [
          {type: 'checkbox', width: 50},
          {field: 'id', title: '日记id', width: 90},
          {field: 'create_time', title: '发布时间'},
          {title: '操作', slots: {default: 'option'}}
        ]
      }
    },
    mounted() {
      this.getDiaryList(1)
    },
    methods: {
      getDiaryList(pageNum) {
        this.$store.dispatch('diary/selectDiaryList', {page: pageNum, size: this.pageSize}).then((resp) => {
          if (resp && resp.code === 200) {
            this.diaryList = resp.result.list
          }
        })
      },
      editDiary() {
        this.$router.push(
          {
            name: 'TinyMode'
          }
        )
      },
      updateDiary (diary) {
        this.$router.push(
          {
            name: 'TinyMode',
            params: {
              diary: diary
            }
          }
        )
      },
      deleteDiary (id) {
        this.$store.dispatch('diary/deleteDiary', id).then(resp => {
          if (resp && resp.code === 200) {
            this.getDiaryList(1)
          }
        })
      },
      // 工具栏按钮点击事件
      toolbarClick({code, button}) {
        // 根据我们的code来判断不同操作
        switch (code) {
          case 'write':
            this.editDiary()
            break
        }
      }
    }
  }
</script>

<style scoped>

  .vxe-button + .vxe-button {
    margin-left: 0 !important;
  }
</style>
