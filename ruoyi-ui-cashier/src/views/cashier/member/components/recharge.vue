<template>
  <div class=" section-container input-container">
    <el-form label-width="100px" ref="form" :model="inputForm" :rules="rules">
      <el-form-item label="实际应付:">
        {{ inputForm.totalAmount }}
      </el-form-item>
      <el-form-item label="折扣金额:">
        {{ inputForm.totalDiscountAmount }}
      </el-form-item>
      <el-form-item label="充值金额:" prop="rechargeAmount">
        <el-input-number :min="100" :max="999999" v-model="inputForm.rechargeAmount" @change="onRechargeAmountChanged"
                         placeholder="请输入充值金额100~999999"/>
      </el-form-item>
      <el-form-item label="支付密码:" prop="pwd">
        <el-input type="password" min="6" maxlength="20" v-model="inputForm.pwd"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="margin-left: 20px" round @click="onClick " v-loading="loading">
          确认
        </el-button>
      </el-form-item>
    </el-form>


  </div>
</template>
<script>

import {postRecharge,getPreRecharge} from "@/api/cashier/member";

export default {
  props: ['memberId'],
  emits: ["ok"],
  data() {

    return {
      rules: {
        rechargeAmount: [
          {required: true, message: "充值金额不能为空", trigger: "blur"},
        ],
        pwd: [
          {required: true, message: "请输入支付密码", trigger: "blur"},
          {min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur"},
          {pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur"}
        ],
      },
      inputForm: {
        totalAmountDue: null,
        totalAmount: null,
        discountValue: null,
        totalDiscountAmount: null,
        rechargeAmount: 100,
        pwd: '',
      },
      loading: false,
      currentTitle: '0'
    }
  },
  created() {

  },
  mounted() {
    this.onRechargeAmountChanged()
  },
  methods: {
    onRechargeAmountChanged(){
      setTimeout((val)=>{
        if(val!==this.inputForm.rechargeAmount){
          return;
        }
        if(!this.inputForm.rechargeAmount){
          this.inputForm.totalAmount=null;
          this.inputForm.totalAmountDue=null;
          this.inputForm.totalDiscountAmount= null;
          this.inputForm.discountValue= null;
        }
        if(this.loading){
          return;
        }
        this.loading=true;
        getPreRecharge(this.memberId,this.inputForm.rechargeAmount).then(res=>{
          this.inputForm.totalAmount=res.data?.totalAmount
          this.inputForm.totalDiscountAmount= res.data?.totalDiscountAmount;
          this.inputForm.totalAmountDue=res.data?.totalAmountDue;
          this.inputForm.discountValue= res.data?.discountValue;
        }).finally(()=>this.loading=false)
      },500,this.inputForm.rechargeAmount)

    },
    onClick() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          postRecharge({
            memberId: this.memberId, ...this.inputForm
          }).then(response => {
            this.$modal.msgSuccess("充值成功");
            this.$emit("ok")
          });
        }
      });
    }
  }

}
</script>

<style scoped lang="scss">
.input-container {
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 1;
  width: 300px!important;
}


</style>
