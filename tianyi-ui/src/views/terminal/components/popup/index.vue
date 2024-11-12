<template>
  <el-dialog title="轨迹图" :visible.sync="show" width="90%" top="0vh"  :append-to-body="true" custom-class="dialogClass" :modal="false" :show-close="true" @close="closeModal">
    <div id="guijiMap"></div>
  </el-dialog>
</template>

<script>
import * as MapWorks from '../MapWorks';

export default {
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    pointList: {
      type: Array,
      default: []
    }
  },
  
  data() {
    return {
      show: false,
      viewer: null,
    };
  },
  mounted() {
    this.show = this.$props.visible;
    this.init()
  },
  methods: {
    init(){
      setTimeout(() => {
        let container = document.getElementById("guijiMap");
        this.viewer = MapWorks.initMap(container) //初始化地图
        MapWorks.changeBaseMap(this.viewer, 0) //设置影像地图
        let coords = [119.142584, 36.712430, MapWorks.zoomToAltitude(this.viewer, 13)]; // 参数:中心点参数
        let hpr = { heading: 0, pitch:-90.0, roll: 0 } // 参数:方向、视角、倾斜角度
        MapWorks.setView(this.viewer, coords, hpr) //设置中心点以及视图方向
        MapWorks.removePointerAll(this.viewer)
        this.setPoints()
      }, 0);
    },
    setPoints(){
      // this.pointList = [
      //   { bsId: '61238542897', lng: 119.142584, lat: 36.712430, terminalCnt: 80, },
      //   { bsId: '36845952258', lng: 119.135584, lat: 36.722430, terminalCnt: 60, },
      //   { bsId: '15867942596', lng: 119.105584, lat: 36.732430, terminalCnt: 30, },
      //   { bsId: '83232612552', lng: 119.152584, lat: 36.702430, terminalCnt: 8, },
      // ];
      // 设置轨迹
      console.log(this.$props.pointList)
      this.$props.pointList.reduce((prev, curr) => {
        MapWorks.setLineLayer(this.viewer, prev, curr);
        return curr
      });
    },
    closeModal() {
      this.$emit('closeModal')
    }
  }
};
</script>
<style lang="scss" scoped>
::v-deep {
  .dialogClass {
    background: #284978;
    .el-dialog__header {
      // border-bottom: 1px solid #fff;
    }
    .el-dialog__title {
      color: #0496DB;
      font-size: 18px;
    }
    .el-dialog__body{
      padding: 20px;
    }
  }
}
#guijiMap {
  height: calc(94vh - 200px);
}
</style>