<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="风机编号" prop="turbineCode">
        <el-input
          v-model="queryParams.turbineCode"
          placeholder="请输入风机编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整机厂家" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入整机厂家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="叶片厂家" prop="bladeManufacturer">
        <el-input
          v-model="queryParams.bladeManufacturer"
          placeholder="请输入叶片厂家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="叶片型号" prop="bladeModel">
        <el-input
          v-model="queryParams.bladeModel"
          placeholder="请输入叶片型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检修单位" prop="maintenanceUnit">
        <el-input
          v-model="queryParams.bladeManufacturer"
          placeholder="请输入检修单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="叶片厂家" prop="bladeManufacturer">
        <el-input
          v-model="queryParams.bladeManufacturer"
          placeholder="请输入叶片厂家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="叶片型号" prop="bladeModel">
        <el-input
          v-model="queryParams.bladeModel"
          placeholder="请输入叶片型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检修单位" prop="maintenanceUnit">
        <el-input
          v-model="queryParams.maintenanceUnit"
          placeholder="请输入检修单位"
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
          v-hasPermi="['windSys:wind:add']"
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
          v-hasPermi="['windSys:wind:edit']"
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
          v-hasPermi="['windSys:wind:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['windSys:wind:export']"
        >导出</el-button>
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport2"
          v-hasPermi="['windSys:wind:export']"
        >导出Word</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="windList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="风机ID" align="center" prop="wId" />
      <el-table-column label="风机编号" align="center" prop="turbineCode" />
      <el-table-column label="风机编号照片" align="center" prop="turbineCodePhoto" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.turbineCodePhoto" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="整机厂家" align="center" prop="manufacturer" />
      <el-table-column label="叶片厂家" align="center" prop="bladeManufacturer" />
      <el-table-column label="叶片型号" align="center" prop="bladeModel" />
      <el-table-column label="巡检时间" align="center" prop="inspectionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检修单位" align="center" prop="maintenanceUnit" />
      <el-table-column label="巡检人员" align="center" prop="inspectionStaff" />
      <el-table-column label="录入人员" align="center" prop="entryStaff" />
      <el-table-column label="公司" align="center" prop="company" />
      <el-table-column label="风场" align="center" prop="windFarm" />
      <el-table-column label="叶片1_编号" align="center" prop="blade1Code" />
      <el-table-column label="叶片1_图片url" align="center" prop="blade1PhotoUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.blade1PhotoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="叶片2_编号" align="center" prop="blade2Code" />
      <el-table-column label="叶片2_图片url" align="center" prop="blade2PhotoUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.blade1PhotoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="叶片2_编号" align="center" prop="blade2Code" />
      <el-table-column label="叶片2_图片url" align="center" prop="blade2PhotoUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.blade2PhotoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="叶片3_编号" align="center" prop="blade3Code" />
      <el-table-column label="叶片3_图片url" align="center" prop="blade3PhotoUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.blade3PhotoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['windSys:wind:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['windSys:wind:remove']"
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

    <!-- 添加或修改风机管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="风机编号" prop="turbineCode">
          <el-input v-model="form.turbineCode" placeholder="请输入风机编号" />
        </el-form-item>
        <el-form-item label="风机编号照片" prop="turbineCodePhoto">
          <image-upload v-model="form.turbineCodePhoto"/>
        </el-form-item>
        <el-form-item label="整机厂家" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入整机厂家" />
        </el-form-item>
        <el-form-item label="叶片厂家" prop="bladeManufacturer">
          <el-input v-model="form.bladeManufacturer" placeholder="请输入叶片厂家" />
        </el-form-item>
        <el-form-item label="整机厂家" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入整机厂家" />
        </el-form-item>
        <el-form-item label="叶片厂家" prop="bladeManufacturer">
          <el-input v-model="form.bladeManufacturer" placeholder="请输入叶片厂家" />
        </el-form-item>
        <el-form-item label="叶片型号" prop="bladeModel">
          <el-input v-model="form.bladeModel" placeholder="请输入叶片型号" />
        </el-form-item>
        <el-form-item label="巡检时间" prop="inspectionDate">
          <el-date-picker clearable
                          v-model="form.inspectionDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择巡检时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检修单位" prop="maintenanceUnit">
          <el-input v-model="form.maintenanceUnit" placeholder="请输入检修单位" />
        </el-form-item>
        <el-form-item label="巡检人员" prop="inspectionStaff">
          <el-input v-model="form.inspectionStaff" placeholder="请输入巡检人员" />
        </el-form-item>
        <el-form-item label="录入人员" prop="entryStaff">
          <el-input v-model="form.entryStaff" placeholder="请输入录入人员" />
        </el-form-item>
        <el-form-item label="叶片1_编号" prop="blade1Code">
          <el-input v-model="form.blade1Code" placeholder="请输入叶片1_编号" />
        </el-form-item>
        <el-form-item label="叶片1_图片url" prop="blade1PhotoUrl">
          <image-upload v-model="form.blade1PhotoUrl"/>
        </el-form-item>
        <el-form-item label="叶片2_编号" prop="blade2Code">
          <el-input v-model="form.blade2Code" placeholder="请输入叶片2_编号" />
        </el-form-item>
        <el-form-item label="叶片2_图片url" prop="blade2PhotoUrl">
          <image-upload v-model="form.blade2PhotoUrl"/>
        </el-form-item>
        <el-form-item label="叶片3_编号" prop="blade3Code">
          <el-input v-model="form.blade3Code" placeholder="请输入叶片3_编号" />
        </el-form-item>
        <el-form-item label="叶片3_图片url" prop="blade3PhotoUrl">
          <image-upload v-model="form.blade3PhotoUrl"/>
        </el-form-item>
        <el-divider content-position="center">叶片管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBladePart">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBladePart">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="bladePartList" :row-class-name="rowBladePartIndex" @selection-change="handleBladePartSelectionChange" ref="bladePart">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="叶片部位编号" prop="bladePartCode" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bladePartCode" placeholder="请输入叶片部位编号" />
            </template>
          </el-table-column>
          <el-table-column label="叶片_编号" prop="bladeCode" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bladeCode" placeholder="请输入叶片_编号" />
            </template>
          </el-table-column>
          <el-table-column label="叶片部位名称" prop="bladePartName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bladePartName" placeholder="请输入叶片部位名称" />
            </template>
          </el-table-column>
          <el-table-column label="叶片部位检测结果" prop="bladePartInspectionResult" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bladePartInspectionResult" placeholder="请输入叶片部位检测结果" />
            </template>
          </el-table-column>
          <el-table-column label="施工区域" prop="constructionArea" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.constructionArea" placeholder="请输入施工区域" />
            </template>
          </el-table-column>
          <el-table-column label="叶片内外" prop="bladeInsideOutside" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.bladeInsideOutside" placeholder="请选择叶片内外">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="上传时间" prop="uploadTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.uploadTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择上传时间" />
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
import {listWind, delWind, addWind, updateWind, getReportManager} from "@/api/reportSys/reportmanager";
import {getWind} from "@/api/windSys/wind";
export default {
  name: "Wind",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBladePart: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 风机管理表格数据
      windList: [],
      // 叶片管理表格数据
      bladePartList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        turbineCode: null,
        manufacturer: null,
        bladeManufacturer: null,
        bladeModel: null,
        maintenanceUnit: null,
        company: null,
        windFarm: null,
      },
      // 查询参数
      queryParams2: {
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
    /** 查询风机管理列表 */
    getList() {
      this.loading = true;
      listWind(this.queryParams).then(response => {
        this.windList = response.rows;
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
        wId: null,
        turbineCode: null,
        turbineCodePhoto: null,
        manufacturer: null,
        bladeManufacturer: null,
        bladeModel: null,
        inspectionDate: null,
        maintenanceUnit: null,
        inspectionStaff: null,
        entryStaff: null,
        company: null,
        windFarm: null,
        blade1Code: null,
        blade1PhotoUrl: null,
        blade2Code: null,
        blade2PhotoUrl: null,
        blade3Code: null,
        blade3PhotoUrl: null,
        wReserve1: null,
        wReserve2: null,
        wReserve3: null,
        wReserve4: null,
        wReserve5: null,
        wReserve6: null,
        wReserve7: null
      };
      this.bladePartList = [];
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
      this.ids = selection.map(item => item.wId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加风机管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wId = row.wId || this.ids
      getWind(wId).then(response => {
        this.form = response.data;
        this.bladePartList = response.data.bladePartList;
        this.open = true;
        this.title = "修改风机管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.bladePartList = this.bladePartList;
          if (this.form.wId != null) {
            updateWind(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWind(this.form).then(response => {
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
      const wIds = row.wId || this.ids;
      this.$modal.confirm('是否确认删除风机管理编号为"' + wIds + '"的数据项？').then(function() {
        return delWind(wIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 叶片管理序号 */
    rowBladePartIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 叶片管理添加按钮操作 */
    handleAddBladePart() {
      let obj = {};
      obj.bladePartCode = "";
      obj.bladeCode = "";
      obj.bladePartName = "";
      obj.bladePartPhotoUrl = "";
      obj.bladePartInspectionResult = "";
      obj.constructionArea = "";
      obj.constructionContent = "";
      obj.bladeInsideOutside = "";
      obj.uploadTime = "";
      this.bladePartList.push(obj);
    },
    /** 叶片管理删除按钮操作 */
    handleDeleteBladePart() {
      if (this.checkedBladePart.length == 0) {
        this.$modal.msgError("请先选择要删除的叶片管理数据");
      } else {
        const bladePartList = this.bladePartList;
        const checkedBladePart = this.checkedBladePart;
        this.bladePartList = bladePartList.filter(function(item) {
          return checkedBladePart.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleBladePartSelectionChange(selection) {
      this.checkedBladePart = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('windSys/wind/export', {
        ...this.queryParams
      }, `wind_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handleExport2(row) {
      const wIds = row.wId || this.ids;
      getReportManager(wIds).then(response => {
        console.log(response.code)
      })
    },
    /** 删除按钮操作 */
    handleExQuery(row) {
      const wIds = row.wId || this.ids;
      this.$modal.confirm('是否确认导出风机管理编号为"' + wIds + '"的数据项？').then(function() {
        return delWind(wIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }

  }
};
</script>
