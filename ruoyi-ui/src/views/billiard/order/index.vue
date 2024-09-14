<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="订单编码" prop="orderNo">
            <el-input
              v-model="queryParams.orderNo"
              placeholder="请输入订单编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="订单类型" prop="orderType">
            <el-select v-model="queryParams.orderType" placeholder="请选择订单类型">
              <el-option
                v-for="item in orderTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="订单状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择订单状态">
              <el-option
                v-for="item in orderStatusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="支付方式" prop="payType">
            <el-select v-model="queryParams.payType" placeholder="请选择支付方式">
              <el-option
                v-for="item in orderPayTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

      </div>
      <div class="col-sm-12 select-table table-striped">
        <!--      <el-row :gutter="10" class="mb8">-->
        <!--        <el-col :span="1.5">-->
        <!--          <el-button-->
        <!--            type="primary"-->
        <!--            plain-->
        <!--            icon="el-icon-plus"-->
        <!--            size="mini"-->
        <!--            @click="handleAdd"-->
        <!--            v-hasPermi="['billiard:order:add']"-->
        <!--          >新增-->
        <!--          </el-button>-->
        <!--        </el-col>-->
        <!--        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
        <!--      </el-row>-->

        <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
          <el-table-column label="订单id" align="center" prop="orderId"/>
          <el-table-column label="订单编码" align="center" prop="orderNo"/>
          <el-table-column label="订单类型" align="center" prop="orderType">
            <template slot-scope="scope">
              <dict-tag :options="orderTypeOptions" :value="scope.row.orderType"/>
            </template>
          </el-table-column>
          <el-table-column label="应付总金额 " align="center" prop="totalAmountDue"/>
          <el-table-column label="折扣金额" align="center" prop="totalDiscountAmount"/>
          <el-table-column label="实际支付金额" align="center" prop="totalAmount"/>
          <el-table-column label="当前折扣" align="center" prop="discountValue"/>
          <el-table-column label="抹零金额" align="center" prop="totalWipeZero"/>
          <el-table-column label="支付方式" align="center" prop="payType">
            <template slot-scope="scope">
              <dict-tag :options="orderPayTypeOptions" :value="scope.row.payType"/>
            </template>
          </el-table-column>
          <el-table-column label="订单状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="orderStatusOptions" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="支付会员id" align="center" prop="memberId"/>
          <el-table-column label="备注" align="center" prop="remark"/>
