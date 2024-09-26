<template>
  <div class="   input-container">

    <div class="  container-wrapper" v-show="showList">
      <div class="section-container tutor-filter-box">

        <el-select v-model="queryParams.day" @change="getTutorList">
          <el-option :value="item" v-for="item in queryParams.dayList" :label="item"></el-option>
        </el-select>
        <el-time-select
          placeholder="起始时间" :clearable="false"
          v-model="queryParams.startTime" @change="getTutorList"
          :picker-options="{  start: '00:00',  step: '00:30',  end: '24:00'  }">
        </el-time-select>
        <el-time-select
          placeholder="结束时间" @change="getTutorList"
          v-model="queryParams.endTime" :clearable="false"
          :picker-options="{  start: '00:00', step: '00:30',   end: '24:00',  minTime: queryParams.startTime
              }"/>

        <el-button v-loading="loading" type="primary" icon="el-icon-search" circle @click="getTutorList"></el-button>
        <custom-tip content="选择时间段,可查询当前时间段教练预约数量"></custom-tip>

      </div>
      <div class="section-container tutor-items-box">
        <div class="tutor-container">
          <el-card @click.native="onTutorClick(item)" class="tutor-item" :class="{'selected':item.selected}"
                   v-for="item in tutorList">
            <div class="item-status" :class="`item-status-${item.workStatus}`"></div>
            <image-preview class="item-img big" :src="item.userImg"/>
            <div>
              <div class="tutor-item-name"> {{ item.realName }}</div>
              <div class="tutor-item-price"> {{ item.price }}元/分钟</div>
            </div>
            <div class="tutor-item-price"> 预约: <span class="tutor-item-count"
                                                      :class="{'success':item.bookingCount==='0' }">
                {{ item.bookingCount }}</span>
            </div>

          </el-card>

        </div>
      </div>
    </div>
    <div class="container-wrapper" v-if="selectedItem && !showList">
      <div class="section-container btn-container">
        <el-button type="primary" icon="el-icon-back" @click="onBackClick" circle></el-button>
        <div>当前台桌：{{ selectedItem.title }}</div>
        <div>
          <el-date-picker
            v-model="currentMonth" @change="onMonthChanged"
            type="date" :clearable="false"
            placeholder="请选择开始时间">
          </el-date-picker>
        </div>
        <custom-tip content="选择开始时间，可查询最近七天的预约"></custom-tip>
      </div>
      <div class=" section-container cus-calendar" v-model="calendarValue">
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

              <div class="tag-item" :class="'tag-item-'+item.status" v-for="(item,idx) in getDayBooks(day)">
                <div style="display: flex; align-items: center">
                  <div>
                    <div> {{ item.startTime|timeFormat('HH:mm') }}~{{ item.endTime|timeFormat('HH:mm') }}</div>
                    <div style="padding-left: 5px"> {{ item.bookingUserName }} &nbsp; {{ item.bookingUserMobile }}</div>
                  </div>


                  <i class="el-icon-remove"
                     v-if="item.status!==BookingStatus.Used"
                     @click="onRemoveBookingClick(day,idx)"/>
                </div>
              </div>

            </div>


          </div>
        </div>
      </div>

    </div>
    <custom-dialog :visible.sync="showAdd" width="400px" title="添加预约" :on-ok="onSubmitBooking">
      <el-form ref="form" label-width="120px" :model="bookingForm" :rules="bookingRules">
        <el-form-item label="开始时间：" prop="startTime">
          <el-time-select
            :clearable="false"
            v-model="bookingForm.startTime"
            :picker-options="timeOptions"
            placeholder="请选择开始时间">
          </el-time-select>

        </el-form-item>
        <el-form-item label="结束时间：" prop="endTime">
          <el-time-select
            :clearable="false"

            v-model="bookingForm.endTime"
            :picker-options="{   ...timeOptions}"
            placeholder="请选择结束时间">
          </el-time-select>
        </el-form-item>
        <el-form-item label="预约人姓名：" prop="bookingUserName">
          <el-input maxlength="30"
                    v-model="bookingForm.bookingUserName"
                    placeholder="请输入姓名">
          </el-input>
        </el-form-item>
        <el-form-item label="预约人手机：" prop="bookingUserMobile">
          <el-input maxlength="11"
                    v-model="bookingForm.bookingUserMobile"
                    placeholder="请输入手机号">
          </el-input>
        </el-form-item>
      </el-form>
    </custom-dialog>
  </div>
</template>
<script>

import {addTutorBooking, delTutorBooking, getTutorBookingMap, listAllTutor} from "@/api/cashier/tutor";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {BookingStatus, formatTime} from "@/views/cashier/components/constant";
import CustomTip from "@/views/cashier/components/customTip.vue";

