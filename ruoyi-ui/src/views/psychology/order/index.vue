<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in orderStatus"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务名称" prop="serveName">
        <el-input
          v-model="queryParams.serveName"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下单时间" prop="dateLimit">
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
      <el-form-item label="所属咨询师" prop="consultId">
        <el-select v-model="queryParams.consultId" clearable filterable :disabled="consultList.length === 1">
          <el-option
            v-for="item in consultList"
            :key="item.id"
            :label="item.nickName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="pay">
        <el-select v-model="queryParams.pay" placeholder="请选择支付状态" clearable>
          <el-option
            v-for="dict in payStatus"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="primary" icon="el-icon-plus" v-hasPermi="['psychology:order:admin']" size="mini" @click="openAdd">创建订单</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" size="mini" style="width: 100%" :data="orderList" @selection-change="handleSelectionChange">
<!--      <el-table-column fixed type="selection" width="55" align="center" />-->
      <el-table-column fixed label="订单编号" width="180" align="center" prop="orderNo" />
      <el-table-column label="服务名称" width="150" align="center" prop="serveName" />
      <el-table-column label="咨询师" width="100" align="center" prop="consultName" />
      <el-table-column label="用户名称" width="100" align="center" prop="nickName" />
      <el-table-column label="用户手机号" width="120" align="center" prop="phone" />
      <el-table-column label="咨询类型" width="120" align="center" prop="consultTypeName" />
      <el-table-column label="订单原价" width="100" align="center" prop="originalPrice">
        <template slot-scope="scope">
          {{ scope.row.originalPrice.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="订单实付" width="100" align="center" prop="pay">
        <template slot-scope="scope">
          {{ scope.row.pay.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="优惠券编号" width="200" align="center" prop="couponNo" />
      <el-table-column label="优惠券名" width="100" align="center" prop="couponName" />
      <el-table-column label="服务次数" width="100" align="center" prop="num">
        <template slot="header" slot-scope="{ column, $index }">
          <span>服务次数</span>
          <el-tooltip class="item" effect="dark" content="剩余次数/用户购买次数" placement="top-start">
            <i class="el-icon-question"/>
          </el-tooltip>
        </template>
        <template slot-scope="scope">
          {{ scope.row.num }} / {{ scope.row.num + scope.row.buyNum }}
        </template>
      </el-table-column>
      <el-table-column label="下次预约时间" width="180" align="center" prop="orderTime" />
      <el-table-column label="支付状态" width="100" align="center" prop="payStatusName" />
      <el-table-column label="订单状态" width="100" align="center" prop="statusName">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '3' || scope.row.status === '4'" type="info">{{ scope.row.statusName }}</el-tag>
          <el-tag v-else-if="scope.row.status === '2'" type="success">{{ scope.row.statusName }}</el-tag>
          <el-tag v-else>{{ scope.row.statusName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="下单时间" align="center" prop="createTime" width="180"/>
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width" width="120">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleDetail(scope.row.id)"
            v-hasPermi="['psychology:order:edit']"
          >详情</el-button>
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right" />
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleHx(scope.row.id)" v-if="checkPermi(['psychology:order:edit']) && hasHx(scope.row)">核销次数</el-dropdown-item>
              <el-dropdown-item :disabled="['0', '3'].includes(scope.row.status)" @click.native="handleRemark(scope.row)" v-if="checkPermi(['psychology:order:edit'])">备注</el-dropdown-item>
              <el-dropdown-item :disabled="scope.row.status !== '0'" @click.native="handlePrice(scope.row.id)" v-if="checkPermi(['psychology:order:price'])">改价</el-dropdown-item>
              <el-dropdown-item :disabled="scope.row.status !== '1'" @click.native="handleRefer(scope.row)" v-if="checkPermi(['psychology:order:referral'])">转介</el-dropdown-item>
              <el-dropdown-item :disabled="scope.row.status !== '1'" @click.native="handleClose(scope.row.id)" v-if="checkPermi(['psychology:order:remove'])">关闭</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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

    <el-dialog :title="title" :visible.sync="openRm" width="700px" append-to-body>
      <el-form ref="formRm" :model="formRm" :rules="rulesRm" label-width="80px">
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" :rows="4" v-model="formRm.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRm">确 定</el-button>
        <el-button @click="cancelRm">关 闭</el-button>
      </div>
    </el-dialog>

    <times ref="formHx" @handleOk="handleOk" />

    <price ref="formPrice" @handleOk="handleOk" />

    <referral ref="formRefer" @handleOk="handleOk" />

    <close-modal ref="formClose" @handleOk="handleOk" />

    <el-dialog title="创建订单" :visible.sync="openAd" width="700px" append-to-body>
      <el-form ref="formAd" :model="formAd" :rules="rulesAd" label-width="120px">
        <el-form-item label="订单渠道" prop="channel">
          <el-select v-model="formAd.channel" placeholder="请选择渠道">
            <el-option
              v-for="dict in dict.type.consult_order_channel"
              :key="dict.value"
              :label="dict.label"
              :value="dict.label"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="支付状态" prop="status">
          <el-radio-group v-model="formAd.status">
            <el-radio label="0">未支付</el-radio>
            <el-radio label="1">支付成功</el-radio>
          </el-radio-group>
          <div v-if="formAd.status === '0'" style="color:#BEBEBE;font-size: 12px;">如选择未支付，请创建订单后提醒用户在24小时内完成支付</div>
        </el-form-item>

        <el-form-item label="订单实付" prop="pay" v-if="formAd.status === '1'">
          <el-input-number size="mini" v-model="formAd.pay" :min="0" placeholder="请输入订单实付金额" />
        </el-form-item>

        <el-form-item label="下单用户" prop="userId">
          <el-select v-model="formAd.userId" clearable filterable>
            <el-option
              v-for="item in userList"
              :key="item.id"
              :label="item.id + '-' + item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="咨询师" prop="consultId">
          <el-select v-model="formAd.consultId" clearable filterable @change="getServeList">
            <el-option
              v-for="item in consults"
              :key="item.id"
              :label="item.nickName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="服务名" prop="serveId">
          <el-select v-model="formAd.serveId" clearable filterable @change="changeServe">
            <el-option
              v-for="item in serveList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="服务次数" prop="num">
          <el-input-number size="mini" disabled v-model="formAd.num" placeholder="选择服务后显示" />
        </el-form-item>

<!--        <div style="color:#F56C6C;margin-left: 120px">后台订单创建成功后请提醒用户在24小时内完成支付</div>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="add">确 定</el-button>
        <el-button @click="closeAdd">关 闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listOrder, getOrder, addOrder, doRemark, getInfo } from "@/api/psychology/order";
import { getServeRef, listServeConfig } from "@/api/psychology/serveConfig";
import { listUser } from "@/api/psychology/user";
import { getConsultAll } from "@/api/psychology/consult";
import times from "./times";
import price from "./price";
import referral from "./referral";
import closeModal from "./closeModal";
import { checkPermi } from "@/utils/permission";
import {mapState} from "vuex"; // 权限判断函数

export default {
  name: "Order",
  dicts: ['consult_order_channel'],
  components: { times, price, referral, closeModal },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      order: {},
      items: {},
      orderId: '',
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 咨询订单表格数据
      payStatus: this.$constants.payStatus,
      orderStatus: this.$constants.orderStatus,
      consultType: this.$constants.consultType,
      timeVal: [],
      orderList: [],
      consultList: [],
      consults: [],
      serveList: [],
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openRm: false,
      openAd: false,
      consultId: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        nickName: null,
        consultId: null,
        serveName: null,
        dateLimit: null,
        status: null,
        pay: null,
      },
      // 表单参数
      form: {},
      formRm: {},
      formAd: {
        channel: null,
        status: null,
        pay: null,
        userId: null,
        nickName: null,
        serveId: null,
        consultId: null,
        num: null,
      },
      rulesRm: {
        remark: [
          { required: true, message: "备注不能为空", trigger: "blur" }
        ],
      },
      rulesAd: {
        channel: [ { required: true, message: "请选择订单渠道", trigger: "change" } ],
        status: [ { required: true, message: "请选择支付状态", trigger: "change" } ],
        userId: [ { required: true, message: "请选择用户", trigger: "change" } ],
        serveId: [ { required: true, message: "请选择服务", trigger: "change" } ],
        consultId: [ { required: true, message: "请选择咨询师", trigger: "change" } ],
      }
    };
  },
  async created() {
    await this.getConsultServeRef();
    this.getList();
    console.log(this.userInfo)
  },
  computed: {
    ...mapState({
      userInfo: (state) => state.user,
    })
  },
  methods: {
    checkPermi,
    hasHx(order) {
      if (this.userInfo.name !== 'admin' && this.userInfo.nickName !== order.consultName) {
        return false
      }

      return order.num !== 0 && ['1', '2'].includes(order.status)
    },
    onchangeTime (e) {
      this.timeVal = e;
      this.queryParams.dateLimit = e ? this.timeVal.join(',') : ''
      this.queryParams.pageNum = 1
      // this.getList();
    },
    async getConsultServeRef() {
      const res = await getConsultAll()
      this.consultList = res.data
      if (this.consultList.length === 1) {
        this.consultId = this.consultList[0].id
        this.queryParams.consultId = this.consultId
      }
      this.consults = this.consultList.filter(a => a.status === '0')
    },
    /** 查询咨询订单列表 */
    getList() {
      this.loading = true;

      if (this.consultId) {
        this.queryParams.consultId = this.consultId
      }
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
          orderNo: null,
          nickName: null,
          consultId: this.consultId,
          serveName: null,
          dateLimit: null,
          status: null,
          pay: null,
      }

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
    handleDetail(id) {
      this.$router.push({ path: '/psychology/psyOrderDetail', query: { id: id }})
    },
    /** 修改按钮操作 */
    handlePrice(id) {
      getOrder(id).then(response => {
        if (response.data) {
          this.$refs.formPrice.setForm(response.data)
        }
      })
    },
    /** 修改按钮操作 */
    handleHx(id) {
      getOrder(id).then(response => {
        if (response.data) {
          this.$refs.formHx.setForm(response.data)
        }
      })
    },
    /** 转介 */
    handleRefer(data) {
      getServeRef({ serveId: data.serveId }).then(response => {
        if (response.data) {
          this.$refs.formRefer.setForm(data, response.data.filter(a => a.status === '0'))
        }
      })
    },
    /** 关闭订单 */
    handleClose(id) {
      getOrder(id).then(response => {
        if (response.data) {
          this.$refs.formClose.setForm(response.data)
        }
      })
    },
    handleOk() {
      this.getList()
    },
    /** 创建订单 start */
    openAdd() {
      if (this.userList.length === 0) {
        this.getUserList()
      }

      this.openAd = true
    },
    add() {
      const that = this
      that.$refs["formAd"].validate(valid => {
        if (that.formAd.status === '1' && that.formAd.pay === 0) {
          return  that.$modal.msgError("请输入支付金额");
        }

        if (that.formAd.status === '0') {
          that.formAd.pay = null
        }

        if (valid) {
          that.formAd.nickName = that.userList.find(a => a.id === that.formAd.userId).name

          that.$modal.confirm('你确定创建订单吗？').then(function() {
            addOrder(that.formAd).then(response => {
              that.$modal.msgSuccess("创建成功");
              that.closeAdd();
              that.getList();
            });
          }).then(() => {
            that.cancel()
          }).catch(() => {});
        }
      });
    },
    closeAdd() {
      this.openAd = false
      this.formAd = {
        channel: null,
        status: null,
        pay: null,
        num: null,
        nickName: null,
        userId: null,
        serveId: null,
        consultId: null
      }
      this.serveList = []
    },
    getUserList() {
      const queryParams = {
        pageNum: 1,
        pageSize: 9999,
      }
      listUser(queryParams).then(response => {
        this.userList = response.rows
      })
    },
    getServeList(cId) {
      this.serveList = []
      this.formAd.num = null
      this.formAd.serveId = null
      const queryParams = {
        pageNum: 1,
        pageSize: 99,
        status: '0',
        orderByColumn: 'z_index',
        isAsc: 'descending',
        cId: cId
      }
      listServeConfig(queryParams).then(response => {
        this.serveList = response.rows
      })
    },
    changeServe(serveId) {
      this.formAd.num = this.serveList.find(a => a.id === serveId).num
    },
    /** 创建订单 end */
    cancelRm() {
      this.openRm = false
      this.formRm = {}
    },
    submitRm() {
      this.$refs["formRm"].validate(valid => {
        if (valid) {
          if (this.formRm.id != null) {
            doRemark(this.formRm).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openRm = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 备注操作 */
    handleRemark(row) {
      const id = row.id
      getInfo(id).then(response => {
        this.formRm = response.data;
        this.openRm = true;
        this.title = "修改咨询订单";
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      if (this.consultId) {
        this.queryParams.consultId = this.consultId
      }

      this.download('psychology/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
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
