<template>

  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick" :hide-store-info="currentOrder">
      <template v-slot:title v-if="currentOrder">
        <div>{{ currentOrder.orderNo }}</div>
      </template>
      <template v-if="currentOrder">
        <div class="order-detail-wrapper">
          <div class="order-detail-box">
            <template v-if="currentOrder.orderDeskTimes">
              <div class="time-box-wrapper" v-for="(item,idx) in currentOrder.orderDeskTimes">
                <div class="num-box">
                  {{ item.idx }}
                </div>
                <div class="time-box">
                  <div class="time-box-row">

                    <div> {{ item.storeDesk.deskName }}({{ item.storeDesk.deskNum }})</div>
                    <dict-tag style="margin-left: 0" :options="dict.type.store_desk_status"
                              :value=" item.status"></dict-tag>


                  </div>
                  <div class="time-box-row">
                    <div>{{ item.price }} 元/分钟</div>
                    <div class="item-center">{{ item.totalTime }}分钟</div>
                    <div class="item-right">
                      <div>{{ item.totalAmount }}</div>
                      <div class="due-amount">{{ item.totalAmountDue }}</div>
                    </div>
                  </div>
                  <div class="time-box-row">
                    <el-tag type="info" size="mini">开始</el-tag>
                    <div>{{ item.startTime|timeFormat('MM-DD HH:mm') }}</div>
                  </div>
                  <div class="time-box-row">
                    <el-tag type="info" size="mini">结束</el-tag>
                    <div>{{ item.endTime|timeFormat('MM-DD HH:mm') }}</div>
                  </div>
                </div>
              </div>
            </template>
            <template v-if="currentOrder.orderTutorTimes">
              <div class="time-box-wrapper" v-for="(item,idx) in currentOrder.orderTutorTimes" :key="'order-t'+idx">
                <div class="num-box">
                  {{ item.idx }}
                </div>
                <div class="time-box">
                  <div class="time-box-row">
                    <image-preview class="goods-img" :preview="false" :src="item.storeTutor.userImg"/>
                    <div> {{ item.storeTutor.realName }}</div>
                    <dict-tag style="margin-left: 0" :options="dict.type.store_desk_status"
                              :value=" item.status"></dict-tag>


                  </div>
                  <div class="time-box-row">
                    <div>{{ item.price }} 元/分钟</div>
                    <div class="item-center">{{ item.totalTime }}分钟</div>
                    <div class="item-right">
                      <div>{{ item.totalAmount }}</div>
                      <div class="due-amount">{{ item.totalAmountDue }}</div>
                    </div>
                  </div>
                  <div class="time-box-row">
                    <el-tag type="info" size="mini">开始</el-tag>
                    <div>{{ item.startTime|timeFormat('MM-DD HH:mm') }}</div>
                  </div>
                  <div class="time-box-row">
                    <el-tag type="info" size="mini">结束</el-tag>
                    <div>{{ item.endTime|timeFormat('MM-DD HH:mm') }}</div>
                  </div>
                </div>
              </div>
            </template>
            <template v-if="currentOrder.orderGoods">
              <div class="time-box-wrapper" v-for="(item,idx) in currentOrder.orderGoods" :key="'g'+idx">
                <div class="num-box">
                  {{ item.idx }}
                </div>
                <div class="time-box">
                  <div class="time-box-row">
                    <image-preview class="goods-img" :preview="false" :src="item.goods.goodsImg"/>
                    <div>{{ item.goods.goodsName }}</div>
                    <div class="item-center"> {{ item.price }}元 *
                      <el-tag type="primary" size="mini">{{ item.num }}</el-tag>
                    </div>
                    <div class="item-right">
                      <div>{{ item.totalAmount }}</div>
                      <div class="due-amount">{{ item.totalAmountDue }}</div>
                    </div>
                  </div>
                </div>
              </div>
            </template>
          </div>
          <div class="order-amount-box  ">
            <div class="box-title">支付详情</div>
            <div class="amount-item">
              <div> 应付金额:</div>
              <div> {{ currentOrder.totalAmountDue }}</div>

            </div>
            <div class="amount-item">
              <div> 折扣金额:</div>
              <div> {{ currentOrder.totalDiscountAmount }}</div>
            </div>
            <div class="amount-item">
              <div>预付金额:</div>
              <div> {{ currentOrder.prePayAmount }}</div>
            </div>
            <div class="amount-item">
              <div> 抹零金额:</div>
              <div> {{ currentOrder.totalWipeZero }}</div>

            </div>
            <div class="amount-item">
              <div class="tip-text">实际应付:</div>
              <div class="tip-text"> {{ currentOrder.totalAmount }}</div>
            </div>
          </div>
          <div class="order-member-box">
            <svg-icon icon-class="user_choose"/>
            <div class="member-input"></div>
          </div>
          <div class="order-tool-box">

            <el-button size="mini" circle type="danger">结算</el-button>
          </div>

        </div>
      </template>


    </left-container>
    <div class="right-panel">
      <div class="section-container">
        <el-form :inline="true" label-width="90px">
          <el-form-item>
            <el-select v-model="queryParams.orderType" @change="onConditionChanged">
              <el-option label="订单类型" :value="null"></el-option>
              <el-option :label="item.label" :key="'type'+item.value" :value="item.value"
                         v-for="item in dict.type.order_type"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-select v-model="queryParams.status" @change="onConditionChanged">
              <el-option label="订单状态" :value="null"></el-option>
              <el-option :label="item.label" :key="'sta'+item.value" :value="item.value"
                         v-for="item in dict.type.order_status"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间:">
            <el-date-picker
              v-model="queryParams.times"
              type="datetimerange" @change="onConditionChanged"
              format="yyyy-MM-dd HH:mm"
              value-format="yyyy-MM-dd HH:mm"
              range-separator="至"
              start-placeholder="创建开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </div>

      <div class="section-container order-box">
        <div class="table-box">
          <el-scrollbar>
            <el-table @row-click="onRowClick" v-loading="loading" :data="orderList" @change="getList"
                      :row-style="rowStyle">
              <el-table-column label="订单号" align="center" prop="orderNo" width="210"/>
              <el-table-column label="订单类型" align="center" prop="orderType">
                <template slot-scope="scope">
                  <dict-tag :options="dict.type.order_type" :value="scope.row.orderType"/>
                </template>
              </el-table-column>
              <el-table-column label="应付金额(元)" align="center" prop="totalAmountDue"/>
              <el-table-column label="实际支付金额(元)" align="center" prop="totalAmount"/>


              <el-table-column label="订单状态" align="center" prop="status">
                <template slot-scope="scope">
                  <dict-tag :options="dict.type.order_status" :value="scope.row.status"/>
                </template>
              </el-table-column>
              <el-table-column label="创建时间" align="center" prop="createTime"/>
              <el-table-column label="备注" align="center" prop="remark"/>
            </el-table>
          </el-scrollbar>
        </div>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </div>

    <!-- 换台确认框 -->

  </div>
