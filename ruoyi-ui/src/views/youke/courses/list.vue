<template>
  <div class="app-container">
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

    <el-row :gutter="20" v-show="showCard">
      <el-col :span="5" v-for="(item, index) in coursesList" :key="item.id">
        <el-card :body-style="{ padding: '0px' }">
          <el-image :src="item.courseCover" @click="showDetail(item.id)"
                    v-if="item.courseCover !== '' && item.courseCover !== null"></el-image>
          <el-image :src="require('@/assets/images/noImage.png')" @click="showDetail(item.id)"
                    v-if="item.courseCover === '' || item.courseCover === null"></el-image>
          <div style="padding: 14px;">
            <span>{{ item.courseName }}</span>
          </div>
          <div>
            <span>{{ item.createTime }}</span>
          </div>
          <div class="bottom">
            <el-button type="text" class="right-button" @click="showDetail(item.id)">详情</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <pagination
      v-show="total>0"
      v-if="showCard"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"/>


    <!-- 课程详情页 -->
    <div v-show="!showCard">
      <el-page-header @back="goBack">
      </el-page-header>
      <el-divider></el-divider>
      <el-row>
        <el-col :span="8">
          <div class="grid-content bg-purple">
          <el-image :src="form.courseCover" ></el-image>
            <el-image :src="form.courseCover" v-if="form.courseCover !== '' && form.courseCover !== null"></el-image>
            <el-image :src="require('@/assets/images/noImage.png')" v-if="form.courseCover === '' || form.courseCover === null"></el-image>
          </div>
        </el-col>
        <el-col :span="16">
          <div class="grid-content bg-purple-light">
            <div>
              <h3>{{ form.courseName }}</h3>
            </div>
            <div>
              <span>课程简介：{{form.courseDescription}}</span>
            </div>
            <div>
              <span>适用年纪：</span>
            </div>
            <div>
              <span>创建人：{{form.createBy}}</span>
            </div>
            <div>
              <span>创建时间：{{form.createTime}}</span>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-tabs tab-position="top">
        <el-tab-pane label="课程体系" name="system">
          <el-image v-for="file in systemFiles" :key="file.url" :src="file.url"></el-image>
        </el-tab-pane >
        <el-tab-pane label="课程效果" name="record">
          <el-image v-for="file in effectFiles" :key="file.url" :src="file.url"></el-image>
        </el-tab-pane>
        <el-tab-pane label="材料包展示" name="package">
          <el-image v-for="file in packageFiles" :key="file.url" :src="file.url"></el-image>
        </el-tab-pane>
        <el-tab-pane label="课程目录" name="menu">
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { listCourses, getCourses} from "@/api/youke/courses";

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
      showCard: true,
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
      coverList:[],
      systemFiles:[],
      effectFiles:[],
      packageFiles:[],
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
    goBack() {
      this.showSearch = true;
      this.showCard = true
    },
    showDetail(id) {
      this.loading = true;
      this.reset();
      getCourses(id).then(response => {
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
        this.showSearch = false;
        this.showCard = false;
        this.loading = false;
      });
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
  }
};
</script>
