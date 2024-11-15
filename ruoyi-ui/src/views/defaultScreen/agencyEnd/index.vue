<template>
  <div class="screen">
    <iframe
      :src="src"
      name="iframe"
      style="width: 100vw; height: 100vh"
    ></iframe>
  </div>
</template>
<script setup>
import { getToken } from "@/utils/auth";
import { useRouter } from "vue-router";
const router = useRouter();

const parameter = router.currentRoute.value.path.split("/");
console.log("完整地址:", router.currentRoute.value.path, parameter);

const params = ref({
  params: "105b0f77fd24654d4eebc434e9",
  //地区
  region: parameter[1],
  // 平台（机构/移动/政府）
  platform: parameter[2],
  // 功能类别
  category: parameter[3],
  // 大屏名称
  screenName: parameter[4],
});
const queryString = new URLSearchParams(params.value).toString();

const src = ref();
src.value = `http://www.thingjs.com/p/5f90d9f265e39081db80a17c?${queryString}`;
</script>
