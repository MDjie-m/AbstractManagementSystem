<template>


  <div class="page-container">

    <left-container ref="leftContainer" @onRefreshClick="onRefreshClick" :hide-store-info="currentDesk">
      <div class="score-info" v-if="currentDesk">


        <el-card class="score-info-item">
          {{ currentDesk.score.scoreA|numPad }}
        </el-card>
        <el-card class="score-info-item">
          {{ currentDesk.score.scoreB|numPad }}
        </el-card>
      </div>
      <div class="desk-base-info" v-if="currentDesk">
        <div class="desk-base-info-name">
          {{ currentDesk.deskName }}({{ currentDesk.deskNum }})
        </div>
        <div class="desk-base-info-price">
          {{ currentDesk.price }}元/分钟
        </div>
      </div>

      <ToolBar title="台桌服务" v-if="currentDesk" v-loading="orderLoading">
        <div slot="titleRight" v-if="currentDesk.status ===DeskStatus.Busy "
             style="color: #8a8a8a;font-weight: 200;font-size: 12px">
          <span>{{ currentDesk.deskTotalTimeAmount }}</span>元
        </div>
        <SvgItem svg-icon="clock" class="icon-blue" label="开台" @click.native="onStartDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.Wait && !currentDesk.lastActiveOrder" :btnAble="true"/>
        <el-popconfirm v-if="currentDesk.status ===DeskStatus.Busy"
                       title="确认结束开台？" @confirm="onStopDeskClick">
          <SvgItem slot="reference" svg-icon="desk_end" label="结开"
                   :btnAble="true"/>
        </el-popconfirm>

        <SvgItem svg-icon="desk_pause" label="暂停" @click.native="onPauseDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.Busy" :btnAble="true"/>
        <SvgItem svg-icon="desk_resume" label="恢复" @click.native="onResumeDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.PAUSE" :btnAble="true"/>
        <SvgItem svg-icon="desk_change" label="换台/并台" @click.native="onOpenSwapDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.Busy" :btnAble="true"/>
        <SvgItem svg-icon="timing" label="定时" :badge="currentDesk.lastCalcTime" @click.native="onTempLight( 1)"
                 v-if="currentDesk.currentOrderId"
                 :btnAble="true"/>
        <SvgItem svg-icon="pre_pay" label="预付"
                 :badge="currentDesk.lastActiveOrder &&  parseFloat( currentDesk.lastActiveOrder.prePayAmount)?currentDesk.lastActiveOrder.prePayAmount:0 "
                 :btnAble="true"
                 v-if="currentDesk.lastActiveOrder"
                 @click.native="onPrePayClick  "/>
        <SvgItem svg-icon="light_on" label="开灯" :btnAble="true"
                 @click.native="onSwitchLight(currentDesk.deskNum,true)"/>
        <SvgItem svg-icon="light_temp" label="临时灯" :btnAble="true" :badge="currentDesk.lastTempTime"
                 @click.native="onTempLight( 0)"/>
        <SvgItem svg-icon="close_light" label="关灯" :btnAble="true"
                 @click.native="onSwitchLight(currentDesk.deskNum,false)"/>
      </ToolBar>
      <ToolBar title="订单服务" v-if="currentDesk &&currentDesk.lastActiveOrder" v-loading="orderLoading">
        <div slot="titleRight" style="color: #8a8a8a;font-weight: 200;font-size: 12px"
             v-if="currentDesk.status ===DeskStatus.Busy ">
          <span>{{ currentDesk.otherTotalAmount }}元</span>
        </div>
        <SvgItem svg-icon="shop_car" label="选商品" :btnAble="true" @click.native="onNavBuyClick(ChooseType.Goods)"/>
        <SvgItem svg-icon="user_choose" label="选艺人" :btnAble="true" @click.native="onNavBuyClick(ChooseType.Tutor)"/>
      </ToolBar>
      <ToolBar title="订单操作" v-if="currentDesk &&currentDesk.lastActiveOrder" v-loading="orderLoading">
        <SvgItem svg-icon="trash" label="作废" :btnAble="true" @click.native="onVoidOrderClick"/>
        <SvgItem svg-icon="suspend_order" class="icon-blue" label="挂单" :btnAble="true"
                 @click.native="onSuspendOrderClick"/>
        <SvgItem svg-icon="stop" label="停止" class="icon-blue" :btnAble="true" @click.native="onStopOrderClick"/>
        <SvgItem svg-icon="credit_card" label="去结算" :btnAble="true" @click.native="onNavToSettleOrderClick"/>
      </ToolBar>
      <Dashboard ref="dashboard" v-if="!currentDesk" :storeName="storeInfo?storeInfo.storeName:''"/>

      <ToolBar title="预约/排队" v-if="!(currentDesk &&currentDesk.lastActiveOrder)">
        <SvgItem svg-icon="desk" class="icon-blue" label="台桌预约"
                 @click.native="onOpenLineUpClick(DeskDialogTitle.BookingDesk)"/>
        <SvgItem svg-icon="tutor" class="icon-blue" label="教练预约"
                 @click.native="onOpenLineUpClick(DeskDialogTitle.BookingTutor)"/>
        <SvgItem svg-icon="qrcode" class="icon-blue" label="预约核销"
                 @click.native="onOpenLineUpClick(DeskDialogTitle.BookingVerify)"/>
        <SvgItem svg-icon="line_up" class="icon-blue" label="排队叫号"
                 @click.native="onOpenLineUpClick(DeskDialogTitle.LineUp)"/>
      </ToolBar>
    </left-container>

    <div class="right-panel">
      <div class="  section-container">
        <div>
          <el-row>

            <el-tag
              type="primary"
              @click="onChooseAll"
              :effect="queryParams.deskType===null &&queryParams.placeType===null?'dark':'plain'"
            >
              全部
            </el-tag>

            <el-tag v-for="dict in deskTypeList"
                    :key="dict.value+'deskType'"
                    :label="dict.label"
                    type="primary"
                    @click="onChooseClick('deskType',dict.value)"
                    :effect=" dict.value  ===queryParams.deskType?'dark':'plain'"
                    round>
              {{ dict.label }}
            </el-tag>
            <el-tag v-for="dict in placeTypeList"
                    :key="dict.value+'deskPlace'"
                    :label="dict.label"
                    :effect="dict.value===queryParams.placeType?'dark':'plain'"
                    @click="onChooseClick('placeType',dict.value)"
                    type="primary"
            >
              {{ dict.label }}
            </el-tag>

          </el-row>

          <el-row style="margin-top: 20px; ">
            <el-tag
              type="primary"
              @click="onChooseAllStatus"
              :effect="queryParams.status===null ?'dark':'plain'"
            >
              全部
            </el-tag>
            <el-tag v-for="dict in dict.type.store_desk_status"
                    :key="dict.value+'status'"
                    :label="dict.label"
                    type="primary"
                    @click="onChooseClick('status',dict.value)"
                    :effect="parseInt(dict.value )===queryParams.status?'dark':'plain'"
                    round>
              {{ dict.label }}
            </el-tag>

          </el-row>
        </div>
      </div>

      <div class="  section-container desk-box">

        <template class="box-card" v-for="placeItem in placeTypeList">
          <el-divider content-position="left" :key="'typeDesk'+placeItem.value">{{ placeItem.label }}</el-divider>
          <div class="desk-container">
            <div @click="onDeskClick(desk)" class="desk-item" :class="{'selected':desk.selected}"
                     v-for="desk in placeItem.list" :key="'deskid'+desk.deskId">
              <div class="item-status" :class="`item-status-${desk.status}`"></div>

                <div class="desk-item-name"> {{ desk.deskName }}</div>
                <div class="desk-item-num"> {{ desk.deskNum }}</div>
                <div class="desk-item-price"> {{ desk.price }}元/分钟</div>
                <div class="desk-item-time" v-if="desk.minutes>0">
                  计费中:{{desk.minutes|hhmm}}
                </div>
                <div class="desk-item-booking" v-if="    desk.booking &&!desk.minutes">
                  {{ desk.booking.startTime |timeFormat("HH:mm") }} ~{{ desk.booking.endTime |timeFormat("HH:mm") }}
                </div>



            </div>
          </div>
        </template>
      </div>
      <content-wrapper :visible.sync="openNewDialog" :title="title">
        <line-up :storeName="storeInfo?storeInfo.storeName:''" v-if="title===DeskDialogTitle.LineUp"/>
        <booking-desk :desk-list="deskList" v-if="title===DeskDialogTitle.BookingDesk"/>
        <booking-tutor v-if="title===DeskDialogTitle.BookingTutor"/>
        <booking-verify v-if="title===DeskDialogTitle.BookingVerify"/>
      </content-wrapper>
    </div>

    <custom-dialog title="确认预充值金额" :visible.sync="openPrePay" :onOk="onPrePaySubmit" width="500px">
      <el-form v-if="currentDesk ">

        <el-form-item label="支付方式:">
          <template v-for="item in dict.type.order_pay_type">
            <el-radio
              v-if="  (parseInt(item.value)!==OrderPayType.MEMBER)"
              v-model="prePayForm.payType" :label="item.value">{{ item.label }}
            </el-radio>
          </template>
        </el-form-item>

        <el-form-item label="充值金额:">
          <el-input-number :min="100" :max="9999" v-model="prePayForm.amount">
          </el-input-number>
        </el-form-item>
      </el-form>

    </custom-dialog>
    <!-- 换台确认框 -->
    <custom-dialog title="换台/并台" class="custom-dialog" :visible.sync="openSwapDesk" width="700px" append-to-body>
      <el-form ref="form" :model="targetDesk" label-width="120px">
        <el-row v-if="currentDesk">
          <el-col :span="12">
            <el-form-item label="当前台桌:">
              <span>{{ currentDesk.deskName }}({{ currentDesk.deskNum }})</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前台桌价格:">
              <span>{{ currentDesk.price }}元/分钟</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="目标台桌:">
              <el-select v-model="targetDesk.deskId" filterable @change="onTargetDeskIdChange">
                <!--                <div slot="prefix"></div>-->
                <el-option :value="item.deskId" :key="'targetDesk'+item.deskId"
                           :label="item.title" v-if=" currentDesk &&  currentDesk.deskId!==item.deskId"
                           v-for="item in deskList">
                  <div style="display: flex;flex-direction: row;align-items: center">
                    <div class="desk-status" :class="`desk-status-`+item.status"></div>
                    <div> {{ item.title }}</div>
                    <div style=" padding-left: 20px">
                      {{ item.price }}元/分钟
                    </div>
                  </div>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="目标台桌价格:">
              <span v-if="targetDesk.price!=null">{{ targetDesk.price }}元/分钟</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" v-loading="loading">
        <el-popconfirm @confirm=" onMergeDeskSubmit "
                       title="请谨慎操作，确认好目标台桌，并台后将不可撤销？">
          <el-button slot="reference" style="margin-right: 10px" type="primary">并台</el-button>
        </el-popconfirm>
        <el-button type="primary" @click="onSwapDeskSubmit()">换台</el-button>
        <el-button @click="openSwapDesk=false">取 消</el-button>
      </div>
    </custom-dialog>
  </div>

