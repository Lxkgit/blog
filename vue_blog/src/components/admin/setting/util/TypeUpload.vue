<template>
  <div>
    <div class="tool-img-item">
      <h4>{{ title }}</h4>
      <el-upload
        ref="upload"
        action="#"
        list-type="picture-card"
        :auto-upload="true"
        :file-list="fileList"
        :class="{'img_upload': fileList.length > 0 && fileList[0].url!==''}"
        :on-progress="upload"
      >
        <i slot="default" class="el-icon-plus" />
        <div slot="file" slot-scope="{file}">
          <img
            class="el-upload-list__item-thumbnail"
            :src="file.url"
            alt
          >
          <span class="el-upload-list__item-actions">
            <span
              class="el-upload-list__item-preview"
              @click="handleChangeAddr"
            >
              <i class="el-icon-edit" />
            </span>
            <span
              class="el-upload-list__item-preview"
              @click="handlePictureCardPreview(file)"
            >
              <i class="el-icon-zoom-in" />
            </span>
            <span
              v-if="!disabled"
              class="el-upload-list__item-delete"
              @click="handleRemove()"
            >
              <i class="el-icon-delete" />
            </span>
          </span>
        </div>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'TypeUpload',
    props: {
      title: { type: String, default: '' },
      field: { type: String, default: '' },
      model: { type: String, default: '' },
      type: {type: String, default: ''}
    },
    data () {
      // 判断是否有图片
      let fileList = {}
      if (this.model === '') {
        fileList = []
      } else {
        fileList = [{ name: 'test.png', url: this.model }]
      }
      return {
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        // 因为vue默认不能直接修改设置，所以我们需要自己创建一个副本
        fileList
      }
    },
    methods: {
      handleRemove () {
        this.fileList = []
        // 手动设置图标内容为空
        this.updateSetting('')
      },
      handlePictureCardPreview (file) {
        this.dialogImageUrl = file.url
        this.dialogVisible = true
      },
      handleChangeAddr () {
        this.$prompt('图片地址', '提示', {
          confirmButtonText: '修改',
          cancelButtonText: '取消',
          inputValue: this.fileList[0].url,
          closeOnClickModal: false
        }).then(({ value }) => {
          this.updateSetting(value)
        })
      },
      updateSetting (value) { // 手动设置图片内容
        this.$store.dispatch('setting/updateSetting', { key: this.field, value: value, type: this.type})
          .then((resp) => {
            if (resp.code === 200) {
              this.$message.success('保存成功')
              if (value !== '') {
                this.fileList[0].url = value
                console.log(this.fileList[0].url)
              }
            }
          })
          .catch(() => {
            this.$message.error('保存设置失败')
          })
      },
      upload (event, file) {
        const data = new FormData()
        data.append('file', file.raw)
        data.append('key', this.field)
        data.append('value', this.model)
        this.$store.dispatch('upload/uploadImg', data).then((resp) => {
          if (resp.code === 200) {
            this.$store.dispatch('setting/selectWebSetting')
          }
        }).catch(() => {
          this.fileList = [{ name: 'test.png', url: this.model }]
          this.$message.error('上传图片失败')
        })
      }
    }
  }
</script>
<style>
  /*隐藏上传按钮*/
  .img_upload .el-upload--picture-card{
    display: none!important;
  }
  /*清除默认的正方形预览框*/
  .el-upload-list--picture-card .el-upload-list__item {
    height: auto!important;
    border: 0!important;
    outline: none;
  }
</style>

<style scoped lang="scss">
  /*侧边栏设置的相关设置*/
  .tool-img-item{
    display: flex;
    flex-direction: column;
    h4{
      font-size: 15px;
      font-weight: 800;
      margin: 5px 0;
    }
  }
</style>
