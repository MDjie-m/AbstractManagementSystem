<template>
  <el-dialog title="新建团队" :visible.sync="open" width="1200px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">

      <el-form-item label="团队标题" prop="title" >
        <el-input v-model="form.title" placeholder="请输入团队标题" />
      </el-form-item>

      <el-row>
        <el-col :span="8">
          <el-form-item label="团队类型" prop="teamType" >
            <el-select v-model="form.teamType" placeholder="请选择团队类型" clearable @change="changeTeamType()">
              <el-option
                v-for="dict in teamType"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="面向用户类型" prop="serveUserType" >
            <el-select v-model="form.serveUserType" placeholder="请选择面向用户类型" clearable >
              <el-option
                v-for="dict in userType"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="第几期" prop="periodNo" >
            <el-input-number v-model="form.periodNo" :min="0" :step="1" :precision="0"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="满额人数" prop="maxNumPeople" >
            <el-input-number v-model="form.maxNumPeople" :min="0" :step="1" :precision="0"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="活动次数" prop="cycleNumber" >
            <el-input-number v-model="form.cycleNumber" :min="0" :step="1" :precision="0"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="周几" prop="weekDay" >
            <el-select v-model="form.weekDay"  clearable style="width: 200px;">
              <el-option
                v-for="dict in dict.type.week_day"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="开始时间" prop="lectureStartTime" >
            <el-time-picker
              v-model="form.lectureStartTime"
              style="width: 200px;"
              value-format="HH:mm"
              format="HH:mm"
              type="daterange"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="结束时间" prop="lectureEndTime" >
            <el-time-picker
              v-model="form.lectureEndTime"
              style="width: 200px;"
              value-format="HH:mm"
              format="HH:mm"
              type="daterange"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="入团价格" prop="price">
            <el-input-number v-model="form.price" :min="0" /> 元
          </el-form-item>
        </el-col>
        <el-col :span="8">
        </el-col>
        <el-col :span="8">
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="是否可观摩" prop="isAbleOb" >
            <el-select v-model="form.isAbleOb" clearable :disabled="!isTeamType()">
              <el-option
                v-for="dict in yesOrNO"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="观摩价格" prop="obPrice" v-if="isAbleOb()">
            <el-input-number v-model="form.obPrice" :min="0" /> 元
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="讲师" prop="consultantId">
            <el-select v-model="form.consultantId" clearable filterable>
              <el-option
                v-for="item in consultList"
                :key="item.id"
                :label="item.nameAndPhone"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="讲师收入" prop="lectureAmount">
            <el-input-number v-model="form.lectureAmount" :min="0" />元/每次
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { addTeam } from "@/api/supervision/team";
import { yesOrNO } from "@/utils/constants";

export default {
  name: "addForm",
  dicts: ['supervision_type','supervision_status','week_day'],
  props: {
    consultList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      open: false,
      type: 'add',// tryAdd
      types: this.$constants.partnerTypes,
      teamType: this.$constants.teamType,
      userType: this.$constants.userType,
      yesOrNO: this.$constants.yesOrNO,
      form: {

      },
      // 表单校验
      rules: {
        teamType: [
          { required: true, message: "请选择团队类型", trigger: "change" }
        ],
        title: [
          { required: true, message: "请输入标题", trigger: "blur" }
        ],
        consultantId: [
          { required: true, message: "请选择团队师", trigger: "change" }
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
          { required: true, message: "请输入入团价格", trigger: "change" }
        ],
        lectureAmount: [
          { required: true, message: "请输入团队师每堂课收入", trigger: "change" }
        ],

      }
    }
  },
  methods: {
    init() {
      this.form = {
        id: null
      }
      this.open = true
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
      if (this.form.teamType == '1'){
        return true;
      }
      return false;
    },
    isHasMember(){
      //剩余名额 != 最大人数, 即已有人报名
      this.form.surplusJoinNum != this.form.maxNumPeople;
    },
    isAbleOb(){
      if (this.form.isAbleOb == 'Y'){
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

          that.$modal.confirm('确认新建团队吗？').then(function() {

            addTeam(that.form).then(response => {
                that.$modal.msgSuccess("新建成功");
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
    },
    changeTeamType(){
      console.log('------------')
      console.log(this.form.teamType)
      if (this.form.teamType != '1'){
        this.form.isAbleOb = 'N';
      }
      console.log(this.form.isAbleOb)
    },
  }
}
</script>

<style scoped>

</style>
