import request from '@/utils/request'


// 根据条件查询[咨询师订单]列表
export function queryConsultantOrderList(query) {
  return request({
    url: '/system/consultant-order/getOrderList',
    method: 'post',
    params: query,
    data: query
  })
}


//查询订单详情
export function queryConsultantOrderByNo(orderNo) {
  return request({
    url: '/system/consultant-order/getOrderDetailByNo/'+orderNo,
    method: 'get',
    params: {},
    data: {}
  })
}
