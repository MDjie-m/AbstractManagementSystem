<template>
  <div class="pwd-container">
    <el-form label-width="100px" ref="form" :model="pwdForm" :rules="rules">
      <el-form-item label="旧的密码:" prop="oldPwd">
        <el-input type="password" min="6" maxlength="20" v-model="pwdForm.oldPwd"/>
      </el-form-item>
      <el-form-item label="新的密码:" prop="pwd">
        <el-input type="password" min="6" maxlength="20" v-model="pwdForm.pwd"/>
      </el-form-item>
      <el-form-item label="确认密码:" prop="pwdConfirm">
        <el-input type="password" min="6"  maxlength="20" v-model="pwdForm.pwdConfirm"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="margin-left: 20px" round @click="onClick ">
          确认
        </el-button>
      </el-form-item>
    </el-form>


  </div>
</template>
<script>

import {updatePayPwd} from "@/api/cashier/member";

export default {
  props: ['memberId'],
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.pwdForm.pwd !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      rules: {
        oldPwd:[
          {required: true, message: "新密码不能为空", trigger: "blur"},
          {min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur"},
        ],
        pwd: [
          {required: true, message: "新密码不能为空", trigger: "blur"},
          {min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur"},
          {pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur"}
        ],
        pwdConfirm: [
          {required: true, message: "确认密码不能为空", trigger: "blur"},
          {required: true, validator: equalToPassword, trigger: "blur"}
        ]
      },
      pwdForm: {
        oldPwd:'',
        pwd: '',
        pwdConfirm: ''
      },
      loading: false,
      currentTitle: '0'
    }
  },
  created() {

  },
  methods: {
    onClick() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updatePayPwd({
            memberId: this.memberId, ...this.pwdForm
          }).then(response => {
            this.$modal.msgSuccess("修改成功");
          });
        }
      });
    }
  }

}
</script>

<style scoped lang="scss">
.pwd-container {
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 1;
}

.pwd-footer {
  margin-top: auto;
  padding-top: 20px;
  background-color: #FFFFFF;

}
</style>
