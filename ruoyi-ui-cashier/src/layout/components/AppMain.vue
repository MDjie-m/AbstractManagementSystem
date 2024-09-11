<template>
  <section class="app-main">
    {{ $route.meta }}
    <el-button @click="onDeviceTest">dd</el-button>
    <transition name="fade-transform" mode="out-in">
      <router-view v-if="!$route.meta.link" :key="key"/>
    </transition>
    <iframe-toggle/>
  </section>
</template>

<script>
import iframeToggle from "./IframeToggle/index"

export default {
  name: 'AppMain',
  components: {iframeToggle},
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.path
    }
  }, methods: {
    onDeviceTest() {
      let obj = {deskNo: 1, open: true}
      let type = "lightSwitch";
      let msgId = "1111";



      let res = window.DeviceMethod?.callMethd(type, JSON.stringify(obj), msgId);
      let promise = new Promise((resolve => {
        this.$modal.msgWarning("Call Run");
        this.$registerPCMethod(type+msgId,resolve)
      })).finally(()=>{
        this.$removePCMethod(type+msgId)
      });



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
  min-height: calc(100vh - 50px);
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
