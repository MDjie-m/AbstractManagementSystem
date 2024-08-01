<template>
  <el-dialog title="查看督导" :visible.sync="open" width="1000px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="督导类型" prop="teamType" v-show="false" disabled>
        <el-select v-model="form.teamType" placeholder="请选择督导类型" disabled clearable>
          <el-option
            v-for="item in supervisionType"
            :key="item.value"
            :label="item.label"
            :value="parseInt(item.value)"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="督导标题" prop="title" v-if="isTeamType()" disabled>
        <el-input v-model="form.title" placeholder="请输入督导标题" disabled />
      </el-form-item>

      <el-form-item label="督导师" prop="consultantId">
        <el-select v-model="form.consultantId" disabled clearable filterable>
          <el-option
            v-for="item in consultList"
            :key="item.id"
            :label="item.nickName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="期数" prop="periodNo" v-if="isTeamType()" disabled>
        第<el-input-number v-model="form.periodNo" :min="0" :step="1" :precision="0" disabled/> 期
      </el-form-item>
      <el-form-item label="满额人数" prop="maxNumPeople" v-if="isTeamType()">
        <el-input-number v-model="form.maxNumPeople" :min="0" :step="1" :precision="0" disabled/> 人
      </el-form-item>
      <el-form-item label="本期开课次数" prop="cycleNumber" v-if="isTeamType()">
        <el-input-number v-model="form.cycleNumber" :min="0" :step="1" :precision="0" disabled/> 次
      </el-form-item>
      <el-form-item label="每周几开课" prop="weekDay" v-if="isTeamType()">
        <el-select v-model="form.weekDay"  clearable disabled>
          <el-option
            v-for="item in weekDay"
            :key="item.value"
            :label="item.label"
            :value="parseInt(item.value)"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开课时间" prop="lectureStartTime" v-if="isTeamType()" >
        <el-time-picker
          v-model="form.lectureStartTime"
          style="width: 140px;"
          size="small"
          value-format="HH:mm"
          format="HH:mm"
          type="daterange"
          disabled
        />
      </el-form-item>
      <el-form-item label="下课时间" prop="lectureEndTime" v-if="isTeamType()" >
        <el-time-picker
          v-model="form.lectureEndTime"
          style="width: 140px;"
          size="small"
          value-format="HH:mm"
          format="HH:mm"
          type="daterange"
          disabled
        />
      </el-form-item>
      <el-form-item label="服务价格" prop="price">
        <el-input-number v-model="form.price" :min="0"  disabled/> 元
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder=""  disabled/>
      </el-form-item>

      <el-form-item label="当前成员" v-if="isTeamType()">
        <el-table  :data="form.memberList" >
          <el-table-column label="姓名" align="center" prop="memberName"/>
          <el-table-column label="手机号" align="center" prop="memberPhonenumber"/>
          <el-table-column label="咨询师级别" align="center" prop="memberLevel" >
            <template slot-scope="scope">
              <dict-tag :options="dict.type.consult_level" :value="scope.row.memberLevel"/>
            </template>
          </el-table-column>
          <el-table-column label="加入时间" align="center" prop="createTime"/>

        </el-table>
      </el-form-item>
    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel">返  回</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {infoTeam, addTeam, editTeam} from "@/api/supervision/team";

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
      supervisionType: this.$constants.supervisionType,
      weekDay: this.$constants.weekDay,
      form: {
        memberList:[]
      },
      // 表单校验
      rules: {
        /*teamType: [
          { required: true, message: "请选择督导类型", trigger: "change" }
        ],
        title: [
          { required: true, message: "请输入标题", trigger: "blur" }
        ],
        consultantId: [
          { required: true, message: "请选择督导师", trigger: "change" }
        ],
        periodNo: [
          { required: true, message: "请输入期数", trigger: "change" }
        ],
        maxNumPeople: [
          { required: true, message: "请输入满额人数", trigger: "change" }
        ],
        cycleNumber: [
          { required: true, message: "请输入本期开课次数", trigger: "change" }
        ],
        weekDay: [
          { required: true, message: "请选择每周几开课", trigger: "change" }
        ],
        lectureStartTime: [
          { required: true, message: "请输入开课时间", trigger: "blur" }
        ],
        lectureEndTime: [
          { required: true, message: "请输入下课时间", trigger: "blur" }
        ],
        price: [
          { required: true, message: "请输入服务价格", trigger: "change" }
        ],*/


      }
    }
  },
  methods: {
    init(id) {

      infoTeam(id).then(response => {
        console.log("***********************************************查询结束,data:")
        console.log(response.data)
        if (response.code == 200){
          let data = response.data;
          this.form.consultantId = data.consultantId;
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
          this.form.memberList = data.memberList;
        }
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
