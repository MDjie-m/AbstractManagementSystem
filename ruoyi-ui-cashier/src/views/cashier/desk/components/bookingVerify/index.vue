<template>
  <div class="input-container">


    <div class=" section-container    " style="display: flex;flex: 1;flex-direction: column">
      <el-tabs v-model="currentTitle" class="tab-container" @tab-click="onTabClick">
        <el-tab-pane label="台桌预约" name="0">

          <el-form :inline="true">
            <el-form-item>
              <el-input v-model="desk.queryParams.keyword" maxlength="20" placeholder="预约人姓名/手机号"  autocomplete=“off”
                        @keydown.native.enter="queryDeskBookingList"/>
            </el-form-item>
            <el-form-item>
              <el-select v-model="desk.queryParams.deskId" placeholder="台桌" @change="queryDeskBookingList">
                <el-option :value="null" label="台桌"/>
                <el-option :value="item.deskId" :key="item.deskId+'fffffdes'" :label="item.title" v-for="item in desk.deskList">

                </el-option>
              </el-select>
            </el-form-item>
          </el-form>

          <div class="table-box">
            <el-table v-loading="loading" :data="desk.list" @change="queryDeskBookingList" class="table"
                      style="width: 100%;" height="100%"
            >
              <el-table-column label="序号" align="center" type="index" width="50">
                <template v-slot="scope">
                  {{ (desk.queryParams.pageNum - 1) * desk.queryParams.pageSize + scope.$index + 1 }}
                </template>
              </el-table-column>
              <el-table-column label="预约开始时间" align="center" prop="startTime"/>
              <el-table-column label="预约结束时间" align="center" prop="endTime"/>
              <el-table-column label="预约台桌" align="center" prop="deskTitle"/>
              <el-table-column label="预约人" align="center" prop="bookingUserName"/>
              <el-table-column label="联系电话" align="center" prop="bookingUserMobile"/>
              <el-table-column label="创建时间" align="center" prop="createTime"/>
              <el-table-column label="操作" align="center" type="index"  width="120" >

                <template v-slot="scope">

                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete" round
                    @click="onRemoveDeskBookingClick(scope.row)"
                  > </el-button>
                  <el-button
                    size="mini"
                    type="success"
                    icon="el-icon-s-check" round
                    @click="onRemoveDeskBookingClick(scope.row)"
                  > </el-button>

                </template>
              </el-table-column>
            </el-table>
          </div>
          <pagination
            v-show="desk.total>0"
            :total="desk.total"
            :page.sync="desk.queryParams.pageNum"
            :limit.sync="desk.queryParams.pageSize"
            @pagination="queryDeskBookingList"
          />
        </el-tab-pane>
        <el-tab-pane label="教练预约" name="1">
          <el-form :inline="true">
            <el-form-item>
              <el-input v-model="tutor.queryParams.keyword" maxlength="20" placeholder="预约人姓名/手机号"  autocomplete=“off”
                        @keydown.native.enter="queryTutorBookingList"/>
            </el-form-item>
            <el-form-item>
              <el-select v-model="tutor.queryParams.tutorId" placeholder="请选择助教" @change="queryTutorBookingList">
                <el-option :value="null" label="助教"/>
                <el-option :value="item.storeTutorId" :key="item.storeTutorId+'aaaaaf'" :label="item.realName" v-for="item in tutor.tutorList">

                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div class="table-box">
            <el-table v-loading="loading" :data="tutor.list" @change="queryTutorBookingList" class="table"
                      style="width: 100%;" height="100%">
              <el-table-column label="序号" align="center" type="index" width="50">
                <template v-slot="scope">
                  {{ (tutor.queryParams.pageNum - 1) * tutor.queryParams.pageSize + scope.$index + 1 }}
                </template>
              </el-table-column>
              <el-table-column label="预约开始时间" align="center" prop="startTime"/>
              <el-table-column label="预约结束时间" align="center" prop="endTime"/>
              <el-table-column label="预约助教" align="center" prop="tutorTitle"/>
              <el-table-column label="预约人" align="center" prop="bookingUserName"/>
              <el-table-column label="联系电话" align="center" prop="bookingUserMobile"/>
              <el-table-column label="创建时间" align="center" prop="createTime"/>
              <el-table-column label="操作" align="center" type="index"  width="120" >

                <template v-slot="scope">

                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete" round
                    @click="onRemoveDeskBookingClick(scope.row)"
                  > </el-button>
                  <el-button
                    size="mini"
                    type="success"
                    icon="el-icon-s-check" round
                    @click="onRemoveDeskBookingClick(scope.row)"
                  > </el-button>

                </template>
              </el-table-column>
            </el-table>
          </div>
          <pagination
            v-show="tutor.total>0"
            :total="tutor.total"
            :page.sync="tutor.queryParams.pageNum"
            :limit.sync="tutor.queryParams.pageSize"
            @pagination="queryTutorBookingList"
          />
        </el-tab-pane>
      </el-tabs>

    </div>
  </div>
