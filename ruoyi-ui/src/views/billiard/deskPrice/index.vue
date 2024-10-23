<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="球桌类型" prop="deskType">
            <el-select v-model="queryParams.deskType" placeholder="请选择球桌类型" clearable @change="handleQuery">
              <el-option
                v-for="dict in deskTypeList"
                :key="dict.deskTypeId+'ffsdsdf'"
                :label="dict.name"
                :value="dict.deskTypeId"
              />
            </el-select>
          </el-form-item>
          <!--        <el-form-item label="创建者Id" prop="createById">-->
          <!--          <el-input-->
          <!--            v-model="queryParams.createById"-->
          <!--            placeholder="请输入创建者Id"-->
          <!--            clearable-->
          <!--            @keyup.enter.native="handleQuery"-->
          <!--          />-->
          <!--        </el-form-item>-->
          <!--        <el-form-item label="更新者Id" prop="updateById">-->
          <!--          <el-input-->
          <!--            v-model="queryParams.updateById"-->
          <!--            placeholder="请输入更新者Id"-->
          <!--            clearable-->
          <!--            @keyup.enter.native="handleQuery"-->
          <!--          />-->
          <!--        </el-form-item>-->
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
              v-hasPermi="['billiard:deskPrice:add']"
            >新增
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="deskPriceList" @selection-change="handleSelectionChange">
          <el-table-column label="ID" align="center" prop="deskPriceId"/>
          <el-table-column label="门店" align="center" prop="storeName"/>
          <el-table-column label="球桌类型" align="center" prop="deskTypeName">
          </el-table-column>
          <el-table-column label="价格" align="center" prop="price"/>
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
          <el-table-column label="备注" align="center" prop="remark"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:deskPrice:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:deskPrice:remove']"
              >删除
              </el-button>
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

      <!-- 添加或修改球桌价格对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="门店" prop="storeId">
            <el-tag> {{ storeInfo ? storeInfo.storeName : '' }}</el-tag>
          </el-form-item>
          <el-form-item label="球桌类型" prop="deskType">
            <el-select v-model="form.deskType" placeholder="请选择球桌类型" class="with100">
              <el-option
                v-for="dict in deskTypeList"
                :key="dict.deskTypeId+'ffsdsdffff'"
                :label="dict.name"
                :value="dict.deskTypeId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="价格" prop="price">
            <el-input-number controls-position="right" v-model="form.price" :precision="2" :step="0.1" :max="100"
                             :min="0.00"></el-input-number>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" class="with100" placeholder="请输入内容"
                      maxlength="200"/>
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
import {listDeskPrice, getDeskPrice, delDeskPrice, addDeskPrice, updateDeskPrice} from "@/api/billiard/deskPrice";
import StoreContainer from "@/views/billiard/component/storeContainer.vue";
import { listDeskTypeAll } from '@/api/billiard/deskType'

export default {
  name: "DeskPrice",
  components: {StoreContainer},

  data() {
    return {
      deskTypeList:[],
      storeInfo: null,
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
      // 球桌价格表格数据
      deskPriceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deskType: null,
        storeId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeId: [
          {required: true, message: "门店不能为空", trigger: "blur"}
        ],
        deskType: [
          {required: true, message: "球桌类型不能为空", trigger: "change"}
        ],
        price: [
          {required: true, message: "价格不能为空", trigger: "blur"},
          {
            validator: (rule, value, callback) => {
              if (value <= 0.00) {
                callback(new Error('价格应该大于0'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
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
      this.initData();
      this.queryDeskTypeList();
    },
    initData() {
      this.getList();
    },
    /** 查询球桌价格列表 */
    getList() {
      this.loading = true;
      listDeskPrice(this.queryParams).then(response => {
        this.deskPriceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    queryDeskTypeList(){
      listDeskTypeAll({ storeId: this.storeInfo?.storeId || -1 }).then(res=>{
        this.deskTypeList=res.data||[]
      })
    },
    // 表单重置
    reset() {
      this.form = {
        deskPriceId: null,
        storeId: this.storeInfo?.storeId || -1,
        deskType: null,
        price: null,
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
      this.ids = selection.map(item => item.deskPriceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if (!this.storeInfo?.storeId) {
        return this.$modal.msgWarning("请选择门店");
      }
      this.queryDeskTypeList();
      this.reset();
      this.open = true;
      this.title = "添加球桌价格";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.queryDeskTypeList();
      const deskPriceId = row.deskPriceId || this.ids
      getDeskPrice(deskPriceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改球桌价格";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deskPriceId != null) {
            updateDeskPrice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeskPrice(this.form).then(response => {
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
      const deskPriceIds = row.deskPriceId || this.ids;
      this.$modal.confirm('是否确认删除价格编号为"' + deskPriceIds + '"的数据项？').then(function () {
        return delDeskPrice(deskPriceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/deskPrice/export', {
        ...this.queryParams
      }, `deskPrice_${new Date().getTime()}.xlsx`)
    },

  }
};
</script>
