<template>
  <el-dialog title="会员查找" class="custom-dialog" :visible.sync="visible" width="700px" append-to-body
             :close-on-click-modal="false"
             :close-on-press-escape="false" :show-close="false">
    <el-form ref="form" label-width="120px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="会员:">
            <el-autocomplete
              v-model="memberId"
              :fetch-suggestions="querySearchAsync"
              placeholder="请输入内容手机号或者姓名"

            ></el-autocomplete>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
    <div slot="footer" class="dialog-footer" v-loading="loading">
      <el-button type="primary" @click="onOkClick()">确认</el-button>
      <el-button @click="onCancel">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  emits:["onOk"],
  props:['visible'],
  data() {
    return {
      memberId:null,
      memberList:[]
    }
  },
  methods: {
    querySearchAsync(queryString,cb){

    },
    onOkClick(){
      if(!this.memberId){
        return this.$modal.msgWarning("请选择会员")
      }
      let item  =  this.memberList.find(p=>p.memberId===this.memberId)
      this.$emit("onOk",item)
    },
    onCancel(){
      this.$emit("update:visible",false)
    }
  }
}
</script>

<style scoped lang="scss">

</style>
