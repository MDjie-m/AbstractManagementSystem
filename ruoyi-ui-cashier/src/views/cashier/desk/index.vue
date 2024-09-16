<template>


  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick">
      <div class="desk-base-info" v-if="currentDesk">
        <div class="desk-base-info-name">
          {{ currentDesk.deskName }}({{ currentDesk.deskNum }})
        </div>
        <div class="desk-base-info-price">
          {{ currentDesk.price }}元/分钟
        </div>
      </div>

      <ToolBar title="台桌服务" v-if="currentDesk" v-loading="orderLoading">
        <div slot="titleRight" style="color: #8a8a8a;font-weight: 200;font-size: 12px">
          <span>{{ currentDesk.deskTotalTimeAmount }}</span>元 <i class="el-icon-info"/>
        </div>
        <SvgItem svg-icon="clock" label="开台" @click.native="onStartDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.Wait && !currentDesk.lastActiveOrder" :btnAble="true"/>
        <SvgItem svg-icon="desk_end" label="结开" v-if="currentDesk.status ===DeskStatus.Busy" :btnAble="true"/>
        <SvgItem svg-icon="desk_pause" label="暂停" @click.native="onPauseDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.Busy" :btnAble="true"/>
        <SvgItem svg-icon="desk_resume" label="恢复" @click.native="onResumeDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.PAUSE" :btnAble="true"/>
        <SvgItem svg-icon="desk_change" label="换台" @click.native="onOpenSwapDeskClick()"
                 v-if="currentDesk.status ===DeskStatus.Busy" :btnAble="true"/>
        <SvgItem svg-icon="timing" label="定时" :btnAble="true"/>
        <SvgItem svg-icon="light_on" label="开灯" :btnAble="true"
                 @click.native="onSwitchLight(currentDesk.deskNum,true)"/>
        <SvgItem svg-icon="close_light" label="关灯" :btnAble="true"
                 @click.native="onSwitchLight(currentDesk.deskNum,false)"/>
      </ToolBar>
      <ToolBar title="订单服务" v-if="currentDesk &&currentDesk.lastActiveOrder" v-loading="orderLoading">
        <div slot="titleRight" style="color: #8a8a8a;font-weight: 200;font-size: 12px">
          <span>{{ currentDesk.otherTotalAmount }}元</span> <i class="el-icon-info"/>
        </div>
        <SvgItem svg-icon="shop_car" label="选商品" :btnAble="true"/>
        <SvgItem svg-icon="user_choose" label="选艺人" :btnAble="true"/>
      </ToolBar>
      <ToolBar title="订单操作" v-if="currentDesk &&currentDesk.lastActiveOrder" v-loading="orderLoading">
        <SvgItem svg-icon="trash" label="作废" :btnAble="true" @click.native="onVoidOrderClick"/>
        <SvgItem svg-icon="suspend_order" label="挂单" :btnAble="true" @click.native="onSuspendOrderClick"/>
        <SvgItem svg-icon="stop" label="停止" :btnAble="true" @click.native="onStopOrderClick"/>
        <SvgItem svg-icon="credit_card" label="去结算" :btnAble="true" @click.native="onNavToSettleOrderClick"/>
      </ToolBar>
      <Dashboard ref="dashboard" v-if="!currentDesk" :storeName="storeInfo.storeName"/>

      <ToolBar title="预约/排队" v-if="!(currentDesk &&currentDesk.lastActiveOrder)">
        <SvgItem svg-icon="desk" label="台桌预约"/>
        <SvgItem svg-icon="tutor" label="教练预约"/>
        <SvgItem svg-icon="qrcode" label="预约核销"/>
        <SvgItem svg-icon="line_up" label="排队叫号" @click.native="onOpenLineUpClick()"/>
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

            <el-tag v-for="dict in dict.type.store_desk_type"
                    :key="dict.value+'deskType'"
                    :label="dict.label"
                    type="primary"
                    @click="onChooseClick('deskType',dict.value)"
                    :effect="parseInt(dict.value )===queryParams.deskType?'dark':'plain'"
                    round>
              {{ dict.label }}
            </el-tag>
            <el-tag v-for="dict in dict.type.store_desk_place"
                    :key="dict.value+'deskPlace'"
                    :label="dict.label"
                    :effect="parseInt(dict.value)===queryParams.placeType?'dark':'plain'"
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
        <el-scrollbar>
          <template class="box-card" v-for="placeItem in dict.type.store_desk_place">
            <el-divider content-position="left" :key="'typeDesk'+placeItem.value">{{ placeItem.label }}</el-divider>
            <div class="desk-container">
              <el-card @click.native="onDeskClick(desk)" class="desk-item" :class="{'selected':desk.selected}"
                       v-for="desk in deskList.filter(p=>  p.placeType === parseInt(placeItem.value))">
                <div class="item-status" :class="`item-status-${desk.status}`"></div>
                <div>
                  <div class="desk-item-name"> {{ desk.deskName }}</div>
                  <div class="desk-item-num"> {{ desk.deskNum }}</div>
                  <div class="desk-item-price"> {{ desk.price }}元/分钟</div>
                </div>


              </el-card>
            </div>
          </template>
        </el-scrollbar>
      </div>
      <content-wrapper :visible.sync="openNewDialog" :title="title">
        <line-up v-if="openNewDialog"/>
      </content-wrapper>
    </div>

    <!-- 换台确认框 -->
    <el-dialog title="换台" class="custom-dialog" :visible.sync="openSwapDesk" width="700px" append-to-body
               :close-on-click-modal="false"
               :close-on-press-escape="false" :show-close="false">
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
                           :label="item.deskName"
                           v-for="item in deskList">
                  <div style="display: flex;flex-direction: row;align-items: center">
                    <div class="desk-status" :class="`desk-status-`+item.status"></div>
                    <div> {{ item.deskName }}({{ item.deskNum }}) / {{ item.placeType === 0 ? '大厅' : '包间' }}
                    </div>
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
        <el-button type="primary" @click="onSwapDeskSubmit()">确 定</el-button>
        <el-button @click="openSwapDesk=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>


