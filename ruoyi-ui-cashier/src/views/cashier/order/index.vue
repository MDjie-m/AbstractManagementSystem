<template>

  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick" :hide-store-info="currentOrder">
      <template v-slot:title v-if="currentOrder">
        <div>{{ currentOrder.orderNo }}</div>
      </template>
      <template v-if="currentOrder">
        <div class="order-detail-wrapper">
          <div class="order-detail-box">
            <template v-if="currentOrder.orderRecharges">
              <div class="time-box-wrapper" v-for="(item,idx) in currentOrder.orderRecharges">
                <div class="num-box">
                  {{ item.idx }}
                </div>
                <div class="time-box">
                  <div class="time-box-row">
                    充值金额:{{ item.rechargeAmount }}
                  </div>
                  <div class="time-box-row">
                    折扣金额:{{ item.totalDiscountAmount }}
                  </div>
                  <div class="time-box-row">
                    实际支付金额:{{ item.totalAmount }}
                  </div>
                </div>
              </div>
            </template>
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
                    <el-tag type="info" size="mini">{{ item.status !== CalcTimeStatus.Stop ? '当前' : '结束' }}</el-tag>
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
                    <el-tag type="info" size="mini">{{ item.status !== CalcTimeStatus.Stop ? '当前' : '结束' }}</el-tag>
                    <div>{{ item.endTime|timeFormat('MM-DD HH:mm') }}</div>
                  </div>
                </div>
                <div class="tool-box  "
                     v-if="item.status ===CalcTimeStatus.Busy ||item.status ===CalcTimeStatus.Pause ">
                  <el-popover
                    placement="right" popper-class="pop-auto"
                    trigger="hover">
                    <div class="menu-box">
                      <div v-if="item.status ===CalcTimeStatus.Busy"
                           @click="onTutorHandleClick('stop','停止计费',item)"><i
                        class="el-icon-video-pause icon-red "></i> 停止
                      </div>
                      <div v-if="item.status ===CalcTimeStatus.Pause"
                           @click="onTutorHandleClick('resume','恢复计费',item)"><i
                        class="el-icon-video-play  icon-green"></i> 恢复
                      </div>
                      <div v-if="item.status ===CalcTimeStatus.Busy"
                           @click="onTutorHandleClick('pause','暂停计费',item)"><i
                        class="el-icon-video-pause icon-yellow "></i> 暂停
                      </div>
                      <div v-if="item.status ===CalcTimeStatus.Busy" @click="onOpenSwapClick(item)">
                        <svg-icon icon-class="change" class=" icon-blue "></svg-icon>
                        换桌
                      </div>
                    </div>
                    <div style="height: 130px;display: flex;align-items: center" slot="reference">
                      <svg-icon icon-class="dot"/>
                    </div>

                  </el-popover>


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
              <div> 折后应付:</div>
              <div> {{ (currentOrder.totalAmountDue - currentOrder.totalDiscountAmount)|numFormat(2) }}</div>
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
              <div> 抹零后应付:</div>
              <div> {{ currentOrder.totalAmount }}</div>
            </div>
            <div class="amount-item" :class="{'tip-text':parseFloat(currentOrder.refundAmount)}">
              <div> 退款金额:</div>
              <div class="tip-red"> {{ currentOrder.refundAmount }}</div>
            </div>

            <div class="amount-item">
              <div class="tip-text">当前应付:</div>
              <div class="tip-red" v-if="parseFloat(currentOrder.refundAmount )"> 0.00</div>
              <div class="tip-red" v-else> {{
                  parseInt(currentOrder.repayAmount) ? currentOrder.repayAmount : currentOrder.totalAmount
                }}
              </div>
            </div>
          </div>
          <div class="order-member-box">
            <svg-icon class="name-icon" icon-class="user_choose"/>
            <div class="member-input" v-if="!currentOrder.memberId" @click="onOpenMemberClick"></div>
            <div class="member-name" v-if="currentOrder.memberId &&currentOrder.member ">
              <div @click="onOpenMemberClick"> {{ currentOrder.member.realName }}/{{ currentOrder.member.mobile }}
              </div>
              <i class="el-icon-delete" @click.stop="onRemoveMemberClick"
                 v-if="currentOrder.status ===OrderStatus.Stop ||currentOrder.status ===OrderStatus.Suspend "></i>
            </div>

          </div>
          <div class="order-tool-box">

            <el-button size="mini" circle type="danger" @click="showFinishOrder=true"
                       v-if="currentOrder.status ===OrderStatus.Stop||currentOrder.status ===OrderStatus.Suspend">结算
            </el-button>
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
          <el-form-item>
            <el-select v-model="queryParams.deskId" @change="onConditionChanged">
              <el-option label="台桌" :value="null"></el-option>
              <el-option :label="item.title" :key="'deskId'+item.deskId" :value="item.deskId"
                         v-for="item in deskList">
                <div style="display: flex;flex-direction: row;align-items: center">

                  <div> {{ item.title }}</div>
                  <div class="desk-status" :class="`desk-status-`+item.status"></div>
                </div>
              </el-option>
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

    <MemberSearch :visible.sync="showMemberSearch" @onOk="onChooseMemberOk"/>

    <CustomDialog title="确认" :visible.sync="showFinishOrder" :onOk="onFinishOrderOkClick" width="500px">
      <el-form v-if="currentOrder ">

        <el-form-item :label="currentOrder.refundAmount>0?'退款方式':'支付方式'">
          <template v-for="item in dict.type.order_pay_type">
            <el-radio
              v-if="((currentOrder.memberId && parseInt(item.value)===OrderPayType.MEMBER) || (parseInt(item.value)!==OrderPayType.MEMBER))
