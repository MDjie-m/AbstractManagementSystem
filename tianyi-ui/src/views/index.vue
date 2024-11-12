<template>
  <div class="app-container home">
    <span>欢迎使用{{ title }}</span>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.8",
      title: ''
    };
  },
  mounted() {
    this.title = process.env.VUE_APP_TITLE
  }
};
</script>

<style scoped lang="scss">
.home {
  height: calc(100vh);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
}
</style>

