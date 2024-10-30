<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px" >
      <el-row>
        <el-col :span="8">
          <el-form-item label="系统账号" prop="userName">
            <el-input size="mini" maxlength="20" show-word-limit v-model="form.userName" :disabled="!!form.id" placeholder="请输入系统账号" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="咨询师姓名" prop="nickName">
            <el-input size="mini" maxlength="20" show-word-limit v-model="form.nickName"  placeholder="请输入咨询师姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号码" prop="phonenumber">
            <el-input size="mini" maxlength="11" show-word-limit v-model="form.phonenumber" placeholder="请输入手机号码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="邮箱" prop="email">
            <el-input size="mini" v-model="form.email" placeholder="请输入邮箱" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="咨询语种" prop="lang">
            <el-input size="mini" maxlength="50" show-word-limit v-model="form.lang" placeholder="咨询语种" />
          </el-form-item>
        </el-col>
        <el-form-item label="咨询师级别" prop="level">
          <el-select  maxlength="50" v-model="form.level" placeholder="请选择咨询师级别" clearable>
            <el-option
              v-for="item in levelList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="服务对象" prop="serviceObject">
            <el-checkbox-group v-model="form.serviceObject">
              <el-checkbox v-for="item in dict.type.service_object" :value="item.value" :label="item.value">{{ item.label }}</el-checkbox>
            </el-checkbox-group>

<!--            <el-radio-group v-model="form.serviceObject">
              <el-radio v-for="item in dict.type.service_object" :value="item.value" :label="item.label">{{ item.label }}</el-radio>
            </el-radio-group>-->
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="form.sex">
              <el-radio v-for="item in dict.type.consult_sex" :label="item.label">{{ item.label }}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8" >
          <el-form-item label="可咨询形式" prop="mode">
            <el-checkbox-group v-model="form.mode">
              <el-checkbox v-for="item in dict.type.consult_type" :label="item.label">{{ item.label }}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="所在地区" prop="city">
            <el-cascader
              size="small"
              filterable
              v-model="form.city"
              :options="city"
              collapse-tags
              clearable>
            </el-cascader>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="咨询人数" prop="workNum">
            <el-input-number size="mini" v-model="form.workNum" :min="0" placeholder="请输入咨询人数" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="从业年限(年)" prop="workHours">
            <el-input-number size="mini" v-model="form.workHours" :min="0" placeholder="请输入从业年限" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="服务时长(小时)" prop="workTime">
            <el-input-number size="mini" v-model="form.workTime" :min="0" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="督导时长(小时)" prop="workNum">
            <el-input-number size="mini" v-model="form.buySupTime" :min="0" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="体验时长(小时)" prop="workHours">
            <el-input-number size="mini" v-model="form.buyExpTime" :min="0"  disabled/>
          </el-form-item>
        </el-col>
      </el-row>

<!--      <el-row>-->
<!--        <el-col :span="12">-->
<!--          <el-form-item label="状态" prop="status">-->
<!--            <el-switch v-model="form.status" active-value="0" inactive-value="1" active-text="启用" inactive-text="禁用"/>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--      </el-row>-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="列表图片" prop="img">
<!--            <image-upload v-model="form.img" :extraData="extraData"/>-->
            <my-cropper v-model="form.img" sizeTip="宽140px 高174px" :extraData="extraData" :width="140" :height="174"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="头像" prop="avatar">
<!--            <image-upload v-model="form.avatar" sizeTip="宽172px 高172px" :extraData="extraData"/>-->
            <my-cropper v-model="form.avatar" sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="企业微信名片" prop="wxCard">
