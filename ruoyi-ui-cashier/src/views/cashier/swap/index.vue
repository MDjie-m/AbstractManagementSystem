<template>
  <div class="  swap-wrapper">
    <div class="right-panel">
      <div class="section-container query-box">
        <el-form :inline="true">
          <el-form-item label="班次">
            <el-date-picker
              v-model="customTime" format="yyyy-MM-dd"
              value-format="yyyy-MM-dd" @change="getList"
              type="datetimerange" size="small"
              :picker-options="pickerOptions"
              range-separator="至"
              :clearable="false"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"/>
          </el-form-item>
          <el-form-item label="交班人">
            <el-input maxlength="20" size="small" v-model="queryParams.user" @change="getList"
                      placeholder="输入姓名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" type="primary" circle icon="el-icon-search" @click="getList"/>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" type="primary" circle @click="onShowSwapClick">
              <svg-icon icon-class="swap"></svg-icon>
            </el-button>
          </el-form-item>
        </el-form>
      </div>

      <div class="section-container table-box">
        <el-table :data="recordList" style="width: 100%;" height="100%">
          <el-table-column label="序号" type="index" align="center">
            <template slot-scope="scope">
              <span>{{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}</span>
            </template>
          </el-table-column>
          <el-table-column label="班次" align="center" prop="scheduleDay" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.scheduleDay, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="总营收" align="center" prop="total"/>
          <el-table-column label="账面现金" align="center" prop="cashTotal"/>
          <el-table-column label="台桌费用" align="center" prop="deskTotal"/>
          <el-table-column label="教练费用" align="center" prop="tutorTotal"/>
          <el-table-column label="商品费用" align="center" prop="goodsTotal"/>
          <el-table-column label="未结算金额/单数" align="center" prop="suspendOrderCount">
            <template slot-scope="scope">
              <span>
                {{ scope.row.notSettledOrderAmount }}
              </span>
              /
              <span style="font-size: 10px">
              {{ scope.row.notSettledOrderCount }}
              </span>
            </template>
          </el-table-column>
          <el-table-column label="挂单金额/单数" align="center" prop="suspendOrderCount">
            <template slot-scope="scope">
              <span>
                {{ scope.row.suspendOrderAmount }}
              </span>
              /
              <span style="font-size: 10px">
              {{ scope.row.suspendOrderCount }}
              </span>
            </template>
          </el-table-column>
          <el-table-column label="交班人" align="center" prop="createBy"/>
          <el-table-column label="交班时间" align="center" prop="createTime">
            <template slot-scope="scope">
              <span>{{ scope.row.createTime|timeFormat(TimeFormat.YYYY_MM_DD_HH_mm) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="备注" align="center" prop="remark"/>
        </el-table>


      </div>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

    </div>
    <custom-dialog title="交班信息确认" width="600px"  :confirm-text="confirmText" :visible.sync="openSwap" :onOk="onSubmitSwap">
      <el-form size="small">
        <el-form-item>
          <el-select v-model="swapForm.scheduleDay" @change="querySwapPreview" style="width: 150px">
            <el-option :value="item.value" :label="item.label" v-for="item in dayList">
            </el-option>
          </el-select>
          交班统计
        </el-form-item>

      </el-form>
      <el-descriptions :column="3" border>
        <el-descriptions-item label="班次信息" :span="3" >
          {{ swapPreview.startTime |timeFormat(TimeFormat.YYYY_MM_DD_HH_mm) }} &nbsp; 至&nbsp;{{ swapPreview.endTime|timeFormat(TimeFormat.YYYY_MM_DD_HH_mm)}}
        </el-descriptions-item>
        <el-descriptions-item label="总营收" :span="3">
          {{ swapPreview.total }}
        </el-descriptions-item>
        <el-descriptions-item label="台桌费">
          {{ swapPreview.deskTotal }}
        </el-descriptions-item>
        <el-descriptions-item label="教练费用">
          {{ swapPreview.tutorTotal }}
        </el-descriptions-item>
        <el-descriptions-item label="商品费用">
          {{ swapPreview.goodsTotal }}
        </el-descriptions-item>
        <el-descriptions-item label="未结算金额">
          {{ swapPreview.notSettledOrderAmount }}
        </el-descriptions-item>
        <el-descriptions-item label="未结算单数" :span="2">
          {{ swapPreview.notSettledOrderCount }}
        </el-descriptions-item>
        <el-descriptions-item label="挂单金额">
          {{ swapPreview.suspendOrderAmount }}
        </el-descriptions-item>
        <el-descriptions-item label="挂单单数" :span="2">
          {{ swapPreview.suspendOrderCount }}
        </el-descriptions-item>
        <el-descriptions-item label="交班备注" :span="3">
           <el-input v-model="swapForm.remark" type="textarea" maxlength="200" show-word-limit/>
        </el-descriptions-item>
      </el-descriptions>
    </custom-dialog>


  </div>
</template>
<script >
import {addSwap, getSwapPreview, querySwapRecordList} from "@/api/cashier/store";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {TimeFormat} from "@/views/cashier/components/constant";

export default {
  computed: {
    TimeFormat() {
      return TimeFormat
    }
  },
  components: {CustomDialog},
  data() {
    return {
      openSwap: false,
      dayList: [],
      swapForm: {
        scheduleDay: null,
        remark:''
      },
      confirmText:'',
      swapPreview: {
        "swapRecordId": 0,
        "total": 0.00,
        "cashTotal": 0.00,
        "deskTotal": 0.00,
        "tutorTotal": 0.00,
        "goodsTotal": 0.00,
        "totalWipeZero": 0.00,
        "suspendOrderCount": 0,
        "suspendOrderAmount": 0.00,
        notSettledOrderCount:0,
        notSettledOrderAmount:0.00,
        "scheduleDay": "2024-10-11",
        "params": {}
      },
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      customTime: [
        this.$time().add(-10, 'day').format('YYYY-MM-DD'),
        this.$time().format('YYYY-MM-DD')
      ],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        user: null,

        startTime: this.$time().add(-10, 'day').format('YYYY-MM-DD'),
        endTime: this.$time().format('YYYY-MM-DD'),
      },
      total: 0,
      recordList: [],
    }
  },
  mounted() {
    this.getList();

  },
  methods: {
    onSubmitSwap() {
      return addSwap(this.swapForm).then(res => {
        this.$modal.msgSuccess("交班成功")
        this.getList()
      })
    },
    onShowSwapClick() {
      this.dayList = [
        {
          value: this.$time().format(TimeFormat.YYYY_MM_DD),
          label: this.$time().format(TimeFormat.YYYYcMMcDDc),
        },
        {
          value: this.$time().add(-1, 'day').format(TimeFormat.YYYY_MM_DD),
          label: this.$time().add(-1, 'day').format(TimeFormat.YYYYcMMcDDc),
        },
      ]
      this.swapForm.scheduleDay = this.dayList[0].value;
      this.swapForm.remark=''
      this.querySwapPreview();
    },
    querySwapPreview() {
      getSwapPreview({
        startTime: this.swapForm.scheduleDay,
        endTime: this.swapForm.scheduleDay
      }).then(res => {
        this.swapPreview = res.data || {};
        this.openSwap = true;
        this.confirmText=this.swapPreview.notSettledOrderCount>'0' ||this.swapPreview.suspendOrderCount>'0'?"当前有未结算/挂起的订单，确认交班？":''
      })
    },
    getList() {
      if (!this.customTime) {
        this.customTime = [
          this.$time().add(-10, 'day').format('YYYY-MM-DD'),
          this.$time().add(1, 'day').format('YYYY-MM-DD')
        ];
      }
      this.queryParams.startTime = this.customTime[0] + ' 00:00:00';
      this.queryParams.endTime = this.customTime[1] + ' 24:00:00';
      querySwapRecordList(this.queryParams).then(response => {
        this.recordList = response.rows || [];

        this.total = response.total || 0;
        this.loading = false;
      });
    }
  }
}

</script>
<style scoped lang="scss" src="./index.scss">

</style>
