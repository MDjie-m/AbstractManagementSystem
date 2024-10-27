<template>
  <el-dialog title="邀请家长" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <span>邀请家长加入</span>
    <span>加入学校，在微信中登录报名课后服务课程</span>
    <img src="" alt="">
  </el-dialog>
</template>
<script>
export default {
  name: "InviteF",
  components: {
  },
  data() {
    return {
      dialogVisible: false,
    }
  },
  methods: {
    handleClose() {
      this.dialogVisible = false
      this.$emit('close')
    },
  }
}
</script>