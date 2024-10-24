<template>
  <div class=" section-container line-up-container">
    <el-tabs v-model="currentTitle">
      <el-tab-pane :label="place.label" name="basic" :key="'line_up'+place.value" :name="place.value"
                   v-for="place in placeTypeList" style="height:90%;overflow-y:auto;overflow-x:hidden;">
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
              <el-button class="num-item-btn" type="primary" circle @click="onPrintClick(place.value,place.label,index)">
                打印
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
                  type="danger" :key="'btn'+item.value" v-for="item in placeTypeList">
          <el-button type="primary" style="margin-left: 20px" round @click="onCreateClick(item.value,item.label)"
          >{{ item.label }}
          </el-button>
        </el-badge>
      </el-col>
    </el-row>

  </div>
</template>
<script>
import {callPCMethod, DeviceMethodNames} from "@/utils/pcCommunication";
import {listLineUp, listPlaceTypeAll, saveLineUp} from "@/api/cashier/desk";
import {parseTime} from "@/utils/ruoyi";

const PreNum = ["A", "B", "C", "D", "E", "F"]
export default {
  props: ['storeName'],
  dicts: ['store_desk_status',  ],
  data() {

    return {
      placeTypeList:[],
      lineUpInfo: {},
      loading: false,
      currentTitle: '0'
    }
  },
  created() {
    this.getList();
    this.getPlaceTypeList();
  },
  methods: {
    getPlaceTypeList(){
      listPlaceTypeAll().then(res=>{
        this.placeTypeList=res.data||[];
        this.currentTitle=this.placeTypeList[0].value;
      })
    },
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
    onPrintClick(type, label, idx) {
      let tempLineInfo = this.lineUpInfo[type].numList[idx];
      let num = tempLineInfo.num;
      let lines = [{
        beforeLine: 1,
        content: this.storeName || '',
        align: true,
        fontSize: 8,
        afterLine: 1,
        splitLine: 1,
      }, {
        beforeLine: 1,
        content: "您的号码是",
        align: true,
        afterLine: 1,
      }, {
        content: num,
        fontSize: 20,
        align: true,
        afterLine: 1,
        splitLine: 1,
      }, {
        content: `台桌位置:${label}\r\n取号时间:${tempLineInfo.createTime}\r\n前方等待:${idx}`,

        afterLine: 3,
      } ]
      callPCMethod(DeviceMethodNames.Print, {
        lines: lines
      }).then(res => {
        if(!res?.data?.success){
          this.$modal.msgWarning(res?.data?.msg||'打印失败')
        }
        console.log(res)
      })
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
    onCreateClick(type,label) {
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
        this.onPrintClick(type,label,tempLineInfo.numList.length-1)
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
