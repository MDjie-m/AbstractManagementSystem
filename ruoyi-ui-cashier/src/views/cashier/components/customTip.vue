<template>
  <el-tooltip placement="top">
    <div slot="content">
      {{ content }}
    </div>
    <i class="el-icon-question" style="color:gray;margin: 5px"/>
  </el-tooltip>
</template>
<script>
export default {
  props: ['content']
}
</script>
