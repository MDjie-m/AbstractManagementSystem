import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/billiard/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(orderId) {
  return request({
    url: '/billiard/order/' + orderId,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/billiard/order',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/billiard/order',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrder(orderId) {
  return request({
    url: '/billiard/order/' + orderId,
    method: 'delete'
  })
}


// 获取订单类型枚举
export function getsAnOrderTypeEnumeration() {
  return request({
    url: '/billiard/order/getsAnOrderTypeEnumeration',
    method: 'get'
  })
}

// 获取支付方式枚举
export function getsAnEnumerationOfPaymentMethods() {
  return request({
    url: '/billiard/order/getsAnEnumerationOfPaymentMethods',
    method: 'get'
  })
}

// 获取订单状态枚举
export function getsAnOrderStatusEnumeration() {
  return request({
    url: '/billiard/order/getsAnOrderStatusEnumeration',
    method: 'get'
  })
}
