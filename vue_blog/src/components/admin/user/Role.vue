<template>
  <div>
    <el-dialog
      title="修改角色信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedRole" style="text-align: left" ref="dataForm">
        <el-form-item label="角色名" label-width="120px" prop="username">
          <el-input v-model="selectedRole.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" label-width="120px" prop="name">
          <el-input v-model="selectedRole.nameZh" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="功能配置" label-width="120px" prop="perms">
          <el-checkbox-group v-model="selectedPermsIds">
            <el-checkbox v-for="(perm,i) in permList" :key="i" :label="perm.id">{{perm.desc_}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="菜单配置" label-width="120px" prop="menus">
          <el-tree
            :data="menuList"
            :props="props"
            show-checkbox
            :default-checked-keys="selectedMenusIds"
            node-key="id"
            ref="tree">
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedRole)">确 定</el-button>
      </div>
    </el-dialog>
    <el-card class="box-card">
      <vxe-grid
        ref="grid"
        border
        resizable
        height="780"
        row-id="id"
        :data="roleList"
        :pager-config="{pageSize: this.pageSize}"
        :toolbar-config="tableToolbar"
        :columns="tableColumn"
        @toolbar-button-click="toolbarClick">

        <template v-slot:status="{ row }">
          <vxe-switch v-model="row.status" value="number" open-label="开" :open-value="1" close-label="关" :close-value="0" @change="updateStatus(row)"/>
        </template>

        <!-- 操作模板 -->
        <template v-slot:option="{ row }">
          <vxe-button icon="fa fa-edit" title="编辑" circle @click="editRole(row)"/>
          <el-popconfirm
            confirm-button-text='确认'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除此角色？"
            @confirm="deleteRole(row.id)">
            <vxe-button slot="reference" icon="fa fa-trash" title="删除" circle/>
          </el-popconfirm>
        </template>
      </vxe-grid>
    </el-card>
  </div>
</template>

<script>
  import RoleCreate from './RoleCreate'
  import {mapState} from 'vuex'

  export default {
    name: 'UserRole',
    components: {RoleCreate},
    data() {
      return {
        dialogFormVisible: false,
        pageSize: 10,
        selectedRole: [],
        selectedPermsIds: [],
        selectedMenusIds: [],
        props: {
          id: 'id',
          label: 'nameZh',
          children: 'children'
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
            {code: 'write', name: '创建角色', icon: 'fa fa-plus', status: 'primary'},
            {code: 'delete', name: '删除选中', icon: 'fa fa-trash', status: 'danger'}
          ],
          zoom: true, // 缩放
          custom: true // 自定义显示内容
        },
        // 表格结构
        tableColumn: [
          {type: 'checkbox', width: 50},
          {field: 'id', title: '角色id', width: 90},
          {field: 'name', title: '角色名'},
          {field: 'nameZh', title: '角色描述'},
          {field: 'status', title: '状态',  slots: {default: 'status'}},
          {title: '操作', slots: {default: 'option'}}
        ]
      }
    },
    mounted() {
      this.listRoles()
      this.listPerms()
      this.listMenus()
    },
    computed: {
      ...mapState('role',['roleList']),
      ...mapState('role',['permList']),
      ...mapState('menu',['menuList'])
    },
    methods: {
      listRoles() {
        this.$store.dispatch('role/selectRoleList')
      },
      listPerms() {
        this.$store.dispatch('role/selectPermissionList')
      },
      listMenus() {
        this.$store.dispatch('menu/selectAllMenu')
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
      updateStatus(role) {
        if (role.id !== 1) {
          this.$confirm('是否更改角色状态？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            const rolePost = {
              id: role.id,
              name: role.name,
              nameZh: role.nameZh,
              status: role.status
            }
            this.$store.dispatch('role/updateRoleStatus', rolePost).then(resp => {
              if (resp && resp.code === 200) {
                if (role.status === 1) {
                  this.$message('角色 [' + role.nameZh + '] 已启用')
                } else {
                  this.$message('角色 [' + role.nameZh + '] 已禁用')
                }
              }
            })
          }).catch(() => {
            if (role.status === 1)
              role.status = 0
            else
              role.status = 1
            this.$message({
              type: 'info',
              message: '已取消'
            })
          })
        } else {
          role.status = 1
          this.$alert('无法禁用系统管理员！')
        }
      },
      editRole(role) {
        this.dialogFormVisible = true
        this.selectedRole = role
        let permIds = []
        for (let i = 0; i < role.perms.length; i++) {
          permIds.push(role.perms[i].id)
        }
        this.selectedPermsIds = permIds
        let menuIds = []
        for (let i = 0; i < role.menus.length; i++) {
          menuIds.push(role.menus[i].id)
          for (let j = 0; j < role.menus[i].children.length; j++) {
            menuIds.push(role.menus[i].children[j].id)
          }
        }
        this.selectedMenusIds = menuIds
        // 判断树是否已经加载，第一次打开对话框前树不存在，会报错。所以需要设置 default-checked
        if (this.$refs.tree) {
          this.$refs.tree.setCheckedKeys(menuIds)
        }
      },
      deleteRole(roleId) {

      },
      onSubmit(role) {
        // 根据视图绑定的角色 id 向后端传送角色信息
        let perms = []

        for (let i = 0; i < this.selectedPermsIds.length; i++) {
          for (let j = 0; j < this.permList.length; j++) {
            if (this.selectedPermsIds[i] === this.permList[j].id) {
              perms.push(this.permList[j])
            }
          }
        }
        const rolePost = {
          id: role.id,
          name: role.name,
          nameZh: role.nameZh,
          status: role.status,
          perms: perms
        }
        this.$store.dispatch('role/updateRolePermission', rolePost).then(resp => {
          if (resp && resp.code === 200) {
            this.$alert(resp.result)
            this.dialogFormVisible = false
            this.listRoles()
          }
        })
        const arr = {
          roleId: role.id,
          menusIds: {
            menusIds: this.$refs.tree.getCheckedKeys()
          }
        }
        this.$store.dispatch('role/updateRoleMenu', arr).then(resp => {
          if (resp && resp.code === 200) {
            console.log(resp.result)
          }
        }).catch()
      }
    }
  }
</script>

<style scoped>
  .add-button {
    float: left;
    margin: 18px 0 18px 10px;
  }
</style>
