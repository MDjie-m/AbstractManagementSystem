<template>
  <div>
    <el-divider content-position="left">广告条目清单</el-divider>
    <draggable :list="advertItemList" :animation="340" @end="sortEnd" group="questionList" handle=".option-drag">
      <el-row :key="item.id" v-for="item in advertItemList" style="margin: 0 20px" class="option-drag">
        <el-tag closable :disable-transitions="false" style="width: 100%; height: auto" @close="handleClose(item)"
                 @click="queryItemDetail(item.id)">
          {{ item.sortNo }}：{{ item. itemTitle}}
        </el-tag>
        <div style="height: 20px"></div>
      </el-row>
    </draggable>
    <el-row style="margin: 0 20px">
      <el-input style="width: 100%" class="input-new-tag" v-if="newItemVisible" v-model="newItemValue"
                ref="saveTagInput" size="small" @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm">
      </el-input>
      <el-button v-else class="button-new-tag" style="width: 100%; margin-left: 0" size="small" @click="showInput">+
        新条目
      </el-button>
    </el-row>

    <el-drawer :visible.sync="itemDetailOpen" size="55%" style="padding-right:35%;z-index:1005"
               :wrapperClosable="false" :with-header="true" title="条目设置" append-to-body>
<!--      <div v-for="item in advertItemList" :key="item.id" style="margin:0 20px">
        <div v-if="item.id == itemId">-->
          <template>
            <el-form ref="form" :model="item" :rules="rules" label-width="100px">
              <el-form-item label="条目标题" prop="itemTitle">
                <el-input v-model="item.itemTitle" />
              </el-form-item>
              <el-form-item label="条目描述" prop="itemText">
                <el-input v-model="item.itemText" />
              </el-form-item>
              <el-form-item label="排序号" prop="sortNo">
                <el-input v-model="item.sortNo" />
              </el-form-item>
              <el-form-item label="条目图片" prop="itemImg">
<!--                <my-cropper v-model="item.itemImg" sizeTip="宽172px 高172px" :extraData="extraData" :width="172" :height="172"/>-->
                <image-upload v-model="item.itemImg" :extraData="extraData"  :isShowTip="true" class="image-upload"/>
              </el-form-item>
              <el-form-item label="跳转页面" prop="toPage">
                <el-input v-model="item.toPage" />
              </el-form-item>
              <el-form-item label="外网url" prop="toUrl">
                <el-input v-model="item.toUrl" />
              </el-form-item>
              <el-form-item label="携带参数" prop="param">
                <el-input v-model="item.param" />
              </el-form-item>
              <el-form-item label="关联对象id" prop="relationObjectId">
                <el-input v-model="item.relationObjectId" />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input v-model="item.remark" />
              </el-form-item>
            </el-form>

            <div style="display: flex; justify-content: center;">
              <el-button type="primary" @click="updateItem">确 定</el-button>
              <el-button @click="cancel">取 消</el-button>
            </div>

<!--            <el-divider content-position="left">条目标题</el-divider>
            <el-input v-model="item.itemTitle" type="textarea" @blur="updateQuestion(item)"/>
            <el-input-number v-model="item.sortNo" controls-position="right" size="small" :min="0"/>-->




