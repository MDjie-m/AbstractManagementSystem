<template>
  <div>
    <el-divider content-position="left">问题列表</el-divider>
    <draggable :list="gaugeQuestionList" :animation="340" @end="sortEnd" group="questionList" handle=".option-drag">
      <el-row :key="tag.id" v-for="tag in gaugeQuestionList" style="margin: 0 20px" class="option-drag">
        <el-tag closable :disable-transitions="false" style="width: 100%; height: auto" @close="handleClose(tag)"
                :type="tag.id===questionId?'success':''" @click="queryOptionList(tag.id)">
          {{ tag.no }}：{{ tag.title }}
        </el-tag>
        <div style="height: 20px"></div>
      </el-row>
    </draggable>
    <el-row style="margin: 0 20px">
      <el-input style="width: 100%" class="input-new-tag" v-if="newQuestionVisible" v-model="newQuestionValue"
                ref="saveTagInput" size="small" @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm">
      </el-input>
      <el-button v-else class="button-new-tag" style="width: 100%; margin-left: 0" size="small" @click="showInput">+
        新问题
      </el-button>
    </el-row>

    <el-drawer :visible.sync="questionOptionOpen" size="55%" style="padding-right:35%;z-index:1005"
               :wrapperClosable="false" :with-header="true" title="问题设置" append-to-body>
      <div v-for="item in gaugeQuestionList" :key="item.id" style="margin:0 20px">
        <div v-if="item.id == questionId">
          <template>
            <el-divider content-position="left">问题</el-divider>
            <el-input v-model="item.title" type="textarea" @blur="updateQuestion(item)"></el-input>
            <el-divider content-position="left">选项</el-divider>
            <draggable :list="options" :animation="340" @end="optionsSort" group="optionList" handle=".option-drag">
              <div v-for="(option, index) in options" :key="index" class="select-item">
                <div class="select-line-icon option-drag">
                  <i class="el-icon-s-operation"/>
                </div>
                <el-input disabled :value="'序号:' + option.sort" size="small" style="width: 80px;text-align: center"/>
                <el-input style="width: 350px;" v-model="option.name" placeholder="选项名" size="small" @blur="updateOption(option)"/>
                <el-input-number v-if="gaugeType !== 3" v-model="option.value" controls-position="right" size="small"
                                 :min="-999" :max="20" label="描述文字" @change="updateOption(option)"></el-input-number>
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
            <image-upload v-model="item.img" @input="updateQuestion(item)" :extraData="extraData"/>
          </template>
          <template>


            <!-- <el-divider content-position="left">效果</el-divider>
            <el-tag type="info">{{item.title}}</el-tag>
            <el-form>
              <el-radio-group>
                <div v-for="(option, index) in options" :key="index" class="select-item">
                  <el-radio :label="option.name" style="margin-top:10px"></el-radio>
                </div>
              </el-radio-group>
            </el-form> -->
          </template>
        </div>
      </div>
    </el-drawer>
  </div>

</template>

<script>
import {
  listQuestions,
  getQuestions,
  addQuestions,
  delQuestions,
  updateQuestions,
} from "@/api/gauge/questions.js";

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
      options: [],
      newQuestionVisible: false,
      newQuestionValue: "",
      questionId: 0,
      questionOptionOpen: false,
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
    gaugeId: {
      handler(val) {
        if (val !== this.currentValue) {
          this.getQuestions(val);
        }
      },
      deep: true,
      immediate: true,
    },
  },
  computed: {},
  methods: {
    async getQuestions(value) {
      this.loading = true;
      let response = await listQuestions({gaugeId: value});
      this.gaugeQuestionList = response.rows;
      this.total = response.total;
      //this.loading;
      // this.questionId = response.rows[0].id;
      // await this.queryOptionList(this.questionId);
    },
    async addQuestion(question) {
      await addQuestions(question);
      let response = await listQuestions({gaugeId: this.gaugeId});
      this.gaugeQuestionList = response.rows;
      this.questionId = response.rows[response.rows.length - 1].id;
      let data = {
        gaugeQuestionsId: this.questionId,
        name: "选项1",
        value: 1,
        sort: this.options.length + 1
      };
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

    async queryOptionList(questionId) {
      this.questionId = questionId;
      let options = await listOptions({gaugeQuestionsId: questionId});
      this.options = options.rows;
      this.questionOptionOpen = true;
    },
    updateQuestion(question) {
      updateQuestions(question);
    },
    updateOption(option) {
      updateOptions(option);
    },
    sortEnd() {
      //to,from,item,clone,oldIndex,newIndex
      for (let i = 0; i < this.gaugeQuestionList.length; i++) {
        this.gaugeQuestionList[i].no = i + 1;
        updateQuestions(this.gaugeQuestionList[i]);
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
      this.gaugeQuestionList.splice(this.gaugeQuestionList.indexOf(tag), 1);
      delQuestions(tag.id).then((res) => {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
        // console.log(res, '******')
      })
    },

    showInput() {
      this.newQuestionVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.newQuestionValue;

      if (inputValue) {
        let data = {
          title: inputValue,
          gaugeId: this.gaugeId,
          no: this.gaugeQuestionList.length + 1,
        };
        this.addQuestion(data);
      }
      this.newQuestionVisible = false;
      this.newQuestionValue = "";
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


