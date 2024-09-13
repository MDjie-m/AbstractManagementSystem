<template>


  <div class="page-container">
    <div class="left-panel">
      <div class="  section-container menu-container">
        SDFSDF
      </div>
      <div class="  section-container menu-container">
        SDFSDF
      </div>
      <div class="  section-container menu-container">
        SDFSDF
      </div>
    </div>
    <div class="right-panel">
      <div class="  section-container">
        <div>
          <el-row>

              <el-tag
                type="primary"
                @click="onChooseAll"
                :effect="queryParams.deskType===null &&queryParams.placeType===null?'dark':'plain'"
              >
                全部
              </el-tag>

              <el-tag v-for="dict in dict.type.store_desk_type"
                      :key="dict.value+'deskType'"
                      :label="dict.label"
                      type="primary"
                      @click="onChooseClick('deskType',dict.value)"
                      :effect="parseInt(dict.value )===queryParams.deskType?'dark':'plain'"
                      round>
                {{ dict.label }}
              </el-tag>
              <el-tag v-for="dict in dict.type.store_desk_place"
                      :key="dict.value+'deskPlace'"
                      :label="dict.label"
                      :effect="parseInt(dict.value)===queryParams.placeType?'dark':'plain'"
                      @click="onChooseClick('placeType',dict.value)"
                      type="primary"
              >
                {{ dict.label }}
              </el-tag>

          </el-row>

          <el-row style="margin-top: 20px; ">
            <el-tag
              type="primary"
              @click="onChooseAllStatus"
              :effect="queryParams.status===null ?'dark':'plain'"
            >
              全部
            </el-tag>
            <el-tag v-for="dict in dict.type.stoer_desk_status"
                    :key="dict.value+'status'"
                    :label="dict.label"
                    type="primary"
                    @click="onChooseClick('status',dict.value)"
                    :effect="parseInt(dict.value )===queryParams.status?'dark':'plain'"
                    round>
              {{ dict.label }}
            </el-tag>

          </el-row>
        </div>
      </div>

      <div class="  section-container desk-box" v-loading="loading">
        <el-scrollbar>
          <template class="box-card" v-for="placeItem in dict.type.store_desk_place">
            <el-divider content-position="left" :key="'typeDesk'+placeItem.value">{{ placeItem.label }}</el-divider>
            <div class="desk-container">
              <el-card @click.native="onDeskClick(desk)" class="desk-item" :class="{'selected':desk.selected}"
                       v-for="desk in deskList.filter(p=>  p.placeType === parseInt(placeItem.value))">
                <div>
                  {{ desk.selected }}---
                  <div class="desk-item-name"> {{ desk.deskName }}</div>
                  <div class="desk-item-num"> {{ desk.deskNum }}</div>
                  <div class="desk-item-price"> {{ desk.price }}元/分钟</div>
                </div>


              </el-card>
            </div>
          </template>
        </el-scrollbar>
      </div>
    </div>

    <!-- 添加或修改球桌对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="球桌名" prop="deskName">
              <el-input v-model="form.deskName" placeholder="请输入球桌名" maxlength="30"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="编号" prop="deskNum">
              <el-input v-model="form.deskNum" placeholder="请输入编号" maxlength="3"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="球桌类型" prop="deskType">
              <el-select v-model="form.deskType" placeholder="请选择球桌类型" class="with100">
                <el-option
                  v-for="dict in dict.type.store_desk_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="位置" prop="placeType">
              <el-select v-model="form.placeType" placeholder="请选择位置" class="with100">
                <el-option
                  v-for="dict in dict.type.store_desk_place"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="门店" prop="storeId">
              <el-tag> {{ storeInfo ? storeInfo.storeName : '' }}</el-tag>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="价格" prop="price">
              <el-input-number v-model="form.price" controls-position="left" :precision="2" :step="0.01" :max="100"
                               :min="0.01"></el-input-number>
              元/分钟
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">

          </el-col>

        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="摄像头" prop="cameraDeviceId">
              <el-select v-model="form.cameraDeviceId" class="with100">
                <el-option
                  v-for="dict in cameraList"
                  :key="dict.deviceId"
                  :disabled="dict.deskId&&  dict.deskId!==form.deskId"
                  :label=" dict.deskId&& dict.deskId!==form.deskId?`${dict.deviceName}(已绑定)`:dict.deviceName"
                  :value="dict.deviceId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="灯光" prop="cameraDeviceId">
              <el-select v-model="form.lightDeviceId" type="textarea" placeholder="请输入内容" class="with100">
                <el-option
                  v-for="dict in lightList"
                  :key="dict.deviceId"
                  :disabled="dict.deskId&&  dict.deskId!==form.deskId"
                  :label="dict.deskId&&  dict.deskId!==form.deskId?`${dict.deviceName}(已绑定)`:dict.deviceName"
                  :value="dict.deviceId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <dict-tag :options="dict.type.stoer_desk_status" :value="form.status"/>
          </el-form-item>
        </el-col>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" class="with100" placeholder="请输入内容" maxlength="200"/>
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>


import {listDesk} from "@/api/cashier/desk";

