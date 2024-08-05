<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="面向用户类型" prop="userType">
        <el-select v-model="queryParams.userType"  clearable>
          <el-option
            v-for="dict in dict.type.user_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="优惠券模版名称" prop="couponName">
        <el-input
          v-model="queryParams.couponName"
          placeholder="请输入优惠券模版名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="券类型" prop="couponType">
        <el-select v-model="queryParams.couponType"  clearable>
          <el-option
            v-for="dict in dict.type.coupon_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="服务类型" prop="serverType">
        <el-select v-model="queryParams.serverType"  clearable>
          <el-option
            v-for="dict in dict.type.server_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:template:add']"
        >新增</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:template:remove']"
        >删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="面向用户类型" align="center" prop="userType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_type" :value="scope.row.userType"/>
        </template>
      </el-table-column>

      <el-table-column label="优惠券模版名称" align="center" prop="couponName" />
      <el-table-column label="发行上限数量" align="center" prop="totalNum" />
      <el-table-column label="已发行数量" align="center" prop="usedNum" />
      <el-table-column label="券类型" align="center" prop="couponType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.coupon_type" :value="scope.row.couponType"/>
        </template>
      </el-table-column>
      <el-table-column label="服务类型" align="center" prop="serverType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.server_type" :value="scope.row.serverType"/>
        </template>
      </el-table-column>
      <el-table-column label="最大抵扣金额" align="center" prop="maxDeductionPrice" />
      <el-table-column label="折扣比例 (0.8即8折)" align="center" prop="discountRate" />
      <el-table-column label="有效天数" align="center" prop="validityDay" />
      <el-table-column label="模版状态" align="center" prop="templateStatus" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.templateStatus"/>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:template:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:template:remove']"
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

    <!-- 添加或修改优惠券模版对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">

        <el-form-item label="模版状态" prop="templateStatus" >
          <el-select v-model="form.templateStatus" clearable >
            <el-option
              v-for="item in dict.type.sys_normal_disable"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="面向用户类型" prop="userType" >
          <el-select v-model="form.userType" clearable :disabled="isEdit">
            <el-option
              v-for="item in dict.type.user_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="优惠券模版名称" prop="couponName">
          <el-input v-model="form.couponName" placeholder="请输入优惠券模版名称" />
        </el-form-item>

        <el-form-item label="优惠券类型" prop="couponType" >
          <el-select v-model="form.couponType" clearable @change="changeCouponType" :disabled="isEdit">
            <el-option
              v-for="item in dict.type.coupon_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="最大抵扣金额" prop="maxDeductionPrice"  v-show="form.couponType == 1">
          <el-input v-model="form.maxDeductionPrice" placeholder="请输入最大抵扣金额" :disabled="isEdit"/>
        </el-form-item>
        <el-form-item label="折扣比例" prop="discountRate" v-show="form.couponType == 2">
          <el-input v-model="form.discountRate" placeholder="(0.8即8折)" :disabled="isEdit" />
        </el-form-item>

        <el-form-item label="服务类型" prop="serverType" >
          <el-select v-model="form.serverType" clearable :disabled="isEdit">
            <el-option
              v-for="item in dict.type.server_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="发行上限数量" prop="totalNum">
          <el-input v-model="form.totalNum" placeholder="请输入发行上限数量" />
        </el-form-item>

        <el-form-item label="有效天数" prop="validityDay">
          <el-input v-model="form.validityDay" placeholder="请输入有效天数" />
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
import { listTemplate, getTemplate, delTemplate, addTemplate, updateTemplate } from "@/api/marketing/coupon";

export default {
  name: "Template",
  dicts: ['coupon_type','server_type','user_type','sys_normal_disable'],
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
      // 优惠券模版表格数据
      templateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      isEdit:true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userType: null,
        couponName: null,
        totalNum: null,
        usedNum: null,
        couponType: null,
        serverType: null,
        maxDeductionPrice: null,
        discountRate: null,
        validityDay: null,
        templateStatus: null,
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
    changeCouponType(){
      if (this.form.couponType == 1){//抵扣券
          this.form.discountRate = null;
      }
      if (this.form.couponType == 2){//折扣券
        this.form.maxDeductionPrice = null;
      }
    },

    /** 查询优惠券模版列表 */
    getList() {
      this.loading = true;
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows;
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
        id: null,
        userType: null,
        couponName: null,
        totalNum: null,
        usedNum: null,
        couponType: null,
        serverType: null,
        maxDeductionPrice: null,
        discountRate: null,
        validityDay: null,
        templateStatus: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isEdit = false;
      this.open = true;
      this.title = "添加优惠券模版";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTemplate(id).then(response => {
        this.form = response.data;
        this.isEdit = true;
        this.open = true;
        this.title = "修改优惠券模版";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userType == 1 &&  ['21', '22', '23', '24'].includes(this.form.serverType)){
            this.$modal.msgWarning("[来访者用户]的服务类型仅可选[倾诉/咨询/测评/来访者课程],请重新选择")
          }
          if (this.form.userType == 2 &&  ['11', '12', '13', '14'].includes(this.form.serverType)){
            this.$modal.msgWarning("[咨询师用户]的服务类型仅可选[团队督导/个人督导/个人体验/咨询师课程],请重新选择")
          }
          if (this.form.id != null) {
            updateTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTemplate(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除数据项？').then(function() {
        return delTemplate(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/template/export', {
        ...this.queryParams
      }, `template_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
