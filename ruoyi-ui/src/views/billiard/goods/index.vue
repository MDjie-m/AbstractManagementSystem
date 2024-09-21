<template>
  <StoreContainer @onStoreChanged="onStoreChanged">

  <div class="container-div">
    <div class=" col-sm-12 search-collapse" v-show="showSearch">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品条码" prop="barcode">
        <el-input
          v-model="queryParams.barcode"
          placeholder="请输入商品条码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品分类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in categoryList"
            :key="dict.goodsCategoryId"
            :label="dict.goodsCategoryName"
            :value="dict.goodsCategoryId"
          />
        </el-select>
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
              v-hasPermi="['billiard:goods:add']"
            >新增</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
          <el-table-column label="Id" align="center" prop="goodsId" />
          <el-table-column label="商品名称" align="center" prop="goodsName" />
          <el-table-column label="商品图片" align="center" prop="goodsImg" width="100">
            <template slot-scope="scope">
              <image-preview :src="scope.row.goodsImg" :width="50" :height="50"/>
            </template>
          </el-table-column>
          <el-table-column label="价格" align="center" prop="price" />

          <el-table-column label="商品条码" align="center" prop="barcode" />
          <el-table-column label="商品分类" align="center" prop="categoryName" />
          <el-table-column label="是否上架销售" align="center" prop="sell" >
            <template v-slot:default="scope">
              {{scope.row.sell?'是':'否'}}
            </template>
          </el-table-column>
          <el-table-column label="排序" align="center" prop="sort" />
          <el-table-column label="门店" align="center" prop="storeId" >
            <template>
              <el-tag>       {{ storeInfo?storeInfo.storeName:''}}</el-tag>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:goods:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:goods:remove']"
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

    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form"  class="custom-form" :rules="rules" label-width="80px">

        <el-form-item label="门店" prop="storeId">
          <el-tag>       {{ storeInfo?storeInfo.storeName:''}}</el-tag>
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称"  maxlength="40" />
        </el-form-item>


        <el-form-item label="商品条码" prop="barcode">
          <el-input v-model="form.barcode" placeholder="请输入商品条码"  maxlength="40"/>
        </el-form-item>
        <el-form-item label="商品分类" prop="categoryId">
          <el-select v-model="form.categoryId" placeholder="请选择类型" >
            <el-option
              v-for="dict in categoryList"
              :key="dict.goodsCategoryId"
              :label="dict.goodsCategoryName"
              :value="dict.goodsCategoryId"
            />
          </el-select>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="价格" prop="price">
              <el-input-number controls-position="right"  v-model="form.price"    :precision="2" :step="0.1" :max="100" :min="0.00"  ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序" prop="sort">
              <el-input-number controls-position="right" v-model="form.sort" placeholder="请输入排序"  :min="1" :max="99999" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="禁止折扣" prop="discountDisable" >
          <el-radio-group v-model="form.discountDisable">
            <el-radio-button     :label="true"   >
              是
            </el-radio-button>
            <el-radio-button     :label="false"   >
              否
            </el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否销售" prop="sell">
          <el-tooltip :content="'选择[否]，商品不会在收银台展示'  " placement="top">
          <el-switch
            v-model="form.sell"
            active-text="是"
            inactive-text="否">
          </el-switch>
          </el-tooltip>
        </el-form-item>

        <el-form-item label="商品图片" prop="goodsImg">
          <image-upload v-model="form.goodsImg" :limit="1" />
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
import { listGoods, getGoods, delGoods, addGoods, updateGoods } from "@/api/billiard/goods";
import StoreContainer from '@/views/billiard/component/storeContainer.vue'
import { listAllCategory } from '@/api/billiard/category'

export default {
  name: "Goods",
  components: { StoreContainer },
  data() {
    return {
      categoryList:[],
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
      // 商品表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        barcode: null,
        categoryId: null,
        sell: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeId: [
          { required: true, message: "门店不能为空", trigger: "blur" }
        ],
        goodsName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],

        goodsImg: [
          { required: true, message: "商品图片不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "商品分类不能为空", trigger: "blur" }
        ],
        sell: [
          { required: true, message: "是否上架销售不能为空", trigger: "change" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        discountDisable: [
          { required: true, message: "价格不能为空", trigger: "blur" }
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
      this.getCategories();
    },
    getCategories() {
      listAllCategory({ storeId:this.storeInfo?.storeId??-1 }).then(response => {
        this.categoryList = response.data||[];

      });
    },
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
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
        discountDisable:false,
        goodsId: null,
        storeId: this.storeInfo?.storeId,
        goodsName: null,
        goodsImg:null,
        sort: null,
        barcode: null,
        categoryId: null,
        sell: true,
        price: null,
        remark: null,
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
      this.ids = selection.map(item => item.goodsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if(!this.storeInfo?.storeId){
        return  this.$modal.msgWarning("请选择门店");
      }
      this.getCategories();
      this.reset();
      this.open = true;
      this.form.storeId=this.storeInfo?.storeId;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getCategories();
      const goodsId = row.goodsId || this.ids
      getGoods(goodsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsId != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
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
      const goodsIds = row.goodsId || this.ids;
      this.$modal.confirm('是否确认删除商品编号为"' + goodsIds + '"的数据项？').then(function() {
        return delGoods(goodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
