<template>
  <el-dialog title="会员查找" class="custom-dialog" :visible.sync="visible" width="450px" append-to-body
             :close-on-click-modal="false"
             :close-on-press-escape="false" :show-close="false">
    <el-form ref="form"  >

          <el-form-item  >
            <el-select
              v-model="memberId"

              filterable style="width: 400px"
              remote
              clearable autocomplete="off"
              reserve-keyword
              no-data-text="未找到相关会员"
              placeholder="请输入姓名或者手机号"
              :remote-method="querySearchAsync"
              :loading="loading">
              <el-option
                v-for="item in memberList"
                :key="item.memberId"
                :label="item.title"
                :value="item.memberId">
              </el-option>
            </el-select>
          </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer" v-loading="loading">
      <el-button type="primary" @click="onOkClick()">确认</el-button>
      <el-button @click="onCancel">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import {listMembers} from "@/api/cashier/member";

export default {
  emits: ["onOk"],
  props: ['visible'],
  data() {
    return {
      loading: false,
      memberId: null,
      memberList: []
    }
  },
  methods: {
    querySearchAsync(queryString) {
      this.loading = true;
      listMembers({
        pageIndex: 1,
        pageSize: 10,
        keyword: String(queryString).trim()
      }).then(res => {
        let list =res.rows || [];
        list.forEach(p=>{
          p.title=`${p.realName}/${p.mobile}`
        })
        this.memberList = res.rows || [];

      }).finally(() => this.loading = false)
    },
    onOkClick() {
      if (!this.memberId) {
        return this.$modal.msgWarning("请选择会员")
      }
      let item = this.memberList.find(p => p.memberId === this.memberId)
      this.$emit("onOk", item);
      this.onCancel();
      this.memberId=null;
    },
    onCancel() {
      this.$emit("update:visible", false)
    }
  }
}
</script>

<style scoped lang="scss">

</style>
