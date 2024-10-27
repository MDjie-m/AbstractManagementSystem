<template>
  <div class="app-container">
    <div v-show="!open">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="课程名称" prop="courseName">
          <el-input
            v-model="queryParams.courseName"
            placeholder="请输入课程名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="课程简介" prop="courseDescription">
          <el-input
            v-model="queryParams.courseDescription"
            placeholder="请输入课程简介"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="创建时间" prop="createDate">
          <el-date-picker clearable
                          v-model="queryParams.createDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updateDate">
          <el-date-picker clearable
                          v-model="queryParams.updateDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择更新时间">
          </el-date-picker>
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
            v-hasPermi="['workflow:courses:add']"
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
            v-hasPermi="['workflow:courses:edit']"
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
            v-hasPermi="['workflow:courses:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['workflow:courses:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="coursesList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="课程ID" align="center" prop="id" v-if="true"/>
        <el-table-column label="课程名称" align="center" prop="courseName" />
        <el-table-column label="课程封面" align="center" prop="courseCover">
          <template slot-scope="scope">
            <el-image :src="scope.row.courseCover"  :preview-src-list="[scope.row.courseCover]"
                      v-if="scope.row.courseCover !== '' && scope.row.courseCover !== null">
            </el-image>
            <span v-if="scope.row.courseCover === '' || scope.row.courseCover === null">暂无封面 </span>
          </template>
        </el-table-column>
        <el-table-column label="课程简介" align="center" prop="courseDescription" />
        <el-table-column label="创建时间" align="center" prop="createDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新时间" align="center" prop="updateDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建人员" align="center" prop="createBy" width="180"/>
        <el-table-column label="更新人员" align="center" prop="updateBy" width="180"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['workflow:courses:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['workflow:courses:remove']"
            >删除</el-button>
            <!--          <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDetail(scope.row)"
                        v-hasPermi="['workflow:courses:query']"
                      >详情</el-button>-->
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


    <!-- 添加或修改课程对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程简介" prop="courseDescription">
          <el-input v-model="form.courseDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="课程封面">
&lt;!&ndash;          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />&ndash;&gt;
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="coverList"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="1"
            :on-change="fileChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="课程体系">
          &lt;!&ndash;          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />&ndash;&gt;
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="systemFiles"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="5"
            :on-change="systemFilesChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="课程效果">
          &lt;!&ndash;          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />&ndash;&gt;
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="effectFiles"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="5"
            :on-change="effectFilesChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="材料包展示">
          &lt;!&ndash;          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />&ndash;&gt;
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="packageFiles"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="5"
            :on-change="packageFilesChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
&lt;!&ndash;        <el-form-item label="创建时间" prop="createDate">
          <el-date-picker clearable
            v-model="form.createDate"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>&ndash;&gt;
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>-->

    <div v-show="open">
      <el-page-header @back="goBack">
      </el-page-header>
      <el-divider></el-divider>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="课程名称:" prop="courseName">
          <el-col :span="8">
            <el-input v-model="form.courseName" placeholder="请输入课程名称"/>
          </el-col>
        </el-form-item>
        <el-form-item label="课程简介:" prop="courseDescription">
          <el-col :span="8">
            <el-input v-model="form.courseDescription" type="textarea" placeholder="请输入内容" :rows="5"/>
          </el-col>
        </el-form-item>
        <el-form-item label="课程封面:">
          <!--          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />-->
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="coverList"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="1"
            :on-change="fileChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="课程体系:">
          <!--          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />-->
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="systemFiles"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="5"
            :on-change="systemFilesChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="课程效果:">
          <!--          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />-->
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="effectFiles"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="5"
            :on-change="effectFilesChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="材料包展示:">
          <!--          <el-input v-model="form.courseCover" placeholder="请输入课程封面" />-->
          <el-upload
            action="#"
            list-type="picture-card"
            :file-list="packageFiles"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :auto-upload="false"
            accept=".png,.jpg,.jpeg,.PNG"
            :limit="5"
            :on-change="packageFilesChange"
          >
            <div style="display: flex; align-items: center; flex-direction: column;width: 148px;height: 148px;justify-items: center;justify-content: center;">
              <i class="el-icon-plus"></i>
              <span style="display: inline-block;width:100%;height:10px;line-height:10px;">点击上传</span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="课程目录:" prop="menuList">
          <div v-for="(item,index) in form.menuList" :key="index">
            <el-col :span="8" >
              <span>{{index}}.</span>
              <el-input v-model="item.menuName"  placeholder="请输入目录内容"/>
            </el-col>
            <el-col :span="2" >
              <i class="el-icon-circle-plus-outline mr3" @click="addTableSecondRow()" v-show="index === form.menuList.length"></i>
              <i class="el-icon-remove-outline" v-if="form.menuList.length>1" @click="removeLastRow()"></i>
            </el-col>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { listCourses, getCourses, delCourses, addCourses, updateCourses } from "@/api/youke/courses";

export default {
  name: "Courses",
  data() {
    return {
      // 按钮loading
      buttonLoading: false,
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
      coursesList: [],
      // 弹出层标题
      title: "",
      detailTitle: "",
      // 是否显示弹出层
      open: false,
      openDetail: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseName: undefined,
        courseDescription: undefined,
        courseCover: undefined,
        createDate: undefined,
        updateDate: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseName: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
        courseDescription: [
          { required: true, message: "课程简介不能为空", trigger: "blur" }
        ],
      },
      coverList:[],
      systemFiles:[],
      effectFiles:[],
      packageFiles:[],
      dialogImageUrl: '',
      dialogVisible: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourses(this.queryParams).then(response => {
        this.coursesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancelDeatil() {
      this.openDetail = false;
    },
    goBack() {
      this.open = false;
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        courseName: undefined,
        courseDescription: undefined,
        courseCover: undefined,
        createDate: undefined,
        updateDate: undefined,
        delFlag: undefined,
        createBy: undefined,
        updateBy: undefined,
        effectFiles: [],
        systemFiles: [],
        packageFiles: [],
        menuList: [
          {
            no: 1,
            menuName: ''
          }
        ],
      };
      this.coverList = [];
      this.effectFiles = [];
      this.systemFiles = [];
      this.packageFiles = [];
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
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids
      getCourses(id).then(response => {
        this.loading = false;
        this.form = response.data;
        //图片数据回填
        this.coverList = [{uid: this.form.courseCover,name: '', url: this.form.courseCover}]
        this.systemFiles = this.form.systemFiles.map(t => {
          var img = {uid: t.id, name: t.fileName, url: t.fileUrl}
          return img
        })
        this.effectFiles = this.form.effectFiles.map(t => {
          var img = {uid: t.id, name: t.fileName, url: t.fileUrl}
          return img
        })
        this.packageFiles = this.form.packageFiles.map(t => {
          var img = {uid: t.id, name: t.fileName, url: t.fileUrl}
          return img
        })
        if(this.form.menuList.length == 0) {
          this.addTableSecondRow();
        }
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formData = new FormData();
          //拼接图片
          this.coverList.forEach(file => {
            formData.append("coverfiles", file.raw);
          })
          this.systemFiles.forEach(file => {
            formData.append("systemFiles", file.raw);
          })
          this.effectFiles.forEach(file => {
            formData.append("effectFiles", file.raw);
          })
          this.packageFiles.forEach(file => {
            formData.append("packageFiles", file.raw);
          })
/*          this.secFileList.forEach(file => {
            formData.append("secFiles", file.raw);
            console.log("file",file.raw)
          })
          this.thirdFileList.forEach(file => {
            formData.append("thirdFiles", file.raw);
            console.log("file",file.raw)
          })*/
          const blob = new Blob([JSON.stringify(this.form)],
            {type: 'application/json;charset=utf-8'});
          formData.append("bo",blob)
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateCourses(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addCourses(formData).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除课程编号为"' + ids + '"的数据项？').then(() => {
        this.loading = true;
        return delCourses(ids);
      }).then(() => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    handleDetail(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids
      getCourses(id).then(response => {
        this.loading = false;
        this.form = response.data;
        //图片数据回填
        this.coverList = [{uid: this.form.courseCover,name: '', url: this.form.courseCover}]
        this.systemFiles = this.form.systemFiles.map(t => {
          var img = {uid: t.id, name: t.fileName, url: t.fileUrl}
          return img
        })
        this.effectFiles = this.form.effectFiles.map(t => {
          var img = {uid: t.id, name: t.fileName, url: t.fileUrl}
          return img
        })
        this.packageFiles = this.form.packageFiles.map(t => {
          var img = {uid: t.id, name: t.fileName, url: t.fileUrl}
          return img
        })
        this.openDetail = true;
        this.detailTitle = this.form.courseName + "详情";
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('yk/courses/export', {
        ...this.queryParams
      }, `courses_${new Date().getTime()}.xlsx`)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleRemove(file, fileList) {
      //let list = this.formRealData.imageList.filter(item => item.url !== file.url);
      //this.formRealData.imageList = list;
      this.form.systemFiles = this.form.systemFiles.filter(item => item.url !== file.url);
      this.form.effectFiles = this.form.effectFiles.filter(item => item.url !== file.url);
      this.form.packageFiles = this.form.packageFiles.filter(item => item.url !== file.url);
    },
    fileChange(file,fileList){
      // this.fileList.push(file.raw)
      this.coverList=fileList
    },
    effectFilesChange(file,fileList){
      // this.fileList.push(file.raw)
      this.effectFiles=fileList
    },
    systemFilesChange(file,fileList){
      // this.fileList.push(file.raw)
      this.systemFiles=fileList
    },
    packageFilesChange(file,fileList){
      // this.fileList.push(file.raw)
      this.packageFiles=fileList
    },
    addTableSecondRow() {
      this.form.menuList.push({
        sort: this.form.menuList.length +1,
        menuName: ''
      });
    },
    removeLastRow(param) {
      this.form.menuList.pop();
    },
  }
};
</script>
