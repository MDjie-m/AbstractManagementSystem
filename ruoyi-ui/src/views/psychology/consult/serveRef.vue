<template>
  <div>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
      <el-form-item label="服务名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <div class="table-div">
      <i class="el-icon-warning" style="margin-left: 16px;margin-right: 8px;color: #1890FF"></i>已选择  <span style="color: #1890FF">{{ this.ids.length }}</span>  项
    </div>
    <el-table v-loading="loading" :data="list" :row-key="getRowKeys" @selection-change="handleSelectionChange">
      <el-table-column type="selection" :reserve-selection="true" width="55" align="center" />
      <el-table-column label="服务信息" align="center" prop="name" width="150px"/>
      <el-table-column label="售价(元)" align="center" prop="price"/>
      <el-table-column label="级别" align="center" prop="level">
        <template slot-scope="scope">
          {{ levelList.find(i => i.value === scope.row.level).label }}
        </template>
      </el-table-column>
      <el-table-column label="服务对象" align="center" prop="serviceObject">
        <template slot-scope="scope">
          {{ serviceObjectList.find(i => i.value === scope.row.serviceObject).label }}
        </template>
      </el-table-column>
      <el-table-column label="服务类型" align="center" prop="type">
        <template slot-scope="scope">
          {{ typeList.find(i => i.value === scope.row.type).label }}
        </template>
      </el-table-column>
      <el-table-column label="服务次数" align="center" prop="num"/>
      <el-table-column label="服务时长(分)" align="center" prop="time"/>
<!--      <el-table-column label="状态" align="center" prop="status">-->
<!--        <template slot-scope="scope">-->
<!--          {{ scope.row.status === '0' ? '上架' : '下架'}}-->
<!--        </template>-->
<!--      </el-table-column>-->
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
import { listServeConfig } from "@/api/psychology/serveConfig";

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
      typeList: this.$constants.typeList,
      levelList: this.$constants.levelList,
      serviceObjectList: this.$constants.serviceObjectList,
      // 查询参数
      queryParams: {
        name: '',
        pageNum: 1,
        pageSize: 10,
        orderByColumn: 'status',
        status: 0,
        isAsc: 'ascending',
        nId: ''
      },
    }
  },
  created() {
    console.log(this.id)
    this.getList()
  },
  methods: {
    getRowKeys(row) {
      return row.id
    },
      // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
      console.log(this.ids)
      this.$emit('setServe', this.ids)
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 查询咨询服务配置列表 */
    getList() {
      this.loading = true;
      this.queryParams.nId = this.id
      listServeConfig(this.queryParams).then(response => {
        this.list = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }
  }
}
</script>

<style scoped>
.table-div {
  height: 40px;
  line-height: 40px;
  border-radius: 5px;
  background-color: rgba(230, 247, 255, 1);
  border: 1px solid rgba(186, 231, 255, 1);
  color: rgba(16, 16, 16, 1);
  font-size: 14px;
  margin-bottom: 10px;
}
</style>