<!--          <el-table-column label="创建者Id" align="center" prop="createById"/>-->
<!--          <el-table-column label="更新者Id" align="center" prop="updateById"/>-->
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleInfo(scope.row)"
                v-hasPermi="['billiard:order:info']"
              >详情
              </el-button>
              <!--              <el-button-->
              <!--                size="mini"-->
              <!--                type="text"-->
              <!--                icon="el-icon-delete"-->
              <!--                @click="handleDelete(scope.row)"-->
              <!--                v-hasPermi="['billiard:order:remove']"-->
              <!--              >删除-->
              <!--              </el-button>-->
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

      <!-- 订单详情对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
        <div class="info-box roll">
          <div class="info-box">
            <div class="info-box-row">
              <div class="info-box-row-middle">
                <div class="info-box-title">订单编码：</div>
                <div class="info-box-row-left-content">{{ form.orderNo }}</div>
              </div>
              <div class="info-box-row-middle">
                <div class="info-box-title">会员：</div>
                <div class="info-box-row-left-content" v-if="form.memberId">
                  {{ `${form.member.realName}/${form.member.mobile}` }}
                </div>
              </div>
            </div>
            <div class="info-box-row">
              <div class="info-box-row-middle">
                <div class="info-box-title">应付总金额：</div>
                <div class="info-box-row-left-content">{{ formatAmount(form.totalAmountDue) }}</div>
              </div>
              <div class="info-box-row-middle">
                <div class="info-box-title">折扣金额：</div>
                <div class="info-box-row-left-content">{{ formatAmount(form.totalDiscountAmount) }}</div>
              </div>
            </div>
            <div class="info-box-row">
              <div class="info-box-row-middle">
                <div class="info-box-title">当前折扣：</div>
                <div class="info-box-row-left-content">{{ formatTotalDiscountAmount(form.totalDiscountAmount) }}</div>
              </div>
              <div class="info-box-row-middle">
                <div class="info-box-title">抹零金额：</div>
                <div class="info-box-row-left-content">{{ formatAmount(form.totalWipeZero) }}</div>
              </div>
            </div>
            <div class="info-box-row">
              <div class="info-box-title">实际支付金额：</div>
              <div class="info-box-row-left-content text-red">{{ formatAmount(form.totalAmount) }}</div>
            </div>
            <div class="info-box-row">
              <div class="info-box-title">备注：</div>
              <div class="info-box-row-left-content">{{ form.remark }}</div>
            </div>
          </div>
          <div class="info-box">
            <el-divider content-position="left">商品</el-divider>
            <el-table :data="form.orderGoods" style="width: 100%">
              <el-table-column prop="goodsPrice" label="商品图片">
                <template slot-scope="scope">
                  <image-preview :src="scope.row.goods.goodsImg" :width="'100px'" :height="'100px'"/>
                </template>
              </el-table-column>
              <el-table-column prop="goodsName" label="商品名称"></el-table-column>
              <el-table-column prop="price" label="单价">
                <template slot-scope="scope">
                  {{ formatAmount(scope.row.price) }}
                </template>
              </el-table-column>
              <el-table-column prop="num" label="数量"></el-table-column>
              <el-table-column prop="totalAmountDue" label="应付总金额">
                <template slot-scope="scope">
                  {{ formatAmount(scope.row.totalAmountDue) }}
                </template>
              </el-table-column>
              <el-table-column prop="totalDiscountAmount" label="折扣金额">
                <template slot-scope="scope">
                  {{ formatAmount(scope.row.totalDiscountAmount) }}
                </template>
              </el-table-column>
              <el-table-column prop="discountValue" label="当前折扣">
                <template slot-scope="scope">
                  {{ formatTotalDiscountAmount(scope.row.discountValue) }}
                </template>
              </el-table-column>
              <el-table-column prop="totalWipeZero" label="抹零金额">
                <template slot-scope="scope">
                  {{ formatAmount(scope.row.totalWipeZero) }}
                </template>
              </el-table-column>
              <el-table-column prop="totalAmount" label="实际支付金额">
                <template slot-scope="scope">
                  {{ formatAmount(scope.row.totalAmount) }}
                </template>
              </el-table-column>
              <el-table-column prop="remark" label="备注"></el-table-column>
            </el-table>
          </div>
          <div class="info-box">
            <el-divider content-position="left">会员充值</el-divider>
            <div class="info-box" v-for="(item, idx) in form.orderRecharges" :key="item.orderRechargeId + idx">
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">充值金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.rechargeAmount) }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">充值赠送金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.giveAmount) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-title">支付金额：</div>
                <div class="info-box-row-left-content text-red">{{ formatAmount(item.totalAmount) }}</div>
              </div>
              <div class="info-box-row">
                <div class="info-box-title">备注：</div>
                <div class="info-box-row-left-content">{{ item.remark }}</div>
              </div>
            </div>
          </div>
          <div class="info-box">
            <el-divider content-position="left">球桌计时</el-divider>
            <div class="info-box" v-for="(item, idx) in form.orderDeskTimes" :key="item.orderDeskTimeId + idx">
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">球桌：</div>
                  <div class="info-box-row-left-content" v-if="item.deskId">
                    {{ `${item.storeDesk.deskName}(${item.storeDesk.deskNum})` }}
                  </div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">是否转桌：</div>
                  <div class="info-box-row-left-content">{{ formatIfFormDesk(item.fromDeskId) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">开始时间：</div>
                  <div class="info-box-row-left-content">{{ item.startTime }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">结束时间：</div>
                  <div class="info-box-row-left-content">{{ item.endTime }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">总时间(分钟)：</div>
                  <div class="info-box-row-left-content">{{ formatTotalTime(item.totalTime) }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">价格/分钟：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.price) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">应付总金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.totalAmountDue) }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">折扣金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.totalDiscountAmount) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">当前折扣：</div>
                  <div class="info-box-row-left-content">{{ formatTotalDiscountAmount(item.discountValue) }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">抹零金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.totalWipeZero) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">实际赠送支付金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.totalGiveAmount) }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">实际支付金额：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.totalAmount) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-title">备注：</div>
                <div class="info-box-row-left-content">{{ item.remark }}</div>
              </div>
            </div>
          </div>
          <div class="info-box">
            <el-divider content-position="left">教练计时</el-divider>
            <div class="info-box" v-for="(item, idx) in form.orderTutorTimes" :key="item.orderTutorTimeId + idx">
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">球桌：</div>
                  <div class="info-box-row-left-content">{{ `${item.storeDesk.deskName}(${item.storeDesk.deskNum})` }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">教练类型：</div>
                  <div class="info-box-row-left-content">
                    <dict-tag :options="dict.type.order_tutor" :value="item.type"/>
                  </div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">开始时间：</div>
                  <div class="info-box-row-left-content">{{ item.startTime }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">结束时间：</div>
                  <div class="info-box-row-left-content">{{ item.endTime }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">总时间分钟：</div>
                  <div class="info-box-row-left-content">{{ formatTotalTime(item.totalTime) }}</div>
                </div>
                <div class="info-box-row-middle">
                  <div class="info-box-title">价格/分钟：</div>
                  <div class="info-box-row-left-content">{{ formatAmount(item.price) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-row-middle">
                  <div class="info-box-title">助教总费用：</div>
                  <div class="info-box-row-left-content text-red">{{ formatAmount(item.totalAmount) }}</div>
                </div>
              </div>
              <div class="info-box-row">
                <div class="info-box-title">备注：</div>
                <div class="info-box-row-left-content">{{ item.remark }}</div>
              </div>
            </div>
          </div>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </StoreContainer>
</template>

<script>
import {
  listOrder,
  getOrder,
  delOrder,
  addOrder,
  updateOrder,
  getsAnOrderTypeEnumeration,
  getsAnOrderStatusEnumeration,
  getsAnEnumerationOfPaymentMethods
} from "@/api/billiard/order";
import StoreContainer from "@/views/billiard/component/storeContainer.vue";


export default {
  name: "Order",
  components: {StoreContainer},
  dicts: ['order_tutor'],
  data() {
    return {

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
      // 订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        orderType: null,
        status: null,
        payType: null,
        storeId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
        updateTime: [
          {required: true, message: "更新时间不能为空", trigger: "blur"}
        ]
      },
      // 订单类型枚举
      orderTypeOptions: [],
      // 支付方式枚举
      orderPayTypeOptions: [],
      // 订单支付状态枚举
      orderStatusOptions: [],
    };
  },
  created() {
  },
  methods: {
    onStoreChanged(store) {
      this.storeInfo = store;
      this.queryParams.storeId = store?.storeId || -1;
      this.initData()
    },
    initData() {
      this.getList();
      this.getOrderType()
      this.getOrderPayType()
      this.getOrderStatus()
    },
    /** 查询订单列表 */
    getList() {

      if (!this.storeInfo?.storeId) {
        return this.$modal.msgWarning("请选择门店");
      }
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        console.log('列表', this.orderList)
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
        orderNo: null,
        orderType: null,
        status: null,
        payType: null,
        storeId: this.storeInfo?.storeId,
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
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单";
    },
    /** 详情按钮操作 */
    handleInfo(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        console.log('详情', this.form)
        this.open = true;
        this.title = "订单详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除订单编号为"' + orderIds + '"的数据项？').then(function () {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },
    /** 查询订单类型枚举 */
    getOrderType() {
      return getsAnOrderTypeEnumeration().then(res => {
        this.orderTypeOptions = (res.data || []).map(p => {
          return Object.assign({label: p.name, value: p.value, raw: {listClass: 'primary'}}, p);
        });
      })
    },
    /** 查询订单状态枚举 */
    getOrderStatus() {
      return getsAnOrderStatusEnumeration().then(res => {
        this.orderStatusOptions = (res.data || []).map(p => {
          return Object.assign({label: p.name, value: p.value, raw: {listClass: 'success'}}, p);
        });
      })
    },
    /** 查询订单支付方式枚举 */
    getOrderPayType() {
      return getsAnEnumerationOfPaymentMethods().then(res => {
        this.orderPayTypeOptions = (res.data || []).map(p => {
          return Object.assign({label: p.name, value: p.value, raw: {listClass: 'info'}}, p);
        });

      })
    },
    formatTotalDiscountAmount(value) {
      if (!value) return '';
      return `${this.convertAndRemoveTrailingZeros(value)}折`;
    },
    convertAndRemoveTrailingZeros(numStr) {
      // 使用parseFloat转换为浮点数，然后转换为字符串去掉末尾的0
      return Number(parseFloat(numStr).toFixed(2));
    },
    formatAmount(value) {
      if (!value) return '';
      return `￥${value}`;
    },
    formatTotalTime(value) {
      if (!value) return '0分钟';
      return `${value}分钟`;
    },
    formatIfFormDesk(value) {
      if (!value) return '否';
      return '是';
    },
  }
};
</script>
<style lang="scss" scoped src="./index.scss"></style>">
