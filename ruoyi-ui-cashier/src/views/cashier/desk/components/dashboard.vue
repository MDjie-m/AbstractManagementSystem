<template>
  <div class="menu-wrapper">


    <div class="  section-container menu-container">
      <div class="menu-title">
        球桌概览
      </div>
      <div class="icon-container">
        <div class="sub-item">
          <el-badge :value="deskTotal.deskWaitCount" :hidden="!deskTotal.deskWaitCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="clock_wait"/>
          </el-badge>
          <div class="sub-item-text">
            空闲
          </div>
        </div>
        <div class="sub-item">
          <el-badge :value="deskTotal.deskBusyCount" :hidden="!deskTotal.deskBusyCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="clock_busy"/>
          </el-badge>
          <div class="sub-item-text">
            计费中
          </div>
        </div>
        <div class="sub-item">
          <el-badge :value="deskTotal.deskStopCount" :hidden="!deskTotal.deskStopCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="clock_stop"/>
          </el-badge>
          <div class="sub-item-text">
            已停止
          </div>
        </div>
        <div class="sub-item">
          <el-badge :value="lightOpenCount" :hidden="!lightOpenCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="light_on"/>
          </el-badge>
          <div class="sub-item-text">
            已开灯
          </div>
        </div>

      </div>

    </div>
    <div class="  section-container menu-container">
      <div class="menu-title">
        教练概览
      </div>
      <div class="icon-container">
        <div class="sub-item">
          <el-badge :value="deskTotal.tutorWaitCount" :hidden="!deskTotal.tutorWaitCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="tutor_wait"/>
          </el-badge>
          <div class="sub-item-text">
            空闲
          </div>
        </div>
        <div class="sub-item">
          <el-badge :value="deskTotal.tutorBusyCount" :hidden="!deskTotal.tutorBusyCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="tutor_busy"/>
          </el-badge>
          <div class="sub-item-text">
            计费中
          </div>
        </div>
        <div class="sub-item">
          <el-badge :value="deskTotal.tutorStopCount" :hidden="!deskTotal.tutorStopCount" class="icon-tip"
                    type="primary">
            <svg-icon icon-class="tutor_stop"/>
          </el-badge>
          <div class="sub-item-text">
            已停止
          </div>
        </div>


      </div>

    </div>
    <div class="  section-container menu-container">
      <div class="menu-title">
        预约/排队
      </div>
      <div class="icon-container">
        <div class="sub-item">
          <svg-icon icon-class="desk"/>

          <div class="sub-item-text">
            台桌预约
          </div>
        </div>
        <div class="sub-item">
          <svg-icon icon-class="tutor"/>

          <div class="sub-item-text">
            教练预约
          </div>
        </div>
        <div class="sub-item">

          <svg-icon icon-class="qrcode"/>

          <div class="sub-item-text">
            预约核销
          </div>
        </div>

        <div class="sub-item" @click="onBtnClick(InvokeMethodName.LineUp,'排队叫号')">
          <svg-icon icon-class="line_up"/>
          <div class="sub-item-text" >
            排队叫号
          </div>
        </div>
      </div>

    </div>
    <el-dialog title="排队叫号"   :close-on-click-modal="false"  :visible.sync="openLineUp" width="80%"  style="height: 80%" append-to-body >
       <LineUp/>
    </el-dialog>
  </div>
</template>
<script >

import { listDeskDashboard} from "@/api/cashier/desk";
import LineUp from "@/views/cashier/desk/components/lineUp.vue";
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
const OnBtnClickEvent="onBtnClick"
export  const  InvokeMethodName={
  LineUp:"lineUp"
}
export  default {
  emits:[OnBtnClickEvent],
  components: {LineUp},
  props:["storeName"],
  data(){
    return {
      InvokeMethodName:InvokeMethodName,
      openLineUp:false,
      lightOpenCount:0,
      deskTotal: {
        deskWaitCount: 0,
        deskBusyCount: 0,

        deskStopCount: 0,

        deskLightOnCount: 0,

        tutorWaitCount: 0,
        tutorBusyCount: 0,
        tutorStopCount: 0,
      },
    }
  },
  created() {
    this.refresh()
  },

  methods:{

    onBtnClick(type,title){
      this.$emit(OnBtnClickEvent,type,title)
    },
    getList(){
      listDeskDashboard().then(res=>{
        this.deskTotal=res.data|| {};
      })
    },
    getOpenLightCount(){
      callPCMethod(DeviceMethodNames.LightStateQuery, {}).then(res => {
          this.lightOpenCount= res.data.openCount||0
      })
    },
    refresh(){
      this.getList();
      this.getOpenLightCount();
    }
  }
}
</script>
<style scoped lang="scss" src="./dashboard.scss"></style>
