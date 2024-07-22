<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标签：0：供应商、商家，1：供应商，2.商家" prop="label">
        <el-input
          v-model="queryParams.label"
          placeholder="请输入标签：0：供应商、商家，1：供应商，2.商家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国家" prop="country">
        <el-input
          v-model="queryParams.country"
          placeholder="请输入国家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省份" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入省份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地区" prop="area">
        <el-input
          v-model="queryParams.area"
          placeholder="请输入地区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册编号" prop="registrationNo">
        <el-input
          v-model="queryParams.registrationNo"
          placeholder="请输入注册编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="在华注册编号" prop="registrationNoInChina">
        <el-input
          v-model="queryParams.registrationNoInChina"
          placeholder="请输入在华注册编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称" prop="supplierNameCn">
        <el-input
          v-model="queryParams.supplierNameCn"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称" prop="supplierNameEn">
        <el-input
          v-model="queryParams.supplierNameEn"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册时间" prop="registrationTime">
        <el-date-picker clearable
          v-model="queryParams.registrationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择注册时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="注册时间有效期" prop="registrationTimeValidityPeriod">
        <el-date-picker clearable
          v-model="queryParams.registrationTimeValidityPeriod"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择注册时间有效期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="考察评级:A、B、C、D、E" prop="rate">
        <el-input
          v-model="queryParams.rate"
          placeholder="请输入考察评级:A、B、C、D、E"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核评级:A、B、C、D、E" prop="reviewRating">
        <el-input
          v-model="queryParams.reviewRating"
          placeholder="请输入审核评级:A、B、C、D、E"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除：0：未删除，1：已删除" prop="deleteFlag">
        <el-input
          v-model="queryParams.deleteFlag"
          placeholder="请输入是否删除：0：未删除，1：已删除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考察情况id" prop="inspectionId">
        <el-input
          v-model="queryParams.inspectionId"
          placeholder="请输入考察情况id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input
          v-model="queryParams.contacts"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="telephone">
        <el-input
          v-model="queryParams.telephone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电子邮件" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入电子邮件"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类：0：国产，1：进口" prop="classification">
        <el-input
          v-model="queryParams.classification"
          placeholder="请输入分类：0：国产，1：进口"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据来源：0：供应商入驻，1：系统新增，2：批量导入" prop="dataSources">
        <el-input
          v-model="queryParams.dataSources"
          placeholder="请输入数据来源：0：供应商入驻，1：系统新增，2：批量导入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段1" prop="futureField1">
        <el-input
          v-model="queryParams.futureField1"
          placeholder="请输入预留字段1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段2" prop="futureField2">
        <el-input
          v-model="queryParams.futureField2"
          placeholder="请输入预留字段2"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段3" prop="futureField3">
        <el-input
          v-model="queryParams.futureField3"
          placeholder="请输入预留字段3"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段4" prop="futureField4">
        <el-input
          v-model="queryParams.futureField4"
          placeholder="请输入预留字段4"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留字段5" prop="futureField5">
        <el-input
          v-model="queryParams.futureField5"
          placeholder="请输入预留字段5"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:supplier:add']"
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
          v-hasPermi="['system:supplier:edit']"
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
          v-hasPermi="['system:supplier:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:supplier:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="供应商id" align="center" prop="supplierId" />
      <el-table-column label="标签：0：供应商、商家，1：供应商，2.商家" align="center" prop="label" />
      <el-table-column label="国家" align="center" prop="country" />
      <el-table-column label="省份" align="center" prop="province" />
      <el-table-column label="市" align="center" prop="city" />
      <el-table-column label="地区" align="center" prop="area" />
      <el-table-column label="产品分类:进口/牛肉，国产/鸡肉" align="center" prop="productType" />
      <el-table-column label="注册编号" align="center" prop="registrationNo" />
      <el-table-column label="在华注册编号" align="center" prop="registrationNoInChina" />
      <el-table-column label="企业名称" align="center" prop="supplierNameCn" />
      <el-table-column label="企业名称" align="center" prop="supplierNameEn" />
      <el-table-column label="注册时间" align="center" prop="registrationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registrationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册时间有效期" align="center" prop="registrationTimeValidityPeriod" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registrationTimeValidityPeriod, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考察评级:A、B、C、D、E" align="center" prop="rate" />
      <el-table-column label="审核评级:A、B、C、D、E" align="center" prop="reviewRating" />
      <el-table-column label="是否删除：0：未删除，1：已删除" align="center" prop="deleteFlag" />
      <el-table-column label="考察情况id" align="center" prop="inspectionId" />
      <el-table-column label="联系人" align="center" prop="contacts" />
      <el-table-column label="联系电话" align="center" prop="telephone" />
      <el-table-column label="电子邮件" align="center" prop="email" />
      <el-table-column label="分类：0：国产，1：进口" align="center" prop="classification" />
      <el-table-column label="审核状态:0：待审核:1：已通过:2：未通过" align="center" prop="auditStatus" />
      <el-table-column label="考察状态：0候选，1：待考察，2：已考察" align="center" prop="inspectionStatus" />
      <el-table-column label="考察审核状态：0：待审核:1：已通过:2：未通过" align="center" prop="inspectionAuditStatus" />
      <el-table-column label="数据来源：0：供应商入驻，1：系统新增，2：批量导入" align="center" prop="dataSources" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="预留字段1" align="center" prop="futureField1" />
      <el-table-column label="预留字段2" align="center" prop="futureField2" />
      <el-table-column label="预留字段3" align="center" prop="futureField3" />
      <el-table-column label="预留字段4" align="center" prop="futureField4" />
      <el-table-column label="预留字段5" align="center" prop="futureField5" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:supplier:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:supplier:remove']"
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

    <!-- 添加或修改供应商对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标签：0：供应商、商家，1：供应商，2.商家" prop="label">
          <el-input v-model="form.label" placeholder="请输入标签：0：供应商、商家，1：供应商，2.商家" />
        </el-form-item>
        <el-form-item label="国家" prop="country">
          <el-input v-model="form.country" placeholder="请输入国家" />
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="市" prop="city">
          <el-input v-model="form.city" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="地区" prop="area">
          <el-input v-model="form.area" placeholder="请输入地区" />
        </el-form-item>
        <el-form-item label="注册编号" prop="registrationNo">
          <el-input v-model="form.registrationNo" placeholder="请输入注册编号" />
        </el-form-item>
        <el-form-item label="在华注册编号" prop="registrationNoInChina">
          <el-input v-model="form.registrationNoInChina" placeholder="请输入在华注册编号" />
        </el-form-item>
        <el-form-item label="企业名称" prop="supplierNameCn">
          <el-input v-model="form.supplierNameCn" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="企业名称" prop="supplierNameEn">
          <el-input v-model="form.supplierNameEn" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="注册时间" prop="registrationTime">
          <el-date-picker clearable
            v-model="form.registrationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注册时间有效期" prop="registrationTimeValidityPeriod">
          <el-date-picker clearable
            v-model="form.registrationTimeValidityPeriod"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择注册时间有效期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考察评级:A、B、C、D、E" prop="rate">
          <el-input v-model="form.rate" placeholder="请输入考察评级:A、B、C、D、E" />
        </el-form-item>
        <el-form-item label="审核评级:A、B、C、D、E" prop="reviewRating">
          <el-input v-model="form.reviewRating" placeholder="请输入审核评级:A、B、C、D、E" />
        </el-form-item>
        <el-form-item label="是否删除：0：未删除，1：已删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入是否删除：0：未删除，1：已删除" />
        </el-form-item>
        <el-form-item label="考察情况id" prop="inspectionId">
          <el-input v-model="form.inspectionId" placeholder="请输入考察情况id" />
        </el-form-item>
        <el-form-item label="联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="telephone">
          <el-input v-model="form.telephone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="电子邮件" prop="email">
          <el-input v-model="form.email" placeholder="请输入电子邮件" />
        </el-form-item>
        <el-form-item label="分类：0：国产，1：进口" prop="classification">
          <el-input v-model="form.classification" placeholder="请输入分类：0：国产，1：进口" />
        </el-form-item>
        <el-form-item label="数据来源：0：供应商入驻，1：系统新增，2：批量导入" prop="dataSources">
          <el-input v-model="form.dataSources" placeholder="请输入数据来源：0：供应商入驻，1：系统新增，2：批量导入" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="预留字段1" prop="futureField1">
          <el-input v-model="form.futureField1" placeholder="请输入预留字段1" />
        </el-form-item>
        <el-form-item label="预留字段2" prop="futureField2">
          <el-input v-model="form.futureField2" placeholder="请输入预留字段2" />
        </el-form-item>
        <el-form-item label="预留字段3" prop="futureField3">
          <el-input v-model="form.futureField3" placeholder="请输入预留字段3" />
        </el-form-item>
        <el-form-item label="预留字段4" prop="futureField4">
          <el-input v-model="form.futureField4" placeholder="请输入预留字段4" />
        </el-form-item>
        <el-form-item label="预留字段5" prop="futureField5">
          <el-input v-model="form.futureField5" placeholder="请输入预留字段5" />
        </el-form-item>
        <el-divider content-position="center">产品信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddSysProduct">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteSysProduct">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="sysProductList" :row-class-name="rowSysProductIndex" @selection-change="handleSysProductSelectionChange" ref="sysProduct">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="一级分类" prop="primaryCategory" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.primaryCategory" placeholder="请输入一级分类" />
            </template>
          </el-table-column>
          <el-table-column label="二级分类" prop="secondaryCategory" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.secondaryCategory" placeholder="请输入二级分类" />
            </template>
          </el-table-column>
          <el-table-column label="三级分类" prop="tertiaryCategory" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tertiaryCategory" placeholder="请输入三级分类" />
            </template>
          </el-table-column>
          <el-table-column label="四级分类" prop="quaternaryCategory" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.quaternaryCategory" placeholder="请输入四级分类" />
            </template>
          </el-table-column>
          <el-table-column label="五级分类" prop="fifthCategory" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.fifthCategory" placeholder="请输入五级分类" />
            </template>
          </el-table-column>
          <el-table-column label="产品名称" prop="productName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.productName" placeholder="请输入产品名称" />
            </template>
          </el-table-column>
          <el-table-column label="0-国产 1-进口" prop="domesticImportedType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.domesticImportedType" placeholder="请选择0-国产 1-进口">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="产品型号" prop="productModel" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.productModel" placeholder="请输入产品型号" />
            </template>
          </el-table-column>
          <el-table-column label="是否可报价：0：不可报价，1：可报价" prop="quotationFlag" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.quotationFlag" placeholder="请输入是否可报价：0：不可报价，1：可报价" />
            </template>
          </el-table-column>
          <el-table-column label="预留字段1" prop="futureField1" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.futureField1" placeholder="请输入预留字段1" />
            </template>
          </el-table-column>
          <el-table-column label="预留字段2" prop="futureField2" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.futureField2" placeholder="请输入预留字段2" />
            </template>
          </el-table-column>
          <el-table-column label="预留字段3" prop="futureField3" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.futureField3" placeholder="请输入预留字段3" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSupplier, getSupplier, delSupplier, addSupplier, updateSupplier } from "@/api/system/supplier";

