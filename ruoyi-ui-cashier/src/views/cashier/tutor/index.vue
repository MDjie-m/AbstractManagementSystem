<template>

  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick" :hide-store-info="currentItem">
    </left-container>
    <div class="right-panel">

      <div class="  section-container ">
        <el-form :inline="true"  autocomplete="off">
          <el-form-item label="班次">
            <el-date-picker
              v-model=" queryParams.scheduleDay" @change="getTutorList" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
              type="date" :clearable="false"
              placeholder="请选择班次">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="姓名/编号">
            <el-input maxlength="10"  auto-complete="new-password"
                      v-model=" queryParams.keyword" @change="getTutorList" @keydown.enter.native="getTutorList"
               :clearable="true"
              placeholder="输入姓名或者编号">
            </el-input>
          </el-form-item>
        </el-form>
      </div>

      <div class="  section-container buy-box">


        <div class="buy-container">
          <el-card class="some-item" @click.native.stop="onItemClick(item)" :class="{'selected':item.selected}"
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
              <div class="some-item-btn-box">
                <svg-icon class="some-item-svg" :class="{'punch-in':item.punchIn && item.punchIn.startTime ,success:item.punchIn && item.punchIn.startTime && item.punchIn.endTime}" icon-class="punch_in"
                          @click.stop="onPunchInClick(item,true)"></svg-icon>
                <svg-icon  class="some-item-svg punch-in"   icon-class="work_plan"
                          @click.stop="onWorkPlanClick(item,true)"></svg-icon>

              </div>

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
import {MessageBox} from "element-ui";

export default {
  components: {LeftContainer},
  data() {
    return {
      currentItem:null,
      queryParams: {
        scheduleDay: this.$time().format("YYYY-MM-DD"),
        keyword:'',
      },
      tutorList: [],
    }
  },
  mounted() {
    this.getTutorList()
  },
  methods: {
    onItemClick(item){
      this.currentItem=item
    },
    onWorkPlanClick(item){

    },
    onPunchInClick(item) {
      this.$modal.confirm("确认打卡").then(()=>{
        tutorPunchIn({tutorId:item.storeTutorId,
          scheduleDay:this.queryParams.scheduleDay}).then(res=>{
          this.$modal.msgSuccess("打卡成功")
          this.getTutorList()
        })
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
