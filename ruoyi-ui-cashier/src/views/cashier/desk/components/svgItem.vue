
<template>
  <div class="sub-svg-item" :class="{'btn-able':btnAble}"  >
    <el-badge :value="badge" :hidden="!badge" class="icon-tip"
              type="primary">
      <svg-icon :icon-class="svgIcon"/>
    </el-badge>
    <div class="sub-svg-item-text" v-if="label">
      {{  label }}
    </div>
  </div>
</template>
<script  >
export default {
  props:["badge","svgIcon","label","btnAble"]
}

</script>

<style scoped lang="scss">
@import '@/assets/styles/variables.scss';

.sub-svg-item {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 5px;

  ::v-deep.el-badge__content {
    // font-size: 10px!important;
    right: 8px !important;
  }
  &.btn-able{
    cursor: pointer;
  }
  .svg-icon {
    font-size: 20px;
  }

  &-text {
    color: $sub-text-color;
    font-size: 12px;
  }
}
</style>
