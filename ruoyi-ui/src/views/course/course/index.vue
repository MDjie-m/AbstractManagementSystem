<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名称" prop="nameValue">
        <el-input
          v-model="queryParams.nameValue"
          placeholder="请输入课程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程类型" prop="typeValue">
        <el-select v-model="queryParams.typeValue" placeholder="请选择课程类型" clearable>
          <el-option
            v-for="item in courseClassList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="服务对象" prop="typeValue">
        <el-select v-model="queryParams.serviceTo" placeholder="请选择服务对象" clearable>
          <el-option
            v-for="dict in dict.type.user_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="付费类型" prop="payTypeValue">
        <el-select v-model="queryParams.payTypeValue" placeholder="请选择课程付费方式" clearable>
          <el-option
            v-for="item in coursePayTypeList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="课程作者" prop="authorValue">
        <el-input
          v-model="queryParams.authorValue"
          placeholder="请输入课程作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程价格" class="price">
        <el-input
          v-model="queryParams.lowPrice"
          placeholder="请输入课程最低价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
        <div style="margin: 0 10px">~</div>
        <el-input
          v-model="queryParams.highPrice"
          placeholder="请输入课程最高价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否上架" prop="onSaleValue">
        <el-select v-model="queryParams.onSaleValue" placeholder="请选择课程是否上架" clearable>
          <el-option
            v-for="item in onSaleList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
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
          v-hasPermi="['course:course:add']"
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
          v-hasPermi="['course:course:edit']"
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
          v-hasPermi="['course:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['course:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="课程名称" align="center" prop="name" />
      <el-table-column label="课程类型" align="center" prop="type">
        <template slot-scope="scope">
          {{ getCourseClassName(scope.row.type) }}
        </template>
      </el-table-column>
      <el-table-column label="服务对象" align="center" prop="serviceTo">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_type" :value="scope.row.serviceTo"/>
        </template>
      </el-table-column>
      <el-table-column label="付费类型" align="center" prop="payType">
        <template slot-scope="scope">
          {{ getCoursePayType(scope.row.payType) }}
        </template>
      </el-table-column>
      <el-table-column label="课程图片" align="center" prop="url" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.url" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="列表图片" align="center" prop="iconUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.iconUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="课程作者" align="center" prop="author" />
      <el-table-column label="课程价格" align="center" prop="price" sortable />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['course:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['course:course:remove']"
          >删除</el-button>
          <el-button
            type="text"
            icon="el-icon-plus"
            size="mini"
            @click="handleSectionDrawer(scope.row)"
            v-hasPermi="['course:section:list']">
            章节管理
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="handleOnSale(scope.row)"
            v-show="scope.row.onSale === 0"
            v-hasPermi="['course:section:edit']">
            上架
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="handleOffSale(scope.row)"
            v-show="scope.row.onSale === 1"
            v-hasPermi="['course:section:edit']">
            下架
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

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px"  append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-row>
          <el-col :span="12">
            <el-form-item label="课程名称" prop="name">
              <el-input class="author_input" v-model="form.name" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="课程作者" prop="author" >
              <el-select v-model="form.author" filterable placeholder="请选择">
                <el-option
                  v-for="item in consultList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
<!--              <el-input class="author_input" v-model="form.author" placeholder="请输入课程作者" />-->
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-form-item label="课程类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择课程类型">
                <el-option
                  v-for="item in courseClassList"
                  :key="item.id"
                  :label="item.name"
                  :value="parseInt(item.id)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="付费类型" prop="payType">
              <el-select v-model="form.payType" placeholder="请选择课程付费类型" @change="handlePayTypeChange">
                <el-option
                  v-for="item in coursePayTypeList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
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
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="课程价格" prop="price">
              <el-input v-model="form.price" placeholder="请输入课程价格" :disabled="form.payType === 1" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="课程图片">
<!--          <image-upload v-model="form.url" sizeTip="宽750px 高422px" :limit="1" :extraData="extraData"/>-->
          <my-cropper v-model="form.url" sizeTip="宽750px 高394px" :extraData="extraData" :width="375" :height="197"/>
        </el-form-item>

        <el-form-item label="列表图片">
<!--          <image-upload v-model="form.iconUrl" sizeTip="宽172px 高172px" :limit="1" :extraData="extraData"/>-->
          <my-cropper v-model="form.iconUrl" sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>

        </el-form-item>

        <el-form-item label="课程详情">
<!--          <editor v-model="form.detail" :min-height="192" :extraData="extraData"/>-->

<!--<div>111
            &lt;!&ndash; 显示已上传的图片 &ndash;&gt;
            <div v-if="detailImages.length > 0">
              <div v-for="(url, index) in detailImages" :key="index">
                <image-upload  :value="url" :extraData="extraData" />
              </div>
            </div>
222
            <image-upload v-model="currentImage" :extraData="extraData" @finish-upload="addImage" />
             </div>-->
            <div class="upload-container">
              <image-upload v-model="form.detailImg1" :extraData="extraData"  :isShowTip="false" class="image-upload"/>
              <image-upload v-model="form.detailImg2" :extraData="extraData"  :isShowTip="false" class="image-upload"/>
              <image-upload v-model="form.detailImg3" :extraData="extraData"  :isShowTip="false" class="image-upload"/>
              <image-upload v-model="form.detailImg4" :extraData="extraData"  :isShowTip="false" class="image-upload"/>
            </div>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 新增关联章节侧边栏 -->
    <SectionDrawer :drawOpen="drawOpen" :drawCourse="drawCourse" @close="drawOpen = !drawOpen"></SectionDrawer>
  </div>
</template>

<script>
import { adminListConsult } from "@/api/psychology/consult";
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/course/course";
import { listClass } from "@/api/course/class"
import SectionDrawer from '@/views/components/course/sectionDrawer/index.vue'

export default {
  name: "Course",
  dicts: ['user_type'],
  data() {
    var validatePrice = (rule, value, callback) => {
      // 保留两位小数
      const regex = /^[0-9]+(\.[0-9]{1,2})?$/
      if (!regex.test(value)) {
        callback('课程价格为两位小数的数值表示')
      } else {
        callback()
      }
    }
    return {
      // 上传
      extraData: {
        module: this.$constants['picModules'][0],
        type: this.$constants['picTypes'][2]
      },
      userTypeList: this.$constants.userType,
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
      // 课程表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nameValue: null,
        typeValue: null,
        payTypeValue: null,
        authorValue: null,
        lowPrice: null,
        highPrice: null,
        onSaleValue: null,
        serviceTo: null,
      },
      currentImage: '', // 用于暂存上传的图片
      detailImages:[],
      // 表单参数
      form: {
        id: null,
        name: null,
        type: null,
        payType: null,
        author: null,
        userName: null,
        url: null,
        iconUrl: null,
        price: null,
        detail: null,
        createBy: null,
        createTime: null,
        updateTime: null,
        updateBy: null,
        detailImg1:null,
        detailImg2:null,
        detailImg3:null,
        detailImg4:null
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "课程类型不能为空", trigger: "change" }
        ],
        payType: [
          { required: true, message: "付费类型不能为空", trigger: "change" }
        ],
        price: [
          { required: true, message: "课程价格不能为空", trigger: "blur" },
          { validator: validatePrice, trigger: "blur" }
        ],
      },
      drawOpen: false,
      drawCourse: null,
      courseClassList: [],
      consultList: [],
      coursePayTypeList: [
        {
          id: 0,
          name: '付费课'
        },
        {
          id: 1,
          name: '免费课'
        },
      ],
      onSaleList: [
        {
          id: 0,
          name: '未上架'
        },
        {
          id: 1,
          name: '已上架'
        },
      ]
    };
  },
  components: {
    SectionDrawer,
  },
  created() {
    console.log('created')
    this.getCourseClassList()
    this.getList()
    this.getConsult()
  },
  /*computed: {
    detailImages() {
      // 从 detail 字符串生成数组
      console.log("****************************")
      console.log(this.form.detail ? this.form.detail.split(',') : [])
      return this.form.detail ? this.form.detail.split(',') : [];
    },
  },*/
  methods: {
      addImage(fileList) {
        this.detailImages.push(fileList[0].url);
        this.currentImage = ''; // 清空当前图片
        console.log(this.detailImages)
      },
      removeImage(index) {
        // 从数组中移除指定的图片
        const imagesArray = this.detailImages;
        imagesArray.splice(index, 1);
        this.form.detail = imagesArray.join(','); // 更新 detail 字符串
      },

    async getConsult() {
      // consultList
      const req = {
          pageNum: 1,
          pageSize: 999,
      }
      const res = await adminListConsult(req)
      if (res.code === 200) {
        this.consultList = res.rows
        const list = []
        this.consultList.forEach(a => {
          list.push({
            value: a.nickName,
            label: a.nickName,
            userName: a.userName
          })
        })

        this.consultList = list
      }
    },
    getCoursePayType(payType) {
      const list = this.coursePayTypeList.filter(item => item.id === payType)
      return list.length > 0 ? list[0].name : undefined
      // return this.coursePayTypeList.filter(item => item.id === payType)[0].name
    },
    getCourseClassName(type) {
      const list = this.courseClassList.filter(item => item.id === type)
      return list.length > 0 ? list[0].name : undefined
    },
    getCourseClassList() {
      listClass({}).then(response => {
        this.courseClassList = response.rows;
      });
    },
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
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
        id: null,
        name: null,
        type: null,
        payType: null,
        author: null,
        userName: null,
        url: null,
        iconUrl: null,
        price: null,
        detail: null,
        createBy: null,
        createTime: null,
        updateTime: null,
        updateBy: null,
        detailImg1:null,
        detailImg2:null,
        detailImg3:null,
        detailImg4:null
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
      // 课程类型默认值
      this.form.type = this.courseClassList[0].id
      // 付费类型默认值
      this.form.payType = this.coursePayTypeList[0].id

      this.open = true;
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.form = response.data;
        if (this.form.detail){
          let imgList = this.form.detail.split(",");
          this.form.detailImg1 = imgList[0];
          this.form.detailImg2 = imgList[1];
          this.form.detailImg3 = imgList[2];
          this.form.detailImg4 = imgList[3];

          console.log(this.form)
        }

        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.author) {
            const item = this.consultList.find(a => a.value === this.form.author)
            this.form.userName = item ? item.userName : ''
          }
          this.form.detail = this.form.detailImg1 + "," + this.form.detailImg2 + "," + this.form.detailImg3 + "," + this.form.detailImg4 ;

          if (this.form.id != null) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除课程？').then(function() {
        return delCourse(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    },
    handleSectionDrawer(course) {
      this.drawCourse = course
      this.drawOpen = !this.drawOpen
    },
    handlePayTypeChange(value) {
      if (value === 1) {
        // 付费类型为免费课， 价格为0，输入框禁止输入
        this.form.price = 0
      }
    },
    handleOnSale(course) {
      updateCourse({
        ...course,
        onSale: 1
      }).then(res => {
        if (res.code == 200) {
          this.$modal.msgSuccess("上架课程成功");
          this.getList();
        }
      });
    },
    handleOffSale(course) {
      updateCourse({
        ...course,
        onSale: 0
      }).then(res => {
        if (res.code == 200) {
          this.$modal.msgSuccess("下架课程成功");
          this.getList();
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.upload-container {
  display: flex;
  flex-wrap: wrap; /* 如果有很多组件，可以换行 */
  gap: 10px; /* 组件之间的间距，可以根据需要调整 */
}

.image-upload {
  width: 150px; /* 设置固定宽度 */
  height: 200px; /* 设置固定高度，确保是正方形 */
}
.author_input {
  ::v-deep .el-input__inner {
    width: 217px;
  }
}
.price {
  display: inline-block;
  ::v-deep {
    .el-form-item__content {
      display: inline-flex;
      align-items: center;
    }
    .el-input__inner {
      width: 150px;
    }
  }
}
</style>
