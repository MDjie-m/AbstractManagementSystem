<template>
  <StoreContainer  @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="realName">
        <el-input
          v-model="queryParams.realName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="教练等级" prop="level">

        <el-select v-model="queryParams.level" placeholder="请选择助教等级"   clearable  >
          <el-option
            v-for="dict in dict.type.store_tutor"
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
          v-hasPermi="['billiard:tutor:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tutorList" @selection-change="handleSelectionChange">
      <el-table-column label="ID" align="center" prop="storeTutorId" />
      <el-table-column label="姓名" align="center" prop="realName" />
      <el-table-column label="头像" align="center" prop="userImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.userImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" prop="mobile">
        <template slot-scope="scope">
          <div> {{scope.row.mobile}} <span v-if="scope.row.mobile!==scope.row.account">({{scope.row.account}})</span></div>
        </template>
      </el-table-column>
      <el-table-column label="教练等级" align="center" prop="level">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.store_tutor" :value="scope.row.level"/>
        </template>
      </el-table-column>
      <el-table-column label=门店" align="center" prop="storeName" />
      <el-table-column label="角色" align="center" prop="roleIds"  >
        <template slot-scope="scope">
          <dict-tag :options="roleOptions" :value="scope.row.roleIds"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.store_user_status" :value="scope.row.status"/>
        </template>
      </el-table-column>

      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['billiard:tutor:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-key"
            @click="handleResetPwd(scope.row)"
            v-hasPermi="['billiard:tutor:edit']"
          >重置密码</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['billiard:tutor:remove']"
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
    <!-- 添加或修改门店助教对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row >
          <el-col :span="12">
            <el-form-item label="姓名" prop="realName">
              <el-input v-model="form.realName" placeholder="请输入姓名"  maxlength="20"/>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="手机号" prop="mobile">
              <el-input v-model="form.mobile" placeholder="请输入手机号"  maxlength="11"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row >
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择性别"  style="width: 100%">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="教练等级" prop="level">
              <el-select v-model="form.level" placeholder="请选择助教等级"    style="width: 100%">
                <el-option
                  v-for="dict in dict.type.store_tutor"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row >
          <el-col :span="12">
            <el-form-item label="门店"  prop="storeId">
              <el-tag>       {{ storeInfo?storeInfo.storeName:''}}</el-tag>

            </el-form-item>
          </el-col>

          <el-col :span="12">

            <el-form-item label="教练状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择教练状态"  style="width: 100%">
                <el-option
                  v-for="dict in dict.type.store_user_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row >
          <el-col :span="24">
            <el-form-item label="角色" prop="roleIds">
              <el-select v-model="form.roleIds" multiple placeholder="请选择角色" style="width: 100%">
                <el-option
                  v-for="item in roleOptions"
                  :key="item.roleId"
                  :label="item.roleName"
                  :value="item.roleId"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">

          </el-col>
        </el-row>


        <el-row >
          <el-col :span="24">
            <el-form-item label="头像" prop="userImg">
              <image-upload v-model="form.userImg" :limit="1"/>
            </el-form-item>

          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">

            <el-form-item label="资质" prop="aptitude">
              <el-input v-model="form.aptitude" type="textarea" placeholder="请输入内容" maxlength="400" />
            </el-form-item>

          </el-col>
        </el-row>
        <el-row >
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" maxlength="200" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-if="form.storeUserId">
          <el-col :span="24">
            <el-form-item label="账号" >
              {{form.account}}
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
    </StoreContainer>
</template>

<script>
import { listTutor, getTutor, delTutor, addTutor, updateTutor } from "@/api/billiard/tutor";
import { listAllStore } from '@/api/billiard/store'
import { listAllRole } from '@/api/system/role'
import { resetUserPwd } from '@/api/system/user'
import StoreContainer from '@/views/billiard/component/storeContainer.vue'

export default {
  name: "Tutor",
  components: { StoreContainer },
  dicts: ['store_user_status', 'sys_user_sex','sys_user_sex','store_tutor'],
  data() {
    return {
      storeInfo:null,
      roleOptions: [],
      storeOptions:[],
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
      // 门店助教表格数据
      tutorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        realName: null,
        mobile: null,
        userImg: null,
        sex: null,
        level: null,
        status: null,
        loginUserId: null,
        storeId: null
      },
      // 表单参数
      form: {
        roleIds:[]
      },
      // 表单校验
      rules: {
        realName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        roleIds:[
          { required: true, message: "角色不能为空", trigger: "blur" },
          {
            validator: function(rule, value, callback) {
              if ((value??[]).length===0) {
                callback(new Error("角色不能为空"));
              } else {
                //校验通过
                callback();
              }
            }, trigger: 'blur'
          }
        ],
        mobile: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          { validator:function(rule,value,callback){
              if(!/^(1)\d{10}$/.test(value) ){
                callback(new Error("请输入正确的手机号"));
              }else{
                //校验通过
                callback();
              }
            }, trigger: 'blur'
          },
        ],
        userImg: [
          { required: true, message: "头像不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "助教等级(1=助教，2=教练，3=总教)不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "门店状态不能为空", trigger: "change" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "门店不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.queryStores();
    this.queryRoles();
  },
  methods: {
    onStoreChanged(store){
      this.storeInfo=store;
      this.queryParams.storeId=store?.storeId||-1;
      this.getList();
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.realName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnClickModal: false,
        inputPattern: /^.{5,20}$/,
        inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
        inputValidator: (value) => {
          if (/<|>|"|'|\||\\/.test(value)) {
            return "不能包含非法字符：< > \" ' \\\ |"
          }
        },
      }).then(({ value }) => {
        resetUserPwd(row.loginUserId, value).then(response => {
          this.$modal.msgSuccess("修改成功，新密码是：" + value);
        });
      }).catch(() => {});
    },
    /** 查询门店助教列表 */
    getList() {
      this.loading = true;
      listTutor(this.queryParams).then(response => {
        this.tutorList = response.rows;
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
        storeTutorId: null,
        roleIds:[],
        realName: null,
        mobile: null,
        userImg: null,
        sex: null,
        level: null,
        status: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        loginUserId: null,
        remark: null,
        storeId: null
      };
      this.resetForm("form");
    },
    queryStores(){
      return   listAllStore().then(response => {
        this.storeOptions = (response.data||[]).map(p=>{
          return Object.assign({label:p.storeName,value:p.storeId,raw:{listClass:'primary'}},p);
        });
      });
    },
    queryRoles(){
      return   listAllRole().then(response => {
        this.roleOptions = (response.data||[]).filter(p=>p.roleId!=1).map(p=>{
          return Object.assign({label:p.roleName,value:p.roleId,raw:{listClass:'primary'}},p);
        });
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.storeTutorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if(!this.storeInfo?.storeId){
        return  this.$modal.msgWarning("请选择门店");
      }
      this.reset();
      this.form.storeId=this.storeInfo?.storeId;
      this.open = true;
      this.title = "添加门店助教";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const storeTutorId = row.storeTutorId || this.ids
      getTutor(storeTutorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店助教";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.storeTutorId != null) {
            updateTutor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTutor(this.form).then(response => {
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
      const storeTutorIds = row.storeTutorId || this.ids;
      this.$modal.confirm('是否确认删除门店助教编号为"' + storeTutorIds + '"的数据项？').then(function() {
        return delTutor(storeTutorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/tutor/export', {
        ...this.queryParams
      }, `tutor_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
