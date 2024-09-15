<template>


  <div class="page-container">

    <div class="left-panel">
      <div class="store-info">
        <i v-show="!currentDesk" class="el-icon-refresh-right store-info-btn" @click="onRefreshClick"></i>
        <div class="store-info-icon">
          <svg-icon icon-class="store"/>
        </div>
        <div class="store-info-title">
          {{ storeInfo.storeName }}
        </div>
      </div>
      <div class="desk-base-info" v-if="currentDesk">
        <div class="desk-base-info-name">
          {{ currentDesk.deskName }}({{ currentDesk.deskNum }})
        </div>
        <div class="desk-base-info-price">
          {{ currentDesk.price }}元/分钟
        </div>
      </div>

      <ToolBar title="台桌服务" v-if="currentDesk">
        <div slot="titleRight" style="color: #8a8a8a;font-weight: 200;font-size: 12px">
          <span>0.00元</span> <i class="el-icon-info"/>
        </div>
        <SvgItem svg-icon="clock" label="开台" :btnAble="true"/>
        <SvgItem svg-icon="desk_end" label="结开" :btnAble="true"/>
        <SvgItem svg-icon="desk_pause" label="暂停" :btnAble="true"/>
        <SvgItem svg-icon="desk_change" label="换台" :btnAble="true"/>
        <SvgItem svg-icon="timing" label="定时" :btnAble="true"/>
        <SvgItem svg-icon="light_on" label="开灯" :btnAble="true"
                 @click.native="onSwitchLight(currentDesk.deskNum,true)"/>
        <SvgItem svg-icon="close_light" label="关灯" :btnAble="true"
                 @click.native="onSwitchLight(currentDesk.deskNum,false)"/>
      </ToolBar>
      <ToolBar title="订单服务" v-if="currentDesk">
        <div slot="titleRight" style="color: #8a8a8a;font-weight: 200;font-size: 12px">
          <span>0.00元</span> <i class="el-icon-info"/>
        </div>
        <SvgItem svg-icon="shop_car" label="选商品" :btnAble="true"/>
        <SvgItem svg-icon="user_choose" label="选艺人" :btnAble="true"/>
      </ToolBar>
      <ToolBar title="订单操作" v-if="currentDesk">
        <SvgItem svg-icon="trash" label="清空" :btnAble="true"/>
        <SvgItem svg-icon="suspend_order" label="挂单" :btnAble="true"/>
        <SvgItem svg-icon="stop" label="停止" :btnAble="true"/>
        <SvgItem svg-icon="credit_card" label="去结算" :btnAble="true"/>
      </ToolBar>
      <Dashboard ref="dashboard" v-if="!currentDesk" :storeName="storeInfo.storeName"/>

      <ToolBar title="预约/排队">
        <SvgItem svg-icon="desk" label="台桌预约"/>
        <SvgItem svg-icon="tutor" label="教练预约"/>
        <SvgItem svg-icon="qrcode" label="预约核销"/>
        <SvgItem svg-icon="line_up" label="排队叫号" @click.native="onOpenLineUpClick()"/>
      </ToolBar>

    </div>

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

      <div class="  section-container desk-box" v-loading="loading">
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

    <!-- 添加或修改球桌对话框 -->

  </div>

</template>

<script>


import {listDesk} from "@/api/cashier/desk";
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
import Dashboard from "@/views/cashier/desk/components/dashboard.vue";
import ContentWrapper from "@/views/cashier/desk/components/contentWrapper.vue";
import LineUp from "@/views/cashier/desk/components/lineUp.vue";
import {queryStoreBaseInfo} from "@/api/cashier/store";
import ToolBar from "@/views/cashier/desk/components/toolBar.vue";
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";

export default {
  name: "Desk",
  components: {SvgItem, ToolBar, LineUp, ContentWrapper, Dashboard},
  dicts: ['store_desk_status', 'store_desk_type', 'store_desk_place'],

  data() {
    return {
      storeInfo: {storeName: '', userList: [], tutorList: []},
      openNewDialog: false,
      lightStatus: null,
      currentDesk: null,

      deskList: [],
      originalDeskList: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
      })
      item.selected = !item.selected
      this.currentDesk = item.selected ? item : null;

    },
    onChooseAll() {
      this.queryParams.placeType = null;
      this.queryParams.deskType = null;

      this.getList();

    },
    onChooseAllStatus() {
      this.queryParams.status = null;
      this.getList();
    },
    onChooseClick(field, val) {
      if (this.queryParams[field] === parseInt(val)) {
        this.queryParams[field] = null;
      } else {
        this.queryParams[field] = parseInt(val);
      }

      this.getList()

    },
    filterDeskList() {

      //状态：0=空闲，1=计时中， ,3=已停止
      this.deskList = this.originalDeskList.filter(p => {
        let statusCondition = true;
        let deskTypeCondition = true;
        let placeCondition = true;

        if (this.queryParams.status !== null) {
          statusCondition = p.status === parseInt(this.queryParams.status)
        }
        if (this.queryParams.deskType !== null) {
          deskTypeCondition = p.deskType === parseInt(this.queryParams.deskType)
        }
        if (this.queryParams.placeType !== null) {
          placeCondition = p.placeType === parseInt(this.queryParams.placeType)
        }
        return statusCondition && placeCondition && deskTypeCondition
      });
      return this.deskList;
    },
    /** 查询球桌列表 */
    getList() {
      this.loading = true;
      this.currentDesk = null;
      listDesk({}).then(response => {
        this.originalDeskList = (response.data || []).map(p => {
          p.selected = false;
          return p;
        });
        this.filterDeskList()
        this.loading = false;
      }).finally(() => this.loading = false);
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {};
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


  }
};
</script>
<style scoped lang="scss" src="./index.scss">
</style>
