<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">

      <el-form-item label="地市名称" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入地市名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="客户名称" prop="custName">
        <el-input
          v-model="queryParams.custName"
          placeholder="请输入客户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="用户号码" prop="accNbr">
        <el-input
          v-model="queryParams.accNbr"
          placeholder="请输入用户号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="制式" prop="netStyle">
        <el-input
          v-model="queryParams.netStyle"
          placeholder="请输入制式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="不使用时长(天)" prop="noUseDur">
        <el-input
          v-model="queryParams.noUseDur"
          placeholder="请输入不使用时长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据日期" prop="yyyymmdd">
        <el-input
          v-model="queryParams.yyyymmdd"
          placeholder="请输入数据日期"
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="nonuseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="省份id" align="center" prop="provId" />
      <el-table-column label="省份名称" align="center" prop="provName" />
      <el-table-column label="地市id" align="center" prop="areaId" />
      <el-table-column label="地市名称" align="center" prop="areaName" />
      <el-table-column label="客户ID" align="center" prop="custId" />
      <el-table-column label="客户名称" align="center" prop="custName" />
      <el-table-column label="产品实例id" align="center" prop="prodInstId" />
      <el-table-column label="用户号码" align="center" prop="accNbr" />
      <el-table-column label="主套餐名称" align="center" prop="mainOfferName" />
      <el-table-column label="制式" align="center" prop="netStyle" />
      <el-table-column label="不使用时长" align="center" prop="noUseDur" />
      <el-table-column label="数据日期" align="center" prop="yyyymmdd" />

      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改套餐长期不使用预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="省份id" prop="provId">
          <el-input v-model="form.provId" placeholder="请输入省份id" />
        </el-form-item>
        <el-form-item label="省份名称" prop="provName">
          <el-input v-model="form.provName" placeholder="请输入省份名称" />
        </el-form-item>
        <el-form-item label="地市id" prop="areaId">
          <el-input v-model="form.areaId" placeholder="请输入地市id" />
        </el-form-item>
        <el-form-item label="地市名称" prop="areaName">
          <el-input v-model="form.areaName" placeholder="请输入地市名称" />
        </el-form-item>
        <el-form-item label="客户ID" prop="custId">
          <el-input v-model="form.custId" placeholder="请输入客户ID" />
        </el-form-item>
        <el-form-item label="客户名称" prop="custName">
          <el-input v-model="form.custName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="产品实例id" prop="prodInstId">
          <el-input v-model="form.prodInstId" placeholder="请输入产品实例id" />
        </el-form-item>
        <el-form-item label="用户号码" prop="accNbr">
          <el-input v-model="form.accNbr" placeholder="请输入用户号码" />
        </el-form-item>
        <el-form-item label="主套餐名称" prop="mainOfferName">
          <el-input v-model="form.mainOfferName" placeholder="请输入主套餐名称" />
        </el-form-item>
        <el-form-item label="制式" prop="netStyle">
          <el-input v-model="form.netStyle" placeholder="请输入制式" />
        </el-form-item>
        <el-form-item label="不使用时长" prop="noUseDur">
          <el-input v-model="form.noUseDur" placeholder="请输入不使用时长" />
        </el-form-item>
        <el-form-item label="数据日期" prop="yyyymmdd">
          <el-input v-model="form.yyyymmdd" placeholder="请输入数据日期" />
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
import { listNonuse, getNonuse, delNonuse, addNonuse, updateNonuse } from "@/api/sim/nonuse";

export default {
  name: "Nonuse",
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
      // 套餐长期不使用预警表格数据
      nonuseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        provId: null,
        provName: null,
        areaId: null,
        areaName: null,
        custId: null,
        custName: null,
        prodInstId: null,
        accNbr: null,
        mainOfferName: null,
        netStyle: null,
        noUseDur: null,
        yyyymmdd: null
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
    /** 查询套餐长期不使用预警列表 */
    getList() {
      this.loading = true;
      listNonuse(this.queryParams).then(response => {
        this.nonuseList = response.rows;
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
        provId: null,
        provName: null,
        areaId: null,
        areaName: null,
        custId: null,
        custName: null,
        prodInstId: null,
        accNbr: null,
        mainOfferName: null,
        netStyle: null,
        noUseDur: null,
        yyyymmdd: null
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
      this.ids = selection.map(item => item.provId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加套餐长期不使用预警";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const provId = row.provId || this.ids
      getNonuse(provId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改套餐长期不使用预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.provId != null) {
            updateNonuse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNonuse(this.form).then(response => {
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
      const provIds = row.provId || this.ids;
      this.$modal.confirm('是否确认删除套餐长期不使用预警编号为"' + provIds + '"的数据项？').then(function() {
        return delNonuse(provIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('sim/nonuse/export', {
        ...this.queryParams
      }, `nonuse_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
