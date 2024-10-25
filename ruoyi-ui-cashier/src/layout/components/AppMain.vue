<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <router-view v-if="ifRouterAlive && !$route.meta.link " :key="key"/>
    </transition>
    <iframe-toggle/>
  </section>
</template>

<script>
import iframeToggle from "./IframeToggle/index"
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
import {stopOrder} from "@/api/cashier/order";
import {listLightTimer, removeLightTimer, removeLightTimerById} from "@/api/cashier/desk";
import {GlobalEvent} from "@/utils/globalConst";

let lightTimerList = []
export default {
  name: 'AppMain',
  components: {iframeToggle},
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      intervalId: null,
      ifRouterAlive: true
    }
  },
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.path
    }
  },
  created() {
    this.queryLightTimer()
    this.$eventBus.$on(GlobalEvent.OnGlobalRefresh, this.onReload);
    this.$eventBus.$on(GlobalEvent.OnAddTimer, this.onAddTimer);
    this.queryLightTimer();
    this.intervalId = setInterval(async () => {
      await this.runLightTimer();
    }, 1000);
  },
  beforeDestroy() {
    this.$eventBus.$off(GlobalEvent.OnGlobalRefresh);
    this.$eventBus.$off(GlobalEvent.OnAddTimer);
    clearInterval(this.intervalId);
  },
  methods: {
    onAddTimer(timer) {
      lightTimerList.push(timer)
    },
    async runLightTimer() {
      let time = this.$time().format("YYYY-MM-DD HH:mm:ss");
      let oldTimers = lightTimerList.filter(p => p.endTime < time);

      while (oldTimers.length > 0) {

        let p = oldTimers.pop();
        try {
          console.log("定时关灯", p)
            callPCMethod(DeviceMethodNames.LightSwitch, {deskNum: p.deskNum, open: false});
          if (p.lightType === 1) {
            await stopOrder(p.orderId)
          }
          removeLightTimerById(p.lightTimerId);


        } catch (e) {
        }
        let idx=lightTimerList.indexOf(p);
        if(idx>-1) {
          lightTimerList.splice(idx, 1)
          this.$eventBus.$emit(GlobalEvent.OnRefreshDesk,  {deskId:p.deskId,stopOrder:p.lightType===1} )
        }
      }

    },
    async queryLightTimer() {

      let time = this.$time().add(24,'hours').format("YYYY-MM-DD HH:mm:ss");
      let res = await listLightTimer(time);
      lightTimerList = (res.data || []).filter(p => p.enable);
      console.log("定时器",lightTimerList)
    },
    onReload() {
      this.reload();
    },
    reload() {
      this.ifRouterAlive = false;
      this.$nextTick(() => {
        this.ifRouterAlive = true
      })
    },
    onDeviceTest() {
      let obj = {deskNo: 1, open: true}
      let type = "lightSwitch";

      callPCMethod(type, obj).then(val => {
        console.log("--returen:" + val)
        this.$modal.msgSuccess("oOK:" + val)
      })
      // this.$registerPCMethod(type+msgId,p=>{
      //   this.$modal.msgWarning(type+p);
      //   this.$removePCMethod( type+msgId);
      // })

      //    this.$registerPCMethod(type,)

    }
  }
}
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  height: calc(100vh);
  width: 100%;
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.fixed-header + .app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header + .app-main {
    padding-top: 84px;
  }
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 6px;
  }
}

::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background-color: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background-color: #c0c0c0;
  border-radius: 3px;
}
</style>
