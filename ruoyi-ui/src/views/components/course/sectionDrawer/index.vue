<template>
  <el-drawer
    title="关联章节管理"
    size="50%"
    :visible.sync="drawerOpenLocal"
    :with-header="false"
    @close="$emit('close')"
    >
    <div class="app-container">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['course:section:add']"
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
            v-hasPermi="['course:section:edit']"
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
            v-hasPermi="['course:section:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['course:section:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :search="false" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <!-- 章节列表 -->
      <el-table v-loading="loading" :data="sectionList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="章节编号" align="center" prop="sectionId" />
        <el-table-column label="章节题目" align="center" prop="topic" />
        <el-table-column label="章节类型" align="center" prop="type">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.course_section_type" :value="scope.row.type"/>
          </template>
        </el-table-column>
        <el-table-column label="内容类型" align="center" prop="contentType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.course_section_content_type" :value="scope.row.contentType"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['course:section:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['course:section:remove']"
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

      <!-- 添加或修改章节对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="章节题目" prop="topic">
            <el-input v-model="form.topic" placeholder="请输入章节题目" />
          </el-form-item>
          <el-form-item label="章节编号" prop="sectionId">
            <el-input v-model="form.sectionId" placeholder="请输入章节编号" />
          </el-form-item>
          <el-form-item label="章节时长" prop="duration">
            <el-input v-model.number="form.duration" placeholder="请输入章节时长（以秒为单位的数字）" />
          </el-form-item>

          <el-row>
            <el-col :span="12">
              <el-form-item label="章节类型" prop="type">
                <el-select v-model="form.type" placeholder="请选择章节类型">
                  <el-option
                    v-for="dict in dict.type.course_section_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="内容类型" prop="contentType">
                <el-select v-model="form.contentType" placeholder="请选择内容类型">
                  <el-option
                    v-for="dict in dict.type.course_section_content_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="内容链接" prop="contentUrl">
            <file-upload-sts
              v-model="form.contentUrl"
              :limit="1"
              :fileType="fileTypes"
              :fileSize="1024"
              :extraData="extraData"
              @input="handleFileUploadSuccess"
              @deleteFile="handleFileDeleteSuccess"
             />
          </el-form-item>

          <el-form-item label="章节内容">
            <editor v-model="form.content" :min-height="192" :extraData="extraData"/>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel" v-if="!hasDeleted">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </el-drawer>
</template>

<script>
import { listSection, getSection, delSection, addSection, updateSection } from "@/api/course/section";
import FileUploadSts from "@/components/FileUploadSts";

export default {
  name: "SectionDrawer",
  components: {FileUploadSts},
  dicts: ['course_section_type', 'course_section_content_type'],
  props: ['drawOpen', 'drawCourse'],
  data() {
    return {
      fileTypes: ["mp4", "avi", "rmvb", "mp3", "wma", "rm", "rmvb", "flv", "mpg", "mov", "mkv"],
      extraData: {
        module: this.$constants['picModules'][0],
        type: this.$constants['picTypes'][3]
      },
      drawerOpenLocal: false,
      course: null, // 关联的课程
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: false,
      // 总条数
      total: 0,
      // 章节表格数据
      sectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sectionId: null,
        topic: null,
        duration: null,
        type: null,
        courseId: null,
        contentType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sectionId: [
        ],
        topic: [
          { required: true, message: "章节题目不能为空", trigger: "blur" }
        ],
        duration: [
          { required: true, message: "章节时长不能为空", trigger: "blur" },
        ],
        type: [
          { required: true, message: "章节类型不能为空", trigger: "change" }
        ],
        contentUrl: [
          { required: true, message: "内容链接不能为空", trigger: "blur" }
        ],
        courseId: [
          { required: true, message: "课程编号不能为空", trigger: "blur" }
        ],
      },
      hasDeleted: false, // 有上传文件删除时，
    }
  },
  methods: {
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      // 章节类型默认值
      this.form.type = parseInt(this.dict.type.course_section_type && this.dict.type.course_section_type[0].value)
      if (this.course.payType === 1) {
        // 免费课所有章节免费
        this.form.type = 2
      }
      // 内容类型默认值
      this.form.contentType = parseInt(this.dict.type.course_section_content_type && this.dict.type.course_section_content_type[0].value)

      this.open = true;
      this.title = "添加章节";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSection(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改章节";
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除章节编号为"' + ids + '"的数据项？').then(function() {
        return delSection(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('course/section/export', {
        ...this.queryParams
      }, `section_${new Date().getTime()}.xlsx`)
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 查询章节列表 */
    getList() {
      this.loading = true;
      listSection(this.queryParams).then(response => {
        this.sectionList = response.rows;
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
        sectionId: null,
        topic: null,
        duration: null,
        type: null,
        content: null,
        contentUrl: null,
        contentType: null
      };
      this.resetForm("form");
    },
    /** 提交按钮 */
    submitForm() {
      this.form.courseId = this.course.id

      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSection(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 文件上传完成后回调，计算视频或音频时长
    handleFileUploadSuccess(fileUrl) {
      console.log('fileUrl: ', fileUrl)
      let audioElement = new Audio(fileUrl);
      let duration;
      let fun = (duration) => {
        this.form.duration = parseInt(duration) || 0
      }
      //下面需要注意的是在监听loadedmetadata绑定的事件中对duration直接进行赋值是无效的，需要在fun回调函数中进行赋值
      audioElement.addEventListener("loadedmetadata", function() { //音频/视频的元数据已加载时，会发生 loadedmetadata 事件
        duration = audioElement.duration; //时长以秒作为单位
        fun(parseFloat(duration).toFixed(1))
      });
    },
    handleFileDeleteSuccess() {
      this.hasDeleted = true
    }
  },
  created() {

  },
  watch: {
    drawOpen() {
      this.drawerOpenLocal = this.drawOpen
      if (this.drawerOpenLocal) {
        this.course = this.drawCourse
        this.queryParams.courseId = this.course.id
        this.form.courseId = this.course.id
        console.log('this.form.courseId: ' + this.form.courseId)
        this.getList();
      }
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep {
}
</style>
