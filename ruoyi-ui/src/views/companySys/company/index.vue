<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司编号" prop="companyCode">
        <el-input
          v-model="queryParams.companyCode"
          placeholder="请输入公司编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
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
          v-hasPermi="['companySys:company:add']"
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
          v-hasPermi="['companySys:company:edit']"
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
          v-hasPermi="['companySys:company:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['companySys:company:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="cId" />
      <el-table-column label="公司编号" align="center" prop="companyCode" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="公司地址" align="center" prop="companyAddress" />
      <el-table-column label="公司邮箱" align="center" prop="companyEmail" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['companySys:company:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['companySys:company:remove']"
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

    <!-- 添加或修改公司管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司编号" prop="companyCode">
          <el-input v-model="form.companyCode" placeholder="请输入公司编号" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司地址" prop="companyAddress">
          <el-input v-model="form.companyAddress" placeholder="请输入公司地址" />
        </el-form-item>
        <el-form-item label="公司邮箱" prop="companyEmail">
          <el-input v-model="form.companyEmail" placeholder="请输入公司邮箱" />
        </el-form-item>
        <el-divider content-position="center">风场管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddWindFarm">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteWindFarm">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="windFarmList" :row-class-name="rowWindFarmIndex" @selection-change="handleWindFarmSelectionChange" ref="windFarm">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="风场编号" prop="windFarmCode" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.windFarmCode" placeholder="请输入风场编号" />
            </template>
          </el-table-column>
          <el-table-column label="风场名称" prop="windFarmName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.windFarmName" placeholder="请输入风场名称" />
            </template>
          </el-table-column>
          <el-table-column label="地址" prop="address" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.address" placeholder="请输入地址" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCompany, getCompany, delCompany, addCompany, updateCompany } from "@/api/companySys/company";

export default {
  name: "Company",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedWindFarm: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 公司管理表格数据
      companyList: [],
      // 风场管理表格数据
      windFarmList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyCode: null,
        companyName: null,
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
    /** 查询公司管理列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then(response => {
        this.companyList = response.rows;
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
        cId: null,
        companyCode: null,
        companyName: null,
        companyAddress: null,
        companyEmail: null,
        cReserve1: null,
        cReserve2: null,
        cReserve3: null,
        cReserve4: null
      };
      this.windFarmList = [];
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
      this.ids = selection.map(item => item.cId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公司管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cId = row.cId || this.ids
      getCompany(cId).then(response => {
        this.form = response.data;
        this.windFarmList = response.data.windFarmList;
        this.open = true;
        this.title = "修改公司管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.windFarmList = this.windFarmList;
          if (this.form.cId != null) {
            updateCompany(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompany(this.form).then(response => {
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
      const cIds = row.cId || this.ids;
      this.$modal.confirm('是否确认删除公司管理编号为"' + cIds + '"的数据项？').then(function() {
        return delCompany(cIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 风场管理序号 */
    rowWindFarmIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 风场管理添加按钮操作 */
    handleAddWindFarm() {
      let obj = {};
      obj.windFarmCode = "";
      obj.windFarmName = "";
      obj.address = "";
      this.windFarmList.push(obj);
    },
    /** 风场管理删除按钮操作 */
    handleDeleteWindFarm() {
      if (this.checkedWindFarm.length == 0) {
        this.$modal.msgError("请先选择要删除的风场管理数据");
      } else {
        const windFarmList = this.windFarmList;
        const checkedWindFarm = this.checkedWindFarm;
        this.windFarmList = windFarmList.filter(function(item) {
          return checkedWindFarm.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleWindFarmSelectionChange(selection) {
      this.checkedWindFarm = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('companySys/company/export', {
        ...this.queryParams
      }, `company_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
