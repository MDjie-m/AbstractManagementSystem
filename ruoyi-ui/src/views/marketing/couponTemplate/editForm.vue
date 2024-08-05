<template>
  <el-dialog title="新建套餐" :visible.sync="open" width="1000px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">

      <el-form-item label="套餐名称" prop="productName">
        <el-input v-model="form.productName" placeholder="请输入套餐名称" />
      </el-form-item>

      <el-form-item label="套餐产品图片" prop="productPicUrl">
        <my-cropper v-model="form.productPicUrl" sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>
      </el-form-item>

      <el-form-item label="详情图片地址" prop="detailPicUrl">
        <my-cropper v-model="form.detailPicUrl" sizeTip="宽500px 高500px" :extraData="extraData" :width="500" :height="500"/>
      </el-form-item>

      <el-form-item label="套餐价格" prop="price">
        <el-input-number v-model="form.price" :min="0" /> 元
      </el-form-item>

      <el-form-item label="团队督导券张数" prop="teamSupNum" >
        <el-input-number v-model="form.teamSupNum" :min="0" :step="1" :precision="0"/> 次
      </el-form-item>

      <el-form-item label="个人督导券张数" prop="personSupNum" >
        <el-input-number v-model="form.personSupNum" :min="0" :step="1" :precision="0"/> 次
      </el-form-item>

      <el-form-item label="个人体验券张数" prop="personExpNum" >
        <el-input-number v-model="form.personExpNum" :min="0" :step="1" :precision="0"/> 次
      </el-form-item>

      <el-form-item label="课程券张数" prop="courseNum" >
        <el-input-number v-model="form.courseNum" :min="0" :step="1" :precision="0"/> 次
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
      type: 'add',// tryAdd
      types: this.$constants.partnerTypes,
      form: {
        teamSupNum:0,
        personSupNum:0,
        personExpNum:0,
        courseNum:0
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
      this.open = true
    },
    initData(data) {
      this.form = data
      console.log(this.form)
      this.open = true
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(async valid => {
        if (valid) {
          const that = this

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
