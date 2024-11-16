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
const routeParams = ref(router.currentRoute.value.query);

const params = ref({
  params: "105b0f77fd24654d4eebc434e9",
  region: "default",
  screenName: routeParams.value.screenName,
});
const queryString = new URLSearchParams(params.value).toString();

const src = ref();
src.value = `http://www.thingjs.com/p/5f90d9f265e39081db80a17c?${queryString}`;
</script>
