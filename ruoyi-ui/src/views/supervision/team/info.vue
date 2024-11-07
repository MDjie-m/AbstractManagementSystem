<template>
  <el-dialog title="查看团队" :visible.sync="open" width="1200px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">

      <el-form-item label="团队标题" prop="title" >
        <el-input v-model="form.title" placeholder="请输入团队标题" disabled/>
      </el-form-item>

      <el-row>
        <el-col :span="8">
          <el-form-item label="团队类型" prop="teamType" >
            <el-select v-model="form.teamType" placeholder="请选择团队类型" clearable @change="changeTeamType()" disabled>
              <el-option
                v-for="dict in teamType"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="面向用户类型" prop="serveUserType" >
            <el-select v-model="form.serveUserType" placeholder="请选择面向用户类型" clearable disabled>
              <el-option
                v-for="dict in userType"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="标签" prop="label" >
            <el-select v-model="selectedLabelList" clearable multiple  disabled>
              <el-option
                v-for="item in teamSupLabelList"
                :key="item.value"
                :label="item.label"
                :value="item.label"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="第几期" prop="periodNo" >
            <el-input-number v-model="form.periodNo" :min="0" :step="1" :precision="0" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="满额人数" prop="maxNumPeople" >
            <el-input-number v-model="form.maxNumPeople" :min="0" :step="1" :precision="0" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="活动次数" prop="cycleNumber" >
            <el-input-number v-model="form.cycleNumber" :min="0" :step="1" :precision="0" disabled/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="周几" prop="weekDay" >
            <el-select v-model="form.weekDay"  clearable style="width: 200px;" disabled>
              <el-option
                v-for="dict in dict.type.week_day"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="开始时间" prop="lectureStartTime" >
            <el-time-picker
              v-model="form.lectureStartTime" disabled
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
              v-model="form.lectureEndTime" disabled
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
            <el-input-number v-model="form.price" :min="0"  disabled/> 元
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
            <el-select v-model="form.isAbleOb" clearable disabled>
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
            <el-input-number v-model="form.obPrice" :min="0"  disabled/> 元
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="讲师" prop="consultantId">
            <el-select v-model="form.consultantId" clearable filterable disabled>
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
            <el-input-number v-model="form.lectureAmount" :min="0"  disabled/>元/每次
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="团督头像图片" prop="avatarPicUrl">
        <my-cropper v-model="form.avatarPicUrl"  sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172" disabled/>
      </el-form-item>

      <el-form-item label="团督head图片" prop="headPicUrl">
        <my-cropper v-model="form.headPicUrl"  sizeTip="宽375px 高197px" :extraData="extraData" :width="375" :height="197" disabled/>
      </el-form-item>

      <el-form-item label="小组特色图片" prop="specialPicUrl">
        <editor v-model="form.specialPicUrl" :min-height="192"  disabled/>
      </el-form-item>

      <el-form-item label="报名须知图片" prop="registerNoticePicUrl">
        <editor v-model="form.registerNoticePicUrl" :min-height="192"  disabled/>
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注"  disabled/>
      </el-form-item>

<!--      <el-form-item label="团队类型" prop="teamType" v-show="false" disabled>
        <el-select v-model="form.teamType" placeholder="请选择团队类型" disabled clearable>
          <el-option
            v-for="item in supervisionType"
            :key="item.value"
            :label="item.label"
            :value="parseInt(item.value)"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="团队标题" prop="title" v-if="isTeamType()" disabled>
        <el-input v-model="form.title" placeholder="请输入团队标题" disabled />
      </el-form-item>

      <el-form-item label="讲师" prop="consultantId">
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
      <el-form-item label="入团价格" prop="price">
        <el-input-number v-model="form.price" :min="0"  disabled/> 元
      </el-form-item>
      <el-form-item label="讲师每堂课收入" prop="lectureAmount">
        <el-input-number v-model="form.lectureAmount" :min="0" disabled/> 元
      </el-form-item>

      <el-form-item label="团督头像图片" prop="avatarPicUrl">
        <my-cropper v-model="form.avatarPicUrl"  sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>
      </el-form-item>

      <el-form-item label="团督head图片" prop="headPicUrl">
        <my-cropper v-model="form.headPicUrl"  sizeTip="宽375px 高197px" :extraData="extraData" :width="375" :height="197"/>
      </el-form-item>

      <el-form-item label="小组特色图片" prop="specialPicUrl">
        &lt;!&ndash;        <image-upload v-model="form.specialPicUrl" :extraData="extraData" />&ndash;&gt;
        <editor v-model="form.specialPicUrl" :min-height="192" :extraData="extraData"/>
      </el-form-item>

      <el-form-item label="报名须知图片" prop="registerNoticePicUrl">
        &lt;!&ndash;        <image-upload  v-model="form.registerNoticePicUrl"  :extraData="extraData" />&ndash;&gt;
        <editor v-model="form.registerNoticePicUrl" :min-height="192" :extraData="extraData"/>
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
      </el-form-item>-->
    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel">返  回</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {infoTeam, addTeam, editTeam} from "@/api/supervision/team";
import { yesOrNO } from "@/utils/constants";

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
      teamType: this.$constants.teamType,
      userType: this.$constants.userType,
      yesOrNO: this.$constants.yesOrNO,
      teamSupLabelList: this.$constants.teamSupLabel,
      // 上传
      extraData: {
        module: this.$constants['picModules'][2],
        type: this.$constants['picTypes'][2]
      },
      selectedLabelList:[],
      form: {
        memberList:[]
      },
      // 表单校验
      rules: {
        /*teamType: [
          { required: true, message: "请选择团队类型", trigger: "change" }
        ],
        title: [
          { required: true, message: "请输入标题", trigger: "blur" }
        ],
        consultantId: [
          { required: true, message: "请选择讲师", trigger: "change" }
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
          data.consultExperience = '';
          data.consultAvatar = '';
          data.consultDetail = '';
          this.form = data;
          if (this.form.label){
            this.selectedLabelList = this.form.label.split(",");
          }
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
      if (this.form.teamType == '1'){
        return true;
      }
      return false;
    },
    isAbleOb(){
      if (this.form.isAbleOb == 'Y'){
        return true;
      }
      return false;
    },
    isHasMember(){
      //剩余名额 != 最大人数, 即已有人报名
      this.form.surplusJoinNum != this.form.maxNumPeople;
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

          that.$modal.confirm('确认修改团队吗？').then(function() {



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
