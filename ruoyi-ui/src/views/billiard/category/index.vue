<template>
  <StoreContainer @onStoreChanged="onStoreChanged">


  <div class="container-div">
    <div class=" col-sm-12 search-collapse" v-show="showSearch">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="商品分类名称" prop="goodsCategoryName">
        <el-input
          v-model="queryParams.goodsCategoryName"
          placeholder="请输入商品分类名称" maxlength="40"
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
              v-hasPermi="['billiard:category:add']"
            >新增</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
          <el-table-column label="ID" align="center" prop="goodsCategoryId" />
          <el-table-column label="商品分类名称" align="center" prop="goodsCategoryName" />
          <el-table-column label="门店" align="center" prop="storeId" >
            <template>
              <el-tag>       {{ storeInfo?storeInfo.storeName:''}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="排序" align="center" prop="sort" />
          <el-table-column label="创建/更新" align="center" prop="updateTime" width="250">
            <template slot-scope="scope">
              <div>
                <span>{{scope.row.createBy}}&nbsp;</span>
                <span>{{ parseTime(scope.row.createTime ) }}</span>
              </div>
              <div>
                <span>{{scope.row.updateBy}} &nbsp;</span>
                <span>{{ parseTime(scope.row.updateTime ) }}</span>
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
                v-hasPermi="['billiard:category:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:category:remove']"
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

    <!-- 添加或修改商品分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="商品分类名称" prop="goodsCategoryName">
          <el-input v-model="form.goodsCategoryName" placeholder="请输入商品分类名称"  maxlength="40"/>
        </el-form-item>

        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" placeholder="请输入排序"  :min="1" :max="99999" />
        </el-form-item>
        <el-form-item label="门店"  prop="storeId">
          <el-tag>       {{ storeInfo?storeInfo.storeName:''}}</el-tag>
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
import { listCategory, getCategory, delCategory, addCategory, updateCategory } from "@/api/billiard/category";
import StoreContainer from '@/views/billiard/component/storeContainer.vue'

export default {
  name: "Category",
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
      // 商品分类表格数据
      categoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsCategoryName: null,
        storeId: null,
        sort: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsCategoryName: [
          { required: true, message: "商品分类名称不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "门店不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
  },

  methods: {
    onStoreChanged(store){
      this.storeInfo=store;
      this.queryParams.storeId=store?.storeId||-1;
      this.getList();
    },
    /** 查询商品分类列表 */
    getList() {
      this.loading = true;
      listCategory(this.queryParams).then(response => {
        this.categoryList = response.rows;
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
        goodsCategoryId: null,
        goodsCategoryName: null,
        storeId: null,
        sort: null,
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
      this.ids = selection.map(item => item.goodsCategoryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if(!this.storeInfo?.storeId){
        return  this.$modal.msgWarning("请选择门店");
      }
      this.reset();
      this.open = true;
      this.form.storeId=this.storeInfo?.storeId;
      this.title = "添加商品分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodsCategoryId = row.goodsCategoryId || this.ids
      getCategory(goodsCategoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsCategoryId != null) {
            updateCategory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCategory(this.form).then(response => {
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
      const goodsCategoryIds = row.goodsCategoryId || this.ids;
      this.$modal.confirm('是否确认删除商品分类编号为"' + goodsCategoryIds + '"的数据项？').then(function() {
        return delCategory(goodsCategoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/category/export', {
        ...this.queryParams
      }, `category_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
