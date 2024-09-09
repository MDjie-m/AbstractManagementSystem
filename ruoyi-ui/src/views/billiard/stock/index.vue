<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px"
        >
          <el-form-item label="商品名称" prop="goodsName">
            <el-input
              v-model="queryParams.goodsName"
              placeholder="请输入商品名称" maxlength="40"
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
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd(ChangeType.In,'入库')"
              v-hasPermi="['billiard:stock:edit']"
            >入库
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-minus"
              size="mini"
              @click="handleAdd(ChangeType.Out,'出库')"
              v-hasPermi="['billiard:stock:edit']"
            >出库
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              @click="handleAdd(ChangeType.Check,'盘点')"
              v-hasPermi="['billiard:stock:edit']"
            >盘点
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="stockList" @selection-change="handleSelectionChange">
          <el-table-column label="商品" align="center" prop="goodsName">
              <template slot-scope="scope">
                <div style="display: flex;flex-direction: row;align-items: center;justify-content: left;padding-left: 40px">
                  <image-preview :src="scope.row.goodsImg" :width="50" :height="50"/>
                  <div style="margin-left: 10px">{{scope.row.goodsName}}</div>
                </div>

              </template>
          </el-table-column>
          <el-table-column label="当前库存数量" align="center" prop="total"/>
          <el-table-column label="入库数量" align="center" prop="totalIn"/>
          <el-table-column label="出库数量" align="center" prop="totalOut"/>
          <el-table-column label="创建/更新" align="center" prop="updateTime" width="200">
            <template slot-scope="scope">
              <div>
                <span>{{ scope.row.createBy }}&nbsp;</span>
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </div>
              <div>
                <span>{{ scope.row.updateBy }} &nbsp;</span>
                <span>{{ parseTime(scope.row.updateTime) }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-view"
                @click="handleViewLog(scope.row)"
                v-hasPermi="['billiard:goods:edit']"
              >查看记录
              </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-plus"
                  @click="handleAdd(ChangeType.In,'入库', scope.row.goodsId)"
                  v-hasPermi="['billiard:stock:edit']"
                >入库
                </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-minus"
                @click="handleAdd(ChangeType.Out,'出库', scope.row.goodsId)"
                v-hasPermi="['billiard:stock:edit']"
              >出库
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

      <!-- 添加或修改库存对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="custom-form">
          <el-form-item label="商品名称" prop="goodsId">
            <el-select v-model="form.goodsId" placeholder="请选择设商品">
              <el-option
                v-for="dict in goodsList"
                :key="dict.goodsId"
                :label="dict.goodsName"
                :value="dict.goodsId"
              >
                <template>
                  <div style="display:flex;flex-direction: row;   align-items: center ; justify-content: left">
                   <image-preview :src="dict.goodsImg" :width="25" :height="25" :preview="false"/>
                    <div style="margin-left: 10px">{{dict.goodsName}}</div>
                  </div>
                </template>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :label="`${title}数量`" prop="changeCount">
            <el-input-number v-model="form.changeCount" placeholder="请输入数量" :min="1" :max="999999" :step="1"/>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" :maxlength="200"/>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog :title="`[${log.goodsName}]库存操作记录`" :visible.sync="openLog" width="900px" append-to-body>
        <el-table v-loading="logLoading" :data="log.list" @selection-change="handleSelectionChange">
          <el-table-column label="序号" width="50">
            <template v-slot:default="scope">
              {{logQueryParams.pageSize * (logQueryParams.pageNum - 1) + (scope.$index + 1)}}
            </template>
          </el-table-column>
          <el-table-column label="操作前数量" align="center" prop="beforeCount"/>
          <el-table-column label="操作数量" align="center" prop="changeCount"/>
          <el-table-column label="操作后数量" align="center" prop="currentCount"/>

          <el-table-column label="操作方式" align="center" prop="changeType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.stock_change_type" :value="scope.row.changeType"/>
            </template>
          </el-table-column>
          <el-table-column label="备注" width="150" align="center" prop="remark"/>
          <el-table-column label="操作人" align="center" prop="createBy"/>
          <el-table-column label="操作时间" align="center" prop="createTime" width="200">
            <template slot-scope="scope">
              <div>
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </div>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="log.total>0"
          :total="log.total"
          :page.sync="logQueryParams.pageNum"
          :limit.sync="logQueryParams.pageSize"
          @pagination="getLogList"
        />
      </el-dialog>
    </div>
  </StoreContainer>
</template>

<script>
const ChangeType = {
  In: 0,
  Out: 1,
  Check: 2
}
import {
  listStock,
  getStock,
  delStock,
  listStoreGoods,
  changeStock,
  listStockLogList
} from '@/api/billiard/stock'
import StoreContainer from '@/views/billiard/component/storeContainer.vue'

export default {
  name: 'Stock',
  dicts: ['stock_change_type'],
  components: { StoreContainer },
  data() {
    return {
      ChangeType: ChangeType,
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
      // 库存表格数据
      stockList: [],
      goodsList: [],
      log:{
        list:[],
        total:0,
        pageNum:0,
        pageSize:10,
        goodsName:null,
      },
      logQueryParams:{
        pageNum: 1,
        pageSize: 10,
        stockId:0,
      },
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsId: null,
        goodsName: null,
        storeId: null,
        total: null,
        lastCheckPointId: null,
        createById: null,
        updateById: null
      },
      // 表单参数
      form: {},
      openLog:false,
      logLoading:false,
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: '商品不能为空', trigger: 'blur' }
        ],
        changeCount: [
          { required: true, message: '数量不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {

  },
  methods: {
    /** 查询库存列表 */
    onStoreChanged(store) {
      this.storeInfo = store
      this.queryParams.storeId = store?.storeId || -1
      this.getList()
      this.getGoodsList()
    },
    getList() {
      this.loading = true
      listStock(this.queryParams).then(response => {
        this.stockList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getLogList() {
      this.logLoading = true;
      listStockLogList(this.logQueryParams).then(response => {
        this.log.list = response.rows
        this.log.total = response.total
        this.logLoading = false
      })
    },
    getGoodsList() {
      listStoreGoods(this.queryParams.storeId).then(response => {
        this.goodsList = response.data || []
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {

        goodsId: null,
        changeType: null,
        changeCount: null,
        storeId: this.storeInfo?.storeId,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.stockId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(type, title, goodsId) {
      if(!this.storeInfo?.storeId){
        return  this.$modal.msgWarning("请选择门店");
      }
      this.getGoodsList();
      this.reset()
      this.form.changeType = type
      this.form.goodsId = goodsId
      this.open = true

      this.title = title
    },
    handleViewLog(item){
      this.log.total=0;
      this.log.list=0;
      this.queryParams.pageNum=1;
      this.logQueryParams.stockId=item.stockId;
      this.log.goodsName=item.goodsName;
      this.openLog=true;

      this.getLogList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stockId = row.stockId || this.ids
      getStock(stockId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改库存'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {

          changeStock(this.form).then(response => {
            this.$modal.msgSuccess('操作成功')
            this.open = false
            this.getList()
          })
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const stockIds = row.stockId || this.ids
      this.$modal.confirm('是否确认删除库存编号为"' + stockIds + '"的数据项？').then(function() {
        return delStock(stockIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/stock/export', {
        ...this.queryParams
      }, `stock_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