||(currentOrder.refundAmount>0 &&parseInt(item.value)!==OrderPayType.MEMBER )"
              v-model="finishOrderForm.payType" :label="item.value">{{ item.label }}
            </el-radio>
          </template>


        </el-form-item>
        <template v-if="parseInt(finishOrderForm.payType)===OrderPayType.MEMBER">
          <el-form-item label="当前会员余额:">
            <span> {{ currentOrder.member.currentAmount }}  </span>
          </el-form-item>
          <el-form-item label="待支付金额:">
            <span> {{ currentOrder.totalAmount }}  </span>
          </el-form-item>
          <el-form-item label="请输入会员密码:">
            <el-input maxlength="10" type="password" autocomplete="off" v-model="finishOrderForm.password">

            </el-input>
          </el-form-item>
        </template>


      </el-form>

    </CustomDialog>

    <swap-desk v-if="currentTutorTime" @onOk="refreshOrder" :tutor-id="currentTutorTime.tutorId" :desk-id="currentTutorTime.deskId" :open.sync="swapDeskShow"/>
  </div>
</template>
<script>
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {fillMember, finishOrder, getOrderInfo, listOrders} from "@/api/cashier/order";
import MemberSearch from "@/views/cashier/components/memberSearch.vue";
import {MessageBox} from "element-ui";
import {listDesk, resumeCalcFee} from "@/api/cashier/desk";
import {CalcTimeStatus, DeskStatus, OrderPayType, OrderStatus} from "@/views/cashier/components/constant";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {tutorOrderHandle} from "@/api/cashier/tutor";
import SwapDesk from "@/views/cashier/order/components/swapDesk.vue";


