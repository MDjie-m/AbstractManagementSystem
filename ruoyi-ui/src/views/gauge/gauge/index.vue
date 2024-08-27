<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="测评标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入测评标题" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="子标题" prop="subtitle">
        <el-input v-model="queryParams.subtitle" placeholder="请输入子标题" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="测评分类" prop="gaugeClass">
        <el-input v-model="queryParams.gaugeClass" placeholder="请输入测评分类" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="测评题数" prop="gaugeNum">
        <el-input v-model="queryParams.gaugeNum" placeholder="请输入测评题数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input v-model="queryParams.price" placeholder="请输入价格" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="测评次数" prop="num">
        <el-input v-model="queryParams.num" placeholder="请输入测评次数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['psychology:gauge:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['psychology:gauge:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['psychology:gauge:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['psychology:gauge:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gaugeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="测评标题" align="center" prop="title" />
      <el-table-column label="子标题" align="center" prop="subtitle" />
      <el-table-column label="头部图片" align="center" prop="headPicture" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.headPicture" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="测评分类" align="center" prop="gaugeClass" :formatter="getGaugeClassName" />
       <el-table-column label="计算类型" align="center" prop="type" width="100">
        <template slot-scope="scope">
          <el-tag> {{ cList.find(a => a.value === scope.row.type).label }} </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="测评题数" align="center" prop="gaugeNum" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="测评次数" align="center" prop="num" />
      <el-table-column label="操作" align="center" min-width="100px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['psychology:gauge:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="openGaugeQuestions(scope.row)" v-hasPermi="['psychology:gauge:edit']">测评问题</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['psychology:gauge:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改心理测评对话框 -->
    <el-dialog :title="title" :visible.sync="open" top="5vh" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="测评标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入测评标题" />
        </el-form-item>
        <el-form-item label="子标题" prop="subtitle">
          <el-input v-model="form.subtitle" placeholder="请输入子标题" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="头部图片">
              <!--          <image-upload v-model="form.headPicture" sizeTip="宽750px 高422px" :extraData="extraData"/>-->
              <my-cropper v-model="form.headPicture" sizeTip="宽750px 高422px" :extraData="extraData" :width="375" :height="211"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="列表展示图片">
              <!--          <image-upload v-model="form.listShowPicture" sizeTip="宽183px 高208px" :extraData="extraData" />-->
              <my-cropper v-model="form.listShowPicture" sizeTip="宽183px 高208px" :extraData="extraData" :width="183" :height="208"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="测评分类" prop="gaugeClass">
              <el-select v-model="form.gaugeClass" placeholder="请选择测评分类">
                <el-option v-for="item in gaugeClassList" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计算类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择计算类型">
                <el-option
                  v-for="item in cList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="测评题数" prop="gaugeNum">
              <el-input-number v-model="form.gaugeNum" :min="1" placeholder="请输入测评题数" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="价格" prop="price">
              <el-input-number v-model="form.price" :min="0.01" :precision="2"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="总分数" prop="gaugeScore">
              <el-input-number v-model="form.gaugeScore" :min="1" placeholder="请输入测评题数" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="比率" prop="gaugeRatio">
              <el-input-number v-model="form.gaugeRatio" :min="0.01" :precision="2"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="测评次数" prop="num">
              <el-input-number v-model="form.num" :min="0" placeholder="测评次数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="雷达展示" prop="radar">
              <el-switch v-model="form.radar" active-value="1" inactive-value="0" active-text="展示" inactive-text="隐藏"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="错题展示" prop="wrong">
              <el-switch v-model="form.wrong" active-value="1" inactive-value="0" active-text="展示" inactive-text="隐藏"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="测评说明" prop="gaugeDes">
          <editor v-model="form.gaugeDes" :height="120"  :extraData="extraData"/>
        </el-form-item>
        <el-form-item label="测评介绍">
          <editor v-model="form.introduce" :height="192" :extraData="extraData"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 心理测评项编辑对话框 -->
    <el-drawer :title="title" :visible.sync="questionOpen" size="35%" :with-header="false" append-to-body>
      <el-tabs v-model="activeTab" style="margin:0 20px">
        <el-tab-pane label="问题设置" name="questions">
          <questions v-bind:gaugeId="gaugeId" :gaugeType="gaugeType"></questions>
        </el-tab-pane>
<!--        <el-tab-pane v-if="[5,6,7,8].includes(gaugeType)" label="维度设置" name="lat">-->
        <el-tab-pane v-if="[2,8].includes(gaugeType)" label="维度设置" name="lat">
          <lat v-bind:gaugeId="gaugeId"/>
        </el-tab-pane>
        <el-tab-pane label="测评设置" name="setting">
           <setting v-bind:gaugeId="gaugeId" :gaugeType="gaugeType"></setting>
<!--           <multi v-if="gaugeType==2" v-bind:gaugeId="gaugeId" ></multi>-->
        </el-tab-pane>
      </el-tabs>
    </el-drawer>
  </div>
</template>

