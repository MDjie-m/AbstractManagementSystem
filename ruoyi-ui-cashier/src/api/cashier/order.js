import request from "@/utils/request";

export function stopOrder(orderId) {
  return request({
    url: `/cashier/order/${orderId}/stop`,
    method: 'post',
  })
}
export function stopOrderByTimer(orderId) {
  return request({
    url: `/cashier/order/${orderId}/stop-by-timer`,
    method: 'post',
  })
}
export function suspendOrder(orderId) {
  return request({
    url: `/cashier/order/${orderId}/suspend`,
    method: 'post',
  })
}
export function orderPrePay(data) {
  return request({
    url: `/cashier/order/pre-pay`,
    method: 'post',
    data:data
  })
}
export function voidOrder(orderId,remark) {
  return request({
    url: `/cashier/order/${orderId}/void`,
    method: 'post',
    params:{remark}
  })
}
