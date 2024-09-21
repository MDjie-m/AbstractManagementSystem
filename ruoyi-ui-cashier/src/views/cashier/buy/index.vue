<template>

  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick" :hide-store-info="isCreating">

      <div class="shop-car" v-if="!isCreating">
        <SvgItem @click.native="onCreateOrderClick" class="shop-car-icon" svg-icon="shop_car" label="创建订单"
                 :btnAble="true"/>
      </div>

      <div class="order-info-box" v-show="isCreating">
        <div class="order-info-box">
          <template v-if="order.orderDeskTimes">
            <div class="time-box-wrapper" v-for="(item,idx) in order.orderDeskTimes">
              <div class="num-box">
                {{ item.idx }}
              </div>
              <div class="time-box">
                <div class="time-box-row">

                  <div> {{ item.storeDesk.deskName }}({{ item.storeDesk.deskNum }})</div>
                  <dict-tag style="margin-left: 0" :options="dict.type.store_desk_status"
                            :value=" item.status"></dict-tag>


                </div>
                <div class="time-box-row">
                  <div>{{ item.price }} 元/分钟</div>
                  <div class="item-center">{{ item.totalTime }}分钟</div>
                  <div class="item-right">
                    <div>{{ item.totalAmount }}</div>
                    <div class="due-amount">{{ item.totalAmountDue }}</div>
                  </div>
                </div>
                <div class="time-box-row">
                  <el-tag type="info" size="mini">开始</el-tag>
                  <div>{{ item.startTime|timeFormat('MM-DD HH:mm') }}</div>
                </div>
                <div class="time-box-row">
                  <el-tag type="info" size="mini">结束</el-tag>
                  <div>{{ item.endTime|timeFormat('MM-DD HH:mm') }}</div>
                </div>
              </div>
            </div>
          </template>
          <template v-if="order.orderTutorTimes">
            <div class="time-box-wrapper" v-for="(item,idx) in order.orderTutorTimes" :key="'order-t'+idx">
              <div class="num-box">
                {{ item.idx }}
              </div>
              <div class="time-box">
                <div class="time-box-row">
                  <image-preview class="goods-img" :preview="false" :src="item.storeTutor.userImg"/>
                  <div> {{ item.storeTutor.realName }}</div>
                  <dict-tag style="margin-left: 0" :options="dict.type.store_desk_status"
                            :value=" item.status"></dict-tag>


                </div>
                <div class="time-box-row">
                  <div>{{ item.price }} 元/分钟</div>
                  <div class="item-center">{{ item.totalTime }}分钟</div>
                  <div class="item-right">
                    <div>{{ item.totalAmount }}</div>
                    <div class="due-amount">{{ item.totalAmountDue }}</div>
                  </div>
                </div>
                <div class="time-box-row">
                  <el-tag type="info" size="mini">开始</el-tag>
                  <div>{{ item.startTime|timeFormat('MM-DD HH:mm') }}</div>
                </div>
                <div class="time-box-row">
                  <el-tag type="info" size="mini">结束</el-tag>
                  <div>{{ item.endTime|timeFormat('MM-DD HH:mm') }}</div>
                </div>
              </div>
              <div class="btn-box">

                <i v-if="!item.orderTutorTimeId" class="el-icon-remove" @click="onRemoveTutorClick(item)"
                   style="color:#ff4949"></i>
              </div>
            </div>
          </template>
          <template v-if="order.orderGoods">
            <div class="time-box-wrapper" v-for="(item,idx) in order.orderGoods" :key="'g'+idx">
              <div class="num-box">
                {{ item.idx }}
              </div>
              <div class="time-box">
                <div class="time-box-row">
                  <div class="item-column">
                    <image-preview class="goods-img" :preview="false" :src="item.goods.goodsImg"/>
                    <div>{{ item.goods.goodsName }}</div>
                  </div>

                  <div class="item-center  "> {{ item.price }}元 *
                    <el-input-number class="mini-input" @change="onGoodsNumChanged(item)" :min="1" :max="999"
                                     type="primary" size="mini" v-model="item.num"></el-input-number>
                  </div>
                  <div class="item-right">
                    <div>{{ item.totalAmount }}</div>
                    <div class="due-amount">{{ item.totalAmountDue }}</div>
                  </div>
                  <div class="item-right">
                    <i class="el-icon-remove" @click="onRemoveGoodsClick(item)" style="color:#ff4949"></i>
                  </div>
                </div>
              </div>
            </div>
          </template>
        </div>
      </div>


      <div class="order_btn_container" v-show="isCreating">
        <el-button @click="onCancelOrderClick">取消</el-button>
        <el-button type="primary" @click="onOkOrderClick">完成选购</el-button>
      </div>
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
          <el-card class="some-item" :class="{'selected':item.selected}"
                   v-for="item in  goodsList">
            <div>
              <image-preview class="item-img" :src="item.goodsImg"/>
            </div>
            <div>
              <div class="some-item-name"> {{ item.goodsName }}</div>
              <div class="some-item-price"> {{ item.price }}元</div>

            </div>
            <i class="el-icon-plus item-btn" v-if="isCreating" @click="addGoodsToOrder(item)"></i>

          </el-card>
        </div>
        <div class="buy-container" v-show="!isGoodsPanel">
          <el-card class="some-item" :class="{'selected':item.selected}"
                   v-for="item in  tutorList">
            <div class="item-status" :class="`item-status-${item.status}`"></div>
            <image-preview class="item-img big" :src="item.userImg"/>
            <div>
              <div class="some-item-name"> {{ item.realName }}</div>
              <div class="some-item-price"> {{ item.price }}元/分钟</div>
            </div>
            <i class="el-icon-plus item-btn" v-if="isCreating &&item.workStatus===TutorWorkStatus.Wait" @click="addTutorToOrder(item)"></i>

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
import {CalcTimeStatus,TutorWorkStatus} from "@/views/cashier/components/constant";

