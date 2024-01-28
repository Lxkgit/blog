<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/index' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>系统更新</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <router-link to="/admin/content/update/editor" class="add-link">
      <el-button type="success">发布更新</el-button>
    </router-link>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="updateList"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="updateTime"
          label="发布日期">
          <!--          width="200">-->
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editUpdate(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteUpdate(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import TinyMCE from '../util/TinyMCE'

export default {
  name: 'SystemUpdate',
  components: {TinyMCE},
  data () {
    return {
      updateList: [],
      pageSize: 10,
      total: 0
    }
  },
  mounted () {
    this.loadUpdate()
  },
  computed: {
    tableHeight () {
      return window.innerHeight
    }
  },
  methods: {
    loadUpdate () {
      var _this = this
      this.$axios.get('/admin/update/select/list/' + this.pageSize + '/' + 1).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.updateList = resp.data.result.list
          _this.total = resp.data.result.size
        }
      })
    },
    handleCurrentChange (page) {
      var _this = this
      this.$axios.get('/admin/update/select/list/' + this.pageSize + '/' + page).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.updateList = resp.data.result.list
          _this.total = resp.data.result.size
        }
      })
    },
    editUpdate (update) {
      this.$router.push(
        {
          name: 'TinyModeUpdate',
          params: {
            update: update
          }
        }
      )
    },
    deleteUpdate (id) {
      this.$confirm('此操作将永久删除该系统更新日志, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete('/admin/update/delete/id/' + id).then(resp => {
          if (resp && resp.data.code === 200) {
            this.loadUpdate()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
  .add-link {
    margin: 18px 0 15px 10px;
    float: left;
  }
</style>
