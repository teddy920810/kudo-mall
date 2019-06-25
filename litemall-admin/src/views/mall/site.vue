<template>
  <div class="app-container">
    <el-tabs type="border-card">
      <el-tab-pane label="首页">
        <el-card class="box-card" shadow="always">
          <el-form ref="indexForm" :model="dataForm" status-icon label-position="left" label-width="100px">
            <el-form-item label="Banner图片" prop="picUrl">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_banner')}"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_banner" :src="dataForm.kudo_index_banner" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
            <el-form-item label="系列图" prop="picUrl">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_category', 0)}"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_category[0]" :src="dataForm.kudo_index_category[0]" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_category', 1)}"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_category[1]" :src="dataForm.kudo_index_category[1]" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_category', 2)}"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_category[2]" :src="dataForm.kudo_index_category[2]" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-form>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="企事业单位定制" shadow="always">
        <el-card class="box-card" shadow="always">
          <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px">
            <el-form-item label="Banner图片" prop="picUrl">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_customized_banner')}"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_customized_banner" :src="dataForm.kudo_customized_banner" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-form>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="品牌介绍" shadow="always">
        <el-card class="box-card" shadow="always">
          <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px">
            <el-form-item label="Banner图片" prop="picUrl">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_brand_banner')}"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_brand_banner" :src="dataForm.kudo_brand_banner" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-form>
        </el-card>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.avatar-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 220px;
  display: inline-block;
}
.avatar-uploader :hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 220px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
</style>

<script>
import { getSite, updateSite } from '@/api/site'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'

export default {
  name: 'Site',
  data() {
    return {
      uploadPath,
      dataForm: {
        kudo_index_banner: '',
        kudo_index_category: [],
        kudo_customized_banner: '',
        kudo_brand_banner: ''
      }
    }
  },
  computed: {
    headers() {
      return {
        'X-Litemall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.intiSite()
  },
  methods: {
    intiSite() {
      getSite().then((res) => {
        this.dataForm = res.data.data
      })
    },
    updateData() {
      updateSite(this.dataForm).then(res => {
        this.intiSite()
      })
    },
    uploadPicUrl(response, dom, index) {
      console.log(response, dom, index)
      this.dataForm[dom] = response.data.url
      // this.dataForm.picUrl = response.data.url
    }
  }
}
</script>
