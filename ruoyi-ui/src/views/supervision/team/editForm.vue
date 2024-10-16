<template>
  <el-dialog title="修改督导" :visible.sync="open" width="1000px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="督导类型" prop="teamType" v-show="false">
        <el-select v-model="form.teamType" placeholder="请选择督导类型"  clearable disabled>
          <el-option
            v-for="item in supervisionType"
            :key="item.value"
            :label="item.label"
            :value="parseInt(item.value)"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="督导标题" prop="title" v-if="isTeamType()" >
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

      <el-form-item label="标签" prop="label" v-if="isTeamType()">
        <el-select v-model="selectedLabelList" clearable multiple >
          <el-option
            v-for="item in teamSupLabelList"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="期数" prop="periodNo" v-if="isTeamType()" disabled>
        第<el-input-number v-model="form.periodNo" :min="0" :step="1" :precision="0" disabled/> 期
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
            v-for="item in weekDay"
            :key="item.value"
            :label="item.label"
            :value="parseInt(item.value)"
          ></el-option>
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
      <el-form-item label="入团价格" prop="price">
        <el-input-number v-model="form.price" :min="0" /> 元
      </el-form-item>
      <el-form-item label="督导师每堂课收入" prop="lectureAmount">
        <el-input-number v-model="form.lectureAmount" :min="0" /> 元
      </el-form-item>

      <el-form-item label="团督头像图片" prop="avatarPicUrl">
        <my-cropper v-model="form.avatarPicUrl"  sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>
      </el-form-item>

      <el-form-item label="团督head图片" prop="headPicUrl">
        <my-cropper v-model="form.headPicUrl"  sizeTip="宽375px 高197px" :extraData="extraData" :width="375" :height="197"/>
      </el-form-item>

      <el-form-item label="小组特色图片" prop="specialPicUrl">
<!--        <image-upload v-model="form.specialPicUrl" :extraData="extraData" />-->
        <editor v-model="form.specialPicUrl" :min-height="192" />
      </el-form-item>

      <el-form-item label="报名须知图片" prop="registerNoticePicUrl">
<!--        <image-upload  v-model="form.registerNoticePicUrl"  :extraData="extraData" />-->
        <editor v-model="form.registerNoticePicUrl" :min-height="192" />
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注" />
      </el-form-item>

<!--      <el-form-item label="标签" prop="label">
        <el-tag
          v-for="tag in labelList"
          :key="tag"
          closable
          :disable-transitions="false"
          @close="deleteTag(tag)"
        >
          {{ tag }}
        </el-tag>
        <el-input
          v-if="tagInputVisible"
          ref="InputRef"
          v-model="tagInputValue"
          class="w-20"
          size="small"
          @keyup.enter="handleTagInputConfirm"
          @blur="handleTagInputConfirm"
        />
        <el-button v-else class="button-new-tag" size="small" @click="showInputTag">
          + New Tag
        </el-button>
      </el-form-item>-->

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {addTeam, editTeam} from "@/api/supervision/team";
import {teamSupLabel, weekDay} from "@/utils/constants";

export default {
  name: "editForm",
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
      type: 'edit',// tryAdd
      supervisionType: this.$constants.supervisionType,
      weekDay: this.$constants.weekDay,
      teamSupLabelList: this.$constants.teamSupLabel,
      labelList: [],
      tagInputVisible: false,
      tagInputValue: '',
      selectedLabelList:[],
      form: {
      },
      // 上传
      extraData: {
        module: this.$constants['picModules'][2],
        type: this.$constants['picTypes'][2]
      },
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
          { required: true, message: "请输入入团价格", trigger: "change" }
        ],
        lectureAmount: [
          { required: true, message: "请输入督导师每堂课收入", trigger: "change" }
        ],

      }
    }
  },
  methods: {
    init(data) {
      data.consultExperience = '';
      data.consultAvatar = '';
      data.consultDetail = '';
      this.form = data;
      if (this.form.label){
        this.selectedLabelList = this.form.label.split(",");
      }


     /* this.form.consultantId = data.consultantId;
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
      this.form.remark = data.remark;*/

      this.open = true
    },
    initData(data) {
      this.form = data
      console.log(this.form)
      this.open = true
    },

    //删除标签
    deleteTag(tag){
      this.labelList.splice(this.labelList.indexOf(tag),1);
    },
    //显示标签输入框
    showInputTag(){
      this.tagInputVisible = true;
    },
    //添加标签
    handleTagInputConfirm(){
      if(this.tagInputValue){
         this.labelList.push(this.tagInputValue)
      }
      this.tagInputVisible = false;
      this.tagInputValue = '';
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
          this.form.label = this.selectedLabelList.join(",");
          //this.form.specialPicUrl = encodeURIComponent(this.form.specialPicUrl);

          const that = this;
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
    },
  }
}
</script>

<style scoped>

</style>
