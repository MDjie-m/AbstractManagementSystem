<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px"
        >
          <el-form-item label="设备名称" prop="deviceName">
            <el-input
              v-model="queryParams.deviceName"
              placeholder="请输入设备名称" maxlength="40"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="设备编码" prop="deviceSerialNum">
            <el-input
              v-model="queryParams.deviceSerialNum"
              placeholder="请输入设备编码" maxlength="40"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="设备类型" prop="deviceType">
            <el-select v-model="queryParams.deviceType" placeholder="请选择设备类型" clearable>
              <el-option
                v-for="dict in dict.type.store_device_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
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
              v-hasPermi="['billiard:device:add']"
            >新增
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
          <el-table-column label="设备id" align="center" prop="deviceId"/>
          <el-table-column label="门店" align="center" prop="storeName"/>
          <el-table-column label="设备名称" align="center" prop="deviceName"/>
          <el-table-column label="设备编码" align="center" prop="deviceSerialNum"/>
          <el-table-column label="设备类型" align="center" prop="deviceType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.store_device_type" :value="scope.row.deviceType"/>
            </template>
          </el-table-column>
          <el-table-column label="是否绑球桌" align="center">
            <template slot-scope="scope">
              <template v-if="scope.row.deskId">
                <el-tag type="success">是</el-tag>
              </template>
              <template v-else>
                <el-tag type="info">否</el-tag>
              </template>
            </template>
          </el-table-column>
          <el-table-column label="设备状态" align="center" prop="status">
            <template slot-scope="scope">
              <div style="display: flex;flex-direction: row;align-items: center;justify-content: center">
                <dict-tag :options="dict.type.store_device_status" :value="scope.row.status"/>
                <el-tooltip :content="scope.row.customStatus?'灯光已打开':'灯光已关闭'">
                  <svg-icon class-name="icon-light" v-if="scope.row.deviceType===1"
                            :icon-class="scope.row.customStatus?'light':'light_close'" style="margin-left: 10px"
                  />
                </el-tooltip>
              </div>


            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:device:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:device:remove']"
              >删除
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-switch-button"
                @click="handleSwitchLight(scope.row)"
                v-if="scope.row.deviceType===1"
                v-hasPermi="['billiard:device:edit'] "
              >{{ scope.row.customStatus ? '关灯' : '开灯' }}
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

      <!-- 添加或修改设备信息对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form class="custom-form" ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="门店" prop="storeId">
            <el-tag> {{ storeInfo ? storeInfo.storeName : '' }}</el-tag>
          </el-form-item>
          <el-form-item label="设备名称" prop="deviceName">
            <el-input v-model="form.deviceName" placeholder="请输入设备名称" maxlength="40"/>
          </el-form-item>
          <el-form-item label="设备编码" prop="deviceSerialNum">
            <el-input v-model="form.deviceSerialNum" placeholder="请输入设备编码" maxlength="40"/>
          </el-form-item>
          <el-form-item label="设备类型" prop="deviceType">
            <el-select v-model="form.deviceType" placeholder="请选择设备类型">
              <el-option
                v-for="dict in dict.type.store_device_type"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-divider v-if="form.deviceType===1">设备自定义设置</el-divider>
          <div v-if="form.deviceType===1">
            <el-form-item label="订阅主题" prop="subTopic">
              <el-input v-model="form.extendData.subTopic" placeholder="请输入订阅主题" maxlength="100"/>
            </el-form-item>
            <el-form-item label="发送主题" prop="pubTopic">
              <el-input v-model="form.extendData.pubTopic" placeholder="请输入发送主题" maxlength="100"/>
            </el-form-item>
          </div>
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
import { listDevice, getDevice, delDevice, addDevice, updateDevice, switchLight } from '@/api/billiard/device'
import StoreContainer from '@/views/billiard/component/storeContainer.vue'

export default {
  name: 'Device',
  components: { StoreContainer },
  dicts: ['store_device_type', 'store_device_status'],
  data() {
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
      // 设备信息表格数据
      deviceList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceName: null,
        deviceSerialNum: null,
        deviceType: null,
        status: null
      },
      // 表单参数
      form: {
        extendData: {
          subTopic: null,
          pubTopic: null
        }
      },

      storeInfo: null,
      // 表单校验
      rules: {
        storeId: [
          { required: true, message: '门店不能为空', trigger: 'blur' }
        ],
        deviceName: [
          { required: true, message: '设备名称不能为空', trigger: 'blur' }
        ],
        deviceType: [
          { required: true, message: '设备类型不能为空', trigger: 'change' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        updateTime: [
          { required: true, message: '更新时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {

  },
  methods: {
    onStoreChanged(store) {
      this.storeInfo = store
      this.queryParams.storeId = store?.storeId || -1
      this.getList()
    },

    /** 查询设备信息列表 */
    getList() {
      this.loading = true
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows
        this.total = response.total
        this.loading = false
      }).catch(p => {
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        deviceId: null,
        storeId: null,
        deviceName: null,
        deviceSerialNum: null,
        deviceType: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        extendData: {
          subTopic: null,
          pubTopic: null
        }
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if (!this.storeInfo?.storeId) {
        return this.$modal.msgWarning('请选择门店')
      }
      this.reset()
      this.open = true
      this.form.storeId = this.storeInfo?.storeId
      this.title = '添加设备信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const deviceId = row.deviceId || this.ids
      getDevice(deviceId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改设备信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.deviceId != null) {
            updateDevice(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addDevice(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleSwitchLight(row) {
      switchLight(row.deviceId, !row.customStatus)
        .then((res) => {
          this.loading=true;
          this.getList()
        }).catch(() => {
        this.loading = false
      })

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deviceIds = row.deviceId || this.ids
      this.$modal.confirm('是否确认删除设备信息编号为"' + deviceIds + '"的数据项？').then(function() {
        return delDevice(deviceIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/device/export', {
        ...this.queryParams
      }, `device_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
<style>
.icon-light {

  font-size: 1.5em;
}
</style>
