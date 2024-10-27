<template>
  <div class="addAll">
    <el-form :model="ruleForm" inline :rules="rules" ref="ruleForm">
      <el-tabs v-model="activeName">
        <el-tab-pane label="幼儿园" name="0">
          <el-form-item label="" prop="name" v-for="item in 5" :key="item">
            <div class="flex">
              <div class="mr-10">幼儿园</div>
              <el-select placeholder="请选择" style="width:100px">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
              <span class="ml">个年级</span>
            </div>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <div class="form-btn">
        <el-button @click="font('ruleForm')">上一步</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">完成</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "AddAll",
  components: {
  },
  data() {
    return {
      ruleForm: {
        name: "",
        sort: ''
      },
      activeName: '0',
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      value: '',
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
    font(formName) {
      this.$refs[formName].resetFields();
      this.$emit('font')
    }
  }
}
</script>

<style scoped lang="scss">
.addAll {
  padding: 20px;
  .tip {
    color: #caced4;
  }
  .flex {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  .ml {
    margin-left: 10px;
  }
  .mr-10 {
    width: 50px;
    color: #a5aab6;
    width: 60px;
  }
  .form-btn {
    width: 100%;
    text-align: right;
    border-top: 1px solid #dddddd;
    padding: 10px 0 0 0;
  }
}
</style>