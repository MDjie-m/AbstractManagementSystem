<template>
  <!-- <Header></Header> -->
  <div id="cesiumContainer">
    <div class="networkBox">
      <el-select
          v-model="networkVal"
          placeholder="网络制式"
          style="width: 100px"
          size="small"
          :popper-append-to-body="false"
        >
          <el-option
            v-for="item in networkOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
    </div>
    <div class="legendBox">
      <div class="legendContent">
        <img src="@/views/terminal/components/images/legend.png" />
        <div class="wordDes">
          <span>低流量</span>
          <span>高流量</span>
        </div>
      </div>
    </div>
    <div class="search">
      <div class="searchBox">
        <el-input class="search-input-class" placeholder="请输入IMEI前14位数字" v-model="searchTxt" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');">
          <el-button slot="append" icon="el-icon-search" @click="searchImeiFun(searchTxt)"></el-button>
        </el-input>
      </div>
      <div class="searchContent" :style="{display: visble ? 'block' : 'none'}">
        <div class="baseInfo">
          <div class="title">基本信息</div>
          <el-row class="formBox">
            <el-col :span="24">
              <div class="formItem">
                <div class="label">IMEI:</div>
                <div class="value">{{baseInfoForm.imei}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">接入号:</div>
                <div class="value">{{baseInfoForm.billingNbr}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">销售者:</div>
                <div class="value">{{baseInfoForm.provName}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">销售单位:</div>
                <div class="value">{{baseInfoForm.areaName}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">客户名称:</div>
                <div class="value">{{baseInfoForm.custName}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">模组厂家:</div>
                <div class="value">{{baseInfoForm.manufacturer}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">模组型号:</div>
                <div class="value">{{baseInfoForm.modelName}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">流量:</div>
                <div class="value">{{baseInfoForm.vol}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">时长:</div>
                <div class="value">{{baseInfoForm.volDur}}</div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="trackInfo">
          <div class="title">
            <span>终端轨迹</span>
            <el-button type="primary" size="mini" class="jumpZbt" @click="clickGjIMEI(baseInfoForm.imei)">显示轨迹</el-button>
          </div>
          <div class="tableBox">
            <el-table ref="tables" :data="tableData" height="480" :header-cell-style="tableHeaderCellStyle" :cell-style="tableCellStyle">
              <el-table-column label="IMEI" align="center" prop="imei" fixed="left">
                <template slot-scope="scope"><span>{{ scope.row.imei }}</span></template>
              </el-table-column>
              <el-table-column label="接入号码" align="center" prop="billingNbr" :show-overflow-tooltip="true" />
              <el-table-column label="开始时间" align="center" prop="startTime" :show-overflow-tooltip="true" />
              <el-table-column label="结束时间" align="center" prop="endTime" :show-overflow-tooltip="true" />
              <el-table-column label="流量" align="center" prop="vol" :show-overflow-tooltip="true" />
              <el-table-column label="基站id" align="center" prop="bdId" :show-overflow-tooltip="true" />
            </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.page"
              :limit.sync="queryParams.limit"
              @pagination="getImeiFun"
              layout="total,  prev, pager, next, sizes"
              class="terminalPagClass"
            ></pagination>
          </div>
        </div>
      </div>
      <div class="searchContent" :style="{display: visble2 ? 'block' : 'none'}">
        <div class="baseInfo">
          <div class="title">基本信息</div>
          <el-row class="formBox">
            <el-col :span="24">
              <div class="formItem">
                <div class="label">基站名称:</div>
                <div class="value">{{baseInfoForm2.bsName}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">基站经度:</div>
                <div class="value">{{baseInfoForm2.lng}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">基站维度:</div>
                <div class="value">{{baseInfoForm2.lat}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">终端数量:</div>
                <div class="value">{{baseInfoForm2.terminalCnt}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">总流量:</div>
                <div class="value">{{baseInfoForm2.vol}}</div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="trackInfo">
          <div class="title">终端列表</div>
          <div class="tableBox">
            <el-table ref="tables" :data="imeiTableData" height="480" :header-cell-style="tableHeaderCellStyle" :cell-style="tableCellStyle">
              <el-table-column label="IMEI" align="center" prop="imei" fixed="left">
                <template slot-scope="scope"><span class="imeiLink" @click="searchImeiFun(scope.row.imei)">{{ scope.row.imei }}</span></template>
              </el-table-column>
              <el-table-column label="接入号" align="center" prop="billingNbr" :show-overflow-tooltip="true" />
              <el-table-column label="销售省" align="center" prop="provName" :show-overflow-tooltip="true" />
              <el-table-column label="销售单位" align="center" prop="areaName" :show-overflow-tooltip="true" />
              <el-table-column label="客户名称" align="center" prop="custName" :show-overflow-tooltip="true" />
              <!-- <el-table-column label="模组厂家" align="center" prop="manufacturer" :show-overflow-tooltip="true" />
              <el-table-column label="模组型号" align="center" prop="modelName" :show-overflow-tooltip="true" />
              <el-table-column label="模组流量" align="center" prop="vol":show-overflow-tooltip="true" /> -->
              <el-table-column label="时长" align="center" prop="volDur" :show-overflow-tooltip="true" />
            </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams2.page"
              :limit.sync="queryParams2.limit"
              @pagination="getBsFun"
              layout="total,  prev, pager, next, sizes"
              class="terminalPagClass"
            ></pagination>
          </div>
        </div>
      </div>
    </div>
    <popup v-if="showMapStatus" :visible="showMapStatus" :pointList="tableData" @closeModal="closeModal"></popup>
  </div>
</template>

<script>
import * as MapWorks from '../components/MapWorks';
import popup from "../components/popup"
import { queryBSStatInfo, queryByImeiInfo, trackIMEI, queryBSTerminalInfo, queryImeiInfo } from "@/api/terminal/terminalMap.js";
export default {
  name: 'TerminalMap',
  components: { popup },
  data() {
    return {
      searchTxt: '',
      bsId: '',
      searchTxt: '',
      networkVal: 0,
      networkOptions: [
        {value:0,label:'全部'},
        {value:1,label:'NB'},
        {value:2,label:'CAT1'},
        {value:3,label:'4G'},
        {value:4,label:'5G'},
      ],
      // 坐标点信息
      pointList: [],
      baseInfoForm: {},
      baseInfoForm2: {},
      tableData: [],
      imeiTableData: [],
      queryParams: {
        page: 1,
        limit: 10
      },
      queryParams2: {
        page: 1,
        limit: 10
      },
      total: 0,
      total2: 0,
      tableHeaderCellStyle: {
        background: '#0E1A2B',
        color: '#FFFFFF',
        border: 0,
        padding: '15px 0'
      },
      tableCellStyle: {
        background: '#0E1A2B',
        color: '#FFFFFF',
        border: 0,
        padding: '10px 0'
      },
      visble: false,
      visble2: false,
      showMapStatus: false,
      level: '18',
      myInfo: null,
      viewer: null,
      northwest: [],
      southeast: [],
    }
  },
  mounted() {
    this.init();
  },
  beforeDestroy(){
    MapWorks.destroy(this.viewer);
  },
  methods: {

    init(){
      let container = document.getElementById("cesiumContainer");
      this.viewer = MapWorks.initMap(container) //初始化地图
      MapWorks.changeBaseMap(this.viewer,0) //设置影像地图
      let coords = [119.142584, 36.712430, MapWorks.zoomToAltitude(this.viewer, 13)]; // 参数:中心点参数
      let hpr = { heading: 0, pitch:-90.0, roll: 0 } // 参数:方向、视角、倾斜角度
      MapWorks.setView(this.viewer, coords, hpr) //设置中心点以及视图方向

      // 移动相机视图交互
      MapWorks.setScaleMap(this.viewer, (northwest, southeast) => {
        MapWorks.removePointerAll(this.viewer)
        this.northwest = northwest;
        this.southeast = southeast;
        // 获取所有基站点
        this.getPoints();
      })
      
      // 点击地图交互
      MapWorks.addLeftClick(this.viewer, myInfo => {
        this.visble = false;
        this.visble2 = false;
        this.myInfo = null;
        if (myInfo) {
          this.myInfo = myInfo;
          // 获取某个基站点详情数据
          this.searchBsFun(myInfo.bsId)
        }
      });
      
    },
    // 获取所有基站点
    getPoints(){
      // queryBSStatInfo({
      //   lngBegin: this.northwest[0],
      //   lngEnd: this.northwest[1],
      //   latBegin: this.southeast[0],
      //   latEnd: this.southeast[1],
      //   rateType: this.networkVal ? this.networkVal : 0  // 0:全部  1:NB  2:CAT1  3:4G   4:5G
      // }).then(res => {
      //   this.pointList = res.data;
      // })
      this.pointList = [
        { bsId: '61238542897', lng: 119.142584, lat: 36.712430, terminalCnt: 80, },
        { bsId: '36845952258', lng: 119.135584, lat: 36.722430, terminalCnt: 60, },
        { bsId: '15867942596', lng: 119.105584, lat: 36.732430, terminalCnt: 30, },
        { bsId: '83232612552', lng: 119.152584, lat: 36.702430, terminalCnt: 8, },
      ];
      // 设置标注点
      this.pointList.map(item => {
        MapWorks.setPointLayer(this.viewer, item)
      });
    },
    // 打开IMEI弹窗
    searchImeiFun(imei) {
      if (!imei) return;
      this.searchTxt = imei;
      this.visble = true;
      this.visble2 = false;
      this.getImeiFun({ page: 1,  limit: 20 })
    },
    // 根据IMEI获取imei基本信息和轨迹列表
    getImeiFun(params) {
      this.queryParams.page = params.page;
      this.queryParams.limit = params.limit;
      // // IMEI基本信息查询接口
      // queryByImeiInfo({
      //   imei: this.searchTxt,
      // }).then(res => {
      //   this.baseInfoForm = res.data;
      // });
      // // IMEI轨迹查询接口
      // trackIMEI({
      //   imei: this.searchTxt,
      //   index: queryParams.page,
      //   size: queryParams.limit,
      // }).then(res => {
      //   this.tableData = res.data.dataRows;
      //   this.total = res.data.total;
      // });
      setTimeout(() => {
        this.tableData = [
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.142584, lat: 36.712430, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '36845952258', lng: 119.135584, lat: 36.722430, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.105584, lat: 36.732430, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.152584, lat: 36.702430, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.142184, lat: 36.702730, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.142384, lat: 36.702430, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.146584, lat: 36.703530, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.142984, lat: 36.702530, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.141584, lat: 36.701530, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.142284, lat: 36.703530, },
          { imei: '12313', billingNbr: '1321321', startTime: '2022-02-04', endTime: '2022-02-05', vol: '45', bdId: '61238542897', lng: 119.142984, lat: 36.704530, },
        ];
        this.total = 11;
        this.baseInfoForm = { imei: '12313', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' };
      }, 1000);
    },
    searchBsFun(bsId) {
      if (!bsId) return;
      this.bsId = bsId;
      this.visble = false;
      this.visble2 = true;
      this.getBsFun({ page: 1,  limit: 20 })
    },
    // 根据bsId获取基站基本信息和基站列表
    getBsFun(params) {
      this.queryParams2.page = params.page;
      this.queryParams2.limit = params.limit;
      // queryBSTerminalInfo({
      //   bsId: this.bsId,
      // }).then(res => {
      //   this.baseInfoForm2 = res.data;
      // });
      // queryImeiInfo({
      //   bsId: this.bsId,
      //   index: queryParams.pageNum,
      //   size: queryParams.pageSize,
      // }).then(res => {
      //   this.imeiTableData = res.data.dataRows;
      //   this.total2 = res.data.total;
      // });
      setTimeout(() => {
        this.baseInfoForm2 = { bsName: 'XXX', lng: 'xxx', lat: 'xxxx', terminalCnt: 'XXX', vol: 'xxx' };
        this.imeiTableData = [
          { imei: '12313', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '12315', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '12316', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '123123', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '123112', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '223434', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '12334532', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '213123', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '1231232', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '1243565', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '3132322', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
          { imei: '321312312', billingNbr: '1321321', provName: 'xxx', areaName: 'xxxx', custName: 'XXX', manufacturer: 'xxx', modelName: 'xxx', vol: 'XXX', volDur: 'xxx' },
        ];
        this.total2 = 12;
      }, 200);
    },
    // 打开轨迹弹窗
    clickGjIMEI() {
      this.showMapStatus = true;
    },
    // 关闭轨迹弹窗
    closeModal() {
      this.showMapStatus = false;
    }
  },
}

</script>

<style lang="scss" scoped>
#cesiumContainer {
  width: 100%;
  height: calc(100vh - 84px);
  position: relative;
  .networkBox {
    position: absolute;
    left: 20px;
    top: 20px;
    z-index: 1;
    ::v-deep {
      .el-input__inner {
        background-color: rgb(22,51,81)!important;
        border: 0;
        color: rgb(4,150,219);
        height: 35px;
        font-size: 18px;
        &:placeholder {
          color: rgb(4,150,219);
          font-size: 16px;
        }
        &:-moz-placeholder {   
            color: rgb(4,150,219);
            font-size: 16px;
        }   
          
        &:-ms-input-placeholder {   
            color: rgb(4,150,219);
            font-size: 16px;
        }   
          
        &::-webkit-input-placeholder {   
            color: rgb(4,150,219);
            font-size: 16px;
        }
      }
      .el-select-dropdown {
        background: rgb(22,51,81);
        border: 0;
      }
      .el-select-dropdown__item {
          color: rgb(4,150,219);
          font-size: 18px;
      }
      .el-select-dropdown__item.hover, .el-select-dropdown__item:hover {
        background-color: rgba(22,51,81,0.8)!important;
      }
    }
  }
  .legendBox {
    position: absolute;
    left: 20px;
    bottom: 20px;
    z-index: 1;
    .legendContent {
      border: 3px solid #1D92C0;
      background: #1D2733;
      border-radius: 5px;
      width: 450px;
      padding: 8px 10px 5px;
      img {
        width: 100%;
        display: block;
      }
      .wordDes{
        display: flex;
        justify-content: space-between;
        padding: 3px 10px 0px;
        color: #0C6B9C;
      }
    }
  }
  .search {
    position: absolute;
    right: 20px;
    top: 20px;
    z-index: 1;
    width: 550px;
    .searchBox {
      .search-input-class {
        border-color: red;
        ::v-deep .el-input__inner {
          background-color: rgb(22,51,81)!important;
          border: 0;
          color: #ffffff;
          height: 50px;
          font-size: 18px;
          &:placeholder {
            color: rgb(4,150,219);
            font-size: 16px;
          }
          &:-moz-placeholder {   
              color: rgb(4,150,219);
              font-size: 16px;  
          }   
            
          &:-ms-input-placeholder {   
              color: rgb(4,150,219);
              font-size: 16px; 
          }   
            
          &::-webkit-input-placeholder {   
              color: rgb(4,150,219);
              font-size: 16px;
          }
        }
        ::v-deep .el-input-group__append {
          border: 0;
          color: rgb(4,150,219);
          background: rgb(40,73,120);
        }
      }
      // color: rgb(4,150,219);
      // background: rgb(22,51,81);
    }
    .searchContent {
      background: linear-gradient(rgb(40,73,120),rgba(1,1,2));
      margin-top: 10px;
      padding: 20px;
      height: calc(100vh - 190px);
      overflow: hidden;
      .title {
        color: rgb(4,150,219);
        font-size: 24px;
        font-weight: 600;
        padding-left: 20px;
        position: relative;
        display: flex;
        justify-content: space-between;
        align-items: center;
        &::before {
          content: '||';
          position: absolute;
          left: 0px;
          top: 1px;
          color: rgb(4,150,219);
        }
        .jumpZbt {
        }
      }
      .baseInfo {
        margin-bottom: 30px;
        .formBox {
          .formItem {
            color: rgb(4,150,219);
            display: flex;
            align-items: center;
            line-height: 18px;
            margin-top: 10px;
            .label {
              margin-right: 5px;
            }
          }
        }
      }
      .trackInfo {
        .tableBox {
          // height: 530px;
          margin-top: 10px;
          padding-bottom: 54px;
          position: relative;
          overflow-y: auto;
          ::v-deep .el-table {
            background: transparent;
            tr {
              background: transparent;
            }
            &::before {
              height: 0;
            }
            .el-table__fixed::before {
              display: none;
            }
          }
          .imeiLink {
            color: #0496db;
            cursor: pointer;
          }
          .terminalPagClass {
            width: 100%;
            background: transparent;
            padding: 0!important;
            margin: 0;
            height: 42px;
            position: absolute;
            bottom: 0;
            left: 0;
            ::v-deep {
              .el-pagination {
                margin-top: 10px;
                width: 100%;
                text-align: center;
              }
              .el-pagination.is-background .el-pager li:not(.disabled), .btn-prev, .btn-next {
                background-color: #173263; /*进行修改未选中背景和字体 */
                color: #fff;
                margin: 0 2px;
              }
              .el-pagination.is-background .el-pager li:not(.disabled).active{
                background-color: #5A9FFF; /*进行修改选中项背景和字体 */
                color: #fff;
              }
              .el-pagination__sizes .el-input__inner {
                background-color: #173263;
                color: #fff;
                border: 0;
              }
              .el-input__suffix {
                color: #fff;
              }
              .el-pagination__total {
                color: #275293;
              }
            }
          }
        }
      }
    }
  }
}
</style>

