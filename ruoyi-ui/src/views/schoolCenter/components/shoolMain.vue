<template>
  <div class="shoolMain">
    <el-tabs v-model="activeName">
      <el-tab-pane label="学校（6个校区,0人）" name="0">
        <template v-if="step===''">
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button type="primary" size="mini" @click="addSchoolArea">添加校区</el-button>
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
        <addShoolMain v-else-if="step==='1'" @closeAdd="closeAdd" @next="next"/>
        <addAll v-else-if="step==='2'" @font="addSchoolArea"/>
        <inviteF ref="inviteFRef" />
        <editSort ref="editSortRef" />
        <editShool ref="editShoolRef" />
        
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import addShoolMain from './addShoolMain'
import inviteF from './inviteF'
import editSort from './editSort'
import editShool from './editShool'
import addAll from './addAll'
export default {
  name: "SchoolMain",
  components: {
    addShoolMain,
    inviteF,
    editSort,
    editShool,
    addAll
  },
  data() {
    return {
      activeName: '0',
      loading: false,
      shoolList: [{ sort: 1 }],
      step: '',
    }
  },
  methods: {
    //编辑
    edit(row) {
      this.$refs.editShoolRef.dialogVisible = true
    },
    // 删除
    del(row) {
      this.$confirm('是否要删除该校区?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 邀请家长
    invite() {
      this.$refs.inviteFRef.dialogVisible = true
    },
    // 添加校区
    addSchoolArea() {
      this.step = '1'
    },
    next() {
      this.step = '2'
    },
    // 取消新增
    closeAdd() {
      this.step = ''
    },
    // 编辑排序
    editSort() {
      this.$refs.editSortRef.dialogVisible = true
    }
  }
}
</script>

<style scoped lang="scss">
.shoolMain {
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