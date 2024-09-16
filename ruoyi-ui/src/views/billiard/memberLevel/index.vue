<template>
  <StoreContainer @onStoreChanged="onStoreChanged">
    <div class="container-div">
      <div class=" col-sm-12 search-collapse" v-show="showSearch">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="会员等级" prop="levelName">
            <el-input
              v-model="queryParams.levelName"
              placeholder="请输入会员等级"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
<!--          <el-form-item label="门店" prop="storeId">-->
<!--            <el-select v-model="queryParams.storeId" placeholder="请选择门店" clearable @change="handleQuery">-->
<!--              <el-option-->
<!--                v-for="dict in stoOptions"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->
          <!--      <el-form-item label="创建者Id" prop="createById">-->
          <!--        <el-input-->
          <!--          v-model="queryParams.createById"-->
          <!--          placeholder="请输入创建者Id"-->
          <!--          clearable-->
          <!--          @keyup.enter.native="handleQuery"-->
          <!--        />-->
          <!--      </el-form-item>-->
          <!--      <el-form-item label="更新者Id" prop="updateById">-->
          <!--        <el-input-->
          <!--          v-model="queryParams.updateById"-->
          <!--          placeholder="请输入更新者Id"-->
          <!--          clearable-->
          <!--          @keyup.enter.native="handleQuery"-->
          <!--        />-->
          <!--      </el-form-item>-->
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
              v-hasPermi="['billiard:memberLevel:add']"
            >新增
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="memberLevelList" @selection-change="handleSelectionChange">
          <el-table-column label="ID" align="center" prop="memberLevelId"/>
          <el-table-column label="会员等级" align="center" prop="levelName"/>
          <el-table-column label="折扣力度" align="center" prop="discount">
            <template slot-scope="scope" v-if="scope.row.discount">
              {{ scope.row.discount }}折
            </template>
          </el-table-column>
          <el-table-column label="门店" align="center" prop="storeId">
            <template slot-scope="scope">
              <dict-tag :options="stoOptions" :value="scope.row.storeId"/>
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
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['billiard:memberLevel:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['billiard:memberLevel:remove']"
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

      <!-- 添加或修改门店会员等级对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="会员等级" prop="levelName">
            <el-input v-model="form.levelName" placeholder="请输入会员等级"/>
          </el-form-item>
          <el-form-item label="折扣力度" prop="discount">
            <el-input v-model="form.discount" placeholder="请输入折扣力度 95折就填写95"/>
          </el-form-item>
<!--          <el-form-item label="门店" prop="storeId">-->
<!--            <el-select v-model="form.storeId" placeholder="请选择门店" clearable>-->
<!--              <el-option-->
<!--                v-for="dict in stoOptions"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->
          <!--        <el-form-item label="创建者Id" prop="createById">-->
          <!--          <el-input v-model="form.createById" placeholder="请输入创建者Id" />-->
          <!--        </el-form-item>-->
          <!--        <el-form-item label="更新者Id" prop="updateById">-->
          <!--          <el-input v-model="form.updateById" placeholder="请输入更新者Id" />-->
          <!--        </el-form-item>-->
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
          </el-form-item>
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
import {
  listMemberLevel,
  getMemberLevel,
  delMemberLevel,
  addMemberLevel,
  updateMemberLevel,
  checkMemberLevel
} from "@/api/billiard/memberLevel";
import {listStoreAll} from "@/api/billiard/store";
import {listUserAll} from "@/api/system/user";
import StoreContainer from "@/views/billiard/component/storeContainer.vue";

export default {
  name: "MemberLevel",
  components: {StoreContainer},
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
      // 门店会员等级表格数据
      memberLevelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        levelName: null,
        storeId: null,
        createById: null,
        updateById: null,
      },
      stoOptions: [], // 门店列表
      userOptions: [], // 用户列表
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        levelName: [
          {required: true, message: "会员等级不能为空", trigger: "blur"}
        ],
        discount: [
          {required: true, message: "折扣力度", trigger: "blur"}
        ],
        storeId: [
          {required: true, message: "门店不能为空", trigger: "change"}
        ]
      }
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
      this.getList()
      this.getAllStore()
      this.getUserList()
    },
    /** 查询所有门店 */
    getAllStore() {
      listStoreAll().then(res => {
        this.stoOptions = (res.data || []).map(p => {
          return Object.assign({label: p.storeName, value: p.storeId, raw: {listClass: ''}}, p)
        })
      })
    },

    /** 获取用户列表 */
    getUserList() {
      listUserAll().then(response => {
        this.userOptions = (response.data || []).map(p => {
          return Object.assign({label: p.nickName, value: p.userId, raw: {listClass: ''}}, p)
        });

        console.log('用户', this.userOptions)
      })
    },
    /** 查询门店会员等级列表 */
    getList() {
      this.loading = true;
      listMemberLevel(this.queryParams).then(response => {
        this.memberLevelList = response.rows;
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
        memberLevelId: null,
        levelName: null,
        discount: null,
        storeId: this.storeInfo?.storeId || -1,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        createById: null,
        updateById: null,
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
      this.ids = selection.map(item => item.memberLevelId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门店会员等级";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const memberLevelId = row.memberLevelId || this.ids
      getMemberLevel(memberLevelId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店会员等级";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.memberLevelId != null) {
            updateMemberLevel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMemberLevel(this.form).then(response => {
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
      const _vm = this
      const memberLevelIds = row.memberLevelId || this.ids;
      this.$modal.confirm('是否确认删除门店会员等级编号为"' + memberLevelIds + '"的数据项？').then(function () {
        checkMemberLevel(memberLevelIds).then(res => {
          if (res.data) {
            console.log(1111111111111)
            _vm.$modal.msgError('该等级下有会员，无法删除')
            return
          }
          delMemberLevel(memberLevelIds).then(res => {
            if (res.code === 200) {
              _vm.getList();
              _vm.$modal.msgSuccess("删除成功");
            }
          });

        })
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('billiard/memberLevel/export', {
        ...this.queryParams
      }, `memberLevel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
