<template>
  <div class="page-container"  >

    <left-container @onRefreshClick="onRefreshClick">
      <div class="section-container " style="margin:10px;margin-bottom: 0" v-if="current">
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


      </div>
      <div style="display: flex ;justify-content: center; margin: 10px">
        <tool-bar  v-if="current" title="操作" style="width: 100%">
          <SvgItem     svg-icon="credit_card" class=" icon-blue" label="充值" :btnAble="true" @click.native="onPwdClick(MemberDialogTitle.Recharge)"/>
          <SvgItem   svg-icon="password" label="修改密码"   class=" icon-red"  :btnAble="true" @click.native="onPwdClick(MemberDialogTitle.ChangePwd)"/>
          <SvgItem   svg-icon="money" label="查看余额" class=" icon-blue"  :btnAble="true" @click.native="current.showAmount=!current.showAmount"/>
          <SvgItem   svg-icon="list" label="消费记录" class=" icon-green"  :btnAble="true" @click.native="onPwdClick(MemberDialogTitle.Order)"/>
          <SvgItem     svg-icon="edit" label="修改会员" class=" icon-yellow"  :btnAble="true" @click.native="onShowEdit"/>
          <SvgItem     svg-icon="register" label="注册会员" class=" icon-blue"  :btnAble="true" @click.native="onAddUserClick"/>
        </tool-bar>

        <SvgItem  v-else    svg-icon="register" label="注册会员" class=" icon-blue"  :btnAble="true" @click.native="onAddUserClick"/>
      </div>
    </left-container>
    <div class="right-panel">
      <div class="section-container" style="flex-direction: row">
        <el-form :inline="true"  >
          <el-form-item   >
            <el-input  style="width: 500px" @keydown.enter.native="getList" v-model="queryParams.keyword" maxlength="20" autocomplete="off"
                      placeholder="请输入会员姓名或者手机号"></el-input>
          </el-form-item>
          <el-form-item>      <el-button size="mini" type="primary" circle icon="el-icon-search" @click.stop="getList"/>
          </el-form-item>
        </el-form>


      </div>
      <div class="section-container member-box">
        <div class="table-box">
          <el-table v-loading="loading" :data="memberList" @change="getList" @row-click ="onRowClick"
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
        <money-record e v-if="title===MemberDialogTitle.Order &&current" :memberId="current.memberId"
                      @ok="openNewDialog=false"></money-record>
      </content-wrapper>
      <custom-dialog :hide-close="false" v-if="showEdit" width="700px" :visible.sync="showEdit" :title="current?'修改会员':'注册会员'">
        <member-register :member="current" @onOk="onEditOk" @onCancel="onEditCancel"></member-register>
        <div slot="footer"></div>
      </custom-dialog>
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
import MoneyRecord from "@/views/cashier/member/components/moneyRecord.vue";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import MemberRegister from "@/views/cashier/member/components/memberRegister.vue";
import ToolBar from "@/views/cashier/desk/components/toolBar.vue";


export default {
  computed: {
    MemberDialogTitle() {
      return MemberDialogTitle
    }
  },
  components: {
    ToolBar,
    MemberRegister, CustomDialog, MoneyRecord, Recharge, ChangePwd, ContentWrapper, LeftContainer, SvgItem},
  dicts: ['sys_user_sex'],
  data() {

    return {
      showEdit:false,
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
    onOtherClick(){
      this.current=null;
    },
    onRechargeOk() {
      this.openNewDialog = false;
      if (this.current) {
        this.queryMember(this.current?.memberId, true);
      }
    },
    onAddUserClick(){
      this.current=null;
      this.showEdit=true
    },
    onEditOk(){

      this.showEdit=false;
      this.getList()
    },
    onEditCancel(){
      this.showEdit=false;
    },
    onShowEdit(){
      this.showEdit=true;
    },
    onPwdClick(val) {
      this.title = val
      this.openNewDialog = true
    },
    onRowClick(item) {
      window.event.stopImmediatePropagation()

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
        if(this.current){
          this.current=this.memberList.find(p=>p.memberId===this.current.memberId)
        }
      }).finally(() => this.loading = false)
    },
    onRefreshClick() {
      if (this.current) {
        this.queryMember(this.current?.memberId, this.current?.showAmount);
      }

    },
    queryMember(id, showAmount) {
      listMemberDetail(id).then(res => {
        this.current = {showAmount: showAmount, ...res.data}
      })
    }
  }

}
</script>
<style scoped lang="scss" src="./index.scss"/>
