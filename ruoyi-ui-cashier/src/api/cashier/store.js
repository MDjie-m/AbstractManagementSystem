import request from "@/utils/request";

export function queryStoreBaseInfo(needEmployees) {
  return request({
    url: '/cashier/store/info',
    method: 'get',
    params:{needEmployees}
  })
}