import {getDeskBaseInfo, listDesk, pauseCalcFee, resumeCalcFee, startCalcFee, swapToNewDesk} from "@/api/cashier/desk";
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
import Dashboard from "@/views/cashier/desk/components/dashboard.vue";
import ContentWrapper from "@/views/cashier/desk/components/contentWrapper.vue";
import LineUp from "@/views/cashier/desk/components/lineUp.vue";
import {queryStoreBaseInfo} from "@/api/cashier/store";
import ToolBar from "@/views/cashier/desk/components/toolBar.vue";
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";
import {MessageBox} from "element-ui";
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {stopOrder, suspendOrder, voidOrder} from "@/api/cashier/order";

const DeskStatus = {
  Wait: 0,
  Busy: 1,
  PAUSE: 2,
  Stop: 3,

}
const OrderStatus = {
  Charging: 0,//"计费中"),
  Stop: 1,//"待结算"),
  Settled: 2,//"已结算"),
  Void: 3,//"作废"),
  Suspend: 4,//"挂起订单")
}

export default {
  name: "Desk",
  components: {LeftContainer, SvgItem, ToolBar, LineUp, ContentWrapper, Dashboard},
  dicts: ['store_desk_status', 'store_desk_type', 'store_desk_place'],

  data() {
    return {
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
  created() {
    this.getList();
    this.getStoreInfo()
  },
  methods: {
    onSwapDeskSubmit() {
      if (!this.targetDesk?.deskId) {
        return this.$modal.msgWarning("请选择要更换的目标台桌");
      }
      this.loading = true;
      swapToNewDesk(this.currentDesk.deskId, this.targetDesk.deskId, this.currentDesk.lastActiveOrder.orderId).then(res => {
        this.onSwitchLight(this.currentDesk.deskNum, false);
        this.currentDesk = res.data;
        this.onSwitchLight(this.currentDesk?.deskNum, true);
        this.$modal.msgSuccess("操作成功");
        this.openSwapDesk = false;
        this.getList();
      }).then().then(n => {

      }).finally(() => this.loading = false)
    },
    onTargetDeskIdChange(val) {
      this.targetDesk.price = this.deskList.find(p => p.deskId === val)?.price;
    },
    onRefreshClick() {
      this.$refs.dashboard?.refresh();
      this.getList();
      this.getStoreInfo();
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
    onOpenLineUpClick() {
      this.openNewDialog = true;
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
      return getDeskBaseInfo(deskId).then(res => {
        this.currentDesk = res.data;
        this.deskList.forEach(p=>{
          if(p.deskId===deskId){
            p.status=this.currentDesk.status;
            p.selected=true;
          }
        })
      }).finally(() => this.loading = false);
    },
    onChooseAll() {
      this.queryParams.placeType = null;
      this.queryParams.deskType = null;
      this.currentDesk=null;
      this.getList();

    },
    onChooseAllStatus() {
      this.currentDesk=null;
      this.queryParams.status = null;
      this.getList();
    },
    onChooseClick(field, val) {
      if (this.queryParams[field] === parseInt(val)) {
        this.queryParams[field] = null;
      } else {
        this.queryParams[field] = parseInt(val);
      }
      this.currentDesk=null;
      this.getList()
    },

    /** 查询球桌列表 */
    getList() {
      if (this.loading) {
        return;
      }
      this.loading = true;
      let params = JSON.parse(JSON.stringify(this.queryParams));
      if (params.status === 1) {
        params.statusList = [1, 2]
      }
      listDesk(params).then(response => {
        this.deskList = (response.data || []).map(p => {
          p.selected = this.currentDesk?.deskId === p.deskId;
          return p;
        });

        this.loading = false;
      }).finally(() => this.loading = false);
    },
    onOpenSwapDeskClick() {
      this.openSwapDesk = true;
    },
    closeLoading() {
      this.loading = false
    },
    startLoading() {
      this.loading = false
    },
    onResumeDeskClick() {
      let deskTitle = `${this.currentDesk.deskName}(${this.currentDesk.deskNum})`;
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
        return  this.$message.warning("订单状态不允许此操作.")
      }
      let deskTitle = `${this.currentDesk.deskName}(${this.currentDesk.deskNum})`;
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
      }).then(({ value }) => {
        this.orderLoading = true;
        voidOrder(this.currentDesk?.lastActiveOrder.orderId,value).then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          this.$message.success("订单已作废.")
        }).finally(() => this.orderLoading = false)
      }).catch(() => {});

    },
    onSuspendOrderClick() {
      if (![OrderStatus.Stop,OrderStatus.Charging].includes(this.currentDesk?.lastActiveOrder.status ) ) {
        return  this.$message.warning("订单状态不允许此操作.")
      }
      let deskTitle = `${this.currentDesk.deskName}(${this.currentDesk.deskNum})`;

      MessageBox.confirm(`${deskTitle}:是否挂起订单?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.orderLoading = true;
        suspendOrder(this.currentDesk?.lastActiveOrder.orderId).then(res => {
          this.queryDeskById(this.currentDesk.deskId);
          this.$message.success("订单已挂起.")
        }).finally(() => this.orderLoading = false)
      })
    },
    navToOrder(orderId) {
      MessageBox.confirm(`订单已停止，是否进入结算页面？`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'success'
      }).then(res => {

      }, () => {

      })
    },
    onStopOrderClick() {
      if (this.currentDesk?.lastActiveOrder.status !== OrderStatus.Charging) {
        return  this.$message.warning("订单状态不允许此操作.")
      }
      let deskTitle = `${this.currentDesk.deskName}(${this.currentDesk.deskNum})`;

      MessageBox.confirm(`${deskTitle}:确认终止订单?`, '确认', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.orderLoading = true;
        stopOrder(this.currentDesk?.lastActiveOrder.orderId).then(res => {
          this.queryDeskById(this.currentDesk.deskId);

          this.navToOrder(this.currentDesk?.lastActiveOrder.orderId);
        }).finally(() => this.orderLoading = false)
      })
    },
    onNavToSettleOrderClick() {

    },
    onPauseDeskClick() {
      let deskTitle = `${this.currentDesk.deskName}(${this.currentDesk.deskNum})`;
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
    onStartDeskClick() {
      let deskTitle = `${this.currentDesk.deskName}(${this.currentDesk.deskNum})`;
      MessageBox.confirm(`${deskTitle}确认开台?`, '确认', {
        confirmButtonText: '确认',
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
          this.$message.success(`${deskTitle}已开台。`)
        }).then(res => {
          this.onSwitchLight(this.currentDesk.deskNum, true)
        })
      })
    }


  }
};
</script>
<style scoped lang="scss" src="./index.scss">
</style>
