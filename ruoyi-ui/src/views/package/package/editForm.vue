<template>
  <el-dialog title="修改套餐" :visible.sync="open" width="1000px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="150px">

      <el-form-item label="套餐名称" prop="productName">
        <el-input v-model="form.productName" placeholder="请输入套餐名称" />
      </el-form-item>

      <el-form-item label="简介" prop="blurb">
        <el-input v-model="form.blurb" placeholder="请输入简介" />
      </el-form-item>

      <el-form-item label="套餐产品图片" prop="productPicUrl">
        <my-cropper v-model="form.productPicUrl" sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>
      </el-form-item>

      <el-form-item label="头部图片" prop="headPicUrl">
        <my-cropper v-model="form.headPicUrl" sizeTip="宽504px 高254px" :extraData="extraData" :width="504" :height="254" disabled/>
      </el-form-item>

      <el-form-item label="详情图片" prop="detailPicUrl">
        <image-upload v-model="form.detailPicUrl" :extraData="extraData" />
      </el-form-item>

      <el-form-item label="套餐价格" prop="price">
        <el-input-number v-model="form.price" :min="0" /> 元
      </el-form-item>

      <el-form-item label="团队督导券张数" prop="teamSupNum" >
        <el-input-number v-model="form.teamSupNum" :min="0" :step="1" :precision="0" /> 张
      </el-form-item>

      <el-form-item label="团队督导券" prop="teamSupCouponTemplateId" v-show="form.teamSupNum != 0">
        <el-select v-model="form.teamSupCouponTemplateId" clearable filterable>
          <el-option
            v-for="item in couponTeamSupTemplateList"
            :key="item.id"
            :label="item.couponName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="个人督导券张数" prop="personSupNum" >
        <el-input-number v-model="form.personSupNum" :min="0" :step="1" :precision="0"/> 张
      </el-form-item>

      <el-form-item label="个人督导券" prop="personSupCouponTemplateId" v-show="form.personSupNum != 0">
        <el-select v-model="form.personSupCouponTemplateId" clearable filterable>
          <el-option
            v-for="item in couponPersonSupTemplateList"
            :key="item.id"
            :label="item.couponName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="个人体验券张数" prop="personExpNum" >
        <el-input-number v-model="form.personExpNum" :min="0" :step="1" :precision="0"/> 张
      </el-form-item>

      <el-form-item label="个人体验券" prop="personExpCouponTemplateId" v-show="form.personExpNum != 0">
        <el-select v-model="form.personExpCouponTemplateId" clearable filterable>
          <el-option
            v-for="item in couponPersonExpTemplateList"
            :key="item.id"
            :label="item.couponName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="课程券张数" prop="courseNum" >
        <el-input-number v-model="form.courseNum" :min="0" :step="1" :precision="0"/> 张
      </el-form-item>

      <el-form-item label="课程券" prop="courseCouponTemplateId" v-show="form.courseNum != 0">
        <el-select v-model="form.courseCouponTemplateId" clearable filterable>
          <el-option
            v-for="item in couponCourseTemplateList"
            :key="item.id"
            :label="item.couponName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { addPackage, editPackage } from "@/api/package/package";
import {getConsultAll} from "@/api/psychology/consult";
import {listTemplate} from "@/api/marketing/coupon";

export default {
  name: "editForm",
  dicts: ['supervision_type','supervision_status','week_day'],
  props: {
    consultList: {
      type: Array,
      default: () => []
    }
  },
  created() {
    this.getCouponList();
  },
  data() {
    return {
      open: false,
      type: 'add',// tryAdd
      types: this.$constants.partnerTypes,
      //优惠券模版清单
      couponTemplateList:[],
      couponTeamSupTemplateList:[],
      couponPersonSupTemplateList:[],
      couponPersonExpTemplateList:[],
      couponCourseTemplateList:[],
      form: {
        teamSupNum:0,
        personSupNum:0,
        personExpNum:0,
        courseNum:0,
        teamSupCouponTemplateId:'',
        personSupCouponTemplateId:'',
        personExpCouponTemplateId:'',
        courseCouponTemplateId:'',
      },
      // 上传
      extraData: {
        module: this.$constants['picModules'][2],
        type: this.$constants['picTypes'][2]
      },
      // 表单校验
      rules: {
        productName: [
          { required: true, message: "请输入套餐名称", trigger: "blur" }
        ],
        teamSupNum: [
          { required: true, message: "请输入团队督导券张数", trigger: "blur" }
        ],
        personSupNum: [
          { required: true, message: "请输入个人督导券张数", trigger: "blur" }
        ],
        personExpNum: [
          { required: true, message: "请输入个人体验券张数", trigger: "blur" }
        ],
        courseNum: [
          { required: true, message: "请输入课程券张数", trigger: "blur" }
        ],
        price: [
          { required: true, message: "请输入套餐价格", trigger: "change" }
        ],


      }
    }
  },
  methods: {
    init(data) {
      this.form = JSON.parse(JSON.stringify(data));
      console.log(this.form)
      this.open = true
    },
/*    initData(data) {
      this.form = data
      console.log(this.form)
      this.open = true
    },*/
    //获取优惠券模版清单
    async getCouponList() {
      const res = await listTemplate({"templateStatus":"0"});
      console.log("*****************************",res.rows)
      this.couponTemplateList = res.rows;
      this.couponTeamSupTemplateList = res.rows.filter(item => item.serverType === 21);
      this.couponPersonSupTemplateList = res.rows.filter(item => item.serverType === 22);
      this.couponPersonExpTemplateList = res.rows.filter(item => item.serverType === 23);
      this.couponCourseTemplateList = res.rows.filter(item => item.serverType === 24);
      console.log("*****************************",this.couponTeamSupTemplateList)
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(async valid => {
        if (valid) {
          const that = this;

          if (this.form.teamSupNum > 0 && !this.form.teamSupCouponTemplateId ){
            that.$modal.msgWarning("团队督导券张数大于0, 请指定券名称.")
            return;
          }
          if (this.form.personSupNum > 0 && !this.form.personSupCouponTemplateId ){
            that.$modal.msgWarning("个人督导券张数大于0, 请指定券名称.")
            return;
          }
          if (this.form.personExpNum > 0 && !this.form.personExpCouponTemplateId ){
            that.$modal.msgWarning("个人体验券张数大于0, 请指定券名称.")
            return;
          }
          if (this.form.courseNum > 0 && !this.form.courseCouponTemplateId ){
            that.$modal.msgWarning("课程券张数大于0, 请指定券名称.")
            return;
          }

          if (this.form.teamSupNum == 0){this.form.teamSupCouponTemplateId = null}
          if (this.form.personSupNum == 0){this.form.personSupCouponTemplateId = null}
          if (this.form.personExpNum == 0){this.form.personExpCouponTemplateId = null}
          if (this.form.courseNum == 0){this.form.courseCouponTemplateId = null}


          that.$modal.confirm('确认修改套餐吗？').then(function() {
            editPackage(that.form).then(response => {
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
      const that = this
    }
  }
}
</script>

<style scoped>

</style>
