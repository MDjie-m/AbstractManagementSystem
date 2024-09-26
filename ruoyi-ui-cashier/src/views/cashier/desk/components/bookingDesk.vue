<template>
  <div class="   input-container">

    <div class="  container-wrapper" v-show="showList">
      <div class="section-container desk-filter-box">

        <el-select v-model="deskQueryParams.day" @change="getDeskList">
          <el-option :value="item" v-for="item in deskQueryParams.dayList" :label="item"></el-option>
        </el-select>
        <el-time-select
          placeholder="起始时间" :clearable="false"
          v-model="deskQueryParams.startTime" @change="getDeskList"
          :picker-options="{  start: '00:00',  step: '00:30',  end: '24:00'  }">
        </el-time-select>
        <el-time-select
          placeholder="结束时间" @change="getDeskList"
          v-model="deskQueryParams.endTime" :clearable="false"
          :picker-options="{  start: '00:00', step: '00:30',   end: '24:00',  minTime: deskQueryParams.startTime
              }"/>

        <el-button v-loading="loading" type="primary" icon="el-icon-search" circle @click="getDeskList"></el-button>
        <custom-tip content="选择时间段,可查询当前时间段台桌预约数量"></custom-tip>

      </div>
      <div class="section-container desk-items-box">
        <template v-for="placeItem in dict.type.store_desk_place">
          <el-divider content-position="left" :key="'typeDesk'+placeItem.value">{{ placeItem.label }}</el-divider>
          <div class="desk-container">
            <el-card @click.native="onDeskClick(desk)" class="desk-item" :class="{'selected':desk.selected}"
                     v-for="desk in deskList.filter(p=>  p.placeType === parseInt(placeItem.value))">
              <div class="item-status" :class="`item-status-${desk.status}`"></div>
              <div>
                <div class="desk-item-name"> {{ desk.deskName }}</div>
                <div class="desk-item-num"> {{ desk.deskNum }}</div>
                <div class="desk-item-price"> {{ desk.price }}元/分钟</div>
                <div class="desk-item-price"> 预约: <span class="desk-item-count"
                                                          :class="{'success':desk.bookingCount==='0' }">{{
                    desk.bookingCount
                  }}</span></div>
              </div>
            </el-card>

          </div>
        </template>
      </div>
    </div>
    <div class="container-wrapper" v-if="selectedDesk && !showList">
      <div class="section-container btn-container">
        <el-button type="primary" icon="el-icon-back" @click="showList=true" circle></el-button>
        <div>当前台桌：{{ selectedDesk.title }}</div>
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
                     v-if="item.status!==DeskBookingStatus.Used"
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

import {addDeskBooking, delDeskBooking, getBookingMap, listDesk} from "@/api/cashier/desk";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {DeskBookingStatus, formatTime} from "@/views/cashier/components/constant";
import CustomTip from "@/views/cashier/components/customTip.vue";

export default {
  components: {CustomTip, CustomDialog},
  props: ['memberId'],
  emits: ["ok"],
  dicts: ['store_desk_status', 'store_desk_type', 'store_desk_place'],
  computed: {
    DeskBookingStatus() {
      return DeskBookingStatus
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
      deskList: [],
      showAdd: false,
      timeOptions: {
        start: '00:01',
        step: '00:30',
        end: '24:00',
      },
      deskQueryParams: {
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
      selectedDesk: null,
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
    this.deskQueryParams.day=this.$time().format("YYYY-MM-DD")
    this.deskQueryParams.startTime = this.$time().format("HH:00")
    this.deskQueryParams.endTime = this.$time().add(2, 'hour').format("HH:00")
    this.getDeskList();
  },
  methods: {
    initDayList() {
      let time = this.$time();
      let list = [];
      for (let i = 0; i < 7; i++) {
        list.push(time.add(i, 'day').format("YYYY-MM-DD"))
      }
      this.deskQueryParams.dayList = list;
    },
    getDeskList() {
      this.loading = true
      listDesk({
        bookingCount: 1,
        bookingStart: `${this.deskQueryParams.day} ${this.deskQueryParams.startTime}`,
        bookingEnd: `${this.deskQueryParams.day} ${this.deskQueryParams.endTime}`
      }).then(res => {
        this.deskList = res.data;
      }).finally(() => this.loading = false)
    },
    onRemoveBookingClick(day, idx) {
      let item = this.bookingMap[day][idx];
      let id = item.deskBookingId
      this.$confirm(`确认删除${formatTime(item.startTime, 'HH:mm')}到${formatTime(item.endTime, 'HH:mm')}的预约？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delDeskBooking(id).then(res => {
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
        return addDeskBooking({
          deskId: this.selectedDesk.deskId,
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
    onDeskClick(item) {
      this.selectedDesk = item;
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

      getBookingMap({
        deskId: this.selectedDesk?.deskId ?? -1,
        startTime: this.$time(this.currentMonth).format('YYYY-MM-DD 00:00:00'),
        endTime: this.$time(this.currentMonth).add(7, 'day').format('YYYY-MM-DD 23:59:59'),
      }).then(res => {
        this.bookingMap = res.data || {};
      })
    }
  }

}
</script>

<style scoped lang="scss" src="./bookingDesk.scss">


</style>
