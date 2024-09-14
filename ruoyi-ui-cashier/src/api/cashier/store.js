import request from "@/utils/request";

export function queryStoreBaseInfo() {
  return request({
    url: '/cashier/store/info',
    method: 'get',
  })
}
