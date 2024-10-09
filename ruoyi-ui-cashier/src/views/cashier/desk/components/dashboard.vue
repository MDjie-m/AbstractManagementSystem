<template>
  <div class="menu-wrapper">
    <ToolBar title="球桌概览" :menus="deskMenus"></ToolBar>
    <ToolBar title="教练概览" :menus="tutorMenus"></ToolBar>
  </div>
</template>
<script>

import {listDeskDashboard} from "@/api/cashier/desk";
import LineUp from "@/views/cashier/desk/components/lineUp.vue";
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
import ToolBar from "@/views/cashier/desk/components/toolBar.vue";
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";

const OnBtnClickEvent = "onBtnClick"
export const InvokeMethodName = {
  LineUp: "lineUp"
}
export default {
  dicts: ['store_desk_status', 'store_desk_type', 'store_tutor_work_status'],
  emits: [OnBtnClickEvent],
  components: {SvgItem, ToolBar, LineUp},
  props: {
    storeName: {
      type: String,
      default: '',
    },
    deskPanelVisible: {
      type: Boolean,
      default: () => false
    },
    commonPanelVisible: {
      type: Boolean,
      default: () => true
    }
  },
  data() {
    return {
      deskMenus: [],
      tutorMenus: [],
      InvokeMethodName: InvokeMethodName,
    }
  },
  created() {
    this.refresh()
  },

  methods: {
    onBtnClick(type, title) {
      this.$emit(OnBtnClickEvent, type, title)
    },
    getList() {
      listDeskDashboard().then(res => {
        let lightItem = this.deskMenus.find(p => p.label === '已开灯') || {
          label: "已开灯",
          badge: 0,
          svgIcon: 'light_on'
        };
        let deskCount = res.data.deskCount || [];
        let tutorCount = res.data.tutorCount || [];
        let iconClass = ['icon-gray', 'icon-green', 'icon-blue', 'icon-yellow'];

         let tempDeskMenus =  this.dict.type.store_desk_status.map(p => {
          let item = deskCount.find(n => n.key === parseInt(p.value))
          return {
            label: p.label,
            badge: item?.value || 0,
            svgIcon:'clock',
            className:iconClass[p.value]
          }
        });
        tempDeskMenus.push(lightItem);
        this.deskMenus =tempDeskMenus;
        this.tutorMenus = this.dict.type.store_tutor_work_status.map(p => {
          let item = tutorCount.find(n => n.key === parseInt(p.value))
          return {
            label: p.label,
            badge: item?.value || 0,
            svgIcon: 'tutor',
            className:iconClass[p.value]
          }
        });
        this.getOpenLightCount();
      })
    },
    getOpenLightCount() {
      let totalCount = 0;
      callPCMethod(DeviceMethodNames.LightStateQuery, {}).then(res => {
        totalCount = res?.data?.openCount || 0
      }).finally(() => {
        this.deskMenus[this.deskMenus.length - 1].badge = totalCount;
      })
    },
    refresh() {
      this.getList();
    }
  }
}
</script>
<style scoped lang="scss" src="./dashboard.scss"></style>