</template>
<script>
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {getOrderInfo, listOrders} from "@/api/cashier/order";

export default {
  components: {LeftContainer},
  dicts: ['order_type', 'order_status', 'store_desk_status'],
  data() {
    return {
      loading: false,
      total: 0,
      orderList: [],
      currentOrder: null,
      queryParams: {
        times: [this.$time().startOf('day').add(-1, 'd').format('YYYY-MM-DD HH:mm:ss'),
          this.$time().endOf('day').format('YYYY-MM-DD HH:mm:ss')],
        createStart: null,
        createEnd: null,
        orderType: null,
        status: null,
        pageSize: 10,
        pageIndex: 1,
        orderByColumn: "order_id",
        isAsc: "desc"
      }
    }
  },
  created() {

  },
  mounted() {
    this.getList();
    if (this.$route.query?.orderId) {
      this.onRowClick(this.$route.query)
    }
  },
  methods: {

    onRefreshClick() {

    },
    onRowClick(item) {
      this.loading = true;
      getOrderInfo(item.orderId).then(res => {
        let item = res.data;
        if (item) {
          let idx = 0;
          item.orderDeskTimes?.forEach(p => {
            idx++;
            p.idx = idx;
          });

          item.orderTutorTimes?.forEach(p => {
            idx++;
            p.idx = idx;
          })
          item.orderGoods?.forEach(p => {
            idx++;
            p.idx = idx;
          })
        }
        this.currentOrder = item;
      }).catch(e => {
        this.currentOrder = null;
      }).finally(() => this.loading = false)
    },
    onConditionChanged() {
      this.queryParams.pageIndex = 1;
      this.getList();
    },
    rowStyle({row}) {
      if (this.currentOrder && this.currentOrder.orderId === row.orderId) {
        return {'background-color': '#1890ff !important', color: '#fff !important'};
      }
      return {cursor: 'pointer'};
    },
    getList() {
      this.loading = true;
      if (this.queryParams.times) {
        this.queryParams.createStart = this.queryParams.times[0] ?? null;
        this.queryParams.createEnd = this.queryParams.times[1] ?? null;
      } else {
        this.queryParams.createStart = this.queryParams.createEnd = null;
      }


      listOrders(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;

      }).finally(() => this.loading = false)
    }
  }
}
</script>

<style scoped lang="scss" src="./order.scss">

</style>
<style lang="scss">
.order-detail-wrapper, .abc {
  display: flex;
  flex-direction: column;
  flex: 1;
  background-color: #FFFFFF;
  margin-top: 1px;
  overflow: hidden;

  .order-detail-box {
    display: flex;
    width: 100%;
    flex: 1;
    flex-direction: column;
    overflow-y: auto;
  }

  .order-amount-box {
    display: flex;
    flex-direction: column;
    width: 100%;
    box-shadow: 1px 1px 3px rgba(0, 0, 0, .2);


    .box-title {
      background-color: rgba(204, 204, 204, 0.39);
      padding: 10px;
    }

    .amount-item {
      display: flex;
      align-items: center;
      padding: 3px 10px;

      .tip-text {
        font-size: 20px !important;
        font-weight: bold;
      }

      div:first-child {
        margin-right: 10px;
        color: rgba(0, 0, 0, 0.4);
      }

      div:last-child {
        margin-right: 10px;
        font-size: 14px;
        font-weight: bold;
      }
    }
  }

  .order-member-box {
     display: flex;
    align-items: center;
    gap: 5px;
    padding: 5px 10px;
    font-size: 30px;
    box-shadow: 1px 1px 3px rgba(0, 0, 0, .2);
    .member-input{
      flex: 1;
      border-bottom: 1px solid rgba(0,0,0,0.1);
      height: 30px;
    }
  }

  .order-tool-box {
    display: flex;
    padding: 8px 20px;
    flex-direction: row;
    justify-content: flex-end;

    .el-button {
      height: 40px;
    }
  }

}
</style>
