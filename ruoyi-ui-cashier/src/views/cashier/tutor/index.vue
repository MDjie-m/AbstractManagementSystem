<template>

  <div class="page-container">

    <!--    <left-container @onRefreshClick="onRefreshClick" :hide-store-info="currentItem">-->
    <!--    </left-container>-->
    <div class="right-panel">

      <div class="  section-container ">
        <el-form :inline="true" autocomplete="off">
          <el-form-item label="班次">
            <el-date-picker
              v-model=" queryParams.scheduleDay" @change="getTutorList" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
              type="date" :clearable="false"
              placeholder="请选择班次">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="姓名/编号">
            <el-input maxlength="10" auto-complete="new-password"
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

              <div class="some-item-name"> {{ item.title }}</div>
              <div class="some-item-punch-in">
                <div>上班:</div>
                <div v-if="item.punchIn">{{ item.punchIn.startTime |timeFormat("MM-DD HH:mm") }}
                </div>

              </div>
              <div class="some-item-punch-in">
                <div>下班:</div>
                <div v-if="item.punchIn">{{ item.punchIn.endTime |timeFormat("MM-DD HH:mm") }}
                </div>

              </div>
              <div class="some-item-btn-box">
                <svg-icon class="some-item-svg"
                          :class="{'punch-in':item.punchIn && item.punchIn.startTime ,success:item.punchIn && item.punchIn.startTime && item.punchIn.endTime}"
                          icon-class="punch_in"
                          @click.stop="onPunchInClick(item,true)"></svg-icon>
                <el-badge :value="item.planCount" :hidden="!item.planCount" class="icon-tip"
                          type="primary">
                  <svg-icon class="some-item-svg punch-in" icon-class="work_plan"
                            @click.stop="onWorkPlanClick(item,true)"></svg-icon>
                </el-badge>


              </div>




          </el-card>
        </div>
      </div>
      <content-wrapper :visible.sync="openNewDialog" title="排课" @onClose="getTutorList">
        <work-plan :tutor="currentItem" :day="queryParams.scheduleDay"></work-plan>
      </content-wrapper>
    </div>

  </div>
</template>
<script>
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {listAllTutor, tutorPunchIn} from "@/api/cashier/tutor";
import {MessageBox} from "element-ui";
import ContentWrapper from "@/views/cashier/desk/components/contentWrapper.vue";
import WorkPlan from "@/views/cashier/tutor/components/workPlan.vue";

export default {
  components: {WorkPlan, ContentWrapper, LeftContainer},
  data() {
    return {

      openNewDialog: false,
      currentItem: null,
      queryParams: {
        scheduleDay: this.$time().format("YYYY-MM-DD"),
        keyword: '',
      },
      tutorList: [],
    }
  },
  mounted() {
    this.getTutorList()
  },
  methods: {
    onItemClick(item) {
      this.currentItem = item
    },
    onWorkPlanClick(item) {
      this.currentItem = item;
      this.openNewDialog = true;

    },
    onPunchInClick(item) {
      this.$modal.confirm("确认打卡").then(() => {
        tutorPunchIn({
          tutorId: item.storeTutorId,
          scheduleDay: this.queryParams.scheduleDay
        }).then(res => {
          this.$modal.msgSuccess("打卡成功")
          this.getTutorList()
        })
      })

    },
    getTutorList() {
      if (!this.queryParams.scheduleDay) {
        this.queryParams.scheduleDay = this.$time().format("YYYY-MM-DD")
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
