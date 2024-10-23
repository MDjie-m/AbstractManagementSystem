<template>  <StoreContainer @onStoreChanged="onStoreChanged">
  <div class="container-div">
    <div class=" col-sm-12 search-collapse" v-show="showSearch">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入类型名称" maxlength="10"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    </div>
    <div class="col-sm-12 select-table table-striped">
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['billiard:deskType:add']"
            >新增</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="deskTypeList" @selection-change="handleSelectionChange">
          <el-table-column label="ID" align="center" prop="deskTypeId" />
          <el-table-column label="类型名称" align="center" prop="name" />
          <el-table-column label="排序" align="center" prop="sort" />
          <el-table-column label="创建/更新" align="center" prop="createById" width="250">>
            <template slot-scope="scope">
              <div>
                <span>{{ scope.row.createBy }} </span>
                <span>{{ scope.row.createTime }}</span>
              </div>
              <div>
                <span>{{ scope.row.updateBy }} </span>
                <span>{{ scope.row.updateTime }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:deskType:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:deskType:remove']"
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
    </div>

    <!-- 添加或修改台桌类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入类型名称" />
        </el-form-item>

        <el-form-item label="排序" prop="sort">
          <el-input-number :min="1" :max="999" v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</StoreContainer>
</template>

<script>
import { listDeskType, getDeskType, delDeskType, addDeskType, updateDeskType } from "@/api/billiard/deskType";
import StoreContainer from '@/views/billiard/component/storeContainer.vue'

export default {
  name: "DeskType",
  components: { StoreContainer },
  data() {
    return {
      storeInfo:null,
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
      // 台桌类型表格数据
      deskTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        storeId: null,
        sort: null,
        createById: null,
        updateById: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "类型名称不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "门店不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ]

      }
    };
  },
  created() {

  },

  methods: {
    onStoreChanged(store) {
      this.storeInfo = store;
      this.queryParams.storeId = store?.storeId || -1;
      this.getList();
    },
    /** 查询台桌类型列表 */
    getList() {
      this.loading = true;
      listDeskType(this.queryParams).then(response => {
        this.deskTypeList = response.rows;
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
        deskTypeId: null,
        name: null,
        storeId: this.storeInfo?.storeId || -1,
        sort: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        createById: null,
        updateById: null,
        remark: null
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
      this.ids = selection.map(item => item.deskTypeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if (!this.storeInfo?.storeId) {
        return this.$modal.msgWarning("请选择门店");
      }
      this.reset();
      this.open = true;
      this.title = "添加台桌类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {

      this.reset();
      const deskTypeId = row.deskTypeId || this.ids
      getDeskType(deskTypeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改台桌类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deskTypeId != null) {
            updateDeskType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeskType(this.form).then(response => {
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
      const deskTypeIds = row.deskTypeId || this.ids;
      this.$modal.confirm('是否确认删除台桌类型编号为"' + deskTypeIds + '"的数据项？').then(function() {
        return delDeskType(deskTypeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/deskType/export', {
        ...this.queryParams
      }, `deskType_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