<!--            <image-upload v-model="form.wxCard" :extraData="extraData"/>-->
            <my-cropper v-model="form.wxCard" sizeTip="宽100px 高100px" :extraData="extraData" :width="100" :height="100"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="擅长领域" prop="way">
            <el-cascader
              size="small"
              v-model="form.way"
              :options="wayList"
              :props="{ multiple: true }"
              collapse-tags
              filterable
              clearable>
            </el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="执业资格" prop="qualification">
            <el-select style="width: 100%" v-model="form.qualification" multiple placeholder="请选择执业资格" clearable @change="changeQualification">
              <el-option
                v-for="item in dict.type.consult_qualification"
                :key="item.label"
                :label="item.label"
                :value="item.label"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="首页展示资格" prop="qualification">
            <el-select style="width: 100%" v-model="form.indexQualification" placeholder="请选择执业资格" clearable>
              <el-option
                v-for="item in form.qualification"
                :key="item"
                :label="item"
                :value="item"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="学历/简介" prop="info">
            <el-input type="textarea" :rows="4" size="mini" maxlength="100" show-word-limit v-model="form.info" placeholder="学历/简介" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="咨询寄语" prop="zxWord">
            <el-input type="textarea" :rows="4" size="mini" maxlength="100" show-word-limit v-model="form.zxWord" placeholder="请输入咨询寄语" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="咨询风格" prop="zxStyle">
            <el-input type="textarea" :rows="4" size="mini" maxlength="100" show-word-limit v-model="form.zxStyle" placeholder="请输入咨询风格" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="咨询须知" prop="notice">
            <el-input type="textarea" :rows="4" size="mini" maxlength="100" show-word-limit v-model="form.notice" placeholder="请输入咨询须知" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="咨询流派" prop="genre">
            <el-input type="textarea" :rows="4" size="mini" maxlength="100" show-word-limit v-model="form.genre" placeholder="请输入咨询流派" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="分享推荐" prop="share">
            <el-input type="textarea" :rows="4" size="mini" maxlength="100" show-word-limit v-model="form.share" placeholder="请输入分享推荐" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="18">
          <el-form-item label="受训经历" prop="experience">
            <el-table :data="form.experience" border fit highlight-current-row style="width: 100%" size="mini" >
              <el-table-column label="时间" align="center" prop="time">
                <template slot-scope="scope">
                  <el-form-item :rules="rules.time" :prop="'experience.'+scope.$index+'.time'">
                    <el-date-picker
                      size="mini"
                      v-model="scope.row.time"
                      @change="changeTime"
                      value-format="yyyy-MM"
                      type="monthrange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期">
                    </el-date-picker>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="描述" align="center" prop="info">
                <template slot-scope="scope">
                  <el-form-item :rules="rules.info" :prop="'experience.'+scope.$index+'.info'">
                    <el-input size="mini" v-model="scope.row.info" />
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column align="center" label="操作" width="100">
                <template slot-scope="scope">
                  <el-button
                    v-if="scope.$index >= 0"
                    size="mini"
                    type="text"
                    style="color: red"
                    @click="delItem(form.experience, scope.$index)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-button plain type="primary" icon="el-icon-plus" @click="addItem(form.experience)">
              添加经历
            </el-button>
          </el-form-item>
        </el-col>
      </el-row>

<!--      <el-row>-->
<!--        <el-col :span="18">-->
<!--          <el-form-item label="图文详情" prop="detail">-->
<!--            <editor v-model="form.detail" :min-height="192" :height="300" :extraData="extraData"/>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--      </el-row>-->
    </el-form>

    <div class="footer">
      <el-button type="primary" @click="submitForm" style="margin-right: 20px">确 定</el-button>
      <el-button @click="goBack">取 消</el-button>
    </div>
  </div>
</template>

<script>
import { getConsult, addConsult, updateConsult } from "@/api/psychology/consult";
import { getTree } from "@/api/psychology/type";
import { getCascaderData } from "@/utils/pc-city";
import {levelList, serviceObjectList} from "@/utils/constants";

