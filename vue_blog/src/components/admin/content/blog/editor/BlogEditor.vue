<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/index'}">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/blog'}">文章管理</el-breadcrumb-item>
        <el-breadcrumb-item>编辑器</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row>
      <el-input
        v-model="article.title"
        style="margin: 10px 0;font-size: 18px;"
        placeholder="请输入标题"></el-input>
    </el-row>
    <el-row style="height: calc(100vh - 140px);">
      <mavon-editor
        v-model="article.content_md"
        style="height: 100%;"
        ref=md
        @save="saveArticles"
        fontSize="16px">
        <button type="button" class="op-icon el-icon-s-management" :title="'文章分类'" slot="left-toolbar-after"
                @click="dialogFormVisible = true"></button>
      </mavon-editor>
    </el-row>


    <el-dialog title="发布文章" :visible.sync="dialogFormVisible" style="padding-top: 10px" width="500px" :close-on-click-modal="false">
      <div>
        <span>填写标签: &nbsp;&nbsp;&nbsp;&nbsp;</span>
        <el-tag
          :key="tag.labelId"
          v-for="tag in selectLabelList"
          closable
          :disable-transitions="false"
          @close="deleteLabel(tag)">
          {{tag.labelName}}
        </el-tag>
        <div style="display: inline-block;" id="editor-div">
          <el-popover
            id="editor-popover"
            placement="bottom"
            width="400"
            trigger="click"
            style="max-height: 200px"
            :close-on-click-modal="false">
            <span style="margin-left: 30px">添加标签</span>
            <div style="width: auto">
              <el-input class="input-new-tag" ref="saveTagInput" size="small"/>
            </div>
            <div style="margin-top: 20px;">
              <div style="float: left; width: 100px; border-right: 1px solid rgb(151,151,160)">
                <ul class="row pre-scrollable infinite-list" style="overflow-y: auto; height: 200px;" >
                  <li v-for="labelType in labelList" class="infinite-list-item" style="height: 30px; text-align:center;" @click="selectLabelType(labelType)">{{ labelType.labelTypeName }}</li>
                  <el-input size="mini" v-model="inputLabelType" @change="addLabelType" />
                </ul>
              </div>
              <div style="float: left; margin-left: 30px; display:inline-block; width: 250px; height: 200px; overflow:auto;">
                <el-tag
                  size="small"
                  :key="label.labelId"
                  v-for="label in labelType"
                  :disable-transitions="false"
                  @click="selectLabel(label)">
                  {{label.labelName}}
                </el-tag>
                <el-input
                  class="input-new-tag"
                  v-if="labelVisible"
                  v-model="inputLabel"
                  ref="saveTagInput"
                  size="mini"
                  style="width: 60px;height: 24px;margin-top: 12px"
                  @keyup.enter.native="addLabel"
                  @blur="addLabel"
                >
                </el-input>
                <el-button v-else size="mini" style="width: 60px; height: 24px;margin-top: 12px; line-height: 0" class="button-new-tag" @click="showInput">New</el-button>
              </div>
            </div>
            <el-button slot="reference" size="small" style="margin-left: 6px; margin-top: 10px;">选择标签</el-button>
          </el-popover>
        </div>

      </div>
      <div style="margin-top: 20px">
        <span>选择分类: &nbsp;&nbsp;&nbsp;&nbsp;</span>
        <el-cascader
          :options="types"
          :props="{ checkStrictly: true }"
          ref="saveTagInput"
          v-model="type"
          placeholder="选择文章分类"
          @change="handleChange"
          clearable></el-cascader>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import ImgUpload from '../../util/ImgUpload'
  import {mapGetters} from "vuex";

  export default {
    name: 'Editor',
    components: {ImgUpload},
    data() {
      return {
        // 编辑页面文章
        article: {},
        // 提交后端的文章类
        postArticle: {},
        // 博客文章标签弹框是否显示
        dialogFormVisible: false,
        // 当前文章浏览量
        browse: 1,
        // 当前文章点赞数量
        like: 1,
        // 当前文章的分类
        type: [],
        // 所有文章分类属性
        types: [],
        // 接口返回标签分类和标签
        labelList: [],
        // 存放点击的标签分类包含的标签
        labelType:[],
        // 存放选中的标签
        selectLabelList: [],
        // 新增标签类型弹框是否显示
        labelTypeVisible: false,
        // 新增标签弹框是否显示
        labelVisible: false,
        // 当前文章的标签
        labelValue: '',
        // 当前文章的标签
        labelTypeValue: '',
        // 添加标签分类输入框
        inputLabelType: '',
        // 添加标签输入框
        inputLabel: '',
        // 当前选中的标签分类id
        selectTypeId: ''
      }
    },
    computed: {
      ...mapGetters('articleLabel', ['blogArticleLabelTypeList']),
    },
    mounted() {
      if (this.$route.params.article) {
        this.article = this.$route.params.article
        this.selectLabelList = this.article.labels
        for (let i=0; i<this.article.types.length; i++) {
          this.type[i] = this.article.types[i].value
        }
      }
      this.selectLabelTree()
      this.selectArticleLabelTypeAndLabelList()

    },
    methods: {
      // 前端添加标签分类方法
      addLabelType(){
        let labelType
        if (this.inputLabelType !== ''){
          labelType = {
            labelTypeName: this.inputLabelType,
            labelNum: 0
          }
          this.$store.dispatch('articleLabel/insertArticleLabelType', labelType).then(resp => {
            if (resp && resp.code === 200) {
              this.selectArticleLabelTypeAndLabelList()
              this.inputLabelType = ''
            }
          })
        }
      },
      // 前端添加标签
      addLabel(){
        if (this.inputLabel !== ''){
          let label
          label = {
            labelName: this.inputLabel,
            labelTypeId: this.selectTypeId,
            articleNum: 0
          }
          this.$store.dispatch('articleLabel/insertArticleLabel', label).then(resp => {
            if (resp && resp.code === 200) {
              this.$store.dispatch('articleLabel/selectArticleLabelByLabelTypeId', this.selectTypeId).then(resp => {
                this.labelType = []
                this.labelType = this.$store.getters["articleLabel/blogArticleLabelList"]
              })
            }
          })
        }
        this.labelVisible = false
        this.inputLabel = ''
      },
      // 前端页面选择标签分类触发事件
      selectLabelType(labelType){
        this.selectTypeId = labelType.labelTypeId
        this.labelType = labelType.articleLabelList
      },
      // 前端页面选择标签触发事件
      selectLabel(label){
        let flag = true
        for (let i=0; i<this.selectLabelList.length; i++){
          if (this.selectLabelList[i].labelId === label.labelId){
            flag = false
            this.$message.error('当前标签已被添加');
          }
        }
        if (flag){
          this.selectLabelList.push(label)
        }
      },
      // 接口调用后端返回标签分类和标签数据
      selectArticleLabelTypeAndLabelList() {
        this.$store.dispatch('articleLabel/selectArticleLabelTypeAndLabelList').then(resp => {
          if (resp && resp.code === 200){
            this.labelList = []
            this.labelList = this.$store.getters["articleLabel/blogArticleLabelTypeList"]
            if (this.labelType === undefined || this.labelType.length <= 0 ){
              this.selectTypeId = this.labelList[0].labelTypeId
              this.labelType = this.labelList[0].articleLabelList
            }
          }
        })
      },
      // 删除已选择的文章标签
      deleteLabel(tag) {
        this.selectLabelList.splice(this.selectLabelList.indexOf(tag), 1);
      },
      // 选择当前文章的分类
      handleChange(value) {
        this.type = value
      },
      // 调用接口加载所有的文章分类
      selectLabelTree() {
        this.$store.dispatch('articleType/selectArticleTypeTree').then(resp => {
          if (resp && resp.code === 200) {
            this.types = resp.result
          }
        })
      },
      // 保存文章函数
      saveArticles(value, render) {
        // value 是 md，render 是 html
        // 文章标签转换
        let labelId
        for(let i=0; i<this.selectLabelList.length; i++){
          if (i === 0){
            labelId = this.selectLabelList[i].labelId
          } else {
            labelId += '$'
            labelId += this.selectLabelList[i].labelId
          }
        }
        // 文章类型转换
        let typeId
        for (let i=0; i<this.type.length; i++){
          if (i === 0){
            typeId = this.type[i]
          } else {
            typeId += '$'
            typeId += this.type[i]
          }

        }
        // if (this.type[0] === undefined || this.type[0] === '' || this.type[1] === undefined || this.type[1] === '') {
        if (this.type[0] === undefined || this.type[0] === '') {
          this.dialogFormVisible = true
        } else {
          this.$confirm('确认保存文章', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var date = new Date()
            // 获取年
            var dateYear = date.getFullYear()
            // 获取月
            var dateMonth = date.getMonth() + 1
            if (dateMonth < 10) {
              dateMonth = '0' + dateMonth
            }
            // 取当日
            var dateDate = date.getDate()
            if (dateDate < 10) {
              dateDate = '0' + dateDate
            }
            var createTime = dateYear + '-' + dateMonth + '-' + dateDate
            this.postArticle = {
              id: this.article.id,
              title: this.article.title,
              content_md: value,
              content_html: render,
              create_time: this.article.create_time === '' || this.article.create_time === undefined ? createTime : this.article.create_time,
              articleType: typeId,
              articleLabel: labelId,
              browse_count: (this.article.browse_count === '' || this.article.browse_count === undefined) ? this.browse : this.article.browse_count,
              like_count: (this.article.like_count === '' || this.article.like_count === undefined) ? this.like : this.article.like_count,
            }
            if (this.article.id === 0 || this.article.id === undefined) {
              this.$store.dispatch('article/insertBlog', this.postArticle).then(resp => {
                if (resp && resp.code === 200) {
                  this.$message({
                    type: 'success',
                    message: '已保存成功'
                  })
                  this.$router.replace('/admin/content/blog')
                }
              })
            } else {
              this.$store.dispatch('article/updateBlog', this.postArticle).then(resp => {
                if (resp && resp.code === 200) {
                  this.$message({
                    type: 'success',
                    message: '已修改成功'
                  })
                  this.$router.replace('/admin/content/blog')
                }
              })
            }
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消发布'
            })
          })
        }
      },
      showInput() {
        this.labelVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      }
    },
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

  /*消除多选*/
  .el-radio-group .el-radio {
    margin-right: 14px !important;
  }
</style>

<style scoped>
  .el-tag + .el-tag {
    margin-top: 10px;
    margin-left: 10px;

  }
  .button-new-tag {
    margin-top: 10px;
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    margin-top: 10px;
    width: 370px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .infinite-list li:hover{
    color: rgba(0,1,0,0.5);
  }

</style>