</template>

<script>


import {
  createLightTimer,
  getDeskBaseInfo,
  listDesk, listDeskTypeAll, listPlaceTypeAll, mergeToNewDesk,
  pauseCalcFee,
  resumeCalcFee,
  startCalcFee,
  swapToNewDesk
} from "@/api/cashier/desk";
import {
  callPCMethod,
  DeviceCallbackMethodName,
  DeviceMethodNames,
  registerMethod,
  removeMethod
} from "@/utils/pcCommunication";
import Dashboard from "@/views/cashier/desk/components/dashboard.vue";
import ContentWrapper from "@/views/cashier/desk/components/contentWrapper.vue";
import LineUp from "@/views/cashier/desk/components/lineUp.vue";
import {queryStoreBaseInfo} from "@/api/cashier/store";
import ToolBar from "@/views/cashier/desk/components/toolBar.vue";
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";
import {MessageBox} from "element-ui";
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {orderPrePay, orderStopDesk, stopOrder, suspendOrder, voidOrder} from "@/api/cashier/order";
import {
  OrderStatus,
  DeskStatus,
  LightType,
  ChooseType,
  DeskDialogTitle,
  OrderPayType
} from "@/views/cashier/components/constant";
import BookingDesk from "@/views/cashier/desk/components/bookingDesk.vue";