export default {
  components: {SvgItem, LeftContainer},
  dicts: ['store_tutor_work_status', 'store_desk_type', 'store_desk_place'],
  data() {
    return {
      CalcTimeStatus:CalcTimeStatus,
      TutorWorkStatus:TutorWorkStatus,
      isGoodsPanel: true,
      tutorStatus: null,
      goodsList: [],
      tutorList: [],
      isCreating: false,
      order: {
        orderTutorTimes: [],
        orderGoods: [],
        orderDestTimes: [],
      }
    }
  },
  mounted() {
    this.getGoodsList();
    this.getTutorList();
  },
  methods: {
    onRefreshClick() {

    },
    onCancelOrderClick() {
      this.isCreating = false;
      this.order = {
        orderTutorTimes: [],
        orderGoods: [],
        orderDestTimes: [],
      }
    },
    onOkOrderClick() {

    },
    onGoodsNumChanged(item) {
      item.totalAmount = item.price * item.num;
      item.totalAmountDue = item.price * item.num;
    },
    onRemoveTutorClick(item) {
      this.order.orderTutorTimes.splice(this.order.orderGoods.indexOf(item), 1);
    },
    onRemoveGoodsClick(item) {
      this.order.orderGoods.splice(this.order.orderGoods.indexOf(item), 1);
    },
    onCreateOrderClick() {
      this.isCreating = true;
      this.order = {
        orderTutorTimes: [],
        orderDestTimes: [],
        orderGoods: [],
      }
    },
    addTutorToOrder(item) {
      let findItem = this.order.orderTutorTimes.find(p => p.tutorId === item.storeTutorId && p.status !== CalcTimeStatus.Stop);
      if (findItem) {
        return this.$modal.msgWarning("教练已在订单内，无需重复添加")
      }
      findItem = {
        tutorId: item.storeTutorId,
        price: item.price,
        totalAmount: 0.00,
        totalAmountDue: 0.00,
        totalTime:0,
        status: CalcTimeStatus.Busy,
        startTime: this.$time().format("YYYY-MM-DD HH:mm:ss"),
        storeTutor: item,
        idx: 0,
      }
      this.order.orderTutorTimes.push(findItem)

      this.sortIdx();
    },
    addGoodsToOrder(item) {
      let findItem = this.order.orderGoods.find(p => p.goodsId === item.goodsId);
      if (!findItem) {
        findItem = {
          goodsId: item.goodsId,
          price: item.price,
          totalAmount: 0.00,
          totalAmountDue: 0.00,
          num: 0,
          goods: item,
          idx: 0,
        }
        this.order.orderGoods.push(findItem)
      }
      findItem.num += 1;
      findItem.totalAmount = findItem.price * findItem.num;
      findItem.totalAmountDue = findItem.price * findItem.num;
      this.sortIdx();
    },
    sortIdx() {
      let idx = 0;
      [this.order.orderDestTimes || [], this.order.orderTutorTimes || [], this.order.orderGoods || []].forEach(list => {
        list.forEach(p => {
          idx++;
          p.idx = idx;
        })
      })
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

.order_btn_container {
  display: flex;
  flex-direction: row;
  padding: 10px;

  *:first-child {
    margin-left: auto !important;
  }
}

</style>