export default {
  computed: {
    OrderPayType() {
      return OrderPayType
    },
    DeskStatus() {
      return DeskStatus
    },
    CalcTimeStatus() {
      return CalcTimeStatus
    }
  },
  components: {SwapDesk, CustomDialog, MemberSearch, LeftContainer},
  dicts: ['order_type', 'order_status', 'store_desk_status', 'store_desk_type', 'store_desk_place', 'order_pay_type'],
  data() {
    return {
      currentTutorTime: null,
      swapDeskShow: false,
      showFinishOrder: false,
      finishOrderForm: {
        payType: '0',
        password: null,
        orderId: null,
      },
      loading: false,
      OrderStatus: OrderStatus,
      showMemberSearch: false,
      total: 0,
      deskList: [],
      orderList: [],
      currentOrder: null,
      queryParams: {
        deskId: null,
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
    this.getDeskList();
    if (this.$route.query?.orderId) {
      this.onRowClick(this.$route.query)
    }
  },
  methods: {
    getDeskList() {
      return listDesk({}).then(response => {
        this.deskList = (response.data || []).map(p => {
          this.fillTitle(p);
          return p;
        });
      })
    },
    fillTitle(item) {
      let type = this.dict.type.store_desk_type.find(p => parseInt(p.value) === item.deskType)?.label ?? '';
      let place = this.dict.type.store_desk_place.find(p => parseInt(p.value) === item.placeType)?.label ?? '';
      item.shortTitle = `${item.deskName}(${item.deskNum})`
      item.title = `${item.deskName}(${item.deskNum})/${type}/${place}`
    },
    onFinishOrderOkClick() {
      if (parseInt(this.finishOrderForm.payType) === OrderPayType.MEMBER && !String(this.finishOrderForm.password).trim()) {
        this.$modal.msgWarning("请输入密码");
        return Promise.reject()
      }
      this.finishOrderForm.orderId = this.currentOrder.orderId;
      return finishOrder(this.finishOrderForm).then(res => {
        this.$modal.msgSuccess("操作成功")
      }).finally(() => {
        this.onRowClick(this.currentOrder);
        this.getList();
      })

    },
    onOpenSwapClick(item) {
      this.currentTutorTime = item;
      this.swapDeskShow = true;
    },
    onTutorHandleClick(url, title, item) {
      MessageBox.confirm(`确认${title}?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        tutorOrderHandle(url, {tutorId: item.tutorId}).then(res => {
          this.$modal.msgSuccess("操作成功")
          this.onRowClick(JSON.parse(JSON.stringify(this.currentOrder)));
        })
      })
    },
    onOpenMemberClick() {
      if (![OrderStatus.Stop, OrderStatus.Suspend].includes(this.currentOrder.status)) {
        return
      }
      this.showMemberSearch = true;
    },
    onRemoveMemberClick() {
      MessageBox.confirm(`确认不使用会员结算?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.onChooseMemberOk({memberId: null})
      })
    },
    onChooseMemberOk(member) {
      this.showMemberSearch = false;
      fillMember(this.currentOrder.orderId, member.memberId).then(() => {
        this.$modal.msgSuccess("已变更会员信息,请查看支付金额是否更新")
      }).finally(() => {
        this.onRowClick(this.currentOrder)
      })

    },
    refreshOrder(){
      this.onRowClick(this.currentOrder)
    },
    onRefreshClick() {
      this.getList();
      this.getDeskList();
    },
    onRowClick(item) {
      this.loading = true;
      getOrderInfo(item.orderId).then(res => {
        let item = res.data;
        if (item) {
          let idx = 0;
          item.orderRecharges?.forEach(p => {
            idx++;
            p.idx = idx;
          })
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

    .tip-text {
      font-size: 20px !important;
      font-weight: bold;
    }

    .tip-red {
      font-size: 20px !important;
      font-weight: bold;
      color: #C03639;
    }

    .amount-item {
      display: flex;
      align-items: center;
      padding: 3px 10px;


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
    height: 40px;
    box-shadow: 1px 1px 3px rgba(0, 0, 0, .2);

    .name-icon {
      font-size: 20px;
      margin-right: 20px;
    }

    .member-input {
      flex: 1;
      border-bottom: 1px solid rgba(0, 0, 0, 0.1);
      height: 30px;
    }

    .member-name {
      display: flex;
      align-items: center;
      gap: 10px;

      i {
        font-size: 12px;
        color: #ff4949;
        cursor: pointer;
      }
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
