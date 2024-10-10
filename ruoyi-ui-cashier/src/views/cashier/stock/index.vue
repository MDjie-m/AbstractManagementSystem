<template>
  <div class="section-container stock-wrapper">
    <div class="left-panel">
      <div @click="onCategoryClick(item)" v-for="item in categories" :class="{selected:item===currentCategory}">
        {{ item.goodsCategoryName }}
      </div>
    </div>
    <div class="right-panel">
      <div class="table-box" v-if="currentCategory">
        <el-table :data="currentCategory.goodsStocks" style="width: 100%;" height="100%">
          <el-table-column label="商品名称" prop="goodsName">
            <template v-slot="scope">
              <div style="display: flex;align-items: center">
                <image-preview :src="scope.row.goodsImg" :width="50" :height="50"/>
                <div style="margin-left: 10px">{{ scope.row.goodsName }}</div>
              </div>

            </template>
          </el-table-column>
          <el-table-column label="库存" prop="total"/>
          <el-table-column label="变更" prop="changeCount">
            <template slot-scope="scope">
              <el-input-number size="small" v-model="scope.row.changeCount" placeholder="请输入数量"
                               :min="-scope.row.total" :max="99999"
                               :step="1"/>
            </template>
          </el-table-column>
          <el-table-column label="备注" header-align="center" width="400px" prop="changeCount">
            <template slot-scope="scope">
              <el-input size="small" maxlength="200" style="width: 100%" v-model="scope.row.remark"/>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="btn-container">
        <el-button type="primary" @click="onCheckClick">盘点</el-button>
      </div>
    </div>

  </div>
</template>
<script>
import {checkStock, listCategoryStock} from "@/api/cashier/goods";

export default {
  data() {
    return {
      currentCategory: null,
      categories: [],
    }
  },
  mounted() {
    this.queryCategories();
  },
  methods: {
    onCategoryClick(item) {
      this.currentCategory = item
    },
    onCheckClick() {
      let dataList = [];

      this.categories.forEach(p => {

        dataList = dataList.concat(p.goodsStocks.filter(p => p.changeCount))
      })
      if (!dataList.length) {
        this.$modal.msgWarning("没有要盘点的商品");
        return
      }
      this.$modal.confirm("确认盘点?").then(() => [
        checkStock(dataList).then(res => {
          if (res.data?.failList?.length === 0) {
            this.queryCategories();
            return this.$modal.msgSuccess("盘点成功")
          } else if (res.data?.failList?.length > 0) {
            this.queryCategories()
            return this.$message({
              duration: 5000,
              dangerouslyUseHTMLString: true,
              type: 'error',
              message: ["<div> 其他商品已盘点成功，以下是盘点失败商品：</div> <br/>"].concat(res.data?.failList.map(p => `<div style="padding-bottom: 5px">${p.msg}</div> `)).join("")
            });
          } else {
            this.$modal.msgWarning("盘点异常")
          }
        })
      ])
    },
    queryCategories() {
      return listCategoryStock({}).then(res => {
        this.categories = (res.data || []).map(p => {
          p.goodsStocks.forEach(s => {
            s.changeCount = 0;
            s.remark = ''
          })
          return p;
        });
        if (this.currentCategory) {
          this.currentCategory = this.categories.find(p => p.goodsCategoryId === this.currentCategory.goodsCategoryId)
        } else {
          this.currentCategory = this.categories[0];
        }
        return Promise.resolve();
      })

    }
  }
}
</script>
<style lang="scss" scoped src="./index.scss">

</style>
