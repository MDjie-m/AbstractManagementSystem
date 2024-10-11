import Vue from "vue";

export const OrderStatus = Object.freeze({
  Charging: 0,//"计费中"),
  Stop: 1,//"待结算"),
  Settled: 2,//"已结算"),
  Void: 3,//"作废"),
  Suspend: 4,//"挂起订单")
})
export const CalcTimeStatus = Object.freeze({
  Busy: 1,
  Pause: 2,
  Stop: 3
})

export const TutorWorkStatus = Object.freeze({
  Wait: 0,
  Busy: 1,
  Stop: 3

})

export const LightType = Object.freeze({
  Temp: 0,
  CalcFee: 1,
})
export const DeskStatus = Object.freeze({
  Wait: 0,
  Busy: 1,
  PAUSE: 2,
  Stop: 3,

})
export const ChooseType = Object.freeze({
  Goods: 0,
  Tutor: 1,

})
export const OrderPayType = Object.freeze({
  SCAN_QRCODE: 0,
  CASH: 1,
  MEMBER: 2,
})

export const MemberDialogTitle = Object.freeze({
  ChangePwd: '修改密码',
  Recharge: "会员充值",
  Order: "消费记录"
})
export const DeskDialogTitle = Object.freeze({
  LineUp: '排队叫号',
  BookingDesk: "台桌预约",
  BookingTutor: "教练预约",
  BookingVerify: "预约核销"
})
export const formatTime = (val, format) => {
  return Vue.prototype.$time(val).format(format || 'YYYY-MM-DD HH:mm')
}
export const BookingStatus = Object.freeze({
  Active: 0,//生效中
  Used: 1,//已使用,
  Expire: 2 //"已过期"
})
//计划类型：4=陪练,5=教学
export const WorkPlanType = Object.freeze({

  Play: {
    value: 4,
    label: '陪练'
  },//陪练,
  Teach: {
    value: 5,
    label: '教学'
  }//"教学"
})
export const ChangeType = Object.freeze({
  In: 0,
  Out: 1,
  Check: 2
})

export const TimeFormat = Object.freeze({
  YYYY_MM_DD: 'YYYY-MM-DD',
  YYYYcMMcDDc: 'YYYY年MM月DD日',
  YYYY_MM_DD_HH_mm: 'YYYY-MM-DD HH:mm',
  HH_mm: 'HH:mm'
})
