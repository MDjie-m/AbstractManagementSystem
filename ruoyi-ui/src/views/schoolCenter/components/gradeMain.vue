<template>
  <div class="gradeMain">
    <el-tabs v-model="activeName">
      <el-tab-pane label="班级（0人）" name="0">
        <template>
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button type="primary" size="mini" @click="addSchoolArea">添加学生</el-button>
              <el-button type="primary" size="mini" @click="invite">邀请家长</el-button>
            </el-col>
          </el-row>

          <el-table v-loading="loading" :data="shoolList">
            <el-table-column label="校区名称" prop="noticeTitle" />
            <el-table-column label="学生总数" prop="noticeType"></el-table-column>
            <el-table-column label="家长总数" prop="sender" />
            <el-table-column label="学段学生统计" prop="sendTime" />
            <el-table-column label="排序" prop="sort">
              <template slot-scope="scope">
                <el-button type="text" @click="editSort(scope.row)">{{scope.row.sort}}</el-button>
              </template>
            </el-table-column>
            <el-table-column label="操作" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini" type="text" @click="edit(scope.row)">编辑</el-button>
                <el-button size="mini" type="text" @click="del(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-tab-pane>
    </el-tabs>
   </div>
</template>

<script>
export default {
  name: "GradeMain",
  components: {
    
  },
  data() {
    return {
      activeName: '0',
      loading: false,
      shoolList: [{ sort: 1 }],
    }
  },
  methods: {
  }
}
</script>

<style scoped lang="scss">
.gradeMain {
  ::v-deep .el-tabs__item {
    font-size: 20px;
    color: rgba(0, 0, 0, 0.8);
  }
  ::v-deep .is-active {
    font-weight: 600;
  }
  ::v-deep .el-tabs__active-bar {
    background-color: rgba(0, 0, 0, 0.8);
  }
}
</style>