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
