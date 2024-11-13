<template>
  <!-- <Header></Header> -->
  <div id="terminalCloudMap">
    <div class="legendBox">
      <img src="@/views/terminal/components/images/legend.png" />
    </div>
    <div class="search">
      <div class="searchBox">
        <el-input class="search-input-class" placeholder="请输入IMEI前14位数字" v-model="searchTxt" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');">
          <el-button slot="append" icon="el-icon-search" @click="searchFun"></el-button>
        </el-input>
      </div>
      <div class="searchContent" :style="{display: visble ? 'block' : 'none'}">
        <div class="baseInfo">
          <div class="title">基本信息</div>
          <el-row class="formBox">
            <el-col :span="24">
              <div class="formItem">
                <div class="label">IMEI:</div>
                <div class="value">{{baseInfoForm.IMEI}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">IMSI:</div>
                <div class="value">{{baseInfoForm.IMSI}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">接入号:</div>
                <div class="value">{{baseInfoForm.jrh}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">销售者:</div>
                <div class="value">{{baseInfoForm.xsz}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">销售单位:</div>
                <div class="value">{{baseInfoForm.xsdw}}</div>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="formItem">
                <div class="label">客户名称:</div>
                <div class="value">{{baseInfoForm.khName}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">模组厂家:</div>
                <div class="value">{{baseInfoForm.mzcj}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">模组型号:</div>
                <div class="value">{{baseInfoForm.mzxh}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">流量:</div>
                <div class="value">{{baseInfoForm.liuliang}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">时长:</div>
                <div class="value">{{baseInfoForm.shic}}</div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="trackInfo">
          <div class="title">终端轨迹</div>
          <div class="tableBox">
            <el-table ref="tables" :data="tableData" height="490" :header-cell-style="tableHeaderCellStyle" :cell-style="tableCellStyle">
              <el-table-column label="IMEI" align="center" prop="IMEI" />
              <el-table-column label="接入号" align="center" prop="jrh" :show-overflow-tooltip="true" />
              <el-table-column label="销售单位" align="center" prop="xsdw" :show-overflow-tooltip="true" />
              <el-table-column label="基站ID" align="center" prop="jzId" :show-overflow-tooltip="true" />
              <el-table-column label="上报次数" align="center" prop="sbcs" :show-overflow-tooltip="true" />
              <el-table-column label="最后话单时间" align="center" prop="zhhdsj" width="100" :show-overflow-tooltip="true" />
            </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
              layout="total,  prev, pager, next, sizes"
              class="terminalPagClass"
            ></pagination>
          </div>
        </div>
      </div>
      <div class="searchContent" :style="{display: visble2 ? 'block' : 'none', height: '900px'}">
        <div class="baseInfo">
          <el-row class="formBox">
            <el-col :span="12">
              <div class="formItem">
                <div class="label">IMEI:</div>
                <div class="value">{{baseInfoForm2.IMEI}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">接入号:</div>
                <div class="value">{{baseInfoForm2.jrh}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">销售单位:</div>
                <div class="value">{{baseInfoForm2.xsdw}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">基站ID:</div>
                <div class="value">{{baseInfoForm2.jzId}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">上报次数:</div>
                <div class="value">{{baseInfoForm2.sbcs}}</div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="formItem">
                <div class="label">最后话单时间:</div>
                <div class="value">{{baseInfoForm2.zhhdsj}}</div>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as MapWorks from '../components/MapWorks';
export default {
  name: 'TerminalCloudMap',
  data() {
    return {
      searchTxt: '',
      // 坐标点信息
      pointList: [
        {id: '61238542897', position: [119.142584, 36.712430], num: 80 },
        {id: '36845952258', position: [119.135584, 36.722430], num: 60 },
        {id: '15867942596', position: [119.105584, 36.732430], num: 30 },
        {id: '83232612552', position: [119.152584, 36.702430], num: 8},
      ],
      baseInfoForm: {
        IMEI: '874586924568',
        IMSI: '874586924568',
        jrh: 'XXXX',
        xsz: 'XXXX',
        xsdw: 'XXXX',
        khName: 'XXXX',
        mzcj: 'XXXX',
        mzxh: 'XXXX',
        liuliang: 'XXXXX',
        shic: 'XXXXXXXX'
      },
      baseInfoForm2: { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
      tableData: [
        { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
        // { IMEI: '12313', jrh: '1321321', xsdw: 'xxx', jzId: 'xxxx', sbcs: '12', zhhdsj: '2012-12-03' },
      ],
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      total: 23,
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
    }
  },
  mounted() {
    this.init();
  },
  beforeDestroy(){
    MapWorks.destroy();
  },
  methods: {
    init(){
      let container = document.getElementById("terminalCloudMap");
      MapWorks.initMap(container) //初始化地图
      MapWorks.changeBaseMap(0) //设置影像地图
      let coords = [119.142584, 36.712430, 15000]; // 参数:中心点参数
      let hpr = { heading: 0, pitch:-90.0, roll: 0 } // 参数:方向、视角、倾斜角度
      MapWorks.setView(coords, hpr) //设置中心点以及视图方向
      // 设置标注点
      this.pointList.map(item => {
        MapWorks.setPointLayer(item)
      });
      MapWorks.addLeftClick((myInfo) => {
        this.visble = false;
        if (myInfo) {
          this.visble2 = true;
          console.log(myInfo)
        } else {
          this.visble2 = false;
          console.log(myInfo)
        }
      })
    },

    changeMap(index){
      MapWorks.changeBaseMap(index)
    },

    getList() {},
    searchFun() {
      this.visble = true;
      this.visble2 = false;
    }
  },
}

</script>

<style lang="scss" scoped>
#terminalCloudMap {
  width: 100%;
  height: 100%;
  position: relative;
  .legendBox {
    position: absolute;
    left: 20px;
    bottom: 20px;
    z-index: 1;
    img {
      width: 500px;
    }
  }
  .search {
    position: absolute;
    right: 20px;
    top: 30px;
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
          }   
            
          &:-ms-input-placeholder {   
              color: rgb(4,150,219);   
          }   
            
          &::-webkit-input-placeholder {   
              color: rgb(4,150,219);   
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
      .title {
        color: rgb(4,150,219);
        font-size: 24px;
        font-weight: 600;
        padding-left: 20px;
        position: relative;
        &::before {
          content: '||';
          position: absolute;
          left: 0px;
          top: 3px;
          color: rgb(4,150,219);
        }
      }
      .baseInfo {
        .formBox {
          .formItem {
            color: rgb(4,150,219);
            display: flex;
            align-items: center;
            line-height: 18px;
            margin-top: 18px;
            .label {
              margin-right: 5px;
            }
          }
        }
      }
      .trackInfo {
        margin-top: 40px;
        .tableBox {
          // height: 530px;
          margin-top: 20px;
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

