<template>
  <el-dialog title="新建督导" :visible.sync="open" width="1000px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="督导类型" prop="teamType">
        <el-select v-model="form.teamType" placeholder="请选择督导类型" clearable>
          <el-option
            v-for="dict in dict.type.supervision_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="督导标题" prop="title" v-if="isTeamType()">
        <el-input v-model="form.title" placeholder="请输入督导标题" />
      </el-form-item>

      <el-form-item label="督导师" prop="consultantId">
        <el-select v-model="form.consultantId" clearable filterable>
          <el-option
            v-for="item in consultList"
            :key="item.id"
            :label="item.nickName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="期数" prop="periodNo" v-if="isTeamType()">
        第<el-input-number v-model="form.periodNo" :min="0" :step="1" :precision="0"/> 期
      </el-form-item>
      <el-form-item label="满额人数" prop="maxNumPeople" v-if="isTeamType()">
        <el-input-number v-model="form.maxNumPeople" :min="0" :step="1" :precision="0"/> 人
      </el-form-item>
      <el-form-item label="本期开课次数" prop="cycleNumber" v-if="isTeamType()">
        <el-input-number v-model="form.cycleNumber" :min="0" :step="1" :precision="0"/> 次
      </el-form-item>
      <el-form-item label="每周几开课" prop="weekDay" v-if="isTeamType()">
        <el-select v-model="form.weekDay"  clearable>
          <el-option
            v-for="dict in dict.type.week_day"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开课时间" prop="lectureStartTime" v-if="isTeamType()">
        <el-time-picker
          v-model="form.lectureStartTime"
          style="width: 140px;"
          size="small"
          value-format="HH:mm"
          format="HH:mm"
          type="daterange"
        />
      </el-form-item>
      <el-form-item label="下课时间" prop="lectureEndTime" v-if="isTeamType()">
        <el-time-picker
          v-model="form.lectureEndTime"
          style="width: 140px;"
          size="small"
          value-format="HH:mm"
          format="HH:mm"
          type="daterange"
        />
      </el-form-item>
      <el-form-item label="服务价格" prop="price">
        <el-input-number v-model="form.price" :min="0" /> 元
      </el-form-item>
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
      form: {},
      // 表单校验
      rules: {
        teamType: [
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
        ],


      }
    }
  },
  methods: {
    init() {
      this.form = {
        id: null,
        name: '新增督导'
      }
      this.open = true
      console.log(2222)
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

          that.$modal.confirm('确认新建督导吗？').then(function() {

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
    }
  }
}
</script>

<style scoped>

</style>
