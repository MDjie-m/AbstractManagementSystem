<template>


  <div class="page-container">
    <div class="left-panel">

      <div class="store-info">
        <i class="el-icon-refresh-right store-info-btn" @click="onRefreshClick"></i>
        <div class="store-info-icon">
          <svg-icon icon-class="store"/>
        </div>
        <div class="store-info-title">
          {{ storeInfo.storeName }}
        </div>
        <el-button @click="onSwitchLight(1)">test</el-button>
      </div>
      <Dashboard ref="dashboard" @onBtnClick="onMenuBtnClick" :storeName="storeInfo.storeName"/>
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

export default {
  name: "Desk",
  components: {LineUp, ContentWrapper, Dashboard},
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
      queryStoreBaseInfo().then(res => {
        this.storeInfo = res.data || {
          storeName: '', userList: [], tutorList: []
        }
      });
    },
    onMenuBtnClick(name, title) {
      this.openNewDialog = true;
      this.title = title
    },
    onSwitchLight(deskNum) {
      let req = {deskNum: deskNum, open: this.lightStatus ? false : true}
      callPCMethod(DeviceMethodNames.LightSwitch, req).then(res => {
        this.lightStatus = res.data.state;
        this.$modal.msgSuccess(JSON.stringify(res))
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