export default {
  name: "Desk",
  dicts: ['stoer_desk_status', 'store_desk_type', 'store_desk_place'],
  data() {
    return {
      currentDesk: null,
      storeOptions: [],
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
      // 球桌表格数据
      deskList: [],

      originalDeskList: [],
      deviceList: [],
      lightList: [],
      cameraList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deskName: null,
        deskNum: undefined,
        deskType: null,
        placeType: null,
        storeId: null,
        price: null,
        status: null,
        lightDeviceId: null,
        cameraDeviceId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deskName: [
          {required: true, message: "球桌名不能为空", trigger: "blur"}
        ],
        deskNum: [
          {required: true, message: "编号不能为空", trigger: "blur"},
          {
            validator: function (rule, value, callback) {
              if (!/^[1-9]\d{0,2}$/.test(value)) {
                callback(new Error("只能输入三位数字"));
              } else {
                //校验通过
                callback();
              }
            }, trigger: 'blur'
          },
        ],
        deskType: [
          {required: true, message: "球桌类型不能为空", trigger: "change"}
        ],
        placeType: [
          {required: true, message: "位置不能为空", trigger: "change"}
        ],
        storeId: [
          {required: true, message: "门店不能为空", trigger: "change"}
        ],
        price: [
          {required: true, message: "价格不能为空", trigger: "blur"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    onDeskClick(item) {
      this.deskList.forEach(p => {
        if (p === item) {
          return;
        }
        p.selected = false;
      })
      item.selected = !item.selected
      this.currentDesk = item.selected ? item : null;
    },
    onChooseAll() {
      this.queryParams.placeType = null;
      this.queryParams.deskType = null;
      this.getList();
    },
    onChooseAllStatus() {
      this.queryParams.status = null;
      this.getList();
    },
    onChooseClick(field, val) {
      if (this.queryParams[field] === parseInt(val)) {
        this.queryParams[field] = null;
      } else {
        this.queryParams[field] = parseInt(val);
      }

      this.getList()

    },
    filterDeskList() {
      this.deskList = this.originalDeskList.filter(p => {
        let statusCondition = true;
        let deskTypeCondition = true;
        let placeCondition = true;
        if (this.queryParams.status !== null) {
          statusCondition = p.status === parseInt(this.queryParams.status)
        }
        if (this.queryParams.deskType !== null) {
          deskTypeCondition = p.deskType === parseInt(this.queryParams.deskType)
        }
        if (this.queryParams.placeType !== null) {
          placeCondition = p.placeType === parseInt(this.queryParams.placeType)
        }
        return statusCondition && placeCondition && deskTypeCondition
      });
      console.log(this.deskList, 'ddd')
      return this.deskList;
    },
    /** 查询球桌列表 */
    getList() {
      this.loading = true;
      listDesk({}).then(response => {
        this.originalDeskList = (response.data || []).map(p => {
          p.selected = false;
          return p;
        });
        this.filterDeskList()
        this.loading = false;
      }).finally(() => this.loading = false);
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deskId: null,
        deskName: null,
        deskNum: null,
        deskType: null,
        placeType: null,
        storeId: null,
        price: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        lightDeviceId: null,
        cameraDeviceId: null
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
      this.ids = selection.map(item => item.deskId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if (!this.storeInfo?.storeId) {
        return this.$modal.msgWarning("请选择门店");
      }
      this.reset();
      this.getAllDevices();
      this.form.storeId = this.storeInfo?.storeId;
      this.form.status = 0;
      this.open = true;
      this.title = "添加球桌";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getAllDevices();
      const deskId = row.deskId || this.ids
      getDesk(deskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改球桌";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deskId != null) {
            updateDesk(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDesk(this.form).then(response => {
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
      const deskIds = row.deskId || this.ids;
      this.$modal.confirm('是否确认删除球桌编号为"' + deskIds + '"的数据项？').then(function () {
        return delDesk(deskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/desk/export', {
        ...this.queryParams
      }, `desk_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style scoped lang="scss">
@import '@/assets/styles/element-variables.scss';

.el-tag {
  margin-right: 10px;
  margin-bottom: 10px;
}
.left-panel{
  padding: 10px;
}
.menu-container{
  padding: 10px;
}

.desk-box {
  flex: 1;
  overflow-y: auto;
}

.desk-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;


}

.desk-item {
  display: flex;
  width: 110px;
  min-height: 130px;
  margin: 10px;
  font-size: 14px;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: rgba(0, 0, 0, 0.8);
  border-radius: 10px;

  &:hover, &.selected {
    background-color: $--color-primary;
    border: 1px solid $--color-primary;
    color: #FFFFFF;
    cursor: pointer;
  }

  div + div {
    margin-top: 10px;
  }

  &-name {
    font-weight: bold;
    font-size: 15px;
  }

  &-num {
    font-weight: bold;
    font-size: 15px;
  }

  &-price {
    font-size: 12px;
  }
}

.el-tag {
  border-radius: 20px;
  padding: 0px 10px;
  min-width: 80px;
  text-align: center;
  letter-spacing: 5px;
  cursor: pointer;
  font-size: 14px;
  font-weight: bold;
}
</style>