</template>
<script>


import {getDeskBookingList, listDesk} from "@/api/cashier/desk";
import {getTutorBookingList, listAllTutor} from "@/api/cashier/tutor";

export default {
  emits: ["ok"],
  dicts: ['store_desk_status', 'store_desk_type', 'store_desk_place','store_tutor_work_status'],
  data() {

    return {
      desk: {
        list: [],
        total: 0,
        deskList: [],
        queryParams: {
          keyword: null,
          deskId: null,
          pageSize: 10,
          pageNum: 1,
        }
      },
      tutor: {
        list: [],
        total: 0,
        tutorList: [],
        queryParams: {
          keyword: null,
          tutorId: null,
          pageSize: 10,
          pageNum: 1,
        }
      },
      loading: false,
      currentTitle: '0'
    }
  },
  created() {
    this.queryDeskList();
    this.queryTutorList()
  },
  mounted() {
    this.desk.queryParams.memberId = this.tutor.queryParams.memberId = this.memberId;
    this.queryDeskBookingList();

  },
  methods: {
    onRemoveDeskBookingClick(item){

    },
    onTabClick() {
      if (this.currentTitle === '0') {
        this.queryDeskBookingList()
      } else {
        this.queryTutorBookingList()
      }
    },
    queryDeskList() {
      listDesk({}).then(res => {
        this.desk.deskList = (res.data || []).map(p=>this.fillTitle(p));
      })
    },
    queryTutorList() {
      listAllTutor({}).then(res => {
        this.tutor.tutorList = res.data || []
      })
    },
    queryDeskBookingList() {
      this.loading = true
      getDeskBookingList(this.desk.queryParams).then(res => {
        let list = res.rows || [];
        this.desk.list =   list;
        this.desk.total = res.total;
      }).finally(() => this.loading = false)
    },
    queryTutorBookingList() {
      this.loading = true
      getTutorBookingList(this.desk.queryParams).then(res => {
        let list = res.rows || [];
        this.tutor.list = list;
        this.tutor.total = res.total;
      }).finally(() => this.loading = false)
    },
    fillTitle(item) {
      let type = this.dict.type.store_desk_type.find(p => parseInt(p.value) === item.deskType)?.label ?? '';
      let place = this.dict.type.store_desk_place.find(p => parseInt(p.value) === item.placeType)?.label ?? '';
      item.shortTitle = `${item.deskName}(${item.deskNum})`
      item.title = `${item.deskName}(${item.deskNum})/${type}/${place}`;
      return item;
    },
  }

}
</script>

<style scoped lang="scss">
.input-container {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.tab-container {
  flex-direction: column;
  flex: 1;
  display: flex;
  overflow: hidden;

  ::v-deep.el-tabs__content {
    flex: 1;
    display: flex;
    overflow: hidden;

    .el-tab-pane {
      flex: 1;
      position: relative;
      display: flex;
      flex-direction: column;
      overflow: hidden;
    }

    .table-box {
      position: relative;
      overflow: hidden;
      flex: 1;
    }

    .table {
      position: absolute;
    }
  }
}


</style>
