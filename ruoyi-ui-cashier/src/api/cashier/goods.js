import request from "@/utils/request";

export function listAllGoods(params) {
  return request({
    url: '/cashier/goods/list',
    method: 'get',
    params: params
  })
}
