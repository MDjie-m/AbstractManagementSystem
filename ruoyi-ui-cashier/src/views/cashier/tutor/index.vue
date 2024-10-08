<template>

  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick">
    </left-container>
    <div class="right-panel">

      <div class="  section-container ">
        <el-form :inline="true">
          <el-form-item label="打卡班次">
            <el-date-picker
              v-model=" queryParams.scheduleDay" @change="getTutorList" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
              type="date" :clearable="false"
              placeholder="请选择打卡班次">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </div>

      <div class="  section-container buy-box">


        <div class="buy-container">
          <el-card class="some-item" :class="{'selected':item.selected}"
                   v-for="item in  tutorList">
            <div class="item-status" :class="`item-status-${item.workStatus}`"></div>
            <image-preview class="item-img big" :src="item.userImg"/>
            <div>
              <div class="some-item-name"> {{ item.title }}</div>
              <div class="some-item-punch-in"  >
                <span>上班:</span>
                <span v-if="item.punchIn">{{ item.punchIn.startTime |timeFormat("HH:mm") }}
                </span>

              </div>
              <div class="some-item-punch-in"  >
                <span>下班:</span>
                <span v-if="item.punchIn">{{ item.punchIn.endTime |timeFormat("HH:mm") }}
                </span>

              </div>
              <svg-icon class="some-item-svg" icon-class="punch_in"
                        @click="onPunchInClick(item,true)"></svg-icon>
            </div>


          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {listAllTutor, tutorPunchIn} from "@/api/cashier/tutor";

export default {
  components: {LeftContainer},
  data() {
    return {
      queryParams: {
        scheduleDay: this.$time().format("YYYY-MM-DD")
      },
      tutorList: [],
    }
  },
  mounted() {
    this.getTutorList()
  },
  methods: {
    onPunchInClick(item) {
      tutorPunchIn({tutorId:item.storeTutorId,
        scheduleDay:this.queryParams.scheduleDay}).then(res=>{
          this.$modal.msgSuccess("打卡成功")
          this.getTutorList()
      })
    },
    getTutorList() {
      if(!this.queryParams.scheduleDay){
        this.queryParams.scheduleDay=this.$time().format("YYYY-MM-DD")
      }
      listAllTutor(this.queryParams).then(res => {
        this.tutorList = res.data
      })
    },
    onRefreshClick() {

    }
  }
}
</script>
<style lang="scss" scoped src="./tutor.scss">

</style>
