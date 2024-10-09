<template>
  <div>
    <el-table v-loading="loading" :data="list">
      <el-table-column label="序号" type="index" align="center"/>
      <el-table-column label="服务名称" align="center" prop="name"/>
      <el-table-column label="咨询形式" align="center" prop="mode">
        <template slot-scope="scope">
          {{ modeList.find(i => i.value === scope.row.mode).label }}
        </template>
      </el-table-column>
      <el-table-column label="服务类型" align="center" prop="type">
        <template slot-scope="scope">
          {{ typeList.find(i => i.value === scope.row.type).label }}
        </template>
      </el-table-column>
      <el-table-column label="销量" align="center" prop="sales"/>
      <el-table-column label="价格" align="center" prop="price"/>
      <el-table-column label="级别" align="center" prop="level">
        <template slot-scope="scope">
          {{ levelList.find(i => i.value === scope.row.level).label }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status === '0' ? '上架' : '下架'}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            style="color: red"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
            v-hasPermi="['psychology:consult:serveDelete']">
            解绑
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
  </div>
</template>

<script>
import { delConsultServeRef, listServeConfig } from "@/api/psychology/serveConfig";
export default {
  props: {
    id: {
      type: String
    },
  },
  data() {
    return {
      ids: [],
      list: [],
      total: 0,
      loading: false,
      modeList: this.$constants.modeList,
      typeList: this.$constants.typeList,
      levelList: this.$constants.levelList,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderByColumn: 'z_index',
        isAsc: 'descending',
        cId: ''
      },
    }
  },
  created() {
    console.log(this.id)
    this.getList()
  },
  methods: {
    /** 查询咨询服务配置列表 */
    getList() {
      this.loading = true;
      this.queryParams.cId = this.id
      listServeConfig(this.queryParams).then(response => {
        this.list = response.rows;
        this.total = response.total;
        console.log("====================",this.list)
        this.loading = false;
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const data = {
        serveId: row.id,
        consultId: this.id
      }
      this.$modal.confirm('确认要解绑服务"' + row.name + '"吗？').then(function() {
        return delConsultServeRef(data);
      }).then(() => {
        this.getList();
        this.$emit('setServe')
        this.$modal.msgSuccess("解绑成功");
      }).catch(() => {});
    },
  }
}
</script>

<style scoped>

</style>
