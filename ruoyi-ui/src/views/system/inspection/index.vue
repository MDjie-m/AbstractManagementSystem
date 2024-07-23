<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商uuid" prop="supplierId">
        <el-input
          v-model="queryParams.supplierId"
          placeholder="请输入供应商uuid"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商名称" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入供应商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考察员id" prop="inspectorId">
        <el-input
          v-model="queryParams.inspectorId"
          placeholder="请输入考察员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考察员姓名" prop="inspectorName">
        <el-input
          v-model="queryParams.inspectorName"
          placeholder="请输入考察员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除标志" prop="deleteFlag">
        <el-input
          v-model="queryParams.deleteFlag"
          placeholder="请输入删除标志"
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
      <el-form-item label="预留字段4" prop="futureField4">
        <el-input
          v-model="queryParams.futureField4"
          placeholder="请输入预留字段4"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段5" prop="futureField5">
        <el-input
          v-model="queryParams.futureField5"
          placeholder="请输入预留字段5"
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
          v-hasPermi="['system:inspection:add']"
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
          v-hasPermi="['system:inspection:edit']"
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
          v-hasPermi="['system:inspection:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:inspection:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inspectionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考察表uuid" align="center" prop="inspectionId" />
      <el-table-column label="供应商uuid" align="center" prop="supplierId" />
      <el-table-column label="供应商名称" align="center" prop="supplierName" />
      <el-table-column label="考察员id" align="center" prop="inspectorId" />
      <el-table-column label="考察员姓名" align="center" prop="inspectorName" />
      <el-table-column label="考察图片保存地址" align="center" prop="imageAddress" />
      <el-table-column label="视频保存地址" align="center" prop="videoAddress" />
      <el-table-column label="考察详情描述" align="center" prop="investigationDetails" />
      <el-table-column label="删除标志" align="center" prop="deleteFlag" />
      <el-table-column label="预留字段1" align="center" prop="futureField1" />
      <el-table-column label="预留字段2" align="center" prop="futureField2" />
      <el-table-column label="预留字段3" align="center" prop="futureField3" />
      <el-table-column label="预留字段4" align="center" prop="futureField4" />
      <el-table-column label="预留字段5" align="center" prop="futureField5" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:inspection:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:inspection:remove']"
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

    <!-- 添加或修改考察情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="供应商uuid" prop="supplierId">
          <el-input v-model="form.supplierId" placeholder="请输入供应商uuid" />
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入供应商名称" />
        </el-form-item>
        <el-form-item label="考察员id" prop="inspectorId">
          <el-input v-model="form.inspectorId" placeholder="请输入考察员id" />
        </el-form-item>
        <el-form-item label="考察员姓名" prop="inspectorName">
          <el-input v-model="form.inspectorName" placeholder="请输入考察员姓名" />
        </el-form-item>
        <el-form-item label="考察图片保存地址" prop="imageAddress">
          <el-input v-model="form.imageAddress" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="视频保存地址" prop="videoAddress">
          <el-input v-model="form.videoAddress" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="考察详情描述" prop="investigationDetails">
          <el-input v-model="form.investigationDetails" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="删除标志" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除标志" />
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
        <el-form-item label="预留字段4" prop="futureField4">
          <el-input v-model="form.futureField4" placeholder="请输入预留字段4" />
        </el-form-item>
        <el-form-item label="预留字段5" prop="futureField5">
          <el-input v-model="form.futureField5" placeholder="请输入预留字段5" />
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
import { listInspection, getInspection, delInspection, addInspection, updateInspection } from "@/api/system/inspection";

export default {
  name: "Inspection",
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
      // 考察情况表格数据
      inspectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierId: null,
        supplierName: null,
        inspectorId: null,
        inspectorName: null,
        imageAddress: null,
        videoAddress: null,
        investigationDetails: null,
        deleteFlag: null,
        futureField1: null,
        futureField2: null,
        futureField3: null,
        futureField4: null,
        futureField5: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        supplierId: [
          { required: true, message: "供应商uuid不能为空", trigger: "blur" }
        ],
        supplierName: [
          { required: true, message: "供应商名称不能为空", trigger: "blur" }
        ],
        inspectorId: [
          { required: true, message: "考察员id不能为空", trigger: "blur" }
        ],
        inspectorName: [
          { required: true, message: "考察员姓名不能为空", trigger: "blur" }
        ],
        imageAddress: [
          { required: true, message: "考察图片保存地址不能为空", trigger: "blur" }
        ],
        videoAddress: [
          { required: true, message: "视频保存地址不能为空", trigger: "blur" }
        ],
        investigationDetails: [
          { required: true, message: "考察详情描述不能为空", trigger: "blur" }
        ],
        deleteFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询考察情况列表 */
    getList() {
      this.loading = true;
      listInspection(this.queryParams).then(response => {
        this.inspectionList = response.rows;
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
        inspectionId: null,
        supplierId: null,
        supplierName: null,
        inspectorId: null,
        inspectorName: null,
        imageAddress: null,
        videoAddress: null,
        investigationDetails: null,
        deleteFlag: null,
        futureField1: null,
        futureField2: null,
        futureField3: null,
        futureField4: null,
        futureField5: null
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
      this.ids = selection.map(item => item.inspectionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加考察情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inspectionId = row.inspectionId || this.ids
      getInspection(inspectionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考察情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.inspectionId != null) {
            updateInspection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInspection(this.form).then(response => {
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
      const inspectionIds = row.inspectionId || this.ids;
      this.$modal.confirm('是否确认删除考察情况编号为"' + inspectionIds + '"的数据项？').then(function() {
        return delInspection(inspectionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/inspection/export', {
        ...this.queryParams
      }, `inspection_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
