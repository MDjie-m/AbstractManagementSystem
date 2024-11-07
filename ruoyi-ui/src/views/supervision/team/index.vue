<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="团队类型" prop="teamType">
        <el-select v-model="queryParams.teamType" placeholder="请选择团队类型" clearable>
          <el-option
            v-for="dict in teamType"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="团队状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择团队状态" clearable>
          <el-option
            v-for="dict in dict.type.supervision_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="团队标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入团队标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="讲师" prop="consultantId">
        <el-select v-model="queryParams.consultantId"  clearable filterable>
          <el-option
            v-for="item in consultList"
            :key="item.id"
            :label="item.nickName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="团队标签" prop="teamType">
        <el-select v-model="queryParams.label" placeholder="请选择团队标签" clearable>
          <el-option
            v-for="dict in dict.type.team_sup_label"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

<!--      <el-form-item label="应付金额" class="amount">
        <el-input
          v-model="queryParams.lowAmount"
          placeholder="请输入最低应付金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
        <div style="margin: 0 10px">~</div>
        <el-input
          v-model="queryParams.highAmount"
          placeholder="请输入最高应付金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['course:order:export']"
        >导出</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:supervision-team:add']"
        >新建团队</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--  团队清单  -->
    <el-table v-loading="loading" :data="teamList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="团队类型" align="center" prop="teamType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.team_type" :value="scope.row.teamType"/>
        </template>
      </el-table-column>
      <el-table-column label="团队标题" align="center" prop="title"/>
      <el-table-column label="第几期" align="center" prop="periodNo"/>
      <el-table-column label="讲师" align="center" prop="consultantId">
        <template slot-scope="scope">
          <span>
             {{
              (consultList.find(item => item.id == scope.row.consultantId) || { nickName: '未知团队师' }).nickName
            }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="团队状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.supervision_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="本期活动次数" align="center" prop="cycleNumber" />
      <el-table-column label="满额人数" align="center" prop="maxNumPeople" />
      <el-table-column label="剩余名额" align="center" prop="surplusJoinNum" />

      <el-table-column label="入团价格" align="center" prop="price" sortable=""/>
      <el-table-column label="每周几活动" align="center" prop="weekDay" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.week_day" :value="scope.row.weekDay"/>
        </template>
      </el-table-column>
      <el-table-column label="活动时间" align="center" prop="lectureStartTime" />
      <el-table-column label="下课时间" align="center" prop="lectureEndTime" />
      <el-table-column label="标签" align="center" prop="label" />
      <el-table-column label="首次活动日期" align="center" prop="firstLectureDate" />
      <el-table-column label="创建时间" align="center" prop="createTime" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="view(scope.row)"
          >查看详情</el-button>

          <el-button
            size="mini"
            type="text"
            @click="viewSchedule(scope.row)"
          >查看排班</el-button>

          <el-button
            size="mini"
            type="text"
            @click="edit(scope.row)"
            v-hasPermi="['system:supervision-team:edit']"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            @click="del(scope.row)"
            v-hasPermi="['system:supervision-team:edit']"
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

    <!-- 添加-对话框 -->
    <add-form ref="addForm" :consultList="consultList" @handleOk="getList"/>

    <edit-form ref="editForm" :consultList="consultList" @handleOk="getList"/>

    <info-form ref="infoForm" :consultList="consultList" @handleOk="getList"/>

    <schedule-info-form ref="scheduleInfoForm" :consultList="consultList" @handleOk="getList"/>

  </div>
</template>

<script>
import {addTeam,editTeam, queryTeamList,deleteTeam} from "@/api/supervision/team";
import addForm from "./addForm";
import editForm from "./editForm";
import infoForm from "./info";
import scheduleInfoForm from "./scheduleInfo";
import {getConsultAll} from "@/api/psychology/consult";

export default {
  components: {
    addForm,
    editForm,
    infoForm,
    scheduleInfoForm
  },
  name: "team",
  dicts: ['team_type','supervision_status','week_day', 'team_sup_label'],
  data() {
    var validatePrice = (rule, value, callback) => {
      // 保留两位小数
      const regex = /^[0-9]+(\.[0-9]{1,2})?$/
      if (!regex.test(value)) {
        callback('课程价格为两位小数的数值表示')
      } else {
        callback()
      }
    }
    return {
      // 遮罩层
      loading: true,
      teamType: this.$constants.teamType,
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
      // 课程订单表格数据
      teamList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        teamType: null,
        status: null,
        title: null,
        consultantId: null,
      },
      // 团队师列表
      consultList: [],
    };
  },
  async created() {
    await this.getConsults();
    this.getList();
  },
  methods: {
    //获取团队师清单
    async getConsults() {
      const res = await getConsultAll();//{level:'5'}
      this.consultList = res.data
      console.log("*****************************",this.consultList)
      if (this.consultList.length === 1) {
        this.consultId = this.consultList[0].id
        this.queryParams.consultId = this.consultId
      }
    },

    /** 查询课程订单列表 */
    getList() {
      this.loading = true;
      queryTeamList(this.queryParams).then(response => {
        this.teamList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        teamType: null,
        status: null,
        title: null,
        consultantId: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('course/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.$refs.addForm.init()
    },

    /** 查看团队 */
    view(row) {
      this.$refs.infoForm.init(row.id)
    },

    /** 查看排班 */
    viewSchedule(row) {
      this.$refs.scheduleInfoForm.init(row.id)
    },

    /** 编辑团队 */
    edit(row) {
      this.$refs.editForm.init(row)
    },

    /** 删除团队 */
    /*del(row) {
      this.$modal.confirm('确认删除团队吗？').then(function() {
        console.log(1111111111111)
        deleteTeam(row.id).then(response => {
          console.log(22222222222222)
        /!*  this.$modal.msgSuccess("删除成功");
          this.$emit('handleOk')*!/
          this.getList();
        });
      }).then(() => {
      }).catch(() => {});
    },*/
    del(row) {
      this.$modal.confirm('确认删除团队吗？')
        .then(() => {
          return deleteTeam(row.id);
        })
        .then(response => {
          this.$modal.msgSuccess("删除成功");
          this.getList();  // Delete successful, now get new list
        })
        .catch(() => {
          // handle any errors here
        });
    }

  }
};
</script>
<style lang="scss" scoped>
.amount {
  ::v-deep {
    .el-form-item__content {
      display: inline-flex;
      align-items: center;
    }
    .el-input__inner {
      width: 150px;
    }
  }
}
</style>
