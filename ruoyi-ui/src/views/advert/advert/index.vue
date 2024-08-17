<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="广告编号" prop="advertNo">
        <el-input
          v-model="queryParams.advertNo"
          placeholder="请输入广告编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="广告标题" prop="advertTitle">
        <el-input
          v-model="queryParams.advertTitle"
          placeholder="请输入广告标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="广告描述" prop="advertText">
        <el-input
          v-model="queryParams.advertText"
          placeholder="请输入广告描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属页面" prop="pageFor">
        <el-input
          v-model="queryParams.pageFor"
          placeholder="请输入所属页面"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="服务对象" prop="serviceTo">
        <el-select v-model="queryParams.serviceTo" clearable>
          <el-option
            v-for="dict in dict.type.user_type"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:advert:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="advertList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="广告编号" align="center" prop="advertNo" />
      <el-table-column label="广告标题" align="center" prop="advertTitle" />
      <el-table-column label="广告描述" align="center" prop="advertText" />
      <el-table-column label="所属页面" align="center" prop="pageFor" />
      <el-table-column label="数据类型" align="center" prop="dataType" />
      <el-table-column label="服务对象" align="center" prop="serviceTo" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_type" :value="scope.row.serviceTo"/>
        </template>
      </el-table-column>
      <el-table-column label="广告主图" align="center" prop="advertImg" >
        <template slot-scope="scope">
          <image-preview :src="scope.row.advertImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:advert:edit']"
          >修改</el-button>

          <el-button size="mini" type="text" icon="el-icon-edit" @click="openAdvertItemList(scope.row)" v-hasPermi="['system:advert:edit']">设置条目</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:advert:remove']"
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

    <!-- 添加或修改页面广告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="广告编号" prop="advertNo">
          <el-input v-model="form.advertNo" placeholder="请输入广告编号" :disabled="isEdit" />
        </el-form-item>
        <el-form-item label="广告标题" prop="advertTitle">
          <el-input v-model="form.advertTitle" placeholder="请输入广告标题" />
        </el-form-item>
        <el-form-item label="广告描述" prop="advertText">
          <el-input v-model="form.advertText" placeholder="请输入广告描述" />
        </el-form-item>
        <el-form-item label="所属页面" prop="pageFor">
          <el-input v-model="form.pageFor" placeholder="请输入所属页面" />
        </el-form-item>
        <el-form-item label="数据类型" prop="dataType">
          <el-input v-model="form.dataType" placeholder="请输入数据类型" />
        </el-form-item>
        <el-form-item label="服务对象" prop="type">
          <el-select v-model="form.serviceTo" placeholder="请选择服务对象">
            <el-option
              v-for="dict in userTypeList"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="广告主图" prop="advertImg">
          <my-cropper v-model="form.advertImg" sizeTip="宽500px 高500px" :extraData="extraData" :width="500" :height="500"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 广告条目编辑对话框 -->
    <el-drawer :title="title" :visible.sync="advertItemListOpen" size="35%" :with-header="false" append-to-body>
      <el-tabs v-model="activeTab" style="margin:0 20px">
        <el-tab-pane label="条目清单" name="advertItemList">
          <advertItemList v-bind:advertNo="advertNo" ></advertItemList>
        </el-tab-pane>
        <!--        <el-tab-pane v-if="[5,6,7,8].includes(gaugeType)" label="维度设置" name="lat">-->
<!--        <el-tab-pane v-if="[2,8].includes(gaugeType)" label="维度设置" name="lat">
          <lat v-bind:gaugeId="gaugeId"/>
        </el-tab-pane>
        <el-tab-pane label="条目设置" name="advertItemSetting">
          <setting v-bind:gaugeId="gaugeId" :gaugeType="gaugeType"></setting>
          &lt;!&ndash;           <multi v-if="gaugeType==2" v-bind:gaugeId="gaugeId" ></multi>&ndash;&gt;
        </el-tab-pane>-->
      </el-tabs>
    </el-drawer>


  </div>
</template>

<script>
import { listAdvert, getAdvert, delAdvert, addAdvert, updateAdvert } from "@/api/advert/advert";
import advertItemList from "@/views/components/advert/advertItemList";
import draggable from "vuedraggable";

export default {
  name: "Advert",
  dicts: ['user_type'],
  components: {
    advertItemList,
    draggable,
  },
  data() {
    return {
      userTypeList: this.$constants.userType,
      advertItemListOpen:false,
      activeTab:'',
      advertNo:'',
      // 上传
      extraData: {
        module: this.$constants['picModules'][2],
        type: this.$constants['picTypes'][2]
      },
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
      // 页面广告表格数据
      advertList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      isEdit: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        advertTitle: null,
        advertText: null,
        pageFor: null,
        dataType: null,
        serviceTo: null,
        advertImg: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询页面广告列表 */
    getList() {
      this.loading = true;
      listAdvert(this.queryParams).then(response => {
        this.advertList = response.rows;
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
        advertNo: null,
        advertTitle: null,
        advertText: null,
        pageFor: null,
        dataType: null,
        serviceTo: null,
        advertImg: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.advertNo)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.isEdit = false;
      this.title = "添加页面广告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const advertNo = row.advertNo || this.ids
      getAdvert(advertNo).then(response => {
        this.form = response.data;
        this.open = true;
        this.isEdit = true;
        this.title = "修改页面广告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.isEdit) {
            updateAdvert(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            if (!this.form.advertNo){ this.$modal.msgError("[广告编号]不能为空"); return }
            addAdvert(this.form).then(response => {
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
      const advertNos = row.advertNo || this.ids;
      this.$modal.confirm('是否确认删除页面广告编号为"' + advertNos + '"的数据项？').then(function() {
        return delAdvert(advertNos);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/advert/export', {
        ...this.queryParams
      }, `advert_${new Date().getTime()}.xlsx`)
    },
    //设置广告条目
    openAdvertItemList(data) {
      this.advertItemListOpen = true;
      this.activeTab = 'advertItemList'

      this.advertNo = data.advertNo;
    },
  }
};
</script>
