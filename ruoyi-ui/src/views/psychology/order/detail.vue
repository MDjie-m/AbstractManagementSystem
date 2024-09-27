<template>
  <div class="app-container" style="margin: 10px" v-if="order && order.orderNo">
    <el-descriptions title="用户信息" size="mini">
      <template slot="extra">
        <el-button size="small" @click="goBack">返回列表</el-button>
      </template>
      <el-descriptions-item label="用户昵称">{{ order.nickName }}</el-descriptions-item>
      <el-descriptions-item label="用户手机号">{{ order.phone }}</el-descriptions-item>
      <el-descriptions-item label="用户ID">{{ order.userId }}</el-descriptions-item>
    </el-descriptions>
    <el-divider/>
    <el-descriptions title="购买信息" size="mini">
      <el-descriptions-item label="服务名称">{{ order.serveName }}</el-descriptions-item>
      <el-descriptions-item label="服务次数">{{ order.serve.num }}</el-descriptions-item>
      <el-descriptions-item label="咨询师">{{ order.consultName }}</el-descriptions-item>
      <el-descriptions-item label="咨询方式">{{ order.serve.modeName }}</el-descriptions-item>
      <el-descriptions-item label="已使用次数">{{ order.buyNum }}</el-descriptions-item>
      <el-descriptions-item label="服务类型">{{ order.serve.typeName }}</el-descriptions-item>
      <el-descriptions-item label="单次服务时长">{{ order.serve.time }}分钟</el-descriptions-item>
      <el-descriptions-item label="剩余次数">{{ order.num }}</el-descriptions-item>
    </el-descriptions>
    <el-divider/>
    <template v-if="['1', '2'].includes(this.order.status)">
      <div style="font-size: 16px;font-weight: bold;color: #303133;margin-bottom: 20px">
        咨询明细 <el-button v-if="checkPermi(['psychology:order:edit']) && hasHx(order)" icon="el-icon-circle-plus-outline" type="text" @click="handleHx">核销</el-button>
      </div>
      <el-table :data="items" size="mini">
        <el-table-column label="咨询次数" align="center" prop="num" />
        <el-table-column label="用户预约时间" align="center" prop="time"/>
        <el-table-column label="预约执行状态" align="center" prop="time">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.schedule_status" :value="scope.row.status"/>
          </template>
        </el-table-column>
<!--        <el-table-column label="实际咨询时间" align="center" prop="realTime"/>-->
<!--        <el-table-column label="操作人" align="center" prop="updateBy"/>-->
      </el-table>
      <el-divider/>
    </template>
    <el-descriptions title="订单信息" :column="2" size="mini">
      <el-descriptions-item label="订单编号">{{ order.orderNo }}</el-descriptions-item>
      <el-descriptions-item label="订单渠道">{{ order.channel || '-' }}</el-descriptions-item>
      <el-descriptions-item label="下单时间">{{ order.createTime }}</el-descriptions-item>
      <el-descriptions-item label="订单总价">{{ order.amount.toFixed(2) }}</el-descriptions-item>
      <el-descriptions-item label="支付时间">{{ order.payTime || '-' }}</el-descriptions-item>
      <el-descriptions-item label="订单实付">{{ order.pay.toFixed(2) }}</el-descriptions-item>
      <el-descriptions-item label="下单方式">{{ order.source === '5' ? '后台创建' : '用户创建' }}</el-descriptions-item>
      <el-descriptions-item label="优惠券金额">-</el-descriptions-item>
      <el-descriptions-item label="订单状态">{{ order.statusName }}</el-descriptions-item>
      <el-descriptions-item label="积分抵扣">-</el-descriptions-item>
      <el-descriptions-item v-if="order.reason" label="转介原因">{{ order.reason }}</el-descriptions-item>
      <el-descriptions-item label="支付状态">{{ order.payStatusName }}</el-descriptions-item>
      <el-descriptions-item label="订单备注">{{ order.remark }}</el-descriptions-item>
    </el-descriptions>

    <template v-if="logs.length > 0">
      <el-divider/>
      <div style="font-size: 16px;font-weight: bold;color: #303133;margin-bottom: 20px">
        操作记录
      </div>
      <el-table :data="logs" size="mini">
        <el-table-column label="操作类型" align="center" prop="changeType">
          <template slot-scope="scope">
            <span>{{ logName.find(i => i.value === scope.row.changeType)['label'] }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作内容" align="center" prop="changeMessage"/>
        <el-table-column label="操作时间" align="center" prop="createTime"/>
        <el-table-column label="操作人" align="center" prop="createBy"/>
      </el-table>
    </template>

    <times ref="formHx" @handleOk="handleOk" />
  </div>
</template>

<script>
import times from "./times";
import { getOrder, getLogs } from "@/api/psychology/order";
import {mapState} from "vuex";
import { checkPermi } from "@/utils/permission";

export default {
  components: { times },
  name: 'OrderDetail',
  dicts: ['schedule_status'],
  data() {
    return {
      logName: this.$constants.logName,
      order: {},
      items: [],
      logs: []
    }
  },
  created() {
    if (this.$route.query && this.$route.query.id) {
      this.handleDetail(this.$route.query.id)
    }
  },
  computed: {
    ...mapState({
      userInfo: (state) => state.user,
    })
  },
  methods: {
    checkPermi,
    hasHx(order) {
      if (this.userInfo.name !== 'admin' && this.userInfo.nickName !== order.consultName) {
        return false
      }

      return order.num !== 0 && ['1', '2'].includes(order.status)
    },
    getLogs() {
      getLogs(this.order.orderNo).then(response => {
        this.logs = response.data
      })
    },
    handleDetail(id) {
      getOrder(id).then(response => {
        this.open = true;
        this.order = response.data
        const items = []

        if (["1", "2"].includes(this.order.status) ) {
          if (this.order.items && this.order.items.length > 0) {
            this.order.items.forEach((i, idx) => {
              const it = {
                num: idx + 1,
                time: i.day + ' ' + i.timeStart + '-' + i.timeEnd,
                realTime: i.realTime,
                status: i.status,
                updateBy: i.realTime ? i.updateBy : null
              }
              items.push(it)
            })
          }

          const len = items.length
          const num = this.order.serve.num - len

          // if (num > 0) {
          //   for (let i = 1; i <= num; i++) {
          //     const tt = {
          //       num: len + i,
          //       time: '用户未预约',
          //       realTime: '',
          //       status: '',
          //       updateBy: ''
          //     }
          //     items.push(tt)
          //   }
          // }
        }

        this.items = items
        this.getLogs()
      });
    },
    handleHx() {
      this.$refs.formHx.setForm(this.order)
    },
    handleOk() {
      this.handleDetail(this.order.id)
    },
    goBack() {
      this.$tab.closeOpenPage()
      this.$router.push({ path: '/psychology/order' })
    }
  }
}
</script>

<style scoped>

</style>
