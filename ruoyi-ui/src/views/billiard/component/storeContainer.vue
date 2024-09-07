

<template>
  <div class="store-container-wrapper ">
    <el-row :gutter="20" style="flex: 1;display: flex" >
      <!--部门数据-->
      <el-col :span="4" :xs="24" class="store-container"  v-if="isAdminPage">
        <div class="head-container">
          <el-input
            v-model="storeName"
            placeholder="请输入门店名称"
            clearable
            size="small"
            @keydown.enter.native="onFilterStores($event)"
            @clear="onFilterStoresClear($event)"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="store-list">
          <div :key="item.storeId" v-for="item in storeOptions" class="store-list-item" :class="{'selected':currentStoreId===item.storeId}"
            @click="handleNodeClick(item)"  >
          {{item.storeName}}
          </div>
        </div>
      </el-col>
      <div class="split-line" v-if="isAdminPage"></div>
      <!--用户数据-->
      <el-col :span="isAdminPage?20:24" :xs="24" style="padding: 0;margin: 0">
        <slot></slot>
      </el-col>
    </el-row>
  </div>
</template>
<script  >
import { listAllStore } from '@/api/billiard/store'

export default {
  emits:["onStoreChanged"],
  computed: {
    store() {
      return this.$store.getters.store
    }
  },
  data(){
    return {
      isAdminPage:false,
      originalStoreList:[],
      storeName:'',
      currentStoreId:null,
      storeOptions:[],
      defaultProps: {
        children: "children",
        label: "storeName"
      },
    }
  },
  mounted() {
     this.isAdminPage = this.$route.path.toLocaleLowerCase().includes("/admin/");
    if(this.isAdminPage){
      this.queryStores();
    }else {
      this.handleNodeClick(   Object.assign({},this.store))
    }

  },

  methods:{
    onFilterStoresClear(e){
      this.storeOptions=this.originalStoreList.map(p=>p)
    },
    onFilterStores(e){
      console.log('111')
      this.storeOptions=this.originalStoreList.filter(p=>{
        return   p.storeName.includes(this.storeName)
      })
    },
    queryStores(){
      return   listAllStore().then(response => {
        this.originalStoreList= (response.data||[]).map(p=>Object.assign({},p));
        this.storeOptions = (response.data||[]).map(p=>{
          return Object.assign({label:p.storeName,value:p.storeId,raw:{listClass:'primary'}},p);
        });
        let findStore;
        if(this.store?.storeId){
          findStore=this.originalStoreList.find(p=>p.storeId===this.store?.storeId);
        }
          this.handleNodeClick(findStore??this.storeOptions[0]);


      });
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.currentStoreId = data?.storeId;
      this.$emit("onStoreChanged",  Object.assign({},data))
    },
  }
}
</script>
<style scoped lang="scss">
.store-container-wrapper {
  height: 100%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  flex: 1;
}
.store-container{
  margin: 10px;
  display: flex;
  flex-direction: column;
}
.split-line{
  border-right: 1px solid #cccccc;

}
.store-list{
  display: flex;
  flex-direction: column;

  flex: 1;
  &-item{
    display: flex;
    flex-direction: row;
    padding: 5px 10px;
    background:rgb(236, 245, 255) ;
    border-radius: 5px;
    margin-bottom: 5px;
    color: rgba(0,0,0,0.3);
    font-size: 14px;

  }
  &-item.selected{
     background: rgb(64, 158, 255);
    color: #FFFFFF;
    font-weight: bold;
    font-size: 16px;
  }
}
</style>
