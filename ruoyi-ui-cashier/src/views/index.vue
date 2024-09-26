<template>
  <div class="app-container home">
    <div class="time-container">
      <el-radio-group v-model="activeName" style="margin-bottom: 30px;">
        <el-radio-button label="today">当天班次</el-radio-button>
        <el-radio-button label="week">最近一周</el-radio-button>
        <el-radio-button label="month">当月</el-radio-button>
        <el-radio-button label="year">今年</el-radio-button>
        <el-radio-button label="custom"> 自定义</el-radio-button>
      </el-radio-group>
      {{customTime}}
      <el-date-picker v-if="activeName==='custom'"
        v-model="customTime"  format="yyyy-MM-dd HH:mm"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="right">
      </el-date-picker>
    </div>


<!--    <el-divider />-->
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>收入统计</span>
          </div>
          <div class="body">

          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="16">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>趋势</span>
          </div>
          <el-button @click="onSendScore">test</el-button>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";

export default {
  name: "Index",
  data() {
    return {
      customTime:'',
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
      activeName:'today',
      // 版本号
      version: "3.8.8"
    };
  },
  methods: {
    onSendScore(){
      callPCMethod(DeviceMethodNames.CallAddScore,{deskNum:2,btnType:0})
    },
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  .time-container{
    display: flex;
    flex-direction: row;
  }
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

