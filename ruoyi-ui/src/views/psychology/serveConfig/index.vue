<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="服务名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入服务名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="服务类型" prop="type">
            <el-select v-model="queryParams.type" clearable>
              <el-option
                v-for="dict in typeList"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="关联咨询师" prop="cId">
            <el-select v-model="queryParams.cId" clearable filterable>
              <el-option
                v-for="item in consultList"
                :key="item.id"
                :label="item.nickName"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="咨询师级别" prop="level">
            <el-select v-model="queryParams.level" clearable>
              <el-option
                v-for="dict in levelList"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" clearable>
              <el-option
                v-for="dict in statusList"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="创建时间" prop="dateLimit">
            <el-date-picker
              v-model="timeVal"
              style="width: 280px;"
              size="small"
              value-format="yyyy-MM-dd"
              format="yyyy-MM-dd"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              @change="onchangeTime"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['psychology:serveConfig:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['psychology:serveConfig:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['psychology:serveConfig:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>



    <el-table v-loading="loading" :data="serveConfigList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="服务信息" align="center" prop="name">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['psychology:serveConfig:edit']"
          >{{ scope.row.name }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="售价(元)" align="center" prop="price" />
      <el-table-column label="销量" align="center" prop="sales" />
      <el-table-column label="咨询师级别" align="center" prop="level">
<!--  todo  会导致控制台报错    -->
        <template slot-scope="scope">
          {{ levelList.find(i => i.value === scope.row.level).label }}
        </template>
      </el-table-column>
      <el-table-column label="服务类型" align="center" prop="type">
        <template slot-scope="scope">
          {{ typeList.find(i => i.value === scope.row.type).label }}
        </template>
      </el-table-column>
      <el-table-column label="服务次数" align="center" prop="num" />
      <el-table-column label="服务时长(分钟）" align="center" prop="time" />
      <el-table-column label="排序" align="center" prop="zindex" />
      <el-table-column label="关联咨询师" align="center" prop="ref">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="getRef(scope.row)"
            v-hasPermi="['psychology:serveConfig:edit']"
          >{{ scope.row.ref }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="服务状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '0'" type="success">上架</el-tag>
          <el-tag v-else type="info">下架</el-tag>
        </template>
      </el-table-column>
      <el-table-column width="180" label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['psychology:consult:edit']"
          >编辑服务</el-button>
          <el-button
            size="mini"
            type="text"
            style="color: red"
            @click="changeStatus(scope.row)"
            v-hasPermi="['psychology:consult:edit']"
          >{{ scope.row.status === '0' ? '下架' : '上架' }}</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
            v-hasPermi="['psychology:consult:edit']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改咨询服务配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="服务名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入服务名称" />
        </el-form-item>
        <el-form-item label="咨询形式" prop="mode">
          <el-radio-group v-model="form.mode">
            <el-radio v-for="item in modeList.filter(i => i.label !== '全部')" :label="item.value">{{ item.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="咨询师级别" prop="level">
          <el-select v-model="form.level" >
            <el-option
              v-for="item in levelList.filter(i => i.label !== '全部')"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
              {{ item.label }}
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务对象" prop="serviceObjectList">
          <el-radio-group v-model="form.serviceObject">
            <el-radio v-for="item in serviceObjectList" :label="item.value">{{ item.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="服务类型" prop="type">
          <el-radio-group v-model="form.type" @input="changeType">
            <el-radio v-for="item in typeList.filter(i => i.label !== '全部')" :label="item.value" :disabled="isEditing">{{ item.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="服务次数" prop="num" >
          <el-input-number :disabled="form.type === 1 || isEditing" v-model="form.num" step-strictly	:max="999999" />
        </el-form-item>
        <el-form-item label="单次服务时长" prop="time">
          <el-input-number style="margin-right: 10px" v-model="form.time" :min="10" :max="60" :step="5" step-strictly/>分钟
        </el-form-item>
        <el-form-item label="售价" prop="price">
          <el-input-number v-model="form.price" :precision="2" :max="999999"/>
        </el-form-item>
        <el-form-item label="咨询师分成比例" prop="consultantRatio">
          <el-input-number v-model="form.consultantRatio" :precision="2" :max="1" :min="0"  :step="0.1"/>
        </el-form-item>
        <el-form-item label="排序" prop="zindex">
          <el-input-number v-model="form.zindex" step-strictly :max="9999"/>
        </el-form-item>
        <el-form-item label="限购人群" prop="bound">
          <el-radio-group v-model="form.bound">
            <el-radio v-for="item in limitStatus" :label="item.value">{{ item.value === 0 ? '不限制，所有人可购买' : '仅新用户可购买（未在平台成功支付过的用户）' }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="服务有效期" prop="end">
          <el-radio-group v-model="end">
            <el-radio :label="1">
              购买后<el-input-number style="margin: 0 10px" v-model="form.end" step-strictly :max="9999"/>天有效
            </el-radio>
            <el-radio :label="0">永久有效</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改咨询服务配置对话框 -->
    <el-dialog :title="consultTitle" :visible.sync="consultOpen" width="700px" append-to-body>
      <consult :data="consults"/>
    </el-dialog>
  </div>
</template>

<script>
import { listServeConfig, getServeConfig, getServeRef, delServeConfig, addServeConfig, updateServeConfig } from "@/api/psychology/serveConfig";
import { getConsultAll } from "@/api/psychology/consult";

import consult from "./consult";

export default {
  name: "ServeConfig",
  components: { consult },
  data() {
    return {
      // 遮罩层
      loading: true,
      isEditing: false,
      timeVal: [],
      consults: [],
      consultList: [],
      end: 0,
      limitStatus: this.$constants.limitStatus,
      typeList: this.$constants.typeList,
      modeList: this.$constants.modeList,
      levelList: this.$constants.levelList,
      serviceObjectList: this.$constants.serviceObjectList,
      statusList: this.$constants.comListStatus,
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
      // 咨询服务配置表格数据
      serveConfigList: [],
      // 弹出层标题
      title: "",
      consultTitle: "",
      // 是否显示弹出层
      open: false,
      consultOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dateLimit: null,
        cId: null,
        type: null,
        name: null,
        status: null,
      },
      // 表单参数
      form: {
       // serviceObjectList:[]
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: "服务名称不能为空", trigger: "blur" }
        ],
        mode: [
          { required: true, message: "咨询形式不能为空", trigger: "change" }
        ],
        type: [
          { required: true, message: "服务类型不能为空", trigger: "change" }
        ],
        time: [
          { required: true, message: "服务时长不能为空", trigger: "blur" }
        ],
        num: [
          { required: true, message: "服务次数不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "售价不能为空", trigger: "blur" }
        ],
        consultantRatio: [
          { required: true, message: "咨询师分成比例不能为空", trigger: "blur" }
        ],
        bound: [
          { required: true, message: "限购人群不能为空", trigger: "change" }
        ],
        end: [
          { required: true, message: "服务有效期不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getConsultServeRef()
  },
  methods: {
    async getConsultServeRef() {
      const res = await getConsultAll({ status: '0' })
      this.consultList = res.data
    },
    getRef(row) {
      if (row.ref > 0) {
        getServeRef({ serveId: row.id }).then(res => {
          this.consults = res.data
          this.consultTitle = row.name
          this.consultOpen = true
        })
      }
    },
    onchangeTime (e) {
      this.timeVal = e;
      this.queryParams.dateLimit = e ? this.timeVal.join(',') : ''
      this.queryParams.pageNum = 1
      // this.getList();
    },
    /** 查询咨询服务配置列表 */
    getList() {
      this.loading = true;
      listServeConfig(this.queryParams).then(response => {
        this.serveConfigList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.end = 0
      this.form = {
        id: null,
        mode: 2,
        type: 1,
        name: null,
        info: null,
        price: null,
        consultantRatio: 0,
        time: 50,
        num: 1,
        zindex: 999,
        bound: 0,
        end: 0,
        delFlag: "0",
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      };
      this.resetForm("form");
    },
    changeType(val) {
      console.log(val)
      if (val === 1) {
        console.log(val)
        this.form.num = 1
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.timeVal = []
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isEditing = false;
      this.open = true;
      this.title = "添加咨询服务配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isEditing = true;
      const id = row.id || this.ids
      getServeConfig(id).then(response => {
        this.form = response.data;
        if (this.form.end && this.form.end > 0) {
          this.end = 1
        }
        this.open = true;
        this.title = "修改咨询服务配置";
      });
    },
    changeStatus(row) {
      const title = row.status === '0' ? '确认要下架选中的服务吗？' : '确定要上架选中的服务吗？'
      this.$modal.confirm(title).then(function() {
        row.status = row.status === '0' ? '1' : '0'
        updateServeConfig(row)
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("修改成功");
      }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.end === 0) {
            this.form.end = 0
          }

          /*if (this.form.level != 5 && (this.form.serviceObject == 2 || this.form.serviceObject == 3) ){
            this.$modal.msgWarning("级别为[督导师]才可支持[个人督导][个人体验]服务")
            return
          }*/

          if (this.form.id != null) {
            updateServeConfig(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addServeConfig(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除咨询服务配置编号为"' + ids + '"的数据项？').then(function() {
        return delServeConfig(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('psychology/serveConfig/export', {
        ...this.queryParams
      }, `serveConfig_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
