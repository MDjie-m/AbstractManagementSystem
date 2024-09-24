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
