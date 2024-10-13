<template>
  <custom-dialog title="更换台桌" class="custom-dialog" :visible.sync="visible" width="500px" append-to-body>
    <el-form ref="form" :model="targetDesk" label-width="120px">

      <el-form-item v-if="currentDesk" label="当前台桌:">
        <span>{{ currentDesk.longTitle }}</span>
      </el-form-item>

      <el-form-item label="目标台桌:">
        <el-select v-model="targetDesk.deskId" filterable @change="onDeskChange">
          <!--                <div slot="prefix"></div>-->
          <el-option :value="item.deskId" :key="'targetDesk'+item.deskId"
                     :label="item.longTitle"
                     v-for="item in deskList">
            <div style="display: flex;flex-direction: row;align-items: center">
              <div class="desk-status" :class="`desk-status-`+item.status"></div>
              <div> {{ item.longTitle }}</div>
            </div>
          </el-option>
        </el-select>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer" v-loading="loading">

      <el-button type="primary" @click="onSwapDeskSubmit()">换台</el-button>
      <el-button @click="onCancel">取 消</el-button>
    </div>
  </custom-dialog>
</template>
<script>
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {listDesk} from "@/api/cashier/desk";
import {DeskStatus} from "@/views/cashier/components/constant";
import {tutorOrderHandle} from "@/api/cashier/tutor";

export default {
  computed: {
    DeskStatus() {
      return DeskStatus
    }
  },
  emits:['onOk'],
  props: ['open', 'deskId', 'tutorId'],
  components: {CustomDialog},
  data() {
    return {
      loading: false,
      targetDesk: {
        deskId: null,
      },
      currentDesk: null,
      deskList: [],
      visible: false,
    }
  },
  watch: {
    open(newVal, oldVal) {
      if (newVal === oldVal) {
        return;
      }
      if (this.visible === newVal) {
        return;
      }
      this.visible = true
    },
    visible(newVal, oldVal) {
      if (newVal === oldVal) {
        return;
      }
      if (this.open === newVal) {
        return;
      }
      this.$emit('update:open', newVal)
    }
  },
  mounted() {
    this.queryDeskList();
    this.visible = this.open;
  },
  methods: {
    onDeskChange() {
      this.targetDesk = this.deskList.find(p => p.deskId === this.targetDesk?.deskId);
    },
    queryDeskList() {
      listDesk({}).then(res => {
        this.deskList = (res.data || []);
        this.currentDesk = this.deskList.find(p => p.deskId === this.deskId);
        this.deskList = this.deskList.filter(p => p.deskId !== this.deskId && p.status === DeskStatus.Busy);
      })
    },
    onSwapDeskSubmit() {
      if (!this.targetDesk?.deskId) {
        this.$modal.msgWarning("请选择台桌");
        return Promise.reject()
      }
      this.loading = true;
      return tutorOrderHandle("swap", {
        tutorId: this.tutorId,
        newDeskId: this.targetDesk.deskId
      }).then(res => {
        this.$modal.msgSuccess(`已更换到台桌${this.targetDesk.longTitle}`);
        this.$emit("onOk")
        this.onCancel();
      }).finally(() => this.loading = false)

    },
    onCancel() {
      this.visible = false;
      this.$emit('update:open', false)
    }
  }
}
</script>
<style lang="scss" scoped>
.desk-status {
  height: 10px;
  width: 10px;
  border-radius: 50%;
  margin-right: 10px;
}

.desk-status-0 {
  background-color: #8a8a8a;
}

.desk-status-1, .desk-status-2 {
  background-color: #26dc51;
}

.desk-status-3 {
  background-color: #E88E37;
}
</style>
