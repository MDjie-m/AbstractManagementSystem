<template>
  <div class="content-wrapper" v-if="visible">
    <div class="content-header">
      <div class="header-text">{{ title }}</div>
      <i class="el-icon-close header-close-btn" @click="onClose"></i>
    </div>

    <div class="content-box">
<!--      <div class="split-line"></div>-->
      <div class="slot-container">
        <slot/>
      </div>

    </div>
  </div>
</template>
<script>
export default {
  props: {
    title: {
      type: String,
      default: "",
    },
    visible: {
      type: Boolean,
      default: false
    },
  },
  methods: {
    onClose() {
      this.$emit("update:visible", false)
    }
  }
}
</script>

<style scoped lang="scss">
.content-wrapper {
  position: absolute;
  display: flex;
  flex-direction: column;
  left: 0;
  top: 0;
  flex: 1;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);

   .content-header,.content-box{
     box-shadow: inset 3px 1px 5px rgba(0, 21, 41, 0.18);
   }
  .content-header {
    height: 60px;
    margin-top: 60px;
    display: flex;


    background-color: #FFFFFF;
    align-items: center;
    padding-left: 20px;
    padding-right: 20px;

    .header-text {
      font-size: 25px;
    }

    .header-close-btn {
      margin-left: auto;
      cursor: pointer;
      font-size: 25px;
    }


  }

  .content-box {
    .split-line{
      border-bottom: 1px solid rgba(0,0,0,0.2);

      height: 1px;
      margin-left: 20px;
      margin-right: 20px;
    }

    background-color: #f3f3f4;
    display: flex;
    z-index: 20;
    width: 100%;
    flex-direction: column;
    flex: 1;
    height: calc(100% - 120px);
    overflow: hidden;
    .slot-container{
      padding: 20px;

      padding-top: 10px;
      overflow: hidden;
      display: flex;
      width: 100%;
      flex: 1;
    }
  }
}
</style>
