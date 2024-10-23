<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="90px">
      <el-form-item label="咨询师姓名" prop="userId">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入咨询师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="status">
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
        <el-button type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['psychology:partner:export']"
        >导出</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="partnerList">
      <el-table-column label="申请编号" align="center" prop="id" />
      <el-table-column label="申请人姓名" align="center" prop="name" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          {{ scope.row.sex==1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column label="申请类型" align="center" prop="typeName"/>
      <el-table-column label="审核状态" align="center" prop="statusName"/>
      <el-table-column label="申请时间" align="center" prop="createTime" width="180"/>
      <el-table-column label="通过时间" align="center" prop="passTime" width="180"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="toDetail(scope.row.id)"
            v-hasPermi="['psychology:partner:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            :disabled="scope.row.status !== '1' && scope.row.status !== '3'"
            @click="openApp(scope.row)"
            v-hasPermi="['psychology:partner:edit']"
          >审核</el-button>
<!--          <el-button
            size="mini"
            type="text"
            :disabled="scope.row.status !== '2' && scope.row.status !== '3'"
            @click="openUser(scope.row)"
            v-hasPermi="['psychology:partner:account']"
          >{{ scope.row.status === '2' ? '查看账号' : '开通账号'}}</el-button>-->
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

    <approve ref="formApp" @handleOk="approveOk"/>

    <user ref="formUser"/>
  </div>
</template>

<script>
import { listPartner, createUser } from "@/api/psychology/partner";
import approve from "./approve";
import user from "./user";

export default {
  name: "PartnerIndex",
  components:{ approve, user },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      statusList: this.$constants.partnerStatus,
      // 总条数
      total: 0,
      // 咨询师入驻申请表格数据
      partnerList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderBy: '',
        name: null,
        status: 1,
        dateLimit: null
      },
      timeVal: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    onchangeTime (e) {
      this.timeVal = e;
      this.queryParams.dateLimit = e ? this.timeVal.join(',') : ''
      this.queryParams.pageNum = 1
      this.getList();
    },
    /** 查询咨询师入驻申请列表 */
    getList() {
      this.loading = true;
      listPartner(this.queryParams).then(response => {
        this.partnerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.timeVal = []
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 详情页 */
    toDetail(id) {
      this.$router.push({ path: '/partner/partnerDetail', query: { id: id }})
    },
    /** 审批 */
    openApp(row) {
      this.$refs.formApp.init(row)
    },
    openUser(row) {
      if (row.status === '2') {
        return this.doCreateUser(row.id)
      }

      this.$modal.confirm('请确认已收到咨询师的入驻费用后，再操作开通账号。').then(function() {
      }).then(() => {
        this.doCreateUser(row.id)
      }).catch(() => {});
    },
    doCreateUser(id) {
      createUser(id).then(response => {
        console.log(response)
        if (response.code === 200) {
          this.$refs.formUser.init(response.fm)
        }
        this.getList();
      });
    },
    /** 审批完成 */
    approveOk() {
      this.getList();
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('psychology/partner/export', {
        ...this.queryParams
      }, `入驻合同_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
