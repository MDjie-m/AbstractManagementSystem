<template>
  <StoreContainer  @onStoreChanged="onStoreChanged">
    <template>
      <div class="container-div">
        <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true"  label-width="68px">
          <el-form-item label="球桌名"  prop="deskName">
            <el-input
              v-model="queryParams.deskName"
              placeholder="请输入球桌名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="编号" prop="deskNum">
            <el-input-number
              v-model="queryParams.deskNum"
              placeholder="请输入编号"
              controls-position="right" :max="9999"
              :clearable="true"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="球桌类型" prop="deskType">
            <el-select v-model="queryParams.deskType" placeholder="请选择球桌类型" clearable>
              <el-option
                v-for="dict in dict.type.store_desk_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="位置" prop="placeType">
            <el-select v-model="queryParams.placeType" placeholder="请选择位置" clearable>
              <el-option
                v-for="dict in dict.type.store_desk_place"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
              <el-option
                v-for="dict in dict.type.stoer_desk_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"  class="btn btn-primary btn-rounded btn-sm" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" class="btn btn-primary btn-rounded btn-sm" size="mini" @click="resetQuery">重置</el-button>
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
              v-hasPermi="['billiard:desk:add']"
            >新增</el-button>
          </el-col>

          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="deskList" @selection-change="handleSelectionChange">

          <el-table-column label="Id" align="center" prop="deskId" />
          <el-table-column label="球桌名" align="center" prop="deskName" />
          <el-table-column label="编号" align="center" prop="deskNum" />
          <el-table-column label="球桌类型" align="center" prop="deskType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.store_desk_type" :value="scope.row.deskType"/>
            </template>
          </el-table-column>
          <el-table-column label="位置" align="center" prop="placeType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.store_desk_place" :value="scope.row.placeType"/>
            </template>
          </el-table-column>
          <el-table-column label="门店" align="center" prop="storeName" />
          <el-table-column label="价格" align="center" prop="price" >
            <template slot-scope="scope">
              <span>{{scope.row.price}}元/分钟</span>
            </template>
          </el-table-column>

          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.stoer_desk_status" :value="scope.row.status"/>
            </template>
          </el-table-column>

          <el-table-column label="备注" align="center" prop="remark" />
          <el-table-column label="灯光设备" align="center" prop="lightName" />
          <el-table-column label="摄像头设备" align="center" prop="cameraName" />
          <el-table-column label="创建/更新" align="center" prop="updateTime" width="200">
            <template slot-scope="scope">
              <div>
                <span>{{scope.row.createBy}}&nbsp;</span>
                <span>{{ parseTime(scope.row.createTime ) }}</span>
              </div>
              <div>
                <span>{{scope.row.updateBy}} &nbsp;</span>
                <span>{{ parseTime(scope.row.updateTime ) }}</span>
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
                v-hasPermi="['billiard:desk:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:desk:remove']"
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
        </div>
        <!-- 添加或修改球桌对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="球桌名" prop="deskName">
                  <el-input v-model="form.deskName" placeholder="请输入球桌名"  maxlength="30"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="编号" prop="deskNum">
                  <el-input v-model="form.deskNum" placeholder="请输入编号" maxlength="3" />
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
                <el-form-item label="门店"  prop="storeId">
                  <el-tag>       {{ storeInfo?storeInfo.storeName:''}}</el-tag>

                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="价格" prop="price">
                  <el-input-number v-model="form.price"  controls-position="left" :precision="2" :step="0.01" :max="100" :min="0.01"  ></el-input-number>元/分钟
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
                  <el-select v-model="form.cameraDeviceId"   class="with100" >
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
                  <el-select v-model="form.lightDeviceId" type="textarea" placeholder="请输入内容" class="with100" >
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
            <el-col :span="12" >
              <el-form-item  label="状态" prop="status">
                <dict-tag :options="dict.type.stoer_desk_status" :value="form.status"/>
              </el-form-item>
            </el-col>
            <el-row>
              <el-col :span="24">
                <el-form-item label="备注" prop="remark">
                  <el-input v-model="form.remark" type="textarea"  class="with100" placeholder="请输入内容" maxlength="200"  />
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
  </StoreContainer>

</template>

<script>
import { listDesk, getDesk, delDesk, addDesk, updateDesk } from "@/api/billiard/desk";
import { listAllStore } from '@/api/billiard/store'
import StoreContainer from '@/views/billiard/component/storeContainer.vue'
import { listAllDevice } from '@/api/billiard/device'

export default {
  name: "Desk",
  components: { StoreContainer },
  dicts: ['stoer_desk_status', 'store_desk_type', 'store_desk_place'],
  data() {
    return {
      storeOptions:[],
      storeInfo:null,
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
      deviceList:[],
      lightList:[],
      cameraList:[],
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
          { required: true, message: "球桌名不能为空", trigger: "blur" }
        ],
        deskNum: [
          { required: true, message: "编号不能为空", trigger: "blur" },
          { validator:function(rule,value,callback){
              if(!/^[1-9]\d{0,2}$/.test(value) ){
                callback(new Error("只能输入三位数字"));
              }else{
                //校验通过
                callback();
              }
            }, trigger: 'blur'
          },
        ],
        deskType: [
          { required: true, message: "球桌类型不能为空", trigger: "change" }
        ],
        placeType: [
          { required: true, message: "位置不能为空", trigger: "change" }
        ],
        storeId: [
          { required: true, message: "门店不能为空", trigger: "change" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.queryStores();
  },
  methods: {
    onStoreChanged(store){
      this.storeInfo=store;
      this.queryParams.storeId=store?.storeId||-1;
      this.getList();
      this.getAllDevices();
    },
    queryStores(){
      return   listAllStore().then(response => {
        this.storeOptions = (response.data||[]).map(p=>{
          return Object.assign({label:p.storeName,value:p.storeId,raw:{listClass:'primary'}},p);
        });
      });
    },
    /** 查询球桌列表 */
    getList() {
      this.loading = true;
      listDesk(this.queryParams).then(response => {
        this.deskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getAllDevices() {
      listAllDevice({storeId: this.storeInfo?.storeId||-1}).then(response => {
        this.deviceList = (response.data||[]).map(p=>{
          p.label=`${p.deviceName}`;
          return p;
        });
        this.lightList=this.deviceList.filter(p=>p.deviceType===1);
        this.cameraList=this.deviceList.filter(p=>p.deviceType===0);
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if(!this.storeInfo?.storeId){
        return        this.$modal.msgWarning("请选择门店");
      }
      this.reset();
      this.getAllDevices();
      this.form.storeId=this.storeInfo?.storeId;
      this.form.status=0;
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
      this.$modal.confirm('是否确认删除球桌编号为"' + deskIds + '"的数据项？').then(function() {
        return delDesk(deskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
