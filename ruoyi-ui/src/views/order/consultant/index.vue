<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">

      <el-form-item label="订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="订单服务类型" prop="serverType">
        <el-select v-model="queryParams.serverType" placeholder="请选择订单服务类型" clearable>
          <el-option
            v-for="dict in dict.type.order_server_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="下单人" prop="payConsultantId">
        <el-select v-model="queryParams.payConsultantId"  clearable filterable>
          <el-option
            v-for="item in consultList"
            :key="item.id"
            :label="item.nickName"
            :value="item.id"
          />
        </el-select>


      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.order_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>


      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--  订单清单  -->
    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="orderNo" width="130"/>
      <el-table-column label="服务类型" align="center" prop="serverType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_server_type" :value="scope.row.serverType"/>
        </template>
      </el-table-column>
      <el-table-column label="服务名称" align="center" prop="serverName"/>
      <el-table-column label="下单人" align="center" prop="payConsultantName"/>
      <el-table-column label="订单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="支付方式" align="center" prop="payType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_type" :value="scope.row.payType"/>
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="payStatus" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_status" :value="scope.row.payStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="订单原价" align="center" prop="payAmount" sortable=""/>
      <el-table-column label="付款时间" align="center" prop="payDatetime" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            @click="view(scope.row)"
          >查看详情</el-button>

<!--          <el-button
            size="mini"
            type="text"
            @click="del(scope.row)"
            v-hasPermi="['system:supervision-team:edit']"
          >删除</el-button>-->

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

    <info-form ref="infoForm" :consultList="consultList" @handleOk="getList"/>

  </div>
</template>

<script>
import {queryConsultantOrderList} from "@/api/order/consultantOrder";
import infoForm from "./info";
import {getConsultAll} from "@/api/psychology/consult";
import {serverType} from "@/utils/constants";

export default {
  components: {
    infoForm,
  },
  name: "team",
  dicts: ['order_server_type','order_status','pay_status','order_status','pay_type'],
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
      // 订单表格数据
      orderList: [],
      serverTypeList: this.$constants.serverType,
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
      // 督导师列表
      consultList: [],
    };
  },
  async created() {
    await this.getConsults();
    this.getList();
  },
  methods: {
    //获取督导师清单
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
      queryConsultantOrderList(this.queryParams).then(response => {
        this.orderList = response.rows;
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

    /** 查看督导 */
    view(row) {
      this.$refs.infoForm.init(row.orderNo)
    },

    /** 编辑督导 */
    edit(row) {
      this.$refs.editForm.init(row)
    },

    /** 删除督导 */
    /*del(row) {
      this.$modal.confirm('确认删除督导吗？').then(function() {
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
      this.$modal.confirm('确认删除督导吗？')
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
