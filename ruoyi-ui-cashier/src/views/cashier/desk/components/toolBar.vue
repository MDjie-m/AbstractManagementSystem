<template>
  <div class="  section-container menu-container">
    <div class="menu-title">
      {{ title }}
      <div class="right-content">
        <slot name="titleRight"/>
      </div>
    </div>
    <div class="icon-container">
      <template v-if="!$slots.default">
        <div class="sub-item" v-for="item in menus" :key="item.label+'customMenu'">
          <el-badge :value="item.badge" :hidden="!item.badge" class="icon-tip"
                    type="primary">
            <svg-icon :icon-class="item.svgIcon" :class-name="item.className"/>
          </el-badge>
          <div class="sub-item-text">
            {{ item.label }}
          </div>
        </div>
      </template>
      <template v-else>
        <slot/>
      </template>
    </div>

  </div>
</template>
<script>
export default {
  props: {
    title: {type: String, default: ''},
    menus: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {}
  }

}
</script>
<style lang="scss" scoped>
@import '@/assets/styles/variables';

.menu-container {
  padding: 10px;

  display: flex;
  gap: 10px;
  flex-direction: column;

  .menu-title {
    height: 20px;
    color: $main-text-color;
    font-weight: bold;
    display: flex;
   align-items: center;

    .right-content {
      margin-left: auto;
    }
  }

  .icon-container {
    display: flex;
    flex-direction: row;
    flex: 1;
    flex-wrap: wrap;

    > * {
      width: 25%;
      margin-top: 10px;
      margin-bottom: 10px;
    }

    .sub-item {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      gap: 5px;

      ::v-deep.el-badge__content {
        // font-size: 10px!important;
        right: 8px !important;
      }

      .svg-icon {
        font-size: 20px;
      }

      &-text {
        color: $sub-text-color;
        font-size: 12px;
      }
    }
  }
}

</style>

