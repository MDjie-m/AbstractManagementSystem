import request from "@/utils/request";

export function listAllTutor(params) {
  return request({
    url: '/cashier/tutor/list',
    method: 'get',
    params: params
  })
}