<!--            <el-divider content-position="left">选项</el-divider>
            <draggable :list="options" :animation="340" @end="optionsSort" group="optionList" handle=".option-drag">
              <div v-for="(option, index) in options" :key="index" class="select-item">
                <div class="select-line-icon option-drag">
                  <i class="el-icon-s-operation"/>
                </div>
                <el-input disabled :value="'序号:' + option.sort" size="small" style="width: 80px;text-align: center"/>
                <el-input style="width: 350px;" v-model="option.name" placeholder="选项名" size="small" @blur="updateOption(option)"/>
                <el-input-number v-if="gaugeType !== 3" v-model="option.value" controls-position="right" size="small"
                                 :min="0" :max="20" label="描述文字" @change="updateOption(option)"></el-input-number>
                <el-select v-if="gaugeType === 3" @change="updateOption(option)" size="small" style="width: 150px"
                           v-model="option.lat" placeholder="纬度" clearable>
                  <el-option
                    v-for="item in gaugeMbti"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>

                <image-upload  v-if="gaugeType === 8" :isShowTip="false" v-model="option.imgUrl" @input="updateOption(option)" :extraData="extraData"/>

                <div class="close-btn select-line-icon">
                  <i class="el-icon-remove-outline" style="color:#777" v-if="index==0 || index==1"/>
                  <i class="el-icon-remove-outline" v-else @click="removeOption(option); options.splice(index, 1);"/>
                </div>
              </div>
            </draggable>
            <div style="margin-left: 20px">
              <el-button style="padding-bottom: 0" icon="el-icon-circle-plus-outline" type="text"
                         @click="addQuestionOption({ gaugeQuestionsId:item.id,name: '选项', value: 1,sort: options.length+1})">
                添加选项
              </el-button>
            </div>
            <el-divider content-position="left">选择类型</el-divider>
            <div>
              <el-radio-group v-model="item.selectType" size="medium" @change="updateQuestion(item)">
                <el-radio-button v-for="(selectTypeOption, index) in selectTypeOptions" :key="index"
                                 :label="selectTypeOption.value">{{ selectTypeOption.label }}
                </el-radio-button>
              </el-radio-group>
            </div>
            <el-divider content-position="left">错题解析</el-divider>
            <el-input v-model="item.remark" maxlength="255" show-word-limit type="textarea" @blur="updateQuestion(item)"></el-input>
            <el-divider content-position="left">问题图示</el-divider>
            <image-upload v-model="item.img" @input="updateQuestion(item)" :extraData="extraData"/>-->
          </template>

<!--        </div>
      </div>-->
    </el-drawer>
  </div>

</template>

<script>
import {
  listItem,
  getItem,
  addItem,
  delItem,
  updateItem,
} from "@/api/advert/advert.js";

import {
  listOptions,
  getOptions,
  addOptions,
  delOptions,
  updateOptions,
} from "@/api/gauge/options.js";

import draggable from "vuedraggable";

