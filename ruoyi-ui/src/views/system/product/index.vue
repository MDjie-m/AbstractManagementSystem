<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商名称" prop="supplierId">
        <el-input
          v-model="queryParams.supplierId"
          placeholder="请输入供应商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一级分类" prop="primaryCategory">
        <el-input
          v-model="queryParams.primaryCategory"
          placeholder="请输入一级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二级分类" prop="secondaryCategory">
        <el-input
          v-model="queryParams.secondaryCategory"
          placeholder="请输入二级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="三级分类" prop="tertiaryCategory">
        <el-input
          v-model="queryParams.tertiaryCategory"
          placeholder="请输入三级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="四级分类" prop="quaternaryCategory">
        <el-input
          v-model="queryParams.quaternaryCategory"
          placeholder="请输入四级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="五级分类" prop="fifthCategory">
        <el-input
          v-model="queryParams.fifthCategory"
          placeholder="请输入五级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品型号" prop="productModel">
        <el-input
          v-model="queryParams.productModel"
          placeholder="请输入产品型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否可报价：0：不可报价，1：可报价" prop="quotationFlag">
        <el-input
          v-model="queryParams.quotationFlag"
          placeholder="请输入是否可报价：0：不可报价，1：可报价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段1" prop="futureField1">
        <el-input
          v-model="queryParams.futureField1"
          placeholder="请输入预留字段1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段2" prop="futureField2">
        <el-input
          v-model="queryParams.futureField2"
          placeholder="请输入预留字段2"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段3" prop="futureField3">
        <el-input
          v-model="queryParams.futureField3"
          placeholder="请输入预留字段3"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:product:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:product:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:product:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:product:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="产品编号" align="center" prop="productId" />
      <el-table-column label="供应商名称" align="center" prop="supplierId" />
      <el-table-column label="一级分类" align="center" prop="primaryCategory" />
      <el-table-column label="二级分类" align="center" prop="secondaryCategory" />
      <el-table-column label="三级分类" align="center" prop="tertiaryCategory" />
      <el-table-column label="四级分类" align="center" prop="quaternaryCategory" />
      <el-table-column label="五级分类" align="center" prop="fifthCategory" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="0-国产 1-进口" align="center" prop="domesticImportedType" />
      <el-table-column label="产品型号" align="center" prop="productModel" />
      <el-table-column label="是否可报价：0：不可报价，1：可报价" align="center" prop="quotationFlag" />
      <el-table-column label="预留字段1" align="center" prop="futureField1" />
      <el-table-column label="预留字段2" align="center" prop="futureField2" />
      <el-table-column label="预留字段3" align="center" prop="futureField3" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:product:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:product:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="供应商名称" prop="supplierId">
          <el-input v-model="form.supplierId" placeholder="请输入供应商名称" />
        </el-form-item>
        <el-form-item label="一级分类" prop="primaryCategory">
          <el-input v-model="form.primaryCategory" placeholder="请输入一级分类" />
        </el-form-item>
        <el-form-item label="二级分类" prop="secondaryCategory">
          <el-input v-model="form.secondaryCategory" placeholder="请输入二级分类" />
        </el-form-item>
        <el-form-item label="三级分类" prop="tertiaryCategory">
          <el-input v-model="form.tertiaryCategory" placeholder="请输入三级分类" />
        </el-form-item>
        <el-form-item label="四级分类" prop="quaternaryCategory">
          <el-input v-model="form.quaternaryCategory" placeholder="请输入四级分类" />
        </el-form-item>
        <el-form-item label="五级分类" prop="fifthCategory">
          <el-input v-model="form.fifthCategory" placeholder="请输入五级分类" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="产品型号" prop="productModel">
          <el-input v-model="form.productModel" placeholder="请输入产品型号" />
        </el-form-item>
        <el-form-item label="是否可报价：0：不可报价，1：可报价" prop="quotationFlag">
          <el-input v-model="form.quotationFlag" placeholder="请输入是否可报价：0：不可报价，1：可报价" />
        </el-form-item>
        <el-form-item label="预留字段1" prop="futureField1">
          <el-input v-model="form.futureField1" placeholder="请输入预留字段1" />
        </el-form-item>
        <el-form-item label="预留字段2" prop="futureField2">
          <el-input v-model="form.futureField2" placeholder="请输入预留字段2" />
        </el-form-item>
        <el-form-item label="预留字段3" prop="futureField3">
          <el-input v-model="form.futureField3" placeholder="请输入预留字段3" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProduct, getProduct, delProduct, addProduct, updateProduct } from "@/api/system/product";

export default {
  name: "Product",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 产品表格数据
      productList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierId: null,
        primaryCategory: null,
        secondaryCategory: null,
        tertiaryCategory: null,
        quaternaryCategory: null,
        fifthCategory: null,
        productName: null,
        domesticImportedType: null,
        productModel: null,
        quotationFlag: null,
        futureField1: null,
        futureField2: null,
        futureField3: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询产品列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        productId: null,
        supplierId: null,
        primaryCategory: null,
        secondaryCategory: null,
        tertiaryCategory: null,
        quaternaryCategory: null,
        fifthCategory: null,
        productName: null,
        domesticImportedType: null,
        productModel: null,
        quotationFlag: null,
        futureField1: null,
        futureField2: null,
        futureField3: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.productId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加产品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const productId = row.productId || this.ids
      getProduct(productId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.productId != null) {
            updateProduct(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduct(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const productIds = row.productId || this.ids;
      this.$modal.confirm('是否确认删除产品编号为"' + productIds + '"的数据项？').then(function() {
        return delProduct(productIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/product/export', {
        ...this.queryParams
      }, `product_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
