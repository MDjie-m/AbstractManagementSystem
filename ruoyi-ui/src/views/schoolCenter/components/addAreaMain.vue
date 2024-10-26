<template>
  <div class="addAreaMain">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="学段" prop="sort">
        <el-checkbox-group v-model="checkList">
          <el-checkbox label="幼儿园">
            <div class="flex">
              <div class="mr-10">幼儿园</div>
              <el-select placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
              <span class="ml">个年级</span>
            </div>
          </el-checkbox>
          <el-checkbox label="小学">
            <div class="flex">
              <div class="mr-10">小学</div>
              <el-select placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
              <span class="ml">个年级</span>
            </div>
          </el-checkbox>
          <el-checkbox label="初中">
            <div class="flex">
              <div class="mr-10">初中</div>
              <el-select placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
              <span class="ml">个年级</span>
            </div>
          </el-checkbox>
          <el-checkbox label="高中">
            <div class="flex">
              <div class="mr-10">高中</div>
              <el-select placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
              <span class="ml">个年级</span>
            </div>
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <div class="form-btn">
        <el-button type="primary" @click="submitForm('ruleForm')">下一步</el-button>
        <el-button @click="resetForm('ruleForm')">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "AddAreaMain",
  components: {
  },
  data() {
    return {
      ruleForm: {
      },
      checkList: [],
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
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit('next')
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$emit('closeAdd')
    }
  }
}
</script>

<style scoped lang="scss">
.addAreaMain {
  padding: 20px;
  .tip {
    color: #caced4;
  }
  .flex {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  ::v-deep .el-checkbox-group {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }
  .ml {
    margin-left: 10px;
  }
  .mr-10 {
    width: 50px;
  }
  .form-btn {
    width: 100%;
    text-align: right;
    border-top: 1px solid #dddddd;
    padding: 10px 0 0 0;
  }
}
</style>