<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="78px">
      <el-form-item label="姓名" prop="userId">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入咨询师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <router-link :to="{ path:'/psychology/consultDetail' }">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            v-hasPermi="['psychology:consult:add']"
          >新增</el-button>
        </router-link>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['psychology:consult:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddAllRelation"
          v-hasPermi="['psychology:consult:addAllRelation']"
        >初始化服务关联</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="consultList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column v-if="columns[1].visible" label="登录名" align="center" prop="userName" />
      <el-table-column v-if="columns[2].visible" label="头像" align="center" prop="avatar">
        <template slot-scope="scope">
          <image-preview :src="scope.row.avatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column v-if="columns[3].visible" label="用户邮箱" align="center" prop="email" />
      <el-table-column v-if="columns[4].visible" label="手机号码" align="center" prop="phonenumber" />
      <el-table-column v-if="columns[5].visible" label="用户性别" align="center" prop="sex" />
      <el-table-column v-if="columns[14].visible" label="服务数量" align="center" prop="serve">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleServeList(scope.row)"
          >{{ scope.row.serve }}</el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column v-if="columns[6].visible" label="标签" align="center" prop="tabs" />-->
      <!--      <el-table-column v-if="columns[7].visible" label="咨询方向" align="center" prop="way" />-->
      <!--      <el-table-column v-if="columns[8].visible" label="介绍文案" align="center" prop="info" />-->
      <!--      <el-table-column v-if="columns[10].visible" label="咨询人数" align="center" prop="workNum" />-->
      <!--      <el-table-column v-if="columns[11].visible" label="服务时长(小时)" align="center" prop="workTime" />-->
      <!--      <el-table-column v-if="columns[12].visible" label="从业时间(年)" align="center" prop="workHours" />-->
      <el-table-column v-if="columns[13].visible" label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" :disabled="!checkPermi(['psychology:consult:serveUpdate'])" @change="handleStatus(scope.row)" active-value="0" inactive-value="1" active-text="启用" inactive-text="禁用"/>
        </template>
      </el-table-column>
      <el-table-column v-if="columns[15].visible" label="是否显示" align="center" prop="isShow">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isShow" :disabled="!checkPermi(['psychology:consult:serveUpdate'])" @change="handleShow(scope.row)" active-value="0" inactive-value="1" active-text="显示" inactive-text="隐藏"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button  size="mini" type="text" @click="handleFinanceWin(scope.row)">
             账户
          </el-button>
          <el-button  size="mini" type="text" @click="handleAddressWin(scope.row)">
             地址
          </el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleServeUpdate(scope.row)"
            v-hasPermi="['psychology:consult:serveUpdate']"
          >关联服务</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['psychology:consult:edit']"
          >修改</el-button>
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

    <!-- 服务列表 -->
    <el-dialog :title="titleServe" :visible.sync="openServe" width="900px" append-to-body>
      <serve v-if="openServe" :id="consultId" @setServe="getList"/>
    </el-dialog>

    <!-- 修改心理咨询师服务对话框 -->
    <el-dialog title="关联服务" :visible.sync="openServeRef" width="900px" append-to-body>
      <serve-ref v-if="openServeRef" :id="consultId" @setServe="setServe"/>
      <div slot="footer" class="dialog-footer">
        <el-button :disabled="cIds.length === 0" type="primary" @click="updateServeForm">确定关联</el-button>
        <el-button @click="cancelServe">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 咨询师账户余额信息 -->
    <el-dialog title="账户" :visible.sync="openFinanceRef" width="900px" append-to-body>
      <account-ref v-if="openFinanceRef" :id="bingConsultId"/>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelFinance">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 咨询地址信息 -->
    <el-dialog title="地址" :visible.sync="openAddressRef" width="900px" append-to-body>
      <addressRef v-if="openAddressRef" :id="bingConsultId" />
    </el-dialog>

  </div>
</template>

<script>
import { refConsultServe, adminListConsult, delConsult, updateConsult,accountListConsult ,addAllRelation} from "@/api/psychology/consult";

import serve from "./serve";
import serveRef from "./serveRef";
import accountRef from "../account/index";
import addressRef from "../address/index";
import { checkPermi } from "@/utils/permission";

export default {
  name: "Consult",
  components: { serve, serveRef ,accountRef,addressRef},
  // dicts: ['consult_sex','consult_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      consultId: '',
      // 选中数组
      statusList: this.$constants.comListStatus,
      ids: [],
      cIds: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 心理咨询师表格数据
      consultList: [],
      // 弹出层标题
      title: "",
      titleServe: "",
      // 是否显示弹出层
      open: false,
      // 服务编辑弹出层
      openServe: false,
      openServeRef: false,
      openFinanceRef: false,
      openAddressRef: false,
      bingConsultId: '',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderBy: '',
        userName: null,
        status: null,
        dateLimit: null
      },
      timeVal: [],
      // 列信息
      columns: [
        { key: 0, label: `分类`, visible: true },
        { key: 1, label: `登录名`, visible: true },
        { key: 2, label: `头像`, visible: true },
        { key: 3, label: `用户邮箱`, visible: false },
        { key: 4, label: `手机号码`, visible: false },
        { key: 5, label: `用户性别`, visible: true },
        { key: 6, label: `标签`, visible: false },
        { key: 7, label: `咨询方向`, visible: false },
        { key: 8, label: `介绍文案`, visible: false },
        { key: 9, label: `图文详情`, visible: false },
        { key: 10, label: `咨询人数`, visible: false },
        { key: 11, label: `服务时长`, visible: false },
        { key: 12, label: `从业时间`, visible: false },
        { key: 13, label: `状态`, visible: true },
        { key: 14, label: `服务数量`, visible: true },
        { key: 15, label: `是否显示`, visible: true },
      ]
    };
  },
  created() {
    // 获取分类
    // 获取标签
    // 获取性别
    // 获取咨询方向
    this.getList();
  },
  methods: {
    checkPermi,
    updateServeForm() {
      if (this.consultId && this.cIds.length > 0) {
        const data = {
          consultId: this.consultId,
          ids: this.cIds
        }
        refConsultServe(data).then(response => {
          if (response.code === 200) {
            this.$modal.msgSuccess("修改成功")
            this.cancelServe()
            this.getList()
          }
        });
      }
    },
    // 取消按钮
    cancelServe() {
      this.openServeRef = false;
      this.consultId = ''
      this.cIds = []
    },
    cancelFinance() {
      this.openFinanceRef = false;
      this.bingConsultId = ''
    },
    cancelAddress() {
      this.cancelAddress = false;
      this.bingConsultId = ''
    },
    setServe (ids) {
      this.cIds = ids
    },
    /** 修改服务按钮操作 */
    handleServeUpdate(row) {
      this.consultId = row.id
      this.openServeRef = true;
      this.titleServe = "关联服务";
    },
    /** 财务按钮操作 */
    handleFinanceWin(row) {
      this.bingConsultId = row.id
      this.openFinanceRef = true;
      this.titleServe = "财务服务";
    },
    /** 地址按钮操作 */
    handleAddressWin(row) {
      this.bingConsultId = row.id
      console.log("id:"+this.bingConsultId)
      this.openAddressRef = true;
      this.titleServe = "地址服务";
    },
    /** 修改服务按钮操作 */
    handleServeList(row) {
      this.consultId = row.id
      this.openServe = true;
      this.titleServe = row.nickName;
    },
    onchangeTime (e) {
      this.timeVal = e;
      this.queryParams.dateLimit = e ? this.timeVal.join(',') : ''
      this.queryParams.pageNum = 1
      this.getList();
    },
    /** 查询心理咨询师列表 */
    getList() {
      this.loading = true;
      adminListConsult(this.queryParams).then(response => {
        this.consultList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询账户信息列表 */
    getFinanceList() {
      this.loading = true;
      accountListConsult(this.queryParams).then(response => {
        this.consultList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      console.log(this.queryParams)
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.timeVal = []
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改状态操作 */
    handleStatus(row) {
      const that = this
      const content = row.status === '0' ? '确定要启用该咨询师吗？' : '确认禁用该咨询师吗？'
      const type = row.status === '0' ? 'success' : 'warning'

      this.$confirm(content, '提示', {
        type: type
      }).then(() => {
        if (row.status === '1') {
          row.isShow = '1'
        }

        updateConsult(row).then(response => {
          that.$modal.msgSuccess("修改成功");
          that.getList();
        });
      }).catch(() => {
        row.status = row.status === '0' ? '1' : '0'
      });
    },
    handleShow(row) {
      const that = this
      const content = row.isShow === '0' ? '确定要显示该咨询师吗？' : '确认隐藏该咨询师吗？'
      const type = row.isShow === '0' ? 'success' : 'warning'

      this.$confirm(content, '提示', {
        type: type
      }).then(() => {
        updateConsult(row).then(response => {
          that.$modal.msgSuccess("修改成功");
          that.getList();
        });
      }).catch(() => {
        row.isShow = row.isShow === '0' ? '1' : '0'
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const id = row.id
      this.$router.push({ path: '/psychology/consultDetail', query: { id: id }})
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除心理咨询师编号为"' + ids + '"的数据项？').then(function() {
        return delConsult(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('psychology/consult/export', {
        ...this.queryParams
      }, `consult_${new Date().getTime()}.xlsx`)
    },
    /** 初始化服务关联关系*/
    handleAddAllRelation(){
      this.$modal.confirm('是否确认为所有咨询师 执行初始化服务关联？ \r\n 会清空目前的服务关联, 并为每个咨询师关联与其相符的全部服务.').then(function() {
        return addAllRelation();
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("关联成功");
      }).catch(() => {});
    },

  }
};
</script>
<style scoped>
/deep/ .el-switch .el-switch__core, .el-switch .el-switch__label {
  width: 60px !important;
  font-size: 12px !important;
}
</style>