export default {
  components: {
    draggable,
  },
  props: {
    advertNo:{
      type: String,
      default: null,
    },
    gaugeId: {
      type: Number,
      default: null,
    },
    gaugeType: {
      type: Number,
      default: null,
    }
  },
  data() {
    return {
      extraData: {
        module: this.$constants['picModules'][1],
        type: this.$constants['picTypes'][8]
      },
      gaugeMbti: this.$constants.gaugeMbti,
      gaugeMbtiRes: this.$constants.gaugeMbtiRes,
      gaugeQuestionList: [],
      advertItemList: [],
      options: [],
      newItemVisible: false,
      newQuestionVisible: false,
      newQuestionValue: "",
      newItemValue: "",
      itemId: "",
      item: {},
      itemDetailOpen: false,
      formData: {
        selectType: 0,
      },
      rules: {
        selectType: [{
          required: true,
          message: '选择类型不能为空',
          trigger: 'change'
        }],
      },
      selectTypeOptions: [{
        "label": "单选",
        "value": 0
      }, {
        "label": "多选",
        "value": 1
      }],
    };
  },
  watch: {
    advertNo: {
      handler(val) {
        if (val !== this.currentValue) {
          this.getAdvertItemList(val);
        }
      },
      deep: true,
      immediate: true,
    },
  },
  computed: {},
  methods: {
    async getAdvertItemList(value) {
      this.loading = true;
      let response = await listItem({advertNo: value});
      this.advertItemList = response.rows;
      this.total = response.total;
      //this.loading;
      // this.questionId = response.rows[0].id;
      // await this.queryOptionList(this.questionId);
    },

    async addItem(question) {
      await addItem(question);
      let response = await listItem({advertNo: this.advertNo});
      this.advertItemList = response.rows;
      this.itemId = response.rows[response.rows.length - 1].id;
      /*let data = {
        gaugeQuestionsId: this.questionId,
        name: "选项1",
        value: 1,
        sort: this.options.length + 1
      };*/
      // await this.addQuestionOption(data);
      // data.name = "选项2";
      // data.sort=this.options.length+1
      // await this.addQuestionOption(data);
    },
    async addQuestionOption(data) {
      await addOptions(data);
      let res = await listOptions({gaugeQuestionsId: this.questionId});
      this.options = res.rows;
    },


    async queryItemDetail(itemId) {
      this.itemId = itemId;
      let itemData = await getItem(itemId);
      console.log(itemData.data,"**************************************")
      this.item = itemData.data;
      this.itemDetailOpen = true;
    },

    async queryItemList(advertNo) {
      this.advertNo = advertNo;
      let options = await listItem({advertNo: advertNo});
      this.options = options.rows;
      this.itemDetailOpen = true;
    },
    updateItem() {
      updateItem(this.item).then((res) => {
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.itemDetailOpen = false;
        this.getAdvertItemList(this.advertNo);
      });
    },
    updateOption(option) {
      updateOptions(option);
    },
    sortEnd() {
      //to,from,item,clone,oldIndex,newIndex
      for (let i = 0; i < this.gaugeQuestionList.length; i++) {
        this.gaugeQuestionList[i].no = i + 1;
        updateItem(this.gaugeQuestionList[i]);
      }
    },
    optionsSort() {
      for (let i = 0; i < this.options.length; i++) {
        this.options[i].sort = i + 1;
        updateOptions(this.options[i]);
      }
    },
    handleClose(tag) {
      // console.log(this.gaugeQuestionList)
      this.advertItemList.splice(this.advertItemList.indexOf(tag), 1);
      delItem(tag.id).then((res) => {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
        // console.log(res, '******')
      })
    },

    showInput() {
      this.newItemVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.newItemValue;

      if (inputValue) {
        let data = {
          itemTitle: inputValue,
          advertNo: this.advertNo,
          sortNo: this.advertItemList.length + 1,
        };
        this.addItem(data);
      }
      this.newItemVisible = false;
      this.newItemValue = "";
    },
    removeOption(data) {
      // console.log(data);
      delOptions(data.id);
    },
    onOpen() {
    },
    onClose() {
      this.$refs['elForm'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
    },
    handleConfirm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        this.close()
      })
    },
    cancel() {
      this.itemDetailOpen = false
      this.item = {}
    }
  },
};
</script>

<style lang="scss" scoped>
.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 32px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}

.select-item {
  display: flex;
  border: 1px dashed #fff;
  box-sizing: border-box;

  & .close-btn {
    cursor: pointer;
    color: #f56c6c;
  }

  & .el-input + .el-input {
    margin-left: 4px;
  }

  & .el-input + .el-input-number {
    margin-left: 4px;
  }
}

.select-item + .select-item {
  margin-top: 4px;
}

.select-item.sortable-chosen {
  border: 1px dashed #409eff;
}

.select-line-icon {
  line-height: 32px;
  font-size: 22px;
  padding: 0 4px;
  color: #777;
}

.option-drag {
  cursor: move;
}

::v-deep .select-item .component-upload-image {
  width: 32px;
  height: 32px;
}
::v-deep .select-item .el-upload--picture-card {
  width: 32px;
  height: 32px;
}
::v-deep .select-item .el-upload {
  width: 32px;
  height: 32px;
  line-height: 32px;
}
::v-deep .select-item .el-upload-list--picture-card .el-upload-list__item {
  width: 32px;
  height: 32px;
}
::v-deep .select-item .el-upload-list--picture-card .el-upload-list__item-thumbnail {
  width: 32px;
  height: 32px;
  line-height: 32px;
}
::v-deep .select-item .avatar {
  width: 32px;
  height: 32px;
}
::v-deep .select-item .el-upload__tip {
  margin: 0;
}
</style>


