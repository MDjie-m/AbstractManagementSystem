<template>
  <el-dialog title="查看督导" :visible.sync="open" width="1000px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">

      <el-form-item label="订单编号" prop="title"  disabled>
        <el-input v-model="form.orderNo" placeholder="请输入督导标题" disabled />
      </el-form-item>

      <el-form-item label="订单服务类型" prop="serverType">
        <el-select v-model="form.serverType" placeholder="请选择订单服务类型" disabled clearable>
          <el-option
            v-for="item in serverTypeList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="服务名称" prop="serverName">
        <el-input v-model="form.serverName" placeholder="请输入服务名称" disabled />
      </el-form-item>

      <el-form-item label="下单人" prop="payConsultantName">
        <el-input v-model="form.payConsultantName" placeholder="请输入下单人" disabled />
      </el-form-item>

      <el-form-item label="订单状态" prop="status">
        <el-select v-model="form.status" placeholder="请选择订单状态" disabled clearable>
          <el-option
            v-for="item in orderStatusList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="支付方式" prop="payType">
        <el-select v-model="form.payType" placeholder="请选择支付方式" disabled clearable>
          <el-option
            v-for="item in payTypeList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="支付状态" prop="payStatus">
        <el-select v-model="form.payStatus" placeholder="请选择支付状态" disabled clearable>
          <el-option
            v-for="item in payStatusList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="订单原价" prop="payAmount">
        <el-input v-model="form.payAmount" placeholder="请输入订单原价" disabled />
      </el-form-item>

      <el-form-item label="付款时间" prop="payDatetime">
        <el-input v-model="form.payDatetime" placeholder="请输入付款时间" disabled />
      </el-form-item>
    </el-form>




    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel">返  回</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {queryConsultantOrderByNo } from "@/api/order/consultantOrder";

export default {
  name: "infoForm",
  dicts: ['supervision_type','supervision_status','week_day','consult_level'],
  props: {
    consultList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      open: false,
      type: 'info',// tryAdd
      serverTypeList: this.$constants.serverType,
      orderStatusList: this.$constants.orderStatus,
      payTypeList: this.$constants.payType,
      payStatusList: this.$constants.payStatus,
      weekDay: this.$constants.weekDay,
      form: {
        memberList:[]
      },
      // 表单校验
      rules: {
      }
    }
  },
  methods: {
    init(id) {

      queryConsultantOrderByNo(id).then(response => {
        console.log("***********************************************查询结束,data:")
        console.log(response.data)
        if (response.code == 200){
          let data = response.data;
          /*this.form.consultantId = data.consultantId;
          this.form.cycleNumber = data.cycleNumber;
          this.form.firstLectureDate = data.firstLectureDate;
          this.form.id = data.id;
          this.form.lectureEndTime = data.lectureEndTime;
          this.form.lectureStartTime = data.lectureStartTime;
          this.form.maxNumPeople = data.maxNumPeople;
          this.form.periodNo = data.periodNo;
          this.form.price = data.price;
          this.form.status = data.status;
          this.form.teamType = data.teamType;
          this.form.title = data.title;
          this.form.weekDay = data.weekDay;
          this.form.remark = data.remark;
          this.form.memberList = data.memberList;*/
          this.form = data;
        }
        this.form.payType = this.form.payType + "";
        console.log("================================form-info")
        console.log(this.form)
        this.open = true
      });

    },
    initData(data) {
      this.form = data
      console.log(this.form)
      this.open = true
    },
    changeType(val) {
      switch (val) {
        case 1:
          this.form.money = 700
          this.form.ratio = 50
          break
        case 2:
          this.form.money = 3200
          this.form.ratio = 70
          break
        case 3:
          this.form.money = 3000
          this.form.ratio = 70
          break
        case 4:
          this.form.money = 5800
          this.form.ratio = 70
          break
      }
    },
    onChangeTime(val) {
      if (val && val.length > 0) {
        this.form.startTime = val[0] + ' 00:00:00'
        this.form.endTime = val[1] + ' 23:59:59'
      }
    },
    isTeamType(){
      if (this.form.teamType == 1){
        return true;
      }
      return false;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(async valid => {
        if (valid) {
          const that = this
          // 查询是否存在有效的合同
          /*const req = {
            consultId: that.form.consultId,
            startTime: that.form.startTime,
            endTime: that.form.endTime
          }
          const res = await exist(req);
          if (res.code === 200 && res.data > 0) {
            return that.$message.error('选择的合同时间段内存在有效合同，请先终止原合同再发起新合同。')
          }*/

          that.$modal.confirm('确认修改督导吗？').then(function() {



            editTeam(that.form).then(response => {
              that.$modal.msgSuccess("修改成功");
              that.cancel()
              that.$emit('handleOk')
            });

          }).then(() => {
          }).catch(() => {});
        }
      });
    },
    cancel() {
      this.form = {}
      this.open = false
    }
  }
}
</script>

<style scoped>

</style>
