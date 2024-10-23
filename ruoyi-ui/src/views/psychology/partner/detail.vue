<template>
  <div class="app-container" v-if="info.id">
    <el-descriptions size="mini">
      <template slot="title">
        申请编号: {{ info.id }} <span style="margin-left: 100px">{{ info.statusName }}</span>
      </template>
      <template slot="extra">
        <el-button v-hasPermi="['psychology:partner:edit']" v-if="info.status === '1'" type="primary" size="small" @click="openApp" style="margin-right: 20px">审批</el-button>
        <el-button size="small" @click="goBack">返回列表</el-button>
      </template>
      <el-descriptions-item label="申请时间">{{ info.createTime }}</el-descriptions-item>
      <el-descriptions-item label="入驻费用" :span="2">{{ info.money }}</el-descriptions-item>
      <el-descriptions-item label="申请类型">{{ info.typeName }}</el-descriptions-item>
      <el-descriptions-item label="结算比例">{{ info.ratio }}</el-descriptions-item>
    </el-descriptions>
    <el-divider/>
    <el-descriptions title="基本信息" size="mini">
      <el-descriptions-item label="真实姓名">{{ info.name }}</el-descriptions-item>
      <el-descriptions-item label="性别">{{ info.sex == 1 ? '男' : '女' }}</el-descriptions-item>
      <el-descriptions-item label="手机号码">{{ info.phone }}</el-descriptions-item>
      <el-descriptions-item label="邮箱">{{ info.email }}</el-descriptions-item>
      <el-descriptions-item label="微信号">{{ info.wechat }}</el-descriptions-item>
      <el-descriptions-item label="QQ号">{{ info.qq }}</el-descriptions-item>
      <el-descriptions-item label="咨询地点">{{ info.province }}-{{ info.city }}</el-descriptions-item>
      <el-descriptions-item label="咨询语种">{{ info.lang }}</el-descriptions-item>
      <el-descriptions-item label="从业年限(年)">{{ info.workHours }}</el-descriptions-item>
      <el-descriptions-item label="证件类型">{{ info.card }}</el-descriptions-item>
      <el-descriptions-item label="咨询流派" :span="2">{{ info.genre }}</el-descriptions-item>
      <el-descriptions-item label="证明照片"/>
    </el-descriptions>
    <image-preview style="margin-right: 25px" v-for="(item,index) in imgs" :src="item" width="100px" height="100px"/>
    <el-divider/>

    <el-row>
      <el-col :span="14">
        <div class="title" style="margin-top: 0">学历背景</div>
        <el-table border :data="info.items.filter(a => a.type === 1)" size="mini">
          <el-table-column label="时间" align="center" prop="startTime">
            <template slot-scope="scope">
              {{ scope.row.startTime }} ~ {{ scope.row.endTime }}
            </template>
          </el-table-column>
          <el-table-column label="学校名称" align="center" prop="param1"/>
          <el-table-column label="专业名称" align="center" prop="param2"/>
          <el-table-column label="获得学位" align="center" prop="param3"/>
          <el-table-column label="证明照片" align="center" prop="img">
            <template slot-scope="scope" v-if="scope.row.img">
              <image-preview style="margin-right: 10px" v-for="(item,index) in scope.row.img.split(',')" :src="item"
                             width="150px" height="150px"/>
            </template>
          </el-table-column>
          <el-table-column label="是否已审过" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === '1'" type="success">审核通过</el-tag>
              <el-tag v-else type="danger">初次提审</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="14">
        <div class="title">专业资质&专业证书</div>
        <el-table border :data="info.items.filter(a => a.type === 2 || a.type === 3)" size="mini">
          <el-table-column label="资质类型" align="center" prop="type">
            <template slot-scope="scope">
              {{ scope.row.type === 2 ? scope.row.param1 : '-' }}
            </template>
          </el-table-column>
          <el-table-column label="证书编号" align="center" prop="param2">
            <template slot-scope="scope">
              {{ scope.row.type === 2 ? scope.row.param2 : '-' }}
            </template>
          </el-table-column>
          <el-table-column label="证书名称" align="center" prop="type">
            <template slot-scope="scope">
              {{ scope.row.type === 3 ? scope.row.param1 : '-' }}
            </template>
          </el-table-column>
          <el-table-column label="证明照片" align="center" prop="img">
            <template slot-scope="scope" v-if="scope.row.img">
              <image-preview style="margin-right: 10px" v-for="(item,index) in scope.row.img.split(',')" :src="item"
                             width="150px" height="150px"/>
            </template>
          </el-table-column>
          <el-table-column label="是否已审过" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === '1'" type="success">审核通过</el-tag>
              <el-tag v-else type="danger">初次提审</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="14">
        <div class="title">受训经历</div>
        <el-table border :data="info.items.filter(a => a.type === 4)" size="mini">
          <el-table-column label="时间" align="center" prop="startTime">
            <template slot-scope="scope">
              {{ scope.row.startTime }} ~ {{ scope.row.endTime }}
            </template>
          </el-table-column>
          <el-table-column label="培训名称" align="center" prop="param1"/>
          <el-table-column label="主办机构" align="center" prop="param2"/>
          <el-table-column label="总计小时数" align="center" prop="num"/>
          <el-table-column label="证明照片" align="center" prop="img">
            <template slot-scope="scope" v-if="scope.row.img">
              <image-preview style="margin-right: 10px" v-for="(item,index) in scope.row.img.split(',')" :src="item"
                             width="150px" height="150px"/>
            </template>
          </el-table-column>
          <el-table-column label="是否已审过" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === '1'" type="success">审核通过</el-tag>
              <el-tag v-else type="danger">初次提审</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="14">
        <div class="title">个案咨询经历</div>
        <el-table border :data="info.items.filter(a => a.type === 5)" size="mini">
          <el-table-column label="时间" align="center" prop="startTime">
            <template slot-scope="scope">
              {{ scope.row.startTime }} ~ {{ scope.row.endTime }}
            </template>
          </el-table-column>
          <el-table-column label="咨询类型" align="center" prop="param1"/>
          <el-table-column label="总计小时数" align="center" prop="num"/>
          <el-table-column label="负责人姓名" align="center" prop="param2"/>
          <el-table-column label="机构联系方式" align="center" prop="param3"/>
          <el-table-column label="证明照片" align="center" prop="img">
            <template slot-scope="scope" v-if="scope.row.img">
              <image-preview style="margin-right: 10px" v-for="(item,index) in scope.row.img.split(',')" :src="item"
                             width="150px" height="150px"/>
            </template>
          </el-table-column>
          <el-table-column label="是否已审过" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === '1'" type="success">审核通过</el-tag>
              <el-tag v-else type="danger">初次提审</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="14">
        <div class="title">接受督导经历</div>
        <el-table border :data="info.items.filter(a => a.type === 6)" size="mini">
          <el-table-column label="时间" align="center" prop="startTime">
            <template slot-scope="scope">
              {{ scope.row.startTime }} ~ {{ scope.row.endTime }}
            </template>
          </el-table-column>
          <el-table-column label="督导类型" align="center" prop="param1"/>
          <el-table-column label="总计小时数" align="center" prop="num"/>
          <el-table-column label="督导姓名" align="center" prop="param2"/>
          <el-table-column label="督导取向" align="center" prop="param3"/>
          <el-table-column label="证明照片" align="center" prop="img">
            <template slot-scope="scope" v-if="scope.row.img">
              <image-preview style="margin-right: 10px" v-for="(item,index) in scope.row.img.split(',')" :src="item"
                             width="150px" height="150px"/>
            </template>
          </el-table-column>
          <el-table-column label="是否已审过" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === '1'" type="success">审核通过</el-tag>
              <el-tag v-else type="danger">初次提审</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="14">
        <div class="title">个人体验经历</div>
        <el-table border :data="info.items.filter(a => a.type === 7)" size="mini">
          <el-table-column label="说明" align="center" prop="param1"/>
          <el-table-column label="是否已审过" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === '1'" type="success">审核通过</el-tag>
              <el-tag v-else type="danger">初次提审</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <approve ref="formApp" @handleOk="approveOk"/>
  </div>
</template>

<script>
import {getPartner} from "@/api/psychology/partner";
import approve from "./approve";

export default {
  name: "PartnerDetail",
  components:{ approve },
  data() {
    return {
      info: {
        id: null,
        items: []
      },
      extraData: {},
      imgs: []
    }
  },
  created() {
    if (this.$route.query && this.$route.query.id) {
      this.getInfo(this.$route.query.id)
    }
  },
  methods: {
    getInfo(id) {
      getPartner(id).then(response => {
        this.info = response.data
        if (this.info.cardImgs.length > 0) {
          this.imgs = this.imgs.concat(this.info.cardImgs)
        }
      })
    },
    goBack() {
      this.$router.push({ path: '/partner/partnerList' })
    },
    /** 审批 */
    openApp() {
      this.$refs.formApp.init(this.info)
    },
    /** 审批完成 */
    approveOk() {
      this.getInfo(this.info.id);
    },
  }
}
</script>

<style scoped>
.title {
  margin-top: 20px;
  font-size: 16px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 20px;
}

</style>
