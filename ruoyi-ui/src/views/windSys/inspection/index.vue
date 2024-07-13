<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属风机id" prop="turbineCode">
        <el-input
          v-model="queryParams.turbineCode"
          placeholder="请输入所属风机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="叶片_编号" prop="bladeCode">
        <el-input
          v-model="queryParams.bladeCode"
          placeholder="请输入叶片_编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="叶片部位名称" prop="bladePartName">
        <el-input
          v-model="queryParams.bladePartName"
          placeholder="请输入叶片部位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上传时间">
        </el-date-picker>
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
          v-hasPermi="['windSys:part:add']"
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
          v-hasPermi="['windSys:part:edit']"
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
          v-hasPermi="['windSys:part:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['windSys:part:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="partList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="bpId" />
      <el-table-column label="叶片部位编号" align="center" prop="bladePartCode" />
      <el-table-column label="所属风机id" align="center" prop="turbineCode" />
      <el-table-column label="叶片_编号" align="center" prop="bladeCode" />
      <el-table-column label="叶片部位名称" align="center" prop="bladePartName" />
      <el-table-column label="叶片部位图片url" align="center" prop="bladePartPhotoUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.bladePartPhotoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="叶片部位检测结果" align="center" prop="bladePartInspectionResult" />
      <el-table-column label="施工区域" align="center" prop="constructionArea" />
      <el-table-column label="施工内容" align="center" prop="constructionContent" />
      <el-table-column label="叶片内外" align="center" prop="bladeInsideOutside" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['windSys:part:edit']"
          >检测</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['windSys:part:remove']"
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

    <!-- 添加或修改叶片管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="叶片部位编号" prop="bladePartCode">
          <el-input v-model="form.bladePartCode" placeholder="请输入叶片部位编号" />
        </el-form-item>
        <el-form-item label="所属风机id" prop="turbineCode">
          <el-input v-model="form.turbineCode" placeholder="请输入所属风机id" />
        </el-form-item>
        <el-form-item label="叶片_编号" prop="bladeCode">
          <el-input v-model="form.bladeCode" placeholder="请输入叶片_编号" />
        </el-form-item>
        <el-form-item label="叶片部位名称" prop="bladePartName">
          <el-input v-model="form.bladePartName" placeholder="请输入叶片部位名称" />
        </el-form-item>
        <el-form-item label="叶片部位图片url" prop="bladePartPhotoUrl">
          <image-upload v-model="form.bladePartPhotoUrl"/>
        </el-form-item>
        <el-form-item label="叶片部位检测结果" prop="bladePartInspectionResult">
          <el-input v-model="form.bladePartInspectionResult" placeholder="请输入叶片部位检测结果" />
        </el-form-item>
        <el-form-item label="施工区域" prop="constructionArea">
          <el-input v-model="form.constructionArea" placeholder="请输入施工区域" />
        </el-form-item>
        <el-form-item label="施工内容">
          <editor v-model="form.constructionContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker clearable
            v-model="form.uploadTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上传时间">
          </el-date-picker>
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
import { listPart, getPart, delPart, addPart, updatePart } from "@/api/windSys/part";

export default {
  name: "Part",
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
      // 叶片管理表格数据
      partList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        turbineCode: null,
        bladeCode: null,
        bladePartName: null,
        constructionArea: null,
        bladeInsideOutside: null,
        uploadTime: null,
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
    /** 查询叶片管理列表 */
    getList() {
      this.loading = true;
      listPart(this.queryParams).then(response => {
        this.partList = response.rows;
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
        bpId: null,
        bladePartCode: null,
        turbineCode: null,
        bladeCode: null,
        bladePartName: null,
        bladePartPhotoUrl: null,
        bladePartInspectionResult: null,
        constructionArea: null,
        constructionContent: null,
        bladeInsideOutside: null,
        uploadTime: null,
        bpReserve1: null,
        bpReserve2: null,
        bpReserve3: null,
        bpReserve4: null
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
      this.ids = selection.map(item => item.bpId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加叶片管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bpId = row.bpId || this.ids
      getPart(bpId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改叶片管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bpId != null) {
            updatePart(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPart(this.form).then(response => {
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
      const bpIds = row.bpId || this.ids;
      this.$modal.confirm('是否确认删除叶片管理编号为"' + bpIds + '"的数据项？').then(function() {
        return delPart(bpIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('windSys/part/export', {
        ...this.queryParams
      }, `part_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
