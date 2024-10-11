import request from "@/utils/request";

export function queryStoreBaseInfo(needEmployees) {
  return request({
    url: '/cashier/store/info',
    method: 'get',
    params:{needEmployees}
  })
}
export function queryDashboard(params) {
  return request({
    url: '/cashier/store/dashboard',
    method: 'get',
    params:params
  })
}
export function querySwapRecordList(params) {
  return request({
    url: '/cashier/store/swap/list',
    method: 'get',
    params:params
  })
}
export function getSwapPreview(params) {
  return request({
    url: '/cashier/store/swap/preview',
    method: 'get',
    params:params
  })
}
export function addSwap (params) {
  return request({
    url: '/cashier/store/swap',
    method: 'post',
    data:params
  })
}