export default {
  components: {CustomTip, CustomDialog},

  emits: ["ok"],
  dicts: [ ],
  computed: {
    BookingStatus() {
      return BookingStatus
    }
  },

  data() {

    const validateTime = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请选择时间"));
      }
      if (this.bookingForm.startTime >= this.bookingForm.endTime) {
        return callback(new Error(`开始时间必须小于结束时间`))
      }
      let tempTime = `${this.bookingForm.day} ${value}`;
      let bookingList = this.bookingMap[this.bookingForm.day] || [];
      if (bookingList.find(p => p.startTime < tempTime && tempTime < p.endTime)) {
        return callback(new Error(`当前时间段已存在预约`))
      }
      callback();

    };
    return {
      tutorList: [],
      showAdd: false,
      timeOptions: {
        start: '00:01',
        step: '00:30',
        end: '24:00',
      },
      queryParams: {
        day: this.$time().format("YYYY-MM-DD"),
        startTime: "00:00",
        endTime: "24:00",
        dayList: [],
      },
      bookingRules: {
        startTime: [
          {required: true, message: "开始时间不能为空", trigger: "blur"},
          {required: true, validator: validateTime, trigger: "blur"}],
        endTime: [
          {required: true, message: "结束时间不能为空", trigger: "blur"},
          {required: true, validator: validateTime, trigger: "blur"}
        ],
        bookingUserName: [
          {required: true, message: "姓名不能为空", trigger: "blur"}],
        bookingUserMobile: [
          {required: true, message: "姓名不能为空", trigger: "blur"},
          {
            validator: function (rule, value, callback) {
              if (!/^(1)\d{10}$/.test(value)) {
                callback(new Error("请输入正确的手机号"));
              } else {
                //校验通过
                callback();
              }
            }, trigger: 'blur'
          },],
      },
      bookingForm: {
        day: null,
        startTime: null,
        endTime: null,
        bookingUserName: null,
        bookingUserMobile: null
      },
      calendarValue: this.$time().startOf('d').format('YYYY-MM-DD'),
      showList: true,
      selectedItem: null,
      currentMonth: this.$time().format('YYYY-MM-DD'),
      startTime: this.$time().startOf('d').format('YYYY-MM-DD'),
      endTime: this.$time().startOf('d').add(7, 'd').format('YYYY-MM-DD'),
      timeRange: [],
      bookingMap: {},
      loading: false,
    }
  },
  created() {


  },
  mounted() {
    this.initDayList()
    this.onMonthChanged();

    this.queryParams.day = this.$time().format("YYYY-MM-DD")
    this.queryParams.startTime = this.$time().format("HH:00")
    this.queryParams.endTime = this.$time().add(2, 'hour').format("HH:00")
    this.getTutorList();
  },
  methods: {
    onBackClick(){
      this.showList=true;
      this.getTutorList();
    },
    initDayList() {
      let time = this.$time();
      let list = [];
      for (let i = 0; i < 7; i++) {
        list.push(time.add(i, 'day').format("YYYY-MM-DD"))
      }
      this.queryParams.dayList = list;
    },
    getTutorList() {
      this.loading = true
      listAllTutor({
        bookingCount: 1,
        bookingStart: `${this.queryParams.day} ${this.queryParams.startTime}`,
        bookingEnd: `${this.queryParams.day} ${this.queryParams.endTime}`
      }).then(res => {
        this.tutorList = res.data;
      }).finally(() => this.loading = false)
    },
    onRemoveBookingClick(day, idx) {
      let item = this.bookingMap[day][idx];
      let id = item.tutorBookingId
      this.$confirm(`确认删除${formatTime(item.startTime, 'HH:mm')}到${formatTime(item.endTime, 'HH:mm')}的预约？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delTutorBooking(id).then(res => {
          this.bookingMap[day].splice(idx, 1);
          this.$modal.msgSuccess("操作成功")
        })
      }).catch(() => {
      });

    },
    onAddClick(day) {
      this.bookingForm = {
        day: day,
        startTime: null,
        endTime: null,
        bookingUserName: null,
        bookingUserMobile: null
      };

      this.timeOptions.start = day > this.$time().format("YYYY-MM-DD") ? '00:00' : this.$time().format("HH:00")

      this.showAdd = true
    },
    onSubmitBooking() {
      return this.$refs["form"].validate().then(valid => {
        if (!valid) {
          return Promise.reject()
        }
        return addTutorBooking({
          tutorId: this.selectedItem.storeTutorId,
          startTime: `${this.bookingForm.day} ${this.bookingForm.startTime}`,
          endTime: `${this.bookingForm.day} ${this.bookingForm.endTime}`,
          bookingUserName: this.bookingForm.bookingUserName,
          bookingUserMobile: this.bookingForm.bookingUserMobile
        }).then(res => {
          this.$modal.msgSuccess("添加成功");
          let tempList = this.bookingMap[this.bookingForm.day] || [];
          tempList.push(res.data);
          let tempMap = {
            ...this.bookingMap,
          }
          tempMap[this.bookingForm.day] = tempList;
          this.bookingMap = tempMap;

        });
      });

    },

    addAble(time) {
      time = this.$time(time).format("YYYY-MM-DD");
      return time >= this.startTime && time <= this.endTime
    },
    onTutorClick(item) {
      this.selectedItem = item;
      this.showList = false;
      this.queryBookings();
    },
    getDayBooks(day) {
      return this.bookingMap[day || ''] || []
    },
    onMonthChanged() {
      if (!this.currentMonth) {
        this.currentMonth = this.$time().format("YYYY-MM-DD")
      }

      let dayList = [];
      for (let i = 0; i < 7; i++) {
        dayList.push(this.$time(this.currentMonth).add(i, 'day').format("YYYY-MM-DD"))
      }
      this.timeRange = dayList;
      this.queryBookings();
    },
    queryBookings() {

      getTutorBookingMap({
        storeTutorId: this.selectedItem?.storeTutorId ?? -1,
        startTime: this.$time(this.currentMonth).format('YYYY-MM-DD 00:00:00'),
        endTime: this.$time(this.currentMonth).add(7, 'day').format('YYYY-MM-DD 23:59:59'),
      }).then(res => {
        this.bookingMap = res.data || {};
      })
    }
  }

}
</script>

<style scoped lang="scss" src="./index.scss">


</style>
