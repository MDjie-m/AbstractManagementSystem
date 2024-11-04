<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="90px">
      <el-form-item label="账单日期" prop="times">
        <el-date-picker
          v-model="queryParams.times"
          style="width: 280px;"
          size="small"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="warning" icon="el-icon-download" plain v-hasPermi="['psychology:bill:export']" size="mini" @click="handleExport">导出</el-button>
        <el-button icon="el-icon-upload" size="mini" @click="importFinishedExcel">导入已完成打款的清单</el-button>
            <input type="file" ref="fileInput" @change="handleFileChange" style="display: none;" />
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <span style="color: #C5291B;margin: 0 10px">*每月20号更新账单，如有未核销订单，请在20号之前完成核对，逾期将无法结算。</span>
      </el-col>
    </el-row>

    <el-table v-loading="loading" size="mini" style="width: 100%" :data="bills">
      <el-table-column label="账单日期" width="160" align="center" prop="billTime" />
      <el-table-column label="结算次数" width="100" align="center" prop="num" />
      <el-table-column label="订单笔数" width="260" align="center" prop="orderNum" />
      <el-table-column label="结算咨询师" width="160" align="center" prop="consultNum" />
      <el-table-column label="分期结算款项(元）" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{ scope.row.total ? scope.row.total.toFixed('2') : 0 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分期结算佣金(元）" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{ scope.row.pay ? scope.row.pay.toFixed('2') : 0 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="view(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleDetailExport(scope.row)"
            v-hasPermi="['psychology:bill:export']"
          >下载核销分账报表</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleDrawDetailExport(scope.row)"
            v-hasPermi="['psychology:bill:export']"
          >下载提现申请报表</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleRemark(scope.row)"
            v-hasPermi="['psychology:bill:edit']"
          >备注</el-button>
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

    <el-dialog title="账单备注" :visible.sync="openRm" width="700px" append-to-body>
      <el-form ref="formBillRm" :model="formRm" :rules="rulesRm" label-width="80px">
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" :rows="4" v-model="formRm.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRm">确 定</el-button>
        <el-button @click="cancelRm">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getConsultAll } from "@/api/psychology/consult";
import { listBill, updateBill } from "@/api/psychology/bill";
import { drawFinish } from "@/api/psychology/record";
import * as XLSX from "xlsx";

export default {
  name: "PysBill",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 咨询师合同协议表格数据
      bills: [],
      consultId: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        consultId: null,
        orderNo: null,
        startTime: null,
        endTime: null,
        times: []
      },
      // 备注
      openRm: false,
      formRm: {},
      rulesRm: {
        remark: [
          { required: true, message: "备注不能为空", trigger: "blur" }
        ],
      },
    };
  },
  async created() {
    await this.getConsults()
    this.getList();
  },
  methods: {
    async getConsults() {
      const res = await getConsultAll()
      this.consultList = res.data
      if (this.consultList.length === 1) {
        this.consultId = this.consultList[0].id
        this.queryParams.consultId = this.consultId
      }
    },
    /** 查询咨询师合同协议列表 */
    getList() {
      this.loading = true;
      if (this.queryParams.times.length > 0) {
        this.queryParams.startTime = this.queryParams.times[0]
        this.queryParams.endTime = this.queryParams.times[1]
      } else {
        this.queryParams.startTime = null
        this.queryParams.endTime = null
      }

      if (this.consultId) {
        this.queryParams.consultId = this.consultId
      }
      listBill(this.queryParams).then(response => {
        this.bills = response.rows;
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    //已完成打款的清单导入
    importFinishedExcel() {
      this.$refs.fileInput.click();
    },
    handleFileChange(event) {
      const file = event.target.files[0];
      if (!file) { return; }
      const fileName = file.name.toLowerCase();
      if (!fileName.endsWith('.xlsx') && !fileName.endsWith('.xls')) {
        this.$modal.msgError('请上传一个Excel文件（.xlsx 或 .xls）');
        return;
      }

      const reader = new FileReader();
      reader.onload = (e) => {
        const data = new Uint8Array(e.target.result);
        const workbook = XLSX.read(data, { type: 'array' });

        const firstSheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[firstSheetName];

        const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

        if (jsonData.length > 0) {
          const firstColumnData = jsonData.slice(1).map(row => row[6]);
          const joinedData = firstColumnData.join(',');
          //todo通知  打款完成
          drawFinish({recordIds:joinedData}).then(response => {
            console.log(response);
            this.$modal.msgSuccess("处理成功")
          });
        } else {
          this.$modal.msgError('Excel 表格为空或格式不正确');
        }
      };
      reader.readAsArrayBuffer(file);
    },
    /** 导出按钮操作 */
    handleExport() {
      if (this.consultId) {
        this.queryParams.consultId = this.consultId
      }

      this.download('psychology/bill/export', {
        ...this.queryParams
      }, `心理咨询账单_${new Date().getTime()}.xlsx`)
    },
    /** 下载报表 */
    handleDetailExport(row) {
      const queryParams = {
        billId: row.id,
        consultId: this.consultId
      }

      this.download('psychology/bill/exportItemsForDetail', {
        ...queryParams
      }, `心理咨询${row.billTime}账单_${new Date().getTime()}.xlsx`)
    },

    //下载提现申请报表
    handleDrawDetailExport(row){
      const billDate = new Date(row.billTime);
      const createTimeStart = new Date(billDate);
      createTimeStart.setMonth(createTimeStart.getMonth() - 1);
      createTimeStart.setDate(21);

      const queryParams = {
        payType : 1,//提现申请记录
        createTimeStart: createTimeStart.toISOString().split('T')[0],
        createTimeEnd: row.billTime
      }

      this.download('/finance/record/drawExport', {
        ...queryParams
      }, `咨询师${row.billTime}提现申请报表_${new Date().getTime()}.xlsx`)
    },
    view(row) {
      this.$router.push({ path: '/bill/billDetail', query: { id: row.id }})
    },
    /** 备注操作 */
    handleRemark(row) {
      this.formRm = JSON.parse(JSON.stringify(row))
      this.openRm = true
    },
    // 备注
    cancelRm() {
      this.openRm = false
      this.$refs["formBillRm"].resetFields()
    },
    submitRm() {
      this.$refs["formBillRm"].validate(valid => {
        if (valid) {
          if (this.formRm.id != null) {
            updateBill(this.formRm).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.cancelRm()
              this.getList();
            });
          }
        }
      });
    },
  },
}
</script>

<style scoped>

</style>
