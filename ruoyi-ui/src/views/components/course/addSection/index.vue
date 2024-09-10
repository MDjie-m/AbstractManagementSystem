<template>
    <!-- 添加或修改章节对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body @close="$emit('close')">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="章节编号" prop="sectionId">
          <el-input v-model="form.sectionId" placeholder="请输入章节编号" />
        </el-form-item>
        <el-form-item label="章节题目" prop="topic">
          <el-input v-model="form.topic" placeholder="请输入章节题目" />
        </el-form-item>
        <el-form-item label="章节时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入章节时长" />
        </el-form-item>
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
        <el-form-item label="章节内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="内容链接">
          <file-upload v-model="form.contentUrl" :fileType="['mp4', 'avi']" :fileSize="100"/>
        </el-form-item>
        <el-form-item label="课程编号" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程编号" disabled />
        </el-form-item>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
</template>

<script>
import { addSection } from "@/api/course/section";
export default {
  name: "AddSection",
  dicts: ['course_section_type', 'course_section_content_type'],
  props: ['addSectionOpen', 'addSectionCourseId'],
  data() {
    return {
      // 弹出层标题
      title: "添加关联章节",
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sectionId: [
          { required: true, message: "章节编号不能为空", trigger: "blur" }
        ],
        topic: [
          { required: true, message: "章节题目不能为空", trigger: "blur" }
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
      }
    }
  },
  methods: {
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addSection(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
          });
        }

      });
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
  },
  created() {
  },
  watch: {
    addSectionOpen() {
      this.open = this.addSectionOpen
    },
    addSectionCourseId() {
      this.form.courseId = this.addSectionCourseId
      console.log('this.form.courseId: ' + this.form.courseId)
    }
  }
}
</script>

<style>

</style>
