<template>
  <el-dialog :title="title" class="custom-dialog" :visible.sync="visible" :width="dialogWidth" append-to-body
             :close-on-click-modal="false" @close="onCancel"
             :close-on-press-escape="false" :show-close="false">
     <template>
       <slot/>
     </template>
    <div slot="footer" class="dialog-footer"  v-if="!hideFooter" >
      <template v-if="!$slots.footer">
        <el-popconfirm v-if="confirmText"
          icon-color="red" @confirm="onOkClick"
          :title="confirmText"
        >
      <el-button   slot="reference"   v-loading="loading" type="primary"  style="margin-right: 10px" >确认</el-button>
        </el-popconfirm>
        <el-button v-else v-loading="loading" type="primary" @click="onOkClick">确认</el-button>
        <el-button @click="onCancel">取 消</el-button>
      </template>
      <template v-else>
        <slot name="footer"/>
      </template>
    </div>
  </el-dialog>
</template>
<script>
import {listMembers} from "@/api/cashier/member";

export default {
  emits: ["onOk","onCancel" ],
  props: ['visible','title','width','onOk','hideFooter','confirmText'],
  data() {
    return {
      loading:false
    }
  },
  computed:{
     dialogWidth(){
       return this.width??"auto"
     }
  },
  methods: {
    async onOkClick() {

      this.loading=true;
      try{
        if(this.onOk){
          await this.onOk();
        }
        this.onCancel();
      }catch (e){
        this.loading=false;
      }finally {
        this.loading=false;
      }
    },
    onCancel() {
      this.$emit("update:visible", false)
      this.$emit("onCancel");
    }
  }
}
</script>

<style scoped lang="scss">

</style>
