<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">


      <el-form-item label="套餐名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入套餐名称"
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
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['course:order:export']"
        >导出</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:supervision-team:add']"
        >新建套餐</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="packageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="套餐名称" align="center" prop="productName"/>
      <el-table-column label="套餐产品图片" align="center" prop="productPicUrl">
        <template slot-scope="scope">
          <image-preview :src="scope.row.productPicUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="套餐价格(元)" align="center" prop="price"/>
      <el-table-column label="团队督导券张数" align="center" prop="teamSupNum"/>
      <el-table-column label="个人督导券张数" align="center" prop="personSupNum"/>
      <el-table-column label="个人体验券张数" align="center" prop="personExpNum"/>
      <el-table-column label="课程券张数" align="center" prop="courseNum"/>
      <el-table-column label="创建时间" align="center" prop="createTime"/>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            @click="view(scope.row)"
            v-hasPermi="['system:package:info']"
          >详情</el-button>

          <el-button
            size="mini"
            type="text"
            @click="edit(scope.row)"
            v-hasPermi="['system:package:edit']"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            @click="del(scope.row)"
            v-hasPermi="['system:package:delete']"
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

    <!-- 添加-对话框 -->
    <add-form ref="addForm"  @handleOk="getList"/>

    <edit-form ref="editForm"  @handleOk="getList"/>

    <info-form ref="infoForm"  @handleOk="getList"/>

  </div>
</template>

<script>
import { queryPackageList, deletePackage } from "@/api/package/package";
import addForm from "./addForm";
import editForm from "./editForm";
import infoForm from "./info";

export default {
  components: {
    addForm,
    editForm,
    infoForm,
  },
  name: "package",
  dicts: ['supervision_type','supervision_status','week_day'],
  data() {
    var validatePrice = (rule, value, callback) => {
      // 保留两位小数
      const regex = /^[0-9]+(\.[0-9]{1,2})?$/
      if (!regex.test(value)) {
        callback('课程价格为两位小数的数值表示')
      } else {
        callback()
      }
    }
    return {
      // 遮罩层
      loading: false,
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
      // 课程订单表格数据
      packageList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        teamType: null,
        status: null,
        title: null,
        consultantId: null,
      },
      // 套餐师列表
      consultList: [],
    };
  },
  async created() {
    this.getList();
  },
  methods: {

    /** 查询课程订单列表 */
    getList() {
      //this.loading = true;
      queryPackageList(this.queryParams).then(response => {
        this.packageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        teamType: null,
        status: null,
        title: null,
        consultantId: null,
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('course/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.$refs.addForm.init()
    },

    /** 查看套餐 */
    view(row) {
      this.$refs.infoForm.init(row)
    },

    /** 编辑套餐 */
    edit(row) {
      this.$refs.editForm.init(row)
    },

    /** 删除套餐 */
    del(row) {
      this.$modal.confirm('确认删除套餐吗？')
        .then(() => {
          return deletePackage(row.packageId);
        })
        .then(response => {
          this.$modal.msgSuccess("删除成功");
          this.getList();  // Delete successful, now get new list
        })
        .catch(() => {
          // handle any errors here
        });
    }

  }
};
</script>
<style lang="scss" scoped>
.amount {
  ::v-deep {
    .el-form-item__content {
      display: inline-flex;
      align-items: center;
    }
    .el-input__inner {
      width: 150px;
    }
  }
}
</style>
