<template>

  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick" :hide-store-info="isCreating">
      <div class="shop-car" v-if="!isCreating">
        <SvgItem @click.native="onCreateOrderClick" class="shop-car-icon" svg-icon="shop_car" label="创建订单"
                 :btnAble="true"/>
      </div>
      <template v-if="isCreating">
        <div class="order-info-box">

        </div>
        <div class="order-btn-container">
          f
        </div>
      </template>

    </left-container>
    <div class="right-panel">
      <div class="section-container">
        <el-row class="tag-container">

          <el-tag
            type="primary"
            @click="onChangePanel(true)"
            :effect="isGoodsPanel?'dark':'plain'"
          >
            商品
          </el-tag>
          <el-tag
            type="primary"
            @click="onChangePanel(false)"
            :effect="!isGoodsPanel?'dark':'plain'"
          >
            教练
          </el-tag>
        </el-row>

        <el-row class="tag-container" v-if="!isGoodsPanel">

          <el-tag
            type="primary"
            @click="onChooseTutorStatus(null)"
            :effect="tutorStatus===null?'dark':'plain'"
          >
            全部
          </el-tag>
          <el-tag
            type="primary" v-for="item in dict.type.store_tutor_work_status"
            @click="onChooseTutorStatus(parseInt(item.value))"
            :effect="tutorStatus===parseInt(item.value)?'dark':'plain'"
          >
            {{ item.label }}
          </el-tag>
        </el-row>
      </div>


      <div class="  section-container buy-box">

        <div class="buy-container" v-show="isGoodsPanel">
          <el-card @click.native="onDeskClick(item)" class="some-item" :class="{'selected':item.selected}"
                   v-for="item in  goodsList">
            <div>
              <image-preview class="item-img" :src="item.goodsImg"/>
            </div>
            <div>
              <div class="some-item-name"> {{ item.goodsName }}</div>
              <div class="some-item-price"> {{ item.price }}元</div>

            </div>
            <i class="el-icon-plus item-btn" v-if="isCreating"></i>

          </el-card>
        </div>
        <div class="buy-container" v-show="!isGoodsPanel">
          <el-card @click.native="onDeskClick(item)" class="some-item" :class="{'selected':item.selected}"
                   v-for="item in  tutorList">
            <div>
              <div class="some-item-name"> {{ item.realName }}</div>
              <div class="some-item-price"> {{ item.price }}元/分钟</div>
            </div>
            <i class="el-icon-plus item-btn" v-if="isCreating"></i>

          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {listAllGoods} from "@/api/cashier/goods";
import {listAllTutor} from "@/api/cashier/tutor";
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";

export default {
  components: {SvgItem, LeftContainer},
  dicts: ['store_tutor_work_status', 'store_desk_type', 'store_desk_place'],
  data() {
    return {
      isGoodsPanel: true,
      tutorStatus: null,
      goodsList: [],
      tutorList: [],
      isCreating: false,
      order: {
        orderTutorTimes: [],
        orderGoods: [],
      }
    }
  },
  mounted() {
    this.getGoodsList();
    this.getTutorList();
  },
  methods: {
    onCreateOrderClick() {
      this.isCreating = true;
      this.order = {
        orderTutorTimes: [],
        orderGoods: [],
      }
    },
    getGoodsList() {
      listAllGoods(null).then(res => {
        this.goodsList = res.data || []
      })
    },
    getTutorList() {
      listAllTutor({workStatus: this.tutorStatus}).then(res => {
        this.tutorList = res.data
      })
    },
    onChooseTutorStatus(val) {
      this.tutorStatus = val;
    },
    onChangePanel(val) {
      this.isGoodsPanel = val;
    },
  }
}
</script>
e
<style scoped lang="scss" src="./buy.scss">

</style>
<style scoped lang="scss">
::v-deep.shop-car {
  margin-top: 50px;

  &-icon {
    .svg-icon {
      font-size: 40px !important;
    }

  }
}
</style>