import BookingTutor from "@/views/cashier/desk/components/bookingTutor/index.vue";
import BookingVerify from "@/views/cashier/desk/components/bookingVerify/index.vue";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {GlobalEvent} from "@/utils/globalConst";

export default {
  name: "Desk",
  computed: {
    OrderPayType() {
      return OrderPayType
    },
    DeskDialogTitle() {
      return DeskDialogTitle
    },
    ChooseType() {
      return ChooseType
    }
  },
  components: {
    CustomDialog,
    BookingVerify, BookingDesk, BookingTutor, LeftContainer, SvgItem, ToolBar, LineUp, ContentWrapper, Dashboard
  },
  dicts: ['store_desk_status', 'order_pay_type'],

  data() {
    return {
      placeTypeList: [],
      deskTypeList: [],
      prePayForm: {
        payType: '0',
        amount: null,
        orderId: null,
      },
      orderLoading: false,
      DeskStatus: DeskStatus,
      storeInfo: {storeName: '', userList: [], tutorList: []},
      openNewDialog: false,
      lightStatus: null,
      currentDesk: null,
      loading: false,
      deskList: [],
      targetDesk: {deskId: null, deskName: null, deskNum: null, price: null},

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openSwapDesk: false,
      openPrePay: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deskName: null,
        deskNum: undefined,
        deskType: null,
        placeType: null,
        storeId: null,
        price: null,
        status: null,
        lightDeviceId: null,
        cameraDeviceId: null
      },
      // 表单参数
      form: {},

    };
  },
  mounted() {
    this.$eventBus.$on(GlobalEvent.OnRefreshDesk, this.onRefreshDeskCallback)
    this.getDeskTypeList();
    this.getPlaceTypeList().then(this.getList)
    this.getStoreInfo();
    this.initSomePCCallBackMethods();


  },


  beforeDestroy() {
    this.$eventBus.$off(GlobalEvent.OnRefreshDesk);
    removeMethod(DeviceCallbackMethodName.AddScore)
  },
  methods: {
    onRefreshDeskCallback({deskId, stopOrder}) {
      if (this.currentDesk?.deskId === deskId) {
        this.queryDeskById(deskId)
        this.getList();
      } else {
        this.getList();
      }
      if (stopOrder) {
        let item = this.deskList.find(p => p.deskId === deskId);
        this.$modal.msgSuccess(`${item.title}已自动停止计费`)
      }
    },
    getStoreName() {
      return this.$refs?.leftContainer.storeName
    },
    getPlaceTypeList() {
      return listPlaceTypeAll().then(res => {
        this.placeTypeList = (res.data || []).map(p => {
          return {list: [], ...p}
        });
        return this.placeTypeList;
      })
    },
    getDeskTypeList() {
      listDeskTypeAll().then(res => {
        this.deskTypeList = res.data || [];
      })
    },
    initSomePCCallBackMethods() {
      registerMethod(DeviceCallbackMethodName.AddScore, this.addScore);
    },
    addScore({data}) {
      console.log("加分", JSON.stringify(data))
      if (this.currentDesk) {
        this.queryDeskById(this.currentDesk.deskId)
      }
    },

    onNavBuyClick(type) {
      this.$router.push({
        path: '/cashier/buy', query: {
          orderId: this.currentDesk?.currentOrderId,
          deskId: this.currentDesk?.deskId,
          type: type
        }
      })
    },
    onMergeDeskSubmit() {
      if (!this.targetDesk?.deskId) {
        return this.$modal.msgWarning("请选择要合并的目标台桌");
      }
      this.loading = true;
      mergeToNewDesk(this.currentDesk.deskId, this.targetDesk.deskId, this.currentDesk.lastActiveOrder.orderId).then(res => {
        this.onSwitchLight(this.currentDesk.deskNum, false);
        this.currentDesk = res.data;
        this.onSwitchLight(this.currentDesk?.deskNum, true);
        this.$modal.msgSuccess("操作成功");
        this.openSwapDesk = false;
        this.getList();
      }).then().then(n => {

      }).finally(() => this.loading = false)
    },
    onSwapDeskSubmit() {
      if (!this.targetDesk?.deskId) {
        return this.$modal.msgWarning("请选择要更换的目标台桌");
      }
      this.loading = true;
      swapToNewDesk(this.currentDesk.deskId, this.targetDesk.deskId, this.currentDesk.lastActiveOrder.orderId).then(res => {
        this.onSwitchLight(this.currentDesk.deskNum, false);
        this.currentDesk = res.data;
        this.onSwitchLight(this.currentDesk?.deskNum, true);

        this.openSwapDesk = false;

        this.getList();
        this.$modal.msgSuccess("操作成功");
      }).finally(() => this.loading = false)
    },
    onTargetDeskIdChange(val) {
      this.targetDesk.price = this.deskList.find(p => p.deskId === val)?.price;
    },
    onRefreshClick() {
      this.$refs.dashboard?.refresh();
      this.getList();
      if (this.currentDesk) {
        this.queryDeskById(this.currentDesk?.deskId)
      }

      this.$modal.msgSuccess("已刷新")
    },
    getStoreInfo() {
      queryStoreBaseInfo(false).then(res => {
        this.storeInfo = res.data || {
          storeName: '', userList: [], tutorList: []
        }
      });
    },
    onMenuBtnClick(name, title) {
      this.openNewDialog = true;
      this.title = title
    },
    onOpenLineUpClick(title) {
      this.title = title
      this.openNewDialog = true;
    },

    onTempLight(lightType) {
      let msg = LightType.Temp === lightType ? '请输入灯光持续打开时间(分钟)' : '请输入计费时间(分钟)，订单会在到期后自动停止计费'
      this.$prompt(msg, "确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnClickModal: false,
        inputPattern: /^[1-9]\d{0,2}$/,
        inputErrorMessage: "请输入1~999的数字",
        inputValidator: (value) => {
          if (!/^[1-9]\d{0,2}$/.test(value)) {
            return "请输入1~999的数字"
          }
        },
      }).then(({value}) => {
        if (lightType === LightType.Temp) {
          this.onSwitchLight(this.currentDesk?.deskNum, true);
        }
        createLightTimer({
          deskId: this.currentDesk?.deskId,
          lightType: lightType,
          orderId: this.currentDesk?.currentOrderId,
          startTime: this.$time().format("YYYY-MM-DD HH:mm:00"),
          endTime: this.$time().add(value, "minute").format("YYYY-MM-DD HH:mm:00"),
        }).then(response => {
          if (response.data) {
            this.$eventBus.$emit(GlobalEvent.OnAddTimer, response.data)
          }
          if (lightType === LightType.Temp) {
            this.$modal.msgSuccess(`灯已打开,将会在${value}分钟后关闭`);
          } else {
            this.$modal.msgSuccess(`已开始计费，订单将会在${value}分钟后停止计费。`);
          }
          this.queryDeskById(this.currentDesk?.deskId)

        });
      }).catch(() => {
      });

    },
    onPrePaySubmit() {
      if (this.currentDesk.lastActiveOrder.prePayAmount >= 9999) {
        this.$modal.msgWarning("预付金额不能超过9999")
        return Promise.reject();
      }
      return orderPrePay({
        amount: this.prePayForm.amount,
        payType: this.prePayForm.payType,
        orderId: this.currentDesk?.currentOrderId,
      }).then(response => {
        this.currentDesk.lastActiveOrder.prePayAmount = response.data;
        this.$modal.msgSuccess(`预付费成功`);
      });
    },
    onPrePayClick() {
      this.openPrePay = true
    },
    onSwitchLight(deskNum, open) {
      if (deskNum === null || deskNum === undefined) {
        return
      }
      let req = {deskNum: deskNum, open: !!open}
      callPCMethod(DeviceMethodNames.LightSwitch, req).then(res => {

      })
      // callPCMethod(DeviceMethodNames.LightStateQuery, req).then(res => {
      //
      //   this.$modal.msgSuccess(JSON.stringify(res))
      // })
      // let msgs=[{content:"请"},{content: "11",emphasis:1},{content: "号到前台"}]
      // callPCMethod("speech", msgs).then(res => {
      //   this.lightStatus = res.data;
      //   this.$modal.msgSuccess(JSON.stringify(res))
      // })
    },
    onDeskClick(item) {
      this.deskList.forEach(p => {
        if (p === item) {
          return;
        }
        p.selected = false;
      });
      if (item.selected) {
        item.selected = false;
        this.currentDesk = null;
        return;
      }
      if (this.loading) {
        return
      }
      this.loading = true;
      this.queryDeskById(item.deskId);
    },
    queryDeskById(deskId) {
      this.loading = true;
      return getDeskBaseInfo(deskId).then(res => {
        this.currentDesk = res.data || [];
        this.deskList.forEach(p => {
          if (p.deskId === deskId) {
            p.status = this.currentDesk.status;
            p.selected = true;
          }
        })
      }).finally(() => this.loading = false);
    },
    onChooseAll() {
      this.queryParams.placeType = null;
      this.queryParams.deskType = null;
      this.currentDesk = null;
      this.getList();

    },
    onChooseAllStatus() {
      this.currentDesk = null;
      this.queryParams.status = null;
      this.getList();
    },
    onChooseClick(field, val) {
      if (this.queryParams[field] === val) {
        this.queryParams[field] = null;
      } else {
        this.queryParams[field] = val;
      }
      this.currentDesk = null;
      this.getList()
    },

    /** 查询球桌列表 */
    getList() {
      this.loading = true;
      let params = JSON.parse(JSON.stringify(this.queryParams));
      params.queryLastBooking = true
      params.queryTime = true;
      if (params.status === 1) {
        params.statusList = [1, 2]

      }
      listDesk(params).then(response => {
        this.deskList = (response.data || []).map(p => {
          p.selected = this.currentDesk?.deskId === p.deskId;
          return p;
        });
        this.placeTypeList = this.placeTypeList.map(type => {
          type.list = this.deskList.filter(p => p.placeType === type.value)
          return type;
        });
        this.loading = false;
      }).finally(() => this.loading = false);
    },

    onOpenSwapDeskClick() {
      this.getList();
      this.openSwapDesk = true;
    },
    closeLoading() {
      this.loading = false
    },
    startLoading() {
      this.loading = false
    },
    onResumeDeskClick() {
      let deskTitle = this.currentDesk.title;
      MessageBox.confirm(`${deskTitle}恢复计费?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        resumeCalcFee(this.currentDesk.deskId).then(res => {
          this.currentDesk = res.data;
          this.deskList.forEach(p => {
            if (p.deskId === this.currentDesk.deskId) {
              p.status = this.currentDesk.status
            }
          })
          this.$message.success(`${deskTitle}已恢复计费。`)
        })
      })
    },
    onVoidOrderClick() {
      if (this.currentDesk?.lastActiveOrder.status !== OrderStatus.Charging) {
        return this.$message.warning("订单状态不允许此操作.")
      }
      this.$prompt('请输入订单作废备注', "确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnClickModal: false,
        inputPattern: /^.{0,200}$/,
        inputErrorMessage: "备注内容长度0-200",
        // inputValidator: (value) => {
        //   if (/<|>|"|'|\||\\/.test(value)) {
        //     return "不能包含非法字符：< > \" ' \\\ |"
        //   }
        // },
      }).then(({value}) => {
        this.orderLoading = true;
        voidOrder(this.currentDesk?.lastActiveOrder.orderId, value).then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          this.$message.success("订单已作废.")
        }).finally(() => this.orderLoading = false)
      }).catch(() => {
      });

    },
    onSuspendOrderClick() {
      if (![OrderStatus.Stop, OrderStatus.Charging].includes(this.currentDesk?.lastActiveOrder.status)) {
        return this.$message.warning("订单状态不允许此操作.")
      }
      let deskTitle = this.currentDesk.title;

      MessageBox.confirm(`${deskTitle}:是否挂起订单?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.orderLoading = true;
        suspendOrder(this.currentDesk?.lastActiveOrder.orderId).then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          res.data?.busyDesks?.forEach(p => {
            this.onSwitchLight(p.deskNum, false)
          })
          this.$message.success("订单已挂起.")
        }).finally(() => this.orderLoading = false)
      })
    },
    routeToOrder(orderId) {
      this.$router.push({
        path: '/cashier/order', query: {
          orderId
        }
      })
    },
    navToOrder(orderId) {
      MessageBox.confirm(`订单已停止，是否进入结算页面？`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'success'
      }).then(res => {
        this.routeToOrder(orderId)
      }, () => {

      })
    },
    onStopOrderClick() {
      if (this.currentDesk?.lastActiveOrder.status !== OrderStatus.Charging) {
        return this.$message.warning("订单状态不允许此操作.")
      }

      let orderId = this.currentDesk?.lastActiveOrder.orderId;

      MessageBox.confirm(`${this.currentDesk.title}:确认终止订单?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.orderLoading = true;
        stopOrder(this.currentDesk?.lastActiveOrder.orderId).then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          res.data?.busyDesks?.forEach(p => {
            this.onSwitchLight(p.deskNum, false)
          })

          this.navToOrder(orderId);
        }).finally(() => this.orderLoading = false)
      })
    },
    onNavToSettleOrderClick() {
      let orderId = this.currentDesk?.lastActiveOrder.orderId;
      MessageBox.confirm(`${this.currentDesk.title}:确认终止订单并结算?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.orderLoading = true;
        stopOrder(this.currentDesk?.lastActiveOrder.orderId).then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          res.data?.busyDesks?.forEach(p => {
            this.onSwitchLight(p.deskNum, false)
          })
          this.routeToOrder(orderId)
        }).finally(() => this.orderLoading = false)
      })
    },
    onPauseDeskClick() {
      let deskTitle = this.currentDesk.title;
      MessageBox.confirm(`${deskTitle}暂停后将停止计费,确认暂停?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        pauseCalcFee(this.currentDesk.deskId).then(res => {
          this.currentDesk = res.data;
          this.deskList.forEach(p => {
            if (p.deskId === this.currentDesk.deskId) {
              p.status = this.currentDesk.status
            }
          })
          this.$message.success(`${deskTitle}已暂停。`)
        })
      })
    },
    onStopDeskClick() {

      orderStopDesk(this.currentDesk.currentOrderId, this.currentDesk.deskId)
        .then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          if (res.data.hasOtherDesk) {
            this.$message.success("当前台桌已结束开台,但是还有其他台桌在计费,如要结账，请结开另一个台桌。")
            return
          }
          this.onSwitchLight(this.currentDesk.deskNum, false)
          this.navToOrder(res.data.orderId);
        })
    },
    onStartDeskClick() {
      let deskTitle = this.currentDesk.title;
      let msgList = [];
      const h = this.$createElement;
      if (this.currentDesk.booking) {
        let startTime = this.$time(this.currentDesk.booking.startTime).format('MM-DD HH:mm');
        let endTime = this.$time(this.currentDesk.booking.endTime).format('MM-DD HH:mm');
        msgList.push(h('p', null, `${deskTitle}有预约：`))
        msgList.push(h('p', {style: {color: '#1890ff'}}, `${startTime}到${endTime},`))
        msgList.push(h('p', null, "确认开台?"))
      } else {
        msgList = [`${deskTitle}确认开台?`]
      }

      this.$confirm('确认', {
        title: "确认",
        confirmButtonText: '确认',
        message: h('div', null, msgList),
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        startCalcFee(this.currentDesk.deskId).then(res => {
          this.currentDesk = res.data;
          this.deskList.forEach(p => {
            if (p.deskId === this.currentDesk.deskId) {
              p.status = this.currentDesk.status
            }
          })
          this.$message.success(`${deskTitle}已开台。`);
          this.getList()
        }).then(res => {
          this.onSwitchLight(this.currentDesk.deskNum, true);
          callPCMethod(DeviceMethodNames.CallAddScore, {
            deskNum: this.currentDesk.deskNum,
            btnType: 1,
          })
        })
      })
    }


  }
};
</script>
<style scoped lang="scss" src="./index.scss">
</style>
