<template>
  <div class="page-container">

    <left-container @onRefreshClick="onRefreshClick">
      <div class="section-container " style="margin:10px" v-if="current">
        <el-form label-width="100px">
          <el-form-item label="姓名:">
            {{ current.realName }}
          </el-form-item>
          <el-form-item label="会员等级:">
            {{ current.levelName }}
          </el-form-item>
          <el-form-item label="手机号:">
            {{ current.mobile }}
          </el-form-item>
          <el-form-item label="性别：">
            <dict-tag :options="dict.type.sys_user_sex" :value="current.sex"/>
          </el-form-item>
          <el-form-item label="当前余额:">
            {{ current.showAmount ? current.currentAmount : '***' }}
          </el-form-item>
          <el-form-item label="充值总额:">
            {{ current.showAmount ? current.totalAmount : '***' }}
          </el-form-item>

        </el-form>
        <div style="display: flex ;justify-content: center">
          <el-button-group>
            <el-button type="danger" @click="onPwdClick(MemberDialogTitle.Recharge)" size="mini">充值</el-button>
            <el-button type="primary" @click="onPwdClick(MemberDialogTitle.ChangePwd)" size="mini">修改密码</el-button>
            <el-button type="primary" size="mini" @click="current.showAmount=!current.showAmount">查看余额</el-button>
            <el-button type="primary" @click="onPwdClick(MemberDialogTitle.Order)" size="mini">消费记录</el-button>
          </el-button-group>
        </div>

      </div>

    </left-container>
    <div class="right-panel">
      <div class="section-container">
        <el-input @keydown.enter.native="getList" v-model="queryParams.keyword" maxlength="20" autocomplete="off"
                  placeholder="请输入会员姓名或者手机号"></el-input>
      </div>
      <div class="section-container member-box">
        <div class="table-box">
          <el-table v-loading="loading" :data="memberList" @change="getList" @row-click="onRowClick"
                    :row-style="rowStyle">
            <el-table-column label="姓名" align="center" prop="realName" width="210"/>
            <el-table-column label="手机号" align="center" prop="mobile"/>
            <el-table-column label="会员等级" align="center" prop="levelName"/>
            <el-table-column label="性别" align="center" prop="currentAmount">
              <template v-slot="scope">
                <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
              </template>
            </el-table-column>

            <el-table-column label="账户余额" align="center" prop="currentAmount">
              <template v-slot="scope">
                <span> {{ scope.row.showAmount ? scope.row.currentAmount : '***' }}</span>
              </template>
            </el-table-column>
            <el-table-column label="历史充值总额" align="center" prop="totalAmount">
              <template v-slot="scope">
                <span> {{ scope.row.showAmount ? scope.row.totalAmount : '***' }}</span>
              </template>
            </el-table-column>

            <el-table-column label="注册日期" align="center" prop="createTime"/>

          </el-table>
        </div>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </div>

      <content-wrapper :visible.sync="openNewDialog" :title="title">
        <change-pwd v-if="title===MemberDialogTitle.ChangePwd &&current" :memberId="current.memberId"
                    @ok="openNewDialog=false"></change-pwd>
        <recharge v-if="title===MemberDialogTitle.Recharge &&current" :memberId="current.memberId"
                  @ok="onRechargeOk"></recharge>
      </content-wrapper>
    </div>
  </div>
</template>
<script>
import SvgItem from "@/views/cashier/desk/components/svgItem.vue";
import LeftContainer from "@/views/cashier/components/leftContainer.vue";
import {listMemberDetail, listMembers} from "@/api/cashier/member";
import ContentWrapper from "@/views/cashier/desk/components/contentWrapper.vue";

import ChangePwd from "@/views/cashier/member/components/changePwd.vue";
import {MemberDialogTitle} from "@/views/cashier/components/constant";
import Recharge from "@/views/cashier/member/components/recharge.vue";

export default {
  computed: {
    MemberDialogTitle() {
      return MemberDialogTitle
    }
  },
  components: {Recharge, ChangePwd, ContentWrapper, LeftContainer, SvgItem},
  dicts: ['sys_user_sex'],
  data() {

    return {
      openNewDialog: false,
      title: '修改密码',
      loading: false,
      memberList: [],
      current: null,
      total: 0,
      queryParams: {
        keyword: null, pageNum: 1, pageSize: 10
      }
    }
  },
  created() {

  },
  mounted() {
    this.getList()
  },
  methods: {
    onRechargeOk() {
      this.openNewDialog = false;
      if (this.current) {
        this.queryMember(this.current?.memberId,true);
      }
    },
    onPwdClick(val) {
      this.title = val
      this.openNewDialog = true
    },
    onRowClick(item) {
      this.current = item;
    },
    rowStyle({row}) {
      if (this.current && this.current.memberId === row.memberId) {
        return {'background-color': '#1890ff !important', color: '#fff !important'};
      }
      return {cursor: 'pointer'};
    },
    getList() {
      this.loading = true
      listMembers(this.queryParams).then(response => {
        let list = response.rows || [];
        list.forEach(p => {
          p.showAmount = false;
        })
        this.memberList = list;
        this.total = response.total;

      }).finally(() => this.loading = false)
    },
    onRefreshClick() {
      if (this.current) {
        this.queryMember(this.current?.memberId,this.current?.showAmount);
      }

    },
    queryMember(id,showAmount) {
      listMemberDetail(id).then(res => {
        this.current = {showAmount: showAmount, ...res.data}
      })
    }
  }

}
</script>
<style scoped lang="scss" src="./index.scss"/>
