<template>
  <div class="app-container">

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="留言ID" prop="id"/>

      <el-table-column align="center" label="客户姓名" prop="contact"/>

      <el-table-column align="center" label="联系电话" prop="phone"/>

      <el-table-column align="center" label="邮箱" prop="email"/>

      <el-table-column align="center" min-width="400px" label="留言内容" prop="content"/>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

  </div>
</template>

<script>
import { listMessage } from '@/api/message'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Message',
  components: { Pagination },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      listMessage(this.listQuery)
        .then(response => {
          this.list = response.data.data.list
          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    }
  }
}
</script>