export default {
  name: "ConsultDetail",
  dicts: ['consult_sex', 'consult_type', 'consult_qualification' , "service_object"],
  data() {
    return {
      levelList: this.$constants.levelList,
      serviceObjectList: this.$constants.serviceObjectList,
      // 上传
      extraData: {
        module: this.$constants['picModules'][2],
        type: this.$constants['picTypes'][2]
      },
      eList: [],
      wayList: [],
      city: [],
      qualifications: [],
      form: {
        way: [],
        city: [],
        mode: [],
        serviceObject: [],
        qualification: [],
        indexQualification: '',
        experience: []
      },
      // 表单校验
      rules: {
        catId: [
          { required: true, message: "分类不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "系统账号不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '系统账号长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: "咨询师姓名不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '咨询师姓名长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        sex: [ { required: true, message: "请选择性别", trigger: "change" } ],
        way: [ { required: true, message: "请选择擅长领域", trigger: "change" } ],
        mode: [ { required: true, message: "请选择咨询形式", trigger: "change" } ],
        workNum: [ { required: true, message: "请输入咨询人数", trigger: "blur" } ],
        workTime: [ { required: true, message: "请输入服务时长", trigger: "blur" } ],
        workHours: [ { required: true, message: "请输入从业时间", trigger: "blur" } ],
        email: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        time: [
          { required: true, message: '请选择受训时间', trigger: 'blur' }
        ],
        info: [
          { message: '请输入学历', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    // 获取分类
    // 获取标签
    // 获取性别
    // 获取咨询方向
    this.city = getCascaderData()
    this.getAttr()
    if (this.$route.query && this.$route.query.id) {
      this.form.id = this.$route.query.id
      this.getInfo()
    }
  },
  methods: {
    changeQualification() {
      if (this.form.indexQualification && !this.form.qualification.includes(this.form.indexQualification)) {
        this.form.indexQualification = ''
      }
    },
    changeTime() {
      // console.log(this.form.experience)
      this.form.experience = this.form.experience.sort((a, b) => {
        return (a.time[0] < b.time[0]) ? -1 : (a.time[0] > b.time[0]) ? 1 : 0
      });
    },
    addItem(items) {
      console.log(items)
      items.push(Object.assign({}, {
        time: '',
        info: ''
      }))
    },
    delItem(items, index) {
      items.splice(index, 1)
    },
    async getAttr() {
      const res = await getTree()
      this.wayList = res.data
    },
    getInfo() {
      getConsult(this.form.id).then(response => {
        response.data.experience = response.data.experience ? JSON.parse(response.data.experience) : []
        response.data.qualification = response.data.qualification ? response.data.qualification.split(',') : []
        response.data.way = response.data.way ? JSON.parse(response.data.way) : []
        response.data.mode = response.data.mode ? response.data.mode.split('/') : []
        response.data.serviceObject = response.data.serviceObject ? response.data.serviceObject.split(',') : []
        response.data.city = response.data.city && response.data.province ? [response.data.province, response.data.city] : []

        this.form = response.data
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        const form = JSON.parse(JSON.stringify(this.form))
        form.way = JSON.stringify(form.way)
        form.experience = JSON.stringify(form.experience)
        form.mode = form.mode.join('/')
        form.serviceObject.sort((a, b) => a - b);//排序
        form.serviceObject = form.serviceObject.join(',')//list转string
        const city = form.city
        form.province = city[0]
        form.city = city[1]

        form.qualification = form.qualification.join(',')

        if (valid) {
          if (this.form.id != null) {
            updateConsult(form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.goBack()
            });
          } else {
            addConsult(form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.goBack()
              // this.getList();
            });
          }
        }
      });
    },
    goBack() {
      this.$tab.closeOpenPage()
      this.$router.push({ path: '/psychology/consult' })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userId: null,
        catId: null,
        tabs: null,
        way: null,
        info: null,
        detail: null,
        avatar: null,
        wxCard: null,
        zxWord: null,
        zxStyle: null,
        notice: null,
        experience: [],
        workNum: null,
        workTime: null,
        buyExpTime: null,
        buySupTime: null,
        workHours: null,
        delFlag: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
  }

}
</script>

<style scoped>
.footer {
  width: 100%;
  margin-top: 20px;
  text-align: center;
  height: 76px;
  line-height: 76px;
}
/deep/ .el-switch .el-switch__core, .el-switch .el-switch__label {
  width: 60px !important;
  font-size: 12px !important;
}
::v-deep .el-upload--picture-card {
  width: 80px;
  height: 80px;
}
::v-deep .el-upload {
  width: 80px;
  height: 80px;
  line-height: 80px;
}
::v-deep .el-upload-list--picture-card .el-upload-list__item {
  width: 80px;
  height: 80px;
}
::v-deep .el-upload-list--picture-card .el-upload-list__item-thumbnail {
  width: 80px;
  height: 80px;
  line-height: 80px;
}
::v-deep .avatar {
  width: 80px;
  height: 80px;
}
::v-deep .el-upload__tip {
  margin: 0;
}
</style>
