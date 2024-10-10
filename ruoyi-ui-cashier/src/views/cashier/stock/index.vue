<template>
  <div class="section-container stock-wrapper">
    <div class="left-panel">
      <div @click="onCategoryClick(item)" :key="'sdfsdfsfaccc'+item.goodsCategoryId" v-for="item in categories" :class="{selected:item===currentCategory}">
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
                               :min="scope.row.total?-scope.row.total:0" :max="99999"
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
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd(ChangeType.In,'入库')"
          v-hasPermi="['cashier:stock:edit']"
        >入库
        </el-button>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd(ChangeType.Out,'出库')"
          v-hasPermi="['cashier:stock:edit']"
        >出库
        </el-button>
        <el-button type="primary"     size="mini" v-hasPermi="['cashier:stock:edit']" icon="el-icon-edit" @click="onCheckClick">盘点
        </el-button>
      </div>
    </div>
    <custom-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="custom-form">
        <el-form-item label="商品名称" prop="goodsId">
          <el-select v-model="form.goodsId" placeholder="请选择设商品" filterable>
            <el-option
              v-for="dict in goodsList"
              :key="dict.goodsId"
              :label="dict.goodsName"
              :value="dict.goodsId"
            >
              <template>
                <div style="display:flex;flex-direction: row;   align-items: center ; justify-content: left">
                  <image-preview :src="dict.goodsImg" :width="25" :height="25" :preview="false"/>
                  <div style="margin-left: 10px">{{ dict.goodsName }}</div>
                </div>
              </template>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label="`${title}数量`" prop="changeCount">
          <el-input-number   v-model="form.changeCount" placeholder="请输入数量" :min="1" :max="999999" :step="1"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" :maxlength="200"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click=" open=false">取 消</el-button>
      </div>
    </custom-dialog>

  </div>
</template>
<script>
import {changeStock, checkStock, listCategoryStock} from "@/api/cashier/goods";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {ChangeType} from "@/views/cashier/components/constant";

export default {
  computed: {
    ChangeType() {
      return ChangeType
    }
  },

  components: {CustomDialog},
  data() {
    return {
      form: {},
      open: false,
      rules: {
        goodsId: [
          {required: true, message: '商品不能为空', trigger: 'blur'}
        ],
        changeCount: [
          {required: true, message: '数量不能为空', trigger: 'blur'}
        ]
      },
      title: '',
      currentCategory: null,
      goodsList: [],
      categories: [],
    }
  },
  mounted() {
    this.queryCategories();
  },
  methods: {
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          changeStock(this.form).then(response => {
            this.$modal.msgSuccess('操作成功')
            this.open = false
            this.queryCategories()
          })
        }
      })
    },
    reset() {
      this.form = {

        total:0,
        goodsId: null,
        changeType: null,
        changeCount: null,
        storeId: this.storeInfo?.storeId,
        remark: null
      }
      this.resetForm('form')
    },
    handleAdd(type, title, goodsId) {

      this.reset()
      this.form.changeType = type
      this.form.goodsId = goodsId
      this.open = true

      this.title = title
    },
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
        this.goodsList = [];
        this.categories = (res.data || []).map(p => {
          p.goodsStocks.forEach(s => {

            s.changeCount = 0;
            s.remark = '';
            this.goodsList.push(s);
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
