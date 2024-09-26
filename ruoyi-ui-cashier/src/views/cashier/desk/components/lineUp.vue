<template>
  <div class=" section-container line-up-container">
    <el-tabs v-model="currentTitle">
      <el-tab-pane :label="place.label" name="basic" :key="'line_up'+place.value" :name="place.value"
                   v-for="place in dict.type.store_desk_place" style="height:90%;overflow-y:auto;overflow-x:hidden;">
        <el-scrollbar class="right-scrollbar">
          <div class="num-container">
            <div class="num-item" :key="place.value+'line_item_key'+item.num"
                 v-for="(item,index) in getLineUpList(place.value).numList">
              <div class="num-item-text">
                {{ item.num }}
              </div>
              <div class="num-item-time">
                <span v-if="index===0">{{ getWaitTime(item) }}   </span>
              </div>
              <el-button class="num-item-btn" circle @click="onSpeechClick(item.num)"> 呼叫</el-button>
              <el-button class="num-item-btn" type="success" circle @click="onRemoveNumClick(place.value,index)"> 用号
              </el-button>
              <el-button class="num-item-btn" type="danger" circle @click="onRemoveNumClick(place.value,index)"> 过号
              </el-button>

            </div>
          </div>
        </el-scrollbar>
      </el-tab-pane>

    </el-tabs>
    <el-row class="line-up-footer">
      <el-col style="text-align: center;">
        <el-badge :value="getLineUpList(item.value).numList.length" :hidden="!getLineUpList(item.value).numList.length"
                  class="icon-tip"
                  type="danger" :key="'btn'+item.value" v-for="item in dict.type.store_desk_place">
          <el-button type="primary" style="margin-left: 20px" round @click="onCreateClick(item.value)"
          >{{ item.label }}
          </el-button>
        </el-badge>
      </el-col>
    </el-row>

  </div>
</template>
<script>
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
import {listLineUp, saveLineUp} from "@/api/cashier/desk";
import {parseTime} from "@/utils/ruoyi";

const PreNum = ["A", "B", "C", "D", "E", "F"]
export default {
  dicts: ['store_desk_status', 'store_desk_type', 'store_desk_place'],
  data() {
    return {
      lineUpInfo: {},
      loading: false,
      currentTitle: '0'
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listLineUp().then(p => {
        this.lineUpInfo = p.data || {}
      }).finally(this.closeLoading)
    },
    getWaitTime(item) {
      let minute = Math.abs(this.$time(item.createTime).diff(new Date(), 'minute'));
      return minute > 0 ? `${minute}分钟` : '1分钟';
    },
    onRemoveNumClick(type, idx) {
      if (this.loading) {
        return
      }
      this.loading = true;
      let lineUp = JSON.parse(JSON.stringify(this.lineUpInfo));
      let tempLineInfo = lineUp[type];
      tempLineInfo.numList.splice(idx, 1);
      if (tempLineInfo.numList.length === 0) {
        tempLineInfo.currentNum = 0;
      }
      saveLineUp(lineUp).then(p => {
        this.lineUpInfo = lineUp;
      }).finally(this.closeLoading)
    },
    onSpeechClick(num) {
      callPCMethod(DeviceMethodNames.Speech, [{content: num, emphasis: 1}, {content: "号客户请到前台"}])
    },
    onCreateClick(type) {
      this.currentTitle = type;
      if (this.loading) {
        return
      }
      this.loading = true;
      this.getLineUpList(type);
      let lineUp = JSON.parse(JSON.stringify(this.lineUpInfo));

      let tempLineInfo = lineUp[type];

      let newNum = `${PreNum[parseInt(type)]}${tempLineInfo.currentNum + 1}`;
      let newItem = {
        num: newNum,
        createTime: this.$time().format("YYYY-MM-DD HH:mm:ss")
      };
      tempLineInfo.numList.push(newItem);
      tempLineInfo.currentNum += 1;
      saveLineUp(lineUp).then(p => {
        this.lineUpInfo = lineUp;
      }).finally(this.closeLoading)

    },
    closeLoading() {
      this.loading = false
    },
    getLineUpList(type) {
      let info = this.lineUpInfo[type] || {
        currentNum: 0,
        numList: []
      }
      this.lineUpInfo[type] = info;
      return info;
    }
  }

}
</script>

<style scoped lang="scss" src="./lineUp.scss">

</style>
