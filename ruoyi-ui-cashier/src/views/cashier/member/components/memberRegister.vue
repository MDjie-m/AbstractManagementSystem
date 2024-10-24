<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">

      <el-form-item label="姓名" prop="realName">
        <el-input v-model="form.realName" placeholder="请输入姓名"/>
      </el-form-item>

      <el-form-item label="手机号" prop="mobile">
        <el-input type="tel" maxlength="11" v-model="form.mobile" placeholder="请输入手机号"/>
      </el-form-item>

      <el-form-item label="性别" prop="sex">
        <el-select v-model="form.sex" placeholder="请选择性别" class="with100">
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="会员等级" prop="levelId">
        <el-select v-model="form.levelId" placeholder="请选择会员等级" class="with100">
          <el-option
            v-for="dict in levelOptions"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="form.status">
          <el-radio-button :label="0">
            正常
          </el-radio-button>
          <el-radio-button :label="1">
            停用
          </el-radio-button>
        </el-radio-group>
      </el-form-item>

      <el-form-item v-if="!form.memberId" label="支付密码" prop="payPassword">
        <el-input v-model="form.payPassword" type="password" class="with100" placeholder="请输入密码"
                  maxlength="20"/>
      </el-form-item>

      <el-form-item v-if="!form.memberId" label="确认密码:" prop="pwdConfirm">
        <el-input type="password" min="6" maxlength="20" v-model="form.pwdConfirm"/>
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" type="textarea" class="with100" placeholder="请输入内容"
                  maxlength="200"/>
      </el-form-item>


      <!--          <el-form-item label="当前金额" prop="currentAmount">-->
      <!--            <el-input v-model="form.currentAmount" placeholder="请输入当前金额"/>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="历史总金额" prop="totalAmount">-->
      <!--            <el-input v-model="form.totalAmount" placeholder="请输入历史总金额"/>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="门店" prop="storeId">-->
      <!--            <el-input v-model="form.storeId" placeholder="请输入门店"/>-->
      <!--          </el-form-item>-->

    </el-form>
    <div slot="footer" style="text-align: center">
      <el-button type="primary" @click="submitForm">{{ member ? '保存' : '注册' }}</el-button>
      <el-button type="info" @click="onCancel">取消</el-button>
    </div>
  </div>
</template>
<script>
import {getAllMemberLevel, registerMember, updateMember} from "@/api/cashier/member";
import member from "@/views/cashier/member/index.vue";

export default {
  props: ['member'],
  emits: ['onOk', 'onCancel'],
  dicts: ['sys_user_sex'],
  data() {
    const equalToPassword = (rule, value, callback) => {
      if(this.form.memberId){
        callback();
        return;
      }
      if (this.form.payPassword !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    const checkPhone = (rule, value, callback) => {
      const reg = /^1[3-9]\d{9}$/;
      if (value === '') {
        callback(new Error('手机号不能为空'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入正确的手机号'));
      } else {
        callback();
      }
    };
    return {
      rules: {
        realName: [
          {required: true, message: "姓名不能为空", trigger: "blur"}
        ],
        mobile: [
          {required: true, message: "手机号不能为空", trigger: "blur"},
          {validator: checkPhone, trigger: 'blur'}
        ],
        pwdConfirm: [
          {required: true, validator: equalToPassword, trigger: "blur"}
        ],

        levelId: [
          {required: true, message: "会员等级不能为空", trigger: "change"}
        ],
        status: [
          {required: true, message: "状态不能为空", trigger: "change"}
        ],
      },
      // 表单参数
      form: {
        memberId: null,
        realName: null,
        mobile: null,
        payPassword: null,
        currentAmount: null,
        totalAmount: null,
        sex: null,
        levelId: null,
        pwdConfirm:null,
        status: 0,
        remark: null
      },
      levelOptions: [], // 当前门店会员等级列表
    }
  },
  mounted() {
    this.getAllLevelByStoreId();
    if (this.member) {
      this.form = JSON.parse(JSON.stringify(this.member))
    }
  },
  methods: {
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.memberId != null) {
            updateMember(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.$emit("onOk")
            });
          } else {
            registerMember(this.form).then(response => {
              this.$emit("onOk")
              this.$modal.msgSuccess("注册成功");
            });

          }
        }
      });
    },
    onCancel() {
      this.$emit("onCancel")
    },
    getAllLevelByStoreId() {
      getAllMemberLevel().then(res => {
        this.levelOptions = (res.data || []).map(p => {
          return Object.assign({label: p.levelName, value: p.memberLevelId, raw: {listClass: ''}}, p)
        })
        console.log(111, this.levelOptions)
      })
    },
  }
}
</script>
