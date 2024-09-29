<template>
  <div>

    <div class="time-container" v-loading="loading">
      <el-radio-group v-model="activeName" @change="queryData" style="margin-bottom: 30px;">
        <el-radio-button label="today">当天班次</el-radio-button>
        <el-radio-button label="week">最近一周</el-radio-button>
        <el-radio-button label="month">当月</el-radio-button>
        <el-radio-button label="year">今年</el-radio-button>
        <el-radio-button label="custom"> 自定义</el-radio-button>
      </el-radio-group>
      <el-date-picker v-show="activeName==='custom'" style="margin-left: 10px"
                      v-model="customTime" format="yyyy-MM-dd"
                      value-format="yyyy-MM-dd" @change="queryData"
                      type="datetimerange"
                      :picker-options="pickerOptions"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      align="right">
      </el-date-picker>
    </div>
    <el-divider content-position="left">
      营业信息
    </el-divider>
    <div class="section-container total-container">
      <div class="total-item">
        <div class="total-item-amount">
          <el-badge :value="info.totalOrderCount" :hidden="!info.totalOrderCount" type="primary">
    <span>
                {{ info.totalAmount }}
    </span>


          </el-badge>
        </div>
        <div class="total-item-label">
          营业额/单数
        </div>
      </div>
      <div class="total-item">

        <div class="total-item-amount">
          <el-badge :value="info.rechargeOrderCount" :hidden="!info.rechargeOrderCount" type="primary">
            <span>
                 {{ info.rechargeOrderCount }}
            </span>
          </el-badge>
        </div>

        <div class="total-item-label">
          会员充值/单数
        </div>
      </div>
      <div class="total-item">
        <div class="total-item-amount">
          <el-badge :value="info.refundOrderCount" :hidden="!info.refundOrderCount" type="primary">
      <span>
                  {{ info.refundAmount }}
      </span>


          </el-badge>
        </div>
        <div class="total-item-label">
          退款额/单数
        </div>
      </div>
    </div>
    <el-divider content-position="left">
      支付、优惠
    </el-divider>
    <div class="detail-container">
      <el-card>
        <div class="card-item">
          <div slot="header" class="card-item-row header">
            <div>支付方式</div>
            <div>{{ info.totalAmount }}</div>
          </div>
          <div class="card-item-row " v-for="item in info.payList">
            <div>{{ item.typeText }}</div>
            <div>{{ item.amount }}</div>
          </div>
        </div>
      </el-card>
      <el-card>
        <div class="card-item">
          <div slot="header" class="card-item-row header">
            <div>优惠金额</div>
            <div>{{ info.preferentialTotal }}</div>
          </div>

          <div class="card-item-row" v-for="item in info.preferentialList">
            <div>{{ item.typeText }}</div>
            <div>{{ item.amount }}</div>
          </div>
        </div>
      </el-card>
      <el-card>
        <div class="card-item">
          <div slot="header" class="card-item-row header">
            <div>退款金额</div>
            <div>{{ info.refundAmount }}</div>
          </div>

          <div class="card-item-row" v-for="item in info.refundList">
            <div>{{ item.typeText }}</div>
            <div>{{ item.amount }}</div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
import {queryDashboard} from "@/api/cashier/store";

export default {
  data() {
    return {
      loading: false,
      info: {
        "totalOrderCount": 0,
        "totalAmount": 0.00,
        "rechargeAmount": 0.00,
        "rechargeOrderCount": 0,
        "refundAmount": 0.00,
        "refundOrderCount": 0,
        "cashOrderAmount": 0,
        "cashOrderCount": 0,
        "payList": [],
        "refundList": [],
        "preferentialList": []
      },
      customTime: [this.$time().format("YYYY-MM-DD"), this.$time().format("YYYY-MM-DD")],
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
      activeName: 'today',

    }
  }, mounted() {
    this.queryData();
  },
  methods: {
    queryData() {

      let startTime, endTime;
      if (this.activeName === "today") {
        startTime = this.$time().format("YYYY-MM-DD");
        endTime = this.$time().format("YYYY-MM-DD");
      } else if (this.activeName === "month") {
        startTime = this.$time().startOf("week").format("YYYY-MM-DD");
        endTime = this.$time().endOf("week").format("YYYY-MM-DD");
      } else if (this.activeName === "month") {
        startTime = this.$time().startOf("month").format("YYYY-MM-DD");
        endTime = this.$time().endOf("month").format("YYYY-MM-DD");
      } else if (this.activeName === "year") {
        startTime = this.$time().startOf("year").format("YYYY-MM-DD");
        endTime = this.$time().endOf("year").format("YYYY-MM-DD");
      } else {
        if (!this.customTime?.length) {
          this.customTime = [this.$time().format("YYYY-MM-DD"), this.$time().format("YYYY-MM-DD")]
        }
        startTime = this.customTime[0];
        endTime = this.customTime[1]
      }
      if (this.loading) {
        return
      }
      this.loading = true;
      queryDashboard({
        startTime, endTime
      }).then(res => {
        this.info = res.data
      }).finally(() => this.loading = false)
    }
  }
}
</script>
<style scoped lang="scss">
@import '@/assets/styles/variables.scss';

::v-deep.el-radio-group {
  & > .el-radio-button:first-child .el-radio-button__inner {
    border-bottom-left-radius: 15px;
    border-top-left-radius: 15px;
  }

  & > .el-radio-button:last-child .el-radio-button__inner {
    border-bottom-right-radius: 15px;
    border-top-right-radius: 15px;
  }
}

.time-container {
  display: flex;
  flex-direction: row;
}

.total-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 80px;

  .total-item {
    min-width: 300px;

    justify-content: center;
    display: flex;
    flex-direction: column;


    &-amount {
      font-size: 40px;
      letter-spacing: 3px;
      color: $red;
    }

    &-label {
      font-size: 15px;
      letter-spacing: 3px;
      color: $sub-text-color;
    }
  }
}

.detail-container {
  display: flex;
  flex-direction: row;
  gap: 20px;

  .el-card {
    display: flex;
    flex-direction: column;
    flex: 1;

    .card-item {
      display: flex;
      flex-direction: column;
      flex: 1;
      gap: 10px;
      font-size: 15px;
      color: $sub-text-color;

      .header {
        color: $main-text-color;
        font-weight: bold;
      }

      &-row {
        display: flex;

        & > div:last-child {
          margin-left: auto;
          color: $red;
          font-size: 20px;
        }
      }
    }


  }
}
</style>