export default {
  name: "Supplier",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedSysProduct: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 供应商表格数据
      supplierList: [],
      // 产品表格数据
      sysProductList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        label: null,
        country: null,
        province: null,
        city: null,
        area: null,
        productType: null,
        registrationNo: null,
        registrationNoInChina: null,
        supplierNameCn: null,
        supplierNameEn: null,
        registrationTime: null,
        registrationTimeValidityPeriod: null,
        rate: null,
        reviewRating: null,
        deleteFlag: null,
        inspectionId: null,
        contacts: null,
        telephone: null,
        email: null,
        classification: null,
        auditStatus: null,
        inspectionStatus: null,
        inspectionAuditStatus: null,
        dataSources: null,
        futureField1: null,
        futureField2: null,
        futureField3: null,
        futureField4: null,
        futureField5: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        label: [
          { required: true, message: "标签：0：供应商、商家，1：供应商，2.商家不能为空", trigger: "blur" }
        ],
        country: [
          { required: true, message: "国家不能为空", trigger: "blur" }
        ],
        registrationNo: [
          { required: true, message: "注册编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询供应商列表 */
    getList() {
      this.loading = true;
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows;
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
        supplierId: null,
        label: null,
        country: null,
        province: null,
        city: null,
        area: null,
        productType: null,
        registrationNo: null,
        registrationNoInChina: null,
        supplierNameCn: null,
        supplierNameEn: null,
        registrationTime: null,
        registrationTimeValidityPeriod: null,
        rate: null,
        reviewRating: null,
        deleteFlag: null,
        inspectionId: null,
        contacts: null,
        telephone: null,
        email: null,
        classification: null,
        auditStatus: null,
        inspectionStatus: null,
        inspectionAuditStatus: null,
        dataSources: null,
        remark: null,
        futureField1: null,
        futureField2: null,
        futureField3: null,
        futureField4: null,
        futureField5: null
      };
      this.sysProductList = [];
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
      this.ids = selection.map(item => item.supplierId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加供应商";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const supplierId = row.supplierId || this.ids
      getSupplier(supplierId).then(response => {
        this.form = response.data;
        this.sysProductList = response.data.sysProductList;
        this.open = true;
        this.title = "修改供应商";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.sysProductList = this.sysProductList;
          if (this.form.supplierId != null) {
            updateSupplier(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSupplier(this.form).then(response => {
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
      const supplierIds = row.supplierId || this.ids;
      this.$modal.confirm('是否确认删除供应商编号为"' + supplierIds + '"的数据项？').then(function() {
        return delSupplier(supplierIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 产品序号 */
    rowSysProductIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 产品添加按钮操作 */
    handleAddSysProduct() {
      let obj = {};
      obj.primaryCategory = "";
      obj.secondaryCategory = "";
      obj.tertiaryCategory = "";
      obj.quaternaryCategory = "";
      obj.fifthCategory = "";
      obj.productName = "";
      obj.domesticImportedType = "";
      obj.productModel = "";
      obj.quotationFlag = "";
      obj.futureField1 = "";
      obj.futureField2 = "";
      obj.futureField3 = "";
      this.sysProductList.push(obj);
    },
    /** 产品删除按钮操作 */
    handleDeleteSysProduct() {
      if (this.checkedSysProduct.length == 0) {
        this.$modal.msgError("请先选择要删除的产品数据");
      } else {
        const sysProductList = this.sysProductList;
        const checkedSysProduct = this.checkedSysProduct;
        this.sysProductList = sysProductList.filter(function(item) {
          return checkedSysProduct.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleSysProductSelectionChange(selection) {
      this.checkedSysProduct = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/supplier/export', {
        ...this.queryParams
      }, `supplier_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
