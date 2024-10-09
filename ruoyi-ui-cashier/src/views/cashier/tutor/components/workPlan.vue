<template>
  <div class="   input-container">
    <div class="container-wrapper" v-if="tutor ">
      <div class="section-container btn-container">

        <div>当前教练：{{ tutor.title }}</div>
        <div>
          <el-date-picker
            v-model="currentDay" @change="onDayChanged"
            type="date" :clearable="false"
            placeholder="请选择开始时间">
          </el-date-picker>
        </div>
        <custom-tip content="选择开始时间，可查询最近七天的排班"></custom-tip>
      </div>
      <div class=" section-container cus-calendar"  >
        <div class="day-header-box">
          <div class="day-header" v-for="day in timeRange">
            <div>
              <div> {{ day }}</div>
              <div>{{ day|week }}</div>
              <el-button @click="onAddClick(day)" size="mini" type="primary" circle icon="el-icon-plus "
                         class=" btn"
                         v-if="addAble(day)"></el-button>
            </div>

          </div>
        </div>
        <div class="day-content-box">


          <div class="day-item" v-for="day in timeRange">


            <div class="tag-container">

              <div class="tag-item" :class="'tag-item-'+item.planType" v-for="(item,idx) in getDayValues(day)">

                  <div style="flex: 1;align-items: center;display: flex;justify-content: center">
                    <div> {{ item.startTime|timeFormat('HH:mm') }}~{{ item.endTime|timeFormat('HH:mm') }}</div>
                    <div style="padding-left: 5px">  </div>
                  </div>
                <div style="width: 20px">
                  <i class="el-icon-remove"

                     @click="onRemoveBookingClick(day,idx)"/>
                </div>



              </div>

            </div>


          </div>
        </div>
      </div>

    </div>
    <custom-dialog :visible.sync="showAdd" width="400px" title="添加预约" :on-ok="onSubmit ">
      <el-form ref="form" label-width="120px" :model="formModel" :rules="formRules">
        <el-form-item label="开始时间：" prop="startTime">
          <el-time-select
            :clearable="false"
            v-model="formModel.startTime"
            :picker-options="timeOptions"
            placeholder="请选择开始时间">
          </el-time-select>

        </el-form-item>
        <el-form-item label="结束时间：" prop="endTime">
          <el-time-select
            :clearable="false"

            v-model="formModel.endTime"
            :picker-options="{   ...timeOptions}"
            placeholder="请选择结束时间">
          </el-time-select>
        </el-form-item>
        <el-form-item label="类型：" prop="planType">
          <el-select
            :clearable="false"

            v-model="formModel.planType"
            placeholder="请选择类型">
            <el-option  :value="WorkPlanType[key].value" :label="WorkPlanType[key].label" :key="'llllaaaa'+key" v-for="key in Object.keys(WorkPlanType)"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </custom-dialog>
  </div>
</template>
<script>
import CustomTip from "@/views/cashier/components/customTip.vue";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {formatTime, WorkPlanType} from "@/views/cashier/components/constant";
import {addTutorPlan, delTutorPlan,  getTutorPlanMap} from "@/api/cashier/tutor";

export default {
  computed: {
    WorkPlanType() {
      return WorkPlanType
    }
  },
  components: {CustomDialog, CustomTip},
  props:['tutor','day'],
  data(){
    const validateTime = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请选择时间"));
      }
      if (this.formModel.startTime >= this.formModel.endTime) {
        return callback(new Error(`开始时间必须小于结束时间`))
      }
      let tempTime = `${this.formModel.day} ${value}`;
      let bookingList = this.planMap[this.formModel.day] || [];
      if (bookingList.find(p => p.startTime < tempTime && tempTime < p.endTime)) {
        return callback(new Error(`当前时间段已存在排课`))
      }
      callback();

    };
    return{
      showAdd:false,
      startTime: this.$time().startOf('d').format('YYYY-MM-DD'),
      endTime: this.$time().startOf('d').add(7, 'd').format('YYYY-MM-DD'),
      planMap:{},
      timeOptions: {
        start: '00:01',
        step: '00:30',
        end: '24:00',
      },
      currentDay: this.$time().format('YYYY-MM-DD'),
      timeRange:[],
      formRules:{},
      formModel:{
        startTime: [
          {required: true, message: "开始时间不能为空", trigger: "blur"},
          {required: true, validator: validateTime, trigger: "blur"}],
        endTime: [
          {required: true, message: "结束时间不能为空", trigger: "blur"},
          {required: true, validator: validateTime, trigger: "blur"}
        ],
      }
    }
  },mounted() {
    this.onDayChanged()
  },
  methods:{
    getDayValues(day){
      return this.planMap[day || ''] || []
    },
    addAble(time) {
      time = this.$time(time).format("YYYY-MM-DD");
      return time >= this.startTime && time <= this.endTime
    },
    onAddClick(day){
      this.formModel = {
        day: day,
        startTime: null,
        tutorId:this.tutor.storeTutorId,
        endTime: null,
        planType:WorkPlanType.Play.value
      };

      this.timeOptions.start = day > this.$time().format("YYYY-MM-DD") ? '00:00' : this.$time().format("HH:00")

      this.showAdd = true
    },
    onSubmit(){
      return this.$refs["form"].validate().then(valid => {

        if (!valid) {
          return Promise.reject()
        }
        return addTutorPlan({
          tutorId: this.formModel.tutorId,
          startTime: `${this.formModel.day} ${this.formModel.startTime}`,
          endTime: `${this.formModel.day} ${this.formModel.endTime}`,
          planType: this.formModel.planType,
        }).then(res => {
          this.$modal.msgSuccess("添加成功");
          let tempList = this.planMap[this.formModel.day] || [];
          tempList.push(res.data);
          let tempMap = {
            ...this.planMap,
          }
          tempMap[this.formModel.day] = tempList;
          this.planMap = tempMap;

        });
      });
    },
    onRemoveBookingClick(day, idx) {
      let item = this.planMap[day][idx];

      let id = item.tutorWorkPlanDetailId
      this.$confirm(`确认删除${formatTime(item.startTime, 'HH:mm')}到${formatTime(item.endTime, 'HH:mm')}的排课？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delTutorPlan(id).then(res => {
          this.planMap[day].splice(idx, 1);
          this.$modal.msgSuccess("操作成功")
        })
      }).catch(() => {
      });
    },
    onDayChanged(){
      if(!this.currentDay){
        this.currentDay=this.$time().format('YYYY-MM-DD')
      }
      let dayList = [];
      for (let i = 0; i < 7; i++) {
        dayList.push(this.$time(this.currentDay).add(i, 'day').format("YYYY-MM-DD"))
      }
      this.timeRange = dayList;
      this.queryPlans();
    },
    queryPlans() {
      getTutorPlanMap({
        tutorId: this.tutor?.storeTutorId ?? -1,
        startTime: this.$time(this.currentDay).format('YYYY-MM-DD 00:00:00'),
        endTime: this.$time(this.currentDay).add(7, 'day').format('YYYY-MM-DD 23:59:59'),
      }).then(res => {
        this.planMap = res.data || {};
      })
    }
  }
}
</script>
<style scoped lang="scss" src="./workPlan.scss">

</style>
