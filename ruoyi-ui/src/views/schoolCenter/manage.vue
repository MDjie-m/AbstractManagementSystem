<template>
  <div class="school">
    <div class="school-left">
      <el-input v-model="input" placeholder="请输入内容" clearable></el-input>
      <el-tree :data="data" class="elTree" :props="defaultProps" @node-click="nodeClick" :highlight-current="true"></el-tree>
    </div>
    <div class="school-right">
      <!-- <shoolMain /> -->
      <!-- <areaMain /> -->
      <!-- <periodMain /> -->
      <!-- <classMain /> -->
      <gradeMain />
    </div>
  </div>
</template>
<script>
import shoolMain from './components/shoolMain'
import addShoolMain from './components/addShoolMain'
import areaMain from './components/areaMain'
import periodMain from './components/periodMain'
import classMain from './components/classMain'
import gradeMain from './components/gradeMain'
export default {
  name: "SchoolManage",
  components: {
    shoolMain,
    addShoolMain,
    areaMain,
    periodMain,
    classMain,
    gradeMain
  },
  data() {
    return {
      data: [{
        label: '一级 1',
        children: [{
          label: '二级 1-1',
          children: [{
            label: '三级 1-1-1'
          }]
        }]
      }, {
        label: '一级 2',
        children: [{
          label: '二级 2-1',
          children: [{
            label: '三级 2-1-1'
          }]
        }, {
          label: '二级 2-2',
          children: [{
            label: '三级 2-2-1'
          }]
        }]
      }
      ],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      step: '',
      input: '',
      form: {}, //维护的新增对象
    }
  },
  methods: {
    nodeClick(e) {

    }
  }
}
</script>

<style scoped lang="scss">
.school {
  margin: 15px;
  padding: 20px;
  border-radius: 4px;
  background-color: #fff;
  height: calc(100vh - 115px);
  display: flex;

  &-left {
    width: 320px;
    height: 100%;
    padding-right: 20px;
    box-sizing: border-box;
    border-right: 1px solid #dcdee2;
    .elTree {
      margin-top: 20px;
    }
  }
  &-right {
    width: calc(100% - 320px);
    padding-left: 20px;
  }
}
</style>