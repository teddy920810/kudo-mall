<template>
  <div class="app-container">
    <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-width="300px">
      <el-form-item label="商场名称" prop="litemall_mall_name">
        <el-input v-model="dataForm.litemall_mall_name"/>
      </el-form-item>
      <el-form-item label="商场地址" prop="litemall_mall_address">
        <el-input v-model="dataForm.litemall_mall_address"/>
      </el-form-item>
      <el-form-item label="联系电话" prop="litemall_mall_phone">
        <el-input v-model="dataForm.litemall_mall_phone"/>
      </el-form-item>
      <el-form-item label="电子邮箱" prop="litemall_mall_email">
        <el-input v-model="dataForm.litemall_mall_email"/>
      </el-form-item>
      <el-form-item label="二维码">
        <el-upload
          :action="uploadPath"
          :show-file-list="false"
          :headers="headers"
          :on-success="uploadPicUrl"
          class="avatar-uploader"
          accept=".jpg,.jpeg,.png,.gif">
          <img v-if="dataForm.litemall_mall_qrcode" :src="dataForm.litemall_mall_qrcode" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="update">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { listMall, updateMall } from '@/api/config'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'

export default {
  name: 'ConfigMail',
  data() {
    return {
      uploadPath,
      dataForm: {
        litemall_mall_name: '',
        litemall_mall_address: '',
        litemall_mall_phone: '',
        litemall_mall_email: '',
        litemall_mall_qrcode: ''
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
    this.init()
  },
  methods: {
    init: function() {
      listMall().then(response => {
        this.dataForm = response.data.data
      })
    },
    cancel() {
      this.init()
    },
    update() {
      updateMall(this.dataForm)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '商场配置成功'
          })
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    uploadPicUrl: function(response) {
      this.dataForm.litemall_mall_qrcode = response.data.url
    }
  }
}
</script>
<style scoped>
  .avatar-uploader {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 120px;
  }
  .avatar-uploader :hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
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
