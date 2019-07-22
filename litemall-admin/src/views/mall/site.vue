<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-button @click="cancel">取消</el-button>
      <el-button type="primary" @click="update">确定</el-button>
    </el-card>
    <el-tabs type="border-card" style="margin-top: 20px">
      <el-tab-pane label="首页">
        <el-card class="box-card" shadow="always">
          <el-form ref="indexForm" :model="dataForm" status-icon label-position="left" label-width="100px">
            <el-form-item label="Banner图片">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_banner')}"
                class="avatar-uploader"
                style="width: 960px;height: 489px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_banner.picUrl" :src="dataForm.kudo_index_banner.picUrl" style="width: 960px;height: 489px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 960px;height: 489px;line-height: 489px"/>
              </el-upload>
              <div>建议大小：1920*978</div>
            </el-form-item>
            <el-form-item label="系列图">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_category', 0)}"
                class="avatar-uploader"
                style="width: 388px;height: 310px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_category.gallery[0]" :src="dataForm.kudo_index_category.gallery[0]" class="avatar" style="width: 388px;height: 310px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 380px;height: 310px;line-height: 310px;"/>
              </el-upload>
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_category', 1)}"
                class="avatar-uploader"
                style="width: 388px;height: 310px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_category.gallery[1]" :src="dataForm.kudo_index_category.gallery[1]" class="avatar" style="width: 388px;height: 310px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 380px;height: 310px;line-height: 310px;"/>
              </el-upload>
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_category', 2)}"
                class="avatar-uploader"
                style="width: 388px;height: 310px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_category.gallery[2]" :src="dataForm.kudo_index_category.gallery[2]" class="avatar" style="width: 388px;height: 310px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 380px;height: 310px;line-height: 310px;"/>
              </el-upload>
              <div>建议大小：388*310</div>
            </el-form-item>
            <el-form-item label="品牌文化图">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_brand')}"
                class="avatar-uploader"
                style="width: 960px;height: 489px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_brand.picUrl" :src="dataForm.kudo_index_brand.picUrl" style="width: 960px;height: 489px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 960px;height: 489px;line-height: 489px"/>
              </el-upload>
              <div>建议大小：903*552</div>
            </el-form-item>
            <el-form-item label="企业定制图">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_ent')}"
                class="avatar-uploader"
                style="width: 903px;height: 552px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_ent.picUrl" :src="dataForm.kudo_index_ent.picUrl" style="width: 903px;height: 552px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 903px;height: 552px;line-height: 552px"/>
              </el-upload>
              <div>建议大小：903*552</div>
            </el-form-item>
            <el-form-item label="酒店定制图">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_hotel')}"
                class="avatar-uploader"
                style="width: 903px;height: 552px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_hotel.picUrl" :src="dataForm.kudo_index_hotel.picUrl" style="width: 903px;height: 552px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 903px;height: 552px;line-height: 552px"/>
              </el-upload>
              <div>建议大小：903*552</div>
            </el-form-item>
            <el-form-item label="学校定制图">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="(res) => { return uploadPicUrl(res, 'kudo_index_school')}"
                class="avatar-uploader"
                style="width: 903px;height: 552px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_index_school.picUrl" :src="dataForm.kudo_index_school.picUrl" style="width: 903px;height: 552px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 903px;height: 552px;line-height: 552px"/>
              </el-upload>
              <div>建议大小：903*552</div>
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
                style="width: 960px;height: 259px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_customized_banner.picUrl" :src="dataForm.kudo_customized_banner.picUrl" class="avatar" style="width: 960px;height: 259px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 960px;height: 259px;line-height: 259px"/>
              </el-upload>
              <div>建议大小：1920*518</div>
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
                style="width: 960px;height: 259px"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="dataForm.kudo_brand_banner.picUrl" :src="dataForm.kudo_brand_banner.picUrl" class="avatar" style="width: 960px;height: 259px">
                <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 960px;height: 259px;line-height: 259px"/>
              </el-upload>
              <div>建议大小：1920*518</div>
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

const defaultSite = {
  id: undefined,
  key: '',
  title: '',
  content: '',
  picUrl: '',
  gallery: []

}
export default {
  name: 'Site',
  data() {
    return {
      uploadPath,
      dataForm: {
        kudo_index_banner: Object.assign({}, defaultSite),
        kudo_index_category: Object.assign({}, defaultSite),
        kudo_customized_banner: Object.assign({}, defaultSite),
        kudo_brand_banner: Object.assign({}, defaultSite),
        kudo_index_brand: Object.assign({}, defaultSite),
        kudo_index_ent: Object.assign({}, defaultSite),
        kudo_index_hotel: Object.assign({}, defaultSite),
        kudo_index_school: Object.assign({}, defaultSite)
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
    cancel() {
      this.intiSite()
    },
    update() {
      updateSite(this.dataForm).then(res => {
        this.intiSite()
      }).catch(response => {
        this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
    },
    uploadPicUrl(response, dom, index) {
      console.log(response, dom, index)
      if (index === undefined) {
        this.dataForm[dom].picUrl = response.data.url
        console.log(this.dataForm[dom])
      } else {
        this.$set(this.dataForm[dom].gallery, index, response.data.url)
      }
      //
      // this.dataForm.picUrl = response.data.url
    }
  }
}
</script>
