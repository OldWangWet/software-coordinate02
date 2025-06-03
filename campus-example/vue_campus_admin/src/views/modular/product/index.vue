<template>
  <div class="app-container">
    <el-card shadow="never">
      <!-- 搜索条件 -->
      <div class="toolbar">
        <el-form :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item label="商品名称">
            <el-input
              v-model="queryForm.name"
              placeholder="请输入商品名称"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="handleSearch"
            >查询</el-button>
            <el-button
              type="warning"
              icon="el-icon-refresh"
              @click="resetQuery"
            >重置</el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- 操作按钮 -->
      <div class="toolbar">
        <el-button
          type="primary"
          icon="el-icon-plus"
          @click="openAddDialog"
        >新增</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          @click="handleBatchDelete"
          :disabled="!multipleSelection.length"
        >批量删除</el-button>
      </div>

      <!-- 表格 -->
      <el-table
        :data="productList"
        border
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="name" label="名称" min-width="180"></el-table-column>
        <el-table-column prop="price" label="价格" width="120">
          <template #default="{ row }">
            {{ row.price !== null ? row.price.toFixed(2) : '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="introduce" label="简介" min-width="200"></el-table-column>
        <el-table-column prop="stock" label="库存" width="100"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180"></el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="{ row }">
            <el-button
              type="text"
              size="small"
              @click="openEditDialog(row)"
            >编辑</el-button>
            <el-button
              type="text"
              size="small"
              style="color: red;"
              @click="handleDelete(row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="pagination">
        <el-pagination
          background
          layout="prev, pager, next, jumper, total"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </el-card>

    <!-- 新增/编辑 对话框（保持不变） -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input-number
            v-model="form.price"
            :min="0"
            :step="0.01"
            style="width: 100%;"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="简介" prop="introduce">
          <el-input type="textarea" v-model="form.introduce" :rows="3"></el-input>
        </el-form-item>
        <el-form-item label="图片 URL" prop="image">
          <el-input v-model="form.image" placeholder="请输入图片地址"></el-input>
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input-number
            v-model="form.stock"
            :min="0"
            style="width: 100%;"
          ></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listProduct,
  getProduct,
  addProduct,
  updateProduct,
  delProduct
} from '@/api/modular/product'

export default {
  name: 'Product',
  data() {
    return {
      // 查询表单：只保留 name。page/size 用于分页请求
      queryForm: {
        name: '',
        page: 1,
        size: 10
      },
      // 列表数据、分页信息
      productList: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      multipleSelection: [],

      // 对话框相关
      dialogVisible: false,
      dialogTitle: '新增商品',
      form: {
        id: null,
        name: '',
        price: 0.0,
        introduce: '',
        image: '',
        stock: 0
      },
      rules: {
        name: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
        price: [{ required: true, message: '请输入价格', trigger: 'change' }],
        stock: [{ required: true, message: '请输入库存', trigger: 'change' }]
      }
    }
  },
  mounted() {
    this.fetchData()
  },
  methods: {
    // —— 获取列表数据 —— 
    fetchData() {
      const params = {
        page: this.currentPage,
        size: this.pageSize,
        name: this.queryForm.name.trim()
      }
      listProduct(params).then(res => {
        // 前端要根据实际返回结构：res.data.rows 数组，res.data.total 总条数
        const result = res
        // result.rows 里是记录数组，result.total 是字符串，需要转成数字
        this.productList = Array.isArray(result.rows) ? result.rows : []
        this.total = Number(result.total) || 0
        // currentPage 和 pageSize 由前端自己维护，不从后端取
      })
    },

    // 翻页 回调
    handleCurrentChange(page) {
      this.currentPage = page
      this.fetchData()
    },

    // 选中行
    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    // 点击“查询”
    handleSearch() {
      this.currentPage = 1
      this.fetchData()
    },

    // 重置 查询条件
    resetQuery() {
      this.queryForm.name = ''
      this.handleSearch()
    },

    // 打开“新增”对话框
    openAddDialog() {
      this.dialogTitle = '新增商品'
      this.dialogVisible = true
      Object.assign(this.form, {
        id: null,
        name: '',
        price: 0.0,
        introduce: '',
        image: '',
        stock: 0
      })
    },

    // 打开“编辑”对话框
    openEditDialog(row) {
      this.dialogTitle = '编辑商品'
      getProduct(row.id).then(res => {
        // getProduct 返回格式一般是 { code, msg, data: { ...product } }

        Object.assign(this.form, res.data)
        this.dialogVisible = true
      })
    },

    // 提交 新增/修改 表单
    submitForm() {
      this.$refs.formRef.validate(valid => {
        if (!valid) return
        const apiCall = this.form.id ? updateProduct : addProduct
        apiCall(this.form).then(() => {
          this.$message.success('操作成功')
          this.dialogVisible = false
          this.fetchData()
        })
      })
    },

    // 单条 删除
    handleDelete(row) {
      this.$confirm('此操作将删除该商品，是否继续？', '提示', {
        type: 'warning'
      }).then(() => {
        delProduct([row.id]).then(() => {
          this.$message.success('删除成功')
          this.fetchData()
        })
      })
    },

    // 批量 删除
    handleBatchDelete() {
      const ids = this.multipleSelection.map(item => item.id)
      this.$confirm(`此操作将删除选中的 ${ids.length} 条记录，是否继续？`, '提示', {
        type: 'warning'
      }).then(() => {
        delProduct(ids).then(() => {
          this.$message.success('批量删除成功')
          this.fetchData()
        })
      })
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
.toolbar {
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}
.pagination {
  margin-top: 20px;
  text-align: right;
}
</style>
