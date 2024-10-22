<template>
  <div v-if="form && form.id">
    <el-dialog title="审核入驻申请" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="formApprove" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="审核结果" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="(item,index) in status" :label="item.value">{{ item.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="申请类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择申请类型" @change="changeType">
            <el-option
              v-for="item in types"
              :key="item.value"
              :label="item.label"
              :value="parseInt(item.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入驻费用" prop="money">
          <el-input-number size="mini" v-model="form.money" :min="0" /> 元/年
        </el-form-item>
        <el-form-item label="结算比率" prop="ratio">
          <el-input-number size="mini" v-model="form.ratio" :min="0" /> %
        </el-form-item>-->
        <el-form-item label="审核说明" prop="remark">
          <el-input type="textarea" :rows="4" size="mini" maxlength="255" show-word-limit v-model="form.remark" placeholder="请填写审核不通过原因，用于告知申请人。" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { updatePartner } from "@/api/psychology/partner";

export default {
  name: "PartnerApprove",
  data() {
    return {
      open: false,
      status: [
        { label: '审核通过', value: '2' },
        { label: '审核不通过', value: '4' }
      ],
      types: this.$constants.partnerTypes,
      form: {},
      rules: {
        status: [
          { required: true, message: "请选择审核结果", trigger: "change" }
        ],
        /*type: [
          { required: true, message: "请选择申请类型", trigger: "change" }
        ],
        money: [
          { required: true, message: "请输入入驻费用", trigger: "blur" }
        ],
        ratio: [
          { required: true, message: "请输入结算比例", trigger: "blur" }
        ]*/
      }
    }
  },
  methods: {
    init(data) {
      this.form = JSON.parse(JSON.stringify(data))
      if (this.form.status === '0' || this.form.status === '1') {
        this.form.status = ''
      }
      this.changeType(this.form.type)
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
    async submit() {
      const that = this
      that.$refs["formApprove"].validate(valid => {
        if (!valid) {
          return false
        }
        if (that.form.status === '4' && !that.form.remark) {
          return that.$modal.msgError("请输入审核说明");
        }

        that.form.passTime = that.form.status === '3' ? new Date().getTime() : null

        updatePartner(that.form).then(res => {
          if (res.code === 200) {
            that.open = false
            that.$modal.msgSuccess("操作成功")
            that.$emit('handleOk')
          }
        })
      })
    },
    cancel() {
      this.open = false
    },
  }
}
</script>

<style scoped>

</style>
