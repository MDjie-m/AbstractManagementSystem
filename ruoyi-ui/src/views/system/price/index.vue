<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商id：外键" prop="supplierId">
        <el-input
          v-model="queryParams.supplierId"
          placeholder="请输入供应商id：外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品id：外键" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入产品id：外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称中文" prop="supplierNameCn">
        <el-input
          v-model="queryParams.supplierNameCn"
          placeholder="请输入企业名称中文"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称英文" prop="supplierNameEn">
        <el-input
          v-model="queryParams.supplierNameEn"
          placeholder="请输入企业名称英文"
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
      <el-form-item label="人民币报价" prop="priceRmb">
        <el-input
          v-model="queryParams.priceRmb"
          placeholder="请输入人民币报价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="美金报价" prop="priceUsd">
        <el-input
          v-model="queryParams.priceUsd"
          placeholder="请输入美金报价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间" prop="time">
        <el-date-picker clearable
          v-model="queryParams.time"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作员id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入操作员id"
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
          v-hasPermi="['system:price:add']"
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
          v-hasPermi="['system:price:edit']"
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
          v-hasPermi="['system:price:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:price:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="priceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="supplierPriceId" />
      <el-table-column label="供应商id：外键" align="center" prop="supplierId" />
      <el-table-column label="产品id：外键" align="center" prop="productId" />
      <el-table-column label="企业名称中文" align="center" prop="supplierNameCn" />
      <el-table-column label="企业名称英文" align="center" prop="supplierNameEn" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="人民币报价" align="center" prop="priceRmb" />
      <el-table-column label="美金报价" align="center" prop="priceUsd" />
      <el-table-column label="时间" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员id" align="center" prop="userId" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="预留字段1" align="center" prop="futureField1" />
      <el-table-column label="预留字段2" align="center" prop="futureField2" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:price:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:price:remove']"
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

    <!-- 添加或修改供应商报价对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="供应商id：外键" prop="supplierId">
          <el-input v-model="form.supplierId" placeholder="请输入供应商id：外键" />
        </el-form-item>
        <el-form-item label="产品id：外键" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品id：外键" />
        </el-form-item>
        <el-form-item label="企业名称中文" prop="supplierNameCn">
          <el-input v-model="form.supplierNameCn" placeholder="请输入企业名称中文" />
        </el-form-item>
        <el-form-item label="企业名称英文" prop="supplierNameEn">
          <el-input v-model="form.supplierNameEn" placeholder="请输入企业名称英文" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="人民币报价" prop="priceRmb">
          <el-input v-model="form.priceRmb" placeholder="请输入人民币报价" />
        </el-form-item>
        <el-form-item label="美金报价" prop="priceUsd">
          <el-input v-model="form.priceUsd" placeholder="请输入美金报价" />
        </el-form-item>
        <el-form-item label="时间" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作员id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入操作员id" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否删除 0:未删除 1:已删除" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入是否删除 0:未删除 1:已删除" />
        </el-form-item>
        <el-form-item label="预留字段1" prop="futureField1">
          <el-input v-model="form.futureField1" placeholder="请输入预留字段1" />
        </el-form-item>
        <el-form-item label="预留字段2" prop="futureField2">
          <el-input v-model="form.futureField2" placeholder="请输入预留字段2" />
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
import { listPrice, getPrice, delPrice, addPrice, updatePrice } from "@/api/system/price";

export default {
  name: "Price",
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
      // 供应商报价表格数据
      priceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierId: null,
        productId: null,
        supplierNameCn: null,
        supplierNameEn: null,
        productName: null,
        priceRmb: null,
        priceUsd: null,
        time: null,
        userId: null,
        remarks: null,
        futureField1: null,
        futureField2: null
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
    /** 查询供应商报价列表 */
    getList() {
      this.loading = true;
      listPrice(this.queryParams).then(response => {
        this.priceList = response.rows;
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
        supplierPriceId: null,
        supplierId: null,
        productId: null,
        supplierNameCn: null,
        supplierNameEn: null,
        productName: null,
        priceRmb: null,
        priceUsd: null,
        time: null,
        userId: null,
        remarks: null,
        delFlag: null,
        futureField1: null,
        futureField2: null
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
      this.ids = selection.map(item => item.supplierPriceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加供应商报价";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const supplierPriceId = row.supplierPriceId || this.ids
      getPrice(supplierPriceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改供应商报价";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.supplierPriceId != null) {
            updatePrice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPrice(this.form).then(response => {
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
      const supplierPriceIds = row.supplierPriceId || this.ids;
      this.$modal.confirm('是否确认删除供应商报价编号为"' + supplierPriceIds + '"的数据项？').then(function() {
        return delPrice(supplierPriceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/price/export', {
        ...this.queryParams
      }, `price_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
