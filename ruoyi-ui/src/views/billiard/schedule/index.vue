<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="排班时间">
            <el-date-picker
              v-model="daterangeDay"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

      </div>
      <div class="col-sm-12 select-table table-striped">
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['billiard:schedule:add']"
            >新增
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange">
          <el-table-column label="ID" align="center" prop="storeScheduleId"/>
          <el-table-column label="门店" align="center" prop="storeName"/>
          <el-table-column label="开始时间" align="center" prop="startTimeStr" width="360"/>
          <el-table-column label="结束时间" align="center" prop="endTimeStr" width="360"/>
          <!--        <el-table-column label="-1,0,+1" align="center" prop="startTimeOffsetDay" />-->
          <!--        <el-table-column label="-1,0,+1" align="center" prop="endTimeOffsetDay" />-->
          <el-table-column label="具体排班日期" align="center" prop="day" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.day, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="创建/更新" align="center" prop="createById" width="360">>
            <template slot-scope="scope">
              <div>
                <span>{{ scope.row.createBy }} </span>
                <span>{{ scope.row.createTime }}</span>
              </div>
              <div>
                <span>{{ scope.row.updateBy }} </span>
                <span>{{ scope.row.updateTime }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="备注" align="center" prop="remark"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:schedule:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:schedule:remove']"
              >删除
              </el-button>
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
      </div>

      <!-- 添加或修改门店班次对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="门店" prop="storeId">
                <el-tag> {{ storeInfo ? storeInfo.storeName : '' }}</el-tag>
              </el-form-item>
            </el-col>
            <!--            <el-col :span="12">-->
            <!--              <el-form-item label="手机号" prop="mobile">-->
            <!--                <el-input type="tel" maxlength="11" v-model="form.mobile" placeholder="请输入手机号"/>-->
            <!--              </el-form-item>-->
            <!--            </el-col>-->
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="开始时间" prop="startTime">
                <el-select v-model="form.startTimeOffsetDay" placeholder="请选择" class="form-offset mr10">
                  <el-option
                    v-for="dict in dict.type.store_schedule_offset_day"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
                <el-time-picker
                  v-model="form.startTime"
                  clearable
                  editable
                  :picker-options="{
                      format: 'HH:mm'
                    }"
                  value-format="HH:mm"
                  placeholder="请选择开始时间">
                </el-time-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="结束时间" prop="endTime">
                <div class="d-flex">
                  <el-select v-model="form.endTimeOffsetDay" placeholder="请选择" class="form-offset mr10">
                    <el-option
                      v-for="dict in dict.type.store_schedule_offset_day"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    />
                  </el-select>
                  <el-time-picker
                    v-model="form.endTime"
                    clearable
                    editable
                    :picker-options="{
                      format: 'HH:mm'
                    }"
                    value-format="HH:mm"
                    placeholder="请选择结束时间">
                  </el-time-picker>
                </div>

              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="排班日期" prop="day">
                <el-date-picker clearable
                                v-model="form.day"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择排班日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">

            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="备注" prop="remark">
                <el-input v-model="form.remark" type="textarea" class="with100" placeholder="请输入内容"
                          maxlength="200"/>
              </el-form-item>
            </el-col>
          </el-row>

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
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </StoreContainer>
</template>

<script>
import {listSchedule, getSchedule, delSchedule, addSchedule, updateSchedule} from "@/api/billiard/schedule";
import StoreContainer from "@/views/billiard/component/storeContainer.vue";

export default {
  name: "Schedule",
  components: {StoreContainer},
  dicts: ['store_schedule_offset_day'],
  data() {
    return {
      storeInfo: null,
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
      // 门店班次表格数据
      scheduleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeDay: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeId: null,
        day: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeId: [
          {required: true, message: "门店不能为空", trigger: "blur"}
        ],
        startTime: [
          {required: true, message: "开始时间不能为空", trigger: "blur"}
        ],
        endTime: [
          {required: true, message: "结束时间不能为空", trigger: "blur"}
        ],
        startTimeOffsetDay: [
          {required: true, message: "-1,0,+1不能为空", trigger: "blur"}
        ],
        day: [
          {required: true, message: "排班日期不能为空", trigger: "blur"}
        ],
      },
      offsetDayOptions: [
        {label: "当天", value: 0},
        {label: "前一天", value: -1},
        {label: "后一天", value: 1},
      ],
    };
  },
  created() {
  },
  methods: {

    onStoreChanged(store) {
      this.storeInfo = store;
      this.queryParams.storeId = store?.storeId || -1;
      this.form.storeId = store?.storeId || -1;
      this.initData()
    },
    initData() {
      this.getList();
    },
    /** 查询门店班次列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeDay && '' != this.daterangeDay) {
        this.queryParams.params["beginDay"] = this.daterangeDay[0];
        this.queryParams.params["endDay"] = this.daterangeDay[1];
      }
      listSchedule(this.queryParams).then(response => {
        this.scheduleList = response.rows;
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
      this.form = {
        storeScheduleId: null,
        storeId: this.storeInfo?.storeId || -1,
        startTime: null,
        endTime: null,
        startTimeOffsetDay: null,
        endTimeOffsetDay: null,
        day: null,
        remark: null
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
      this.daterangeDay = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.storeScheduleId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门店班次";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const storeScheduleId = row.storeScheduleId || this.ids
      getSchedule(storeScheduleId).then(response => {
        this.form = response.data;
        this.form.startTimeOffsetDay = this.form.startTimeOffsetDay + '';
        this.form.endTimeOffsetDay = this.form.endTimeOffsetDay + '';
        this.open = true;
        this.title = "修改门店班次";
      });
    },
    /** 提交按钮 */
    submitForm() {

      console.log(this.form)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.storeScheduleId != null) {
            updateSchedule(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSchedule(this.form).then(response => {
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
      const storeScheduleIds = row.storeScheduleId || this.ids;
      this.$modal.confirm('是否确认删除门店班次编号为"' + storeScheduleIds + '"的数据项？').then(function () {
        return delSchedule(storeScheduleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/schedule/export', {
        ...this.queryParams
      }, `schedule_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style lang="scss" scoped src="./index.scss"></style>
