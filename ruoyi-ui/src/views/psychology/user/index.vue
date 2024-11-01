<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="头像地址" prop="avatar">
        <el-input
          v-model="queryParams.avatar"
          placeholder="请输入头像地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="" prop="wxOpenid">
        <el-input
          v-model="queryParams.wxOpenid"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['psychology:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['psychology:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['psychology:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['psychology:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户id" align="center" prop="id" />
      <el-table-column label="用户名" align="center" prop="name" />
      <el-table-column label="手机号码" align="center" prop="phone" />
      <el-table-column label="头像地址" align="center" prop="avatar">
        <template slot-scope="scope">
          <image-preview :src="scope.row.avatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="帐号状态" align="center" prop="status" >
        <template slot-scope="scope">
          {{scope.row.status==1?'停用':'正常'}}
        </template>
      </el-table-column>
<!--      <el-table-column label="" align="center" prop="wxOpenid" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['psychology:user:edit']"
          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDetail(scope.row)"
            v-hasPermi="['psychology:user:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['psychology:user:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon=""
            @click="handleGrant(scope.row)"
            v-hasPermi="['psychology:user:remove']"
          >发放优惠券</el-button>
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

    <!-- 添加或修改用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="头像地址" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像地址" />
        </el-form-item>
        <el-form-item label="微信Id" prop="wxOpenid">
          <el-input v-model="form.wxOpenid" placeholder="请输入" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户详情对话框 -->
    <el-dialog :title="title" :visible.sync="openDetail" width="1350px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name" placeholder="请输入" disabled/>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入" disabled/>
        </el-form-item>
<!--        <el-form-item label="头像地址" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像地址" disabled/>
        </el-form-item>-->
        <el-form-item label="微信Id" prop="wxOpenid">
          <el-input v-model="form.wxOpenid" placeholder="请输入" disabled/>
        </el-form-item>

        <el-form-item label="拥有的券清单">
          <el-button :type="condition === 1 ? 'primary' : 'info'" size="mini" @click="switchCondition(1)">可用</el-button>
          <el-button :type="condition === 2 ? 'primary' : 'info'" size="mini" @click="switchCondition(2)">已使用</el-button>
          <el-button :type="condition === 3 ? 'primary' : 'info'" size="mini" @click="switchCondition(3)">已过期</el-button>
          <el-table v-loading="loading" :data="couponList" >
              <el-table-column label="优惠券编号" align="center" prop="couponNo" />
              <el-table-column label="优惠券名" align="center" prop="couponName" />
              <el-table-column label="领取时间" align="center" prop="createTime" />
              <el-table-column label="使用时间" align="center" prop="useTime" />
              <el-table-column label="过期日" align="center" prop="expireDate" />
          </el-table>

          <pagination
            v-show="totalCoupon>0"
            :total="totalCoupon"
            :page.sync="queryCoupon.pageNum"
            :limit.sync="queryCoupon.pageSize"
            @pagination="getCouponList"
          />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelDetail">返 回</el-button>
      </div>
    </el-dialog>

    <!-- 发放优惠券-对话框 -->
    <el-dialog :title="title" :visible.sync="openGrant" width="500px" append-to-body>
      <el-form ref="form" :model="grantForm" :rules="grantRules" label-width="80px">

        <el-form-item label="券模版" prop="level">
          <el-select v-model="grantForm.templateId" clearable>
            <el-option
              v-for="tem in couponTemList"
              :key="tem.id"
              :label="tem.couponName"
              :value="tem.id"
            />
          </el-select>
        </el-form-item>
<!--        <el-form-item label="发放张数" prop="count">
          <el-input-number size="mini" v-model="form.workHours" :min="0" placeholder="请输入从业年限" />
        </el-form-item>-->
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitGrantForm">确 定</el-button>
        <el-button @click="cancelGrant">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/psychology/user";
import { listTemplate , grantCoupon, listCoupon } from "@/api/marketing/coupon";

export default {
  name: "User",
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
      totalCoupon: 0,
      // 用户表格数据
      userList: [],
      couponTemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openGrant: false,
      openDetail: false,
      condition: 1,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        phone: null,
        avatar: null,
        status: null,
        wxOpenid: null,
      },
      queryGrant:{
        userType:1,
        pageNum: 1,
        pageSize: 99999,
        templateStatus:0
      },
      queryCoupon:{
        pageNum: 1,
        pageSize: 10,
        isUsable: 0,
        isExpire: 0,
        userId: ''
      },
      // 表单参数
      form: {},
      couponList: [],
      grantForm: {},
      // 表单校验
      rules: {},
      grantRules: {},
    };
  },
  created() {
    this.getList();
    this.getCouponTemList();
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //查询优惠券模版清单
    getCouponTemList(){
      listTemplate(this.queryGrant).then(response => {
        this.couponTemList = response.rows;
        console.log("===============");
        console.log(this.couponTemList);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancelGrant(){
      this.openGrant = false;
      this.grantForm = {
      };
    },
    cancelDetail(){
      this.openDetail = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        phone: null,
        avatar: null,
        status: "0",
        wxOpenid: null,
        createTime: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户";
      });
    },
    /** 详情按钮操作 */
    handleDetail(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.openDetail = true;
        this.title = "用户详情";
      });
      this.queryCoupon.userId = id;
      this.getCouponList();
    },
    //查询用户名下的优惠券清单
    getCouponList(){
      listCoupon(this.queryCoupon).then(response => {
        this.couponList = response.rows;
        this.totalCoupon = response.total;
      });
    },
    //用户详情, 切换券清单的查询条件
    switchCondition(condition){
        switch (condition) {
            case 1://可用
              this.queryCoupon.isUsable = 0;
              this.queryCoupon.isExpire = 0;
              break;
            case 2://已使用
              this.queryCoupon.isUsable = 1;
              this.queryCoupon.isExpire = null;
              break;
            case 3://已过期
              this.queryCoupon.isUsable = 0;
              this.queryCoupon.isExpire = 1;
              break;
        }
        this.condition = condition;
        this.getCouponList();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    //提交发放
    submitGrantForm(){
      grantCoupon(this.grantForm).then(response => {
        this.$modal.msgSuccess("发放成功");
        this.openGrant = false;
        this.getList();
        this.grantForm = {};
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + ids + '"的数据项？').then(function() {
        return delUser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** [发放优惠券] 按钮操作 */
    handleGrant(row) {
      this.openGrant = true;
      this.grantForm.userId = row.id;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('psychology/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
