<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="风机编号管理" prop="turbineCode">
        <el-input
          v-model="queryParams.turbineCode"
          placeholder="请输入风机编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报告时间" prop="reportDate">
        <el-date-picker clearable
          v-model="queryParams.reportDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报告时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="报告人" prop="reporterId">
        <el-input
          v-model="queryParams.reporterId"
          placeholder="请输入报告人"
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
          v-hasPermi="['reportSys:historyreport:add']"
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
          v-hasPermi="['reportSys:historyreport:edit']"
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
          v-hasPermi="['reportSys:historyreport:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reportSys:historyreport:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="historyreportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报告ID" align="center" prop="hId" />
      <el-table-column label="风机编号" align="center" prop="turbineCode" />
      <el-table-column label="检测位置" align="center" prop="inspectionPosition" />
      <el-table-column label="检测方式" align="center" prop="inspectionMethod" />
      <el-table-column label="检测时间" align="center" prop="inspectionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报告时间" align="center" prop="reportDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检测人" align="center" prop="inspectorId" />
      <el-table-column label="报告人" align="center" prop="reporterId" />
      <el-table-column label="缺陷问题汇总" align="center" prop="defectSummary" />
      <el-table-column label="检测总结" align="center" prop="inspectionSummary" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reportSys:historyreport:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reportSys:historyreport:remove']"
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

    <!-- 添加或修改历史检测报告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="风机编号" prop="turbineCode">
          <el-input v-model="form.turbineCode" placeholder="请输入风机编号" />
        </el-form-item>
        <el-form-item label="检测时间" prop="inspectionDate">
          <el-date-picker clearable
            v-model="form.inspectionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检测时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报告时间" prop="reportDate">
          <el-date-picker clearable
            v-model="form.reportDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报告时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检测人" prop="inspectorId">
          <el-input v-model="form.inspectorId" placeholder="请输入检测人" />
        </el-form-item>
        <el-form-item label="报告人" prop="reporterId">
          <el-input v-model="form.reporterId" placeholder="请输入报告人" />
        </el-form-item>
        <el-form-item label="缺陷问题汇总" prop="defectSummary">
          <el-input v-model="form.defectSummary" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="检测总结" prop="inspectionSummary">
          <el-input v-model="form.inspectionSummary" type="textarea" placeholder="请输入内容" />
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
import { listHistoryreport, getHistoryreport, delHistoryreport, addHistoryreport, updateHistoryreport } from "@/api/reportSys/historyreport";

export default {
  name: "Historyreport",
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
      // 历史检测报告表格数据
      historyreportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        turbineCode: null,
        inspectionPosition: null,
        inspectionMethod: null,
        reportDate: null,
        reporterId: null,
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
    /** 查询历史检测报告列表 */
    getList() {
      this.loading = true;
      listHistoryreport(this.queryParams).then(response => {
        this.historyreportList = response.rows;
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
        hId: null,
        turbineCode: null,
        inspectionPosition: null,
        inspectionMethod: null,
        inspectionDate: null,
        reportDate: null,
        inspectorId: null,
        reporterId: null,
        defectSummary: null,
        inspectionSummary: null,
        hReserve1: null,
        hReserve2: null,
        hReserve3: null,
        hReserve4: null
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
      this.ids = selection.map(item => item.hId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加历史检测报告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const hId = row.hId || this.ids
      getHistoryreport(hId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改历史检测报告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.hId != null) {
            updateHistoryreport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHistoryreport(this.form).then(response => {
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
      const hIds = row.hId || this.ids;
      this.$modal.confirm('是否确认删除历史检测报告编号为"' + hIds + '"的数据项？').then(function() {
        return delHistoryreport(hIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reportSys/historyreport/export', {
        ...this.queryParams
      }, `historyreport_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
