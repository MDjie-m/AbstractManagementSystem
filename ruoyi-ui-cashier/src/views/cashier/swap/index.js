import {addSwap, getSwapPreview, querySwapRecordList} from "@/api/cashier/store";
import CustomDialog from "@/views/cashier/components/CustomDialog.vue";
import {TimeFormat} from "@/views/cashier/components/constant";

export default {
  computed: {
    TimeFormat() {
      return TimeFormat
    }
  },
  components: {CustomDialog},
  data() {
    return {
      openSwap: false,
      dayList: [],
      swapForm: {
        scheduleDay: null,
        remark:''
      },
      confirmText:'',
      swapPreview: {
        "swapRecordId": 0,
        "total": 0.00,
        "cashTotal": 0.00,
        "deskTotal": 0.00,
        "tutorTotal": 0.00,
        "goodsTotal": 0.00,
        "totalWipeZero": 0.00,
        "suspendOrderCount": 0,
        "suspendOrderAmount": 0.00,
        notSettledOrderCount:0,
        notSettledOrderAmount:0.00,
        "scheduleDay": "2024-10-11",
        "params": {}
      },
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      customTime: [
        this.$time().add(-10, 'day').format('YYYY-MM-DD'),
        this.$time().format('YYYY-MM-DD')
      ],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        user: null,

        startTime: this.$time().add(-10, 'day').format('YYYY-MM-DD'),
        endTime: this.$time().format('YYYY-MM-DD'),
      },
      total: 0,
      recordList: [],
    }
  },
  mounted() {
    this.getList();

  },
  methods: {
    onSubmitSwap() {
      return addSwap(this.swapForm).then(res => {
        this.$modal.msgSuccess("交班成功")
        this.getList()
      })
    },
    onShowSwapClick() {
      this.dayList = [
        {
          value: this.$time().format(TimeFormat.YYYY_MM_DD),
          label: this.$time().format(TimeFormat.YYYYcMMcDDc),
        },
        {
          value: this.$time().add(-1, 'day').format(TimeFormat.YYYY_MM_DD),
          label: this.$time().add(-1, 'day').format(TimeFormat.YYYYcMMcDDc),
        },
      ]
      this.swapForm.scheduleDay = this.dayList[0].value;
      this.swapForm.remark=''
      this.querySwapPreview();
    },
    querySwapPreview() {
      getSwapPreview({
        startTime: this.swapForm.scheduleDay,
        endTime: this.swapForm.scheduleDay
      }).then(res => {
        this.swapPreview = res.data || {};
        this.openSwap = true;
        this.confirmText=this.swapPreview.notSettledOrderCount>'0' ||this.swapPreview.suspendOrderCount>'0'?"当前有未结算/挂起的订单，确认交班？":''
      })
    },
    getList() {
      if (!this.customTime) {
        this.customTime = [
          this.$time().add(-10, 'day').format('YYYY-MM-DD'),
          this.$time().add(1, 'day').format('YYYY-MM-DD')
        ];
      }
      this.queryParams.startTime = this.customTime[0] + ' 00:00:00';
      this.queryParams.endTime = this.customTime[1] + ' 24:00:00';
      querySwapRecordList(this.queryParams).then(response => {
        this.recordList = response.rows || [];

        this.total = response.total || 0;
        this.loading = false;
      });
    }
  }
}
