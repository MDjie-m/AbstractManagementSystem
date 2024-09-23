<template>
  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick">


    </left-container>
    <div class="right-panel">
      <div class="section-container">
        <el-input v-model="queryParams.keyword" maxlength="20" placeholder="请输入会员姓名或者手机号"></el-input>
      </div>
      <div class="section-container member-box">
      </div>
    </div>
  </div>
</template>
<script>
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";
import LeftContainer from "@/views/cashier/components/leftContainer.vue";

export default {
  components: {LeftContainer, SvgItem},
  data() {
    return {
      queryParams: {
        keyword: null
      }
    }
  },
  created() {

  },
  methods: {
    onRefreshClick() {

    }
  }

}
</script>
<style scoped lang="scss" src="./index.scss"/>
