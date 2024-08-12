<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关键字" prop="plaintiff">
        <el-input
          v-model="queryParams.plaintiff"
          placeholder="请输入关键字"
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
          v-hasPermi="['caseinfo:manage:add']"
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
          v-hasPermi="['caseinfo:manage:edit']"
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
          v-hasPermi="['caseinfo:manage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['caseinfo:manage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="manageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案件ID" align="center" prop="caseId" />
      <el-table-column label="原告" align="center" prop="plaintiff" />
      <el-table-column label="被告" align="center" prop="defendant" />
      <el-table-column label="被告手机号" align="center" prop="defendantPhone" />
      <el-table-column label="法院" align="center" prop="court" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="合作公司" align="center" prop="cooperativeCompany" />
      <el-table-column label="承办律师A" align="center" prop="undertakeLawyerA" />
      <el-table-column label="承办律师B" align="center" prop="undertakeLawyerB" />
      <el-table-column label="律师助理A" align="center" prop="paralegalA" />
      <el-table-column label="律师助理B" align="center" prop="paralegalB" />
      <el-table-column label="调解员" align="center" prop="mediator" />
      <el-table-column label="一审文书" align="center" prop="firstInstanceFileids" />
      <el-table-column label="二审文书" align="center" prop="secondInstanceFileids" />
      <el-table-column label="首次执行文书" align="center" prop="firstExecutionFileids" />
      <el-table-column label="恢复执行文书" align="center" prop="secondExecutionFileids" />
      <el-table-column label="认领状态" align="center" prop="claimStatus" />
      <el-table-column label="是否退回" align="center" prop="isReturn" />
      <el-table-column label="欠款总额" align="center" prop="arrears" />
      <el-table-column label="已还款" align="center" prop="arrearsYes" />
      <el-table-column label="未还款" align="center" prop="arrearsNo" />
      <el-table-column label="案件状态" align="center" prop="caseStatus" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="数据权限查看人" align="center" prop="dataRoleUserids" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['caseinfo:manage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['caseinfo:manage:remove']"
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

    <!-- 添加或修改案件信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="原告" prop="plaintiff">
          <el-input v-model="form.plaintiff" placeholder="请输入原告" />
        </el-form-item>
        <el-form-item label="被告" prop="defendant">
          <el-input v-model="form.defendant" placeholder="请输入被告" />
        </el-form-item>
        <el-form-item label="被告手机号" prop="defendantPhone">
          <el-input v-model="form.defendantPhone" placeholder="请输入被告手机号" />
        </el-form-item>
        <el-form-item label="法院" prop="court">
          <el-input v-model="form.court" placeholder="请输入法院" />
        </el-form-item>
        <el-form-item label="案号" prop="caseNo">
          <el-input v-model="form.caseNo" placeholder="请输入案号" />
        </el-form-item>
        <el-form-item label="合作公司" prop="cooperativeCompany">
          <el-input v-model="form.cooperativeCompany" placeholder="请输入合作公司" />
        </el-form-item>
        <el-form-item label="承办律师A" prop="undertakeLawyerA">
          <el-input v-model="form.undertakeLawyerA" placeholder="请输入承办律师A" />
        </el-form-item>
        <el-form-item label="下拉选择" prop="undertakeLawyerA">
          <el-select v-model="form.undertakeLawyerA" placeholder="请选择下拉选择" clearable :style="{width: '100%'}">
            <el-option v-for="(item, index) in undertakeLawyers" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>



        <el-form-item label="承办律师B" prop="undertakeLawyerB">
          <el-input v-model="form.undertakeLawyerB" placeholder="请输入承办律师B" />
        </el-form-item>
        <el-form-item label="律师助理A" prop="paralegalA">
          <el-input v-model="form.paralegalA" placeholder="请输入律师助理A" />
        </el-form-item>
        <el-form-item label="律师助理B" prop="paralegalB">
          <el-input v-model="form.paralegalB" placeholder="请输入律师助理B" />
        </el-form-item>
        <el-form-item label="调解员" prop="mediator">
          <el-input v-model="form.mediator" placeholder="请输入调解员" />
        </el-form-item>
        <el-form-item label="一审文书" prop="firstInstanceFileids">
          <el-input v-model="form.firstInstanceFileids" placeholder="请输入一审文书" />
        </el-form-item>
        <el-form-item label="二审文书" prop="secondInstanceFileids">
          <el-input v-model="form.secondInstanceFileids" placeholder="请输入二审文书" />
        </el-form-item>
        <el-form-item label="首次执行文书" prop="firstExecutionFileids">
          <el-input v-model="form.firstExecutionFileids" placeholder="请输入首次执行文书" />
        </el-form-item>
        <el-form-item label="恢复执行文书" prop="secondExecutionFileids">
          <el-input v-model="form.secondExecutionFileids" placeholder="请输入恢复执行文书" />
        </el-form-item>
        <el-form-item label="欠款总额" prop="arrears">
          <el-input v-model="form.arrears" placeholder="请输入欠款总额" />
        </el-form-item>
        <el-form-item label="已还款" prop="arrearsYes">
          <el-input v-model="form.arrearsYes" placeholder="请输入已还款" />
        </el-form-item>
        <el-form-item label="未还款" prop="arrearsNo">
          <el-input v-model="form.arrearsNo" placeholder="请输入未还款" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import {listManage, getManage, delManage, addManage, updateManage, getUndertakeLawyers} from "@/api/caseinfo/manage";

export default {
  name: "Manage",
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
      // 案件信息表格数据
      manageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        plaintiff: null,
        defendant: null,
        defendantPhone: null,
        court: null,
        caseNo: null,
        cooperativeCompany: null,
        undertakeLawyerA: null,
        undertakeLawyerB: null,
        paralegalA: null,
        paralegalB: null,
        mediator: null,
        firstInstanceFileids: null,
        secondInstanceFileids: null,
        firstExecutionFileids: null,
        secondExecutionFileids: null,
        claimStatus: null,
        isReturn: null,
        arrears: null,
        arrearsYes: null,
        arrearsNo: null,
        caseStatus: null,
        dataRoleUserids: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      undertakeLawyers: [],
    };
  },
  created() {
    this.getList();
    this.getUndertakeLawyers();
  },
  methods: {
    /** 查询案件信息列表 */
    getList() {
      this.loading = true;
      listManage(this.queryParams).then(response => {
        this.manageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    getUndertakeLawyers(){
      this.loading = true;
      getUndertakeLawyers().then(response => {
        response.rows.forEach(item => {
          this.undertakeLawyers.push({
            "label": item.remark,
            "value": item.testId
          })
        })
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
        caseId: null,
        plaintiff: null,
        defendant: null,
        defendantPhone: null,
        court: null,
        caseNo: null,
        cooperativeCompany: null,
        undertakeLawyerA: null,
        undertakeLawyerB: null,
        paralegalA: null,
        paralegalB: null,
        mediator: null,
        firstInstanceFileids: null,
        secondInstanceFileids: null,
        firstExecutionFileids: null,
        secondExecutionFileids: null,
        claimStatus: null,
        isReturn: null,
        arrears: null,
        arrearsYes: null,
        arrearsNo: null,
        caseStatus: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        dataRoleUserids: null
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
      this.ids = selection.map(item => item.caseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加案件信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const caseId = row.caseId || this.ids
      getManage(caseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改案件信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.caseId != null) {
            updateManage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManage(this.form).then(response => {
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
      const caseIds = row.caseId || this.ids;
      this.$modal.confirm('是否确认删除案件信息编号为"' + caseIds + '"的数据项？').then(function() {
        return delManage(caseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('caseinfo/manage/export', {
        ...this.queryParams
      }, `manage_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
