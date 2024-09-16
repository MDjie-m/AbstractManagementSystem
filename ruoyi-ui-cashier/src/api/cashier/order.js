import request from "@/utils/request";

export function stopOrder(orderId) {
  return request({
    url: `/cashier/order/${orderId}/stop`,
    method: 'post',
  })
}
export function suspendOrder(orderId) {
  return request({
    url: `/cashier/order/${orderId}/suspend`,
    method: 'post',
  })
}
export function voidOrder(orderId,remark) {
  return request({
    url: `/cashier/order/${orderId}/void`,
    method: 'post',
    params:{remark}
  })
}
