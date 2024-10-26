<template>
  <el-dialog title="新建班级" :visible.sync="dialogVisible" width="30%" :show-close="false" :before-close="handleClose">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px">
      <el-form-item label="年级" prop="name">
        <el-select placeholder="请选择" style="width:280px">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="班级名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="请输入班级名称" clearable style="width:280px" />
      </el-form-item>
      <el-form-item label="班主任" prop="name">
        <el-select placeholder="请选择班主任" style="width:280px">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="任课教师" prop="name">
        <el-select placeholder="请选择任课教师" style="width:280px">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
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
  name: "EditShool",
  components: {
  },
  data() {
    return {
      ruleForm: {
        sort: '',
        name: ""
      },
      options: [],
      dialogVisible: false,
      rules: {
        name: [
          { required: true, message: '请输入校区名称', trigger: 'change' }
        ],
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
  padding: 0 0 0 10px;
}
.form-btn {
  width: 100%;
  text-align: right;
  border-top: 1px solid #dddddd;
  padding: 10px 0 0 0;
}
</style>