<template>
  <el-dialog title="督导排程任务" :visible.sync="open" width="1000px" append-to-body>
    <el-table :data="scheduleList">
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="开课时间" align="center" prop="realTime"/>

      <el-table-column label="名称" align="center" prop="serverName"/>

      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.schedule_status" :value="scope.row.status"/>
        </template>
      </el-table-column>


      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            @click="confirm(scope.row)"
          >确认完成</el-button>

<!--
          <el-button
            size="mini"
            type="text"
            @click="viewSchedule(scope.row)"
          >查看排班</el-button>

          <el-button
            size="mini"
            type="text"
            @click="edit(scope.row)"
            v-hasPermi="['system:supervision-team:edit']"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            @click="del(scope.row)"
            v-hasPermi="['system:supervision-team:edit']"
          >删除</el-button>
-->

        </template>
      </el-table-column>
    </el-table>

    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel">返  回</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {infoTeam, addTeam, editTeam, scheduleInfoTeam, teamScheduleList , confirmSchedule} from "@/api/supervision/team";
import {scheduleStatus} from "@/utils/constants";

export default {
  name: "scheduleInfoForm",
  dicts: ['supervision_type','supervision_status','week_day','consult_level', 'schedule_status'],
  props: {
    consultList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      id:"",
      open: false,
      type: 'info',// tryAdd
      supervisionType: this.$constants.supervisionType,
      weekDay: this.$constants.weekDay,
      scheduleStatusList: this.$constants.scheduleStatus,
      // 上传
      extraData: {
        module: this.$constants['picModules'][2],
        type: this.$constants['picTypes'][2]
      },
      form: {
        memberList:[]
      },
      scheduleList:[],
      // 表单校验
      rules: {

      }
    }
  },
  methods: {
    init(id) {
      this.id = id;
      teamScheduleList({"teamId":id} ).then(res => {
        console.log("***********************************************查询结束,data:")
        console.log(res)
        if (res.code == 200){
          this.scheduleList = res.rows;
        }
        console.log("================================form-info")
        console.log(this.form)
        this.open = true
      });

    },

    //确认完成
    confirm(row){
      confirmSchedule(row.id).then(res => {
        if (res.code == 200){
          this.$modal.msgSuccess("确认成功");
          this.init(this.id);
        }
      })
    },

    cancel() {
      this.form = {}
      this.open = false
    }
  }
}
</script>

<style scoped>

</style>
