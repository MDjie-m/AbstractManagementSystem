<template>
  <div class="   input-container">
    <el-tabs v-model="currentTitle" class="tab-container" @tab-click="onTabClick">
      <el-tab-pane label="充值" name="0" >
        <el-table v-loading="loading" :data="recharge.list" @change="getRechargeList"
        >
          <el-table-column label="订单编号" align="center" prop="orderNo" width="210"/>
          <el-table-column label="充值金额" align="center" prop="rechargeAmount"/>
          <el-table-column label="实际支付金额" align="center" prop="totalAmount"/>
          <el-table-column label="支付折扣金额" align="center" prop="totalDiscountAmount"/>
          <el-table-column label="充值日期" align="center" prop="createTime"/>
        </el-table>
        <pagination
          v-show="recharge.total>0"
          :total="recharge.total"
          :page.sync="recharge.queryParams.pageNum"
          :limit.sync="recharge.queryParams.pageSize"
          @pagination="getRechargeList"
        />
      </el-tab-pane>
      <el-tab-pane label="消费" name="1" >
        <el-table v-loading="loading" :data="deduct.list" @change="getDeductList"
        >
          <el-table-column label="订单编号" align="center" prop="orderNo" width="210"/>
          <el-table-column label="订单金额" align="center" prop="totalAmountDue"/>
          <el-table-column label="实际支付金额" align="center" prop="totalAmount"/>
          <el-table-column label="支付折扣金额" align="center" prop="totalDiscountAmount"/>

          <el-table-column label="消费日期" align="center" prop="updateTime"/>

        </el-table>
        <pagination
          v-show="deduct.total>0"
          :total="deduct.total"
          :page.sync="deduct.queryParams.pageNum"
          :limit.sync="deduct.queryParams.pageSize"
          @pagination="getDeductList"
        />
      </el-tab-pane>
    </el-tabs>

  </div>
</template>
<script>

import {postRecharge, getPreRecharge, listMemberRecharges, listMemberDeduct} from "@/api/cashier/member";

export default {
  props: ['memberId'],
  emits: ["ok"],
  data() {

    return {
      recharge: {
        list: [],
        total: 0,
        queryParams: {
          memberId: null,
          pageSize: 10,
          pageNum: 1,
        }
      },
      deduct: {
        list: [],
        total: 0,
        queryParams: {
          memberId: null,
          pageSize: 10,
          pageNum: 1,
        }
      },
      loading: false,
      currentTitle: '0'
    }
  },
  created() {

  },
  mounted() {
    this.recharge.queryParams.memberId = this.deduct.queryParams.memberId = this.memberId;
    this.getRechargeList();

  },
  methods: {
    onTabClick(){
      if(this.currentTitle==='0'){
        this.getRechargeList()
      }else {
        this.getDeductList()
      }
    },
    getRechargeList() {
      this.loading = true
      listMemberRecharges(this.recharge.queryParams).then(res => {
        let list = res.rows || [];
        this.recharge.list = list;
        this.recharge.total = res.total;
      }).finally(() => this.loading = false)
    },
    getDeductList() {
      this.loading = true
      listMemberDeduct(this.recharge.queryParams).then(res => {
        let list = res.rows || [];
        this.deduct.list = list;
        this.deduct.total = res.total;
      }).finally(() => this.loading = false)
    }
  }

}
</script>

<style scoped lang="scss">
.input-container {
  display: flex;
  align-items: start;
  justify-content: center;
  flex: 1;
  width: 300px !important;
}
.tab-container{
  flex: 1;
}


</style>
