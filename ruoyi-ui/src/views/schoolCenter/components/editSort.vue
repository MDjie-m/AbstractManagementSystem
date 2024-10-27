<template>
  <el-dialog title="修改排序" :visible.sync="dialogVisible" width="30%" :show-close="false" :before-close="handleClose">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="ruleForm.sort" :min="0" placeholder="请输入排序" clearable style="width:280px"></el-input-number>
        <div class="tip">按照排序号倒序，即数字越大排序越靠前</div>
      </el-form-item>
      <div class="form-btn">
        <el-button @click="resetForm('ruleForm')" size="small">取消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" size="small">确定</el-button>
      </div>
    </el-form>
  </el-dialog>
</template>
<script>
export default {
  name: "EditSort",
  components: {
  },
  data() {
    return {
      ruleForm: {
        sort: ''
      },
      dialogVisible: false,
      rules: {
        sort: [
          { required: true, message: '请输入排序', trigger: 'change' }
        ],
      }
    }
  },
  methods: {
    handleClose() {
      this.dialogVisible = false
      this.$emit('close')
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.handleClose()
    }
  }
}
</script>

<style scoped>
.tip {
  padding: 0 0 0 50px;
}
.form-btn{
    width: 100%;
    text-align: right;
    border-top: 1px solid #dddddd;
    padding: 10px 0 0 0;
}
</style>