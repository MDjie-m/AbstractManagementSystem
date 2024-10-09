<template>
  <div class="section-container stock-wrapper">
    <div class="left-panel">
      <div v-for="item in categories" :class="{selected:item===currentCategory}">
        {{ item.goodsCategoryName }}
      </div>
    </div>
    <div class="right-panel">
      <div v-show="item ===currentCategory" v-for="item in categories">
        <el-table :data="item.goodsStocks">
          <el-table-column label="商品名称" prop="goodsName">
            <template v-slot="scope">
              <div style="display: flex;align-items: center">
                <image-preview :src="scope.row.goodsImg" :width="50" :height="50"/>
                <div style="margin-left: 10px">{{ scope.row.goodsName }}</div>
              </div>

            </template>
          </el-table-column>
          <el-table-column label="库存" prop="total"/>
          <el-table-column label="变更"    prop="changeCount">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.changeCount" placeholder="请输入数量"
                               :min="-99999" :max="99999"
                               :step="1"/>
            </template>
          </el-table-column>
          <el-table-column label="备注" header-align="center"  width="400px" prop="changeCount">
            <template slot-scope="scope">
              <textarea maxlength="200" style="width: 100%" v-model="scope.row.remark"/>
            </template>
          </el-table-column>
        </el-table>
      </div>

    </div>

  </div>
</template>
<script>
import {listCategoryStock} from "@/api/cashier/goods";

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
    queryCategories() {
      listCategoryStock({}).then(res => {
        this.categories = (res.data || []).map(p => {
          p.goodsStocks.forEach(s => {
            s.changeCount = 0;
            s.remark = ''
          })
          return p;
        });
        this.currentCategory = this.categories[0];
      })
    }
  }
}
</script>
<style lang="scss" scoped src="./index.scss">

</style>
