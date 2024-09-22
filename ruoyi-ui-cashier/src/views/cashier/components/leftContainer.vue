<template>
  <div class="left-panel">
    <div class="left-header" v-if="$slots.title"> <slot name="title"/>  </div>
    <div class="store-info"  v-if="!hideStoreInfo">
      <i class="el-icon-refresh-right store-info-btn" @click="onRefreshClick"></i>
      <div class="store-info-icon">
        <svg-icon icon-class="store"/>
      </div>
      <div class="store-info-title">
        {{ storeName }}
      </div>
    </div>
    <div class="left-slot" >
      <slot/>
    </div>

  </div>
</template>
<script>
export default {
  emits: ["onRefreshClick"],
  props: ["hideStoreInfo"],
  data() {
    return {
      storeName: '',
    }
  },
  created() {
    this.storeName = this.$store.getters.stores[0]?.storeName || ''
  },
  methods: {
    onRefreshClick() {
      this.$emit("onRefreshClick")
    }
  }
}
</script>

<style scoped lang="scss">
@import '@/assets/styles/variables.scss';
.left-slot{
  display: flex;
  flex: 1;
  flex-direction: column;
}
.left-header{
  background: #fff;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, .2);
}
.store-info {
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  padding-top: 30px;
  padding-bottom: 30px;

  &-btn {
    position: absolute;
    font-size: 30px;
    right: 10px;
    top: 10px;
    cursor: pointer;

    :hover::before {
      color: $light-blue;
    }
  }

  &-icon {
    font-size: 30px;
    margin: 20px;


  }

  &-title {
    font-weight: 500;
    font-size: 30px;
  }
}
</style>
