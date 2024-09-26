<template>
  <div id="app">
    <router-view/>
    <theme-picker/>
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";
import {DeviceCallbackMethodName, registerMethod} from "@/utils/pcCommunication";
import {deskAddScore, deskCapture} from "@/api/cashier/desk";

export default {
  name: "App",
  components: {ThemePicker},
  created() {
    this.initSomePCCallBackMethods()
  },
  methods: {
    initSomePCCallBackMethods() {
      registerMethod(DeviceCallbackMethodName.AddScore, this.addScore);
      registerMethod(DeviceCallbackMethodName.UserCapture, this.userCapture)
    },
    addScore({data}) {
      console.log("加分",JSON.stringify(data))
      if(!data.result){
        return
      }
      deskAddScore(data)
    },
    userCapture({data}) {
      console.log("拍照",JSON.stringify(data))
      deskCapture(data)
    },

  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  }
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