<script>
import {
  listGauge,
  getGauge,
  delGauge,
  addGauge,
  updateGauge,
} from "@/api/gauge/gauge";
import { listGaugeClass } from "@/api/gauge/gaugeClass";
import draggable from "vuedraggable";

import questions from "@/views/components/gauge/questions";
import setting from "@/views/components/gauge/setting";
import multi from "@/views/components/gauge/multi";
import lat from "@/views/components/gauge/lat";
export default {
  components: {
    draggable,
    questions,
    setting,
    multi,
    lat
  },
  name: "Gauge",
  data() {
    let validatePrice = (rule, value, callback) => {
      // 保留两位小数
      const regex = /^[0-9]+(\.[0-9]{1,2})?$/
      if (!regex.test(value)) {
        callback('测评价格为两位小数的数值表示')
      } else {
        callback()
      }
    }
    let validateNumber = (rule, value, callback) => {
      // 整数
      const regex = /^[0-9]+?$/
      if (!regex.test(value)) {
        callback('测评题数为整数的数值表示')
      } else {
        callback()
      }
    }
    return {
      // 上传
      extraData: {
        module: this.$constants['picModules'][1],
        type: this.$constants['picTypes'][2]
      },
      cList: this.$constants.gaugeCompute,
      activeTab: "questions",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 心理测评表格数据
      gaugeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //编辑测评问题
      questionOpen: false,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        subtitle: null,
        headPicture: null,
        introduce: null,
        gaugeClass: null,
        gaugeNum: null,
        price: null,
        gaugeId: null,
        num: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "测评标题不能为空", trigger: "blur" },
        ],
        subtitle: [
          { required: true, message: "子标题不能为空", trigger: "blur" },
        ],
        headPicture: [
          { required: true, message: "头部图片不能为空", trigger: "blur" },
        ],
        describe: [
          { required: true, message: "测评描述不能为空", trigger: "blur" },
        ],
        gaugeClass: [
          { required: true, message: "测评分类不能为空", trigger: "blur" },
        ],
        gaugeNum: [
          { required: true, message: "测评题数不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        gaugeScore: [
          { required: true, message: "总分不能为空", trigger: "blur" },
          { validator: validateNumber, trigger: "blur" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" },
          { validator: validatePrice, trigger: "blur" }
        ],
        gaugeRatio: [
          { required: true, message: "比率不能为空", trigger: "blur" },
          { validator: validatePrice, trigger: "blur" }
        ],
        gaugeId: [
          { required: true, message: "测评表单ID不能为空", trigger: "blur" },
        ],
      },
      gaugeClassList: [],

      gaugeId: null,
      gaugeType:null
    };
  },
  created() {
    this.getGaugeClassList();
    this.getList();
  },
  methods: {
    /** 查询心理测评列表 */
    getList() {
      this.loading = true;
      listGauge(this.queryParams).then((response) => {
        this.gaugeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    getGaugeClassList() {
      this.loading = true;
      listGaugeClass({pageNum: 1, pageSize: 999}).then((response) => {
        this.gaugeClassList = response.rows;
        // this.total = response.total;
        this.loading = false;
      });
    },
    getGaugeClassName(row) {
      for (let i = 0; i < this.gaugeClassList.length; i++) {
        if (row.gaugeClass == this.gaugeClassList[i].id) {
          return this.gaugeClassList[i].name;
        }
      }
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
        title: null,
        subtitle: null,
        headPicture: null,
        introduce: null,
        gaugeClass: null,
        gaugeNum: null,
        gaugeRatio: null,
        gaugeScore: null,
        wrong: null,
        radar: null,
        price: null,
        gaugeId: null,
        num: null,
        createTime: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      // 测评分类默认值
      this.form.gaugeClass = this.gaugeClassList[0].id
      // 计算类型默认值
      this.form.type = 1

      this.open = true;
      this.title = "添加心理测评";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getGauge(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改心理测评";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateGauge(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGauge(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除心理测评编号为"' + ids + '"的数据项？')
        .then(function () {
          return delGauge(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "psychology/gauge/export",
        {
          ...this.queryParams,
        },
        `gauge_${new Date().getTime()}.xlsx`
      );
    },
    openGaugeQuestions(data) {
      this.questionOpen = true;
      this.activeTab = 'questions'

      this.gaugeId = data.id;
      this.gaugeType = data.type
    },
  },
};
</script>

<style scoped>
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}

.select-item {
  display: flex;
  border: 1px dashed #fff;
  box-sizing: border-box;
  & .close-btn {
    cursor: pointer;
    color: #f56c6c;
  }
  & .el-input + .el-input {
    margin-left: 4px;
  }
  & .el-input + .el-input-number {
    margin-left: 4px;
  }
}
.select-item + .select-item {
  margin-top: 4px;
}
.select-item.sortable-chosen {
  border: 1px dashed #409eff;
}

.select-line-icon {
  line-height: 32px;
  font-size: 22px;
  padding: 0 4px;
  color: #777;
}
.option-drag {
  cursor: move;
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
