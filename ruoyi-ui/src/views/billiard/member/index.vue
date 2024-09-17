<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
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
          <el-form-item label="性别" prop="sex">
            <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable @change="handleQuery">
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="会员等级" prop="levelId">
            <el-select v-model="queryParams.levelId" placeholder="请选择会员等级" clearable @change="handleQuery">
              <el-option
                v-for="dict in levelOptions"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择状态" clearable @change="handleQuery">
              <el-option
                v-for="dict in dict.type.member_status"
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
              v-hasPermi="['billiard:member:add']"
            >新增
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange">
          <el-table-column label="ID" align="center" prop="memberId"/>
          <el-table-column label="姓名" align="center" prop="realName"/>
          <el-table-column label="手机号" align="center" prop="mobile"/>
          <el-table-column label="当前金额" align="center" prop="currentAmount"/>
          <el-table-column label="历史总金额" align="center" prop="totalAmount"/>
          <el-table-column label="性别" align="center" prop="sex">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
            </template>
          </el-table-column>
          <el-table-column label="门店" align="center" prop="storeName"/>
          <el-table-column label="会员等级" align="center" prop="levelId">
            <template slot-scope="scope">
              <dict-tag :options="levelOptions" :value="scope.row.levelId"/>
            </template>
          </el-table-column>
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.member_status" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="是否删除" align="center" prop="delFlag" width="180">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.member_del_flag" :value="scope.row.delFlag"/>
            </template>
          </el-table-column>
          <el-table-column label="创建/更新" align="center" prop="createById" width="250">>
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
            <template slot-scope="scope" v-if="scope.row.delFlag === '0'">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:member:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:member:remove']"
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

      <!-- 添加或修改门店会员对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名" prop="realName">
                <el-input v-model="form.realName" placeholder="请输入姓名"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="手机号" prop="mobile">
                <el-input type="tel" maxlength="11" v-model="form.mobile" placeholder="请输入手机号"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="性别" prop="sex">
                <el-select v-model="form.sex" placeholder="请选择性别" class="with100">
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
              <el-form-item label="会员等级" prop="levelId">
                <el-select v-model="form.levelId" placeholder="请选择性别" class="with100">
                  <el-option
                    v-for="dict in levelOptions"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
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
              <el-form-item label="状态" prop="status">
                <el-radio-group v-model="form.status">
                  <el-radio-button     :label="0"   >
                    正常
                  </el-radio-button>
                  <el-radio-button     :label="1"   >
                    停用
                  </el-radio-button>
                </el-radio-group>
              </el-form-item>
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
import {listMember, getMember, delMember, addMember, updateMember} from "@/api/billiard/member";
import {getAllMemberLevel} from "@/api/billiard/memberLevel";
import StoreContainer from "@/views/billiard/component/storeContainer.vue";

export default {
  name: "Member",
  components: {StoreContainer},
  dicts: ['sys_user_sex', 'member_status', 'member_del_flag'],
  data() {
    const checkPhone = (rule, value, callback) => {
      const reg = /^1[3-9]\d{9}$/;
      if (value === '') {
        callback(new Error('手机号不能为空'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入正确的手机号'));
      } else {
        callback();
      }
    };
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
      // 门店会员表格数据
      memberList: [],
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
        sex: null,
        levelId: null,
        status: null,
        storeId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        realName: [
          {required: true, message: "姓名不能为空", trigger: "blur"}
        ],
        mobile: [
          {required: true, message: "手机号不能为空", trigger: "blur"},
          { validator: checkPhone, trigger: 'blur' }
        ],
        currentAmount: [
          {required: true, message: "当前金额不能为空", trigger: "blur"}
        ],
        totalAmount: [
          {required: true, message: "历史总金额不能为空", trigger: "blur"}
        ],
        storeId: [
          {required: true, message: "门店不能为空", trigger: "blur"}
        ],
        levelId: [
          {required: true, message: "会员等级不能为空", trigger: "change"}
        ],
        status: [
          {required: true, message: "状态不能为空", trigger: "change"}
        ],
      },
      levelOptions: [], // 当前门店会员等级列表
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
    /** 初始化数据 */
    initData() {
      this.getList();
      this.getAllLevelByStoreId();
    },
    /** 根据当前门店id查询所有当前门店会员等级 */
    getAllLevelByStoreId() {
      getAllMemberLevel(this.queryParams.storeId).then(res => {
        this.levelOptions = (res.data || []).map(p => {
          return Object.assign({label: p.levelName, value: p.memberLevelId, raw: {listClass: ''}}, p)
        })
      })
    },
    /** 查询门店会员列表 */
    getList() {
      this.loading = true;
      listMember(this.queryParams).then(response => {
        this.memberList = response.rows;
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
        memberId: null,
        realName: null,
        mobile: null,
        currentAmount: null,
        totalAmount: null,
        sex: null,
        storeId: this.storeInfo?.storeId || -1,
        levelId: null,
        status: 0,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.memberId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门店会员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const memberId = row.memberId || this.ids
      getMember(memberId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店会员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.memberId != null) {
            updateMember(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMember(this.form).then(response => {
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
      const memberIds = row.memberId || this.ids;
      this.$modal.confirm('是否确认删除门店会员编号为"' + memberIds + '"的数据项？').then(function () {
        return delMember(memberIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/member/export', {
        ...this.queryParams
      }, `member_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
