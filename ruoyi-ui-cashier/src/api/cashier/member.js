import request from "@/utils/request";

export function listMembers(params) {
  return request({
    url: `/cashier/member/list`,
    method: 'get',
    params:params
  })
}
