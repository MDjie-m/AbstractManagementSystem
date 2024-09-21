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
