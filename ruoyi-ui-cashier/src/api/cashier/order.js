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

export function listOrders(params) {
  return request({
    url: `/cashier/order/list`,
    method: 'get',
    params: params
  })
}

export function getOrderInfo(orderId) {
  return request({
    url: `/cashier/order/${orderId}`,
    method: 'get',
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
    data: data
  })
}

export function voidOrder(orderId, remark) {
  return request({
    url: `/cashier/order/${orderId}/void`,
    method: 'post',
    params: {remark}
  })
}

export function fillMember(orderId, memberId) {
  return request({
    url: `/cashier/order/${orderId}/member`,
    method: 'post',
    params: {memberId}
  })
}

export function finishOrder(params) {
  return request({
    url: `/cashier/order/finish`,
    method: 'post',
    data: params
  })
}

export function orderBuy(params) {
  return request({
    url: `/cashier/order/buy`,
    method: 'post',
    data: params
  })
}

export function orderStopDesk(orderId, deskId) {
  return request({
    url: `/cashier/order/${orderId}/stop-desk`,
    method: 'post',
    params: {deskId}
  })
}
